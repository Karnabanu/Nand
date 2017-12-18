import java.io.BufferedReader;
import java.io.IOException;


public class Parser {
    private BufferedReader source;
    private String line;
    private String comment;
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
	words=line.split("\\s+");
	
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
	    this.comment="\n//"+words[0]+"\n";
	}
	else if(
		"push".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1].toLowerCase();
	    this.arg2=Integer.parseInt(words[2]);
	    this.cType=COMMAND.PUSH;
	    this.comment="\n//"+words[0]+" "+words[1]+" "+words[2]+"\n";
	}
	else if(
		"pop".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1].toLowerCase();
	    this.arg2=Integer.parseInt(words[2]);
	    this.cType=COMMAND.POP;
	    this.comment="\n//"+words[0]+" "+words[1]+" "+words[2]+"\n";
	}
	else if(
		"label".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1];
	    this.cType=COMMAND.LABLE;
	    this.comment="\n//"+words[0]+" "+words[1]+"\n";
	}
	else if(
		"goto".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1];
	    this.cType=COMMAND.GOTO;
	    this.comment="\n//"+words[0]+" "+words[1]+"\n";
	}
	else if(
		"if-goto".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1];
	    this.cType=COMMAND.IF;
	    this.comment="\n//"+words[0]+" "+words[1]+"\n";
	}
	else if(
		"function".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1];
	    this.arg2=Integer.parseInt(words[2]);
	    this.cType=COMMAND.FUNCTION;
	    this.comment="\n//"+words[0]+" "+words[1]+" "+words[2]+"\n";
	}
	else if(
		"call".equalsIgnoreCase(words[0])
		){
	    this.arg1=words[1];
	    this.arg2=Integer.parseInt(words[2]);
	    this.cType=COMMAND.CALL;
	    this.comment="\n//"+words[0]+" "+words[1]+" "+words[2]+"\n";
		
	}
	else if(
		"return".equalsIgnoreCase(words[0])
		){
	    this.cType=COMMAND.RETURN;
	    this.comment="\n//"+words[0]+"\n";
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
    public String getComment(){
    	return this.comment;
    }
}

