// generated with ast extension for cup
// version 0.8
// 27/11/2022 2:4:33


package rs.ac.bg.etf.pp1.ast;

public class OtherConstDeclarations_ extends OtherConstDeclarations {

    private OtherConstDeclarations OtherConstDeclarations;
    private String identName;
    private Constant Constant;

    public OtherConstDeclarations_ (OtherConstDeclarations OtherConstDeclarations, String identName, Constant Constant) {
        this.OtherConstDeclarations=OtherConstDeclarations;
        if(OtherConstDeclarations!=null) OtherConstDeclarations.setParent(this);
        this.identName=identName;
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
    }

    public OtherConstDeclarations getOtherConstDeclarations() {
        return OtherConstDeclarations;
    }

    public void setOtherConstDeclarations(OtherConstDeclarations OtherConstDeclarations) {
        this.OtherConstDeclarations=OtherConstDeclarations;
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
    }

    public Constant getConstant() {
        return Constant;
    }

    public void setConstant(Constant Constant) {
        this.Constant=Constant;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OtherConstDeclarations!=null) OtherConstDeclarations.accept(visitor);
        if(Constant!=null) Constant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OtherConstDeclarations!=null) OtherConstDeclarations.traverseTopDown(visitor);
        if(Constant!=null) Constant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OtherConstDeclarations!=null) OtherConstDeclarations.traverseBottomUp(visitor);
        if(Constant!=null) Constant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OtherConstDeclarations_(\n");

        if(OtherConstDeclarations!=null)
            buffer.append(OtherConstDeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        if(Constant!=null)
            buffer.append(Constant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OtherConstDeclarations_]");
        return buffer.toString();
    }
}
