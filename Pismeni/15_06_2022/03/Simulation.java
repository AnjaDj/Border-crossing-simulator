import java.util.*;

public class Simulation{
	
	public static void main(String[] args){
		
		List<Data<Integer>> list1 = new ArrayList<>();
			list1.add(new ExampleData1<Integer>("RED", 14));
			list1.add(new ExampleData1<Integer>("BLUE", 4));
			list1.add(new ExampleData1<Integer>("WHITE", 1));
		List<Data<Integer>> list2 = new ArrayList<>();
			list2.add(new ExampleData2<Integer>("SMALL",1));
			list2.add(new ExampleData2<Integer>("MEDIUM",2));
			list2.add(new ExampleData2<Integer>("LARGE",3));
			
		Util.processData( Arrays.asList( data -> data.getValue() < 10 , data -> data.getValue() < 5 ) , data -> System.out.println(data), list1,list2 );	
	}
}