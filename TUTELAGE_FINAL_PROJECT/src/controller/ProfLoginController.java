package controller;

import dao.LoginDAO;
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
import model.Login;
import model.Prof;

/**
 * This is a controller class for ProfLogin.fxml which is used to validate
 * the professor and provides him login access.
 * 
 */
public class ProfLoginController {

	/*
	 * Creates the static prof class object which stores the professor
	 * information after he logs in.
	 */
	private static Prof profs;

	// delcare ID fields from fxml.
	@FXML
	private TextField profUsername;
	@FXML
	private PasswordField profPassword;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;
	Login login = new Login();
	LoginDAO loginDAO = new LoginDAO();

	/**
	 * This method is called when professor clicks login. It validates the
	 * professor user name and password and provides him login access.
	 * 
	 * @param ae
	 */
	public void profSignin(ActionEvent ae) {

		Prof prof;

		String username = profUsername.getText();
		String password = profPassword.getText();

		// Validating the input values.
		if (username.isEmpty())
			errorMessage.setText("Please enter your Username");
		else if (password.isEmpty())
			errorMessage.setText("Please enter your Password");
		else {
			login.setUsername(username);
			login.setPassword(password);

			// Fetching the professor information if the user name and password is
			// correct.
			prof = loginDAO.ProfDetails(login);

			/*
			 * If the credentials are correct, then they are logged in and
			 * redirected to professor home page.
			 */
			if (prof.getUsername().equals(username)
					&& prof.getPassword().equals(password)) {
				profs = prof;
				try {
					System.out.println("professor logged in.");
					root1 = FXMLLoader.load(getClass().getResource("/view/ProfHomePage.fxml"));
					stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
					stage.setTitle("professor Home Page");
					stage.setScene(new Scene(root1));
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			// If the credentials are in correct, then we are throwing the error message.
			else
				errorMessage.setText("Please enter the valid credentials");
		}
	}

	/*
	 * This method is called when professor clicks register.
	 * If professor is registring for the first time , then he can register and create his account.
	 */
	public void profRegister(ActionEvent ae) {
		System.out.println("Clicked Register Link");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ProfRegistration.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("professor Registration");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	/*
	 * If the 'Back' link is clicked, the this method is called and redirected
	 * to user type selection page.
	 */
	public void goBack(ActionEvent ae) {
		System.out.println("Clicked Back Link");
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

	public static Prof getprof() {
		return profs;
	}

	public static void profLogout() {
		profs = null;
	}

}
