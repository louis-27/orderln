package users;

import vehicles.Bike;
import vehicles.Car;
import vehicles.Vehicle;

public class Driver extends User {
	
	private String status; // "Available" | "Unavailable"
	private Vehicle vehicle;

	public Driver(String name, String status, Vehicle vehicle) {
		super(name);
		this.status = status;
		this.vehicle = vehicle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public boolean isAvailable() {
		if (vehicle instanceof Car) {
			return ((Car) vehicle).isMaintenance();
		} else if (vehicle instanceof Bike) {
			return ((Bike) vehicle).isMaintenance();
		}
		
		return true;
	}
	
}
