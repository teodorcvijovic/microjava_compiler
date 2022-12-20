// generated with ast extension for cup
// version 0.8
// 20/11/2022 1:59:16


package rs.ac.bg.etf.pp1.ast;

public class M_While extends Matched {

    private WhileLoopStart WhileLoopStart;
    private ConditionWhile ConditionWhile;
    private Statement Statement;

    public M_While (WhileLoopStart WhileLoopStart, ConditionWhile ConditionWhile, Statement Statement) {
        this.WhileLoopStart=WhileLoopStart;
        if(WhileLoopStart!=null) WhileLoopStart.setParent(this);
        this.ConditionWhile=ConditionWhile;
        if(ConditionWhile!=null) ConditionWhile.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public WhileLoopStart getWhileLoopStart() {
        return WhileLoopStart;
    }

    public void setWhileLoopStart(WhileLoopStart WhileLoopStart) {
        this.WhileLoopStart=WhileLoopStart;
    }

    public ConditionWhile getConditionWhile() {
        return ConditionWhile;
    }

    public void setConditionWhile(ConditionWhile ConditionWhile) {
        this.ConditionWhile=ConditionWhile;
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
        if(WhileLoopStart!=null) WhileLoopStart.accept(visitor);
        if(ConditionWhile!=null) ConditionWhile.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileLoopStart!=null) WhileLoopStart.traverseTopDown(visitor);
        if(ConditionWhile!=null) ConditionWhile.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileLoopStart!=null) WhileLoopStart.traverseBottomUp(visitor);
        if(ConditionWhile!=null) ConditionWhile.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("M_While(\n");

        if(WhileLoopStart!=null)
            buffer.append(WhileLoopStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionWhile!=null)
            buffer.append(ConditionWhile.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [M_While]");
        return buffer.toString();
    }
}
