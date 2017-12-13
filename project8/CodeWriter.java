import java.io.FileWriter;
import java.io.IOException;


public class CodeWriter {
    FileWriter output;
    int logic_id=0;
    String sourceName;
    String addProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"M=M+D\n";
    String subProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"M=M-D\n" ;
    String negProc=
	"@SP\n" +
	"A=M-1\n" +
	"M=-M\n";
	
    String eqProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"D=M-D\n" +
	"@LOGICAL_IF_:id\n"+
	"D;JEQ\n"+
	"@0\n"+
	"D=A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"@LOGICAL_CONTINUE_:id\n"+
	"0;JMP\n"+
	"(LOGICAL_IF_:id)\n" +
	"@1\n"+
	"D=-A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"(LOGICAL_CONTINUE_:id)\n"
	;
    String ltProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"D=M-D\n" +
	"@LOGICAL_IF_:id\n"+
	"D;JLT\n"+
	"@0\n"+
	"D=A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"@LOGICAL_CONTINUE_:id\n"+
	"0;JMP\n"+
	"(LOGICAL_IF_:id)\n" +
	"@1\n"+
	"D=-A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"(LOGICAL_CONTINUE_:id)\n"
	;
	
    String gtProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"D=M-D\n" +
	"@LOGICAL_IF_:id\n"+
	"D;JGT\n"+
	"@0\n"+
	"D=A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"@LOGICAL_CONTINUE_:id\n"+
	"0;JMP\n"+
	"(LOGICAL_IF_:id)\n" +
	"@1\n"+
	"D=-A\n"+
	"@SP\n"+
	"A=M-1\n"+
	"M=D\n"+
	"(LOGICAL_CONTINUE_:id)\n"
	;
	
    String andProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"M=M&D\n"
	;
    String orProc=
	"@SP\n" +
	"AM=M-1\n" +
	"D=M\n" +
	"@SP\n"+
	"A=M-1\n" +
	"M=M|D\n"
	;
    String notProc=
	"@SP\n" +
	"A=M-1\n" +
	"M=!M\n";
	
    String pushProc=
	"@:index\n" +
	"D=A\n" +
	"@:segment\n" +
	"A=D+M\n" +
	"D=M\n" +
	"@SP\n" +
	"A=M\n" +
	"M=D\n" +
	"@SP\n" +
	"M=M+1\n";
    String constPushProc=
	"@:val\n" +
	"D=A\n" +
	"@SP\n" +
	"A=M\n" +
	"M=D\n" +
	"@SP\n" +
	"M=M+1\n";
    String popProc=
	"@:index\n" +
	"D=A\n" +
	"@:segment\n" +
	"D=D+M\n" +
	"@SP\n" +
	"A=M\n" +
	"M=D\n" +
	"A=A-1\n" +
	"D=M\n" +
	"A=A+1\n" +
	"A=M\n" +
	"M=D\n" +
	"@SP\n" +
	"M=M-1\n";
	
    String directPushProc=
	"@:segment\n"+
	"D=M\n"+
	"@SP\n"+
	"A=M\n"+
	"M=D\n"+
	"@SP\n"+
	"M=M+1\n";
	
    String directPopProc=
	"@SP\n"+
	"M=M-1\n"+
	"@SP\n"+
	"A=M\n"+
	"D=M\n"+
	"@:segment\n"+
	"M=D\n";

    String initProc=
	"@256\n"+
	"D=A\n"+
	"@SP\n"+
	"M=D\n"+
	"@Sys.init\n"+
	"0;JMP\n"
	;

    String  funCallProc=
	"@Return_:fun_:id\n"+
	"D=A\n"+
	"@SP\n"+
	"A=M\n"+
	"M=D\n"+
	"@LCL\n"+
	"D=M\n"+
	"@SP\n"+
	"AM=M+1\n"+
	"M=D\n"+
	"@ARG\n"+
	"D=M\n"+
	"@SP\n"+
	"AM=M+1\n"+
	"M=D\n"+
	"@THIS\n"+
	"D=M\n"+
	"@SP\n"+
	"AM=M+1\n"+
	"M=D\n"+
	"@ARG\n"+
	"D=M\n"+
	"@SP\n"+
	"AM=M+1\n"+
	"M=D\n"+
	"@THAT\n"+
	"D=M\n"+
	"@SP\n"+
	"AM=M+1\n"+
	"M=D\n"+
	"@SP\n"+
	"M=M+1\n"+
	"@5\n"+
	"D=A\n"+
	"@:args\n"+
	"D=D+A\n"+
	"@SP\n"+
	"D=M-D\n"+
	"@ARG\n"+
	"M=D\n"+
	"@SP\n"+
	"D=M\n"+
	"@LCL\n"+
	"M=D\n"+
	"@Declare_:fun\n"+
	"0;JMP\n"+
	"(Return_:fun_:id)\n"
	;

    String funDecProc=
	"(Declare_:fun)\n"+
	"@:args\n"+
	"D=A\n"+
	"(Lable_:fun_init)\n"+
	"@Lable_:fun_init_end\n"+
	"D;JEQ\n"+
	"@SP\n"+
	"A=M\n"+
	"M=0\n"+
	"@SP\n"+
	"M=M+1\n"+
	"D=D-1\n"+
	"(Lable_:fun_init_end)\n"+
	;
    String funRetProc=
	"@LCL\n"+
	"D=M\n"+
	"@FRAME\n"+
	"M=D\n"+
	"@SP\n"+
	"DM=M-1\n"+
	"@ARG\n"+
	"M=D\n"+
	"@SP\n"+
	"M=D-1\n"+
	"@FRAME\n"+
	"D=M\n"+
	"@THAT\n"+
	"MD=D-1\n"+
	"@THIS\n"+
	"MD=D-1\n"+
	"@ARG\n"+
	"MD=D-1\n"+
	"@LCL\n"+
	"MD=D-1\n"+
	"@RET\n"+
	"M=D-1\n"+
	"A=M\n"+
	"0;JMP\n"
	;
	    
    String lableProc=
	"(:lable)\n";

    String gotoProc=
	"@:lable\n"+
	"0;JMP\n";

    String ifGotoProc=
	"@SP\n"+
	"AM=M-1\n"+
	"D=M\n"+
	"@:label\n"+
	"D;JEQ\n";
	
    public CodeWriter(FileWriter output) {
	super();
	this.output = output;
    }
	
    public void setFileName(String str){
	this.sourceName=str;
    }
	
    public boolean writeArithmetic(String command){
	if(command.equalsIgnoreCase("add")){
	    return writeToBinary(this.output,this.addProc);
	}
	else if(command.equalsIgnoreCase("sub")){
	    return writeToBinary(this.output,this.subProc);
	}
	else if(command.equalsIgnoreCase("neg")){
	    return writeToBinary(this.output,this.negProc);
	}
	else if(command.equalsIgnoreCase("eq")){
	    this.logic_id++;
	    return writeToBinary(this.output,this.eqProc.replaceAll(":id", ""+this.logic_id));
	}
	else if(command.equalsIgnoreCase("gt")){
	    this.logic_id++;
	    return writeToBinary(this.output,this.gtProc.replaceAll(":id", ""+this.logic_id));
	}
	else if(command.equalsIgnoreCase("lt")){
	    this.logic_id++;
	    return writeToBinary(this.output,this.ltProc.replaceAll(":id", ""+this.logic_id));
	}
	else if(command.equalsIgnoreCase("and")){
	    return writeToBinary(this.output,this.andProc);
	}
	else if(command.equalsIgnoreCase("or")){
	    return writeToBinary(this.output,this.orProc);
	}
	else if(command.equalsIgnoreCase("not")){
	    return writeToBinary(this.output,this.notProc);
	}
	return false;
		
    }
	
    public boolean WritePushPop(COMMAND command,String segment,int index){
	if("local".equalsIgnoreCase(segment)){
	    segment="LCL";
	}
	else if("argument".equalsIgnoreCase(segment)){
	    segment="ARG";
	} 
	else if("this".equalsIgnoreCase(segment)){
	    segment="THIS";
	} 
	else if("that".equalsIgnoreCase(segment)){
	    segment="THAT";
	} 
	else if("constant".equalsIgnoreCase(segment)){
	    if(command==COMMAND.POP)
		return false;
	}
	else if("static".equalsIgnoreCase(segment)){
	    segment="16";
	} 
	else if("pointer".equalsIgnoreCase(segment)){
	    if((index<0)&&(index>1)){
		return false;
	    }
	} 
	else if("temp".equalsIgnoreCase(segment)){
	    if(index>7)
		return false;
	} 
	else {
	    return false;
	} 
		
	if(command==COMMAND.PUSH){
	    if("temp".equalsIgnoreCase(segment))
		return writeToBinary(this.output,this.directPushProc.replace(":segment",""+(5+index)));
	    else if("pointer".equalsIgnoreCase(segment))
		return writeToBinary(this.output,this.directPushProc.replace(":segment",""+(3+index)));
	    else if(!"constant".equalsIgnoreCase(segment))
		return writeToBinary(this.output,this.pushProc.replace(":segment", segment).replace(":index", ""+index));
	    else
		return writeToBinary(this.output,this.constPushProc.replace(":val", ""+index));
	}
	else if(command==COMMAND.POP){
	    if("temp".equalsIgnoreCase(segment))
		return writeToBinary(this.output,this.directPopProc.replace(":segment",""+(5+index)));
	    else if("pointer".equalsIgnoreCase(segment))
		return writeToBinary(this.output,this.directPopProc.replace(":segment",""+(3+index)));
	    else
		return writeToBinary(this.output,this.popProc.replace(":segment", segment).replace(":index", ""+index));
	}
	return false;
    }
    public boolean writeInit(){
	return writeToBinary(this.output,this.initProc);
    }
    public boolean writeLable(String label){
	return writeToBinary(this.output,this.labelProc.replace(":label",label));
    }
    public boolean writeGoto(String label){
	return writeToBinary(this.output,this.gotoProc.replace(":label",label));
    }
    public boolean writeIf(String label){
	return writeToBinary(this.output,this.ifGotoProc.replace(":label",label));
    }
    public boolean writeCall(String func,int noargs){
	this.logic_id++;
	return writeToBinary(this.output,this.funCallProc.replaceAll(":fun",func).replaceAll(":id",this.logic_id).replaceAll(":args",noargs);
    }
    public boolean writeReturn(){
	    return writeToBinary(this.output,this.funRetProc);
    }
    public boolean writeFunction(String func,int nolocal){
	return writeToBinary(this.output,this.funDecProc.replaceAll(":fun",func).replaceAll(":args",nolocal));
    }
    public boolean writeToBinary(FileWriter binary,String str){
	try {
	    binary.write(str);
	    return true;
	} catch (IOException e) {
	    return false;
	}
		
    }
}
