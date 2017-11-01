package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import view.createDB;

public class Main extends Application {
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStageArg) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStageArg.setScene(scene);
			primaryStageArg.setResizable(false);
			primaryStageArg.show();
			primaryStage = primaryStageArg;
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			createDB.main(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
