package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modulos.Medicamento;
import modulos.Residente;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import baseDeDatos.Medicamentos;
import baseDeDatos.Residentes;
import controladores.CambiarEscena;

public class SubministrarMedicamentoController {
	
	private CambiarEscena adminNav = new CambiarEscena();

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
	
    @FXML
    private AnchorPane scrollAdminMed;
    
    @FXML
    private ImageView selFotoRes; 
    
    @FXML
    private JFXComboBox<Label> selResidente;
    
    private ArrayList<Residente> Residentes=new ArrayList<Residente>();
    
    private Residente resSeleccionado;
    
    private ArrayList<Medicamento> tmpMed = new ArrayList<Medicamento>();

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
    void navRecMedAction(ActionEvent event) {
    		adminNav.loadRecMed();
    		this.killObjects();
    }
    
    @FXML 
    void navInvMedAction(ActionEvent event) {
    		adminNav.loadInvMed();
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
    			resSeleccionado = Residentes.get(0);
    			tmpMed=new Medicamentos().medicamentosSelect(resSeleccionado.IDResidente);
    		}
    		if(tmpMed.size()>0) {
    			this.renderMedTable();    			
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
				resSeleccionado=res;
			}
		}
		selFotoRes.setImage(new Image(new File(resSeleccionado.FotoUrl.toString()).toURI().toString()));
		
		tmpMed=new Medicamentos().medicamentosSelect(resSeleccionado.IDResidente);
		this.renderMedTable();
    }
    
    void renderMedTable() {
	    	int index=0;
	    	scrollAdminMed.getChildren().clear();
	    	for(Medicamento Med: tmpMed) {
	    		try {
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellAdminMed.fxml"));
					StackPane pane = loader.load();
					CellAdminMedController  con = loader.getController();
					con.setValues(Med.Nombre, Med.Presentacion, Med.Cantidad, resSeleccionado.IDResidente, Med.IDMedicamento);
					pane.setLayoutY(index*100-1);
					scrollAdminMed.setPrefHeight(index*100-1+100);
					scrollAdminMed.getChildren().add(pane);
	    		}catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    		index+=1;
	    	}
	}
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
}
