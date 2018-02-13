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
import model.Student;
import model.Login;

/**
 * This is a controller class for StudentLogin.fxml which is used to validate
 * the Student and provides him login access.
 * 
 */
public class StudentLoginController {

	/*
	 * Creates the static Student class object which stores the Student
	 * information after he logs in.
	 */
	private static Student student;

	// declare ID fields from fxml.
	@FXML
	private TextField stdUsername;
	@FXML
	private PasswordField stdPassword;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;
	Login login = new Login();
	LoginDAO loginDAO = new LoginDAO();

	/**
	 * This method is called when Student clicks login. It validates the
	 * Student user name and password and provides him login access.
	 * 
	 * @param ae
	 */
	public void studentSignin(ActionEvent ae) {

		Student std;

		String username = stdUsername.getText();
		String password = stdPassword.getText();

		// Validating the input values.
		if (username.isEmpty())
			errorMessage.setText("Please enter your Username");
		else if (password.isEmpty())
			errorMessage.setText("Please enter your Password");
		else {
			login.setUsername(username);
			login.setPassword(password);

			// Fetching the Student information if the user name and password is
			// correct.
			std = loginDAO.studentDetails(login);

			/*
			 * If the credentials are correct, then they are logged in and
			 * redirected to Student home page.
			 */
			if (std.getUsername().equals(username)
					&& std.getPassword().equals(password)) {
				student = std;
				try {
					System.out.println("Student logged in.");
					root1 = FXMLLoader.load(getClass().getResource("/view/StudentHomePage.fxml"));
					stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
					stage.setTitle("Student Home Page");
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
	 * This method is called when Student clicks register.
	 * If he is new Student, then he can register and create his account.
	 */
	public void studentRegister(ActionEvent ae) {
		System.out.println("Clicked Register Link");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentRegistration.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Student Registration");
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

	public static Student getStudent() {
		return student;
	}

	public static void studentLogout() {
		student = null;
	}

}
