import java.util.*;

public class Sekcija{
	
	public Integer id;
	public List<Polica> police;
	
	public Sekcija(Integer id, List<Polica> police){
		this.police = police;
		this.id = id;
	}
}