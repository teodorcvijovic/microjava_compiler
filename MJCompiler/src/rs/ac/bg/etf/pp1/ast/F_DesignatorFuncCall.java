// generated with ast extension for cup
// version 0.8
// 20/11/2022 1:59:16


package rs.ac.bg.etf.pp1.ast;

public class F_DesignatorFuncCall extends Factor {

    private Designator Designator;
    private ParenWithOptionalActPars ParenWithOptionalActPars;

    public F_DesignatorFuncCall (Designator Designator, ParenWithOptionalActPars ParenWithOptionalActPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ParenWithOptionalActPars=ParenWithOptionalActPars;
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ParenWithOptionalActPars getParenWithOptionalActPars() {
        return ParenWithOptionalActPars;
    }

    public void setParenWithOptionalActPars(ParenWithOptionalActPars ParenWithOptionalActPars) {
        this.ParenWithOptionalActPars=ParenWithOptionalActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("F_DesignatorFuncCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ParenWithOptionalActPars!=null)
            buffer.append(ParenWithOptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_DesignatorFuncCall]");
        return buffer.toString();
    }
}
