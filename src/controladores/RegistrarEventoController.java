package controladores;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import baseDeDatos.Eventos;
import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modulos.Evento;
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
    
    @FXML
    private StackPane rootStack;
    
    @FXML
    private JFXDialog dialogParent;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private StackPane successDialog;

    @FXML
    private StackPane erroDialog;
    
    private ArrayList<Residente> Residentes;
    private Evento eventoLocal;
    
    private Residente resSeleccionado= new Residente();
    


    @FXML
    void btnAgrearEventoAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    		if(!eveNombre.getText().toString().equals("")&&
    		eveFecha.getValue()!=null&&!eveFecha.getValue().toString().equals("")&&
    		!eveDesc.getText().toString().equals("")) {
    			
    			// Residente al que se le aplicar� el evento
	    	    	String residenteSeleccionado = (String)selResidente.getValue().getText();
	    	
	    	    	// Buscar el id del residente
	    	    	for(int i = 0; i < Residentes.size(); i++) {
	    	    		Residente residenteAux = Residentes.get(i);
	    	    		if(residenteAux.Nombre == residenteSeleccionado) {
	    	    			resSeleccionado = residenteAux;
	    	    			break;
	    	    		}
	    	    	}
	    	    	
	    	    	// Generar objeto residente
	    	    	eventoLocal = new Evento();
	    	    	eventoLocal.enfermera = eveNombre.getText();
	    	    	eventoLocal.fecha = eveFecha.getValue().toString();
	    	    	eventoLocal.descripcion = eveDesc.getText();
	    	    	
	    	    	Eventos eventosLokos = new Eventos();
	    	    	eventoLocal.IDEvento = eventosLokos.eventoInsert(eventoLocal, resSeleccionado.IDResidente).IDEvento;
	    	    	
	    	    	if(	eventoLocal.IDEvento!=-1) {
	    	    		dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	        			successDialog.setVisible(true);
	        			erroDialog.setVisible(false);
	        			dialogParent.show();
	        			System.out.println("error");
	    	    	}else {
	    	    		dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	        			successDialog.setVisible(false);
	        			erroDialog.setVisible(true);
	        			dialogParent.show();
	        			System.out.println("error");
	    	    	}
    			
    		}else {
    			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
    			successDialog.setVisible(false);
    			erroDialog.setVisible(true);
    			dialogParent.show();
    			System.out.println("error");
    		}
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
    		adminNav.loadInvMed();
    		this.killObjects();
    }
    
    @FXML
    void navRecMedAction(ActionEvent event) {
    		adminNav.loadRecMed();
    		this.killObjects();
    }

    @FXML
    void initialize() {
	    	try {
	    		Residentes = new Residentes().residenteSelectNombre();
	    		if(Residentes.size()>0) {
	    			for(int i=0;i<Residentes.size();i++) {
	    				selResidente.getItems().add(new Label(Residentes.get(i).Nombre.toString()));
	    			}
	    			selResidente.getSelectionModel().select(0);
	    			selFotoRes.setImage(new Image(new File(Residentes.get(0).FotoUrl.toString()).toURI().toString()));	    			
	    		}
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
    }
    
    @FXML
    void selResidenteAction(ActionEvent event) {
    		for(Residente res : Residentes) {
			if(res.Nombre.equals(selResidente.getValue().getText().toString())) {
				System.out.println(res.FotoUrl);
				resSeleccionado=res;
			}
		}
		selFotoRes.setImage(new Image(new File(resSeleccionado.FotoUrl.toString()).toURI().toString()));
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
}
