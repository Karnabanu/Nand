import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.*;

public class Assembler {
	public static void main(String[] args){
		BufferedReader source;
		FileWriter binary;
		FileReader sourceFile;
		try {
			sourceFile=new FileReader(args[0]);
			source=new BufferedReader(sourceFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Please pass a valid source file");
			return;
		}
		Parser parser=new Parser(source);
		Code code=new Code();
		SymbolTable smtable=new SymbolTable();
		try {
			binary=new FileWriter(args[0].substring(0,args[0].indexOf(".")>0?args[0].indexOf("."):args[0].length()-1)+".hack");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to create Output binary file");
			return;
		}
		
		String binNum;
		int pc=0;
		while(true){
			try{
				if(!parser.hasMoreCommands())
				break;
			}
			catch(IOException e){
				System.out.println("Unable to process the source code");
			}
			parser.advance();
			if(COMMAND.L==parser.commandType()){
				String symbol;
				symbol=parser.symbol();
				if(isSymbol(symbol)){
					if(!smtable.contains(symbol)){
						smtable.addEntry(symbol, pc);
					}
				}
				
			}
			else
				pc++;
		}
		try {
			sourceFile=new FileReader(args[0]);
			source=new BufferedReader(sourceFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Please pass a valid source file");
			return;
		}
		parser=new Parser(source);
		int address=16;
		while(true){
			try{
				if(!parser.hasMoreCommands())
				break;
			}
			catch(IOException e){
				System.out.println("Unable to process the source code");
			}
			parser.advance();
			if(COMMAND.A==parser.commandType()){
				String symbol;
				symbol=parser.symbol();
				if(isSymbol(symbol)){
					if(!smtable.contains(symbol)){
						smtable.addEntry(symbol, address);
						address++;
					}
					symbol=smtable.getAddress(symbol)+"";
				}
				binNum=getBinaryNumber(symbol);
				if(binNum.equals(null))
				{
					System.out.println("Sytax erros in source");
					return;
				}
				if(!writeToBinary(binary,'0'+binNum.substring(1)))
				{
					System.out.println("\nError in creating a binary file");
					return;
				}
			}
			else if(COMMAND.L==parser.commandType()){
				continue;
			}
			else if(COMMAND.C==parser.commandType()){
				String comp=parser.comp();
				String dest=parser.dest();
				String jump=parser.jump();
				String binComp,binDest,binJump;
				String inst="111";
				
				binComp=code.comp(comp);
				binDest=code.dest(dest);
				binJump=code.jump(jump);
				
				if((binComp==null)||(binDest==null)||(binJump==null)){
					System.out.println("Sytax erros in source");
					return;
				}
				inst=inst+binComp+binDest+binJump;
				if(!writeToBinary(binary,inst))
				{
					System.out.println("\nError in creating a binary file");
					return;
				}
				
			}
			if(!writeToBinary(binary,"\n"))
			{
				System.out.println("\nError in creating a binary file");
				return;
			}
		}
		try{
			binary.close();
		}
		catch(IOException e){
			System.out.println("\nError i cretating binary file");
		}
	}
	public static String getBinaryNumber(String str){
		try{
			return String.format("%16s", Integer.toBinaryString(Integer.parseInt(str))).replace(' ', '0');
		}
		catch(NumberFormatException e){
			return null;
		}
	}
	
	public static boolean writeToBinary(FileWriter binary,String str){
		try {
			binary.write(str);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
	public static boolean isSymbol(String symbol){
		Pattern p1= Pattern.compile("^[a-zA-Z]+.*");
		Pattern p2= Pattern.compile("^_.*");
		
		return (p1.matcher(symbol).matches())||(p2.matcher(symbol).matches());
	}
}


