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
import model.Course;

/**
 * This is the controller class for StudentRegisterCourse.fxml. Administrator can
 * view the registered course.
 */
public class ViewCourseController {

	// Getting the required ID fields from fxml.
	@FXML
	private TableView<Course> Courses;
	@FXML
	private Label errorMessage;

	Parent root1;
	Stage stage;

	CourseTypeDAO typeDAO = new CourseTypeDAO();

	/**
	 * Loads all the registered course lists to the table.
	 * 
	 * @param course
	 */
	public void populateData(ObservableList<Course> course) {
		this.Courses.setItems(course);
	}



	// If the admin clicks 'Back' link, then this method is called.
	public void goBack(ActionEvent ae) {
		System.out.println("Back Link Clicked ");
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


}
