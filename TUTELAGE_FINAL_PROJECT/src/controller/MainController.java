package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This the controller class for Main.fxml.
 * Here the tutelage users are redirected to their student/professor/admin login page.
 */
public class MainController {

	Parent root1;
	Stage stage;
	
	// If the user is administrator, then he is redirected to Admin login screen.
	public void adminPage(ActionEvent ae) {
		System.out.println("Clicked Administrator Button..");
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

	
	// If the user is professor, then he is redirected to professor login screen.
	public void profPage(ActionEvent ae) {
		System.out.println("Clicked professor Button..");
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
	
	// If the user is Student, then he is redirected to STUDENT login screen.
	public void studentPage(ActionEvent ae) {
		System.out.println("Clicked STUDENT Button..");
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
			stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
			stage.setTitle("STUDENT LOGIN");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			e.getMessage();
		}

	}
}
