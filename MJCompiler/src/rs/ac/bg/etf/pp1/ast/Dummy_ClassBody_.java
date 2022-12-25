// generated with ast extension for cup
// version 0.8
// 25/11/2022 2:21:17


package rs.ac.bg.etf.pp1.ast;

public class Dummy_ClassBody_ extends Dummy_ClassBody {

    public Dummy_ClassBody_ () {
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
        buffer.append("Dummy_ClassBody_(\n");

        buffer.append(tab);
        buffer.append(") [Dummy_ClassBody_]");
        return buffer.toString();
    }
}
