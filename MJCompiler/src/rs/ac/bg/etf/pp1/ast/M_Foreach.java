// generated with ast extension for cup
// version 0.8
// 24/11/2022 20:29:47


package rs.ac.bg.etf.pp1.ast;

public class M_Foreach extends Matched {

    private DesignatorForeach DesignatorForeach;
    private CurrVarDesignator CurrVarDesignator;
    private Statement Statement;

    public M_Foreach (DesignatorForeach DesignatorForeach, CurrVarDesignator CurrVarDesignator, Statement Statement) {
        this.DesignatorForeach=DesignatorForeach;
        if(DesignatorForeach!=null) DesignatorForeach.setParent(this);
        this.CurrVarDesignator=CurrVarDesignator;
        if(CurrVarDesignator!=null) CurrVarDesignator.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorForeach getDesignatorForeach() {
        return DesignatorForeach;
    }

    public void setDesignatorForeach(DesignatorForeach DesignatorForeach) {
        this.DesignatorForeach=DesignatorForeach;
    }

    public CurrVarDesignator getCurrVarDesignator() {
        return CurrVarDesignator;
    }

    public void setCurrVarDesignator(CurrVarDesignator CurrVarDesignator) {
        this.CurrVarDesignator=CurrVarDesignator;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorForeach!=null) DesignatorForeach.accept(visitor);
        if(CurrVarDesignator!=null) CurrVarDesignator.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorForeach!=null) DesignatorForeach.traverseTopDown(visitor);
        if(CurrVarDesignator!=null) CurrVarDesignator.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorForeach!=null) DesignatorForeach.traverseBottomUp(visitor);
        if(CurrVarDesignator!=null) CurrVarDesignator.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("M_Foreach(\n");

        if(DesignatorForeach!=null)
            buffer.append(DesignatorForeach.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CurrVarDesignator!=null)
            buffer.append(CurrVarDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [M_Foreach]");
        return buffer.toString();
    }
}
