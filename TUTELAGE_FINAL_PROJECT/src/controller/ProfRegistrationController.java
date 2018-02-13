package controller;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import dao.ProfDAO;
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
 * This is the controller class for ProfRegistration.fxml, where the
 * professor used to register and create his own account.
 */
public class ProfRegistrationController {

	// Declare ID fields from fxml.
	@FXML
	private TextField fName;
	@FXML
	private TextField lName;
	@FXML
	private TextField profUsername;
	@FXML
	private PasswordField profPassword;
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
	ProfDAO profDAO = new ProfDAO();

	/**
	 * This called when professor clicks submit button.
	 * Here we validate the input field and create a new professor account.
	 * @param ae
	 */
	public void newProf(ActionEvent ae) {

		String firstName = fName.getText();
		String lastName = lName.getText();
		String username = profUsername.getText();
		String password = profPassword.getText();
		String address1 = addressOne.getText();
		String address2 = addressTwo.getText();
		String profCity = city.getText();
		String profState = state.getText();
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
		else if (profCity == null || profCity.isEmpty())
			errorMessage.setText("Please enter the city name");
		else if (profState == null || profState.isEmpty())
			errorMessage.setText("Please enter the state");
		else if (zipCode == null || zipCode.isEmpty())
			errorMessage.setText("Please enter the zipcode");
		else if (cellNumber == null || cellNumber.isEmpty())
			errorMessage.setText("Please enter your mobile number");
		else {
			try {
				// Converting the zip code to integer.
				Integer profZipcode = Integer.parseInt(zipCode);
				System.out.println("Zipcode: " + profZipcode);
				
				// Inserting those values to professor table.
				boolean created = profDAO.addProf(firstName, lastName, username, password, address1, address2,
						profCity, profState, profZipcode, cellNumber);
				// If the account is created, then the professor is redirected to 
				// login page and asks him to login.
				if (created) {
					
					fName.clear();
					lName.clear();
					profUsername.clear();
					profPassword.clear();
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
						root1 = FXMLLoader.load(getClass().getResource("/view/ProfLogin.fxml"));
						stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
						stage.setTitle("professor Login");
						stage.setScene(new Scene(root1));
						stage.show();
					} catch (Exception e) {
						e.getMessage();
					}
				} 
				/*
				 * If the account is not created successfully, then we throw a
				 * error message.
				 */
				else
					errorMessage.setText("Problem in creating a new professor");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}
	}

	// When the professor clicks clear button, then the input field values are cleared.
	public void clearText(ActionEvent ae) {
		fName.clear();
		lName.clear();
		profUsername.clear();
		profPassword.clear();
		addressOne.clear();
		addressTwo.clear();
		city.clear();
		state.clear();
		postalCode.clear();
		phoneNumber.clear();
	}

	// If the professor clicks 'Back' link, the this method is called.
	// professor is redicted again to login page.
	public void goBack(ActionEvent ae) {
		System.out.println("Back Link Clicked ");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ProfLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Tutelage Application");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
