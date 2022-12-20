// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class Methods_Ident extends ClassMethodConstructorLists {

    private MethodDecl_Ident MethodDecl_Ident;
    private MethodDeclList MethodDeclList;

    public Methods_Ident (MethodDecl_Ident MethodDecl_Ident, MethodDeclList MethodDeclList) {
        this.MethodDecl_Ident=MethodDecl_Ident;
        if(MethodDecl_Ident!=null) MethodDecl_Ident.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
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
        if(MethodDecl_Ident!=null) MethodDecl_Ident.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Methods_Ident(\n");

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
        buffer.append(") [Methods_Ident]");
        return buffer.toString();
    }
}
