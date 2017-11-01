package view;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CambiarEscena {

	public void loadResidentes() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void addResidente() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/view/AgregarResidente.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void addMedicamento() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/view/AddMed.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void viewPerfil() {
		try {
    			Parent root = FXMLLoader.load(getClass().getResource("/view/Perfil.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
}
