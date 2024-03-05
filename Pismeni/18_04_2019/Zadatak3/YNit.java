import java.util.*;

public class YNit extends Thread {
	
	public static Object[][] mapa;
	
	public YNit(int x, int y) {
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
				y++;
				brojPredjenihPolja++;
				if(y >= 20) {
					break;
				} else {
					if(mapa[x][y] != null) {
						System.out.println("Doslo je do konflikta.");
						synchronized(Main.konfliktiNaMapi) {
							Main.konfliktiNaMapi++;
						}
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x][y-1] = null;
						}
					} else {
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x][y-1] = null;
						}
					}
				}
			} else { // u lijevo
				y--;
				brojPredjenihPolja++;
				if(y < 0) {
					break;
				} else {
					if(mapa[x][y] != null) {
						System.out.println("Doslo je do konflikta.");
						synchronized(Main.konfliktiNaMapi) {
							Main.konfliktiNaMapi++;
						}
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x][y+1] = null;
						}
					} else {
						synchronized(mapa) {
							mapa[x][y] = this;
							mapa[x][y+1] = null;
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