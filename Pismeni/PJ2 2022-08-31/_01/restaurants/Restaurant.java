package restaurants;
import java.util.*;
import java.io.*;
import guests.*;

public class Restaurant extends Thread{
	
	private Queue<Group> queue;
	private List<Table> tables;
	private String name;
	private Menu menu;
	
	public Restaurant(String name, List<Table> tables, Menu menu, Queue<Group> queue){
		
		this.menu = menu;
		this.name = name;
		this.queue = queue;
		this.tables = tables;
	}
	
	private boolean isThereAFreeTableForTheGroup(Group group){
		
		for(int i = 0; i < tables.size(); i++)
			if( tables.get(i).getIsFree() && tables.get(i).getTableFor() >= group.getNumberOfMembers() ) return true;
			
		return false;	
	}
	
	private boolean isThereAFreeTableForTheGroupOfSmokers(Group group){
		
		for(int i = 0; i < tables.size(); i++)
			if( tables.get(i).getIsFree() && tables.get(i).getTableFor() >= group.getNumberOfMembers() && tables.get(i).getTableForSmokers() ) return true;
			
		return false;	
	}
	
	private boolean isThereAFreeTableForTheGroupOfVegeterians(){
		
		 return menu.hasVegeterianFood();
	}
	
	private void writeInFile(String file, String message){
		
		File restaurantDIR = new File("restaurants"+File.separator+name);
		if(!restaurantDIR.exists())
			restaurantDIR.mkdir();
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("restaurants"+File.separator+name+File.separator+file))))){
			pw.println(message);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	private void takeTheTable(Group group){
		
		for(int i = 0; i < tables.size(); i++){
			
			Table t = tables.get(i);
			
			if ( t.getIsFree() && t.getTableFor() >= group.getNumberOfMembers() ){ // sto je slobodan i ima dovoljno stolica
				
				if (group.getHasSmokers() && t.getTableForSmokers()) { t.takeTheTable(); return; }
				if (!group.getHasSmokers()) { t.takeTheTable(); return; }
			} 
			
		}
		
	}
	
	@Override
	public void run(){
		
		while(!queue.isEmpty()){	// sve dok red nije prazan
			
			Group group = null;
			synchronized(queue){
				if(!queue.isEmpty()) group = queue.poll();	
			}
			
			if(group != null) {   	// obrada grupe
			
				// ------------------------------ PROVJERA DA LI GOSTI MOGU UCI U RESTORAN ------------------------------------
			
				if (isThereAFreeTableForTheGroup(group) == false){
					writeInFile("no_free_table.txt","There is no free table for the group at this restaurant.");
					continue;
				}
				
				if (group.getHasSmokers() && isThereAFreeTableForTheGroupOfSmokers(group) == false){
					writeInFile("no_free_table_for_smokers.txt","There is no free table for the group of smokers at this restaurant.");
					continue;
				} 
				
				if (group.getHasVegeterians() && isThereAFreeTableForTheGroupOfVegeterians() == false){
					writeInFile("no_free_table_for_vegeterians.txt","There is no free table for the group of vegeterians at this restaurant.");
					continue;
				} 
				
				// ---------------------------------------- USLUZIVANJE GOSTIJU -----------------------------------------------
				
					takeTheTable(group); // grupa sjeda za sto	
					
					Guest[] membersOfAGroup = group.getMembers();
					double check = 0;
					
					for(Guest g : membersOfAGroup){
						
						try{
							sleep(200);
							
							String selectedDrink, selectedFood;
							
							selectedDrink = menu.getDrinkCard().get(rand.nextInt(getDrinkCard().size()));
							selectedFood  = menu.getFoodCard().get(rand.nextInt(getFoodCard().size()));
							
							String[] p1 = selectedDrink.split(",");
							String[] p2 = selectedFood.split(",");
							
							check += Double.valueOf(p1[1]);
							check += Double.valueOf(p2[1]);
							
							System.out.println("Resroran "+name+" : Gost "+g+" iz grupe "+group+" bira : "+selectedDrink+" "+selectedFood);
							
						}catch(InterruptedException e){
							e.printStackTrace();
						}
				
					}
					
					System.out.println("Restran "+name+" racun za grupu "+group+" = "+check);
			
			}
			
		}
	}
}