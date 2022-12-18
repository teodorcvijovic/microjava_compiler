// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class ConstructorDeclList_ extends ConstructorDeclList {

    private ConstructorDeclList ConstructorDeclList;
    private ConstructorDecl_Start ConstructorDecl_Start;
    private ConstructorDecl ConstructorDecl;

    public ConstructorDeclList_ (ConstructorDeclList ConstructorDeclList, ConstructorDecl_Start ConstructorDecl_Start, ConstructorDecl ConstructorDecl) {
        this.ConstructorDeclList=ConstructorDeclList;
        if(ConstructorDeclList!=null) ConstructorDeclList.setParent(this);
        this.ConstructorDecl_Start=ConstructorDecl_Start;
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
    }

    public ConstructorDeclList getConstructorDeclList() {
        return ConstructorDeclList;
    }

    public void setConstructorDeclList(ConstructorDeclList ConstructorDeclList) {
        this.ConstructorDeclList=ConstructorDeclList;
    }

    public ConstructorDecl_Start getConstructorDecl_Start() {
        return ConstructorDecl_Start;
    }

    public void setConstructorDecl_Start(ConstructorDecl_Start ConstructorDecl_Start) {
        this.ConstructorDecl_Start=ConstructorDecl_Start;
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclList!=null) ConstructorDeclList.accept(visitor);
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseTopDown(visitor);
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclList!=null) ConstructorDeclList.traverseBottomUp(visitor);
        if(ConstructorDecl_Start!=null) ConstructorDecl_Start.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorDeclList_(\n");

        if(ConstructorDeclList!=null)
            buffer.append(ConstructorDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl_Start!=null)
            buffer.append(ConstructorDecl_Start.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorDeclList_]");
        return buffer.toString();
    }
}
