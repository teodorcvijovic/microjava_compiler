// generated with ast extension for cup
// version 0.8
// 20/11/2022 1:59:16


package rs.ac.bg.etf.pp1.ast;

public class Dec extends IncOrDec {

    public Dec () {
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
        buffer.append("Dec(\n");

        buffer.append(tab);
        buffer.append(") [Dec]");
        return buffer.toString();
    }
}