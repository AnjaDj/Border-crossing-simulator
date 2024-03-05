import java.util.stream.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class StreamAPI3{
	
	public static final String FILE = "products.csv";
	
	private static List<Product> creatingListOfProducts(){
		
		List<Product> collectionOfProducts = new ArrayList<>();
		try{
			List<String> linesOfText = Files.readAllLines(Paths.get(FILE));	//There is no need for TWR within Files.readAllLines
			linesOfText.remove(0);											// Izbacivanje zaglavlja
			
			for(Iterator<String> it = linesOfText.iterator(); it.hasNext(); ){
				
				String line = it.next();
				
				if (line.contains("\"")){
					String[] temp = line.split("\"");
					
					if (temp.length == 3) {
						if (temp[0].lastIndexOf(',') == temp[0].indexOf(',')) {
							int id = Integer.valueOf( temp[0].substring(0, temp[0].length() - 1) );
							String product_name = temp[1];
							String[] rest = temp[2].split(",");
							String product_category = rest[1];
							int price = Integer.valueOf(rest[2]);
							String currency = rest[3];
							int quantity = Integer.valueOf(rest[4]);
							
							collectionOfProducts.add(new Product( id, product_name, product_category, price, currency, quantity ));
						}else{
							int id = Integer.valueOf( (temp[0].split(","))[0] );
							String product_name = (temp[0].split(","))[1];
							String product_category = temp[1];
							String[] rest = temp[2].split(",");
							int price = Integer.valueOf(rest[1]);
							String currency = rest[2];
							int quantity = Integer.valueOf(rest[3]);
							
							collectionOfProducts.add(new Product( id, product_name, product_category, price, currency, quantity ));
						}
					}else{
						int id = Integer.valueOf( temp[0].substring(0, temp[0].length() - 1) );
						String product_name = temp[1];
						String product_category = temp[3];
						int price = Integer.valueOf(temp[4].split(",")[1]);
						String currency = temp[4].split(",")[2];
						int quantity = Integer.valueOf(temp[4].split(",")[3]);
						
						collectionOfProducts.add(new Product( id, product_name, product_category, price, currency, quantity ));
					}
				}else{
					String[] temp = line.split(",");
					collectionOfProducts.add(new Product( Integer.valueOf(temp[0]), temp[1], temp[2], Integer.valueOf(temp[3]), temp[4], Integer.valueOf(temp[5]) ));
				}
		
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return collectionOfProducts;
	}
	
	public static void main(String[] args){
		
		List<Product> products = creatingListOfProducts();
			
		System.out.println("a. Ispisivanje ukupne kolicine proizvoda po svim kategorijama");
		Map<String, Integer> totalByCategory = products.stream().
			collect(Collectors.groupingBy(Product::getProductCategory, Collectors.summingInt(Product::getQuantity)));
		totalByCategory.forEach((key, val)-> System.out.println("   "+key+" --> "+val));
		
		
		System.out.print("b. Ispis ukupne kolicine proizvoda kojih na stanju ima izmedju 50 i 70 ");
		int total = products.stream()
									.filter(t -> (t.quantity > 50 && t.quantity < 70))
									.mapToInt(t -> t.quantity)
									.sum();
		System.out.println(total);	

		
		System.out.println("c. Prikazati proizvode iz svake kategorije sa najvisom i najnizom cijenom");
		System.out.println("MAX :");
		Map<String, Optional<Product>> max = products.stream()
						.collect(Collectors.groupingBy(Product::getProductCategory, 
													   Collectors.maxBy(Comparator.comparingInt(Product::getPrice))));
		max.forEach((key, val)-> System.out.println("   "+key+" --> "+val.get()));
		
		System.out.println("MIN :");
		Map<String, Optional<Product>> min = products.stream()
						.collect(Collectors.groupingBy(Product::getProductCategory, 
													   Collectors.minBy(Comparator.comparingInt(Product::getPrice))));
		min.forEach((key, val)-> System.out.println("   "+key+" --> "+val.get()));
		
		
		System.out.println("d. Sortirati i ispisati sve proizvode za zadatu vrijednost atributa currency");
		String currency = "RUB";
		products.stream()
				.filter(p -> currency.equals(p.getCurrency()))
				.sorted()
				.forEach(System.out::println);
				
				
		System.out.print("e. Pronaci sve proizvode cija je cijena data u EUR i prikazati ih sortirane po nazivu proizvoda");
		System.out.println(" sa cijenom konvertovanom u BAM ");
		
		products.stream()
				.filter(p -> "EUR".equals(p.getCurrency()))
				.sorted()
				.forEach(p -> {
								System.out.println(p.id+"   "+p.product_name+"   "+p.product_category+"   "+
												   (1.95*p.price)+"   "+"BAM"+"   "+p.quantity);
				});
		
	}
	
}