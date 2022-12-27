// generated with ast extension for cup
// version 0.8
// 27/11/2022 13:38:2


package rs.ac.bg.etf.pp1.ast;

public class R_Ge extends Relop {

    public R_Ge () {
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
        buffer.append("R_Ge(\n");

        buffer.append(tab);
        buffer.append(") [R_Ge]");
        return buffer.toString();
    }
}
