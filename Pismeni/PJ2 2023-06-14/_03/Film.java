import java.util.*;

public class Film{
	
	private Zanr zanr;
	private String nazivFilma;
	private List<String> listaGlumaca;
	private int godinaObjavljivanja, vrijemeTrajanja, budzet;
	
	public Film(Zanr zanr, String nazivFilma, List<String> listaGlumaca, int godinaObjavljivanja, int vrijemeTrajanja, int budzet){
		this.zanr = zanr;
		this.budzet = budzet;
		this.nazivFilma = nazivFilma;
		this.listaGlumaca = listaGlumaca;
		this.vrijemeTrajanja = vrijemeTrajanja;
		this.godinaObjavljivanja = godinaObjavljivanja;
	}
	
	public Zanr getZanr(){return zanr;}
	public int getBudzet(){return budzet;}
	public String getNazivFilma(){return nazivFilma;}
	public List<String> getListaGlumaca(){return listaGlumaca;}
	public int getVrijemeTrajanja(){return vrijemeTrajanja;}
	public int getGodinaObjavljivanja(){return godinaObjavljivanja;}
	
	public static void main(String[] args){
		
		Random random = new Random();
		List<Film> kolekcijaFilmova = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
			kolekcijaFilmova.add(new Film(Zanr.HOROR,"NazivFilma",List.of("Heath Ledger","Keanu Reevs","Keira Knightley","Monica Belucci"),
										  random.nextInt(1960)+64,random.nextInt(151)+80,random.nextInt(1000)+10000));
			kolekcijaFilmova.add(new Film(Zanr.KOMEDIJA,"NazivFilma",List.of("Heath Ledger","Keanu Reevs","Keira Knightley","Monica Belucci"),
										  random.nextInt(1960)+64,random.nextInt(151)+80,random.nextInt(1000)+10000));
			kolekcijaFilmova.add(new Film(Zanr.TRILER,"NazivFilma",List.of("Heath Ledger","Keanu Reevs","Keira Knightley","Monica Belucci"),
										  random.nextInt(1960)+64,random.nextInt(151)+80,random.nextInt(1000)+10000));
			kolekcijaFilmova.add(new Film(Zanr.DOKUMENTARAC,"NazivFilma",List.of("Heath Ledger","Keanu Reevs","Keira Knightley","Monica Belucci"),
										  random.nextInt(1960)+64,random.nextInt(151)+80,random.nextInt(1000)+10000));
			kolekcijaFilmova.add(new Film(Zanr.AKCIJA,"NazivFilma",List.of("Heath Ledger","Keanu Reevs","Keira Knightley","Monica Belucci"),
										  random.nextInt(1960)+64,random.nextInt(151)+80,random.nextInt(1000)+10000));							  
		}
		
		kolekcijaFilmova.forEach(System.out::println);
		
	}
}