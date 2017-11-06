package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellAdminMedController {
	
    @FXML
    private Label nombre;

    @FXML
    private Label medicamento;

    @FXML
    private Label dosis;

    @FXML
    private Label horario;

    @FXML
    void btnListoAction(ActionEvent event) {

    }
    
    public void setValue(String nom, String med, String dos, String hor) {
    		nombre.setText(nom);
    		medicamento.setText(med);
    		dosis.setText(dos);
    		horario.setText(hor);
    }
}
