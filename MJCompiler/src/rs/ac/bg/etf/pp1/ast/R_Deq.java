// generated with ast extension for cup
// version 0.8
// 25/11/2022 17:11:48


package rs.ac.bg.etf.pp1.ast;

public class R_Deq extends Relop {

    public R_Deq () {
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
        buffer.append("R_Deq(\n");

        buffer.append(tab);
        buffer.append(") [R_Deq]");
        return buffer.toString();
    }
}
