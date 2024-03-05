
public class Util<E extends Interfejs>{
	
	private List<E[]> list;	// lista nizova koji sadrze elemente tipa E
	
	public Util(List<E[]> list){
		this.list = list;
	}
	
	public RezultantnaKlasa[] metoda(Status status){
		
		List<RezultantnaKlasa> result = new ArrayList<>();
		List<E> temp = new ArrayList<>();
		
		for(int i = 0; i < list.size();i++){
			
			E[] niz = list.get(i);
			
			for(int j = 0; j < niz.length; i++)
				temp.add(niz[j]);
		}
		
		Map<String,Double> map = temp.stream()	// Stream<E>
										.filter(e -> (e.getStatus()==status)) 	// Stream<E>
										.collect(Collectors.groupingBy(e -> e.getName(),Collectors.summingDouble(e -> e.getValue())));
										
		
		map.forEach((k,v) -> { result.add(new RezultantnaKlasa(k,v)); });
		
		return result.toArray();
		
	}
	
}

