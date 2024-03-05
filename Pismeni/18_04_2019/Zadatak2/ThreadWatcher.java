import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.*;

public class ThreadWatcher extends Thread {
	
	public ArrayList<String> files;
	public String putanja;
	public String putanjaKopiranja;
	public boolean radi = true;
	
	public ThreadWatcher(String putanja, String putanjaKopiranja) {
		files = new ArrayList<String>();
		try {Files.list(Paths.get(putanja)).forEach(t -> files.add(t.toString())); } catch (Exception ex) {ex.printStackTrace();}
		this.putanja = putanja;
		this.putanjaKopiranja = putanjaKopiranja;
	}
	
	@Override
	public void run() {
		while(radi) {
			ArrayList<String> newFiles = new ArrayList<String>();
			try {
				if(Files.list(Paths.get(putanja)).count() > 0) {
					Files.list(Paths.get(putanja)).forEach(t -> newFiles.add(t.toString())); 
				} else {
					continue;
				} 
			} catch (Exception ex) {
				ex.printStackTrace(); 
			}
			newFiles.removeAll(files);
			if(newFiles != null) {
				for(String fajlovi : newFiles) {
					try {
						FileInputStream fis = new FileInputStream(new File(fajlovi));
						byte bajt = (byte)fis.read();
						if(bajt < 0) {
							//Umjesto fajl treba splitovati fajlovi i uzeti zadnji dio od /
							FileOutputStream fos = new FileOutputStream(new File(putanjaKopiranja + File.separator + "fajl"));
							fos.write(bajt);
							fos.flush();
							fos.close();
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			files = new ArrayList<String>();
			try {Files.list(Paths.get(putanja)).forEach(t -> files.add(t.toString())); } catch (Exception ex) {ex.printStackTrace();}
			try{
				Thread.sleep(100);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}