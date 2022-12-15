// generated with ast extension for cup
// version 0.8
// 15/11/2022 23:52:10


package rs.ac.bg.etf.pp1.ast;

public class Program_ extends Program {

    private String I1;
    private GlobalDeclList GlobalDeclList;
    private GlobalMethodDeclList GlobalMethodDeclList;

    public Program_ (String I1, GlobalDeclList GlobalDeclList, GlobalMethodDeclList GlobalMethodDeclList) {
        this.I1=I1;
        this.GlobalDeclList=GlobalDeclList;
        if(GlobalDeclList!=null) GlobalDeclList.setParent(this);
        this.GlobalMethodDeclList=GlobalMethodDeclList;
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public GlobalDeclList getGlobalDeclList() {
        return GlobalDeclList;
    }

    public void setGlobalDeclList(GlobalDeclList GlobalDeclList) {
        this.GlobalDeclList=GlobalDeclList;
    }

    public GlobalMethodDeclList getGlobalMethodDeclList() {
        return GlobalMethodDeclList;
    }

    public void setGlobalMethodDeclList(GlobalMethodDeclList GlobalMethodDeclList) {
        this.GlobalMethodDeclList=GlobalMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalDeclList!=null) GlobalDeclList.accept(visitor);
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseTopDown(visitor);
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalDeclList!=null) GlobalDeclList.traverseBottomUp(visitor);
        if(GlobalMethodDeclList!=null) GlobalMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(GlobalDeclList!=null)
            buffer.append(GlobalDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalMethodDeclList!=null)
            buffer.append(GlobalMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_]");
        return buffer.toString();
    }
}
