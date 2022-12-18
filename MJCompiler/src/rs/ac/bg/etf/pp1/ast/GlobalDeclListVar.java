// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class GlobalDeclListVar extends GlobalDeclList {

    private GlobalDeclList GlobalDeclList;
    private Global_VarDecl Global_VarDecl;

    public GlobalDeclListVar (GlobalDeclList GlobalDeclList, Global_VarDecl Global_VarDecl) {
        this.GlobalDeclList=GlobalDeclList;
        if(GlobalDeclList!=null) GlobalDeclList.setParent(this);
        this.Global_VarDecl=Global_VarDecl;
        if(Global_VarDecl!=null) Global_VarDecl.setParent(this);
    }

    public GlobalDeclList getGlobalDeclList() {
        return GlobalDeclList;
    }

    public void setGlobalDeclList(GlobalDeclList GlobalDeclList) {
        this.GlobalDeclList=GlobalDeclList;
    }

    public Global_VarDecl getGlobal_VarDecl() {
        return Global_VarDecl;
    }

    public void setGlobal_VarDecl(Global_VarDecl Global_VarDecl) {
        this.Global_VarDecl=Global_VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalDeclList!=null) GlobalDeclList.accept(visitor);
        if(Global_VarDecl!=null) Global_VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseTopDown(visitor);
        if(Global_VarDecl!=null) Global_VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalDeclList!=null) GlobalDeclList.traverseBottomUp(visitor);
        if(Global_VarDecl!=null) Global_VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDeclListVar(\n");

        if(GlobalDeclList!=null)
            buffer.append(GlobalDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Global_VarDecl!=null)
            buffer.append(Global_VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDeclListVar]");
        return buffer.toString();
    }
}
