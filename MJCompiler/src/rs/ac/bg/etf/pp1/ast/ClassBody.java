// generated with ast extension for cup
// version 0.8
// 20/11/2022 14:13:48


package rs.ac.bg.etf.pp1.ast;

public class ClassBody implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Class_VarDeclList Class_VarDeclList;
    private Dummy_ClassBody Dummy_ClassBody;
    private ConstructorMethodLists ConstructorMethodLists;

    public ClassBody (Class_VarDeclList Class_VarDeclList, Dummy_ClassBody Dummy_ClassBody, ConstructorMethodLists ConstructorMethodLists) {
        this.Class_VarDeclList=Class_VarDeclList;
        if(Class_VarDeclList!=null) Class_VarDeclList.setParent(this);
        this.Dummy_ClassBody=Dummy_ClassBody;
        if(Dummy_ClassBody!=null) Dummy_ClassBody.setParent(this);
        this.ConstructorMethodLists=ConstructorMethodLists;
        if(ConstructorMethodLists!=null) ConstructorMethodLists.setParent(this);
    }

    public Class_VarDeclList getClass_VarDeclList() {
        return Class_VarDeclList;
    }

    public void setClass_VarDeclList(Class_VarDeclList Class_VarDeclList) {
        this.Class_VarDeclList=Class_VarDeclList;
    }

    public Dummy_ClassBody getDummy_ClassBody() {
        return Dummy_ClassBody;
    }

    public void setDummy_ClassBody(Dummy_ClassBody Dummy_ClassBody) {
        this.Dummy_ClassBody=Dummy_ClassBody;
    }

    public ConstructorMethodLists getConstructorMethodLists() {
        return ConstructorMethodLists;
    }

    public void setConstructorMethodLists(ConstructorMethodLists ConstructorMethodLists) {
        this.ConstructorMethodLists=ConstructorMethodLists;
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
        if(Class_VarDeclList!=null) Class_VarDeclList.accept(visitor);
        if(Dummy_ClassBody!=null) Dummy_ClassBody.accept(visitor);
        if(ConstructorMethodLists!=null) ConstructorMethodLists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Class_VarDeclList!=null) Class_VarDeclList.traverseTopDown(visitor);
        if(Dummy_ClassBody!=null) Dummy_ClassBody.traverseTopDown(visitor);
        if(ConstructorMethodLists!=null) ConstructorMethodLists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Class_VarDeclList!=null) Class_VarDeclList.traverseBottomUp(visitor);
        if(Dummy_ClassBody!=null) Dummy_ClassBody.traverseBottomUp(visitor);
        if(ConstructorMethodLists!=null) ConstructorMethodLists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassBody(\n");

        if(Class_VarDeclList!=null)
            buffer.append(Class_VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Dummy_ClassBody!=null)
            buffer.append(Dummy_ClassBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorMethodLists!=null)
            buffer.append(ConstructorMethodLists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassBody]");
        return buffer.toString();
    }
}
