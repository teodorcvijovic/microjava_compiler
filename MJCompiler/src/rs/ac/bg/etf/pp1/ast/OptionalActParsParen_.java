// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class OptionalActParsParen_ extends OptionalActParsParen {

    private ParenWithOptionalActPars ParenWithOptionalActPars;

    public OptionalActParsParen_ (ParenWithOptionalActPars ParenWithOptionalActPars) {
        this.ParenWithOptionalActPars=ParenWithOptionalActPars;
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.setParent(this);
    }

    public ParenWithOptionalActPars getParenWithOptionalActPars() {
        return ParenWithOptionalActPars;
    }

    public void setParenWithOptionalActPars(ParenWithOptionalActPars ParenWithOptionalActPars) {
        this.ParenWithOptionalActPars=ParenWithOptionalActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ParenWithOptionalActPars!=null) ParenWithOptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalActParsParen_(\n");

        if(ParenWithOptionalActPars!=null)
            buffer.append(ParenWithOptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalActParsParen_]");
        return buffer.toString();
    }
}
