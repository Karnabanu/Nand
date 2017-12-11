import java.io.BufferedReader;
import java.io.IOException;


public class Parser {
	private BufferedReader source;
	private String line;
	private String[] words;
	private COMMAND cType;
	private String arg1;
	private int arg2;
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
		words=line.split(" ");
		
		if(
				"add".equalsIgnoreCase(words[0])||
				"sub".equalsIgnoreCase(words[0])||
				"neg".equalsIgnoreCase(words[0])||
				"eq".equalsIgnoreCase(words[0])||
				"gt".equalsIgnoreCase(words[0])||
				"lt".equalsIgnoreCase(words[0])||
				"and".equalsIgnoreCase(words[0])||
				"or".equalsIgnoreCase(words[0])||
				"not".equalsIgnoreCase(words[0])
		){
			this.arg1=words[0].toLowerCase();
			this.cType=COMMAND.ARITH;
		}
		else if(
				"push".equalsIgnoreCase(words[0])
		){
			this.arg1=words[1].toLowerCase();
			this.arg2=Integer.parseInt(words[2]);
			this.cType=COMMAND.PUSH;
		}
		else if(
				"pop".equalsIgnoreCase(words[0])
		){
			this.arg1=words[1].toLowerCase();
			this.arg2=Integer.parseInt(words[2]);
			this.cType=COMMAND.POP;
		}
		
	}

	public COMMAND commandType(){
		return this.cType;
	}
	
	public String arg1(){
		return this.arg1;
	}
	public int arg2(){
		return this.arg2;
	}
}
