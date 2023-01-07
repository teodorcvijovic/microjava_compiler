// generated with ast extension for cup
// version 0.8
// 4/0/2023 23:19:21


package rs.ac.bg.etf.pp1.ast;

public class TypeIdentList_ extends TypeIdentList {

    private TypeIdentList TypeIdentList;
    private OneFormPar OneFormPar;

    public TypeIdentList_ (TypeIdentList TypeIdentList, OneFormPar OneFormPar) {
        this.TypeIdentList=TypeIdentList;
        if(TypeIdentList!=null) TypeIdentList.setParent(this);
        this.OneFormPar=OneFormPar;
        if(OneFormPar!=null) OneFormPar.setParent(this);
    }

    public TypeIdentList getTypeIdentList() {
        return TypeIdentList;
    }

    public void setTypeIdentList(TypeIdentList TypeIdentList) {
        this.TypeIdentList=TypeIdentList;
    }

    public OneFormPar getOneFormPar() {
        return OneFormPar;
    }

    public void setOneFormPar(OneFormPar OneFormPar) {
        this.OneFormPar=OneFormPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeIdentList!=null) TypeIdentList.accept(visitor);
        if(OneFormPar!=null) OneFormPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeIdentList!=null) TypeIdentList.traverseTopDown(visitor);
        if(OneFormPar!=null) OneFormPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeIdentList!=null) TypeIdentList.traverseBottomUp(visitor);
        if(OneFormPar!=null) OneFormPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeIdentList_(\n");

        if(TypeIdentList!=null)
            buffer.append(TypeIdentList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneFormPar!=null)
            buffer.append(OneFormPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeIdentList_]");
        return buffer.toString();
    }
}
