package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
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
	
	private static int programVarCount = 0;
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
    	currentClassStruct = new Struct(Struct.Class, parentClassStruct);
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
    	if (!returnStatementFound && currentMethodReturnTypeStruct != Tab.noType) {
    		report_error("Funkcija '" + currentMethodObjNode.getName() + "' nema return iskaz", null);
    		
    		/* PAY ATTENTION: should we call return if error occurs */
    	}
    	
    	currentMethodObjNode.setLevel(listOfFormParsObjectNodes.size() + 1); /* PAY ATTENTION: +1 for this */
    	Tab.chainLocalSymbols(currentMethodObjNode);
    	/* PAY ATTENTION: method overriding */
    	if (currentClassStruct != null) overrideMethod(methodDecl);
    	Tab.closeScope();
    	
		report_info("Kraj dosega " + (currentClassName.length()==0? "globalne funkcije":"metode") + "'" + currentMethodObjNode.getName() + "'", methodDecl);

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
    		report_error("Return iskaz se ne nalazi u funkciji", returnOptionalExpr);
    		return;
    	}
    	
    	returnStatementFound = true;
    	
    	if (currentMethodReturnTypeStruct == Tab.noType) {
    		report_error("Funkcija '" + currentMethodObjNode.getName() + "' je definisana kao void, a sadrzi izraz u return naredbi", returnOptionalExpr);
    	}
    	
    	/* PAY ATTENTION: check type compatibility */
    	/* TO DO */
    	
		report_info("Return naredba" + " sa opcionim izrazom", returnOptionalExpr);
    }
    
    public void visit(NoReturnExpr noReturnExpr) {
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
    		
    	// else: this is only param
    	return true;
    }
    
    /**********************************************/
    
}
