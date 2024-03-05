import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulacija{
		
	private static List<Kategorija> kreiranjeKolekcijeKategorija(){
		
		List<Kategorija> kolekcija = new ArrayList<>();
		
		try{
			List<String> text = Files.readAllLines(Paths.get("kategorije.txt"));
			text.remove(0);		// uklanjanje zaglavlja
			
			for(Iterator<String> it = text.iterator(); it.hasNext(); ){
				
				String line = it.next();
				
				if ((line.split(";")).length == 1)
					kolekcija.add( new Kategorija(line.split(";")[0], null) );
				else if ((line.split(";")).length == 2)
					kolekcija.add (new Kategorija(line.split(";")[0], new Kategorija(line.split(";")[1], null)) );
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return kolekcija;
	}
	
	private static List<Proizvod> kreiranjeKolekcijeProizvoda(List<Kategorija> kategorije){
		
		List<Proizvod> kolekcija = new ArrayList<>();
		
		try{
			List<String> text = Files.readAllLines(Paths.get("proizvodi.txt"));
			
			for(Iterator<String> it = text.iterator(); it.hasNext(); ){
				
				String[] parts = (it.next()).split(";");
				
				for(Iterator<Kategorija> it2 = kategorije.iterator(); it2.hasNext(); ){
					Kategorija kategorija = it2.next();
					if (parts[2].equals(kategorija.naziv)){
						
						kolekcija.add(new Proizvod(kategorija, parts[0], Double.valueOf(parts[1])));
						break;
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return kolekcija;
	}
	
	public static void main(String[] args){
		
		List<Kategorija> kategorije = kreiranjeKolekcijeKategorija();
		List<Proizvod> proizvodi = kreiranjeKolekcijeProizvoda(kategorije);
		
		/*for(Iterator<Proizvod> it = proizvodi.iterator(); it.hasNext(); )
			System.out.println(it.next());*/
		
		proizvodi.stream()
						.
		
		
		
	}
}