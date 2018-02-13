package controller;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import dao.CourseTypeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SelectedCourseType;

/**
 * This is the controller class for UpdateCourseType.fxml, where the
 * Administrator can update the the course type details.
 */
public class UpdateCourseTypeController extends SelectedCourseType {

	// declared ID fields from fxml.
	@FXML
	private TextField typeID;
	@FXML
	private TextField typeName;
	@FXML
	private TextArea typeDescription;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;

	CourseTypeDAO typeDAO = new CourseTypeDAO();

	/*
	 * Once the admin selects the course type, it's details are populated to
	 * the text fields.
	 */
	@FXML
	public void initialize() {
		String tID = Integer.toString(getTypeID());
		typeID.setText(tID);
		typeName.setText(getName());
		typeDescription.setText(getDescription());
	}

	/**
	 * Once the Admin clicks the 'Update' button, the details are updated in the
	 * table.
	 * 
	 * @param ae
	 */
	public void updateCourseType(ActionEvent ae) {
		// Fetching the input values.
		String courseTypeID = typeID.getText();
		String courseTypeName = typeName.getText();
		String courseTypeDescription = typeDescription.getText();

		// Validating the input values.
		if (courseTypeID == null || courseTypeID.isEmpty())
			errorMessage.setText("Type ID cannot be zero");
		else if (courseTypeName == null || courseTypeName.isEmpty())
			errorMessage.setText("Please enter course Type Name");
		else if (courseTypeDescription == null || courseTypeDescription.isEmpty())
			errorMessage.setText("Please enter course Type Description");
		else {
			try {
				// Converting the courseTypeID to integer.
				int id = Integer.parseInt(courseTypeID);

				/*
				 * Updating those values to course table. Once the
				 * details are updated, the admin is navigated to the home page.
				 */
				boolean updated = typeDAO.updateCourseType(id, courseTypeName, courseTypeDescription);
				if (updated) {
					JTextPane jtp = new JTextPane();
					jtp.setSize(new Dimension(480, 10));
					jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
					JOptionPane.showMessageDialog(null, "Course Type \"" + courseTypeName + "\" has been updated");
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
				/*
				 * If the course type is not updated successfully, then we
				 * throw an error message. Then check the updateCourseType()
				 * method in CourseTypeDAO class.
				 */
				else
					errorMessage.setText("Problem in updating the course type details");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}

	}

	// If the admin clicks 'Back' link, then this method is called.
	public void goBack(ActionEvent ae) {
		System.out.println("Clicked Back Link");
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

	// If the admin clicks Logout, then the Administrator is logged out
	// and redirected to admin login page.
	public void courseTypeLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Administrator Login");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
