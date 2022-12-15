// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:11


package rs.ac.bg.etf.pp1.ast;

public class NoOptionalRelopExpr extends OptionalRelopExpr {

    public NoOptionalRelopExpr () {
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
        buffer.append("NoOptionalRelopExpr(\n");

        buffer.append(tab);
        buffer.append(") [NoOptionalRelopExpr]");
        return buffer.toString();
    }
}
