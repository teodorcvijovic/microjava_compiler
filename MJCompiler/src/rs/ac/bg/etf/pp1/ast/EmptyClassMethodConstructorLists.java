// generated with ast extension for cup
// version 0.8
// 17/11/2022 0:24:58


package rs.ac.bg.etf.pp1.ast;

public class EmptyClassMethodConstructorLists extends ClassMethodConstructorLists {

    public EmptyClassMethodConstructorLists () {
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
        buffer.append("EmptyClassMethodConstructorLists(\n");

        buffer.append(tab);
        buffer.append(") [EmptyClassMethodConstructorLists]");
        return buffer.toString();
    }
}
