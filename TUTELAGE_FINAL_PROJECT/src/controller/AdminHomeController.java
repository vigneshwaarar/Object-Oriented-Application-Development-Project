package controller;

import java.util.List;
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
import model.Administrator;
import model.AllCourseTypeDetails;
import model.CourseType;
/**
 * This class is mainly used to redirect to appropriate admin related page,
 * once the button is clicked.
 * This is a controller class for AdminHomePage.fxml
 */
public class AdminHomeController {
	
	// declaring ID fields from fxml.
	@FXML
	private Label welcome;

	Parent root1;
	Stage stage;
	CourseTypeDAO typeDAO = new CourseTypeDAO();
	Administrator admin = AdminLoginController.getAdmin();

	// admin name is displayed on the home page.
	@FXML
	void initialize() {
		welcome.setText("Welcome, " + admin.getfName());
	}

	// If the admin clicks Logout, then the Administrator is logged out
	// and redirected to admin login page.
	public void adminLogout(ActionEvent ae) {
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

	// This method helps to redirect to create course home page where the admin can crete course.
	public void createCourseType(ActionEvent ae) {
		System.out.println("Clicked \"Create course\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/CreateCourseType.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Add course");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}


	//This method helps to redirect to view course page.
	public void viewCourseType(ActionEvent ae) {
		System.out.println("Clicked \"View course list\" Button..");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewCourseType.fxml"));
			root1 = loader.load();
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("course Types");
			stage.setScene(new Scene(root1));
			// ViewCourseTypeController is loaded
			ViewCourseTypeController controller = loader.getController();
			List<CourseType> pt;
			// Added all the Course to list.
			pt = typeDAO.fetchCourseType();
			// Passes Course list to populateData() method in ViewCourseTypeController class.
			controller.populateData(FXCollections.observableArrayList(pt));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
//This method helps to redirect to view registired courses and no of student enrolled each course,
//This page gives the admin to initiate the course where more students have enrolled
		public void viewCourseDetails(ActionEvent ae) {
			System.out.println("Clicked \"View Course Enrollement\" Button..");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewCourseDetails.fxml"));
				root1 = loader.load();
				stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
				stage.setTitle("Course Details");
				stage.setScene(new Scene(root1));
				// ViewCourseTypeController is loaded
				ViewCourseTypeController controller = loader.getController();
				List<AllCourseTypeDetails> pt;
				// Added all the Course types to list.
				pt = typeDAO.fetchCourseDetails();
				for(AllCourseTypeDetails a: pt)
				{
					System.out.println(a.getProfName());
					System.out.println(a.getStudentCount());
					System.out.println(a.getTypeName());
				}
				// Passes course type list to populateCourseData() method in ViewCourseTypeController class.
				controller.populateCourseData(FXCollections.observableArrayList(pt));
				stage.show();
			} catch (Exception e) {
				e.getMessage();
			}
		}

}
