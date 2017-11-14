package controladores;

import java.util.ArrayList;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modulos.Residente;

public class RecordatorioMedicamentoController {

    @FXML
    private AnchorPane scrollRes;
    
    @FXML
    private JFXDatePicker fechaTxt;

    @FXML
    private JFXTextField dosisTxt;
    
    @FXML
    private JFXDialog dialogParent;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private StackPane errorStack;
    
    @FXML
    private StackPane rootStack;
    
    private CambiarEscena adminNav = new CambiarEscena();
    
    private ArrayList<Residente> arrRes = new ArrayList<Residente>();
    
    @FXML
    void btnBuscarResAction(ActionEvent event) {
    		if(fechaTxt.getValue()!=null && !fechaTxt.getValue().toString().equals("")&&
    			dosisTxt.getText().toString()!=null&&!dosisTxt.getText().toString().equals("")) {
    			arrRes = new Residentes().residentesRecordatorio(fechaTxt.getValue().toString(),dosisTxt.getText().toString());
    		}else {
    			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
    	    	    	errorStack.setVisible(true);
    	    	    	dialogParent.show();
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
    		
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }

}
