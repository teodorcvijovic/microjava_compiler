// generated with ast extension for cup
// version 0.8
// 28/11/2022 15:59:56


package rs.ac.bg.etf.pp1.ast;

public class U_IfElse extends Unmatched {

    private OpenIfElseScope OpenIfElseScope;
    private ConditionIf ConditionIf;
    private PatchOrConditionJumps PatchOrConditionJumps;
    private Matched Matched;
    private ExitIfBlock_PatchJumpsToElse ExitIfBlock_PatchJumpsToElse;
    private Unmatched Unmatched;

    public U_IfElse (OpenIfElseScope OpenIfElseScope, ConditionIf ConditionIf, PatchOrConditionJumps PatchOrConditionJumps, Matched Matched, ExitIfBlock_PatchJumpsToElse ExitIfBlock_PatchJumpsToElse, Unmatched Unmatched) {
        this.OpenIfElseScope=OpenIfElseScope;
        if(OpenIfElseScope!=null) OpenIfElseScope.setParent(this);
        this.ConditionIf=ConditionIf;
        if(ConditionIf!=null) ConditionIf.setParent(this);
        this.PatchOrConditionJumps=PatchOrConditionJumps;
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ExitIfBlock_PatchJumpsToElse=ExitIfBlock_PatchJumpsToElse;
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.setParent(this);
        this.Unmatched=Unmatched;
        if(Unmatched!=null) Unmatched.setParent(this);
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

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public ExitIfBlock_PatchJumpsToElse getExitIfBlock_PatchJumpsToElse() {
        return ExitIfBlock_PatchJumpsToElse;
    }

    public void setExitIfBlock_PatchJumpsToElse(ExitIfBlock_PatchJumpsToElse ExitIfBlock_PatchJumpsToElse) {
        this.ExitIfBlock_PatchJumpsToElse=ExitIfBlock_PatchJumpsToElse;
    }

    public Unmatched getUnmatched() {
        return Unmatched;
    }

    public void setUnmatched(Unmatched Unmatched) {
        this.Unmatched=Unmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OpenIfElseScope!=null) OpenIfElseScope.accept(visitor);
        if(ConditionIf!=null) ConditionIf.accept(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.accept(visitor);
        if(Unmatched!=null) Unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseTopDown(visitor);
        if(ConditionIf!=null) ConditionIf.traverseTopDown(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.traverseTopDown(visitor);
        if(Unmatched!=null) Unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseBottomUp(visitor);
        if(ConditionIf!=null) ConditionIf.traverseBottomUp(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.traverseBottomUp(visitor);
        if(Unmatched!=null) Unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("U_IfElse(\n");

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

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExitIfBlock_PatchJumpsToElse!=null)
            buffer.append(ExitIfBlock_PatchJumpsToElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Unmatched!=null)
            buffer.append(Unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [U_IfElse]");
        return buffer.toString();
    }
}
