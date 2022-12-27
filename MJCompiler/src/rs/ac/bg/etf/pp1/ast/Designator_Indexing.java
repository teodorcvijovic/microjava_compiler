// generated with ast extension for cup
// version 0.8
// 27/11/2022 2:4:33


package rs.ac.bg.etf.pp1.ast;

public class Designator_Indexing extends Designator {

    private ArrayTypeDesignator ArrayTypeDesignator;
    private Expr Expr;

    public Designator_Indexing (ArrayTypeDesignator ArrayTypeDesignator, Expr Expr) {
        this.ArrayTypeDesignator=ArrayTypeDesignator;
        if(ArrayTypeDesignator!=null) ArrayTypeDesignator.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ArrayTypeDesignator getArrayTypeDesignator() {
        return ArrayTypeDesignator;
    }

    public void setArrayTypeDesignator(ArrayTypeDesignator ArrayTypeDesignator) {
        this.ArrayTypeDesignator=ArrayTypeDesignator;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayTypeDesignator!=null) ArrayTypeDesignator.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayTypeDesignator!=null) ArrayTypeDesignator.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayTypeDesignator!=null) ArrayTypeDesignator.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_Indexing(\n");

        if(ArrayTypeDesignator!=null)
            buffer.append(ArrayTypeDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_Indexing]");
        return buffer.toString();
    }
}
