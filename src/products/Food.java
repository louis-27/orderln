package products;

import java.util.Random;

public class Food extends Product implements Discountable {
	
	static Random rand = new Random();

	public Food(String name, double price) {
		super(name, price);
	}

	@Override
	public boolean isDiscounted() {
		int roll = rand.nextInt(10);
		return (roll < 2);
	}

}
