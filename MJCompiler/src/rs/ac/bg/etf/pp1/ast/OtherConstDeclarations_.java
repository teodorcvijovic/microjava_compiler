// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:10


package rs.ac.bg.etf.pp1.ast;

public class OtherConstDeclarations_ extends OtherConstDeclarations {

    private OtherConstDeclarations OtherConstDeclarations;
    private String I2;
    private Constant Constant;

    public OtherConstDeclarations_ (OtherConstDeclarations OtherConstDeclarations, String I2, Constant Constant) {
        this.OtherConstDeclarations=OtherConstDeclarations;
        if(OtherConstDeclarations!=null) OtherConstDeclarations.setParent(this);
        this.I2=I2;
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
    }

    public OtherConstDeclarations getOtherConstDeclarations() {
        return OtherConstDeclarations;
    }

    public void setOtherConstDeclarations(OtherConstDeclarations OtherConstDeclarations) {
        this.OtherConstDeclarations=OtherConstDeclarations;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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

        buffer.append(" "+tab+I2);
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
