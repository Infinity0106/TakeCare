package controladores;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import modulos.Evento;

public class CellEventoController {
	@FXML
	private Label fecha;
	@FXML
	private Label resp;
	@FXML
	private Label desc;
	
	public void setValues(Evento eve) {
		fecha.setText(eve.fecha);
		resp.setText(eve.enfermera);
		desc.setText(eve.descripcion);
	}

}
