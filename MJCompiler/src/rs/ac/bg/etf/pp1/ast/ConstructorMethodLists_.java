// generated with ast extension for cup
// version 0.8
// 28/11/2022 15:59:56


package rs.ac.bg.etf.pp1.ast;

public class ConstructorMethodLists_ extends ConstructorMethodLists {

    private ClassMethodConstructorLists ClassMethodConstructorLists;

    public ConstructorMethodLists_ (ClassMethodConstructorLists ClassMethodConstructorLists) {
        this.ClassMethodConstructorLists=ClassMethodConstructorLists;
        if(ClassMethodConstructorLists!=null) ClassMethodConstructorLists.setParent(this);
    }

    public ClassMethodConstructorLists getClassMethodConstructorLists() {
        return ClassMethodConstructorLists;
    }

    public void setClassMethodConstructorLists(ClassMethodConstructorLists ClassMethodConstructorLists) {
        this.ClassMethodConstructorLists=ClassMethodConstructorLists;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMethodConstructorLists!=null) ClassMethodConstructorLists.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMethodConstructorLists!=null) ClassMethodConstructorLists.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMethodConstructorLists!=null) ClassMethodConstructorLists.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorMethodLists_(\n");

        if(ClassMethodConstructorLists!=null)
            buffer.append(ClassMethodConstructorLists.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorMethodLists_]");
        return buffer.toString();
    }
}
