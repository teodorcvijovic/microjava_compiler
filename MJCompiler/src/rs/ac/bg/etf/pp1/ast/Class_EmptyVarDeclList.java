// generated with ast extension for cup
// version 0.8
// 24/11/2022 20:29:47


package rs.ac.bg.etf.pp1.ast;

public class Class_EmptyVarDeclList extends Class_VarDeclList {

    public Class_EmptyVarDeclList () {
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
        buffer.append("Class_EmptyVarDeclList(\n");

        buffer.append(tab);
        buffer.append(") [Class_EmptyVarDeclList]");
        return buffer.toString();
    }
}
