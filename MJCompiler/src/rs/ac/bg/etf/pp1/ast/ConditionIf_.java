// generated with ast extension for cup
// version 0.8
// 25/11/2022 17:11:48


package rs.ac.bg.etf.pp1.ast;

public class ConditionIf_ extends ConditionIf {

    private CondTerm CondTerm;
    private Or_CondTermList Or_CondTermList;

    public ConditionIf_ (CondTerm CondTerm, Or_CondTermList Or_CondTermList) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.Or_CondTermList=Or_CondTermList;
        if(Or_CondTermList!=null) Or_CondTermList.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public Or_CondTermList getOr_CondTermList() {
        return Or_CondTermList;
    }

    public void setOr_CondTermList(Or_CondTermList Or_CondTermList) {
        this.Or_CondTermList=Or_CondTermList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(Or_CondTermList!=null) Or_CondTermList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(Or_CondTermList!=null) Or_CondTermList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(Or_CondTermList!=null) Or_CondTermList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionIf_(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Or_CondTermList!=null)
            buffer.append(Or_CondTermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionIf_]");
        return buffer.toString();
    }
}
