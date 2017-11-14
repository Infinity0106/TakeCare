package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modulos.Medicamento;
import modulos.Residente;

public class CellInventarioController {

    @FXML
    private Label nom;

    @FXML
    private Label medDia;

    @FXML
    private Label medTarde;

    @FXML
    private Label medNoche;
    
    public void setValues(Residente res) {
    		nom.setText(res.Nombre);
    		String md="",mt="",mn="";
//    		System.out.println(res.Medicamentos);
    		for(Medicamento med: res.Medicamentos) {
    			if(med.VecesDia.contains("Dia")) {
    				md+=med.Nombre+" "+med.Posologia+",   ";
    			}
    			if(med.VecesDia.contains("Tarde")) {
    				mt+=med.Nombre+" "+med.Posologia+",   ";
    			}
			if(med.VecesDia.contains("Noche")) {
				mn+=med.Nombre+" "+med.Posologia+",   ";
			}
    		}

    		medDia.setText(md);
    		medTarde.setText(mt);
    		medNoche.setText(mn);
    }
}
