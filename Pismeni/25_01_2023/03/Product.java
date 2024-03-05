import java.util.*;

public class Product implements Comparator<Product>, Comparable<Product>{
	
	public int id, price, quantity;
	public String product_name, product_category, currency;
	
	public Product(int id, String product_name, String product_category, int price, String currency, int quantity){
		this.id = id;
		this.price = price;
		this.currency = currency;
		this.quantity = quantity;
		this.product_name = product_name;
		this.product_category = product_category;
	}
	
	@Override
	public String toString(){
		return id+"   "+product_name+"   "+product_category+"   "+price+"   "+currency+"   "+quantity;
	}
	
	public String getProductCategory(){
		return product_category;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getCurrency(){
		return currency;
	}
	
	@Override
	public int compare(Product o1, Product o2){
		return o1.product_name.compareTo(o2.product_name);
	}
	
	@Override
	public int compareTo(Product o){
		return this.product_name.compareTo(o.product_name);
	}
	
}