// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class Designator_Ident extends Designator {

    private String designatorIdentName;

    public Designator_Ident (String designatorIdentName) {
        this.designatorIdentName=designatorIdentName;
    }

    public String getDesignatorIdentName() {
        return designatorIdentName;
    }

    public void setDesignatorIdentName(String designatorIdentName) {
        this.designatorIdentName=designatorIdentName;
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
        buffer.append("Designator_Ident(\n");

        buffer.append(" "+tab+designatorIdentName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_Ident]");
        return buffer.toString();
    }
}
