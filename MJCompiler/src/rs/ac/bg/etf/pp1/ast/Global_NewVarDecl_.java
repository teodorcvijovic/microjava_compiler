// generated with ast extension for cup
// version 0.8
// 27/11/2022 13:38:2


package rs.ac.bg.etf.pp1.ast;

public class Global_NewVarDecl_ extends Global_NewVarDecl {

    private String varName;
    private OptionalSquare OptionalSquare;

    public Global_NewVarDecl_ (String varName, OptionalSquare OptionalSquare) {
        this.varName=varName;
        this.OptionalSquare=OptionalSquare;
        if(OptionalSquare!=null) OptionalSquare.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
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
        if(OptionalSquare!=null) OptionalSquare.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalSquare!=null) OptionalSquare.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalSquare!=null) OptionalSquare.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Global_NewVarDecl_(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptionalSquare!=null)
            buffer.append(OptionalSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Global_NewVarDecl_]");
        return buffer.toString();
    }
}
