import java.util.*;

public class Brod extends ObjektiVode {
	
	public Brod(String naziv) {
		super(naziv);
	}
	
	public void run() {
		while(x < Main.dimenzijaX && y < Main.dimenzijaY) {
			
			try {
				
				kreciSe();
			
				Thread.sleep(brzinaKretanja);//promjeniti
			} catch (InterruptedException ex) {
				
			}
			
		}
		
		Main.mapa[0][x][y - 1] = null; 
	}
	
	public void kreciSe() throws InterruptedException {
		
		int newX = x;
		int newY = y + 1;
			
		if(newY == Main.dimenzijaY) {
			x = newX;
			y = newY;
			return;
		}
			
		synchronized(Main.mapa) {
			if(Main.mapa[0][newX][newY] != null) {
				Main.mapa[0][newX][newY].interrupt();
					//Sudar dva broda
				System.out.println(this.toString() + " se sudario sa " + Main.mapa[0][newX][newY].toString() + ". Broj zrtava je " + (Main.mapa[0][newX][newY].getBrojOsoba() + this.getBrojOsoba()));
				Main.mapa[0][newX][newY].unisten();
				Main.mapa[0][x][y].unisten();
				Main.mapa[0][newX][newY] = null;
				Main.mapa[0][x][y] = null;
				throw new InterruptedException();
			} else {
				Main.mapa[0][newX][newY] = this;
				Main.mapa[0][x][y] = null;
				x = newX;
				y = newY;
			}
		}
	}
	
	public String toString() {
		return "BR";
	}
	
	public int getBrojOsoba() {
		return 0;
	}
}