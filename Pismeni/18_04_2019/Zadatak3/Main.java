import java.util.*;
import java.io.*;

public class Main {
	
	public static final String DATOTEKA = "Datoteka";
	public static Object[][] mapa = new Object[20][20];
	public static Integer konfliktiNaMapi = 0;
	
	public static void main(String[] args) {
		
		ArrayList<Thread> tredovi = new ArrayList<Thread>();
		Random random = new Random();
		XNit.mapa = mapa;
		YNit.mapa = mapa;
		CrossNit.mapa = mapa;
		
		XNit[] xniti = new XNit[10];
		for(int i=0;i<10;i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			while(mapa[x][y] != null) {
				x = random.nextInt(20);
				y = random.nextInt(20);
			}
			xniti[i] = new XNit(x,y);
			tredovi.add(xniti[i]);
		}
		
		YNit[] yniti = new YNit[10];
		for(int i=0;i<10;i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			while(mapa[x][y] != null) {
				x = random.nextInt(20);
				y = random.nextInt(20);
			}
			yniti[i] = new YNit(x,y);
			tredovi.add(yniti[i]);
		}
		
		CrossNit[] crossniti = new CrossNit[10];
		for(int i=0;i<10;i++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			while(mapa[x][y] != null) {
				x = random.nextInt(20);
				y = random.nextInt(20);
			}
			crossniti[i] = new CrossNit(x,y);
			tredovi.add(crossniti[i]);
		}
		
		for(Thread thread : tredovi) {
			thread.start();
		}
		
		while(true) {
			try {			
				PrintWriter pw = new PrintWriter(DATOTEKA + new Date().getTime() + ".txt");
				synchronized(mapa) {
					for(int i=0;i<20;i++) {
						for(int j=0;j<20;j++) {
							if(mapa[i][j] == null) {
								pw.print("O ");
							} else {
								if(mapa[i][j] instanceof XNit) {
									pw.print("X ");
								} else if(mapa[i][j] instanceof YNit) {
									pw.print("Y ");
								} else {
									pw.print("C ");
								}
							}
						}
						pw.println();
					}
				}
				pw.flush();
				pw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
			if(tredovi.stream().filter(t -> Thread.State.TERMINATED.equals(t.getState())).count() == 0) {
				break;
			}
			
		}
		
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Broj konflikta je: " + konfliktiNaMapi);
		System.out.println("----------------------------------------------------------------------------------");
		
	}
	
}