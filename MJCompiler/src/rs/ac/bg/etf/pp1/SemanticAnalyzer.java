package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	
	/****************** error handling ********************/
	
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0) msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
	
	/******************** helper fields ********************/
	
	private static int programVarCount = 0;
	private static boolean mainMethodDefined = false;
	
	/********************** Program ************************/
	
	public void visit(ProgramName programName){
    	programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program_ program){
    	programVarCount = Tab.currentScope.getnVars();
    	
    	if (!mainMethodDefined) {
    		report_error("Greska: Metoda void main() nije definisana.", null);
    	}
    	
    	// uvezujemo sve u ProgramName objektni cvor
    	Tab.chainLocalSymbols(program.getProgramName().obj);
    	Tab.closeScope();
    }
    
    

}
