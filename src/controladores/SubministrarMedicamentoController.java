package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import controladores.CambiarEscena;

public class SubministrarMedicamentoController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private AnchorPane scrollRecMed;

    @FXML
    private AnchorPane scrollAdminMed;

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
