package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class TabExtension {
	
	public static final Struct boolType = new Struct(Struct.Bool);
	
	public static void init() {
		Tab.init();
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
}
