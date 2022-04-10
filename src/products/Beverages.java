package products;

public class Beverages extends Product {
	
	public Beverages(String name, double price) {
		super(name, price);
	}
	
	public void refill() {
		System.out.printf("%s refilled!", getName());
	}

}
