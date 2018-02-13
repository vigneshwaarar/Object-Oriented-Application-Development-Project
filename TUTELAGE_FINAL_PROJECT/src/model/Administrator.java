package model;

/**
 * Model class for Administrator. 
 * After login, Administrator informations are stored in this class. 
 */
public class Administrator {

	// Instance fields.
	private int adminID;
	private String fName;
	private String lName;
	private String adminUsername;
	private String password;
	
	
	// Getters and Setters for instance fields.
	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
