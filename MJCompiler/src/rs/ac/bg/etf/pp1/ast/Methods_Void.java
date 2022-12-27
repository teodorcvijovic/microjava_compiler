// generated with ast extension for cup
// version 0.8
// 27/11/2022 16:35:24


package rs.ac.bg.etf.pp1.ast;

public class Methods_Void extends ClassMethodConstructorLists {

    private MethodDecl_Void MethodDecl_Void;
    private MethodDeclList MethodDeclList;

    public Methods_Void (MethodDecl_Void MethodDecl_Void, MethodDeclList MethodDeclList) {
        this.MethodDecl_Void=MethodDecl_Void;
        if(MethodDecl_Void!=null) MethodDecl_Void.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public MethodDecl_Void getMethodDecl_Void() {
        return MethodDecl_Void;
    }

    public void setMethodDecl_Void(MethodDecl_Void MethodDecl_Void) {
        this.MethodDecl_Void=MethodDecl_Void;
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
        if(MethodDecl_Void!=null) MethodDecl_Void.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDecl_Void!=null) MethodDecl_Void.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDecl_Void!=null) MethodDecl_Void.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Methods_Void(\n");

        if(MethodDecl_Void!=null)
            buffer.append(MethodDecl_Void.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Methods_Void]");
        return buffer.toString();
    }
}
