package vehicles;

import java.util.Random;

public class Bike extends Vehicle implements Maintenance {
	
	static Random rand = new Random();

	@Override
	public boolean isMaintenance() {
		int luck = rand.nextInt(10);
		return (luck < 4); // Jika 0 - 3 maka motor sedang maintenance
	}

}
