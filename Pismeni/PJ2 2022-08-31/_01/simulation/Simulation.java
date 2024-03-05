package simulation;
import restaurants.*;
import guests.*;
import java.util.*;


public class Simulation{
	
	private static List<Table> createTables(){
		
		List<Table> tables = new ArrayList<>();
		
		for(int i = 1; i <= 3; i++){
			
			tables.add(new Table(true,2));
			tables.add(new Table(true,4));
			tables.add(new Table(true,6));
		}
		for(int i = 1; i <= 10; i++){
			
			tables.add(new Table(false,2));
			tables.add(new Table(false,4));
			tables.add(new Table(false,6));	
		}
		
		return tables;
	}
	
	private static List<String> createDrinkCard(){
		
		List<String> drinkCard = new ArrayList<>();
		
		drinkCard.add("Domaca kafa,2.50");
		drinkCard.add("Esspresso kafa,1.50");
		drinkCard.add("Caj kamilica,2.00");
		drinkCard.add("Caj menta,2.00");
		drinkCard.add("Caj nana,2.00");
		drinkCard.add("Caj vocni,2.00");
		drinkCard.add("Limunada,4.00");
		drinkCard.add("Cijedjena narandza,5.00");
		drinkCard.add("Cijedjeni grejp,5.00");
		drinkCard.add("Bijelo vino,7.00");
		
		return drinkCard;
		
	}
	
	private static List<String> createFoodCard(boolean vege){
		
		List<String> foodCard = new ArrayList<>();
		
		if (vege == true) {
			foodCard.add("Rizoto lignje,20.00,V");
			foodCard.add("Rizoto povrce,20.00,V");
			foodCard.add("Vegeterijanski hamburger,20.00,V");
		}
		foodCard.add("Teletina u vlastitom sosu,30.00,NV");
		foodCard.add("Svinjetina u vlastitom sosu,25.00,NV");
		foodCard.add("Piletina sa gljivama,15.00,NV");
		foodCard.add("Piletina sa povrcem,15.00,NV");
		foodCard.add("Pilece krpice,15.00,NV");
		foodCard.add("Svinjska snicla,35.00,NV");
		foodCard.add("Teleca snicla,35.00,NV");
		foodCard.add("Pohovana palacinka,15.00,NV");
		foodCard.add("Karadjordjeva snicla,35.00,NV");
		
		return foodCard;
	}
	
	
	public static void main(String[] args){
		
		Random rand = new Random();
		
		Queue<Group> queue = new LinkedList<>();
			for(int i = 1; i <= 10; i++){
				queue.offer(new Group( rand.nextInt(6)+1 , true , false));
				queue.offer(new Group( rand.nextInt(6)+1 , true , false));
				queue.offer(new Group( rand.nextInt(6)+1 , false, true));
				queue.offer(new Group( rand.nextInt(6)+1 , false, true));
				queue.offer(new Group( rand.nextInt(6)+1 , false, false));
			}
		
		Restaurant r1 = new Restaurant("Kazamat",createTables(),new Menu(createDrinkCard(),createFoodCard(false)),queue);
		Restaurant r2 = new Restaurant("Lukijan",createTables(),new Menu(createDrinkCard(),createFoodCard(true)),queue);
		Restaurant r3 = new Restaurant("Integra",createTables(),new Menu(createDrinkCard(),createFoodCard(true)),queue);
		Restaurant r4 = new Restaurant("Mala Stanica",createTables(),new Menu(createDrinkCard(),createFoodCard(true)),queue);
		Restaurant r5 = new Restaurant("Corso",createTables(),new Menu(createDrinkCard(),createFoodCard(true)),queue);
		
		r1.start();	r2.start();	r3.start();	r4.start();	r5.start();
			
		
		
		
	}
}