// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class U_If extends Unmatched {

    private OpenIfElseScope OpenIfElseScope;
    private ConditionIf ConditionIf;
    private PatchOrConditionJumps PatchOrConditionJumps;
    private Statement Statement;

    public U_If (OpenIfElseScope OpenIfElseScope, ConditionIf ConditionIf, PatchOrConditionJumps PatchOrConditionJumps, Statement Statement) {
        this.OpenIfElseScope=OpenIfElseScope;
        if(OpenIfElseScope!=null) OpenIfElseScope.setParent(this);
        this.ConditionIf=ConditionIf;
        if(ConditionIf!=null) ConditionIf.setParent(this);
        this.PatchOrConditionJumps=PatchOrConditionJumps;
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OpenIfElseScope getOpenIfElseScope() {
        return OpenIfElseScope;
    }

    public void setOpenIfElseScope(OpenIfElseScope OpenIfElseScope) {
        this.OpenIfElseScope=OpenIfElseScope;
    }

    public ConditionIf getConditionIf() {
        return ConditionIf;
    }

    public void setConditionIf(ConditionIf ConditionIf) {
        this.ConditionIf=ConditionIf;
    }

    public PatchOrConditionJumps getPatchOrConditionJumps() {
        return PatchOrConditionJumps;
    }

    public void setPatchOrConditionJumps(PatchOrConditionJumps PatchOrConditionJumps) {
        this.PatchOrConditionJumps=PatchOrConditionJumps;
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
        if(OpenIfElseScope!=null) OpenIfElseScope.accept(visitor);
        if(ConditionIf!=null) ConditionIf.accept(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseTopDown(visitor);
        if(ConditionIf!=null) ConditionIf.traverseTopDown(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseBottomUp(visitor);
        if(ConditionIf!=null) ConditionIf.traverseBottomUp(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("U_If(\n");

        if(OpenIfElseScope!=null)
            buffer.append(OpenIfElseScope.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionIf!=null)
            buffer.append(ConditionIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PatchOrConditionJumps!=null)
            buffer.append(PatchOrConditionJumps.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [U_If]");
        return buffer.toString();
    }
}
