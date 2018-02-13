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

/**
 * This is the controller class for StudentRegistration.fxml, where the
 * Student used to register and create his own account.
 */
public class StudentRegistrationController {

	// declare ID fields from fxml.
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

	/**
	 * This called when Student clicks submit button.
	 * Here we validate the input field and create a new Student account.
	 * @param ae
	 */
	public void newStudent(ActionEvent ae) {

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
				System.out.println("Zipcode: " + stdZipcode);
				
				// Inserting those values to tutelage_student table.
				boolean created = stdDAO.addStudent(firstName, lastName, username, password, address1, address2,
						StudentCity, StudentState, stdZipcode, cellNumber);
				// If the account is created, then the Student is redirected to 
				// login page and asks him to login.
				if (created) {
					
					fName.clear();
					lName.clear();
					stdUsername.clear();
					stdPassword.clear();
					addressOne.clear();
					addressTwo.clear();
					city.clear();
					state.clear();
					postalCode.clear();
					phoneNumber.clear();
					
					JTextPane jtp = new JTextPane();
					jtp.setSize(new Dimension(480, 10));
					jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
					JOptionPane.showMessageDialog(null, "Your account has been created. Please Login to continue.");
					try {
						root1 = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
						stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
						stage.setTitle("Student Login");
						stage.setScene(new Scene(root1));
						stage.show();
					} catch (Exception e) {
						e.getMessage();
					}
				} 
				/*
				 * If the account is not created successfully, then we throw a
				 * error message.
				 * Then check the addStudent() method in StudentDAO class.
				 */
				else
					errorMessage.setText("Problem in creating a new Student");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}
	}

	// When the Student clicks clear button, then the input field values are cleared.
	public void clearText(ActionEvent ae) {
		fName.clear();
		lName.clear();
		stdUsername.clear();
		stdPassword.clear();
		addressOne.clear();
		addressTwo.clear();
		city.clear();
		state.clear();
		postalCode.clear();
		phoneNumber.clear();
	}

	// If the Student clicks 'Back' link, the this method is called.
	// Student is redirected again to login page.
	public void goBack(ActionEvent ae) {
		System.out.println("Clicked Back Link");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("TUTELAGE Application");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
