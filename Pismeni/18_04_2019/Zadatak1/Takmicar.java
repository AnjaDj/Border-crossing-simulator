import java.util.*;
import java.util.function.*;

public class Takmicar extends Thread{
	
	public int pozicija = 0;
	public int brojBodova = 0;
	public long vrijeme;
	public int brojPrepreka = 0;
	public Object[] mapa;
	
	public Takmicar(Object[] mapa) {
		this.mapa = mapa;
	}
	
	public void statistika(ArrayList<Consumer<Takmicar>> predikati) {
		for(Consumer<Takmicar> consumer : predikati) {
			consumer.accept(this);
		}
	}
	
	@Override
	public void run() {
		vrijeme = new Date().getTime();
		while(pozicija < 100) {
			if(pozicija >= 100) {
				break;
			}
			synchronized(mapa) {//Ako ovog nema i ako neko skloni nesto sa polja nastane NullPointerException npr. neko pokupi bodove prije nekog drugog
				if(mapa[pozicija] instanceof Voda) {
					if(this instanceof SavladajVoda) {
						((SavladajVoda) this).savladajVodu();
						brojBodova++;
						brojPrepreka++;
					} else {
						((Voda) mapa[pozicija]).metoda();
						brojBodova -= ((Voda) mapa[pozicija]).bodovi;
						try {
							Thread.sleep(((Voda) mapa[pozicija]).bodovi * 1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				} else if(mapa[pozicija] instanceof Vatra) {
					if(this instanceof SavladajVatra) {
						((SavladajVatra) this).savladajVatru();
						brojBodova++;
						brojPrepreka++;
					} else {
						((Vatra) mapa[pozicija]).metoda();
						brojBodova -= ((Vatra) mapa[pozicija]).bodovi;
						try {
							Thread.sleep(((Vatra) mapa[pozicija]).bodovi * 1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				} else if(mapa[pozicija] instanceof Stijena) {
					if(this instanceof SavladajStijena) {
						((SavladajStijena) this).savladajStijenu();
						brojBodova++;
						brojPrepreka++;
					} else {
						((Stijena) mapa[pozicija]).metoda();
						brojBodova -= ((Stijena) mapa[pozicija]).bodovi;
						try {
							Thread.sleep(((Stijena) mapa[pozicija]).bodovi * 1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				} else if(mapa[pozicija] instanceof Bodovi) {
					System.out.println("Bodovi na polju.");
					brojBodova += ((Bodovi) mapa[pozicija]).vrijednost;
					mapa[pozicija] = null;
				} else {
					brojBodova++;
				}
				if(this instanceof Pjesak) {
					pozicija += 1;
				} else if (this instanceof Vozac) {
					pozicija += 3;
				} else if (this instanceof Pilot) {
					pozicija += 5;
				}
			}
		}
		vrijeme = new Date().getTime() - vrijeme;
		vrijeme = vrijeme / 1000;
		System.out.println(this + "je zavrsio takmicenje.");
	}
	
	@Override
	public String toString() {
		return "";
	}
}