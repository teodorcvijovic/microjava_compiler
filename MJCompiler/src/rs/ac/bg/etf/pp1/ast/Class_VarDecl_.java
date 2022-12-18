// generated with ast extension for cup
// version 0.8
// 18/11/2022 2:36:12


package rs.ac.bg.etf.pp1.ast;

public class Class_VarDecl_ extends Class_VarDecl {

    private Type Type;
    private Class_NewVarDecl Class_NewVarDecl;
    private Class_OptionalVarDeclList Class_OptionalVarDeclList;

    public Class_VarDecl_ (Type Type, Class_NewVarDecl Class_NewVarDecl, Class_OptionalVarDeclList Class_OptionalVarDeclList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Class_NewVarDecl=Class_NewVarDecl;
        if(Class_NewVarDecl!=null) Class_NewVarDecl.setParent(this);
        this.Class_OptionalVarDeclList=Class_OptionalVarDeclList;
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public Class_NewVarDecl getClass_NewVarDecl() {
        return Class_NewVarDecl;
    }

    public void setClass_NewVarDecl(Class_NewVarDecl Class_NewVarDecl) {
        this.Class_NewVarDecl=Class_NewVarDecl;
    }

    public Class_OptionalVarDeclList getClass_OptionalVarDeclList() {
        return Class_OptionalVarDeclList;
    }

    public void setClass_OptionalVarDeclList(Class_OptionalVarDeclList Class_OptionalVarDeclList) {
        this.Class_OptionalVarDeclList=Class_OptionalVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.accept(visitor);
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.traverseTopDown(visitor);
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Class_NewVarDecl!=null) Class_NewVarDecl.traverseBottomUp(visitor);
        if(Class_OptionalVarDeclList!=null) Class_OptionalVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class_VarDecl_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Class_NewVarDecl!=null)
            buffer.append(Class_NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Class_OptionalVarDeclList!=null)
            buffer.append(Class_OptionalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_VarDecl_]");
        return buffer.toString();
    }
}
