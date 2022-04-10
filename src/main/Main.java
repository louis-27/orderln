package main;

import java.util.ArrayList;
import java.util.Scanner;

import orders.Cart;
import orders.Order;
import products.Beverages;
import products.Food;
import products.Product;
import users.Admin;
import users.Customer;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static Utils utils = new Utils();
	
	// Restaurant
	static final Admin admin = new Admin("admin", "admin123");
	static ArrayList<Product> menu = new ArrayList<>();
	static ArrayList<Customer> orders = new ArrayList<>();
	
	// Customer
	static ArrayList<Order> myCart = new ArrayList<>();

	public Main() {
		init();
		
		boolean isRunning = true;
		while (isRunning) {
			utils.clearScreen();
			
			utils.orderInScreen();
			
			System.out.println("1. Customer");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			
			int inp;
			do {				
				System.out.print("Continue as: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					customerMenu();
					break;
				case 2:
					adminMenu();
					break;
				case 3:
					utils.orderInScreen();
					isRunning = false;
					break;
			}
		}
	}
	
	public void init() {
		/**
		 * Insert dummy datas for menu & admin
		 * */
		
		menu.add(new Food("Nasi Padang", 20000));
		menu.add(new Food("Nasi Kuning", 18000));
		menu.add(new Food("Ayam Penyet", 21000));
		menu.add(new Food("Ayam Bakar", 22000));
		menu.add(new Food("Ramen", 25000));
		
		menu.add(new Beverages("Coffee", 10000));
		menu.add(new Beverages("Iced Coffee", 12000));
		menu.add(new Beverages("Tea", 9000));
		menu.add(new Beverages("Iced Tea", 11000));
		menu.add(new Beverages("Boba", 20000));
	}
	
	// 1
	public void customerMenu() {
		boolean isRunning = true;
		
		while (isRunning) {
			utils.clearScreen();
			
			printOrders();
			
			System.out.println("Welcome! What do you want to do?");
			System.out.println("1. Add Order");
			System.out.println("2. Cancel Order");
			System.out.println("3. Exit");
			
			int inp;
			do {
				System.out.print("Choose: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					addOrder();
					break;
				case 2:
					cancelOrder();
					break;
				case 3:
					isRunning = false;
					break;
			}
		}
	}
	
	public void adminMenu() {
		System.out.print("Enter password: ");
	}
	
	public void printOrders() {
		System.out.println("Orders");
		System.out.println("========================================================================");
		System.out.printf("| %-3s | %-44s | %-15s |\n", "No.", "Customer", "Total Price");
		System.out.println("========================================================================");
		
		int i = 0;
		for (Customer customer : orders) {
			Cart customerCart = customer.getCart();
			String customerName = customer.getName();
			double totalPrice = customerCart.getSubtotal();
			
			if (i > 0) System.out.println("|----------------------------------------------------------------------|");
			System.out.printf("| %-3s | %-44s | Rp.%-12s |\n", i + 1, customerName, totalPrice);
			System.out.println("|----------------------------------------------------------------------|");

			ArrayList<Order> customerOrders = customerCart.getOrders();
			System.out.printf("|\t| %-3s | %-25s | %-8s | %-15s |\n", "No.", "Name", "Quantity", "Price");
			System.out.println("|\t|==============================================================|");

			int j = 0;
			for (Order order : customerOrders) {
				String orderName = order.getName();
				int qty = order.getQty();
				double price = order.getPrice();
				
				System.out.printf("|\t| %-3s | %-25s | %-8s | Rp.%-12s |\n", j + 1, orderName, qty, price);
				j++;
			}
			i++;
		}
		System.out.println("========================================================================");
		System.out.println("\n");
	}
	
	// 2
	public void addOrder() {
		if (menu.isEmpty()) {
			System.out.println("There are no food. Try again later maybe ;)");
			utils.waitUser();
			return;
		}
		
		boolean isRunning = true;
		while (isRunning) {
			utils.clearScreen();
			
			printMenu();
			printCart();
			
			System.out.println("1. Add to Cart");
			System.out.println("2. Place Order");
			System.out.println("3. Go back");
			
			int inp;
			do {
				System.out.print("Choose: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					addToCart();
					break;
				case 2:
					placeOrder();
					break;
				case 3:
					isRunning = false;
					break;
			}
		}
	}
	
	public void cancelOrder() {
		if (orders.isEmpty()) {
			System.out.println("There are no orders yet.");
			utils.waitUser();
			return;
		}
		
		String name;
		do {
			System.out.print("Your name: ");
			name = scan.nextLine();
			
			if (!validateCustomer(name)) {
				System.out.printf("There are no orders under %s\n", name);
			} else {
				break;
			}
		} while (!validateCustomer(name));
		
		System.out.println("Order canceled");
		utils.waitUser();
	}
	
	public boolean validateCustomer(String name) {
		for (Customer customer : orders) {
			if (customer.getName().equalsIgnoreCase(name)) {
				orders.remove(customer);
				return true;
			}
		}
		
		return false;
	}
	
	public void printMenu() {
		System.out.println("Menu");
		System.out.println("=====================================================");
		System.out.printf("| %-3s | %-25s | %-15s |\n", "No.", "Food Name", "Food Price");
		System.out.println("=====================================================");

		for (int i = 0; i < menu.size(); i++) {
			String name = menu.get(i).getName();
			double price = menu.get(i).getPrice();
			
			System.out.printf("| %-3s | %-25s | Rp.%-12s |\n", i + 1, name, price);
		}
		
		System.out.println("=====================================================");
		
		System.out.println("\n");
	}
	
	public void printCart() {
		System.out.println("What's in your Cart");
		System.out.println("================================================================");
		System.out.printf("| %-3s | %-25s | %-8s | %-15s |\n", "No.", "Food Name", "Quantity", "Food Price");
		System.out.println("================================================================");
		
		if (myCart.isEmpty()) {
			System.out.printf("| %-60s |\n", "Your cart is empty");
		} else {
			for (int i = 0; i < myCart.size(); i++) {
				String name = myCart.get(i).getName();
				int qty = myCart.get(i).getQty();
				double price = myCart.get(i).getPrice();
				
				System.out.printf("| %-3s | %-25s | %-8d | Rp.%-12s |\n", i + 1, name, qty, price);
			}
		}
		
		System.out.println("================================================================");
		
		System.out.println("\n");
	}
	
	// 3
	public void addToCart() {
		printMenu();
		
		int foodNumber, qty;
		
		do {
			System.out.printf("Add Food Number [1 - %d]: ", menu.size());
			foodNumber = scan.nextInt();
			scan.nextLine();
		} while (!(foodNumber >= 1 && foodNumber <= menu.size()));
		
		do {
			System.out.print("Quantity: x ");
			qty = scan.nextInt();
			scan.nextLine();
		} while (!(qty > 0));
		
		Product chosen = menu.get(foodNumber - 1);
		myCart.add(new Order(chosen.getName(), chosen.getPrice(), qty));
		
		System.out.println("Added to Cart!");
		utils.waitUser();
	}
	
	public void placeOrder() {
		if (myCart.isEmpty()) {
			System.out.println("Your cart is empty. Add to your cart before placing an order.");
			utils.waitUser();
			return;
		}
		
		printCart();
		
		System.out.printf("Subtotal \t: %.2f\n\n", getSubtotal());
		
		String proceed;
		do {
			System.out.print("Proceed? [Yes | No] ");
			proceed = scan.nextLine();
		} while (!(proceed.equalsIgnoreCase("yes") || proceed.equalsIgnoreCase("no")));
		
		if (proceed.equalsIgnoreCase("yes")) {
			String orderAs;
			do {
				System.out.print("Order as: ");
				orderAs = scan.nextLine();
			} while (!(orderAs.length() > 0));
			
			/**
			 * Create a new copy of `myCart` called `newCart` so that when I
			 * clear `myCart`, `newCart` which is assigned to the orders list 
			 * doesn't get cleared.
			 * 
			 * For example:
			 * ```
			 * 	orders.add(new Customer(orderAs, new Cart(myCart)));
			 *	myCart.clear();
			 * ```
			 * By doing the example above, even if `myCart.clear()` was done 
			 * after assigning the `myCart` object to the Customer class, the
			 * assigned `myCart` object will also get cleared as it is referencing 
			 * the same object that is being cleared on `myCart.clear()`.
			 * */
			ArrayList<Order> newCart = new ArrayList<>(myCart);
			orders.add(new Customer(orderAs, new Cart(newCart)));
			myCart.clear();
			
			System.out.println("\nOrder has been placed and your cart has been cleared!");
			utils.waitUser();
		}
	}
	
	public double getSubtotal() {
		double sum = 0;
		
		for (Order order : myCart) {
			sum += (order.getQty() * order.getPrice());
		}
		
		return sum;
	}

	public static void main(String[] args) {
		new Main();
	}

}
