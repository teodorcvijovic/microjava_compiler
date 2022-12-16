// generated with ast extension for cup
// version 0.8
// 17/11/2022 0:24:58


package rs.ac.bg.etf.pp1.ast;

public class Class_NewVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private OptionalSquare OptionalSquare;

    public Class_NewVarDecl (String I1, OptionalSquare OptionalSquare) {
        this.I1=I1;
        this.OptionalSquare=OptionalSquare;
        if(OptionalSquare!=null) OptionalSquare.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public OptionalSquare getOptionalSquare() {
        return OptionalSquare;
    }

    public void setOptionalSquare(OptionalSquare OptionalSquare) {
        this.OptionalSquare=OptionalSquare;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("Class_NewVarDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(OptionalSquare!=null)
            buffer.append(OptionalSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class_NewVarDecl]");
        return buffer.toString();
    }
}
