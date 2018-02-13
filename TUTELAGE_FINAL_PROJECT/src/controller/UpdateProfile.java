package controller;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import dao.StudentDAO;
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

/**
 * This is the controller class for UpdateStudentPage.fxml, where the student
 * can update his profile information.
 */
public class UpdateProfile {

	// Declare ID fields from fxml.
	@FXML
	private TextField fName;
	@FXML
	private TextField lName;
	@FXML
	private TextField stdUsername;
	@FXML
	private PasswordField stdPassword;
	@FXML
	private TextField addressOne;
	@FXML
	private TextField addressTwo;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField postalCode;
	@FXML
	private TextField phoneNumber;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;
	StudentDAO stdDAO = new StudentDAO();
	Student std = StudentLoginController.getStudent();

	/*
	 * The text fields about the Student details are automatically populated
	 * once they are logged in.
	 */
	@FXML
	void initialize() {

		String zipcode = Integer.toString(std.getZipCode());

		fName.setText(std.getFirstName());
		lName.setText(std.getLastName());
		stdUsername.setText(std.getUsername());
		stdPassword.setText(std.getPassword());
		addressOne.setText(std.getAddress1());
		addressTwo.setText(std.getAddress2());
		city.setText(std.getCity());
		state.setText(std.getState());
		postalCode.setText(zipcode);
		phoneNumber.setText(std.getPhoneNumber());

	}

	// If the student clicks Logout, then the student is logged out
	// and redirected to student login page.
	public void studentLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Students Home Page");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// If the student clicks 'Back' link, the this method is called.
	public void goBack(ActionEvent ae) {
		System.out.println(" Back Link Clicked");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentHomePage.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Tutelage Application");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Once the student clicks the 'Update' button, the details are updated in
	 * tutelage_Student table.
	 */
	public void updateStudent(ActionEvent ae) {
		
		// Fetching the input values.
		String firstName = fName.getText();
		String lastName = lName.getText();
		String username = stdUsername.getText();
		String password = stdPassword.getText();
		String address1 = addressOne.getText();
		String address2 = addressTwo.getText();
		String StudentCity = city.getText();
		String StudentState = state.getText();
		String zipCode = postalCode.getText();
		String cellNumber = phoneNumber.getText();

		// Validating the input values.
		if (firstName == null || firstName.isEmpty())
			errorMessage.setText("Please enter your first name");
		else if (lastName == null || lastName.isEmpty())
			errorMessage.setText("Please enter your last name");
		else if (username == null || username.isEmpty())
			errorMessage.setText("Please enter the username");
		else if (password == null || password.isEmpty())
			errorMessage.setText("Please enter the password");
		else if (address1 == null || address1.isEmpty())
			errorMessage.setText("Please enter your Address Line 1");
		else if (address2 == null || address2.isEmpty())
			errorMessage.setText("Please enter your Address Line 2");
		else if (StudentCity == null || StudentCity.isEmpty())
			errorMessage.setText("Please enter the city name");
		else if (StudentState == null || StudentState.isEmpty())
			errorMessage.setText("Please enter the state");
		else if (zipCode == null || zipCode.isEmpty())
			errorMessage.setText("Please enter the zipcode");
		else if (cellNumber == null || cellNumber.isEmpty())
			errorMessage.setText("Please enter your mobile number");
		else {
			try {
				// Converting the zip code to integer.
				Integer stdZipcode = Integer.parseInt(zipCode);
				/* Updating those values to tutelage_student table.
				 * Once the details ate updated, the student is navigated to the home page. 
				 */
				boolean updated = stdDAO.updateStudentProfile(firstName, lastName, username, password, address1,
						address2, StudentCity, StudentState, stdZipcode, cellNumber);
				if (updated) {
					JTextPane jtp = new JTextPane();
					jtp.setSize(new Dimension(480, 10));
					jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
					JOptionPane.showMessageDialog(null, "Your account has been updated");
					try {
						root1 = FXMLLoader.load(getClass().getResource("/view/StudentHomePage.fxml"));
						stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
						stage.setTitle("Student Home Page");
						stage.setScene(new Scene(root1));
						stage.show();
					} catch (Exception e) {
						e.getMessage();
					}
				} 
				/*
				 * If the student info is not updated successfully, then we throw an
				 * error message. Then check the updateStudentProfile() method in
				 * StudentDAO class.
				 */
				else
					errorMessage.setText("Problem in updating the student information");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}

	}

}
