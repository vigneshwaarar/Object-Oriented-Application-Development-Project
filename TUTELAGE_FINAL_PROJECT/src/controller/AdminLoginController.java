package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Administrator;
import model.Login;
import dao.LoginDAO;

/**
 * This is a controller class for AdminLogin.fxml which is used to validate the
 * administrator and provides him login access.
 * 
 */
public class AdminLoginController {

	/*
	 * Creates the static administrator class object which stores the
	 * administrator information after he logs in.
	 */
	private static Administrator administrator;

	// declaring ID fields from fxml.
	@FXML
	private TextField adminUser;
	@FXML
	private PasswordField adminPassword;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;
	Login login = new Login();
	LoginDAO loginDAO = new LoginDAO();

	/**
	 * This method is called when administrator clicks login. It validates the
	 * admin user name and password and provides him login access.
	 * 
	 * @param ae
	 */
	public void adminSignin(ActionEvent ae) {

		Administrator admin;

		// input values.
		String username = adminUser.getText();
		String password = adminPassword.getText();

		// Validating the input values.
		if (username == null || username.isEmpty())
			errorMessage.setText("Please enter your Username");
		else if (username == null || password.isEmpty())
			errorMessage.setText("Please enter your Password");
		else {
			login.setUsername(username);
			login.setPassword(password);
			// Fetching the admin information if the username and password is
			// correct.
			admin = loginDAO.adminDetails(login);

			/*
			 * If the credentials are correct, then they are logged in and
			 * redirected to Admin home page.
			 */
			
			if (admin.getAdminUsername().equals(username) &&
					admin.getPassword().equals(password)) {
				administrator = admin;
				try {
					root1 = FXMLLoader.load(getClass().getResource("/view/AdminHomePage.fxml"));
					stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
					stage.setTitle("Administrator Home Page");
					stage.setScene(new Scene(root1));
					stage.show();
				} catch (Exception e) {
					e.getMessage();
				}
			} 
			// If the credentials are in correct, then we are throwing the error message.
			else
				errorMessage.setText("Please enter the valid credentials");
		}
	}

	/*
	 * If the 'Back' link is clicked, the this method is called and redirected
	 * to user type selection page.
	 */
	public void goBack(ActionEvent ae) {
		System.out.println("Back Link Clicked ");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Tutelage Application");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static Administrator getAdmin() {
		return administrator;
	}

	public static void adminLogout() {
		administrator = null;
	}
}
