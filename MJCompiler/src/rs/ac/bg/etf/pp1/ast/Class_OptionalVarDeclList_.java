// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class Class_OptionalVarDeclList_ extends Class_OptionalVarDeclList {

    private Class_OptionalVarDeclList Class_OptionalVarDeclList;
    private Class_NewVarDecl Class_NewVarDecl;

    public Class_OptionalVarDeclList_ (Class_OptionalVarDeclList Class_OptionalVarDeclList, Class_NewVarDecl Class_NewVarDecl) {
        this.Class_OptionalVarDeclList=Class_OptionalVarDeclList;
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.setParent(this);
        this.Class_NewVarDecl=Class_NewVarDecl;
        if(Class_NewVarDecl!=null) Class_NewVarDecl.setParent(this);
    }

    public Class_OptionalVarDeclList getClass_OptionalVarDeclList() {
        return Class_OptionalVarDeclList;
    }

    public void setClass_OptionalVarDeclList(Class_OptionalVarDeclList Class_OptionalVarDeclList) {
        this.Class_OptionalVarDeclList=Class_OptionalVarDeclList;
    }

    public Class_NewVarDecl getClass_NewVarDecl() {
        return Class_NewVarDecl;
    }

    public void setClass_NewVarDecl(Class_NewVarDecl Class_NewVarDecl) {
        this.Class_NewVarDecl=Class_NewVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.accept(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.traverseTopDown(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.traverseBottomUp(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_OptionalVarDeclList_(\n");

        if(Class_OptionalVarDeclList!=null)
            buffer.append(Class_OptionalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Class_NewVarDecl!=null)
            buffer.append(Class_NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_OptionalVarDeclList_]");
        return buffer.toString();
    }
}
