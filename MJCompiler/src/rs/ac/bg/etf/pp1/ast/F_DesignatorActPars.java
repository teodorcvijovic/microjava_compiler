// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class F_DesignatorActPars extends Factor {

    private Designator Designator;
    private OptionalActParsParen OptionalActParsParen;

    public F_DesignatorActPars (Designator Designator, OptionalActParsParen OptionalActParsParen) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.OptionalActParsParen=OptionalActParsParen;
        if(OptionalActParsParen!=null) OptionalActParsParen.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public OptionalActParsParen getOptionalActParsParen() {
        return OptionalActParsParen;
    }

    public void setOptionalActParsParen(OptionalActParsParen OptionalActParsParen) {
        this.OptionalActParsParen=OptionalActParsParen;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(OptionalActParsParen!=null) OptionalActParsParen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(OptionalActParsParen!=null) OptionalActParsParen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(OptionalActParsParen!=null) OptionalActParsParen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("F_DesignatorActPars(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalActParsParen!=null)
            buffer.append(OptionalActParsParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_DesignatorActPars]");
        return buffer.toString();
    }
}
