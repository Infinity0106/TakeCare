package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellRecordatorioController {
	@FXML
	public Label nombre;
	
	@FXML
	public Label med;
	
	@FXML
	public Label familiar;
	
	@FXML
	public Label tel;
	
	@FXML
	public Label cant;
	
	public void setValues(String sNombre, String sMed, String sFamiliar, String sTel, String sCant) {
		nombre.setText(sNombre);
		med.setText(sMed);
		familiar.setText(sFamiliar);
		tel.setText(sTel);
		cant.setText(sCant);
	}
}
