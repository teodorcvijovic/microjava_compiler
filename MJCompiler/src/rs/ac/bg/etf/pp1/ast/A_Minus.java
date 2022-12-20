// generated with ast extension for cup
// version 0.8
// 20/11/2022 14:13:48


package rs.ac.bg.etf.pp1.ast;

public class A_Minus extends Addop {

    public A_Minus () {
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
        buffer.append("A_Minus(\n");

        buffer.append(tab);
        buffer.append(") [A_Minus]");
        return buffer.toString();
    }
}
