package liga;
import igraci.*;

public class Kolo extends Thread{	// U svakom kolu se odigravaju 4 partije istovremeno

	private Tim[] par1, par2, par3, par4;
	
	public Kolo(Tim[] par1,Tim[] par2,Tim[] par3,Tim[] par4, String s){
		this.par1 = par1;
		this.par2 = par2;
		this.par3 = par3;
		this.par4 = par4;
	}
	
	@Override
	public void run(){
		
		Partija p1 = new Partija(par1);
		Partija p2 = new Partija(par2);
		Partija p3 = new Partija(par3);
		Partija p4 = new Partija(par4);
		
		p1.start(); p2.start(); p3.start(); p4.start();
		p1.join(); p2.join(); p3.join(); p4.join();
	
	}
	
}