// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:10


package rs.ac.bg.etf.pp1.ast;

public class NoOptionalActPars extends OptionalActPars {

    public NoOptionalActPars () {
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
        buffer.append("NoOptionalActPars(\n");

        buffer.append(tab);
        buffer.append(") [NoOptionalActPars]");
        return buffer.toString();
    }
}
