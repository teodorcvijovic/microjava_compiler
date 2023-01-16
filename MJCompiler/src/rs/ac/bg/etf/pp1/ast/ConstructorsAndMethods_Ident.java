// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class ConstructorsAndMethods_Ident extends ClassMethodConstructorLists {

    private ConstructorDecl_Start ConstructorDecl_Start;
    private ConstructorDeclList ConstructorDeclList;
    private MethodDecl_Ident MethodDecl_Ident;
    private MethodDeclList MethodDeclList;

    public ConstructorsAndMethods_Ident (ConstructorDecl_Start ConstructorDecl_Start, ConstructorDeclList ConstructorDeclList, MethodDecl_Ident MethodDecl_Ident, MethodDeclList MethodDeclList) {
        this.ConstructorDecl_Start=ConstructorDecl_Start;
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.setParent(this);
        this.ConstructorDeclList=ConstructorDeclList;
        if(ConstructorDeclList!=null) ConstructorDeclList.setParent(this);
        this.MethodDecl_Ident=MethodDecl_Ident;
        if(MethodDecl_Ident!=null) MethodDecl_Ident.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public ConstructorDecl_Start getConstructorDecl_Start() {
        return ConstructorDecl_Start;
    }

    public void setConstructorDecl_Start(ConstructorDecl_Start ConstructorDecl_Start) {
        this.ConstructorDecl_Start=ConstructorDecl_Start;
    }

    public ConstructorDeclList getConstructorDeclList() {
        return ConstructorDeclList;
    }

    public void setConstructorDeclList(ConstructorDeclList ConstructorDeclList) {
        this.ConstructorDeclList=ConstructorDeclList;
    }

    public MethodDecl_Ident getMethodDecl_Ident() {
        return MethodDecl_Ident;
    }

    public void setMethodDecl_Ident(MethodDecl_Ident MethodDecl_Ident) {
        this.MethodDecl_Ident=MethodDecl_Ident;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.accept(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.accept(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseTopDown(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseTopDown(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseBottomUp(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseBottomUp(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorsAndMethods_Ident(\n");

        if(ConstructorDecl_Start!=null)
            buffer.append(ConstructorDecl_Start.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDeclList!=null)
            buffer.append(ConstructorDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl_Ident!=null)
            buffer.append(MethodDecl_Ident.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorsAndMethods_Ident]");
        return buffer.toString();
    }
}
