package vehicles;

import java.util.Random;

public class Car extends Vehicle implements Maintenance {
	
	static Random rand = new Random();
	
	@Override
	public boolean isMaintenance() {
		int luck = rand.nextInt(10);
		return (luck < 3); // Jika 0 - 2 maka mobil sedang maintenance 
	}

}
