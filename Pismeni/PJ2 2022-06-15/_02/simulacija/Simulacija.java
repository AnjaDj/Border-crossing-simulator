package simulacija;
import predmeti.*;
import studenti.*;
import profesori.*;
import java.io.*;
import java.util.*;

public class Simulacija{
	
	public static void main(String[] args){
		
		File dir = new File(args[1]); 	
		
		if (dir.exists()){
			if(dir.isDirectory()){
				
				File[] studenti   = dir.listFiles(f -> f.getName().contains("student") ); 
				File[] predmeti   = dir.listFiles(f -> f.getName().contains("predmet") ); 
				File[] profesori  = dir.listFiles(f -> f.getName().contains("profesor") ); 
				
				List<Student> nizStudenata = new ArrayList<>();
				List<Profesor> nizProfesora = new ArrayList<>();
				
				for(int i = 0; i < studenti.length; i++){
					
					int index;
					String ime; 
					String prezime; 
					List<Integer> IDPredmeta = new ArrayList<>();
					
					try(BufferedReader br = new BufferedReader(new FileReader(studenti[i]))){
						
						String line = br.readLine();
						String[] parts = line.split(",");
						index = Integer.valueOf(parts[0]);	ime = parts[1]; 	prezime = parts[2];
						
						line = br.readLine();
						while(line != null){
							IDPredmeta.add(Integer.valueOf(line));
							line = br.readLine();
						}
						
					}catch(IOException e){
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					
					nizStudenata.add(new Student(index,ime,prezime,IDPredmeta));
					
				}
				
				for(int i = 0; i < profesori.length; i++){
					
					int JMB;
					String ime; 
					String prezime; 
					List<Integer> IDPredmeta = new ArrayList<>();
					
					try(BufferedReader br = new BufferedReader(new FileReader(profesori[i]))){
						
						String line = br.readLine();
						String[] parts = line.split(",");
						JMB = Integer.valueOf(parts[0]);	ime = parts[1]; 	prezime = parts[2];
						
						line = br.readLine();
						while(line != null){
							IDPredmeta.add(Integer.valueOf(line));
							line = br.readLine();
						}
						
					}catch(IOException e){
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					
					nizProfesora.add(new Profesor(JMB,ime,prezime,IDPredmeta));
					
				}
				
				for(int i = 0; i < predmeti.length; i++){
					
					int IDPredmeta;
					String nazivPredmeta;
					
					try(BufferedReader br = new BufferedReader(new FileReader(predmeti[i]))){
						
						String[] parts = br.readLine().split(",");
						IDPredmeta = Integer.valueOf(parts[0]);
						nazivPredmeta = parts[1];
						
					}catch(IOException e){
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					
					new File(dir.getAbsolutePath()+"/"+nazivPredmeta).mkdir();
					new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/studenti").mkdir();
					new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/profesori").mkdir();
					
					for(int j = 0; i < nizStudenata.size(); i++){
						
						if( (nizStudenata.get(j).getIDPredmeta()).contains(IDPredmeta) ){
							
							new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/studenti/"+nizStudenata.get(j).getIme()+"_"+nizStudenata.get(j).getIndex()).mkdir();
							
							try(ObjectOutputStream oos = new ObjectOutputStream(new  FileOutputStream( new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/studenti/"+nizStudenata.get(j).getIme()+"_"+nizStudenata.get(j).getIndex()+"/"+nizStudenata.get().getIme()+".txt") ))){
								oos.writeObject(nizStudenata.get(i));
							}catch(InterruptedException e){
								e.printStackTrace();
							}
							
						} 
						
					}
					
					for(int i = 0; i < nizProfesora.size(); i++){
						
						if( (nizProfesora.get(i).getIDPredmeta()).contains(IDPredmeta) ){
							
							new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/profesori/"+nizProfesora.get(i).getIme()+"_"+nizProfesora.get(i).getJMB()).mkdir();
							
							try(ObjectOutputStream oos = new ObjectOutputStream(new  FileOutputStream( new File(dir.getAbsolutePath()+"/"+nazivPredmeta+"/profesori/"+nizProfesora.get(i).getIme()+"_"+nizProfesora.get(i).getJMB()+"/"+nizProfesora.get(i).getIme()+".txt") ))){
								oos.writeObject(nizStudenata.get(i));
							}catch(InterruptedException e){
								e.printStackTrace();
							}
							
						} 
						
					}
					
				}
				
				
			}else System.out.println("Na specificiranoj putanji "+dir.getName()+" se ne nalazi direktorijum vec datoteka!"); 
		}else System.out.println("Na specificiranoj putanji "+dir.getName()+" se ne nalazi niti direktorijum niti datoteka!");
			
		
	}
	
}