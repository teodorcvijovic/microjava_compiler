// generated with ast extension for cup
// version 0.8
// 24/11/2022 20:29:47


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclList_Ident extends MethodDeclList {

    private MethodDeclList MethodDeclList;
    private MethodDecl_Ident MethodDecl_Ident;
    private MethodDecl MethodDecl;

    public MethodDeclList_Ident (MethodDeclList MethodDeclList, MethodDecl_Ident MethodDecl_Ident, MethodDecl MethodDecl) {
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.MethodDecl_Ident=MethodDecl_Ident;
        if(MethodDecl_Ident!=null) MethodDecl_Ident.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public MethodDecl_Ident getMethodDecl_Ident() {
        return MethodDecl_Ident;
    }

    public void setMethodDecl_Ident(MethodDecl_Ident MethodDecl_Ident) {
        this.MethodDecl_Ident=MethodDecl_Ident;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(MethodDecl_Ident!=null) MethodDecl_Ident.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclList_Ident(\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl_Ident!=null)
            buffer.append(MethodDecl_Ident.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclList_Ident]");
        return buffer.toString();
    }
}
