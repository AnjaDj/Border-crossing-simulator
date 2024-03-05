import java.io.*;
import java.util.*;

public class CopyMaker extends Thread{
	
	public static File documentPath;
	
	private int countO(String line){
		
		int counter = 0;
		
		for(int i = 0; i < line.length(); i++)
			if ( ('o' == line.charAt(i)) || ('O' == line.charAt(i)) ) counter++;
		
		return counter;
		
	}
	
	private int countN(String line){
		
		int counter = 0;
		
		for(int i = 0; i < line.length(); i++)
			if ( ('0' == line.charAt(i)) || ('1' == line.charAt(i)) || ('2' == line.charAt(i)) || ('3' == line.charAt(i)) ||
				 ('4' == line.charAt(i)) || ('5' == line.charAt(i)) || ('6' == line.charAt(i)) || ('7' == line.charAt(i)) ||
				 ('8' == line.charAt(i)) || ('9' == line.charAt(i)) ) counter++;
		
		return counter;
		
	}
		
	public void algorithm(){	
		System.out.println("-------------------MOFDIFIKACIJA------------------------>");
		List<String> modifiedLines = new ArrayList<>();
		
		int counterO = 0, counterNumber = 0;
	
		try(BufferedReader br = new BufferedReader(new FileReader(documentPath))){
			
			String line = br.readLine();
			
			while(line != null){
				
				counterO += countO(line);
				counterNumber += countN(line);
				
				line = (line.replaceAll("0","")).replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","")
												.replaceAll("7","").replaceAll("8","").replaceAll("9","");
				line = (line.replaceAll("O", "0")).replaceAll("o","0");
				
				modifiedLines.add(line);
				line = br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(documentPath,true)))){
			
			for(int i = 0; i < modifiedLines.size(); i++)
				pw.println(modifiedLines.get(i));
			
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("rezultatiKopiranja.txt"),true)))){
			
			pw.println("Modifikacija : broj slova O zamijenjenih 0 = "+counterO+" ,  broj izbacenih brojeva = "+counterNumber);
			
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void run(){
		
		for(int i = 1; i <= 10; i++){
			
			synchronized(documentPath){
				
				if (!('m' == Simulacija.action)) try{ documentPath.wait(); } catch(InterruptedException e) {e.printStackTrace();}
				
				algorithm();
				Simulacija.action = 'r';
				documentPath.notify();
				
			}
			
			
		}
		
	}
}