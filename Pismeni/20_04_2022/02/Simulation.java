import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Simulation{
	
	public static void main(String[] args){
		
		I[] array1 = new A[10];
		I[] array2 = new B[10];
		I[] array3 = new C[10];
		
		int i = 0;
		
		while( i < 9 ){
			array1[i]   = new A(Status.NEW, 10.0, "name_"+(i+1));
			array1[i+1] = new A(Status.PROCESSING, 10.0, "name_"+(i+2));
			array1[i+2] = new A(Status.DONE, 10.0, "name_"+(i+3));
			
			array2[i]   = new B(Status.NEW, 10.0, "name_"+(i+1));
			array2[i+1] = new B(Status.PROCESSING, 10.0, "name_"+(i+2));
			array2[i+2] = new B(Status.DONE, 10.0, "name_"+(i+3));
			
			array3[i]   = new C(Status.NEW, 10.0, "name_"+(i+1));
			array3[i+1] = new C(Status.PROCESSING, 10.0, "name_"+(i+2));
			array3[i+2] = new C(Status.DONE, 10.0, "name_"+(i+3));
			
			i += 3;
		}
		array1[9]   = new A(Status.NEW, 10.0, "name_10");
		array2[9]   = new B(Status.NEW, 10.0, "name_10");
		array3[9]   = new C(Status.NEW, 10.0, "name_10");
		
		D[] result = reduce(Status.DONE, array1, array2, array3);
		
		for(int j = 0; j < result.length; j++)
			System.out.println(result[j]);
		
	}
	
	static<T extends I> D[] reduce(Status status, T[]... arrays){
		
		List<T> temp_result = new ArrayList<>();
		System.out.println("Number of varargs = "+arrays.length);
		
		for(T[] array : arrays)
			temp_result.addAll(Arrays.asList(array));
		
		Map<String, Double> reduced = temp_result.stream()
  										         .filter( t -> t.getStatus() == status )
										         .collect(Collectors.groupingBy( I::getName , Collectors.summingDouble(I::getValue) ));
				
		List<D> result = new ArrayList<>();
		
		reduced.forEach( (name, value) -> {
											result.add(new D(value, name));
		} );
			
		return result.toArray(new D[0]);
	} 
}