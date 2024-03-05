package pj2.pj2projektni.incident;
import pj2.pj2projektni.putnici.*;
import pj2.pj2projektni.vozila.*;
import pj2.pj2projektni.simulacija.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Incident implements java.io.Serializable {

    private String dodatanOpis;
    private List<Putnik> izbaceniPutnici;
    private Vozilo vozilo;

    private static int COUNTER = 0;
    private static Object lock = new Object();
    private static String pathname = "incidenti"+File.separator+"incidenti_"+Simulacija.START_TIME;
    private static File file = new File(pathname);
    private static ObjectOutputStream oos;
    static {
        file.mkdir();
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(pathname+File.separator +"incidenti_S.txt"), true));
        }catch (IOException e) {
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }

    public Incident(Vozilo vozilo,List<Putnik> izbaceniPutnici,String dodatanOpis){

        this.vozilo = vozilo;
        if (izbaceniPutnici != null){
            this.izbaceniPutnici = new ArrayList<>();
             this.izbaceniPutnici.addAll(izbaceniPutnici);
        }else
            this.izbaceniPutnici = null;
        this.dodatanOpis = dodatanOpis;
    }

    public List<Putnik> getIzbaceniPutnici() {
        return this.izbaceniPutnici;
    }

    public void evidentiranjeIncidenta(){

        synchronized (lock) {
            try{
                COUNTER++;
                oos.writeObject(this);
            } catch (IOException e) {
                Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
            }

            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                                        new File(pathname+File.separator+"incidenti_T.txt"), true)))){
                pw.println(new String(COUNTER+"_"+dodatanOpis));
            }catch (IOException e){
                Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
            }
        }
    }

    @Override
    public String toString(){
        return vozilo+" -> "+dodatanOpis;
    }
    public static int getCounter(){return COUNTER;}
}









