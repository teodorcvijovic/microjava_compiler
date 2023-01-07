// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class M_IfElse extends Matched {

    private OpenIfElseScope OpenIfElseScope;
    private ConditionIf ConditionIf;
    private PatchOrConditionJumps PatchOrConditionJumps;
    private Matched Matched;
    private ExitIfBlock_PatchJumpsToElse ExitIfBlock_PatchJumpsToElse;
    private Matched Matched1;

    public M_IfElse (OpenIfElseScope OpenIfElseScope, ConditionIf ConditionIf, PatchOrConditionJumps PatchOrConditionJumps, Matched Matched, ExitIfBlock_PatchJumpsToElse ExitIfBlock_PatchJumpsToElse, Matched Matched1) {
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
        this.Matched1=Matched1;
        if(Matched1!=null) Matched1.setParent(this);
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

    public Matched getMatched1() {
        return Matched1;
    }

    public void setMatched1(Matched Matched1) {
        this.Matched1=Matched1;
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
        if(Matched1!=null) Matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseTopDown(visitor);
        if(ConditionIf!=null) ConditionIf.traverseTopDown(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.traverseTopDown(visitor);
        if(Matched1!=null) Matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OpenIfElseScope!=null) OpenIfElseScope.traverseBottomUp(visitor);
        if(ConditionIf!=null) ConditionIf.traverseBottomUp(visitor);
        if(PatchOrConditionJumps!=null) PatchOrConditionJumps.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ExitIfBlock_PatchJumpsToElse!=null) ExitIfBlock_PatchJumpsToElse.traverseBottomUp(visitor);
        if(Matched1!=null) Matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("M_IfElse(\n");

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

        if(Matched1!=null)
            buffer.append(Matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [M_IfElse]");
        return buffer.toString();
    }
}
