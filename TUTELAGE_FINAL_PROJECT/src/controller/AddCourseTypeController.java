package controller;

import dao.CourseTypeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is the controller class for CreateCourseType.fxml. When the admin adds a new
 * course this controller is called 
 */
public class AddCourseTypeController {

	// ID fields from fxml.
	@FXML
	private TextField typeName;
	@FXML
	private TextArea typeDescription;
	@FXML
	private Label replyMessage;
	@FXML
	private Hyperlink backLink;

	Parent root1;
	Stage stage;

	// Created object for the dao classes.
	CourseTypeDAO typeDAO = new CourseTypeDAO();

	/**
	 * 
	 * 
	 * @param ae
	 */
	public void newCourseType(ActionEvent ae) {
	
		// Getting the input values.
		String courseType = typeName.getText();
		String courseTypeDesc = typeDescription.getText();
		
		// Validating the input values.
		if (courseType == null || courseType.isEmpty())
			replyMessage.setText("Please enter the course name");
		else if (courseTypeDesc == null || courseTypeDesc.isEmpty())
			replyMessage.setText("Please enter the course description");
		else {
			// Inserting the values to table.
			boolean created = typeDAO.insertCourseType(courseType, courseTypeDesc);
			if (created) {
				replyMessage.setText("New course Type has been created");
				typeDescription.clear();
				typeName.clear();
			} 
			/*
			 * If the COURSE is not created successfully, then we are 
			 * throwing the error message.
			 */
			else
				replyMessage.setText("Problem in creating a new course.");
		}
	}
	
	//	method is called when a new course is created by professor
	public void newCourseTypeP(ActionEvent ae) {
		
		// Getting the input values.
		String courseType = typeName.getText();
		String courseTypeDesc = typeDescription.getText();
		
		// Validating the input values.
		if (courseType == null || courseType.isEmpty())
			replyMessage.setText("Please enter the course name");
		else if (courseTypeDesc == null || courseTypeDesc.isEmpty())
			replyMessage.setText("Please enter the course description");
		else {
			// Inserting the values to table.
			boolean created = typeDAO.insertCourseType_Prof(courseType, courseTypeDesc);
			if (created) {
				replyMessage.setText("New course  has been created");
				typeDescription.clear();
				typeName.clear();
			} 
			/*
			 * If the course is not created successfully, then we are 
			 * throwing the error message.
			 */
			else
				replyMessage.setText("Problem in creating a new course.");
		}
	}
	
	
	// If the admin clicks 'Back', the this method is called.
	public void goBack(ActionEvent ae){
		System.out.println("Back Link Clicked ");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/AdminHomePage.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("Admin Home Page");
			stage.setScene(new Scene(root1));
			
		} catch (Exception e) {
			e.getMessage();
		}
		backLink.setOnAction(event->{
			stage.show();
		});
	}

	// If the professor clicks 'Back' in the professor, the this method is called.
		public void goBackP(ActionEvent ae) {
			System.out.println("Back Link Clicked ");
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
		
	// If the admin clicks Logout, then the Admin is logged out 
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
}
