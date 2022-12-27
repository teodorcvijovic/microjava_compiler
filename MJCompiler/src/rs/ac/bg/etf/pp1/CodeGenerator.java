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
import java.util.Stack;

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
	public static List<Obj> listOfClassMethodObjNodes = new ArrayList<>();
	
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
				String[] tmp = methodName.split("#", -1);
				if (tmp.length == 2) methodName = tmp[0];
				
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
		
		if (currentClassObjNode != null) listOfClassMethodObjNodes.add(currentMethodDefinition);
		
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
    
    /********************* print and read ****************************/
    
    public void visit(M_Print node) {
    	Struct exprTypeStruct = node.getExpr().struct;
    	
    	if (exprTypeStruct == Tab.intType || exprTypeStruct == TabExtension.boolType) Code.put(Code.print);
		else Code.put(Code.bprint);
    }
    
    public void visit(PrintNumConst_ node) {
    	M_Print parentNode = (M_Print) node.getParent();
    	Struct exprTypeStruct = parentNode.getExpr().struct;
    	
    	int width = node.getPrintWidth();
    	
    	if (exprTypeStruct == Tab.intType) Code.loadConst(width); 
    	else if (exprTypeStruct == TabExtension.boolType) Code.loadConst(width); 
    	else Code.loadConst(width);
    }
    
    public void visit(NoPrintNumConst node) {
    	M_Print parentNode = (M_Print) node.getParent();
    	Struct exprTypeStruct = parentNode.getExpr().struct;
    	
    	if (exprTypeStruct == Tab.intType) Code.loadConst(5); 
    	else if (exprTypeStruct == TabExtension.boolType) Code.loadConst(5); 
    	else Code.loadConst(1);
    }
    
    public void visit(M_Read node) {
    	Obj readDesignatorObjNode = node.getDesignator().obj;
    	Struct designatorTypeStruct = readDesignatorObjNode.getType();
    	
    	if(designatorTypeStruct == Tab.charType) Code.put(Code.bread);			
		else Code.put(Code.read);		

		Code.store(readDesignatorObjNode);
    }
    
    /******************************* Expr ***********************************/
    
    public void visit(Expr_NegTerm node) {
    	Code.put(Code.neg);
    }
    
    public void visit(Expr_AddTerm node) {
    	Addop addop = node.getAddop();
    	if (addop instanceof A_Plus) 		Code.put(Code.add);
    	else if (addop instanceof A_Minus) 	Code.put(Code.sub);
    }
    
    /******************************** Term **********************************/
    
    public void visit(Term_MulFactor node) {
    	Mulop mulop = node.getMulop();
    	if (mulop instanceof M_Mul) 			Code.put(Code.mul);
    	else if (mulop instanceof M_Div) 		Code.put(Code.div);
    	else if (mulop instanceof M_Percent) 	Code.put(Code.rem);
    }
    
    /****************************** Factor **********************************/
    
    public void visit(F_NumConst node) {
    	int constant = node.getConstValue();
    	Code.loadConst(constant);
    }
    
    public void visit(F_CharConst node) {
    	char constValue = node.getConstValue();
    	int constant = constValue;
    	Code.loadConst(constant);
    }
    
    public void visit(F_BoolConst node) {
    	boolean constValue = node.getConstValue();
    	int constant;
    	if (constValue == true) constant = 1;
    	else constant = 0;
    	Code.loadConst(constant);
    }
    
    public void visit(F_Designator node) {
    	/* PAY ATTENTION: ident designators on the right side */
    	Obj designatorObjNode = node.getDesignator().obj;
    	Code.load(designatorObjNode);
    }
    
    public void visit(F_NewArray node) {
    	Struct arrayTypeStruct = node.struct.getElemType();
    	
    	Code.put(Code.newarray);
    	if (arrayTypeStruct == Tab.charType) Code.put(0);
    	else Code.put(1);
    }
    
    /*************************** Designator **********************************/
    
    public void visit(Designator_Ident node) {
    	Obj identObjNode = node.obj;
    	SyntaxNode parentSyntaxNode = node.getParent();
    	int identKind = identObjNode.getKind();
    	
    	if (identKind == Obj.Fld) {
    		// put this
    		if (parentSyntaxNode instanceof DesignatorStatement &&
    			parentSyntaxNode instanceof F_Designator) {
    			Code.put(Code.const_ + 0); // this is used for field access
    		}
    	}
    	else if (identKind == Obj.Meth) {
    		if (parentSyntaxNode instanceof CalledFunctionOrMethodDesignator && 
    			listOfClassMethodObjNodes.contains(identObjNode)) {
    			Code.put(Code.const_ + 0); // this is first param
    		}
    	}
    	
    	/* PAY ATTENTION: nothing is loaded here !!! */
    }
    
    public void visit(Designator_FieldAccess node) {
    	Obj leftDesignatorObjNode = node.getDesignator().obj;
    	Code.load(leftDesignatorObjNode);
    }
    
    public void visit(ArrayTypeDesignator node) {
    	Obj arrayDesignatorObjNode = node.getDesignator().obj;
    	Code.load(arrayDesignatorObjNode);
    }
    
    /************************ DesignatorStatement ******************************/
    
    public void visit(DesignatorAssignment node) {
    	Obj leftDesignatorObjNode = node.getDesignator().obj;
    	Code.store(leftDesignatorObjNode);
    }
    
    public void visit(DesignatorInc node) {
    	Obj designatorObjNode = node.getDesignator().obj;
    	int designatorKind = designatorObjNode.getKind();
    	
    	if (designatorKind == Obj.Elem) Code.put(Code.dup2); // array_adr, index
    	else if (designatorKind == Obj.Fld) Code.put(Code.dup); // class_adr
    	
		Code.load(designatorObjNode);	// the node itself holds field position info used in getfield
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorObjNode);
    }
    
    public void visit(DesignatorDec node) {
    	Obj designatorObjNode = node.getDesignator().obj;
    	int designatorKind = designatorObjNode.getKind();
    	
    	if (designatorKind == Obj.Elem) Code.put(Code.dup2); // array_adr, index
    	else if (designatorKind == Obj.Fld) Code.put(Code.dup); // field_adr
    	
		Code.load(designatorObjNode);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorObjNode);
    }
    
    public void visit(ReverseArrayAssignment node) {
    	
    }
    
    public void visit(DesignatorFunctionCall node) {
    	
    }
    
}
