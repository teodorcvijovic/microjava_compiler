// generated with ast extension for cup
// version 0.8
// 27/11/2022 16:35:24


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String firstIdentName;
    private Constant Constant;
    private OtherConstDeclarations OtherConstDeclarations;

    public ConstDecl (Type Type, String firstIdentName, Constant Constant, OtherConstDeclarations OtherConstDeclarations) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.firstIdentName=firstIdentName;
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
        this.OtherConstDeclarations=OtherConstDeclarations;
        if(OtherConstDeclarations!=null) OtherConstDeclarations.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFirstIdentName() {
        return firstIdentName;
    }

    public void setFirstIdentName(String firstIdentName) {
        this.firstIdentName=firstIdentName;
    }

    public Constant getConstant() {
        return Constant;
    }

    public void setConstant(Constant Constant) {
        this.Constant=Constant;
    }

    public OtherConstDeclarations getOtherConstDeclarations() {
        return OtherConstDeclarations;
    }

    public void setOtherConstDeclarations(OtherConstDeclarations OtherConstDeclarations) {
        this.OtherConstDeclarations=OtherConstDeclarations;
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
        if(Type!=null) Type.accept(visitor);
        if(Constant!=null) Constant.accept(visitor);
        if(OtherConstDeclarations!=null) OtherConstDeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Constant!=null) Constant.traverseTopDown(visitor);
        if(OtherConstDeclarations!=null) OtherConstDeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Constant!=null) Constant.traverseBottomUp(visitor);
        if(OtherConstDeclarations!=null) OtherConstDeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+firstIdentName);
        buffer.append("\n");

        if(Constant!=null)
            buffer.append(Constant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OtherConstDeclarations!=null)
            buffer.append(OtherConstDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
