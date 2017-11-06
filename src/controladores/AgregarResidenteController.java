package controladores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import baseDeDatos.Domicilios;
import baseDeDatos.Familiares;
import baseDeDatos.Residentes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import modulos.Domicilio;
import modulos.Emergencia;
import modulos.Enfermedad;
import modulos.Familiar;
import modulos.Historial;
import modulos.Operacion;
import modulos.Residente;
import view.enfermedadCellController;
import controladores.CambiarEscena;

public class AgregarResidenteController {
	
	private CambiarEscena adminNav = new CambiarEscena();

	@FXML
    private StackPane rootStack;

    @FXML
    private JFXTextField infNombre;

    @FXML
    private JFXDatePicker infFecha;

    @FXML
    private JFXComboBox<Label> infSex;

    @FXML
    private ImageView infFoto;

    @FXML
    private JFXTextField infLugar;

    @FXML
    private JFXTextField infCama;

    @FXML
    private JFXTextField famNombre;

    @FXML
    private JFXTextField famCorreo;

    @FXML
    private JFXTextField famTel;

    @FXML
    private JFXComboBox<Label> famRel;

    @FXML
    private JFXTextField famCalle;

    @FXML
    private JFXTextField famMun;

    @FXML
    private JFXTextField famEst;

    @FXML
    private JFXTextField famCol;

    @FXML
    private JFXTextField famPais;

    @FXML
    private AnchorPane scrollInfoAdicional;

    @FXML
    private JFXTextField servCodigo;

    @FXML
    private JFXTextField servTelefono;

    @FXML
    private JFXTextField servHospital;

    @FXML
    private JFXTextField servHospTel;

    @FXML
    private JFXTextField servHosMun;
    
    @FXML
    private JFXTextField servHosEst;

    @FXML
    private JFXTextField servHosCalle;
    
    @FXML
    private JFXTextField servHosCol;
    
    @FXML
    private JFXTextField servHosPais;

    @FXML
    private JFXDialog dialogParent;

    @FXML
    private JFXDialogLayout dialogLayout;

    @FXML
    private StackPane stackAddEnfermedad;

    @FXML
    private JFXTextField addEnfermedadNombre;

    @FXML
    private JFXTextField addEnfermedadSintoma;

    @FXML
    private JFXTextField addEnfermedadTratamiento;
    
    @FXML
    private JFXDatePicker addEnfermedadFecha;

    @FXML
    private StackPane stackAddOperacion;

    @FXML
    private JFXTextField addOperacionNombre;

    @FXML
    private JFXTextField addOperacionTratamiento;

    @FXML
    private JFXDatePicker addOperacionFecha;
    
    @FXML
    private StackPane errorDialog;

    @FXML
    private StackPane successDialog;
    
    @FXML
    private AnchorPane scrollFamiliares;
    
    private ArrayList<Familiar> tmpFam = new ArrayList<Familiar>();
    
    private Historial tmpHis;
    
    private Emergencia tmpEme;
    
    private Residente tmpRes;
    
    private ArrayList<Operacion> tmpOperaciones = new ArrayList<Operacion>();
    
    private ArrayList<Enfermedad> tmpEnfermedades = new ArrayList<Enfermedad>();
    
    private List<Object> tablaEnfermedades=new ArrayList<Object>();
    
    private String imageURL="";

    @FXML
    void addResidenteAction(ActionEvent event) {
    		if(infNombre.getText().toString()!=null&&!infNombre.getText().toString().equals("")&&
    			infFecha.getValue()!=null&&!infFecha.getValue().toString().equals("")&&
    			infSex.getValue()!=null&&!infSex.getValue().toString().equals("")&&
    			imageURL!=null&&!imageURL.equals("")&&
    			infLugar.getText().toString()!=null&&!infLugar.getText().toString().equals("")&&
    			infCama.getText().toString()!=null&&!infCama.getText().toString().equals("")&&
    			famNombre.getText().toString()!=null&&!famNombre.getText().toString().equals("")&&
    			famCorreo.getText().toString()!=null&&!famCorreo.getText().toString().equals("")&&
    			famTel.getText().toString()!=null&&!famTel.getText().toString().equals("")&&
    			famRel.getValue()!=null&&
    			famMun.getText().toString()!=null&&!famMun.getText().toString().equals("")&&
    			famEst.getText().toString()!=null&&!famEst.getText().toString().equals("")&&
    			famCalle.getText().toString()!=null&&!famCalle.getText().toString().equals("")&&
    			famCol.getText().toString()!=null&&!famCol.getText().toString().equals("")&&
    			famPais.getText().toString()!=null&&!famPais.getText().toString().equals("")&&
    			servCodigo.getText().toString()!=null&&!servCodigo.getText().toString().equals("")&&
    			servTelefono.getText().toString()!=null&&!servTelefono.getText().toString().equals("")&&
    			servHospital.getText().toString()!=null&&!servHospital.getText().toString().equals("")&&
    			servHosMun.getText().toString()!=null&&!servHosMun.getText().toString().equals("")&&
    			servHosEst.getText().toString()!=null&&!servHosEst.getText().toString().equals("")&&
    			servHospTel.getText()!=null&&!servHospTel.getText().equals("")&&
    			servHosCalle.getText()!=null&&!servHosCalle.getText().equals("")&&
    			servHosCol.getText().toString()!=null&&!servHosCol.getText().toString().equals("")&&
    			servHosPais.getText().toString()!=null&&!servHosPais.getText().toString().equals("")) {
//    			if enfermedade o operaciones agregar
    			tmpEme = new Emergencia();
    			tmpEme.Clave=servCodigo.getText().toString();
    			tmpEme.Telefono=servTelefono.getText().toString();
    			
    			tmpRes = new Residente();
    			tmpRes.Nombre = infNombre.getText().toString();
    			tmpRes.Estatus = "Activo";
    			tmpRes.FechaNacimiento = infFecha.getValue().toString();
    			tmpRes.FotoUrl = imageURL;
    			tmpRes.Lugar = (Integer) new Integer(infLugar.getText().toString());
    			tmpRes.Cama = (Integer) new Integer(infCama.getText().toString());
    			tmpRes.Sexo = (infSex.getValue().toString().equals("Mujer")?'M':'H');
    			tmpRes.EmergenciaMedica = tmpEme;
    			tmpRes.HistorialEstadia = tmpHis;
    			tmpRes.Familiares = tmpFam;
    			
    			tmpRes.IDResidente=-1;
    			tmpRes.IDResidente=new Residentes().residenteInsert(tmpRes).IDResidente;
    			
    			if(tmpFam.size()>0) {
    				for(Familiar fam : tmpFam) {
    					fam.Residencia.IDDomicilio=new Domicilios().domicilioInsert(fam.Residencia).IDDomicilio;
    					fam.IDFamiliar=new Familiares().familiarInsert(fam, tmpRes.IDResidente).IDFamiliar;
    					
    				}
    			}
    			if(tmpOperaciones.size()>0) {
    				
    			}
    			if(tmpEnfermedades.size()>0) {
    				
    			}
    			
    			
    			
    			if(tmpRes.IDResidente!=-1) {
    				dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
            		stackAddEnfermedad.setVisible(false);
	    	    	    	stackAddOperacion.setVisible(false);
	    	    	    	successDialog.setVisible(true);
	    	    	    	errorDialog.setVisible(false);
	    	    	    	dialogParent.show();
    			}else {
    				dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	    	        	stackAddEnfermedad.setVisible(false);
	    	    	    	stackAddOperacion.setVisible(false);
	    	    	    	successDialog.setVisible(false);
	    	    	    	errorDialog.setVisible(true);
	    	    	    	dialogParent.show();
    			}
    		}else {
    			dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
	        	stackAddEnfermedad.setVisible(false);
	    	    	stackAddOperacion.setVisible(false);
	    	    	successDialog.setVisible(false);
	    	    	errorDialog.setVisible(true);
	    	    	dialogParent.show();
    		}
    }

    @FXML
    void dialogAddEnfermedadAction(ActionEvent event) {
    		if(addEnfermedadNombre.getText()!=null&&!addEnfermedadNombre.getText().toString().equals("")&&
    		addEnfermedadSintoma.getText()!=null&&!addEnfermedadSintoma.getText().toString().equals("")&&
    		addEnfermedadTratamiento.getText()!=null&&!addEnfermedadTratamiento.getText().toString().equals("")&&
    		addEnfermedadFecha.getValue()!=null&&!addEnfermedadFecha.getValue().toString().equals("")) {
    			dialogParent.close();
    			Enfermedad tmpEnf = new Enfermedad();
    			tmpEnf.Nombre=addEnfermedadNombre.getText().toString();
    			tmpEnf.Sintomas=addEnfermedadSintoma.getText().toString();
    			tmpEnf.Tratamiento=addEnfermedadTratamiento.getText().toString();
    			tmpEnf.Fecha=addEnfermedadFecha.getValue().toString();
    			tmpEnfermedades.add(tmpEnf);
    			
    			
    			tablaEnfermedades.add(tmpEnf);
    			this.renderTabla();
    		}
    }

    @FXML
    void dialogAddOperacionAction(ActionEvent event) {
    		if(addOperacionNombre.getText()!=null&&!addOperacionNombre.getText().toString().equals("")&&
    		addOperacionFecha.getValue()!=null&&!addOperacionFecha.getValue().toString().equals("")&&
    		addOperacionTratamiento.getText()!=null&&!addOperacionTratamiento.getText().toString().equals("")) {
    			dialogParent.close();
    			Operacion tmpOp = new Operacion();
    			
    			tmpOp.Nombre=addOperacionNombre.getText().toString();
    			tmpOp.Fecha=addOperacionFecha.getValue().toString();
    			tmpOp.Tipo=addOperacionTratamiento.getText().toString();
    			tmpOperaciones.add(tmpOp);
    			
    			tablaEnfermedades.add(tmpOp);
    			this.renderTabla();
    		}
    		
    }

    @FXML
    void infoAddEnfAction(ActionEvent event) {
    		dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
    		stackAddEnfermedad.setVisible(true);
	    	stackAddOperacion.setVisible(false);
	    	successDialog.setVisible(false);
	    	errorDialog.setVisible(false);
	    	dialogParent.show();
    }

    @FXML
    void infoAddOpAction(ActionEvent event) {
    		dialogParent = new JFXDialog(rootStack,dialogLayout,JFXDialog.DialogTransition.CENTER,true);
		stackAddEnfermedad.setVisible(false);
	    	stackAddOperacion.setVisible(true);
	    	successDialog.setVisible(false);
	    	errorDialog.setVisible(false);
	    	dialogParent.show();
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
    void addFamiliarBtnAction(ActionEvent event) {
    		if(famNombre.getText()!=null&&!famNombre.getText().toString().equals("")&&
		famCorreo.getText()!=null&&!famCorreo.getText().toString().equals("")&&
		famTel.getText()!=null&&!famTel.getText().toString().equals("")&&
		famRel.getValue()!=null&&!famRel.getValue().toString().equals("")&&
		famMun.getText()!=null&&!famMun.getText().toString().equals("")&&
		famEst.getText()!=null&&!famEst.getText().toString().equals("")&&
		famCalle.getText()!=null&&!famCalle.getText().toString().equals("")&&
		famCol.getText()!=null&&!famCol.getText().toString().equals("")&&
		famPais.getText()!=null&&!famPais.getText().toString().equals("")) {
    			Domicilio tmpDirecionFam = new Domicilio();
    			tmpDirecionFam.Calle=famCalle.getText().toString();
    			tmpDirecionFam.Colonia=famCol.getText().toString();
    			tmpDirecionFam.Estado=famEst.getText().toString();
    			tmpDirecionFam.Municipio=famMun.getText().toString();
    			tmpDirecionFam.Pais=famPais.getText().toString();
    			
    			Familiar tmpFamiliar = new Familiar();
    			tmpFamiliar.Nombre=famNombre.getText().toString();
    			tmpFamiliar.Correo=famCorreo.getText().toString();
    			tmpFamiliar.Telefono=famTel.getText().toString();
    			tmpFamiliar.Relacion=famRel.getValue().getText().toString();
    			tmpFamiliar.Residencia=tmpDirecionFam;
    			
    			tmpFam.add(tmpFamiliar);
    			this.renderFamTable();
    		}
    }
    
    @FXML
    void initialize() {
    		DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("selecciona imagen");
        infFoto.setOnMouseClicked((MouseEvent e) -> {
        		FileChooser filechooser = new FileChooser();
        		filechooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        		File Selectedfile = filechooser.showOpenDialog(Main.primaryStage);
        		if(Selectedfile!=null) {
        			System.out.println(Selectedfile.getPath());
        			imageURL=Selectedfile.getPath().toString();
        			infFoto.setImage(new Image(Selectedfile.toURI().toString()));
        		}
        });
        
        infSex.getItems().add(new Label("Hombre"));
        infSex.getItems().add(new Label("Mujer"));
        
        famRel.getItems().add(new Label("Herman@"));
        famRel.getItems().add(new Label("Prim@"));
        famRel.getItems().add(new Label("Sobrin@"));
        famRel.getItems().add(new Label("Niet@"));
        
        famRel.setConverter(new StringConverter<Label>() {
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
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
    
    void renderFamTable() {
	    	int index=0;
	    	scrollFamiliares.getChildren().clear();
	    	for(Familiar fam : tmpFam) {
	    		try {
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellFamiliar.fxml"));
    				StackPane pane = loader.load();
    				CellFamiliarController con = loader.getController();
    				con.setValues(fam.Nombre, fam.Relacion, fam.Telefono);
    				pane.setLayoutY(index*100-1);
    				scrollFamiliares.setPrefHeight(index*100-1+100);
    				scrollFamiliares.getChildren().add(pane);
	    		}catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    		index+=1;
	    	}
    }
    
    void renderTabla() {
    		int index = 0;
    		scrollInfoAdicional.getChildren().clear();
    		for(Object obj : tablaEnfermedades) {
    			if(obj.getClass() == Operacion.class) {
    				Operacion tmp = (Operacion) obj;
    				try {
        				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellEnfOperacion.fxml"));
        				StackPane pane = loader.load();
        				CellEnfOperacionController con = loader.getController();
        				con.setValues("O", tmp.Nombre, tmp.Fecha, tmp.Tipo);
        				pane.setLayoutY(index*100-1);
        				scrollInfoAdicional.setPrefHeight(index*100-1+100);
        				scrollInfoAdicional.getChildren().add(pane);
        			}catch (IOException e){
        				e.printStackTrace();
        			}
    			}
    			if(obj.getClass() == Enfermedad.class) {
    				Enfermedad tmp = (Enfermedad) obj;
    				try {
        				FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellEnfEnfermedad.fxml"));
        				StackPane pane = loader.load();
        				CellEnfEnfermedadController con = loader.getController();
        				con.setValues("E", tmp.Nombre, tmp.Fecha, tmp.Tratamiento, tmp.Sintomas);
        				pane.setLayoutY(index*100-1);
        				scrollInfoAdicional.setPrefHeight(index*100-1+100);
        				scrollInfoAdicional.getChildren().add(pane);
        			}catch (IOException e){
        				e.printStackTrace();
        			}
    			}
    			index+=1;
    		}
    }
}
