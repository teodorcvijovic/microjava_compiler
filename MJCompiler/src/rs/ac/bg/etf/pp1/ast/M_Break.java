// generated with ast extension for cup
// version 0.8
// 20/11/2022 1:59:16


package rs.ac.bg.etf.pp1.ast;

public class M_Break extends Matched {

    public M_Break () {
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
        buffer.append("M_Break(\n");

        buffer.append(tab);
        buffer.append(") [M_Break]");
        return buffer.toString();
    }
}
