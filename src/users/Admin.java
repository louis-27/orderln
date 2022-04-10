package users;

public class Admin extends User {

	private String password;

	public Admin(String name, String password) {
		super(name);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
