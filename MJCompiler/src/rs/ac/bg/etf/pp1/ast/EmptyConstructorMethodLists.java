// generated with ast extension for cup
// version 0.8
// 18/11/2022 2:36:12


package rs.ac.bg.etf.pp1.ast;

public class EmptyConstructorMethodLists extends ConstructorMethodLists {

    public EmptyConstructorMethodLists () {
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
        buffer.append("EmptyConstructorMethodLists(\n");

        buffer.append(tab);
        buffer.append(") [EmptyConstructorMethodLists]");
        return buffer.toString();
    }
}
