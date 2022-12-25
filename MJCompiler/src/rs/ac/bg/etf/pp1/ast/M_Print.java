// generated with ast extension for cup
// version 0.8
// 25/11/2022 2:21:17


package rs.ac.bg.etf.pp1.ast;

public class M_Print extends Matched {

    private Expr Expr;
    private PrintNumConst PrintNumConst;

    public M_Print (Expr Expr, PrintNumConst PrintNumConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintNumConst=PrintNumConst;
        if(PrintNumConst!=null) PrintNumConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintNumConst getPrintNumConst() {
        return PrintNumConst;
    }

    public void setPrintNumConst(PrintNumConst PrintNumConst) {
        this.PrintNumConst=PrintNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintNumConst!=null) PrintNumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintNumConst!=null) PrintNumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintNumConst!=null) PrintNumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("M_Print(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintNumConst!=null)
            buffer.append(PrintNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [M_Print]");
        return buffer.toString();
    }
}
