import java.util.*;

public class Simulacija{
	
	private static Random random = new Random();
	
	public static int X, Y ;
	
	public static void main(String[] args) throws InterruptedException{
		
		for(int i = 0; i < args.length; i++){
			if ("-X".equals(args[i])) X = Integer.valueOf(args[i+1]);
			if ("-Y".equals(args[i])) Y = Integer.valueOf(args[i+1]);
		}
		
		if (Mapa.VELICINA_X > 15 || Mapa.VELICINA_X < 3 || Mapa.VELICINA_Y > 15 || Mapa.VELICINA_Y < 3){
				
			System.out.println("Nevalidan unos pocetnih parametara!");	
			return;	
		}
		
		List<LegoKockica> kolekcijaKockica = napraviKolekcijuKockica();
		
		for(LegoKockica kockica : kolekcijaKockica) kockica.start();
		for(LegoKockica kockica : kolekcijaKockica) kockica.join();
		
		for(int i = 0; i < Mapa.VELICINA_X; i++){
			for(int j = 0; j < Mapa.VELICINA_Y; j++){
				System.out.print((Mapa.PLOCA[i][j]).naPolju+"   ");
			}
			System.out.println();
		}
		
	}
	
	private static List<LegoKockica> napraviKolekcijuKockica(){
		
		List<LegoKockica> kolekcijaKockica = new ArrayList<>();
		
		for(int i = 0; i < 20; i++)
			kolekcijaKockica.add(new PravougaonaLegoKockica(random.nextInt(6)+2, random.nextInt(6)+2 , "CRVENA"));
		for(int i = 0; i < 20; i++)
			kolekcijaKockica.add(new KvadratnaLegoKockica(random.nextInt(6)+2, "PLAVA"));
		for(int i = 0; i < 20; i++)
			kolekcijaKockica.add(new CilindricnaLegoKockica(random.nextInt(6)+2, "BELA"));
		Collections.shuffle(kolekcijaKockica);
		
		return kolekcijaKockica;
	}
	
}