import java.util.*;

public class Polica{
	
	public Integer id;
	public List<Knjiga> knjigeNaPolici;
	
	public Polica(Integer id, List<Knjiga> knjigeNaPolici){
		this.knjigeNaPolici = knjigeNaPolici;
		this.id = id;
	}
	
}