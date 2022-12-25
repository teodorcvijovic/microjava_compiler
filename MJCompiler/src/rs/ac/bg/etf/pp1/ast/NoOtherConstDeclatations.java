// generated with ast extension for cup
// version 0.8
// 25/11/2022 2:21:17


package rs.ac.bg.etf.pp1.ast;

public class NoOtherConstDeclatations extends OtherConstDeclarations {

    public NoOtherConstDeclatations () {
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
        buffer.append("NoOtherConstDeclatations(\n");

        buffer.append(tab);
        buffer.append(") [NoOtherConstDeclatations]");
        return buffer.toString();
    }
}
