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
	
	// CONSTRUCTORS ARE INVOKED AS GLOBAL FUNCTIONS !!!

	private static int mainPC = -1;
	
	public static int getMainPC() { return mainPC; }
	
	/****************************************************/
	
	public static Obj currentClassObjNode = null;
	public static boolean returnStatementFound = false;
	public static Obj currentMethodDefinition = null;
	public static Obj currentConstructorDefinition = null;
	public static Map<Obj, Integer> mapClassObjNodeToVFTadr = new HashMap<>();
	public static List<Obj> listOfClassMethodObjNodes = new ArrayList<>();
	
	public static boolean collectDesignatorsFromReverseArrayAssignment = false;
	public static List<Obj> listOfDesignatorsInReverseArrayAssignment = new ArrayList<>();
	
	public static Stack<Obj> stackOfInvokedFunctionObjNodes = new Stack<>();
	
	//public static Obj invokedConstructorObjNode = null;
	
	private static Stack<List<Integer>> or_jumpsToBePatched = new Stack<>();
    private static Stack<List<Integer>> and_jumpsToBePatched = new Stack<>();
    private static Stack<List<Integer>> skipElse_jumpsToBePached = new Stack<>();
    
    private static Stack<Integer> stackOfWhileStartAdr = new Stack<>();
    private static Stack<List<Integer>> break_jumpsToBePached = new Stack<>();
    private static Stack<List<Integer>> continue_jumpsToBePached = new Stack<>();
	
	/***** helpers ******/
	
	public Obj getClassByName(String className) {
		for (Obj classObjNode: mapClassObjNodeToVFTadr.keySet()) {
			if (classObjNode.getName().equals(className)) return classObjNode;
		}
		return null;
	}
	
	public void pushNewEmptyListsOnPatchingStacks() {
    	or_jumpsToBePatched.push(new ArrayList<>());
    	and_jumpsToBePatched.push(new ArrayList<>());
    	skipElse_jumpsToBePached.push(new ArrayList<>());
    }
    
    public void popFromPatchingStacks() {
    	or_jumpsToBePatched.pop();
    	and_jumpsToBePatched.pop();
    	skipElse_jumpsToBePached.pop();
    }
	
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
			Code.put(1);
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
    
    public void visit(CalledConstructorName node) {
    	// invokedConstructorObjNode = node.obj;
    	Obj invokedConstructorObjNode = node.obj;
    	stackOfInvokedFunctionObjNodes.push(invokedConstructorObjNode);
    	F_NewObjConstruction parentNode = (F_NewObjConstruction) node.getParent();
    	
    	int numOfFields = parentNode.struct.getNumberOfFields();
    	int bytesToAllocate = numOfFields * 4;
    	
    	Code.put(Code.new_);
    	Code.put2(bytesToAllocate);
    	
    	// object is now allocated on the heap, and obj_adr is now on top of expr stack
    	
    	// duplicate obj_adr for this param of the constructor
    	Code.put(Code.dup);
    	
    	// duplicate obj_adr to store VFT_adr
    	Code.put(Code.dup);
    	
    	String className = invokedConstructorObjNode.getName();
    	String[] tmp = className.split("#", -1);
    	if (tmp.length == 2) className = tmp[0];
    	Obj classObjNode = getClassByName(className);
    	
    	int VFT_adr = mapClassObjNodeToVFTadr.getOrDefault(classObjNode, -1);
    	
    	Code.loadConst(VFT_adr);
    	Code.put(Code.putfield);
    	Code.put2(0); // VFT_adr is the first field
    }
    
    public void visit(F_NewObjConstruction node) {
    	// all act pars are placed on stack (including implicit this)
//    	invokeGlobalFunc(invokedConstructorObjNode);
//    	invokedConstructorObjNode = null;
    	Obj constructorObjNode = stackOfInvokedFunctionObjNodes.pop();
    	invokeGlobalFunc(constructorObjNode);
    }
    
    /*************************** Designator **********************************/
    
    public void visit(Designator_Ident node) {
    	Obj identObjNode = node.obj;
    	SyntaxNode parentSyntaxNode = node.getParent();
    	int identKind = identObjNode.getKind();
    	
    	if (identKind == Obj.Fld) {
    		// put this
    		if (parentSyntaxNode instanceof DesignatorStatement ||
    			parentSyntaxNode instanceof F_Designator) {
    			Code.put(Code.load_n + 0); // this is used for field access
    		}
    	}
    	else if (identKind == Obj.Meth) {
    		if (parentSyntaxNode instanceof CalledFunctionOrMethodDesignator && 
    			listOfClassMethodObjNodes.contains(identObjNode)) {
    			Code.put(Code.load_n + 0); // this is first param
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
    
    /***** reverse array assignment ******/
    
    public void visit(OptionalDesignator_ node) {
    	Obj designatorObjNode = node.getDesignator().obj;
    	listOfDesignatorsInReverseArrayAssignment.add(designatorObjNode);
    }
    
    public void visit(NoOptionalDesignator node) {
    	listOfDesignatorsInReverseArrayAssignment.add(null);
    }
    
    public void visit(ReverseArrayAssignment node) {
    	Obj arrayObjNode = node.getDesignator().obj;
    	
    	/* check len in mj assembly and generate trap */
    	int cntOfCollectedDesignators = listOfDesignatorsInReverseArrayAssignment.size();
    	Code.loadConst(cntOfCollectedDesignators);
    	
    	// get array lenght
    	Code.load(arrayObjNode);
    	Code.put(Code.arraylength);
    	
    	// we have two lenghts on expr stack
    	Code.put(Code.jcc + Code.le); 	// 3 bytes
    	Code.put2(5); 					// skip trap (3 + 2)
    	
    	// cntOfCollectedDesignators > array length (runtime error)
    	Code.put(Code.trap); 			// 2 bytes
    	Code.put(2);
    	
    	/* everything is ok */
    	
    	for(Obj designatorObjNode: listOfDesignatorsInReverseArrayAssignment) {
    		if (designatorObjNode == null) continue;
    		
    		int i = listOfDesignatorsInReverseArrayAssignment.indexOf(designatorObjNode);
    		
    		// load i-th elem of array
    		Code.load(arrayObjNode);
    		Code.loadConst(i);
    		Code.put(Code.aload);
    		
    		Code.store(designatorObjNode);
    	}
    	
    	listOfDesignatorsInReverseArrayAssignment.clear();
    }
    
    /**************** function calls *****************/
    
    public void visit(CalledFunctionOrMethodDesignator node) {
    	Obj funcObjNode = node.getDesignator().obj;
    	stackOfInvokedFunctionObjNodes.push(funcObjNode);
    	
    	if (listOfClassMethodObjNodes.contains(funcObjNode)) {
    		// class method is invoked
    		Code.put(Code.dup);		// obj_adr for getfield
    	}
    }
    
    public void visit(ActPar node) {
    	Obj currentlyInvokedFunc = stackOfInvokedFunctionObjNodes.peek();
    	
    	if (listOfClassMethodObjNodes.contains(currentlyInvokedFunc)) {
    		// this for geting VFT_adr should come after act pars 
    		Code.put(Code.dup_x1); 	// copy of act param is places bellow this
    		Code.put(Code.pop);		// delete original act param, so this could be at the top of the stack 
    	}
    }
    
    public boolean checkIfObjNodeIsConstructor(Obj objNode) {
    	String className = objNode.getName();
    	String[] tmp = className.split("#", -1);
    	if (tmp.length == 2) className = tmp[0];
    	
    	Collection<Obj> classObjNodes = mapClassObjNodeToVFTadr.keySet();
    	for (Obj classObjNode: classObjNodes) {
    		String currClassName = classObjNode.getName();
    		if (className.equals(currClassName)) return true;
    	}
    	
    	return false;
    }
    
    public void invokeClassMethod(Obj methodObjNode) {
    	// this should already be on the expr stack
    	Code.put(Code.getfield);
    	Code.put2(0);				// VFT_adr field
    	
    	// call invokevirtual
    	Code.put(Code.invokevirtual);
    	
    	String methodName = methodObjNode.getName();
    	for (int i = 0; i < methodName.length(); i++) {
    		Code.put4(methodName.charAt(i));
    	}
    	Code.put4(-1);
    }
    
    public void invokeGlobalFunc(Obj globalFuncObjNode) {
    	String funcName = globalFuncObjNode.getName();
    	
    	if ("len".equals(funcName)) {
    		Code.put(Code.arraylength);
    		return;
    	}
    	else if ("ord".equals(funcName)) {
    		// value is already on stack
    		return;
    	}
    	else if ("chr".equals(funcName)) {
    		// value is already on stack
    		return;
    	}
    	
    	int func_adr = globalFuncObjNode.getAdr();
    	int pc_func_offset = func_adr - Code.pc;
    	
    	Code.put(Code.call);
    	Code.put2(pc_func_offset);
    }
    
    public void visit(DesignatorFunctionCall node) {
    	Obj funcObjNode = node.getCalledFunctionOrMethodDesignator().getDesignator().obj;
    	
    	if (listOfClassMethodObjNodes.contains(funcObjNode)) invokeClassMethod(funcObjNode);
    	else invokeGlobalFunc(funcObjNode);
    	
    	Struct returnTypeStruct = funcObjNode.getType();
    	if (returnTypeStruct != Tab.noType) {
    		// return val is not used
    		Code.put(Code.pop);
    	}
    	
    	stackOfInvokedFunctionObjNodes.pop();
    }
    
    public void visit(F_DesignatorFuncCall node) {
    	Obj funcObjNode = node.getCalledFunctionOrMethodDesignator().getDesignator().obj;
    	
    	if (listOfClassMethodObjNodes.contains(funcObjNode)) invokeClassMethod(funcObjNode);
    	else invokeGlobalFunc(funcObjNode);
    	
    	stackOfInvokedFunctionObjNodes.pop();
    }
    
    /*************************** if-else statement ****************************/
    
    // open and close if-else scope
    
    public void visit(OpenIfElseScope node) {
    	pushNewEmptyListsOnPatchingStacks();
    }
    
    public void visit(U_If node) {
    	// end of THEN block
    	while(!and_jumpsToBePatched.peek().isEmpty()) {
    		int adrToBePatched = and_jumpsToBePatched.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    	
    	popFromPatchingStacks();
    }
    
    public void visit(U_IfElse node) {
    	while(!skipElse_jumpsToBePached.peek().isEmpty()) {
    		int adrToBePatched = skipElse_jumpsToBePached.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    	
    	popFromPatchingStacks();
    }
    
    public void visit(M_IfElse node) {
    	while(!skipElse_jumpsToBePached.peek().isEmpty()) {
    		int adrToBePatched = skipElse_jumpsToBePached.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    	
    	popFromPatchingStacks();
    }
    
    /************** CondFact ***********/
    
    public void visit(CondFact_Expr node) {
    	// only one value is on expr stack (1 = true, 0 = false)
    	// if value is false, we skip further CondFact in this CondTerm
    	
    	Code.loadConst(1);
    	and_jumpsToBePatched.peek().add(Code.pc + 1); // jeq = 1 byte
    	Code.putFalseJump(Code.eq, 0); 	
    }
    
    public void visit(CondFact_RelopExpr node) {
    	// two values are on expr stack
    	
    	and_jumpsToBePatched.peek().add(Code.pc + 1); // jcc = 1 byte
    	
    	Relop relopSyntaxNode = node.getRelop();
    	int relopCode = -1;
    	
    	if (relopSyntaxNode instanceof R_Deq) 		relopCode = Code.eq;
    	else if (relopSyntaxNode instanceof R_Ne) 	relopCode = Code.ne;
    	else if (relopSyntaxNode instanceof R_Gt) 	relopCode = Code.gt;
    	else if (relopSyntaxNode instanceof R_Ge) 	relopCode = Code.ge;
    	else if (relopSyntaxNode instanceof R_Lt) 	relopCode = Code.lt;
    	else if (relopSyntaxNode instanceof R_Le) 	relopCode = Code.le;
    	
    	Code.putFalseJump(relopCode, 0);
    }
    
    /************** CondTerm ***********/
    
    public void visit(PatchAndConditionJumps node) {
    	// next symbol is OR
    	// EXTRA PAY ATTENTION: if we made it to here, CondTerm is true, so we can proceed to THEN block !!!
    	
    	or_jumpsToBePatched.peek().add(Code.pc + 1); // jmp = 1 byte
    	Code.putJump(0); // jump to THEN (will be patched later)
    	
    	// all false CondFact will jump to here (note that unconditional jump to THEN is before this)
    	
    	while(!and_jumpsToBePatched.peek().isEmpty()) {
    		int adrToBePatched = and_jumpsToBePatched.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    }
    
    public void visit(PatchOrConditionJumps node) {
    	// if any CondTerm is true, we will jump to THEN
    	
    	while(!or_jumpsToBePatched.peek().isEmpty()) {
    		int adrToBePatched = or_jumpsToBePatched.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    }
    
    // what about last CondFact and last CondTerm ?
    // nothing, because CondTerm is consisting of CondFact
    // last CondFact will remain to be patched, and we will patch it after THEN block
    
    public void visit(ExitIfBlock_PatchJumpsToElse node) {
    	// PAY ATTENTION: exit THEN block
    	skipElse_jumpsToBePached.peek().add(Code.pc + 1); // jmp = 1 byte
    	Code.putJump(0);
    	
    	// patch jumps to else
    	while(!and_jumpsToBePatched.peek().isEmpty()) {
    		int adrToBePatched = and_jumpsToBePatched.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    }
    
    /************************************* loops *************************************/
    
    public void visit(WhileLoopStart node) {
    	int whileStartAdr = Code.pc;
    	stackOfWhileStartAdr.push(whileStartAdr);
    	
    	pushNewEmptyListsOnPatchingStacks();
    	break_jumpsToBePached.push(new ArrayList<>());
    }
    
    public void visit(M_While node) {
    	int lastNestedWhileStartAdr = stackOfWhileStartAdr.pop();
    	Code.putJump(lastNestedWhileStartAdr);
    	
    	while(!and_jumpsToBePatched.peek().isEmpty()) {
    		int adrToBePatched = and_jumpsToBePatched.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    	
    	while(!break_jumpsToBePached.peek().isEmpty()) {
    		int adrToBePatched = break_jumpsToBePached.peek().remove(0);
    		Code.fixup(adrToBePatched);
    	}
    	
    	popFromPatchingStacks();
    	break_jumpsToBePached.pop();
    }
    
    public void visit(M_Break node) {
    	break_jumpsToBePached.peek().add(Code.pc + 1);
    	Code.putJump(0);
    }
    
    public void visit(M_Continue node) {
    	int lastNestedWhileStartAdr = stackOfWhileStartAdr.peek();
    	Code.putJump(lastNestedWhileStartAdr);
    }
}
