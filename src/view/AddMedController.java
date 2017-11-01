package view;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import view.CambiarEscena;

public class AddMedController {
	private CambiarEscena adminScene = new CambiarEscena();
    @FXML
    private JFXButton loadResidente;

    @FXML
    private JFXButton addResidente;

    @FXML
    private JFXButton addMedicamento;

    @FXML
    private JFXButton subMedicamento;

    @FXML
    void addMed(ActionEvent event) {
    		adminScene.addMedicamento();
    }

    @FXML
    void addRes(ActionEvent event) {
    		adminScene.addResidente();
    }

    @FXML
    void residentes(ActionEvent event) {
    		adminScene.loadResidentes();
    }

    @FXML
    void subMedicamento(ActionEvent event) {
    
    }

}
