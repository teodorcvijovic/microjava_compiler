// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class ERROR_ConditionIf extends ConditionIf {

    public ERROR_ConditionIf () {
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
        buffer.append("ERROR_ConditionIf(\n");

        buffer.append(tab);
        buffer.append(") [ERROR_ConditionIf]");
        return buffer.toString();
    }
}
