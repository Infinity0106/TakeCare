package controladores;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import modulos.Residente;
import controladores.CambiarEscena;

import baseDeDatos.Residentes;
import baseDeDatos.Medicamentos;

public class AgregarMedicamentoController {
	
	private CambiarEscena adminNav = new CambiarEscena();
	
	@FXML
    private StackPane rootStack;
	
	@FXML
    private JFXDialog dialogParent;

    @FXML
    private JFXDialogLayout dialogLayout;

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
    private StackPane successDialog;

    @FXML
    private StackPane erroDialog;
    
    private ArrayList<Residente> Residentes;
    
    private Residente resSeleccionado;
    
    @FXML
    void addMedicamento(ActionEvent event) {
    		if(addMedNombre==null||addMedVencimiento.getValue()==null||addMedPresentacion==null||addMedCantidad==null||addMedPosologia==null) {
			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
			System.out.print(dialogParent);
			successDialog.setVisible(false);
			erroDialog.setVisible(true);
			dialogParent.show();
    			System.out.println("error");
    		}else {
	    		String veces = "";
	    		if(addMedVenceDia.isSelected()) veces+="Dia|";
	    		if(addMedVenceTarde.isSelected()) veces+="Tarde|";
	    		if(addMedVenceNoche.isSelected()) veces+="Noche";
	    		
	    		Boolean respuesta = new Medicamentos().medicamentosInsert(
	    				resSeleccionado.IDResidente,
	    				addMedNombre.getText().toString(),
	    				addMedVencimiento.getValue().toString(),
	    				addMedPresentacion.getValue().getText().toString(),
	    				(Integer) new Integer(addMedCantidad.getText().toString()),
	    				addMedPosologia.getText().toString(),
	    				veces
	    		);
	    		
	    		if(respuesta) {
	    			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	    			successDialog.setVisible(true);
	    			erroDialog.setVisible(false);
	    			dialogParent.show();
	    			System.out.println("successfull");
	    		}else {
	    			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	    			successDialog.setVisible(false);
	    			erroDialog.setVisible(true);
	    			dialogParent.show();
	    			System.out.println("error");
	    		}
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
	    	
	    	addMedPresentacion.getItems().add(new Label("tabletas"));
	    	addMedPresentacion.getItems().add(new Label("gotas"));
	    	addMedPresentacion.getItems().add(new Label("ampolla"));
	    	addMedPresentacion.getItems().add(new Label("ovulo"));
	    	addMedPresentacion.getItems().add(new Label("supositorio"));
	    	
	    	addMedPresentacion.setConverter(new StringConverter<Label>() {
        		@Override
	        	public String toString(Label object) {
	        		return object==null?"":object.getText();
	        	}

			@Override
			public Label fromString(String string) {
				return new Label(string);
			}
        });
	    	
	    	
    }
    
    @FXML
    void selResidenteAction(ActionEvent event) {
    		for(Residente res : Residentes) {
    			if(res.Nombre.equals(selResidente.getValue().getText().toString())) {
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
