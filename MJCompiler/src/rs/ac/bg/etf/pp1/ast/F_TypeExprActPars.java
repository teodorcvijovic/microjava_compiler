// generated with ast extension for cup
// version 0.8
// 18/11/2022 2:36:12


package rs.ac.bg.etf.pp1.ast;

public class F_TypeExprActPars extends Factor {

    private Type Type;
    private ExprActPars ExprActPars;

    public F_TypeExprActPars (Type Type, ExprActPars ExprActPars) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ExprActPars=ExprActPars;
        if(ExprActPars!=null) ExprActPars.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ExprActPars getExprActPars() {
        return ExprActPars;
    }

    public void setExprActPars(ExprActPars ExprActPars) {
        this.ExprActPars=ExprActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ExprActPars!=null) ExprActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ExprActPars!=null) ExprActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ExprActPars!=null) ExprActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("F_TypeExprActPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprActPars!=null)
            buffer.append(ExprActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [F_TypeExprActPars]");
        return buffer.toString();
    }
}
