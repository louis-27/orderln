package users;

import orders.Cart;

public class Customer extends User {
	
	private Cart cart;

	public Customer(String name, Cart cart) {
		super(name);
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
