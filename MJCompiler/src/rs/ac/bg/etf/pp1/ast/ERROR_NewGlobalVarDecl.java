// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:20


package rs.ac.bg.etf.pp1.ast;

public class ERROR_NewGlobalVarDecl extends Global_NewVarDecl {

    public ERROR_NewGlobalVarDecl () {
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
        buffer.append("ERROR_NewGlobalVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [ERROR_NewGlobalVarDecl]");
        return buffer.toString();
    }
}
