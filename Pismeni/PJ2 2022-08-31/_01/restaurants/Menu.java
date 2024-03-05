package restaurants;
import java.util.List;

public class Menu{
	
	private List<String> drinkCard, foodCard;		// bijelo vino,6.00          piletina u sopstvenom sosu,19.00,NV
	
	public Menu(List<String> drinkCard, List<String> foodCard){
		
		this.drinkCard = drinkCard;
		this.foodCard = foodCard;
	}
	
	public List<String> getDrinkCard(){
		
		return drinkCard;
	}
	
	public List<String> getFoodCard(){
		
		return foodCard;
	}
	
	public boolean hasVegeterianFood(){
		
		for(int i = 0; i < foodCard.size(); i++){
			
			String[] parts = foodCard.get(i).split(",");
			
			if( "V".equals(parts[2]) ) return true;
			
		}
		return false;
	}
	
	
	
}