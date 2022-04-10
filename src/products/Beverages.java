package products;

import java.util.Random;

public class Beverages extends Product implements Discountable {
	
	static Random rand = new Random();

	public Beverages(String name, double price) {
		super(name, price);
	}

	@Override
	public boolean isDiscounted() {
		int roll = rand.nextInt(10);
		return (roll < 4);
	}

}
