// generated with ast extension for cup
// version 0.8
// 20/11/2022 18:4:58


package rs.ac.bg.etf.pp1.ast;

public class PrintNumConst_ extends PrintNumConst {

    private Integer N1;

    public PrintNumConst_ (Integer N1) {
        this.N1=N1;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintNumConst_(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintNumConst_]");
        return buffer.toString();
    }
}
