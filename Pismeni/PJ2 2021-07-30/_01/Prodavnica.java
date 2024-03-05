import java.util.*;

public class Prodavnica{
	
	private static Random random = new Random();
	
	public List<Kasa> kolekcijaKasa;
	private void setKolekcijaKasa(){
		
		kolekcijaKasa = new ArrayList<>();
		
		kolekcijaKasa.add(new SamousluznaKasa());
		kolekcijaKasa.add(new SamousluznaKasa());
		kolekcijaKasa.add(new KasaZaKolica());
		kolekcijaKasa.add(new KasaZaKolica());
		kolekcijaKasa.add(new KasaZaKorpe());
		kolekcijaKasa.add(new KasaZaKorpe());
		
		int temp = 0;
		while(temp < 4){
			int index = random.nextInt(kolekcijaKasa.size());
			if (kolekcijaKasa.get(index).mozeProcesiratiFizickaLica == false){
				
				kolekcijaKasa.get(index).mozeProcesiratiFizickaLica = true;
				temp++;
			}
		}
		
		Collections.shuffle(kolekcijaKasa);
	}
	
	public List<Kupac> kolekcijaKupaca;
	private void setKolekcijaKupaca(){
		
		kolekcijaKupaca = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
			FizickoLice fl = new FizickoLice();
			fl.stanjeNaRacunu = random.nextInt(51)+50;
			if (random.nextInt(11) <= 50) fl.izabraoKorpu = true; else fl.izabraoKolica = true;
			kolekcijaKupaca.add(fl);
		}
		for(int i = 0; i < 10; i++){
			Dijete d = new Dijete();
			d.stanjeNaRacunu = random.nextInt(51)+50;
			if (random.nextInt(11) <= 50) d.izabraoKorpu = true; else d.izabraoKolica = true;
			kolekcijaKupaca.add(d);
		}
		for(int i = 0; i < 10; i++){
			OdrasloLiceBezPrioriteta o = new OdrasloLiceBezPrioriteta();
			o.stanjeNaRacunu = random.nextInt(51)+50;
			if (random.nextInt(11) <= 50) o.izabraoKorpu = true; else o.izabraoKolica = true;
			kolekcijaKupaca.add(o);
		}
		for(int i = 0; i < 10; i++){
			OdrasloLiceSaPrioritetom o = new OdrasloLiceSaPrioritetom();
			o.stanjeNaRacunu = random.nextInt(51)+50;
			if (random.nextInt(11) <= 50) o.izabraoKorpu = true; else o.izabraoKolica = true;
			kolekcijaKupaca.add(o);
		}
		
		Collections.shuffle(kolekcijaKupaca);
		
	}

	public static final Object LOCK_KOLEKCIJA_ARTIKALA = new Object();
	public List<Artikal> kolekcijaArtikala;
	private void setKolekcijaArtikala(){
		
		kolekcijaArtikala = new ArrayList<>();
		
		for(int i = 0; i < 200; i++){
			kolekcijaArtikala.add(Artikal.HLJEB);
			kolekcijaArtikala.add(Artikal.MLIJEKO);
			kolekcijaArtikala.add(Artikal.CIGARETE);
			kolekcijaArtikala.add(Artikal.PIVO);
			kolekcijaArtikala.add(Artikal.SOK);
			kolekcijaArtikala.add(Artikal.SLATKIS);
		}
		
		Collections.shuffle(kolekcijaArtikala);
		
	}
}