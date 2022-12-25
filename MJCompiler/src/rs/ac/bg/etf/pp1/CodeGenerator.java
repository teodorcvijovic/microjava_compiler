package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.SemanticAnalyzer;

public class CodeGenerator extends VisitorAdaptor {

	private static int mainPC = -1;
	
	public static int getMainPC() { return mainPC; }
	
	/****************************************************/
	
	public static Obj currentClassObjNode = null;
	public static boolean returnStatementFound = false;
	public static Obj currentMethodDefinition = null;
	
	/****** function, method and constructor start ******/
	
	public void initVFT() {
		
		int staticDataCnt = SemanticAnalyzer.programVarCount;
		
		// TO DO
		
		SemanticAnalyzer.programVarCount = staticDataCnt;
	}
	
	public void visit(GlobalMethodDecl_Void node) {
		Obj funcObjNode = node.obj;
		currentMethodDefinition = funcObjNode;
		String funcName = funcObjNode.getName();
		
		funcObjNode.setAdr(Code.pc);
		
		if ("main".equals(funcName)) {
			mainPC = Code.pc;
		}
		
		int argCnt = funcObjNode.getLevel();
		int argAndLocalVarCnt = funcObjNode.getLocalSymbols().size();
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
		
		if ("main".equals(funcName)) {
			initVFT();
		}
	}
	
	public void visit(GlobalMethodDecl_Ident node) {
		Obj funcObjNode = node.obj;
		currentMethodDefinition = funcObjNode;
		String funcName = funcObjNode.getName();
		
		funcObjNode.setAdr(Code.pc);
		
		if ("main".equals(funcName)) {
			mainPC = Code.pc;
		}
		
		int argCnt = funcObjNode.getLevel();
		int argAndLocalVarCnt = funcObjNode.getLocalSymbols().size();
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
		
		if ("main".equals(funcName)) {
			initVFT();
		}
	}
	
	public void visit(M_Return node) {
		returnStatementFound = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodDecl node) {
		Struct currentMethodReturnTypeStruct = node.obj.getType();
		
		if (currentMethodReturnTypeStruct != Tab.noType && !returnStatementFound) {
			// runtime error: return not found
			Code.put(Code.trap);
			Code.put(-1);
		}
		
		returnStatementFound = false;
	}
	
	public void visit(MethodDecl_Void node) {
		Obj funcObjNode = node.obj;
		currentMethodDefinition = funcObjNode;
		
		funcObjNode.setAdr(Code.pc);
		
		int argCnt = funcObjNode.getLevel();
		int argAndLocalVarCnt = funcObjNode.getLocalSymbols().size();
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
	}
	
	public void visit(MethodDecl_Ident node) {
		Obj funcObjNode = node.obj;
		currentMethodDefinition = funcObjNode;
		
		funcObjNode.setAdr(Code.pc);
		
		int argCnt = funcObjNode.getLevel();
		int argAndLocalVarCnt = funcObjNode.getLocalSymbols().size();
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
	}
	
	public void visit(ConstructorDecl_Start node) {
		// TO DO
	}
	
	/***** default constructor ******/
	
	public void visit(Methods_Ident node) {
    	generateDefaultConstructorCode();
    }
    
    public void visit(Methods_Void node) {
    	generateDefaultConstructorCode();
    }
 
    public void visit(EmptyClassMethodConstructorLists node) {
    	generateDefaultConstructorCode();
    }
    
    public void visit(EmptyConstructorMethodLists node) {
    	generateDefaultConstructorCode();
    }
    
    public void generateDefaultConstructorCode() {
    	// TO DO
    }
    
    /*** class scope enter and exit ***/
    
    public void visit(ClassNameAndDerivation node) {
    	currentClassObjNode = node.obj;
    }
    
    public void visit(ClassDecl node) {
    	currentClassObjNode = null;
    }
    
    /**********************************/
    
	
}
