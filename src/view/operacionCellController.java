package view;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class operacionCellController {
	@FXML
	private Label tipo;
	@FXML
	private Label fecha;
	
	public void setValues(String ti, String fe) {
		System.out.println(ti+" "+fe);
		tipo.setText(ti);
		fecha.setText(fe);
	}
}