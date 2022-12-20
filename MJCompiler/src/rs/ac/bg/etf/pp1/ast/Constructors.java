// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class Constructors extends ClassMethodConstructorLists {

    private ConstructorDecl_Start ConstructorDecl_Start;
    private ConstructorDeclList ConstructorDeclList;

    public Constructors (ConstructorDecl_Start ConstructorDecl_Start, ConstructorDeclList ConstructorDeclList) {
        this.ConstructorDecl_Start=ConstructorDecl_Start;
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.setParent(this);
        this.ConstructorDeclList=ConstructorDeclList;
        if(ConstructorDeclList!=null) ConstructorDeclList.setParent(this);
    }

    public ConstructorDecl_Start getConstructorDecl_Start() {
        return ConstructorDecl_Start;
    }

    public void setConstructorDecl_Start(ConstructorDecl_Start ConstructorDecl_Start) {
        this.ConstructorDecl_Start=ConstructorDecl_Start;
    }

    public ConstructorDeclList getConstructorDeclList() {
        return ConstructorDeclList;
    }

    public void setConstructorDeclList(ConstructorDeclList ConstructorDeclList) {
        this.ConstructorDeclList=ConstructorDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.accept(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseTopDown(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseBottomUp(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constructors(\n");

        if(ConstructorDecl_Start!=null)
            buffer.append(ConstructorDecl_Start.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDeclList!=null)
            buffer.append(ConstructorDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constructors]");
        return buffer.toString();
    }
}
