
import java.util.*;
import java.io.*;

public class Parser extends Thread {
	public static ArrayList<Narudzbenica> listaNarudzbenica = new ArrayList<Narudzbenica>();
	public static ArrayList<NarudzbenicaStavka> listaNarudzbenicaStavka = new ArrayList<NarudzbenicaStavka>();
	public static ArrayList<Faktura> listaFaktura = new ArrayList<Faktura>();
	public static ArrayList<FakturaStavka> listaFakturaStavka = new ArrayList<FakturaStavka>();
	
	private boolean isFaktura;
	private static RandomAccessFile raf = null;
	static {
		try {
			raf = new RandomAccessFile(new File(Main.NAZIVFAJLA),"r");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} 
	}
	
	
	public Parser(boolean isFaktura) {
		this.isFaktura = isFaktura;
	}
	
	private static Object lock = new Object();
	private static int vratiSe = 0;
	public void run() {
		try {
		if(isFaktura) {
			Faktura faktura = new Faktura();
			FakturaStavka fakturaStavka = new FakturaStavka();
			synchronized(lock) {
				String line = "";
				while((line = raf.readLine()) != null) {
					raf.seek(vratiSe);
					System.out.println("FAKTURA: " + line);
					String[] split = line.split(",");
					if(split[0].equals(Enumeracija.Narudzbenica.toString()) || split[0].equals(Enumeracija.NarudzbenicaStavka.toString())) {
						lock.notifyAll();
						lock.wait();
					} else {
						if(split[0].equals(Enumeracija.Faktura.toString())) {
							listaFaktura.add(faktura.pretvoriStringUObjekat(line));
							vratiSe += line.length();//nema -1 zbog \n
						} else if(split[0].equals(Enumeracija.FakturaStavka.toString())) {
							listaFakturaStavka.add(fakturaStavka.pretvoriStringUObjekat(line));
							vratiSe += line.length();
						}
					}
				}
				lock.notifyAll();
			}
		} else {
			Narudzbenica narudzbenica = new Narudzbenica();
			NarudzbenicaStavka narudzbenicaStavka = new NarudzbenicaStavka();
			synchronized(lock) {
				String line = "";
				while((line = raf.readLine()) != null) {
					raf.seek(vratiSe);
					System.out.println("NARUDZBENICA: " + line);
					String[] split = line.split(",");
					if(split[0].equals(Enumeracija.Faktura.toString()) || split[0].equals(Enumeracija.FakturaStavka.toString())) {
						lock.notifyAll();
						lock.wait();
					} else {
						if(split[0].equals(Enumeracija.Narudzbenica.toString())) {
							listaNarudzbenica.add(narudzbenica.pretvoriStringUObjekat(line));
							vratiSe += line.length();
						} else if(split[0].equals(Enumeracija.NarudzbenicaStavka.toString())) {
							listaNarudzbenicaStavka.add(narudzbenicaStavka.pretvoriStringUObjekat(line));
							vratiSe += line.length();
						}
					}
				}
				lock.notifyAll();
			}
		}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}