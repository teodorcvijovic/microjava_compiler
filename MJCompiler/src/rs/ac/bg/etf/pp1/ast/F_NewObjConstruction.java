// generated with ast extension for cup
// version 0.8
// 27/11/2022 13:38:2


package rs.ac.bg.etf.pp1.ast;

public class F_NewObjConstruction extends Factor {

    private CalledConstructorName CalledConstructorName;
    private OptionalActPars OptionalActPars;

    public F_NewObjConstruction (CalledConstructorName CalledConstructorName, OptionalActPars OptionalActPars) {
        this.CalledConstructorName=CalledConstructorName;
        if(CalledConstructorName!=null) CalledConstructorName.setParent(this);
        this.OptionalActPars=OptionalActPars;
        if(OptionalActPars!=null) OptionalActPars.setParent(this);
    }

    public CalledConstructorName getCalledConstructorName() {
        return CalledConstructorName;
    }

    public void setCalledConstructorName(CalledConstructorName CalledConstructorName) {
        this.CalledConstructorName=CalledConstructorName;
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
        if(CalledConstructorName!=null) CalledConstructorName.accept(visitor);
        if(OptionalActPars!=null) OptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CalledConstructorName!=null) CalledConstructorName.traverseTopDown(visitor);
        if(OptionalActPars!=null) OptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CalledConstructorName!=null) CalledConstructorName.traverseBottomUp(visitor);
        if(OptionalActPars!=null) OptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("F_NewObjConstruction(\n");

        if(CalledConstructorName!=null)
            buffer.append(CalledConstructorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalActPars!=null)
            buffer.append(OptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_NewObjConstruction]");
        return buffer.toString();
    }
}
