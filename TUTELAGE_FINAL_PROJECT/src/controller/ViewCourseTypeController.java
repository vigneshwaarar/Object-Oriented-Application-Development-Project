package controller;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import dao.CourseTypeDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.AllCourseTypeDetails;
import model.CourseType;
import model.SelectedCourseType;

/**
 * This is the controller class for ViewCourseType.fxml. Administrator can view
 * the course type, update and delete the course type.
 */
public class ViewCourseTypeController {

	// Getting the required ID fields from fxml.
	@FXML
	private TableView<CourseType> items;
	@FXML
	private TableView<AllCourseTypeDetails> CourseDetails;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;

	CourseTypeDAO typeDAO = new CourseTypeDAO();

	/**
	 * Loads all the course type lists to the table.
	 * 
	 * @param courseType
	 */
	public void populateData(ObservableList<CourseType> courseType) {
		this.items.setItems(courseType);
	}
	
	public void populateCourseData(ObservableList<AllCourseTypeDetails> courseType) {
		this.CourseDetails.setItems(courseType);
	}
	
	// Administrator can update the course type details by selecting it.
		public void updateCourse(ActionEvent ae) {
			try {
				// gets the selected the course type.
				CourseType courseType = items.getSelectionModel().getSelectedItem();
				// If the course type is not selected, then we are throwing the
				// error
				// message.
				if (courseType == null)
					errorMessage.setText("Please select the course Type");
				// Getting the selected course type values.
				String typeName = courseType.getTypeName();
				String typeDescription = courseType.getTypeDescription();
				// Fetching the courseTypeID using course type name.
				int courseTypeID = typeDAO.fetchTypeID(typeName);

				// The selected course type is stored using setters in
				// SelectedcourseType
				// class.
				SelectedCourseType selectcourse = new SelectedCourseType();
				selectcourse.setTypeID(courseTypeID);
				selectcourse.setName(typeName);
				selectcourse.setDescription(typeDescription);

				System.out.println("Clicked \"Update\" Button..");
				/*
				 * Once the admin clicks update button, then the page is redirected
				 * to update page where the admin can update the course type
				 * details.
				 */
				root1 = FXMLLoader.load(getClass().getResource("/view/UpdateCourseType.fxml"));
				stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
				stage.setTitle("Update course Type");
				stage.setScene(new Scene(root1));
				stage.show();
			} catch (Exception e) {
				e.getMessage();
			}
		}

	// Administrator can delete the course type details by selecting it.
	public void deleteCourseType(ActionEvent ae) {
		System.out.println("Clicked Delete button");
		try {
			// gets the selected the course type.
			CourseType courseType = items.getSelectionModel().getSelectedItem();
			// If the course type is not selected, then we are throwing the
			// error
			// message.
			if (courseType == null)
				errorMessage.setText("Please select the course");
			String typeName = courseType.getTypeName();
			// Fetching the courseTypeID using course type name.
			int typeID = typeDAO.fetchTypeID(typeName);

			JTextPane jtp = new JTextPane();
			jtp.setSize(new Dimension(480, 10));
			jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int result = JOptionPane.showConfirmDialog(null,
					"Would you like to delete \"" + typeName + "\" course type?", "Delete course Type", dialogButton);
			if (result == 0) {
				// Deleting the course type.
				boolean deleted = typeDAO.deleteCategory(typeID);
				// After deleting, the admin is navigated to admin home page.
				if (deleted) {
					JOptionPane.showMessageDialog(null, "Course Type \"" + typeName + "\" is deleted");
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
				 * If the course type is not deleted successfully, then throws
				 * a error message. Then check the deleteCategory() method in
				 * courseTypeDAO class.
				 */
				else
					errorMessage.setText("Course deletion error");
			} else
				System.out.println("Clicked No");

		} catch (Exception e) {
			e.getMessage();
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


	// If the admin clicks Logout, then the admin is logged out
	// and redirected to admin login page.
	public void courseTypeLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
		AdminLoginController.adminLogout();
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
	
	// If the professor clicks 'Back' link, the this method is called.
	public void goBackP(ActionEvent ae) {
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

}
