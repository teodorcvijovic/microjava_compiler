// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:10


package rs.ac.bg.etf.pp1.ast;

public class Class_VarDeclList_ extends Class_VarDeclList {

    private Class_VarDeclList Class_VarDeclList;
    private Class_VarDecl Class_VarDecl;

    public Class_VarDeclList_ (Class_VarDeclList Class_VarDeclList, Class_VarDecl Class_VarDecl) {
        this.Class_VarDeclList=Class_VarDeclList;
        if(Class_VarDeclList!=null) Class_VarDeclList.setParent(this);
        this.Class_VarDecl=Class_VarDecl;
        if(Class_VarDecl!=null) Class_VarDecl.setParent(this);
    }

    public Class_VarDeclList getClass_VarDeclList() {
        return Class_VarDeclList;
    }

    public void setClass_VarDeclList(Class_VarDeclList Class_VarDeclList) {
        this.Class_VarDeclList=Class_VarDeclList;
    }

    public Class_VarDecl getClass_VarDecl() {
        return Class_VarDecl;
    }

    public void setClass_VarDecl(Class_VarDecl Class_VarDecl) {
        this.Class_VarDecl=Class_VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Class_VarDeclList!=null) Class_VarDeclList.accept(visitor);
        if(Class_VarDecl!=null) Class_VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Class_VarDeclList!=null) Class_VarDeclList.traverseTopDown(visitor);
        if(Class_VarDecl!=null) Class_VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Class_VarDeclList!=null) Class_VarDeclList.traverseBottomUp(visitor);
        if(Class_VarDecl!=null) Class_VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_VarDeclList_(\n");

        if(Class_VarDeclList!=null)
            buffer.append(Class_VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Class_VarDecl!=null)
            buffer.append(Class_VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_VarDeclList_]");
        return buffer.toString();
    }
}
