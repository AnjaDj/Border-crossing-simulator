import java.io.*;
import java.util.*;

public class CopyReader extends Thread{

	public static File documentPath;
		
	public void algorithm(){	
	
		try(BufferedReader br = new BufferedReader(new FileReader(documentPath))){
			
			String line = br.readLine();
			
			while(line != null){
				
				System.out.println(line);
				line = br.readLine();
			}
			System.out.println("<--------------------CITANJE-----------------------");
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
	
	@Override
	public void run(){
		
		for(int i = 1; i <= 10; i++){
			
			synchronized(documentPath){
				
				if (!('r'==Simulacija.action)) try{ documentPath.wait(); } catch(InterruptedException e) {e.printStackTrace();}
				
				algorithm();
				Simulacija.action = 'm';
				documentPath.notify();
			}
			
		}
		
	}
}