package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class StructExtension {

	// var_dst = var_src
	// var_class = var_subclass_derived_from_class
	public static boolean assignableTo(Struct src, Struct dst) {
		String dstName = SemanticAnalyzer.map_ClassStructToName.get(dst);
		
//		if(src.assignableTo(dst)) return true;

		// bottom up check if src is derived from dst
		if(src.getKind() == Struct.Class && dst.getKind() == Struct.Class) {
			for (Struct curr = src; curr != null; curr = curr.getElemType()) {
				if (curr == Tab.noType) break;
				
				String currName = SemanticAnalyzer.map_ClassStructToName.get(curr);
				
				if (currName.equals(dstName)) return true;
			}
		}
		else {
			if(src.assignableTo(dst)) return true;
		}
		
		return false;
	}
	
}
