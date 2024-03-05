import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Generator{
	
	public static String konfiguracioni_fajl = null;   // "C:/Users/Anja/Desktop/JAVA-Januar/25_01_2023/02/Osoba.txt";
	public static String naziv_klase = null;
	public static String java_fajl = null;
	
	private static void kreiranjeFajla() throws Exception{
		
		int index1  = konfiguracioni_fajl.lastIndexOf("/");
		int index2  = konfiguracioni_fajl.lastIndexOf(".");
		naziv_klase = konfiguracioni_fajl.substring(index1+1, index2);
		java_fajl   = konfiguracioni_fajl.substring(0,index1) + "/" + naziv_klase + ".java";
		
		Files.createFile(Paths.get(java_fajl));
	}
	
	private static String getVidljivost(String s){
		switch(s) {
		  case "+":
			return "public";
		  case "#":
			return "protected";
		  case "-":
			return "private";
		  default :
		    return "INVALID";
		}
	}
	
	private static String swaping(String s){
		
		String[] temp = s.split(" ");
		return temp[1]+" "+temp[0];
	}
	
	private static void upisUFajl(){
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(java_fajl,true)))){
			pw.println("public class "+naziv_klase+"{");
			
			List<String> sadrzaj_cf = Files.readAllLines(Paths.get(konfiguracioni_fajl));
			
			for(Iterator<String> it = sadrzaj_cf.iterator(); it.hasNext(); ){
				
				String line = it.next();	// line to be processed
				
				String naziv, tip;
				String vidljivost = getVidljivost((line.split(" "))[0]);
				if (!line.contains("(")){
					naziv = (line.split(" "))[1];
					tip = (line.split(" "))[2];
				}else{
					tip = (line.split(" "))[3];
					naziv = (line.split(" "))[1] + " " + (line.split(" "))[2];
					System.out.println(naziv);
					String arg = naziv.split("\\(")[1];
					arg = arg.split("\\)")[0];
					naziv = (naziv.split("\\("))[0] + "(" + swaping(arg) + ")";
				}
				
				pw.println("	"+vidljivost+" "+tip+" "+naziv+";");
				
				if (!line.contains("(") && !"public".equals(vidljivost)){	// clan koji nije public
					pw.println("	public"+" "+tip+" get"+naziv+"(){");
					pw.println("		return "+naziv+";");
					pw.println("	}");
					
					pw.println("	public void"+" set"+naziv+"("+tip+" "+naziv+"){");
					pw.println("		this."+naziv+" = "+naziv+";");
					pw.println("	}");
				}
			}
			
			pw.println("	public "+naziv_klase+"(){}");	// podrazumevani konstruktor
			
			pw.println("}");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{

		for(int i = 0; i < args.length; i++){
			if ("-cf".equals(args[i])) konfiguracioni_fajl = args[i+1];
		}
		if (konfiguracioni_fajl == null) return;
		
		kreiranjeFajla();
		upisUFajl();
	}
}
