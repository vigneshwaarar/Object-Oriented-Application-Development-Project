package model;

/**
 * Users user name and Passwords are stored and retrieved from this class.
 * It is used during Login.
 */
public class Login {
	
	// Instance fields.
	private String username;
	private String password;
	
	// Getters and Setters for instance fields.
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
