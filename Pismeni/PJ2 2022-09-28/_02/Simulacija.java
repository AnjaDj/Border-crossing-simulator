import java.io.*;

public class Simulacija{
	
	public static char action = 'm';
	
	public static void main(String[] args){
		
		String path = "";
		
		for(int i = 0; i < args.length; i++)
			if("-s".equals(args[i])) {  path = args[i+1]; break; }
		
		File documentPath = new File(path);
		
		if( documentPath.exists() && !documentPath.isDirectory() ){
			
			CopyMaker.documentPath = CopyReader.documentPath = documentPath;
			
			CopyMaker cm = new CopyMaker();
			CopyReader cr = new CopyReader();
			
			cr.start();
			cm.start();
			
		}
		else System.out.println("Putanja koju ste naveli do datoteke nije validna!");
		
		
	}
	
}