package controladores;

import java.io.File;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import controladores.CambiarEscena;

public class AgregarMedicamentoController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private ImageView selFotoRes;

    @FXML
    private JFXComboBox<Label> selResidente;

    @FXML
    private JFXTextField addMedNombre;

    @FXML
    private JFXTextField addMedCantidad;

    @FXML
    private JFXDatePicker addMedVencimiento;

    @FXML
    private JFXComboBox<Label> addMedPresentacion;

    @FXML
    private JFXCheckBox addMedVenceDia;

    @FXML
    private JFXCheckBox addMedVenceTarde;

    @FXML
    private JFXCheckBox addMedVenceNoche;

    @FXML
    private JFXTextField addMedPosologia;

    @FXML
    private JFXComboBox<Label> addMedTerapeutico;

    @FXML
    void addMedicamento(ActionEvent event) {
    	
    }
    
    @FXML
    void selBtnResAction(ActionEvent event) {

    }

    @FXML
    void navAddMedAction(ActionEvent event) {
    		adminNav.loadAgregarMedicamento();
    		this.killObjects();
    }

    @FXML
    void navAddResAction(ActionEvent event) {
    		adminNav.loadAgregarResidente();
    		this.killObjects();
    }

    @FXML
    void navRegEveAction(ActionEvent event) {
    		adminNav.loadRegistrarEvento();
    		this.killObjects();
    }

    @FXML
    void navResidenteAction(ActionEvent event) {
    		adminNav.loadResidentes();
    		this.killObjects();
    }

    @FXML
    void navSubMedAction(ActionEvent event) {
    		adminNav.loadSubministrarMedicamento();
    		this.killObjects();
    }

    @FXML
    void initialize() {
        for(int i=0;i<30;i++) {
        		selResidente.getItems().add(new Label("cama "+i)); 
        }
    }
    
    @FXML
    void selResidenteAction(ActionEvent event) {
    		System.out.println(selResidente.getValue().getText().toString());
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
}
