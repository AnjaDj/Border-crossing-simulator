package liga;
import igraci.*;

public class Liga extends Thread{
	
	private Tim[] timovi;
	private String nazivLige;
	private static int counter = 0;
	
	public Liga(){
		
		counter++;
		nazivLige = "Liga_"+counter;
		timovi = new Tim[8];
		
		for(int i = 0; i < 8; i++)
			timovi[i] = new Tim();
	}

	public void printLiga(){
		System.out.println(nazivLige+" :");
		for(int i = 0; i < 8; i++){
			System.out.println("   "+timovi[i]);
		}
		System.out.println();
	}
	
	@Override
	public void run(){ // svaka liga ima 7 kola. U svakom od tih 7 kola takmice se po 4 para timova
		
		Kolo kolo1 = new Kolo({timovi[0],timovi[1]},{timovi[2],timovi[3]},{timovi[4],timovi[5]},{timovi[6],timovi[7]},nazivLige+" -> Kolo_1");
			kolo1.start(); kolo1.join();
		
		Kolo kolo2 = new Kolo({timovi[0],timovi[2]},{timovi[3],timovi[1]},{timovi[4],timovi[6]},{timovi[5],timovi[7]},nazivLige+" -> Kolo_2");
			kolo2.start(); kolo2.join();
		
		Kolo kolo3 = new Kolo({timovi[0],timovi[3]},{timovi[2],timovi[1]},{timovi[4],timovi[7]},{timovi[5],timovi[6]},nazivLige+" -> Kolo_3");
			kolo3.start(); kolo3.join();
		
		Kolo kolo4 = new Kolo({timovi[0],timovi[4]},{timovi[2],timovi[5]},{timovi[3],timovi[6]},{timovi[1],timovi[7]},nazivLige+" -> Kolo_4");
			kolo4.start(); kolo4.join();
		
		Kolo kolo5 = new Kolo({timovi[0],timovi[5]},{timovi[2],timovi[4]},{timovi[3],timovi[7]},{timovi[1],timovi[6]},nazivLige+" -> Kolo_5");
			kolo5.start(); kolo5.join();
		
		Kolo kolo6 = new Kolo({timovi[0],timovi[6]},{timovi[2],timovi[7]},{timovi[4],timovi[3]},{timovi[1],timovi[5]},nazivLige+" -> Kolo_6");
			kolo6.start(); kolo6.join();
		
		Kolo kolo7 = new Kolo({timovi[0],timovi[7]},{timovi[2],timovi[6]},{timovi[4],timovi[1]},{timovi[3],timovi[5]},nazivLige+" -> Kolo_7");
			kolo7.start(); kolo7.join();
		
		
		
	}
}