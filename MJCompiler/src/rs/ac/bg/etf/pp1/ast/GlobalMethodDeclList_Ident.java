// generated with ast extension for cup
// version 0.8
// 24/11/2022 20:29:47


package rs.ac.bg.etf.pp1.ast;

public class GlobalMethodDeclList_Ident extends GlobalMethodDeclList {

    private GlobalMethodDeclList GlobalMethodDeclList;
    private GlobalMethodDecl_Ident GlobalMethodDecl_Ident;
    private MethodDecl MethodDecl;

    public GlobalMethodDeclList_Ident (GlobalMethodDeclList GlobalMethodDeclList, GlobalMethodDecl_Ident GlobalMethodDecl_Ident, MethodDecl MethodDecl) {
        this.GlobalMethodDeclList=GlobalMethodDeclList;
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.setParent(this);
        this.GlobalMethodDecl_Ident=GlobalMethodDecl_Ident;
        if(GlobalMethodDecl_Ident!=null) GlobalMethodDecl_Ident.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public GlobalMethodDeclList getGlobalMethodDeclList() {
        return GlobalMethodDeclList;
    }

    public void setGlobalMethodDeclList(GlobalMethodDeclList GlobalMethodDeclList) {
        this.GlobalMethodDeclList=GlobalMethodDeclList;
    }

    public GlobalMethodDecl_Ident getGlobalMethodDecl_Ident() {
        return GlobalMethodDecl_Ident;
    }

    public void setGlobalMethodDecl_Ident(GlobalMethodDecl_Ident GlobalMethodDecl_Ident) {
        this.GlobalMethodDecl_Ident=GlobalMethodDecl_Ident;
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
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.accept(visitor);
        if(GlobalMethodDecl_Ident!=null) GlobalMethodDecl_Ident.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseTopDown(visitor);
        if(GlobalMethodDecl_Ident!=null) GlobalMethodDecl_Ident.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseBottomUp(visitor);
        if(GlobalMethodDecl_Ident!=null) GlobalMethodDecl_Ident.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalMethodDeclList_Ident(\n");

        if(GlobalMethodDeclList!=null)
            buffer.append(GlobalMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalMethodDecl_Ident!=null)
            buffer.append(GlobalMethodDecl_Ident.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalMethodDeclList_Ident]");
        return buffer.toString();
    }
}
