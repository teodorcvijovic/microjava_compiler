// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class ClassNameAndDerivation implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String className;
    private OptionalDerivation OptionalDerivation;

    public ClassNameAndDerivation (String className, OptionalDerivation OptionalDerivation) {
        this.className=className;
        this.OptionalDerivation=OptionalDerivation;
        if(OptionalDerivation!=null) OptionalDerivation.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public OptionalDerivation getOptionalDerivation() {
        return OptionalDerivation;
    }

    public void setOptionalDerivation(OptionalDerivation OptionalDerivation) {
        this.OptionalDerivation=OptionalDerivation;
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
        if(OptionalDerivation!=null) OptionalDerivation.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDerivation!=null) OptionalDerivation.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDerivation!=null) OptionalDerivation.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassNameAndDerivation(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(OptionalDerivation!=null)
            buffer.append(OptionalDerivation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassNameAndDerivation]");
        return buffer.toString();
    }
}
