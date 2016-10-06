package Project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Mainclass extends Application {
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Mainclass.class.getResource("AnchorPane.fxml"));
		try {
			rootLayout = loader.load();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
