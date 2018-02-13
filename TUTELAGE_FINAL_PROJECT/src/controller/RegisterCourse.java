package controller;

import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import dao.StudentDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Course;
import model.SelectedCourse;

/**
 * This is the controller class for StudentRegisterCourse.fxml, where the Student
 * selects the Course and buys it.
 */
public class RegisterCourse implements Initializable  {

	// Declare fields from fxml.
	@FXML
	private TableView<Course> Courses;
	@FXML
	private Label errorMessage;
	@FXML
	private TableColumn<Course,String> CourseName;
	@FXML
	private TableColumn<Course,String> CourseType;

	Parent root1;
	Stage stage;

	StudentDAO stdDAO = new StudentDAO();

	/**
	 * Loads all the Course lists to the table.
	 * 
	 * @param Course
	 */
	public void CoursesInfo(ObservableList<Course> Course) {
		this.Courses.setItems(Course);
	}

	/**
	 * This method is called when Student 'Register' button is clicked.
	 * 
	 * @param ae
	 */

	public void Register(ActionEvent ae) {
		// gets the selected the Course
		Course item = Courses.getSelectionModel().getSelectedItem();
		// If the Course is not selected, then we are throwing the error
		// message.
		
		if (item == null)
			errorMessage.setText("Please select the Course");
		else {
		// Getting the selected Course values.
			String CourseName = item.getCourseName();
			String CourseType = item.getCourseType();
		
		// Fetching the CourseID using Course name.
			int CourseID = stdDAO.fetchCourseID(CourseName);
		
			try {
				
				/* Updating those values to tutelage_Register_course table.
				 * Once the details ate updated, the student is navigated to the home page. 
				 */
				boolean updated = stdDAO.RegisterCourse(CourseID);
				if (updated) {
					JTextPane jtp = new JTextPane();
					jtp.setSize(new Dimension(480, 10));
					jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
					JOptionPane.showMessageDialog(null, "Course has been registered");
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
				 * error message. Then check the updatestudentProfile() method in
				 * studentDAO class.
				 */
				else
					errorMessage.setText("Problem in updating the student information");
			} catch (NumberFormatException e) {
				errorMessage.setText("Enter the valid input");
				e.getMessage();
			}
		}
		

	}


	// If the Student clicks 'Back' link, the this method is called.
	public void goBack(ActionEvent ae) {
		System.out.println("Clicked Back Link");
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

	// If the Student clicks Logout, then the Student is logged out
	// and redirected to Student login page.
	public void stdLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
		try {
			StudentLoginController.studentLogout();
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Student Login");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
