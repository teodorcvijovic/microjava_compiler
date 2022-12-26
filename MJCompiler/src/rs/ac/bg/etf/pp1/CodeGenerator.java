package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.bg.etf.pp1.SemanticAnalyzer;

public class CodeGenerator extends VisitorAdaptor {

	private static int mainPC = -1;
	
	public static int getMainPC() { return mainPC; }
	
	/****************************************************/
	
	public static Obj currentClassObjNode = null;
	public static boolean returnStatementFound = false;
	public static Obj currentMethodDefinition = null;
	public static Obj currentConstructorDefinition = null;
	public static Map<Obj, Integer> mapClassObjNodeToVFTadr = new HashMap<>();
	
	/****** function, method and constructor start ******/
	
	public void initVFT() {
		
		int staticDataCnt = SemanticAnalyzer.programVarCount;
		
		for (Obj classObjNode: mapClassObjNodeToVFTadr.keySet()) {
			String className = classObjNode.getName();
			int VFT_adr = staticDataCnt;
			mapClassObjNodeToVFTadr.put(classObjNode, VFT_adr);
			
			Collection<Obj> classMemberObjNodes = classObjNode.getType().getMembers();
			for (Obj classMember: classMemberObjNodes) {
				int classMemberKind = classMember.getKind();
				String methodName = classMember.getName();
				
				if (classMemberKind == Obj.Meth && !methodName.equals(className)) {
					// put method name
					
					for(int i = 0; i < methodName.length(); ++i) {
						Code.loadConst(methodName.charAt(i));
						Code.put(Code.putstatic);
						Code.put2(staticDataCnt);
						++staticDataCnt;
					}
					
					Code.loadConst(-1);
					Code.put(Code.putstatic);
					Code.put2(staticDataCnt);
					++staticDataCnt;
					
					// put method address
					int method_adr = classMember.getAdr();
					Code.loadConst(method_adr);
					Code.put(Code.putstatic);
					Code.put2(staticDataCnt);
					++staticDataCnt;
				}
			}
			
			// end of VFT for curr class
			Code.loadConst(-2);
			Code.put(Code.putstatic);
			Code.put2(staticDataCnt);
			++staticDataCnt;
		}
		
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
		
		if ("main".equals(funcName)) {
			initVFT();
		}
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
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
		
		if ("main".equals(funcName)) {
			initVFT();
		}
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
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
		else if (currentMethodReturnTypeStruct == Tab.noType && !returnStatementFound) {
			// method is void, return is not found
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		
		returnStatementFound = false;
		currentMethodDefinition = null;
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
		Obj constructorObjNode = node.obj;
		currentConstructorDefinition = constructorObjNode;

		constructorObjNode.setAdr(Code.pc);
		
		int argCnt = constructorObjNode.getLevel();
		int argAndLocalVarCnt = constructorObjNode.getLocalSymbols().size();
		
		Code.put(Code.enter);
		Code.put(argCnt);
		Code.put(argAndLocalVarCnt);
	}
	
	public void visit(ConstructorDecl node) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		currentConstructorDefinition = null;
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
    	Obj defaultConstructorObjNode = null;
    	
    	Collection<Obj> classMembers = currentClassObjNode.getType().getMembers();
    	for(Obj classMember: classMembers) {
    		if (classMember.getKind() == Obj.Meth && classMember.getName().equals(currentClassObjNode.getName())) {
    			defaultConstructorObjNode = classMember;
    			break;
    		}
    	}
    	
    	defaultConstructorObjNode.setAdr(Code.pc);
    	
    	Code.put(Code.enter);
		Code.put(1); // only argument is this
		Code.put(1);
		
		// empty body
		
		Code.put(Code.exit);
		Code.put(Code.return_);
    }
    
    /*** class scope enter and exit ***/
    
    public void visit(ClassNameAndDerivation node) {
    	currentClassObjNode = node.obj;
    }
    
    public void visit(ClassDecl node) {
    	mapClassObjNodeToVFTadr.put(currentClassObjNode, -1);
    	currentClassObjNode = null;
    }
    
    /**********************************/
    
	/*************************** operators ***************************/
    
    public void visit(A_Plus node) { Code.put(Code.add); }
    
    public void visit(A_Minus node) { Code.put(Code.sub); }
    
    public void visit(M_Mul node) { Code.put(Code.mul); }
    
    public void visit(M_Div node) { Code.put(Code.div); }
    
    public void visit(M_Percent node) { Code.put(Code.rem); }
    
    
    
}
