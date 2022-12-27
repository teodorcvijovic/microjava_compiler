// generated with ast extension for cup
// version 0.8
// 27/11/2022 2:4:33


package rs.ac.bg.etf.pp1.ast;

public class M_Mul extends Mulop {

    public M_Mul () {
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
        buffer.append("M_Mul(\n");

        buffer.append(tab);
        buffer.append(") [M_Mul]");
        return buffer.toString();
    }
}
