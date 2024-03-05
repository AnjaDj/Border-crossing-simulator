import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Salter extends Thread{
	
	public int salterID;
	public static final Object LOCK = new Object();
	public static final Object LOCK_FILE = new Object();
	public static PriorityQueue<Osoba> RED;
	
	public Salter(int salterID){
		this.salterID = salterID;
	}
	
	@Override
	public void run(){
		
		Osoba osoba;
		
		while(!RED.isEmpty()){
			
			osoba = null;
			synchronized(LOCK){
				if (!RED.isEmpty())
					osoba = RED.poll();
			}
			
			if (osoba != null){
				System.out.println(osoba + " se obradjuje na salteru "+ this);
				try{
					Thread.sleep(500);
					if (osoba.podnosenjeZahtjevaZaPasos()){
						System.out.println(osoba + " je ispunila sve preduslove za ispunjenje pasosne usluge na salteru "+ this);
						ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( salterID + "_" + osoba.jmbg + ".txt" ) );
						oos.writeObject(osoba);
						oos.close();
					}else{
						synchronized(LOCK_FILE){
							System.out.println(osoba + " nije ispunila sve preduslove za ispunjenje pasosne usluge na salteru "+ this);
							ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "failed.txt", true ) );
							oos.writeObject(osoba);
							oos.close();
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}	
	}
	
}