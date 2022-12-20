// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class ERROR_OptinalGlobalVarDeclList extends Global_OptionalVarDeclList {

    public ERROR_OptinalGlobalVarDeclList () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ERROR_OptinalGlobalVarDeclList(\n");

        buffer.append(tab);
        buffer.append(") [ERROR_OptinalGlobalVarDeclList]");
        return buffer.toString();
    }
}
