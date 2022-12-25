// generated with ast extension for cup
// version 0.8
// 25/11/2022 2:21:17


package rs.ac.bg.etf.pp1.ast;

public class EmptyClassVarDeclList extends VarDeclList {

    public EmptyClassVarDeclList () {
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
        buffer.append("EmptyClassVarDeclList(\n");

        buffer.append(tab);
        buffer.append(") [EmptyClassVarDeclList]");
        return buffer.toString();
    }
}
