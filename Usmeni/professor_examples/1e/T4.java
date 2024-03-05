import java.util.*;
import java.util.stream.*;
public class T4{
	public static void main(String... args){
		
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(10,10);
		map.put(11,11);
		map.put(new Integer(10),100);
		map.put(11,111);
		map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
	}
}

