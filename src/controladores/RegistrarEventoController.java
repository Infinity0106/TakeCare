package controladores;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modulos.Residente;
import controladores.CambiarEscena;

public class RegistrarEventoController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private ImageView selFotoRes;

    @FXML
    private JFXComboBox<Label> selResidente;

    @FXML
    private JFXTextField eveNombre;

    @FXML
    private JFXDatePicker eveFecha;

    @FXML
    private JFXTextArea eveDesc;
    
    private ArrayList<Residente> Residentes;
    private Residente resSeleccionado;


    @FXML
    void btnAgrearEventoAction(ActionEvent event) {
    	// Residente al que se le aplicará el evento
    	String residenteSeleccionado = (String)selResidente.getValue().getText();

    	// Buscar el id del residente
    	int idResidenteSeleccionado = -1;
    	for(int i = 0; i < Residentes.size(); i++) {
    		Residente residenteAux = Residentes.get(i);
    		if(residenteAux.Nombre == residenteSeleccionado) {
    			idResidenteSeleccionado = residenteAux.IDResidente;
    			break;
    		}
    	}
    	
    	// Generar objeto residente
    	// TODO
    	// Generar objeto y mandar a insert
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
    void initialize() {
    	try {
    		Residentes = new Residentes().residenteSelectNombre();
    		for(int i=0;i<Residentes.size();i++) {
    			selResidente.getItems().add(new Label(Residentes.get(i).Nombre.toString()));
    		}
    		selResidente.getSelectionModel().select(0);
    		selFotoRes.setImage(new Image(new File(Residentes.get(0).FotoUrl.toString()).toURI().toString()));
    		resSeleccionado = Residentes.get(0);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
}
