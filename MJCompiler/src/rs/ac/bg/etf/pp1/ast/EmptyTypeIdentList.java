// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class EmptyTypeIdentList extends TypeIdentList {

    public EmptyTypeIdentList () {
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
        buffer.append("EmptyTypeIdentList(\n");

        buffer.append(tab);
        buffer.append(") [EmptyTypeIdentList]");
        return buffer.toString();
    }
}
