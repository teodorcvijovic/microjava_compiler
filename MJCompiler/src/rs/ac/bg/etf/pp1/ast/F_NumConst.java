// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class F_NumConst extends Factor {

    private Integer constValue;

    public F_NumConst (Integer constValue) {
        this.constValue=constValue;
    }

    public Integer getConstValue() {
        return constValue;
    }

    public void setConstValue(Integer constValue) {
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
        buffer.append("F_NumConst(\n");

        buffer.append(" "+tab+constValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_NumConst]");
        return buffer.toString();
    }
}
