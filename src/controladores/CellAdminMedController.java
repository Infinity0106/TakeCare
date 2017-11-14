package controladores;

import baseDeDatos.Medicamentos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CellAdminMedController {

    @FXML
    private Label nombre;

    @FXML
    private Label medicamento;

    @FXML
    private Label nombre1;
    
    private int resID;
    
    private int medID;
    
    private Medicamentos medBridge=new Medicamentos();

    @FXML
    void btnLAddMedAction(ActionEvent event) {
    		Boolean tmp;
    		tmp=medBridge.medicamentosUpdate(medID,resID,'+');
    		if(tmp) {
    			Integer num = new Integer(nombre1.getText().toString());
    			num=num+1;
    			nombre1.setText(Integer.toString(num));
    		}
    }

    @FXML
    void btnSubMedAction(ActionEvent event) {
    		Boolean tmp;
    		tmp=medBridge.medicamentosUpdate(medID,resID,'-');
    		if(tmp) {
    			Integer num = new Integer(nombre1.getText().toString());
    			num=num-1;
    			nombre1.setText(Integer.toString(num));
    		}
    }

    public void setValues(String nom, String med, int cant, int idr, int idm) {
    		nombre.setText(nom);
    		medicamento.setText(med);
    		nombre1.setText(Integer.toString(cant));
    		resID=idr;
    		medID=idm;
    }
}

