package terminali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Kontrola {

    public static boolean pauza = false;
    public static Object lock = new Object();
    public static final File file = new File("kontrola.txt");

    public static boolean PT_ableToWork(int ID){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            String line = br.readLine();
            while(line != null){

                if (line.equals("PT_"+ID+":RADI")) return true;
                if (line.equals("PT_"+ID+":NE RADI")) return false;
                line = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean CT_ableToWork(int ID){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            String line = br.readLine();
            while(line != null){

                if (line.equals("CT_"+ID+":RADI")) return true;
                if (line.equals("CT_"+ID+":NE RADI")) return false;
                line = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

}
