// generated with ast extension for cup
// version 0.8
// 27/11/2022 16:35:24


package rs.ac.bg.etf.pp1.ast;

public class OptionalDesignatorListDerived1 extends OptionalDesignatorList {

    private OptionalDesignator OptionalDesignator;
    private DesignatorList DesignatorList;

    public OptionalDesignatorListDerived1 (OptionalDesignator OptionalDesignator, DesignatorList DesignatorList) {
        this.OptionalDesignator=OptionalDesignator;
        if(OptionalDesignator!=null) OptionalDesignator.setParent(this);
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
    }

    public OptionalDesignator getOptionalDesignator() {
        return OptionalDesignator;
    }

    public void setOptionalDesignator(OptionalDesignator OptionalDesignator) {
        this.OptionalDesignator=OptionalDesignator;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignator!=null) OptionalDesignator.accept(visitor);
        if(DesignatorList!=null) DesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignator!=null) OptionalDesignator.traverseTopDown(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignator!=null) OptionalDesignator.traverseBottomUp(visitor);
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalDesignatorListDerived1(\n");

        if(OptionalDesignator!=null)
            buffer.append(OptionalDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalDesignatorListDerived1]");
        return buffer.toString();
    }
}
