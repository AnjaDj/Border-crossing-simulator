import java.util.*;
import java.util.function.*;

public class Skladiste<T extends Interfejs>{
	
	private PriorityQueue<T> queue = new PriorityQueue<>();
	
	public void offer(T t){
		queue.offer(t);
	}
	
	public T poll(){
		return queue.poll();
	}
	
	public void print(){
		for(T t : queue)
			System.out.println(t);
	}
	
	public void search(List<Predicate<T>> conditions){
		
		for(T t : queue){
			
			int temp = 0;
			
			for(Predicate<T> condition : conditions){
				if (condition.test(t)) temp++;
			}
			
			if (temp == conditions.size()) System.out.println(t);
		}
		
	}
	
	public void action(){
		for(T t : queue)
			t.action();
	}
	
}