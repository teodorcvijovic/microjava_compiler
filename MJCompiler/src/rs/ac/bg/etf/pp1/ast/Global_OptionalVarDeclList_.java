// generated with ast extension for cup
// version 0.8
// 20/11/2022 14:13:48


package rs.ac.bg.etf.pp1.ast;

public class Global_OptionalVarDeclList_ extends Global_OptionalVarDeclList {

    private Global_OptionalVarDeclList Global_OptionalVarDeclList;
    private Global_NewVarDecl Global_NewVarDecl;

    public Global_OptionalVarDeclList_ (Global_OptionalVarDeclList Global_OptionalVarDeclList, Global_NewVarDecl Global_NewVarDecl) {
        this.Global_OptionalVarDeclList=Global_OptionalVarDeclList;
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.setParent(this);
        this.Global_NewVarDecl=Global_NewVarDecl;
        if(Global_NewVarDecl!=null) Global_NewVarDecl.setParent(this);
    }

    public Global_OptionalVarDeclList getGlobal_OptionalVarDeclList() {
        return Global_OptionalVarDeclList;
    }

    public void setGlobal_OptionalVarDeclList(Global_OptionalVarDeclList Global_OptionalVarDeclList) {
        this.Global_OptionalVarDeclList=Global_OptionalVarDeclList;
    }

    public Global_NewVarDecl getGlobal_NewVarDecl() {
        return Global_NewVarDecl;
    }

    public void setGlobal_NewVarDecl(Global_NewVarDecl Global_NewVarDecl) {
        this.Global_NewVarDecl=Global_NewVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.accept(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.traverseTopDown(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.traverseBottomUp(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Global_OptionalVarDeclList_(\n");

        if(Global_OptionalVarDeclList!=null)
            buffer.append(Global_OptionalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Global_NewVarDecl!=null)
            buffer.append(Global_NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Global_OptionalVarDeclList_]");
        return buffer.toString();
    }
}
