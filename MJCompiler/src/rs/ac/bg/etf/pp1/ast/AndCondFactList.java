// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class AndCondFactList extends And_CondFactList {

    private And_CondFactList And_CondFactList;
    private CondFact CondFact;

    public AndCondFactList (And_CondFactList And_CondFactList, CondFact CondFact) {
        this.And_CondFactList=And_CondFactList;
        if(And_CondFactList!=null) And_CondFactList.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public And_CondFactList getAnd_CondFactList() {
        return And_CondFactList;
    }

    public void setAnd_CondFactList(And_CondFactList And_CondFactList) {
        this.And_CondFactList=And_CondFactList;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(And_CondFactList!=null) And_CondFactList.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(And_CondFactList!=null) And_CondFactList.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(And_CondFactList!=null) And_CondFactList.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AndCondFactList(\n");

        if(And_CondFactList!=null)
            buffer.append(And_CondFactList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AndCondFactList]");
        return buffer.toString();
    }
}
