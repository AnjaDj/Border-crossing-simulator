import java.io.*;
import java.util.*;

public class Nit extends Thread{
	
	public static final Object LOCK = new Object();
	public static boolean END = false;
	private static Random random = new Random();
	
	private int obrada(int  broj){
		
		if (random.nextInt(11) <= 7) return broj+1;
		else return broj-1;
		
	}
	
	@Override
	public void run(){
		
		while(!END){
		synchronized(LOCK){
				if (!END){
						try(BufferedReader br = new BufferedReader(new FileReader(new File(Takmicenje.NAZIV_DATOTEKE)))){
							
							int broj = Integer.valueOf(br.readLine()); System.out.println(this+" PROCITANO : "+broj);
							
							if (broj != Takmicenje.BROJ){
								broj = obrada(broj);
								
								try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(Takmicenje.NAZIV_DATOTEKE),false)))){pw.print(broj);}
								System.out.println(this+" UPISANO : "+broj);
							}else{
								System.out.println(this+" PROCITANO : "+broj+" KRAJ!");
								END = true;
							}
							
							sleep(200);
							
						}catch(Exception e){e.printStackTrace();}
				}
		}
		}
	}
	
}