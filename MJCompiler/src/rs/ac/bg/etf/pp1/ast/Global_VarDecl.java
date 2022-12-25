// generated with ast extension for cup
// version 0.8
// 25/11/2022 17:11:48


package rs.ac.bg.etf.pp1.ast;

public class Global_VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private Global_NewVarDecl Global_NewVarDecl;
    private Global_OptionalVarDeclList Global_OptionalVarDeclList;

    public Global_VarDecl (Type Type, Global_NewVarDecl Global_NewVarDecl, Global_OptionalVarDeclList Global_OptionalVarDeclList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Global_NewVarDecl=Global_NewVarDecl;
        if(Global_NewVarDecl!=null) Global_NewVarDecl.setParent(this);
        this.Global_OptionalVarDeclList=Global_OptionalVarDeclList;
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public Global_NewVarDecl getGlobal_NewVarDecl() {
        return Global_NewVarDecl;
    }

    public void setGlobal_NewVarDecl(Global_NewVarDecl Global_NewVarDecl) {
        this.Global_NewVarDecl=Global_NewVarDecl;
    }

    public Global_OptionalVarDeclList getGlobal_OptionalVarDeclList() {
        return Global_OptionalVarDeclList;
    }

    public void setGlobal_OptionalVarDeclList(Global_OptionalVarDeclList Global_OptionalVarDeclList) {
        this.Global_OptionalVarDeclList=Global_OptionalVarDeclList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.accept(visitor);
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.traverseTopDown(visitor);
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Global_NewVarDecl!=null) Global_NewVarDecl.traverseBottomUp(visitor);
        if(Global_OptionalVarDeclList!=null) Global_OptionalVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Global_VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Global_NewVarDecl!=null)
            buffer.append(Global_NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Global_OptionalVarDeclList!=null)
            buffer.append(Global_OptionalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Global_VarDecl]");
        return buffer.toString();
    }
}
