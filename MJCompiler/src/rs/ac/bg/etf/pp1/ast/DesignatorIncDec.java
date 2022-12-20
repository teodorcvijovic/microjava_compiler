// generated with ast extension for cup
// version 0.8
// 20/11/2022 14:13:48


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIncDec extends DesignatorStatement {

    private Designator Designator;
    private IncOrDec IncOrDec;

    public DesignatorIncDec (Designator Designator, IncOrDec IncOrDec) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.IncOrDec=IncOrDec;
        if(IncOrDec!=null) IncOrDec.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public IncOrDec getIncOrDec() {
        return IncOrDec;
    }

    public void setIncOrDec(IncOrDec IncOrDec) {
        this.IncOrDec=IncOrDec;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(IncOrDec!=null) IncOrDec.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(IncOrDec!=null) IncOrDec.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(IncOrDec!=null) IncOrDec.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorIncDec(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IncOrDec!=null)
            buffer.append(IncOrDec.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIncDec]");
        return buffer.toString();
    }
}
