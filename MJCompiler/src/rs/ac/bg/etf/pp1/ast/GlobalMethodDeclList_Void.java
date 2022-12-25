// generated with ast extension for cup
// version 0.8
// 25/11/2022 2:21:17


package rs.ac.bg.etf.pp1.ast;

public class GlobalMethodDeclList_Void extends GlobalMethodDeclList {

    private GlobalMethodDeclList GlobalMethodDeclList;
    private GlobalMethodDecl_Void GlobalMethodDecl_Void;
    private MethodDecl MethodDecl;

    public GlobalMethodDeclList_Void (GlobalMethodDeclList GlobalMethodDeclList, GlobalMethodDecl_Void GlobalMethodDecl_Void, MethodDecl MethodDecl) {
        this.GlobalMethodDeclList=GlobalMethodDeclList;
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.setParent(this);
        this.GlobalMethodDecl_Void=GlobalMethodDecl_Void;
        if(GlobalMethodDecl_Void!=null) GlobalMethodDecl_Void.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public GlobalMethodDeclList getGlobalMethodDeclList() {
        return GlobalMethodDeclList;
    }

    public void setGlobalMethodDeclList(GlobalMethodDeclList GlobalMethodDeclList) {
        this.GlobalMethodDeclList=GlobalMethodDeclList;
    }

    public GlobalMethodDecl_Void getGlobalMethodDecl_Void() {
        return GlobalMethodDecl_Void;
    }

    public void setGlobalMethodDecl_Void(GlobalMethodDecl_Void GlobalMethodDecl_Void) {
        this.GlobalMethodDecl_Void=GlobalMethodDecl_Void;
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
        if(GlobalMethodDecl_Void!=null) GlobalMethodDecl_Void.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseTopDown(visitor);
        if(GlobalMethodDecl_Void!=null) GlobalMethodDecl_Void.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseBottomUp(visitor);
        if(GlobalMethodDecl_Void!=null) GlobalMethodDecl_Void.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalMethodDeclList_Void(\n");

        if(GlobalMethodDeclList!=null)
            buffer.append(GlobalMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalMethodDecl_Void!=null)
            buffer.append(GlobalMethodDecl_Void.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalMethodDeclList_Void]");
        return buffer.toString();
    }
}
