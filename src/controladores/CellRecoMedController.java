package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellRecoMedController {

    @FXML
    private Label nombre;

    @FXML
    private Label familiar;

    @FXML
    private Label medicamento;
    
    public void setValues(String nom, String fam, String med) {
    		nombre.setText(nom);
    		familiar.setText(fam);
    		medicamento.setText(med);
    }

}
