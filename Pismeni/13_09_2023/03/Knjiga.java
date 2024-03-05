import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Knjiga{
	
	public Zanr zanr;
	public Povez povez;
	public String naslov, pisac;
	public int godinaObjavljivanja, brojStranica;

	public Knjiga(Zanr zanr, Povez povez, String naslov, String pisac, int godinaObjavljivanja, int brojStranica){
		this.godinaObjavljivanja = godinaObjavljivanja;
		this.brojStranica = brojStranica;
		this.naslov = naslov;
		this.pisac = pisac;
		this.povez = povez;
		this.zanr = zanr;
	}

	@Override
	public boolean equals(Object obj){
		Knjiga k = (Knjiga) obj;
		
		if (k.naslov.equals(naslov) && k.pisac.equals(pisac))
			return true;
		else 
			return false;
	}
	
	@Override
	public String toString(){
		return zanr + " " + povez + " " + naslov + " " + pisac + " " +godinaObjavljivanja + " " + brojStranica;
	}
	
	public int getGodinaObjavljivanja(){
		return godinaObjavljivanja;
	}
	
	public int getBrojStranica(){
		return brojStranica;
	}
	
	public String getPisac(){
		return pisac;
	}
}