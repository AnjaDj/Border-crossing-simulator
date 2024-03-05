
import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {
	
	public static final String NAZIVFAJLA = "data.csv";
	public static final String WAIT = "WAIT";
	public static final String GO = "GO";
	public static final String KRAJ = "KRAJ";
	public static final String IZALAZNIFAJL = "ispis.txt";
	
	public static void main(String[] args) {
		//kao staticke da mogu pozvati metodu StringUObjekat
		Narudzbenica narudzbenica = new Narudzbenica();
		NarudzbenicaStavka narudzbenicaStavka = new NarudzbenicaStavka();
		Faktura faktura = new Faktura();
		FakturaStavka fakturaStavka = new FakturaStavka();
		
		ArrayList<Narudzbenica> listaNarudzbenica = new ArrayList<Narudzbenica>();
		ArrayList<NarudzbenicaStavka> listaNarudzbenicaStavka = new ArrayList<NarudzbenicaStavka>();
		ArrayList<Faktura> listaFaktura = new ArrayList<Faktura>();
		ArrayList<FakturaStavka> listaFakturaStavka = new ArrayList<FakturaStavka>();
		
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(new File(NAZIVFAJLA)));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		String line = "";
		ArrayList<String> linije = new ArrayList<String>();
		
		//Ucitavanje podataka
		try {
			while((line = bf.readLine()) != null) {
				linije.add(line);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		Thread threadNarudzbenica = new Thread(() -> {
			for(int k = 0; k < linije.size();k++) {
				synchronized(linije) {
					String[] split = linije.get(k).split(",");
					if(split[0].equals(Enumeracija.Narudzbenica.toString())) {
						listaNarudzbenica.add(narudzbenica.pretvoriStringUObjekat(linije.get(k)));
					} else if (split[0].equals(Enumeracija.NarudzbenicaStavka.toString())) {
						listaNarudzbenicaStavka.add(narudzbenicaStavka.pretvoriStringUObjekat(linije.get(k)));
					}
					try{
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			for(int i=0;i<listaNarudzbenica.size();i++) {
				for(int j=0;j<listaNarudzbenicaStavka.size();j++) {
					if(listaNarudzbenica.get(i).sifra.equals(listaNarudzbenicaStavka.get(j).sifra)) {
						listaNarudzbenica.get(i).nizStavki.add(listaNarudzbenicaStavka.get(j));
					}
				}
			}
		});
		
		Thread threadFaktura = new Thread(() -> {
			for(int k = 0; k < linije.size();k++) {
				synchronized(linije) {
					String[] split = linije.get(k).split(",");
					if (split[0].equals(Enumeracija.Faktura.toString())) {
						listaFaktura.add(faktura.pretvoriStringUObjekat(linije.get(k)));
					} else if (split[0].equals(Enumeracija.FakturaStavka.toString())) {
						listaFakturaStavka.add(fakturaStavka.pretvoriStringUObjekat(linije.get(k)));
					}
					try{
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			for(int i=0;i<listaFaktura.size();i++) {
				for(int j=0;j<listaFakturaStavka.size();j++) {
					if(listaFaktura.get(i).sifra.equals(listaFakturaStavka.get(j).sifra)) {
						listaFaktura.get(i).nizStavki.add(listaFakturaStavka.get(j));
					}
				}
			}
		});
		
		threadNarudzbenica.start();
		threadFaktura.start();
		
		//MORA SE NA KRAJU NESTO NAPISATI DA ZAVRSI nrp. KRAJ ZBOG scan.nextLine()
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("Unesite komandu [WAIT,GO,KRAJ]: ");
			String unesi = scan.nextLine();
			if(unesi.equals(WAIT)) {
				synchronized(linije) {
					while(true) {
						System.out.print("Unesite komandu [GO]: ");
						unesi = scan.nextLine();
						if(unesi.equals(GO)) {
							break;
						}
						try{
							Thread.sleep(100);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			if(unesi.equals(KRAJ)) {
				break;
			}
			if(threadNarudzbenica.getState().equals(Thread.State.TERMINATED) && threadFaktura.getState().equals(Thread.State.TERMINATED)) {
				break;
			}
			try{
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		try {
			threadNarudzbenica.join();
			threadFaktura.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Ispis PRVOG ZADATKA");
		System.out.println("------------------------------------------------------------");
		System.out.println("Faktura: " + listaFaktura.size());
		System.out.println("FakturaStavka: " + listaFakturaStavka.size());
		System.out.println("Narudzbenica: " + listaNarudzbenica.size());
		System.out.println("NarudzbenicaStavka: " + listaNarudzbenicaStavka.size());
		System.out.println("------------------------------------------------------------");
		
		
		//DRUGI ZADATAK
		
		ArrayList<Narudzbenica> listaNarudzbenicaSaGreskom = new ArrayList<Narudzbenica>();
		ArrayList<Faktura> listaFakturaSaGreskom = new ArrayList<Faktura>();
		
		Predicate<Faktura> uslovZaFakturu = (element) -> {
			double suma = 0;
			for(int i=0;i<element.nizStavki.size();i++) {
				suma+=element.nizStavki.get(i).kolicina * element.nizStavki.get(i).cijenaPoKomadu;
			}
			if(suma == element.ukupanIznos)
				return true;
			else
				return false;
		};
		
		Predicate<Narudzbenica> uslovZaNarudzbenicu = (element) -> {
			boolean pozitivan = true;
			for(int i=0;i<element.nizStavki.size() && pozitivan;i++) {
				pozitivan = (element.nizStavki.get(i).kolicina * element.nizStavki.get(i).cijenaPoKomadu) > 0;
			}
			if(element.nizStavki.size() >= 1 && pozitivan)
				return true;
			else
				return false;
		};
		
		Predicate<Faktura>[] nizUslovaZaFakturu = new Predicate[1];
		nizUslovaZaFakturu[0] = uslovZaFakturu;
		Predicate<Narudzbenica>[] nizUslovaZaNarudzbenicu = new Predicate[1];
		nizUslovaZaNarudzbenicu[0] = uslovZaNarudzbenicu;
		
		for(int i=0;i<listaNarudzbenica.size();i++) {
			if(!Validator.provjeriSveUslove(listaNarudzbenica.get(i),nizUslovaZaNarudzbenicu)) {
				listaNarudzbenicaSaGreskom.add(listaNarudzbenica.get(i));
			}
		}
		
		for(int i=0;i<listaFaktura.size();i++) {
			if(!Validator.provjeriSveUslove(listaFaktura.get(i),nizUslovaZaFakturu)) {
				listaFakturaSaGreskom.add(listaFaktura.get(i));
			}
		}
		
		System.out.println("ISPIS DRUGOG ZADATKA");
		System.out.println("------------------------------------------------------------");
		System.out.println("Narudzbenica sa greskom ima : " + listaNarudzbenicaSaGreskom.size());
		for(int i=0;i<listaNarudzbenicaSaGreskom.size();i++) {
			System.out.println(listaNarudzbenicaSaGreskom.get(i).sifra);
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("Faktura sa greskom ima : " + listaFakturaSaGreskom.size());
		for(int i=0;i<listaFakturaSaGreskom.size();i++) {
			System.out.println(listaFakturaSaGreskom.get(i).sifra);
		}
		System.out.println("------------------------------------------------------------");
		
		listaFakturaSaGreskom.stream().sorted((faktura1,faktura2) -> Double.compare(faktura2.ukupanIznos,faktura1.ukupanIznos)).limit(5).forEach(System.out::println);
		System.out.println("------------------------------------------------------------");
		
		//TRECI ZADATAK
		try {
		PrintWriter pw = new PrintWriter(new File(IZALAZNIFAJL));
		pw.println("Broj parsiranih faktura: " + listaFaktura.size());
		ArrayList<Faktura> listaValidnihFaktura = new ArrayList<Faktura>();
		for(int i=0;i<listaFaktura.size();i++) {
			if(Validator.provjeriSveUslove(listaFaktura.get(i),nizUslovaZaFakturu)) {
				listaValidnihFaktura.add(listaFaktura.get(i));
			}
		}
		double ukupanIznosZaPlacanje = 0;
		for(int i=0;i<listaValidnihFaktura.size();i++){
			ukupanIznosZaPlacanje += listaValidnihFaktura.get(i).ukupanIznos;
		}
		pw.println("Ukupan iznos placanja: " + ukupanIznosZaPlacanje);
		pw.println("Broj parsiranih narudzbenica: " + listaNarudzbenica.size());
		ArrayList<Narudzbenica> listaValidnihNarudzbenica = new ArrayList<Narudzbenica>();
		for(int i=0;i<listaNarudzbenica.size();i++) {
			if(Validator.provjeriSveUslove(listaNarudzbenica.get(i),nizUslovaZaNarudzbenicu)) {
				listaValidnihNarudzbenica.add(listaNarudzbenica.get(i));
			}
		}
		int prosjecnaVrijednost = 0;
		for(int i=0;i<listaValidnihNarudzbenica.size();i++) {
			prosjecnaVrijednost += listaValidnihNarudzbenica.get(i).nizStavki.size();
		}
		prosjecnaVrijednost = prosjecnaVrijednost / listaValidnihNarudzbenica.size();
		pw.println("Prosjecan broj stavki po narudzbenicama: " + prosjecnaVrijednost);
		pw.flush();
		pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		/*
		//KLASA Parser sam pravio ako bi fajl bio previse veliki
		Parser threadFakturaProva = new Parser(true);
		Parser threadNarudzbenicaProva = new Parser(false);
		threadFakturaProva.start();
		threadNarudzbenicaProva.start();
		try {
		threadFakturaProva.join();
		threadNarudzbenicaProva.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		Parser.listaFaktura.stream().forEach(System.out::println);
		*/
	}
}