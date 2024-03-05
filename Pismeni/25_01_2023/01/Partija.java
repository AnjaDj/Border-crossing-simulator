import java.util.*;

public class Partija extends Thread{
	Tim tim1, tim2;
	
	public Partija(Tim tim1, Tim tim2){
		this.tim1 = tim1;
		this.tim2 = tim2;
	}
	
	@Override
	public void run(){
		double poeniT1 = 0, poeniT2 = 0;
		
		for(Iterator<Igrac> it = tim1.clanovi_tima.iterator(); it.hasNext();)
			poeniT1 += (it.next()).bacanjeKugle();
		
		for(Iterator<Igrac> it = tim2.clanovi_tima.iterator(); it.hasNext();)
			poeniT2 += (it.next()).bacanjeKugle();
		
		if (poeniT1 > poeniT2) tim1.broj_pobijeda++;
		else if (poeniT2 > poeniT1) tim2.broj_pobijeda++;
	}
}