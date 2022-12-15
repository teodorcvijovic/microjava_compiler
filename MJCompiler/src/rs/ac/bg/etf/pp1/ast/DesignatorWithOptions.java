// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:10


package rs.ac.bg.etf.pp1.ast;

public class DesignatorWithOptions extends DesignatorStatement {

    private Designator Designator;
    private DesignatorOptions DesignatorOptions;

    public DesignatorWithOptions (Designator Designator, DesignatorOptions DesignatorOptions) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorOptions=DesignatorOptions;
        if(DesignatorOptions!=null) DesignatorOptions.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorOptions getDesignatorOptions() {
        return DesignatorOptions;
    }

    public void setDesignatorOptions(DesignatorOptions DesignatorOptions) {
        this.DesignatorOptions=DesignatorOptions;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorOptions!=null) DesignatorOptions.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorOptions!=null) DesignatorOptions.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorOptions!=null) DesignatorOptions.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorWithOptions(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorOptions!=null)
            buffer.append(DesignatorOptions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorWithOptions]");
        return buffer.toString();
    }
}
