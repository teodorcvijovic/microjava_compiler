package rs.ac.bg.etf.pp1;

import java.util.HashMap;
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
	
	private static String currentClassName;
	private static Struct currentClassStruct;
	
	private static Map<Struct, String> map_ClassStructToName = new HashMap<>();
	
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
    			/* PAY ATTENTION: methods are not copied yet */
    		}
    	}
    	
    	report_info("Klasa '" + className + "' je uspesno deklarisana" + (parentClassName.equals("") ? "" : " i izvedena iz klase '" + parentClassName + "'"), classNameAndDerivation);
    }
    
    public void visit(ClassDecl classDecl) {
    	/* PAY ATTENTION: class scope is closing */
    	
    	Tab.chainLocalSymbols(currentClassStruct);
    	Tab.closeScope();
    	currentClassName = "";
    	currentClassStruct = null;
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
    
}
