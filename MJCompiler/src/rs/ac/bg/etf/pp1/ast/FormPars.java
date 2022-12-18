// generated with ast extension for cup
// version 0.8
// 18/11/2022 2:36:12


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private OneFormPar OneFormPar;
    private TypeIdentList TypeIdentList;

    public FormPars (OneFormPar OneFormPar, TypeIdentList TypeIdentList) {
        this.OneFormPar=OneFormPar;
        if(OneFormPar!=null) OneFormPar.setParent(this);
        this.TypeIdentList=TypeIdentList;
        if(TypeIdentList!=null) TypeIdentList.setParent(this);
    }

    public OneFormPar getOneFormPar() {
        return OneFormPar;
    }

    public void setOneFormPar(OneFormPar OneFormPar) {
        this.OneFormPar=OneFormPar;
    }

    public TypeIdentList getTypeIdentList() {
        return TypeIdentList;
    }

    public void setTypeIdentList(TypeIdentList TypeIdentList) {
        this.TypeIdentList=TypeIdentList;
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
        if(OneFormPar!=null) OneFormPar.accept(visitor);
        if(TypeIdentList!=null) TypeIdentList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneFormPar!=null) OneFormPar.traverseTopDown(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneFormPar!=null) OneFormPar.traverseBottomUp(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(OneFormPar!=null)
            buffer.append(OneFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TypeIdentList!=null)
            buffer.append(TypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}
