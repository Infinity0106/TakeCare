package controladores;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CambiarEscena {

	public void loadResidentes() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/vistas/VerResidente.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void loadAgregarResidente() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/vistas/AgregarResidente.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void loadSubministrarMedicamento() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/vistas/SubministrarMedicamento.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void loadAgregarMedicamento() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/vistas/AgregarMedicamento.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
	
	public void loadRegistrarEvento() {
		try {
	    		Parent root = FXMLLoader.load(getClass().getResource("/vistas/RegistrarEvento.fxml"));
	    		Scene scene = new Scene(root);
	    		Main.primaryStage.setScene(scene);
	    		Main.primaryStage.setResizable(false);
	    		Main.primaryStage.show();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}
}
