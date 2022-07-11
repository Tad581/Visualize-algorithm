package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

	public static Stage mainWindow;

	@Override
	public void start(Stage primaryStage) {
		try {
			mainWindow = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

			Scene scene = new Scene(root);

			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			mainWindow.setScene(scene);
			mainWindow.setMaximized(true);
			mainWindow.setTitle("JavaFx demo");
			mainWindow.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}

}