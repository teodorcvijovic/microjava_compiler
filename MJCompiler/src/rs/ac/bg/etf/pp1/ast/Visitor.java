// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Factor Factor);
    public void visit(OptionalDesignatorList OptionalDesignatorList);
    public void visit(Statement Statement);
    public void visit(OptionalRelopExpr OptionalRelopExpr);
    public void visit(OtherConstDeclarations OtherConstDeclarations);
    public void visit(Or_CondTermList Or_CondTermList);
    public void visit(ExprList ExprList);
    public void visit(Relop Relop);
    public void visit(Global_NewVarDecl Global_NewVarDecl);
    public void visit(And_CondFactList And_CondFactList);
    public void visit(Class_VarDecl Class_VarDecl);
    public void visit(OptionalVarDeclList OptionalVarDeclList);
    public void visit(Expr Expr);
    public void visit(MulopFactorList MulopFactorList);
    public void visit(Class_OptionalVarDeclList Class_OptionalVarDeclList);
    public void visit(AddopTermList AddopTermList);
    public void visit(Global_OptionalVarDeclList Global_OptionalVarDeclList);
    public void visit(Dummy_ClassBody Dummy_ClassBody);
    public void visit(ExprActPars ExprActPars);
    public void visit(Unmatched Unmatched);
    public void visit(ParenWithOptionalActPars ParenWithOptionalActPars);
    public void visit(NewVarDecl NewVarDecl);
    public void visit(ReturnOptionalExpr ReturnOptionalExpr);
    public void visit(Program Program);
    public void visit(ConstructorMethodLists ConstructorMethodLists);
    public void visit(OptionalDesignator OptionalDesignator);
    public void visit(PrintNumConst PrintNumConst);
    public void visit(OptionalActPars OptionalActPars);
    public void visit(OptionalSquare OptionalSquare);
    public void visit(GlobalDeclList GlobalDeclList);
    public void visit(Constant Constant);
    public void visit(DesignatorList DesignatorList);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Addop Addop);
    public void visit(StatementList StatementList);
    public void visit(OptionalDerivation OptionalDerivation);
    public void visit(ConstructorDeclList ConstructorDeclList);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(ClassMethodConstructorLists ClassMethodConstructorLists);
    public void visit(OneFormPar OneFormPar);
    public void visit(DesignatorOptions DesignatorOptions);
    public void visit(IncOrDec IncOrDec);
    public void visit(Class_VarDeclList Class_VarDeclList);
    public void visit(Designator Designator);
    public void visit(OptionalMinus OptionalMinus);
    public void visit(GlobalMethodDeclList GlobalMethodDeclList);
    public void visit(Matched Matched);
    public void visit(VarDeclList VarDeclList);
    public void visit(CondFact CondFact);
    public void visit(TypeIdentList TypeIdentList);
    public void visit(FormParsOptional FormParsOptional);
    public void visit(Term Term);
    public void visit(ConditionIf ConditionIf);
    public void visit(M_Percent M_Percent);
    public void visit(M_Div M_Div);
    public void visit(M_Mul M_Mul);
    public void visit(A_Minus A_Minus);
    public void visit(A_Plus A_Plus);
    public void visit(R_Le R_Le);
    public void visit(R_Lt R_Lt);
    public void visit(R_Ge R_Ge);
    public void visit(R_Gt R_Gt);
    public void visit(R_Ne R_Ne);
    public void visit(R_Deq R_Deq);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(Designator_Indexing Designator_Indexing);
    public void visit(Designator_FieldAccess Designator_FieldAccess);
    public void visit(Designator_Ident Designator_Ident);
    public void visit(F_Expr F_Expr);
    public void visit(F_NewArray F_NewArray);
    public void visit(F_NewObjConstruction F_NewObjConstruction);
    public void visit(F_BoolConst F_BoolConst);
    public void visit(F_CharConst F_CharConst);
    public void visit(F_NumConst F_NumConst);
    public void visit(F_Designator F_Designator);
    public void visit(F_DesignatorFuncCall F_DesignatorFuncCall);
    public void visit(Term_MulFactor Term_MulFactor);
    public void visit(Term_Factor Term_Factor);
    public void visit(Expr_AddTerm Expr_AddTerm);
    public void visit(Expr_NegTerm Expr_NegTerm);
    public void visit(Expr_Term Expr_Term);
    public void visit(CondFact_RelopExpr CondFact_RelopExpr);
    public void visit(CondFact_Expr CondFact_Expr);
    public void visit(EmptyAndCondFactList EmptyAndCondFactList);
    public void visit(AndCondFactList AndCondFactList);
    public void visit(CondTerm CondTerm);
    public void visit(EmptyOrCondTermList EmptyOrCondTermList);
    public void visit(OrCondTermList OrCondTermList);
    public void visit(ConditionWhile ConditionWhile);
    public void visit(ERROR_ConditionIf ERROR_ConditionIf);
    public void visit(ConditionIf_ ConditionIf_);
    public void visit(Condition Condition);
    public void visit(EmptyExprList EmptyExprList);
    public void visit(ExprList_ ExprList_);
    public void visit(ActPars ActPars);
    public void visit(EmptyDesignatorList EmptyDesignatorList);
    public void visit(DesignatorList_ DesignatorList_);
    public void visit(NoOptionalDesignator NoOptionalDesignator);
    public void visit(OptionalDesignator_ OptionalDesignator_);
    public void visit(NoOptionalActPars NoOptionalActPars);
    public void visit(OptionalActPars_ OptionalActPars_);
    public void visit(OptionalDesignatorListDerived1 OptionalDesignatorListDerived1);
    public void visit(Dec Dec);
    public void visit(Inc Inc);
    public void visit(ERROR_DesignatorStatement ERROR_DesignatorStatement);
    public void visit(ReverseArrayAssignment ReverseArrayAssignment);
    public void visit(DesignatorIncDec DesignatorIncDec);
    public void visit(DesignatorFunctionCall DesignatorFunctionCall);
    public void visit(DesignatorAssignment DesignatorAssignment);
    public void visit(NoPrintNumConst NoPrintNumConst);
    public void visit(PrintNumConst_ PrintNumConst_);
    public void visit(NoReturnExpr NoReturnExpr);
    public void visit(ReturnOptionalExpr_ ReturnOptionalExpr_);
    public void visit(CurrVarDesignator CurrVarDesignator);
    public void visit(DesignatorForeach DesignatorForeach);
    public void visit(WhileLoopStart WhileLoopStart);
    public void visit(M_Block M_Block);
    public void visit(M_Foreach M_Foreach);
    public void visit(M_Print M_Print);
    public void visit(M_Read M_Read);
    public void visit(M_Return M_Return);
    public void visit(M_Continue M_Continue);
    public void visit(M_Break M_Break);
    public void visit(M_While M_While);
    public void visit(M_IfElse M_IfElse);
    public void visit(M_Designator M_Designator);
    public void visit(U_IfElse U_IfElse);
    public void visit(U_If U_If);
    public void visit(Unmatched_ Unmatched_);
    public void visit(Matched_ Matched_);
    public void visit(Type Type);
    public void visit(EmptyTypeIdentList EmptyTypeIdentList);
    public void visit(TypeIdentList_ TypeIdentList_);
    public void visit(ERROR_OneFormPar ERROR_OneFormPar);
    public void visit(OneFormPar_ OneFormPar_);
    public void visit(FormPars FormPars);
    public void visit(MethodDecl MethodDecl);
    public void visit(EmptyStatementList EmptyStatementList);
    public void visit(StatementList_ StatementList_);
    public void visit(NoFormPars NoFormPars);
    public void visit(FormPars_ FormPars_);
    public void visit(EmptyClassVarDeclList EmptyClassVarDeclList);
    public void visit(VarDeclList_ VarDeclList_);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(MethodDecl_Ident MethodDecl_Ident);
    public void visit(MethodDecl_Void MethodDecl_Void);
    public void visit(NoConstructorDeclList NoConstructorDeclList);
    public void visit(ConstructorDeclList_ ConstructorDeclList_);
    public void visit(OneMethodDecl OneMethodDecl);
    public void visit(MethodDeclList_Void MethodDeclList_Void);
    public void visit(MethodDeclList_Ident MethodDeclList_Ident);
    public void visit(ConstructorDecl_Start ConstructorDecl_Start);
    public void visit(EmptyClassMethodConstructorLists EmptyClassMethodConstructorLists);
    public void visit(Methods_Void Methods_Void);
    public void visit(Methods_Ident Methods_Ident);
    public void visit(Constructors Constructors);
    public void visit(ConstructorsAndMethods_Void ConstructorsAndMethods_Void);
    public void visit(ConstructorsAndMethods_Ident ConstructorsAndMethods_Ident);
    public void visit(EmptyConstructorMethodLists EmptyConstructorMethodLists);
    public void visit(ConstructorMethodLists_ ConstructorMethodLists_);
    public void visit(ClassDerivation ClassDerivation);
    public void visit(NoDerivation NoDerivation);
    public void visit(OptionalDerivation_ OptionalDerivation_);
    public void visit(Class_EmptyVarDeclList Class_EmptyVarDeclList);
    public void visit(Class_VarDeclList_ Class_VarDeclList_);
    public void visit(Dummy_ClassBody_ Dummy_ClassBody_);
    public void visit(ClassBody ClassBody);
    public void visit(ClassNameAndDerivation ClassNameAndDerivation);
    public void visit(ClassDecl ClassDecl);
    public void visit(Class_EmptyOptionalVarDeclList Class_EmptyOptionalVarDeclList);
    public void visit(Class_OptionalVarDeclList_ Class_OptionalVarDeclList_);
    public void visit(Class_NewVarDecl Class_NewVarDecl);
    public void visit(ERROR_Class_VarDecl ERROR_Class_VarDecl);
    public void visit(Class_VarDecl_ Class_VarDecl_);
    public void visit(NoSquare NoSquare);
    public void visit(OptionalSquare_ OptionalSquare_);
    public void visit(ERROR_OptinalGlobalVarDeclList ERROR_OptinalGlobalVarDeclList);
    public void visit(Global_EmptyOptionalVarDeclList Global_EmptyOptionalVarDeclList);
    public void visit(Global_OptionalVarDeclList_ Global_OptionalVarDeclList_);
    public void visit(ERROR_NewGlobalVarDecl ERROR_NewGlobalVarDecl);
    public void visit(Global_NewVarDecl_ Global_NewVarDecl_);
    public void visit(Global_VarDecl Global_VarDecl);
    public void visit(EmptyOptionalVarDeclList EmptyOptionalVarDeclList);
    public void visit(OptionalVarDeclList_ OptionalVarDeclList_);
    public void visit(NewVarDecl_ NewVarDecl_);
    public void visit(VarDecl VarDecl);
    public void visit(NoOtherConstDeclatations NoOtherConstDeclatations);
    public void visit(OtherConstDeclarations_ OtherConstDeclarations_);
    public void visit(BoolConstant BoolConstant);
    public void visit(CharConstant CharConstant);
    public void visit(NumConstant NumConstant);
    public void visit(ConstDecl ConstDecl);
    public void visit(GlobalMethodDecl_Ident GlobalMethodDecl_Ident);
    public void visit(GlobalMethodDecl_Void GlobalMethodDecl_Void);
    public void visit(EmptyGlobalMethodDeclList EmptyGlobalMethodDeclList);
    public void visit(GlobalMethodDeclList_Ident GlobalMethodDeclList_Ident);
    public void visit(GlobalMethodDeclList_Void GlobalMethodDeclList_Void);
    public void visit(EmptyGlobalDeclList EmptyGlobalDeclList);
    public void visit(GlobalDeclListClass GlobalDeclListClass);
    public void visit(GlobalDeclListVar GlobalDeclListVar);
    public void visit(GlobalDeclListConst GlobalDeclListConst);
    public void visit(ProgramName ProgramName);
    public void visit(Program_ Program_);

}
