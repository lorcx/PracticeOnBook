package think_in_java;

import java.util.ArrayList;
import java.util.Random;

public class Product {
	private final int id;
	private String description;//描述
	private double price;
	
	public Product(int id, String description, double price) {
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public String toString() {
		return "id:"+id+"description:"+description+"price:"+price;
	}
	
	public void changePrice(double change){
		price += change;
	}
	
	public static Generator<Product> generator = new Generator<Product>(){
		private Random rn = new Random(47);
		public Product next(){
			return new Product(rn.nextInt(1000),"Test", Math.round(rn.nextDouble() * 1000.0) + 0.99);
		}
	};
}

/**
 * shelf架子
 */
class Shelf extends ArrayList<Product> {
	public Shelf(int nProducts) {

		
	}
}

interface Generator<T>{

	T next();}
