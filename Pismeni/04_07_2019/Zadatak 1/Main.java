import java.util.*;
import java.io.*;

public class Main {
	
	public static int dimenzijaX = 8;
	public static int dimenzijaY = 8;
	public static ObjektiVode[][][] mapa = new ObjektiVode[2][dimenzijaX][dimenzijaY];
	
	public static ArrayList<ObjektiVode> listaObjekataVode = new ArrayList<ObjektiVode>();
	
	public static void main(String[] args) {
		VojniBrod vojniBrod1 = new VojniBrod("VojniBrod1");
		VojniBrod vojniBrod2 = new VojniBrod("VojniBrod2");
		listaObjekataVode.add(vojniBrod1);
		listaObjekataVode.add(vojniBrod2);
		
		Kruzer kruzer1 = new Kruzer("Kruzer1");
		Kruzer kruzer2 = new Kruzer("Kruzer2");
		listaObjekataVode.add(kruzer1);
		listaObjekataVode.add(kruzer2);
		
		Tanker tanker1 = new Tanker("Tanker1");
		Tanker tanker2 = new Tanker("Tanker2");
		listaObjekataVode.add(tanker1);
		listaObjekataVode.add(tanker2);
		
		postaviKoordinateBroda(vojniBrod1);
		postaviKoordinateBroda(vojniBrod2);
		postaviKoordinateBroda(kruzer1);
		postaviKoordinateBroda(kruzer2);
		postaviKoordinateBroda(tanker1);
		postaviKoordinateBroda(tanker2);
		
		CivilnaPodmornica civilnaPodmornica1 = new CivilnaPodmornica("CivilnaPodmornica1");
		CivilnaPodmornica civilnaPodmornica2 = new CivilnaPodmornica("CivilnaPodmornica2");
		listaObjekataVode.add(civilnaPodmornica1);
		listaObjekataVode.add(civilnaPodmornica2);
		
		VojnaPodmornica vojnaPodmornica1 = new VojnaPodmornica("VojnaPodmornica1");
		VojnaPodmornica vojnaPodmornica2 = new VojnaPodmornica("VojnaPodmornica2");
		listaObjekataVode.add(vojnaPodmornica1);
		listaObjekataVode.add(vojnaPodmornica2);
		
		postaviKoordinatePodmornice(civilnaPodmornica1);
		postaviKoordinatePodmornice(civilnaPodmornica2);
		postaviKoordinatePodmornice(vojnaPodmornica1);
		postaviKoordinatePodmornice(vojnaPodmornica2);
		
		
		//Simulacija
		for(int i=0;i<listaObjekataVode.size();i++) {
			listaObjekataVode.get(i).start();
		}
		
		Thread prikazMape = new Thread(() -> {
			boolean kraj = false;
			while(!kraj) {
				kraj = true;
				try {
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("mapa.txt"))));
					
					pw.println("-------------------------------------------");
					pw.println("IZNAD VODE:");
					pw.println("-------------------------------------------");
					synchronized(mapa) {
						for(int i=0;i<dimenzijaX;i++) {
							for(int j=0;j<dimenzijaY;j++) {
								if(mapa[0][i][j] != null) {
									pw.print(mapa[0][i][j].toString() + " ");
									kraj = false;
								} else {
									pw.print("  " + " ");
								}
							}
							pw.println();
						}
					}
					pw.println("-------------------------------------------");
					pw.println("ISPOD VODE:");
					pw.println("-------------------------------------------");
					synchronized(mapa) {
						for(int i=0;i<dimenzijaX;i++) {
							for(int j=0;j<dimenzijaY;j++) {
								if(mapa[1][i][j] != null) {
									pw.print(mapa[1][i][j].toString() + " ");
									kraj = false;
								} else {
									pw.print("  " + " ");
								}
							}
							pw.println();
						}
					}
					pw.println("-------------------------------------------");
					
					pw.close();
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		prikazMape.start();
		
		try {
			for(int i=0;i<listaObjekataVode.size();i++) {
				listaObjekataVode.get(i).join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void postaviKoordinateBroda(Brod brod) {
		Random random = new Random();
		int x = random.nextInt(dimenzijaX);
		int y = 0;
		
		while(mapa[0][x][y] != null) {
			x = random.nextInt(dimenzijaX);
		}
		
		mapa[0][x][y] = brod;
		brod.setKoordinate(x,y);
	}
	
	public static void postaviKoordinatePodmornice(Podmornica podmornica) {
		Random random = new Random();
		int x = 0;
		int y = random.nextInt(dimenzijaY);
		
		while(mapa[1][x][y] != null) {
			y = random.nextInt(dimenzijaY);
		}
		
		mapa[1][x][y] = podmornica;
		podmornica.setKoordinate(x,y);
	}
	
}