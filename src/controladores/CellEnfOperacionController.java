package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellEnfOperacionController {

    @FXML
    private Label tipo;

    @FXML
    private Label nombre;

    @FXML
    private Label sintomas;

    @FXML
    private Label tratamiento;

    public void setValues(String ti, String nom, String sin, String tra) {
    		tipo.setText(ti);
    		nombre.setText(nom);
    		sintomas.setText(sin);
    		tratamiento.setText(tra);
    }
}