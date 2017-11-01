package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class enfermedadCellController {
	@FXML
	private Label nombre;
	@FXML
	private Label sintomas;
	@FXML
	private Label tratamiento;
	
	public void setValues(String nom,String sin,String tra) {
		nombre.setText(nom);
		sintomas.setText(sin);
		tratamiento.setText(tra);
	}
	
}