// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class F_CharConst extends Factor {

    private Character constValue;

    public F_CharConst (Character constValue) {
        this.constValue=constValue;
    }

    public Character getConstValue() {
        return constValue;
    }

    public void setConstValue(Character constValue) {
        this.constValue=constValue;
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
        buffer.append("F_CharConst(\n");

        buffer.append(" "+tab+constValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_CharConst]");
        return buffer.toString();
    }
}
