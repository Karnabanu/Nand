import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class VMTranslator {

    /**
     * @param args
     */
    public static void main(String[] args) {
	ArrayList<String> sources=null;
	try{
	    if(args[0].endsWith(".vm")){
		sources=new ArrayList<String>();
		sources.add(args[0]);
	    }
	    else{
		sources=new ArrayList<String>();
		File dir=new File(args[0]);
		File[] list=dir.listFiles();
		for(File file:list){
		    if(!(file.isDirectory())&&(file.getName().endsWith(".vim"))){
			sources.add(file.getAbsolutePath());
		    }
		}
	    }
	}catch(NullPointerException e){
	    System.out.println("\nPlease provide source file or directory as input");
	}
	String outputFileName;
	FileWriter assemblyFile;
	if(args[0].endsWith(".vm")){
	    int pos=args[0].lastIndexOf(".vm");
	    outputFileName=args[0].substring(0,pos)+".asm";
	}
	else{
	    outputFileName=args[0]+".asm";
	}
		
	try {
	    assemblyFile=new FileWriter(outputFileName);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    System.out.println("Unable to create Output binary file");
	    return;
	}
	CodeWriter codeWriter=new CodeWriter(assemblyFile);
	codeWriter.writeInit();
	FileReader sourceFile;
	BufferedReader source;
	Parser parser;
	for(String sourceFileName:sources){
	    try {
		sourceFile=new FileReader(sourceFileName);
		source=new BufferedReader(sourceFile);
	    } catch (FileNotFoundException e) {
		System.out.println("Please pass a valid source file");
		return;
	    }
			
	    parser=new Parser(source);
	    codeWriter.setFileName(sourceFileName);
	    while(true){
		try{
		    if(!parser.hasMoreCommands())
			break;
		}
		catch(IOException e){
		    System.out.println("Unable to process the source code");
		}
		parser.advance();
				
		if(parser.commandType()==COMMAND.POP||parser.commandType()==COMMAND.PUSH){
		    codeWriter.WritePushPop(parser.commandType(), parser.arg1(), parser.arg2());
		}
		else if(parser.commandType()==COMMAND.ARITH){
		    codeWriter.writeArithmetic(parser.arg1());
		}
		else if(parser.commandType()==COMMAND.LABLE){
		    codeWriter.writeLable(parser.arg1());
		}
		else if(parser.commandType()==COMMAND.GOTO){
		    codeWriter.writeGoto(parser.arg1());
		}
		else if(parser.commandType()==COMMAND.IF){
		    codeWriter.writeIf(parser.arg1());
		}
		else if(parser.commandType()==COMMAND.FUNCTION){
		    codeWriter.writeFunction(parser.arg1(),parser.arg2());
		}
		else if(parser.commandType()==COMMAND.CALL){
		    codeWriter.writeCall(parser.arg1(),parser.arg2());
		}
		else if(parser.commandType()==COMMAND.RETURN){
		    codeWriter.writeReturn();
		}
				
		else{
		    System.out.println("Syntax errors encountered\nExitting");
		}
				
	    }
			
	}
		
	try{
	    assemblyFile.close();
	}
	catch(IOException e){
	    System.out.println("\nError i cretating binary file");
	}
		
    }

}
