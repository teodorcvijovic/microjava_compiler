// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class M_Foreach extends Matched {

    private DesignatorForeach DesignatorForeach;
    private String I2;
    private Statement Statement;

    public M_Foreach (DesignatorForeach DesignatorForeach, String I2, Statement Statement) {
        this.DesignatorForeach=DesignatorForeach;
        if(DesignatorForeach!=null) DesignatorForeach.setParent(this);
        this.I2=I2;
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorForeach getDesignatorForeach() {
        return DesignatorForeach;
    }

    public void setDesignatorForeach(DesignatorForeach DesignatorForeach) {
        this.DesignatorForeach=DesignatorForeach;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorForeach!=null) DesignatorForeach.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorForeach!=null) DesignatorForeach.traverseBottomUp(visitor);
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

        buffer.append(" "+tab+I2);
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
