import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class Main {
	
	public static final String NAZIV_DATOTEKE = "Datoteka";
	public static final String EKSTENZIJA_DATOTEKE = ".bin";
	
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Broj parametara nevalidan!");
		}
		int n;
		String putanja = args[1];
		String putanjaZaKopiranje = args[2];
		try {
			n = Integer.parseInt(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		File datoteka = new File(putanja);
		if(!datoteka.exists()) {
			datoteka.mkdir();
		}
		
		File datotekaZaKopiranje = new File(putanjaZaKopiranje);
		if(!datotekaZaKopiranje.exists()) {
			datotekaZaKopiranje.mkdir();
		}
		
		ThreadWatcher watcher = new ThreadWatcher(putanja,putanjaZaKopiranje);
		watcher.start();
		
		Random random = new Random();
		
		for(int i=0;i<n;i++) {
			File file = new File(putanja + File.separator + NAZIV_DATOTEKE + i + EKSTENZIJA_DATOTEKE);
			try {
				FileOutputStream fis = new FileOutputStream(file);
				byte bajt = (byte)(random.nextInt() % 256);
				fis.write(bajt);
				fis.flush();
				fis.close();
				
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		watcher.radi = false;
	} 
}

