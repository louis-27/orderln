package main;

import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static Utils utils = new Utils();

	public Main() {
		boolean isRunning = true;
		
		while (isRunning) {
			utils.clearScreen();
			
			utils.orderInScreen();
			
			System.out.println("1. Customer");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			
			int inp;
			do {				
				System.out.println("Continue as: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					isRunning = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
