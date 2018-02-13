package controller;

import java.util.List;
import dao.ProfDAO;
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
import model.Prof;
import model.CourseType;

/**
 * This class is mainly used to redirect to appropriate page, once the button is
 * clicked. This is a controller class for ProfHomePage.fxml
 */
public class ProfHomeController extends Prof {

	// Getting the required ID fields from fxml.
	@FXML
	private Label welcome;

	Parent root1;
	Stage stage;

	Prof prof = ProfLoginController.getprof();
	ProfDAO profDAO = new ProfDAO();
	CourseTypeDAO typeDAOP = new CourseTypeDAO();

	// Once the professor logs in, his name is displayed on the home page.
	@FXML
	void initialize() {
		welcome.setText("Welcome, " + prof.getFirstName());
	}

	// If the professor clicks Logout, then the professor is logged out
	// and redirected to professor login page.
	public void profLogout(ActionEvent ae) {
		System.out.println("Clicked \"Logout\" Button..");
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

	// This method redirects to professor information page, where the professor can
	// update his account details.
	public void updateProfInfo(ActionEvent ae) {
		System.out.println("Clicked \"Update Profile\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/UpdateProfPage.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Update Prof Profile");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	
	// This method helps to redirect to create course  home page.
	public void profcreateCourseType(ActionEvent ae) {
		System.out.println("Clicked \"Create course Type\" Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ProfCreateCourseType.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Add course Types");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	//This method helps to redirect to view course page.
	public void ProfviewCourseType(ActionEvent ae) {
		System.out.println("Clicked \"View course Type\" Button..");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfViewCourseType.fxml"));
			root1 = loader.load();
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Course Types");
			stage.setScene(new Scene(root1));
			// ViewCourseTypeController is loaded
			ViewCourseTypeController controller = loader.getController();
			List<CourseType> pt;
			// Added all the course types to list.
			pt = typeDAOP.fetchCourseType();
			// Passes course type list to populateData() method in ViewCourseTypeController class.
			controller.populateData(FXCollections.observableArrayList(pt));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	

}
