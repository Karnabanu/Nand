import java.io.BufferedReader;
import java.io.IOException;


public class Parser {
	private BufferedReader source;
	private String line;
	private COMMAND cType;
	private String symbol,comp,dest,jump;
	public Parser(BufferedReader source) {
		super();
		this.source = source;
	}
	public boolean hasMoreCommands() throws IOException{
		
		if((line=source.readLine())!=null){
			int commentIndex=line.indexOf("//");
			if(commentIndex>=0)
			line=line.substring(0,commentIndex);
			
			if(line.equalsIgnoreCase(""))
				return hasMoreCommands();
			
			return true;
			
		}
		return false;
	}
	public void advance(){
		line=line.replaceAll("\\s+","");
		
		if(line.charAt(0)=='@'){
			cType=COMMAND.A;
			symbol=line.substring(1);
		}
		else if((line.charAt(0)=='(')&&(line.charAt(line.length()-1)==')')){
			cType=COMMAND.L;
			symbol=line.substring(1,line.length()-1);
		}
		else{
			cType=COMMAND.C;
			dest=comp=jump="";
			if(line.indexOf('=')>=1){
				dest=line.substring(0,line.indexOf('='));
				line=line.substring(line.indexOf('=')+1);
			}
			if(line.indexOf(';')>=1){
				comp=line.substring(0,line.indexOf(';'));
				line=line.substring(line.indexOf(';')+1);
			}
			else{
				comp=line;
				line="";
			}
			jump=line;
		}
		
	}
	public COMMAND commandType(){
		return this.cType;
	}
	public String symbol(){
		
		return symbol;
	}
	public String dest(){
		return dest;
	}
	public String comp(){
		return comp;
	}
	public String jump(){
		return jump;
	}
}
