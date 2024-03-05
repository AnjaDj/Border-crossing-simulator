import java.util.*;

public class Podmornica extends ObjektiVode {

	public Podmornica(String naziv) {
		super(naziv);
	}
	
	public void run() {
		
	}
	
	public void kreciSe() throws InterruptedException {
		int newX = x + 1;
		int newY = y;
			
		if(newX == Main.dimenzijaX) {
			x = newX;
			y = newY;
			return;
		}
			
		synchronized(Main.mapa) {
			if(Main.mapa[1][newX][newY] != null) {
				Main.mapa[1][newX][newY].interrupt();
					//Sudar dvije podmornice
				System.out.println(this.toString() + " se sudario sa " + Main.mapa[1][newX][newY].toString() + ". Broj zrtava je " + (Main.mapa[1][newX][newY].getBrojOsoba() + this.getBrojOsoba()));
				Main.mapa[1][newX][newY] = null;
				Main.mapa[1][x][y] = null;
				throw new InterruptedException();
			} else {
				Main.mapa[1][newX][newY] = this;
				Main.mapa[1][x][y] = null;
				x = newX;
				y = newY;
			}
		}
	}
	
	public String toString() {
		return "PO";
	}
	
	public int getBrojOsoba() {
		return 0;
	}
	
}