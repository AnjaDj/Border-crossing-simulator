package liga;
import igraci.*;

public class Partija extends Thread{

	private Tim[] par;
	
	public Partija(Tim[] par){
		this.par = par;
	}
	
	@Override
	public void run(){
		
		System.out.println("Partija izmedju timova : "+par[0].getNazivTima+" VS "+par[1].getNazivTima);
		
		int brojPoenaTima1 = 0, brojPoenaTima2 = 0;
		
		// ......
		
		if(brojPoenaTima1 > brojPoenaTima2) {
			par[0].uvecajBrojPoena(1);
			System.out.println("Pobjednik "+par[0]);
		}
		else if(brojPoenaTima1 < brojPoenaTima2) {
			par[1].uvecajBrojPoena(1);
			System.out.println("Pobjednik "+par[1]);
		}
		else{
			System.out.println("Nerijeseno");
		}
	}

}