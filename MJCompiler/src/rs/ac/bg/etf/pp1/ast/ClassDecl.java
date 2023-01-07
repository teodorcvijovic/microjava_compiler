// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:20


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassNameAndDerivation ClassNameAndDerivation;
    private ClassBody ClassBody;

    public ClassDecl (ClassNameAndDerivation ClassNameAndDerivation, ClassBody ClassBody) {
        this.ClassNameAndDerivation=ClassNameAndDerivation;
        if(ClassNameAndDerivation!=null) ClassNameAndDerivation.setParent(this);
        this.ClassBody=ClassBody;
        if(ClassBody!=null) ClassBody.setParent(this);
    }

    public ClassNameAndDerivation getClassNameAndDerivation() {
        return ClassNameAndDerivation;
    }

    public void setClassNameAndDerivation(ClassNameAndDerivation ClassNameAndDerivation) {
        this.ClassNameAndDerivation=ClassNameAndDerivation;
    }

    public ClassBody getClassBody() {
        return ClassBody;
    }

    public void setClassBody(ClassBody ClassBody) {
        this.ClassBody=ClassBody;
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
        if(ClassNameAndDerivation!=null) ClassNameAndDerivation.accept(visitor);
        if(ClassBody!=null) ClassBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassNameAndDerivation!=null) ClassNameAndDerivation.traverseTopDown(visitor);
        if(ClassBody!=null) ClassBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassNameAndDerivation!=null) ClassNameAndDerivation.traverseBottomUp(visitor);
        if(ClassBody!=null) ClassBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassNameAndDerivation!=null)
            buffer.append(ClassNameAndDerivation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassBody!=null)
            buffer.append(ClassBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
