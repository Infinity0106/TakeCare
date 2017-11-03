package controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import controladores.CambiarEscena;

public class AgregarResidenteController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private JFXTextField infNombre;

    @FXML
    private JFXDatePicker infFecha;

    @FXML
    private JFXComboBox<Label> infSex;

    @FXML
    private ImageView infFoto;

    @FXML
    private JFXTextField infLugar;

    @FXML
    private JFXTextField infCama;

    @FXML
    private JFXTextField famNombre;

    @FXML
    private JFXTextField famCorreo;

    @FXML
    private JFXTextField famTel;

    @FXML
    private JFXComboBox<Label> famRel;

    @FXML
    private JFXTextField famCalle;

    @FXML
    private JFXTextField famMun;

    @FXML
    private JFXTextField famEst;

    @FXML
    private JFXTextField famCol;

    @FXML
    private JFXTextField famPais;

    @FXML
    private JFXTextArea hmedAnamnesis;

    @FXML
    private JFXTextArea hmedExamenes;

    @FXML
    private JFXTextArea hmedDiag;

    @FXML
    private JFXTextArea hmedPro;

    @FXML
    private JFXTextArea hmedTrata;

    @FXML
    private JFXTextField hmedDoc;

    @FXML
    private JFXTextField hmedCedu;

    @FXML
    private JFXTextField hmedEsp;

    @FXML
    private AnchorPane scrollInfoAdicional;

    @FXML
    private JFXTextField servCodigo;

    @FXML
    private JFXTextField servHosp;

    @FXML
    private JFXTextField servMun;

    @FXML
    private JFXTextField servEst;

    @FXML
    private JFXTextField servDir;

    @FXML
    private JFXDialog dialogParent;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private StackPane stackAddEnfermedad;

    @FXML
    private JFXTextField addEnfermedadNombre;

    @FXML
    private JFXTextField addEnfermedadSintoma;

    @FXML
    private JFXTextField addEnfermedadTratamiento;

    @FXML
    private StackPane stackAddOperacion;

    @FXML
    private JFXTextField addOperacionNombre;

    @FXML
    private JFXTextField addOperacionTratamiento;

    @FXML
    private JFXDatePicker addOperacionFecha;

    @FXML
    void addResidenteAction(ActionEvent event) {

    }

    @FXML
    void dialogAddEnfermedadAction(ActionEvent event) {

    }

    @FXML
    void dialogAddOperacionAction(ActionEvent event) {

    }

    @FXML
    void infoAddEnfAction(ActionEvent event) {

    }

    @FXML
    void infoAddOpAction(ActionEvent event) {

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
    	
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
}
