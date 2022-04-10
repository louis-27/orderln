package orders;

import products.Product;

public class Order extends Product {
	
	private int qty;

	public Order(String name, double price, int qty) {
		super(name, price);
		this.qty = qty;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
