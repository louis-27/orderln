package orders;

import java.util.ArrayList;

public class Cart {

	private ArrayList<Order> orders;

	public Cart(ArrayList<Order> orders) {
		super();
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public double getSubtotal() {
		double sum = 0;
		
		for (Order order : orders) {
			sum += (order.getQty() * order.getPrice());
		}
		
		return sum;
	}
	
}
