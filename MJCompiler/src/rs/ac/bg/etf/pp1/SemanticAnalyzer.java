package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	/********************* helper struct ******************/
	
	public class Pair {
		public Obj first;
		public List<Struct> second;
		
		public Pair(Obj first, List<Struct> second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	
	Logger log = Logger.getLogger(getClass());
	
	/****************** error handling ********************/
	
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> linija ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" -> linija ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
	
	/******************** helper fields ********************/
	
	public static int programVarCount = 0;
	private static boolean mainMethodDefined = true;	/* PAY ATTENTION */
	
	private static Struct currentType = null;
	private static boolean currentTypeIsArray = false;
	
	private static String currentClassName = "";
	private static Struct currentClassStruct = null;
	
	private static Map<Struct, String> map_ClassStructToName = new HashMap<>();
	
	private static Obj currentMethodObjNode;
	private static Struct currentMethodReturnTypeStruct;
	private static boolean returnStatementFound = false;
	
	private static Obj constructorObjNode = Tab.noObj;
	
	private static int argCounter = 0;
	
	private static List<Obj> listOfFormParsObjectNodes = new ArrayList<>();
	private static Map<Obj, List<Obj>> listOfDefiniedConstructors = new HashMap<>();
	
	private Obj methodToBeOverridenObjNode = null;
	
	private enum Operators {PLUS, MINUS, MUL, DIV, PERCENT, DEQ, NE, GT, GE, LT, LE, AND, OR, EQ, INC, DEC};
	Operators operator;
	
	int openLoopScopeCounter = 0; // while and foreach
	
	private static List<Obj> listOfGlobalFunctionObjNodes = new ArrayList<>();
	
	private static Obj currVarForeach = null;
	
	private static List<Obj> listOfObjNodesToBeAssigned = new ArrayList<>();
	
	private static Stack<Pair> stackOfCallsWithActPars = new Stack<>();
	
	private static Obj invokedConstructorObjNode = null;
	
	
	/********************** Program ************************/
	
	public void visit(ProgramName programName){
    	programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program_ program){
    	programVarCount = Tab.currentScope.getnVars();
    	
    	if (!mainMethodDefined) {
    		report_error("Metoda void main() nije definisana", null);
    	}
    	
    	// uvezujemo sve u ProgramName objektni cvor
    	Tab.chainLocalSymbols(program.getProgramName().obj);
    	Tab.closeScope();
    }
    
    /************************ Type *************************/
    
    public void visit(Type type) {
    	// type should be in the symbol table
    	
    	String typeName = type.getTypeName();
    	Obj objNode = Tab.find(typeName);
    	
    	if (objNode == Tab.noObj) {
    		report_error("Tip '" + typeName + "' nije pronadjen u tabeli simbola", null);
    		type.struct = Tab.noType;
    	}
    	else if (Obj.Type == objNode.getKind()){
    		// struct node of the given type
			type.struct = objNode.getType();
		} 
    	else {
			report_error("Ime '" + typeName + "' ne predstavlja tip u tabeli simbola", type);
			type.struct = Tab.noType;
		}
    	
    	currentType = type.struct;
    } 
    
    /********************* ConstDecl ***********************/ 
    
    public void visit(NumConstant constant) {
    	constant.struct = Tab.intType;
    }
    
	public void visit(CharConstant constant) {
	    constant.struct = Tab.charType;
	}

	public void visit(BoolConstant constant) {
		constant.struct = TabExtension.boolType;
	}
    
    public void visit(OtherConstDeclarations_ constDecl) {
    	String constName = constDecl.getIdentName();
    	Obj objNode = Tab.find(constName);
    	
    	if (objNode != Tab.noObj) {
    		report_error("Konstanta '" + constName + "' je vec definisana", constDecl);
    		return; 
    	}
    	
    	Constant constant = constDecl.getConstant();
    	Struct constantStruct = constant.struct;
    	
    	if (!constantStruct.assignableTo(currentType)) {
    		report_error("Datu vrednost nije moguce dodeliti promenljivoj zbog nekompatibilnog tipa", constDecl);
    		return;
    	}
    	
    	Obj obj = Tab.insert(Obj.Con, constName, currentType);
    	
    	if (constant.getClass() == NumConstant.class) 		obj.setAdr(((NumConstant) constant).getValue());
    	else if (constant.getClass() == CharConstant.class)	obj.setAdr(((CharConstant) constant).getValue());
    	else if (constant.getClass() == BoolConstant.class) obj.setAdr(((BoolConstant) constant).getValue() == true ? 1 : 0);
    
    	report_info("Konstanta '" + constName + "' je definisana", constDecl);
    }
    
    public void visit(ConstDecl constDecl) {
    	String constName = constDecl.getFirstIdentName();
    	Obj objNode = Tab.find(constName);
    	
    	if (objNode != Tab.noObj) {
    		report_error("Konstanta '" + constName + "' je vec definisana", constDecl);
    		return; 
    	}
    	
    	Constant constant = constDecl.getConstant();
    	Struct constantStruct = constant.struct;
    	
    	if (!constantStruct.assignableTo(currentType)) {
    		report_error("Datu vrednost nije moguce dodeliti promenljivoj zbog nekompatibilnog tipa", constDecl);
    		return;
    	}
    	
    	Obj obj = Tab.insert(Obj.Con, constName, currentType);
    	
    	if (constant.getClass() == NumConstant.class) 		obj.setAdr(((NumConstant) constant).getValue());
    	else if (constant.getClass() == CharConstant.class)	obj.setAdr(((CharConstant) constant).getValue());
    	else if (constant.getClass() == BoolConstant.class) obj.setAdr(((BoolConstant) constant).getValue() == true ? 1 : 0);
    
    	report_info("Konstanta '" + constName + "' je definisana", constDecl);
    }
    
    /************************* global VarDecl *****************************/

    public void visit(Global_NewVarDecl_ newVarDecl) {
    	String varName = newVarDecl.getVarName();
    	Obj constNameNode = Tab.find(varName);
    	
    	// check if var is declared in the scope
    	if(constNameNode != Tab.noObj && Tab.currentScope.findSymbol(varName) != null) {
				report_error("Globalna varijabla '" + varName + "' je vec deklarisana", newVarDecl);
				return;
    	}
    	
    	// check if var is array
    	Struct varType = currentTypeIsArray ? new Struct(Struct.Array, currentType) : currentType;
    	
    	Tab.insert(Obj.Var, varName, varType);
    	
    	report_info("Globalna varijabla '" + varName + "' je deklarisana " + (currentTypeIsArray?"kao niz":""), newVarDecl);
    	
    	/* PAY ATTENTION */
    	currentTypeIsArray = false;
    }
    
    public void visit(OptionalSquare_ optionalSquare) {
    	currentTypeIsArray = true;
    }
    
    /************************* local VarDecl *****************************/

    public void visit(NewVarDecl_ newVarDecl) {
    	String varName = newVarDecl.getVarName();
    	Obj constNameNode = Tab.find(varName);
    	
    	// check if var is declared in the scope
    	if(constNameNode != Tab.noObj && Tab.currentScope.findSymbol(varName) != null) {
				report_error("Lokalna varijabla '" + varName + "' je vec deklarisana", newVarDecl);
				return;
    	}
    	
    	// check if var is array
    	Struct varType = currentTypeIsArray ? new Struct(Struct.Array, currentType) : currentType;
    	
    	Tab.insert(Obj.Var, varName, varType);
    	
    	report_info("Lokalna varijabla '" + varName + "' je deklarisana " + (currentTypeIsArray?"kao niz":""), newVarDecl);
    	
    	/* PAY ATTENTION */
    	currentTypeIsArray = false;
    }
    
    /********************** ClassDecl *****************************/
    
    public void visit(NoDerivation node) {
    	node.struct = Tab.noType;
    }
    
    public void visit(OptionalDerivation_ node) {
    	String parentClassName = node.getType().getTypeName();
    	Obj objNode = Tab.find(parentClassName);
    	
    	if (objNode == Tab.noObj) {
    		node.struct = Tab.noType;
    		report_error("Roditeljska klasa ne postoji u tabeli simbola", node);
    		return;
    	}
   
    	Struct parentClassStruct = objNode.getType();
    	
    	if (parentClassStruct.getKind() != Struct.Class) {
    		report_error("Tip '" + parentClassName + "' nije klasa", node);
    		node.struct = Tab.noType;
    	}
    	else {
    		node.struct = parentClassStruct;
    	}
    }
    
    public void visit(ClassNameAndDerivation classNameAndDerivation) {
    	String className = classNameAndDerivation.getClassName();
    	
    	// check if class is already declared
    	Obj objNode = Tab.find(className);
    	if(objNode != Tab.noObj) {
			report_error("Klasa " + className + " je vec deklarisana", classNameAndDerivation);    		
			return;
    	}
    	
    	// check if the class is derived
    	Struct parentClassStruct = classNameAndDerivation.getOptionalDerivation().struct;
    	String parentClassName = map_ClassStructToName.getOrDefault(parentClassStruct, "");
    	
    	currentClassName = className;
    	currentClassStruct = new Struct(Struct.Class, parentClassStruct); /* CUP IS NOT WORKING PROPERLY HERE */
    	currentClassStruct.setElementType(parentClassStruct);
    	map_ClassStructToName.put(currentClassStruct, currentClassName);
    	
    	// add new class to symbol table
    	classNameAndDerivation.obj = Tab.insert(Obj.Type, currentClassName, currentClassStruct);
    	Tab.openScope();
    	
    	/* PAY ATTENTION */
    	Tab.insert(Obj.Fld, "VFT_pointer", Tab.intType);
    	
    	if (parentClassStruct != Tab.noType) {
    		// copy fields from parent class
    		
    		for (Obj field: parentClassStruct.getMembers()) {
    			if (field.getName().equals("VFT_pointer")) {
    				continue;
    			}
    			else if (field.getKind() == Obj.Fld) {
    				Tab.insert(Obj.Fld, field.getName(), field.getType());
    			}
    			else if(field.getKind() == Obj.Meth && !parentClassName.equals(field.getName())) {
    				/* PAY ATTENTION: parent class constructors should not be inherited */
            		Tab.currentScope().addToLocals(field);
    			}
    		}
    	}
    	
    	/* PAY ATTENTION */
    	listOfDefiniedConstructors.clear(); 
    	
    	report_info("Klasa '" + className + "' je uspesno deklarisana" + (parentClassName.equals("") ? "" : " i izvedena iz klase '" + parentClassName + "'"), classNameAndDerivation);
    }
    
    public void visit(ClassDecl classDecl) {
    	/* PAY ATTENTION: class scope is closing */
    	
    	Tab.chainLocalSymbols(currentClassStruct);
    	Tab.closeScope();
    	currentClassName = "";
    	currentClassStruct = null;
    	/* PAY ATTENTION */
    	listOfDefiniedConstructors.clear(); 
    }
    
    /*************************** Class_VarDecl ******************************/
    
    public void visit(Class_NewVarDecl newVarDecl) {
    	String varName = newVarDecl.getVarName();
    	Obj constNameNode = Tab.find(varName);
    	
    	// check if var is declared in the scope
    	if(constNameNode != Tab.noObj && Tab.currentScope.findSymbol(varName) != null) {
				report_error("Polje '" + varName + "' klase je vec deklarisano", newVarDecl);
				return;
    	}
    	
    	// check if var is array
    	Struct varType = currentTypeIsArray ? new Struct(Struct.Array, currentType) : currentType;
    	
    	Tab.insert(Obj.Fld, varName, varType);
    	
    	report_info("Polje '" + varName + "' klase '" + currentClassName + "' je deklarisano " + (currentTypeIsArray?"kao niz":""), newVarDecl);
    	
    	/* PAY ATTENTION */
    	currentTypeIsArray = false;
    }
    
    /********************* GlobalMethodDecl ***************************/
    
    public void visit(GlobalMethodDecl_Void globalMethodDecl) {
    	String methodName = globalMethodDecl.getMethodName();
    	currentMethodReturnTypeStruct = Tab.noType;
    	currentMethodObjNode = Tab.insert(Obj.Meth, methodName, currentMethodReturnTypeStruct);
    	
    	Tab.openScope();
		report_info("Zapoceta je definicija globalne funkcije '" + methodName + "'", globalMethodDecl);
    }
    
    public void visit(GlobalMethodDecl_Ident globalMethodDecl) {
    	String methodName = globalMethodDecl.getMethodName();
    	currentMethodReturnTypeStruct = globalMethodDecl.getType().struct;
    	currentMethodObjNode = Tab.insert(Obj.Meth, methodName, currentMethodReturnTypeStruct);
    	
    	Tab.openScope();
		report_info("Zapoceta je definicija globalne funkcije '" + methodName + "'", globalMethodDecl);
    }
    
    public void visit(MethodDecl methodDecl) {
//    	if (!returnStatementFound && currentMethodReturnTypeStruct != Tab.noType) {
//    		report_error("Funkcija '" + currentMethodObjNode.getName() + "' nema return iskaz", null);
//    		
//    		/* PAY ATTENTION: should we call return if error occurs */
//    	}
    	// if global function is declared this should not be counted in level
    	int incrementForThis = 0;
    	if (currentClassStruct != null) ++incrementForThis;
    	
    	
    	currentMethodObjNode.setLevel(listOfFormParsObjectNodes.size() + incrementForThis); /* PAY ATTENTION: +1 for this */
    	Tab.chainLocalSymbols(currentMethodObjNode);
    	/* PAY ATTENTION: method overriding */
    	if (currentClassStruct != null) overrideMethod(methodDecl);
    	Tab.closeScope();
    	
		report_info("Kraj dosega " + (currentClassName.length()==0? "globalne funkcije":"metode") + "'" + currentMethodObjNode.getName() + "'", methodDecl);

		/* PAY ATTENTION */
		if (currentClassName.length()==0) {
			// global function if definied
			listOfGlobalFunctionObjNodes.add(currentMethodObjNode);
		}
		
    	returnStatementFound = false;
    	currentMethodObjNode = null;
    	currentMethodReturnTypeStruct = null;
    	methodToBeOverridenObjNode = null;
    	listOfFormParsObjectNodes = new ArrayList<>(); /* PAY ATTENTION */
    }
    
    /***************** M_Return ***************/
    
    public boolean checkIfFunctionScopeIsOpened() {
    	/* constructor is not included */
    	return currentMethodObjNode != Tab.noObj && currentMethodObjNode != null;
    }
    
    public void visit(ReturnOptionalExpr_ returnOptionalExpr) {
    	if (!checkIfFunctionScopeIsOpened()) {
    		returnOptionalExpr.struct = Tab.noType;
    		report_error("Return iskaz se ne nalazi u funkciji", returnOptionalExpr);
    		return;
    	}
    	
    	returnStatementFound = true;
    	
    	if (currentMethodReturnTypeStruct == Tab.noType) {
    		returnOptionalExpr.struct = Tab.noType;
    		report_error("Funkcija '" + currentMethodObjNode.getName() + "' je definisana kao void, a sadrzi izraz u return naredbi", returnOptionalExpr);
    		return;
    	}
    	
    	/* PAY ATTENTION: check type compatibility */
    	Struct returnTypeStruct = returnOptionalExpr.getExpr().struct;
    	returnOptionalExpr.struct = returnTypeStruct;
    	
		report_info("Return naredba" + " sa opcionim izrazom", returnOptionalExpr);
    }
    
    public void visit(NoReturnExpr noReturnExpr) {
    	noReturnExpr.struct = Tab.noType;
    	
    	if (!checkIfFunctionScopeIsOpened()) {
    		report_error("Return iskaz se ne nalazi u funkciji", noReturnExpr);
    		return;
    	}
    	
    	returnStatementFound = true;
    	
    	if (currentMethodReturnTypeStruct != Tab.noType) {
    		report_error("Funkcija '" + currentMethodObjNode.getName() + "' ima povratni tip, a return naredba ne sadrzi izraz", noReturnExpr);
    	}
    	
		report_info("Return naredba pronadjena", noReturnExpr);
    }
    
    public void visit(M_Return node) {
    	Struct returnTypeStruct = node.getReturnOptionalExpr().struct;
    	
    	if (!returnTypeStruct.equals(currentMethodReturnTypeStruct)) {
    		report_error("Tip izraza u return naredbi nije ekvivalentan sa povratnim tipom funkcije", node);
    		return;
    	}
    }
    
    /******************** ConstructorDecl ************************/
    
    public void visit(ConstructorDecl_Start constructorDeclStart) {
    	String constructorName = constructorDeclStart.getConstructorName();
    	
    	if (!constructorName.equals(currentClassName)) {
    		report_error("Neispravno deklarisan konstruktor klase '" + currentClassName + "'", constructorDeclStart);
    		constructorObjNode = Tab.noObj;
    	}
    	else {
    		constructorObjNode = Tab.insert(Obj.Meth, constructorName, Tab.noType);
    	}
    	constructorDeclStart.obj = constructorObjNode;
    	Tab.openScope();
    	// we are now inside constructor scope
    	if (!constructorName.equals(currentClassName)) return;
    	
    	/* PAY ATTENTION: implicit this field */
    	Tab.insert(Obj.Var, "this", currentClassStruct);
    	++argCounter;
    }
    
    public void visit(ConstructorDecl constructorDecl) {
    	if (checkIfConstructorExists()) {
    		report_error("Konstruktor klase '" + currentClassName + "' sa datim parametrima vec postoji", constructorDecl);
    	}
    	else {
    		Tab.chainLocalSymbols(constructorObjNode);
    		listOfDefiniedConstructors.put(constructorObjNode, listOfFormParsObjectNodes);
    		report_info("Konstruktor klase '" + currentClassName + "' je definisan", constructorDecl);
    	}
    	
    	Tab.closeScope();
    	
    	constructorObjNode.setLevel(argCounter);
    	constructorObjNode = null;
    	argCounter = 0;
    	listOfFormParsObjectNodes = new ArrayList<>(); /* PAY ATTENTION */
    }
    
    /*************** no constructor defined *************/
    
    public void visit(Methods_Ident node) {
    	openDefaultConstructorScope();
    	closeDefaultConstructorScope();
    }
    
    public void visit(Methods_Void node) {
    	openDefaultConstructorScope();
    	closeDefaultConstructorScope();
    }
 
    public void visit(EmptyClassMethodConstructorLists node) {
    	openDefaultConstructorScope();
    	closeDefaultConstructorScope();
    }
    
    public void visit(EmptyConstructorMethodLists node) {
    	openDefaultConstructorScope();
    	closeDefaultConstructorScope();
    }
    
    public void openDefaultConstructorScope() {
    	constructorObjNode = Tab.insert(Obj.Meth, currentClassName, Tab.noType);
    	Tab.openScope();
    	
    	Tab.insert(Obj.Var, "this", currentClassStruct);
    	++argCounter;
    }
	
    public void closeDefaultConstructorScope() {
    	Tab.chainLocalSymbols(constructorObjNode);
    	Tab.closeScope();
    	
    	listOfDefiniedConstructors.put(constructorObjNode, listOfFormParsObjectNodes);
    	constructorObjNode.setLevel(argCounter);
    	constructorObjNode = null;
    	argCounter = 0;
    
    	report_info("Podrazumevani konstruktor klase '" + currentClassName + "' je definisan", null);
    }
    
    /******************************* FormPars ******************************/
    
    public void visit(OneFormPar_ formPar) {
    	String formParName = formPar.getFormParName();
    	Obj objNode = Tab.find(formParName);
    	
    	if(objNode != Tab.noObj && Tab.currentScope.findSymbol(formParName) != null) {
			report_error("Parametar '" + formParName + "' funkcije  je vec deklarisan", formPar);
			return;
    	}
    	
    	// check if form par is array
    	Struct formParTypeStruct = currentTypeIsArray ? new Struct(Struct.Array, formPar.getType().struct) : formPar.getType().struct;
    	
    	objNode = Tab.insert(Obj.Var, formParName, formParTypeStruct);
    	
    	report_info("Parametar '" + formParName + "' je deklarisan " + (currentTypeIsArray?"kao niz":""), formPar);
    	currentTypeIsArray = false;
    	listOfFormParsObjectNodes.add(objNode);
    }
    
    /****** helper functions for constructor already exists error ******/
    
    public boolean checkIfConstructorExists() {    	
    	for (Obj objNodeOfDefiniedConstructor: listOfDefiniedConstructors.keySet()) {
    		List<Obj> formParsOfDefiniedConstructor = listOfDefiniedConstructors.get(objNodeOfDefiniedConstructor);
    		
    		if (formParsOfDefiniedConstructor.size() != listOfFormParsObjectNodes.size()) continue;
    		
    		int similarityCounter = formParsOfDefiniedConstructor.size();
    		for (Obj formParOfDefinied: formParsOfDefiniedConstructor) {
    			if (listContainsSameType(listOfFormParsObjectNodes, formParOfDefinied)) {
    				--similarityCounter;
    			}
    		}
    		
    		if (similarityCounter == 0) return true;
    	
    	}
    	
    	return false;
    }
    
    public boolean listContainsSameType(List<Obj> list, Obj elem) {
    	for(Obj obj: list) {
    		if (obj.getType().equals(elem.getType())) return true;
    	}
    	return false;
    }
    
    /********************** class MethodDecl ****************************/
    
    public void visit(MethodDecl_Void methodDecl) {
    	String methodName = methodDecl.getMethodName();
    	if(checkIfMethodRedefinitionIsAttempted(methodName)) {
    		/* PAY ATTENTION */
    		// but we saved that object node in 'methodToBeOverridenObjNode' variable
    		Tab.currentScope().getLocals().deleteKey(methodName);
    	}
    	
    	currentMethodReturnTypeStruct = Tab.noType;
    	currentMethodObjNode = Tab.insert(Obj.Meth, methodName, currentMethodReturnTypeStruct);
    	
    	Tab.openScope();
		report_info("Zapoceta je definicija metode '" + methodName + "'", methodDecl);
		
		/* PAY ATTENTION: add implicit this parameter */
		Tab.insert(Obj.Var, "this", currentClassStruct);
    }
    
    public void visit(MethodDecl_Ident methodDecl) {
    	String methodName = methodDecl.getMethodName();
    	if(checkIfMethodRedefinitionIsAttempted(methodName)) {
    		/* PAY ATTENTION */
    		// but we saved that object node in 'methodToBeOverridenObjNode' variable
    		Tab.currentScope().getLocals().deleteKey(methodName);
    	}
    	
    	currentMethodReturnTypeStruct = methodDecl.getType().struct;
    	currentMethodObjNode = Tab.insert(Obj.Meth, methodName, currentMethodReturnTypeStruct);
    	
    	Tab.openScope();
		report_info("Zapoceta je definicija metode '" + methodName + "'", methodDecl);
		
		/* PAY ATTENTION: add implicit this parameter */
		Tab.insert(Obj.Var, "this", currentClassStruct);
    }
    
    public boolean checkIfMethodRedefinitionIsAttempted(String methodName) {
    	// may be inherited
    	Obj objNode = Tab.currentScope().findSymbol(methodName);
    	
    	if(objNode != null) {
    		// inherited method should be overriden with this one
			methodToBeOverridenObjNode = objNode;
			return true;
    	}
    	else {
    		methodToBeOverridenObjNode = null;
    		return false;
    	}
    }
    
    /*************** overrideMethod ***************/
    
    public boolean overrideMethod(SyntaxNode node) {
    	// method may or may not be overriden
    	
    	if (methodToBeOverridenObjNode == null) return false; 
    	
    	int argCount1 = methodToBeOverridenObjNode.getLevel();
    	int argCount2 = currentMethodObjNode.getLevel();
    	if (argCount1 != argCount2) {
    		// method overloading is not supported
    		report_error("Method overloading nije podrzan", node);
    		return false;
    	}
    	
    	/**** get param types of overrided method *****/
    	
    	List<Struct> listOfOveridedMethodParamStructs = new ArrayList<Struct>(); 
    	int numOfParamsInOveridedMethod = methodToBeOverridenObjNode.getLevel();
    	int i = 0;
    	Collection<Obj> listOfOveridedMethodParamObjNodes = methodToBeOverridenObjNode.getLocalSymbols();
    	Iterator<Obj> iterator = listOfOveridedMethodParamObjNodes.iterator();
    	
    	while (i < numOfParamsInOveridedMethod && iterator.hasNext()) {
    		
    	    Obj overridedMethodParamObjNode = iterator.next();
    	    
    	    if(i == 0 && overridedMethodParamObjNode.getName().equals("this")) {
    	    	++i;
    	    	continue;
    	    }
    	    else if (i == 0 && !overridedMethodParamObjNode.getName().equals("this")) {
	        		report_error("Greska u override-ovanju, metod'" + methodToBeOverridenObjNode.getName() + "' nema implicitni this kao prvi parametar", node);    		    		
			    	return false;
    	    }
    	    listOfOveridedMethodParamStructs.add(overridedMethodParamObjNode.getType());    	    
    	    ++i;
    	}
	
    	/**** get param types of current method ******/
    	
    	List<Struct> listOfCurrentMethodParamStructs = new ArrayList<Struct>(); 
    	iterator = listOfFormParsObjectNodes.iterator();
    	i = 0;
    	while (i < currentMethodObjNode.getLevel() - 1 && iterator.hasNext()) {
    		Obj currentMethodParamObjNode = iterator.next();
    		listOfCurrentMethodParamStructs.add(currentMethodParamObjNode.getType());
    		++i;
    	}
    	
    	/********** check other params ***************/
    	
    	
		for (int j = 0; j < i; j++) {
			Struct param1 = listOfOveridedMethodParamStructs.get(j);
			Struct param2 = listOfCurrentMethodParamStructs.get(j);
			
			if (!param1.equals(param2)) {
	    		report_error("Preklopljeni metod '" + currentMethodObjNode.getName()+ "' nema odgovarajuci potpis", node);   
				return false;
			}
		}
    	
    	return true;
    }
    
    /**********************************************/
    
    /************************ Designator ***********************/
    
    public void visit(Designator_Ident designator) {
    	String identName = designator.getDesignatorIdentName();
    	Obj identObjNode = Tab.find(identName);
    	
    	if (identObjNode == Tab.noObj) {
    		designator.obj = Tab.noObj;
    		report_error("Promenjiva '" + identName + "' nije deklarisana", designator);
    		return;
    	}
    	
    	designator.obj = identObjNode;
    	int identObjNodeKind = identObjNode.getKind();
    	
    	if (identObjNodeKind == Obj.Var && checkIfFuncFormParamIsAccessed(identObjNode)) {
			report_info("Pristup formalnom parametru '" + identObjNode.getName() + "'", designator);
		}
		else if (identObjNodeKind == Obj.Var) {
			report_info("Pristup (" + (
						identObjNode.getLevel() == 0 ? "globalnoj" : "lokalnoj"
					) +") promenljivoj '" + identObjNode.getName() + "'", designator);
		}
		else if (identObjNodeKind == Obj.Con) {
			report_info("Pristup simbolickoj konstanti '" + identObjNode.getName() + "'", designator);
		}
    }
    
    public void visit(Designator_FieldAccess designator) {
    	Obj leftDesignatorObjNode = designator.getDesignator().obj;
    	Obj fieldObjNode = null;
    	Struct leftDesignatorTypeStruct = leftDesignatorObjNode.getType();
    	String leftDesignatorTypeName = map_ClassStructToName.getOrDefault(leftDesignatorTypeStruct, "");
    	String fieldName = designator.getFieldName();
    	
    	if (leftDesignatorObjNode == Tab.noObj) {
    		designator.obj = Tab.noObj;
    		return;
    	}
    	else if (leftDesignatorTypeStruct.getKind() != Struct.Class) {
    		designator.obj = Tab.noObj;
    		report_error("Simbol '" + leftDesignatorObjNode.getName() + "' ne predstavlja referencu na klasu", designator);
    		return;
    	}
    	else if (leftDesignatorTypeStruct == currentClassStruct) {
    		// we are inside the class that is not completely definied
    		
    		fieldObjNode = Tab.currentScope().getOuter().findSymbol(fieldName);
    		if (fieldObjNode == null) {
    			designator.obj = Tab.noObj;
    			report_error("Polje '" + fieldName + "' ne predstavlja polje klase '" + currentClassName + "'", designator);
    			return;
    		}
    		
    		designator.obj = fieldObjNode;
    		report_info("Pristup polju '" + fieldName + "' klase '" + currentClassName + "'", designator);
    		return;
    	}
    	else if (leftDesignatorTypeStruct != currentClassStruct) {
    		Collection<Obj> classMembers = leftDesignatorTypeStruct.getMembers();
    		
    		for (Obj member: classMembers) {
    			if (member.getName().equals(fieldName)) {
    				designator.obj = member;
    				
    				if (member.getKind() == Obj.Fld) {
    					report_info("Pristup polju '" + fieldName + "' klase '"  + leftDesignatorTypeName + "'", designator);
    				}
    				/* PAY ATTENTION */
    				else if (member.getKind() == Obj.Meth) {
    					report_info("Poziv metode '" + fieldName + "' klase '"  + leftDesignatorTypeName + "'", designator);				
    				}
    				
    				return;
    			}
    		}
    		
    		report_error("Ime '" + fieldName + "' nije clan klase '" + leftDesignatorTypeName + "'", designator);   
    	}
    	
    }
    
    public void visit(Designator_Indexing designator) {
    	Obj designatorObjNode = designator.getDesignator().obj;
    	Struct designatorTypeStruct = designatorObjNode.getType();
    	String designatorName =  designatorObjNode.getName();
    	int designatorStructKind = designatorTypeStruct.getKind();
    	
    	if (designatorStructKind != Struct.Array) {
    		designator.obj = Tab.noObj;
    		report_error("Promenljiva '" + designatorName + "' nije deklarisana kao niz", designator);
    		return;
    	}
    	
    	Struct indexingExprTypeStruct = designator.getExpr().struct;
    	if (indexingExprTypeStruct != Tab.intType) {
    		designator.obj = Tab.noObj;
    		report_error("Tip izraza unutar [] mora biti tipa int", designator);
    		return;
    	}
    	
    	/* PAY ATTENTION */ 
    	Struct typeOfArrayElemsStruct = designatorTypeStruct.getElemType();
    	Obj arrayElemObjNode = new Obj(Obj.Elem, designatorName, typeOfArrayElemsStruct);
    	designator.obj = arrayElemObjNode;
    	
    	report_info("Pristup nizu '" + designatorName + "'", designator);
    }
    
    /******************** Factor *********************/
    
    public void visit(F_NumConst factor) {
    	factor.struct = Tab.intType;
    }
    
    public void visit(F_CharConst factor) {
    	factor.struct = Tab.charType;
    }
 
	public void visit(F_BoolConst factor) {
	 	factor.struct = TabExtension.boolType;
	}
	
	public void visit(F_Designator factor) {
		Obj factorDesignatorObjNode = factor.getDesignator().obj;
		int factorDesignatorNestingLevel = factorDesignatorObjNode.getLevel();
		Struct factorDesignatorTypeStruct = factorDesignatorObjNode.getType();
		int factorDesignatorKind = factorDesignatorObjNode.getKind();
		
		factor.struct = factorDesignatorTypeStruct;
		
		/* PAY ATTENTION: this is only for vars left from '=' 		   */
		/* take a look at designator assignment in DesignatorStatement */
		
		//if (factorDesignatorKind == Obj.Var && factorDesignatorTypeStruct.getKind() != Struct.Class && factorDesignatorTypeStruct.getKind() != Struct.Array) {
//		if (factorDesignatorKind == Obj.Var && checkIfFuncFormParamIsAccessed(factorDesignatorObjNode)) {
//			report_info("Pristup formalnom parametru '" + factorDesignatorObjNode.getName() + "'", factor);
//		}
//		else if (factorDesignatorKind == Obj.Var) {
//			report_info("Pristup (" + (
//						factorDesignatorNestingLevel == 0 ? "globalnoj" : "lokalnoj"
//					) +") promenljivoj '" + factorDesignatorObjNode.getName() + "'", factor);
//		}
//		else if (factorDesignatorKind == Obj.Con) {
//			report_info("Pristup simbolickoj konstanti '" + factorDesignatorObjNode.getName() + "'", factor);
//		}
		
	}
	
	/****** helper function *****/
	
	public boolean checkIfFuncFormParamIsAccessed(Obj factorObjNode) {
		for(Obj formPar: listOfFormParsObjectNodes) {
			if (factorObjNode.equals(formPar)) return true;
		}
		return false;
	}
	
	/****************************/
	
	public void visit(F_DesignatorFuncCall factor) {
		// D: FactorFunctionCall
		Obj designatorObjNode = factor.getCalledFunctionOrMethodDesignator().getDesignator().obj;
		Struct returnTypeStruct = designatorObjNode.getType();
		int designatorKind = designatorObjNode.getKind();
		String funcName = designatorObjNode.getName();
		
		if (designatorKind != Obj.Meth) {
			factor.struct = Tab.noType;
			report_error("Identifikator mora oznacavati metodu ili funkciju glavnog programa", factor);
			return;
		}
		
		if (listOfGlobalFunctionObjNodes.contains(designatorObjNode)) {
			report_info("Poziv globalne funkcije '" + funcName + "'", factor);
		}
		
		factor.struct = returnTypeStruct;
	}
	
	public void visit(F_Expr factor) {
		Struct innerExprTypeStruct = factor.getExpr().struct;
		factor.struct = innerExprTypeStruct;
	}
	
	public void visit(F_NewObjConstruction factor) {
		//Struct identTypeStruct = factor.getType().struct;
		Struct identTypeStruct = factor.getCalledConstructorName().getType().struct;
		int identKind = identTypeStruct.getKind();
		
		if (identKind != Struct.Class) {
			factor.struct = Tab.noType;
			report_error("Tip za koji se poziva konstruktor mora biti klasa", factor);
			return;
		}
		
		factor.struct = identTypeStruct;
		report_info("Kreiran je objekat klase '" + map_ClassStructToName.get(identTypeStruct) + "'", factor);
		
		// set obj node for invoked constructor
		factor.getCalledConstructorName().obj = invokedConstructorObjNode;
		invokedConstructorObjNode = null;
	}
	
	public void visit(F_NewArray factor) {
		Struct arraySizeTypeStruct = factor.getExpr().struct;
		Struct elemTypeStruct = factor.getType().struct;
		
		if (arraySizeTypeStruct != Tab.intType) {
			factor.struct = Tab.noType;
			report_error("Velicina niza mora biti tipa int", factor);
			return;
		}
		
		Struct newArrayTypeStruct = new Struct(Struct.Array, elemTypeStruct);
		factor.struct = newArrayTypeStruct;
	}
	
	/************************* Term **************************/
	
	public void visit(Term_Factor term) {
		Struct factorTypeStruct = term.getFactor().struct;
		term.struct = factorTypeStruct;
	}
	
	public void visit(Term_MulFactor term) {
		Struct recursiveTermTypeStruct = term.getTerm().struct;
		Struct factorTypeStruct = term.getFactor().struct;
		
		if (recursiveTermTypeStruct != Tab.intType || factorTypeStruct != Tab.intType) {
			term.struct = Tab.noType;
			report_error("Svi cinioci moraju biti int tipa", term);
			return;
		}
		
		term.struct = Tab.intType;
	}
    
	/********************** Expr *****************************/
	
	public void visit(Expr_Term expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(Expr_NegTerm expr) {
		Struct termTypeStruct = expr.getTerm().struct;
		
		if (termTypeStruct != Tab.intType) {
			expr.struct = Tab.noType;
			report_error("Izraz koji se negira mora biti tipa int", expr);
			return;
		}
		
		expr.struct = Tab.intType;
	}
	
	public void visit(Expr_AddTerm expr) {
		Struct recursiveExprTypeStruct = expr.getExpr().struct;
		Struct termTypeStruct = expr.getTerm().struct;
		
		if (!recursiveExprTypeStruct.compatibleWith(termTypeStruct)) {
			expr.struct = Tab.noType;
			report_error("Tipovi nisu kompatibilni", expr);
			return;
		}
		
		if (recursiveExprTypeStruct != Tab.intType || termTypeStruct != Tab.intType) {
			expr.struct = Tab.noType;
			report_error("Svi sabirci moraju biti int tipa", expr);
			return;
		}
		
		expr.struct = Tab.intType;
	}
	
	/********************* CondFact ********************/
	
	public void visit(CondFact_Expr condfact) {
		Struct exprTypeStruct = condfact.getExpr().struct;
		
		if (exprTypeStruct != TabExtension.boolType) {
			condfact.struct = Tab.noType;
			report_error("Tip uslovnog izraza mora biti tipa bool", condfact);
			return;
		}
		
		condfact.struct = exprTypeStruct;
	}
	
	public void visit(CondFact_RelopExpr condfact) {
		Struct leftExprTypeStruct = condfact.getExpr().struct;
		int leftKind = leftExprTypeStruct.getKind();
		Struct rightExprTypeStruct = condfact.getExpr1().struct;
		int rightKind = rightExprTypeStruct.getKind();
		
		if (!leftExprTypeStruct.compatibleWith(rightExprTypeStruct)) {
			condfact.struct = Tab.noType;
			report_error("Podizrazi u uslovnom izrazu moraju biti kompatibilnog tipa", condfact);
			return;
		}
		
		if ((
				leftKind == Struct.Array || leftKind == Struct.Class ||
				rightKind == Struct.Array || rightKind == Struct.Class
			) && (
				!operator.equals(Operators.DEQ) && !operator.equals(Operators.NE)	
			)) 
		{
			condfact.struct = Tab.noType;
			report_error("Uz promenljive tipa klase ili niza, od relacionih operatora, mogu se koristiti samo != i ==", condfact);

			return;
		}
	}
	
	/*********************** operators **************************/
	
	public void visit(Assignop op) {
		operator = Operators.EQ;
	}
	
	public void visit(R_Deq op) {
		operator = Operators.DEQ;
	}
	
	public void visit(R_Ne op) {
		operator = Operators.NE;
	}
	
	public void visit(R_Gt op) {
		operator = Operators.GT;
	}
	
	public void visit(R_Ge op) {
		operator = Operators.GE;
	}
	
	public void visit(R_Lt op) {
		operator = Operators.LT;
	}
	
	public void visit(R_Le op) {
		operator = Operators.LE;
	}
	
	public void visit(A_Plus op) {
		operator = Operators.PLUS;
	}
	
	public void visit(A_Minus op) {
		operator = Operators.MINUS;
	}
	
	public void visit(M_Mul op) {
		operator = Operators.MUL;
	}
	
	public void visit(M_Div op) {
		operator = Operators.DIV;
	}
	
	public void visit(M_Percent op) {
		operator = Operators.PERCENT;
	}
	
	/**********************************************************/ 
	
	/**************** DesignatorStatement *********************/
	
	public void visit(DesignatorAssignment designatorStatement) {
		operator = null;
		Obj leftDesignatorObjNode = designatorStatement.getDesignator().obj;
		Struct leftDesignatorTypeStruct = leftDesignatorObjNode.getType();
		Struct rightExprTypeStruct = designatorStatement.getExpr().struct;
		
		int leftDesignatorKind = leftDesignatorObjNode.getKind();
		if (leftDesignatorKind != Obj.Var && leftDesignatorKind != Obj.Elem && leftDesignatorKind != Obj.Fld) {
			report_error("Izraz sa leve strane = mora oznacavati promenljivu, element niza, ili polje unutar objekta", designatorStatement);
			return;
		}
		
		if (!StructExtension.assignableTo(rightExprTypeStruct, leftDesignatorTypeStruct)) {
			report_error("Tipovi nisu kompatibilni da bi se izvrsila dodela", designatorStatement);
			return;
		}
		
		/* PAY ATTENTION: foreach constraint */
		if (currVarForeach != null && leftDesignatorObjNode == currVarForeach) {
			report_error("Nije moguce koristiti foreach varijablu '" + currVarForeach.getName() + "' za promenu vrednosti elementa niza (nedozvoljen upis)", designatorStatement);
			return;
		}
		
//		/* PAY ATTENTION: field access right from = */
//		
//		if (leftDesignatorKind == Obj.Var && checkIfFuncFormParamIsAccessed(leftDesignatorObjNode)) {
//			report_info("Pristup formalnom parametru '" + leftDesignatorObjNode.getName() + "'", designatorStatement);
//		}
//		else if (leftDesignatorKind == Obj.Var) {
//			report_info("Pristup (" + (
//						leftDesignatorObjNode.getLevel() == 0 ? "globalnoj" : "lokalnoj"
//					) +") promenljivoj '" + leftDesignatorObjNode.getName() + "'", designatorStatement);
//		}
//		else if (leftDesignatorKind == Obj.Con) {
//			report_info("Pristup simbolickoj konstanti '" + leftDesignatorObjNode.getName() + "'", designatorStatement);
//		}
	}
	
	public void visit(DesignatorFunctionCall designatorStatement) {
		Obj designatorObjNode = designatorStatement.getCalledFunctionOrMethodDesignator().getDesignator().obj;
		int designatorKind = designatorObjNode.getKind();
		String funcName = designatorObjNode.getName();
		
		if (designatorKind != Obj.Meth) {
			report_error("Identifikator mora oznacavati metodu ili funkciju glavnog programa", designatorStatement);
			return;
		}
		
		if (listOfGlobalFunctionObjNodes.contains(designatorObjNode)) {
			report_info("Poziv globalne funkcije '" + funcName + "'", designatorStatement);
		}
	}
	
	public void visit(DesignatorIncDec designatorStatement) {
		operator = null;
		Obj leftDesignatorObjNode = designatorStatement.getDesignator().obj;
		Struct leftDesignatorTypeStruct = leftDesignatorObjNode.getType();
		
		int leftDesignatorKind = leftDesignatorObjNode.getKind();
		if (leftDesignatorKind != Obj.Var && leftDesignatorKind != Obj.Elem && leftDesignatorKind != Obj.Fld) {
			report_error("Izraz koji se inkrementira/dekrementira mora oznacavati promenljivu, element niza, ili polje unutar objekta", designatorStatement);
			return;
		}
		
		if (leftDesignatorTypeStruct != Tab.intType) {
			report_error("Izraz koji se inkrementira/dekrementira mora biti tipa int", designatorStatement);
			return;
		}
	}

	public void visit(ReverseArrayAssignment designatorStatement) {
		Obj rightDesignatorObjNode = designatorStatement.getDesignator().obj;
		Struct rightDesignatorTypeStruct = rightDesignatorObjNode.getType();
		
		// check right designator (must be array)
		if (rightDesignatorTypeStruct.getKind() != Struct.Array) {
			report_error("Varijabla sa desne strane znaka za dodelu vrednosti mora predstavljati niz", designatorStatement);
			return;
		}
		
		// check type compatibility with array elems /* PAY ATTENTION */
		Struct arrayElemTypeStruct = rightDesignatorTypeStruct.getElemType();
		for (Obj objNode : listOfObjNodesToBeAssigned) {
			if (objNode == null) continue;
			Struct leftElemTypeStruct = objNode.getType();
			
			// src, dst
			if (!StructExtension.assignableTo(arrayElemTypeStruct, leftElemTypeStruct)) {
				report_error("Element niza '" + rightDesignatorObjNode.getName() + "' nije kompatibilan pri dodeli sa '" + objNode.getName() + "'", designatorStatement);
				return;
			}
		}
		
		
		listOfObjNodesToBeAssigned.clear();
	}
	
	public void visit (OptionalDesignator_ node) {
		Obj designatorObjNode = node.getDesignator().obj;
		Struct designatorTypeStruct = designatorObjNode.getType();
		int designatorKind = designatorObjNode.getKind();
		
		/* PAY ATTENTION left designator check for reverse array assignment */
		if (designatorKind != Obj.Var && designatorKind != Obj.Elem && designatorKind != Obj.Fld) {
			report_error("Izraz mora oznacavati promenljivu, element niza, ili polje unutar objekta", node);
			return;
		}
		
		listOfObjNodesToBeAssigned.add(designatorObjNode);
	}
	
	public void visit(NoOptionalDesignator node) {
		listOfObjNodesToBeAssigned.add(null);
	}
	
	/********************************** break and continue ***********************************/
	
	public void visit(M_Break node) {
		if (openLoopScopeCounter == 0) {
			report_error("Naredba break se mora nalaziti unutar petlje", node);
		}
	}
	
	public void visit(M_Continue node) {
		if (openLoopScopeCounter == 0) {
			report_error("Naredba continue se mora nalaziti unutar petlje", node);
		}
	}
	
	public void visit(WhileLoopStart node) {
		++openLoopScopeCounter;
	}
	
	public void visit(M_While node) {
		--openLoopScopeCounter;
	}
	
	/* PAY ATTENTION: foreach */
	
	public void visit(DesignatorForeach designatorForeach) {
		++openLoopScopeCounter;
		
		// check designator type
		Obj leftDesignatorObjNode = designatorForeach.getDesignator().obj;
		Struct leftDesignatorTypeStruct = leftDesignatorObjNode.getType();
		int leftDesignatorKind = leftDesignatorTypeStruct.getKind();
		
		if (leftDesignatorKind != Struct.Array) {
			designatorForeach.struct = Tab.noType;
			report_error("Foreach se moze pozivati samo nad nizovima", designatorForeach);
			return;
		}
		
		Struct elemTypeStruct = leftDesignatorTypeStruct.getElemType();
		
		designatorForeach.struct = elemTypeStruct;
	}
	
	public void visit(M_Foreach node) {
		--openLoopScopeCounter;
		
		Struct leftElemTypeStruct = node.getDesignatorForeach().struct;
		Obj currVarObjNode = node.getCurrVarDesignator().obj;
		Struct currVarTypeStruct = currVarObjNode.getType();
		
		if (leftElemTypeStruct != currVarTypeStruct) {
			report_error("Identifikator za iteriranje '" + currVarObjNode.getName() + "' nije istog tipa kao elementi niza nad kojim je pozvan foreach", node);
			currVarForeach = null;
			return;
		}
		
		currVarForeach = null;
	}
	
	public void visit(CurrVarDesignator node) {
		String identName = node.getCurrVar();
    	Obj identObjNode = Tab.find(identName);
    	
    	if (identObjNode == Tab.noObj) {
    		node.obj = Tab.noObj;
    		report_error("Promenjiva '" + identName + "' nije deklarisana", node);
    		return;
    	}
    	
    	node.obj = identObjNode;
    	int identObjNodeKind = identObjNode.getKind();
    	
    	if (identObjNodeKind != Obj.Var) {
			node.obj = Tab.noObj;
			report_error("Varijabla za iteriranje mora biti lokalna ili globalna promenljiva", node);
			return;
		}
    	
    	currVarForeach = identObjNode;
	}
	
	/******************* M_Read ****************/
	
	public void visit(M_Read node) {
		Obj designatorObjNode = node.getDesignator().obj;
		Struct designatorTypeStruct = designatorObjNode.getType();
		
		int designatorKind = designatorObjNode.getKind();
		if (designatorKind != Obj.Var && designatorKind != Obj.Elem && designatorKind != Obj.Fld) {
			report_error("Izraz mora oznacavati promenljivu, element niza, ili polje unutar objekta", node);
			return;
		}
		
		if (designatorTypeStruct != Tab.intType && designatorTypeStruct != Tab.charType && designatorTypeStruct != TabExtension.boolType) {
			report_error("Izraz mora biti char, int ili bool", node);
			return;
		}
	}
	
	/******************* M_Print ***************/
	
	public void visit(M_Print node) {
		Struct exprTypeStruct = node.getExpr().struct;
		
		if (exprTypeStruct != Tab.intType && exprTypeStruct != Tab.charType && exprTypeStruct != TabExtension.boolType) {
			report_error("Prvi argument funkcije print mora biti int, char ili bool", node);
			return;
		}
	}
	
	/******************** ActPars *******************/
	
	// use a stack <Obj, List<Struct>>
	
	public void visit(ActPar node) {
		// run into new act par
		// put new act par in current list
		
		Struct actParTypeStruct = node.getExpr().struct;
		stackOfCallsWithActPars.peek().second.add(actParTypeStruct);
	}
	
	public void visit(NoOptionalActPars node) {
		stackOfCallsWithActPars.pop();
	}
	
	public void visit(ActPars node) {
		// all act pars of current function/method/constructor collected
		
		Pair pair = stackOfCallsWithActPars.pop();
		Obj calledIdentObjNode = pair.first;
		List<Struct> actParsTypeStructs = pair.second;
		boolean actParsAreCompatible = false;
		String message = "";
		
		if (calledIdentObjNode.getKind() == Obj.Type) {
			// if constructor is called (obj is class)
			// get type, get fields, find constructors
			// iterate through constructors until you find a match
			// check locals field, check type
			// get adr field to differentiate form pars from local vars
			
			Struct classTypeStruct = calledIdentObjNode.getType();
			String className = calledIdentObjNode.getName();
			Collection<Obj> classMembers = classTypeStruct.getMembers();
			
			for(Obj classMember: classMembers) {
				if (classMember.getName().equals(className)) {
					// class member is a constructor
					message = checkActPars(classMember, actParsTypeStructs);
					actParsAreCompatible = (message.length() == 0); 
					if (actParsAreCompatible) {
						invokedConstructorObjNode = classMember;
						break;
					}
				}
			}
			
			if (!actParsAreCompatible) message = "Ne postoji odgovarajuci konstruktor koji prihvata zadate parametre";
		}
		else if (calledIdentObjNode.getKind() == Obj.Meth) {
			// if func/method is called (obj is meth)
			// check locals field, check type
			// get adr field to differentiate form pars from local vars
			
			message = checkActPars(calledIdentObjNode, actParsTypeStructs);
			actParsAreCompatible = (message.length() == 0); 
		}
		
		if (!actParsAreCompatible) {
			// error
			report_error(message, node);
			return;
		}
	}
		
	public void visit(CalledConstructorName node) {
		// new constructor call detected
		// put class obj node on stack, create a new act par list
		
		Struct classTypeStruct = node.getType().struct;
		String className = map_ClassStructToName.get(classTypeStruct);
		Obj classObjNode = Tab.find(className);
		stackOfCallsWithActPars.push(new Pair(classObjNode, new ArrayList<>()));
	}
	
	public void visit(CalledFunctionOrMethodDesignator node) {
		// new method or function call start detected
		// put func/method obj node on stack, create a new act par list
		
		Obj funcMethObjNode = node.getDesignator().obj;
		stackOfCallsWithActPars.push(new Pair(funcMethObjNode, new ArrayList<>()));
	}
	
	
	/************ helper for checking act pars ********************/
	
	public String checkActPars(Obj funcObjNode, List<Struct> actParsTypeStructs) {
		int numOfFormPars = funcObjNode.getLevel();
		int numOfActPars = actParsTypeStructs.size();
		
		if (numOfFormPars != numOfActPars) {
			return "Broj formalnih i stvarnih argumenata metode mora biti isti";
		}
		
		Collection<Obj> formParsAndLocalVarsObjNodes = funcObjNode.getLocalSymbols();
		int i = 0;
		for (Obj formPar: formParsAndLocalVarsObjNodes) {
			if (i == numOfFormPars) break;
			
			// src, dst
			if (!StructExtension.assignableTo(actParsTypeStructs.get(i), formPar.getType())) {
				return "Tip stvarnog argumenta nije kompatibilan pri dodeli sa tipom formalnog parametra na poziciji (" + (i+1) + ")";
			}
			
			++i;
		}
		
		return "";
	}
	
	/**************************************************************/
	
}
