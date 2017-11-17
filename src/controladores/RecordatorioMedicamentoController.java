package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import modulos.Familiar;
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
    
    private ArrayList<CellRecordatorioController> arrElementos = new ArrayList<CellRecordatorioController>();
    
    @FXML
    void btnBuscarResAction(ActionEvent event) {
    		if(fechaTxt.getValue() != null && !fechaTxt.getValue().toString().equals("") &&
    			dosisTxt.getText().toString()!=null&&!dosisTxt.getText().toString().equals("")) {
    			arrRes = new Residentes().residentesRecordatorio(fechaTxt.getValue().toString(), dosisTxt.getText().toString());
//				arrElementos = new Residentes().residentesRecordatorio(fechaTxt.getValue().toString(),dosisTxt.getText().toString());
    			System.out.println("getelem");
    			for(Residente res: arrRes) {
    				System.out.println(res.Nombre+" "+res.Familiares.get(0).Nombre+" "+res.Familiares.get(0).Telefono+" "+res.Medicamentos.get(0).Nombre+" "+res.Medicamentos.get(0).Cantidad);
    			}
			renderTableRec();
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
    
    public void renderTableRec() {
	    	int index=0;
	    	scrollRes.getChildren().clear();
	    	for(Residente res : arrRes) {
	    		try {
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellRecordatorio.fxml"));
					AnchorPane pane = loader.load();
					CellRecordatorioController con = loader.getController();
					con.setValues(res.Nombre,res.Medicamentos.get(0).Nombre,res.Familiares.get(0).Nombre,res.Familiares.get(0).Telefono,Integer.toString(res.Medicamentos.get(0).Cantidad));
					pane.setLayoutY(index*80-1);
					scrollRes.setPrefHeight(index*80-1+80);
					scrollRes.getChildren().add(pane);
	    		}catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    		index+=1;
	    	}
    }

}
