package controller;

import java.util.List;
import dao.StudentDAO;
import dao.CourseTypeDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Student;
import model.Course;

/**
 * This class is mainly used to redirect to appropriate page, once the button is
 * clicked. This is a controller class for StudentHomePage.fxml
 */
public class StudentHomeController extends Student {

	// declared ID fields from fxml.
	@FXML
	private Label welcome;

	Parent root1;
	Stage stage;
	Student student = StudentLoginController.getStudent();
	StudentDAO stdDAO = new StudentDAO();
	CourseTypeDAO typeDAO = new CourseTypeDAO();
	
	// Once the student logs in, his name is displayed on the home page.
	@FXML
	void initialize() {
		welcome.setText("Welcome, " + student.getFirstName());
	}

	// If the Student clicks Logout, then the Student is logged out
	// and redirected to Student login page.
	public void StudentLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
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

	// This method redirects to Student information page, where the Student can
	// update his account details.
	public void updateStudentInfo(ActionEvent ae) {
		System.out.println("Clicked \"Update Profile\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/UpdateStudentPage.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Update Student Profile");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}


	//This method helps to redirect to view Course Registration  page.
	public void viewCourses(ActionEvent ae) {
		System.out.println("Clicked \"Register Course\" Button..");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentRegisterCourse.fxml"));
			root1 = loader.load();
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Register Course");
			stage.setScene(new Scene(root1));
			RegisterCourse controller = loader.getController();
			List<Course> Course;
			// Added all the couses to list.
			Course = typeDAO.fetchCourse();
			controller.CoursesInfo(FXCollections.observableArrayList(Course));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
	// This redirects to course history page, where can Student can view his
	// course details.
		public void viewCourseHisory(ActionEvent ae) {
			System.out.println("Clicked \"View Course history\" Button..");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentRegisterHistory.fxml"));
				root1 = loader.load();
				stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
				stage.setTitle("Student Course History");
				stage.setScene(new Scene(root1));
				RegisterCourse controller = loader.getController();
				List<Course> crs;
				// Added all the registered course to list.
				crs = typeDAO.fetchCourseHistory();
				// Passes registered list to populateData() method in RegisterCourse class.
				controller.CoursesInfo(FXCollections.observableArrayList(crs));
				stage.show();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	

}
