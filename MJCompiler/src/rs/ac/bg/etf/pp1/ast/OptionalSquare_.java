// generated with ast extension for cup
// version 0.8
// 18/11/2022 22:7:1


package rs.ac.bg.etf.pp1.ast;

public class OptionalSquare_ extends OptionalSquare {

    public OptionalSquare_ () {
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
        buffer.append("OptionalSquare_(\n");

        buffer.append(tab);
        buffer.append(") [OptionalSquare_]");
        return buffer.toString();
    }
}
