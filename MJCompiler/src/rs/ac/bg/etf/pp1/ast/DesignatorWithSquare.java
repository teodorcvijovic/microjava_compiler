// generated with ast extension for cup
// version 0.8
// 18/11/2022 2:36:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorWithSquare extends DesignatorStatement {

    private OptionalDesignatorList OptionalDesignatorList;
    private Designator Designator;

    public DesignatorWithSquare (OptionalDesignatorList OptionalDesignatorList, Designator Designator) {
        this.OptionalDesignatorList=OptionalDesignatorList;
        if(OptionalDesignatorList!=null) OptionalDesignatorList.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public OptionalDesignatorList getOptionalDesignatorList() {
        return OptionalDesignatorList;
    }

    public void setOptionalDesignatorList(OptionalDesignatorList OptionalDesignatorList) {
        this.OptionalDesignatorList=OptionalDesignatorList;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignatorList!=null) OptionalDesignatorList.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignatorList!=null) OptionalDesignatorList.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignatorList!=null) OptionalDesignatorList.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorWithSquare(\n");

        if(OptionalDesignatorList!=null)
            buffer.append(OptionalDesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorWithSquare]");
        return buffer.toString();
    }
}
