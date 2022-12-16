// generated with ast extension for cup
// version 0.8
// 17/11/2022 0:24:58


package rs.ac.bg.etf.pp1.ast;

public class OrCondTermList extends Or_CondTermList {

    private Or_CondTermList Or_CondTermList;
    private CondTerm CondTerm;

    public OrCondTermList (Or_CondTermList Or_CondTermList, CondTerm CondTerm) {
        this.Or_CondTermList=Or_CondTermList;
        if(Or_CondTermList!=null) Or_CondTermList.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public Or_CondTermList getOr_CondTermList() {
        return Or_CondTermList;
    }

    public void setOr_CondTermList(Or_CondTermList Or_CondTermList) {
        this.Or_CondTermList=Or_CondTermList;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Or_CondTermList!=null) Or_CondTermList.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Or_CondTermList!=null) Or_CondTermList.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Or_CondTermList!=null) Or_CondTermList.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrCondTermList(\n");

        if(Or_CondTermList!=null)
            buffer.append(Or_CondTermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrCondTermList]");
        return buffer.toString();
    }
}
