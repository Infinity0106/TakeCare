package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellEnfEnfermedadController {

    @FXML
    private Label tipo;

    @FXML
    private Label nombre;

    @FXML
    private Label fecha;

    @FXML
    private Label tratamiento;

    @FXML
    private Label sintomas;
    
    public void setValues(String tip, String nom, String fec, String tra, String sin) {
    		tipo.setText(tip);
    		nombre.setText(nom);
    		fecha.setText(fec);
    		tratamiento.setText(tra);
    		sintomas.setText(sin);
    }

}