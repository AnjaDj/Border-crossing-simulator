package pj2.pj2projektni.terminali;
import pj2.pj2projektni.simulacija.Simulacija;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWatcher extends Thread {
	private List<CarinskiTerminal> carinskiTerminali;
	private List<PolicijskiTerminal> policijskiTerminali;
	
	public FileWatcher(List<CarinskiTerminal> carinskiTerminali, List<PolicijskiTerminal> policijskiTerminali) {
		this.setDaemon(true);
		this.carinskiTerminali = carinskiTerminali;
		this.policijskiTerminali = policijskiTerminali;
	}

	@Override
	public void run()
	{
		try {
			WatchService service = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get(Kontrola.KONTROLA_FOLDER_PUTANJA);
			dir.register(service, ENTRY_MODIFY);
			while (true)
			{
				WatchKey key;
				try {
					key = service.take();
				} catch (InterruptedException e) {
					Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
					return;
				}
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					Path file = ((WatchEvent<Path>) event).context();
					if (kind.equals(ENTRY_MODIFY) && file.endsWith(Kontrola.KONTROLA_PUTANJA)) {
						Files.readAllLines(file).stream().forEach(System.out::println);
						for (CarinskiTerminal ct : carinskiTerminali) {
							if (Kontrola.CT_ableToWork(ct.getID()) && !ct.isRadi()) {
								synchronized (ct) {
									ct.notify();
								}
							}
							else if (!Kontrola.CT_ableToWork(ct.getID()) && ct.isRadi()) {
								ct.interrupt();
							}
						}
						for (PolicijskiTerminal pt : policijskiTerminali) {
							if (Kontrola.PT_ableToWork(pt.getID()) && !pt.isRadi()) {
								System.out.println("PT " + pt.getID() + " radi");
								synchronized (pt) {
									pt.notify();
								}
							}
							else if (!Kontrola.PT_ableToWork(pt.getID()) && pt.isRadi()) {
								pt.interrupt();
							}
						}
					}
				}
				boolean valid = key.reset();
				if (!valid)
					return;
			}
		} catch (IOException e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}
}
