import java.util.*;

public class XNit extends Thread {
	
	public static Object[][] mapa;
	
	public XNit(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;
	public int brojPredjenihPolja = 0;
	public long vrijeme;
	
	@Override
	public void run() {
		vrijeme = new Date().getTime();
		Random random = new Random();
		while(true) {
			if(x < 0 || x >= 20 || y < 0 || y >= 20) {
				break;
			}
			int smjerKretanja = random.nextInt(2);
			if(smjerKretanja == 0) { // u desno
				x++;
				brojPredjenihPolja++;
				if(x >= 20) {
					break;
				} else {
					if(mapa[x][y] != null) {
						System.out.println("Doslo je do konflikta.");
						synchronized(Main.konfliktiNaMapi) {
							Main.konfliktiNaMapi++;
						}
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x-1][y] = null;
						}
					} else {
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x-1][y] = null;
						}
					}
				}
			} else { // u lijevo
				x--;
				brojPredjenihPolja++;
				if(x < 0) {
					break;
				} else {
					if(mapa[x][y] != null) {
						System.out.println("Doslo je do konflikta.");
						synchronized(Main.konfliktiNaMapi) {
							Main.konfliktiNaMapi++;
						}
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x+1][y] = null;
						}
					} else {
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x+1][y] = null;
						}
					}
				}
			}
			
			try {
				Thread.sleep(500 + random.nextInt(1500));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		vrijeme = (new Date().getTime() - vrijeme) / 1000;
		System.out.println(this + " je presla: " + brojPredjenihPolja + " polja. Vrijeme kretanja: " + vrijeme);
	}
	
	@Override
	public String toString() {
		return new String("");
	}
}