// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class M_Return extends Matched {

    private ReturnOptionalExpr ReturnOptionalExpr;

    public M_Return (ReturnOptionalExpr ReturnOptionalExpr) {
        this.ReturnOptionalExpr=ReturnOptionalExpr;
        if(ReturnOptionalExpr!=null) ReturnOptionalExpr.setParent(this);
    }

    public ReturnOptionalExpr getReturnOptionalExpr() {
        return ReturnOptionalExpr;
    }

    public void setReturnOptionalExpr(ReturnOptionalExpr ReturnOptionalExpr) {
        this.ReturnOptionalExpr=ReturnOptionalExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnOptionalExpr!=null) ReturnOptionalExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnOptionalExpr!=null) ReturnOptionalExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnOptionalExpr!=null) ReturnOptionalExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("M_Return(\n");

        if(ReturnOptionalExpr!=null)
            buffer.append(ReturnOptionalExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [M_Return]");
        return buffer.toString();
    }
}
