import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
	Map<String,String> smtable;
	SymbolTable(){
		smtable=new HashMap<String,String>();
		smtable.put("R0", "0");
		smtable.put("R1", "1");
		smtable.put("R2", "2");
		smtable.put("R3", "3");
		smtable.put("R4", "4");
		smtable.put("R5", "5");
		smtable.put("R6", "6");
		smtable.put("R7", "7");
		smtable.put("R8", "8");
		smtable.put("R9", "9");
		smtable.put("R10", "10");
		smtable.put("R11", "11");
		smtable.put("R12", "12");
		smtable.put("R13", "13");
		smtable.put("R14", "14");
		smtable.put("R15", "15");
		smtable.put("SCREEN", "16384");
		smtable.put("KBD", "24576");
		smtable.put("SP", "0");
		smtable.put("LCL", "1");
		smtable.put("ARG", "2");
		smtable.put("THIS", "3");
		smtable.put("THAT", "4");
		
	}
	
	public void addEntry(String symbol,int address){
		smtable.put(symbol, ""+address);
	}
	
	public boolean contains(String symbol){
		return smtable.keySet().contains(symbol);
	}
	
	public String getAddress(String symbol){
		return smtable.get(symbol);
	}
	
}
