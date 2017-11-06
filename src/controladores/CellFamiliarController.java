package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellFamiliarController {

    @FXML
    private Label nombre;

    @FXML
    private Label relacion;

    @FXML
    private Label telefono;

    public void setValues(String nom, String rel, String tel) {
    		nombre.setText(nom);
    		relacion.setText(rel);
    		telefono.setText(tel);
    }
}
