package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This class is used to load the first page,
 * which asks for user type (i.e. Either professor or Administrator or student).
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// FXMLLoader loads the Main.fxml
			Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
			// Scene has been created with the specified size.
			Scene scene = new Scene(root,750,500);
			primaryStage.setTitle("Tutelage Application");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Main method which triggers the start() function.
	public static void main(String[] args) {
		launch(args);
	}
}
