import java.util.*;

public class Kolo extends Thread{
	
	public List<Partija> partije;
	
	public Kolo(List<Partija> partije){
		this.partije = partije;
	}

	@Override
	public void run(){
		
		try{
			for(Iterator<Partija> it = partije.iterator(); it.hasNext();)
				(it.next()).start();
			
			for(Iterator<Partija> it = partije.iterator(); it.hasNext();)
				(it.next()).join();
			
		}catch(InterruptedException e){
			
		}
	}
}