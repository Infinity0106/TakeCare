package controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import controladores.CambiarEscena;

public class VerResidenteController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private ImageView selResFoto;

    @FXML
    private JFXComboBox<Label> selResNombre;

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
    private JFXComboBox<Label> famRelacion;

    @FXML
    private JFXTextField famCalle;

    @FXML
    private JFXTextField famMun;

    @FXML
    private JFXTextField famEstado;

    @FXML
    private JFXTextField famCol;

    @FXML
    private JFXTextField famPais;

    @FXML
    private JFXTextArea hisMedAnamesis;

    @FXML
    private JFXTextArea hisMedRes;

    @FXML
    private JFXTextArea hisMedDiag;

    @FXML
    private JFXTextArea hisMedPro;

    @FXML
    private JFXTextArea hisMedTrata;

    @FXML
    private JFXTextField hisMedDoc;

    @FXML
    private JFXTextField hisMedCedula;

    @FXML
    private JFXTextField hisMedEspecialidad;

    @FXML
    private AnchorPane scrollInfoAdicional;

    @FXML
    private JFXTextField servCodigo;

    @FXML
    private JFXTextField servHospital;

    @FXML
    private JFXTextField servMun;

    @FXML
    private JFXTextField servEst;

    @FXML
    private JFXTextField servDir;

    @FXML
    private AnchorPane scrollEventos;

    @FXML
    void dialogAddEnfermedadAction(ActionEvent event) {

    }

    @FXML
    void dialogAddOperacionAction(ActionEvent event) {

    }

    @FXML
    void modResidenteAction(ActionEvent event) {

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
    void navInvMedAction(ActionEvent event) {
    		//TODO
    		this.killObjects();
    }
    
    @FXML
    void navRecMedAction(ActionEvent event) {
    		//TODO
    		this.killObjects();
    }
    
    @FXML
    void addFamiliar(ActionEvent event) {
    	
    }

    @FXML
    void selBtnResAction(ActionEvent event) {

    }
    
    @FXML
    void initialize() {
    	
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }

}
