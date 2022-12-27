// generated with ast extension for cup
// version 0.8
// 27/11/2022 16:35:24


package rs.ac.bg.etf.pp1.ast;

public class R_Gt extends Relop {

    public R_Gt () {
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
        buffer.append("R_Gt(\n");

        buffer.append(tab);
        buffer.append(") [R_Gt]");
        return buffer.toString();
    }
}
