package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import modulos.Residente;

public class InventarioMedicamentoController {

    @FXML
    private AnchorPane scrollRes;
    
    private ArrayList<Residente> arrResidente = new ArrayList<Residente>();
    
    private CambiarEscena adminNav = new CambiarEscena();

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
    		this.renderMed();
    }
    
    void renderMed() {
	    	int index=0;
	    	try {
			arrResidente = new Residentes().residenteInventario();
			scrollRes.getChildren().clear();
			for(int i=0;i<10;i++) {
		    	for(Residente Res: arrResidente) {
		    		try {
		    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellInventario.fxml"));
					AnchorPane pane = loader.load();
					CellInventarioController  con = loader.getController();
					con.setValues(Res);
					pane.setLayoutY(index*400-1);
					scrollRes.setPrefHeight(index*400-1+400);
					scrollRes.getChildren().add(pane);
		    		}catch(IOException e) {
		    			e.printStackTrace();
		    		}
		    		index+=1;
		    	}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }

}
