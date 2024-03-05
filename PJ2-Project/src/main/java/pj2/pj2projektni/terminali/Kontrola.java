package pj2.pj2projektni.terminali;
import pj2.pj2projektni.simulacija.Simulacija;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kontrola {

    public static boolean PAUZA = false;
    public static boolean KRAJ = false;
    public static final Object LOCK = new Object();
    public static final String KONTROLA_FOLDER_PUTANJA = ".";
    public static final String KONTROLA_PUTANJA = "kontrola.txt";
    public static final File FILE = new File(KONTROLA_PUTANJA);

    public static boolean PT_ableToWork(int ID){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){

            String line = br.readLine();
            while(line != null){

                if (line.equals("PT_"+ID+":RADI")) return true;
                if (line.equals("PT_"+ID+":NE RADI")) return false;
                line = br.readLine();
            }
        }catch(IOException e){
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return false;
    }

    public static boolean CT_ableToWork(int ID){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))){

            String line = br.readLine();
            while(line != null){

                if (line.equals("CT_"+ID+":RADI")) return true;
                if (line.equals("CT_"+ID+":NE RADI")) return false;
                line = br.readLine();
            }
        }catch(IOException e){
            Logger.getLogger(Simulacija.class.getName()).log(Level.SEVERE, e.fillInStackTrace().toString());
        }
        return false;
    }

}
