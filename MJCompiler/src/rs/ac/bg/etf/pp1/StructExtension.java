package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Struct;

public class StructExtension {

	// var_dst = var_src
	// var_class = var_subclass_derived_from_class
	public static boolean assignableTo(Struct src, Struct dst) {
		if(src.assignableTo(dst)) return true;

		// bottom up check if dst is derived from src
		if(src.getKind() == Struct.Class && dst.getKind() == Struct.Class)
			for (Struct curr = src; curr != null; curr = curr.getElemType())
				if (curr.equals(dst))
					return true;
		
		return false;
	}
	
}
