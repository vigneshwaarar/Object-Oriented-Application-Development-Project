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
import model.Prof;

/**
 * This is the controller class for UpdateProfPage.fxml, where the professor
 * can update his profile information.
 */
public class ProfUpdateProfile {

	// Getting the required ID fields from fxml.
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
	Prof prof = ProfLoginController.getprof();

	/*
	 * The text fields about the professor details are automatically populated
	 * once they are logged in.
	 */
	@FXML
	void initialize() {

		String zipcode = Integer.toString(prof.getZipCode());

		fName.setText(prof.getFirstName());
		lName.setText(prof.getLastName());
		profUsername.setText(prof.getUsername());
		profPassword.setText(prof.getPassword());
		addressOne.setText(prof.getAddress1());
		addressTwo.setText(prof.getAddress2());
		city.setText(prof.getCity());
		state.setText(prof.getState());
		postalCode.setText(zipcode);
		phoneNumber.setText(prof.getPhoneNumber());

	}

	// If the professor clicks Logout, then the professor is logged out
	// and redirected to professor login page.
	public void profLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ProfLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Prof Home Page");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// If the professor clicks 'Back' link, the this method is called.
	public void goBack(ActionEvent ae) {
		System.out.println("Clicked Back Link");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ProfHomePage.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Tutelage Application");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * Once the professor clicks the 'Update' button, the details are updated in
	 * professor table.
	 */
	public void updateProf(ActionEvent ae) {
		
		// Fetching the input values.
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
				/* Updating those values to table.
				 * Once the details ate updated, the professor is navigated to the home page. 
				 */
				boolean updated = profDAO.updateProfProfile(firstName, lastName, username, password, address1,
						address2, profCity, profState, profZipcode, cellNumber);
				if (updated) {
					JTextPane jtp = new JTextPane();
					jtp.setSize(new Dimension(480, 10));
					jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
					JOptionPane.showMessageDialog(null, "Your account has been updated");
					try {
						root1 = FXMLLoader.load(getClass().getResource("/view/ProfHomePage.fxml"));
						stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
						stage.setTitle("Prof Home Page");
						stage.setScene(new Scene(root1));
						stage.show();
					} catch (Exception e) {
						e.getMessage();
					}
				} 
				/*
				 * If the professor info is not updated successfully, then we throw an
				 * error message. Then check the updateProfProfile() method in
				 * ProfDAO class.
				 */
				else
					errorMessage.setText("Problem in updating the prof information");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}

	}

}
