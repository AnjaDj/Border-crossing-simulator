package simulacija;
import firma.*;
import java.util.stream.*;
import java.util.*;

public class Simulacija{
	
	public static void main(String[] args){
		
		Artikal a0 = new Artikal("1000","banana",2.10);
		Artikal a1 = new Artikal("1001","jabuka",3.10);
		Artikal a2 = new Artikal("1002","mlijeko",1.20);
		Artikal a3 = new Artikal("1003","toalet papir",9.40);
		Artikal a4 = new Artikal("1004","brasno",1.00);
		Artikal a5 = new Artikal("1005","hljeb",2.00);
		Artikal a6 = new Artikal("1006","jaja",3.50);
		Artikal a7 = new Artikal("1007","sir",4.30);
		Artikal a8 = new Artikal("1008","meso",15.00);
		Artikal a9 = new Artikal("1009","pavlaka",2.60);
		
		
		
		Rafa r1 = new Rafa(1,List.of(a0,a1,a2,a3,a4,a5));
		Rafa r2 = new Rafa(2,List.of(a6,a7,a8,a9));
		Rafa r3 = new Rafa(3,List.of(a5,a6,a7));
		Rafa r4 = new Rafa(4,List.of(a0,a9,a3));
		
		Skladiste s1 = new Skladiste(TipSkladista.MALOPRODAJA,"Jovana Ducica 74b",List.of(r1,r2));
		Skladiste s2 = new Skladiste(TipSkladista.MALOPRODAJA,"Milosa Crnjanskog 12c",List.of(r3,r4));
		
		Firma firma = new Firma(List.of(s1,s2));
		
		/*System,out.println("a. Ispisivanje ukupne kolicine artikla po svim skladistima fime : ");
		
		List<Skladiste> listaSkladista = firma.getSkladistaUfirmi()	
		
			for(Iterator<Skladiste> i = listaSkladista.iterator(); i.hasNext();){
				
				Skladiste skladiste = i.next();
				System.out.println(skladiste.getAdresa()+" "+skladiste.getTipSkladista()+" : ");
				
				
		
				
			}
		*/

		System,out.println("b. Ispisivanje ukupnog finansijskog stanja na skladistima firme za maloprodajna skladista: ");
						
		firma.getSkladistaUfirmi()		// List<Skladiste>
					.stream()			// Stream<Skladiste>
					.filter( skladiste -> (skladiste.getTipSkladista() == TipSkladista.MALOPRODAJA) )
					.collect(Collectors.)
		
	}
	
}
