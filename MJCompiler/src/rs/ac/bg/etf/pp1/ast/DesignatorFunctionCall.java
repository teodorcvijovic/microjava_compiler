// generated with ast extension for cup
// version 0.8
// 16/0/2023 1:55:40


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFunctionCall extends DesignatorStatement {

    private CalledFunctionOrMethodDesignator CalledFunctionOrMethodDesignator;
    private OptionalActPars OptionalActPars;

    public DesignatorFunctionCall (CalledFunctionOrMethodDesignator CalledFunctionOrMethodDesignator, OptionalActPars OptionalActPars) {
        this.CalledFunctionOrMethodDesignator=CalledFunctionOrMethodDesignator;
        if(CalledFunctionOrMethodDesignator!=null) CalledFunctionOrMethodDesignator.setParent(this);
        this.OptionalActPars=OptionalActPars;
        if(OptionalActPars!=null) OptionalActPars.setParent(this);
    }

    public CalledFunctionOrMethodDesignator getCalledFunctionOrMethodDesignator() {
        return CalledFunctionOrMethodDesignator;
    }

    public void setCalledFunctionOrMethodDesignator(CalledFunctionOrMethodDesignator CalledFunctionOrMethodDesignator) {
        this.CalledFunctionOrMethodDesignator=CalledFunctionOrMethodDesignator;
    }

    public OptionalActPars getOptionalActPars() {
        return OptionalActPars;
    }

    public void setOptionalActPars(OptionalActPars OptionalActPars) {
        this.OptionalActPars=OptionalActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CalledFunctionOrMethodDesignator!=null) CalledFunctionOrMethodDesignator.accept(visitor);
        if(OptionalActPars!=null) OptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CalledFunctionOrMethodDesignator!=null) CalledFunctionOrMethodDesignator.traverseTopDown(visitor);
        if(OptionalActPars!=null) OptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CalledFunctionOrMethodDesignator!=null) CalledFunctionOrMethodDesignator.traverseBottomUp(visitor);
        if(OptionalActPars!=null) OptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFunctionCall(\n");

        if(CalledFunctionOrMethodDesignator!=null)
            buffer.append(CalledFunctionOrMethodDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalActPars!=null)
            buffer.append(OptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFunctionCall]");
        return buffer.toString();
    }
}
