// generated with ast extension for cup
// version 0.8
// 27/11/2022 16:35:24


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclList_Void extends MethodDeclList {

    private MethodDeclList MethodDeclList;
    private MethodDecl_Void MethodDecl_Void;
    private MethodDecl MethodDecl;

    public MethodDeclList_Void (MethodDeclList MethodDeclList, MethodDecl_Void MethodDecl_Void, MethodDecl MethodDecl) {
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.MethodDecl_Void=MethodDecl_Void;
        if(MethodDecl_Void!=null) MethodDecl_Void.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public MethodDecl_Void getMethodDecl_Void() {
        return MethodDecl_Void;
    }

    public void setMethodDecl_Void(MethodDecl_Void MethodDecl_Void) {
        this.MethodDecl_Void=MethodDecl_Void;
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
        if(MethodDecl_Void!=null) MethodDecl_Void.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(MethodDecl_Void!=null) MethodDecl_Void.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(MethodDecl_Void!=null) MethodDecl_Void.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclList_Void(\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl_Void!=null)
            buffer.append(MethodDecl_Void.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclList_Void]");
        return buffer.toString();
    }
}
