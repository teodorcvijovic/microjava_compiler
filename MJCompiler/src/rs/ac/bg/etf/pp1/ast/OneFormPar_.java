// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class OneFormPar_ extends OneFormPar {

    private Type Type;
    private String formParName;
    private OptionalSquare OptionalSquare;

    public OneFormPar_ (Type Type, String formParName, OptionalSquare OptionalSquare) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formParName=formParName;
        this.OptionalSquare=OptionalSquare;
        if(OptionalSquare!=null) OptionalSquare.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormParName() {
        return formParName;
    }

    public void setFormParName(String formParName) {
        this.formParName=formParName;
    }

    public OptionalSquare getOptionalSquare() {
        return OptionalSquare;
    }

    public void setOptionalSquare(OptionalSquare OptionalSquare) {
        this.OptionalSquare=OptionalSquare;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(OptionalSquare!=null) OptionalSquare.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptionalSquare!=null) OptionalSquare.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptionalSquare!=null) OptionalSquare.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneFormPar_(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formParName);
        buffer.append("\n");

        if(OptionalSquare!=null)
            buffer.append(OptionalSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneFormPar_]");
        return buffer.toString();
    }
}
