package controladores;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.Main;
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
import modulos.Enfermedad;
import modulos.Evento;
import modulos.Familiar;
import modulos.Operacion;
import modulos.Residente;
import controladores.CambiarEscena;

public class VerResidenteController {
	
	private CambiarEscena adminNav = new CambiarEscena();

    @FXML
    private ImageView selResFoto;

    @FXML
    private JFXComboBox<Label> selResNombre;

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
    private JFXComboBox<Label> famRelacion;

    @FXML
    private JFXTextField famCalle;

    @FXML
    private JFXTextField famMun;

    @FXML
    private JFXTextField famEstado;

    @FXML
    private JFXTextField famCol;

    @FXML
    private JFXTextField famPais;
    
    @FXML
    private AnchorPane scrollFamiliares;

    @FXML
    private JFXTextArea hisMedAnamesis;

    @FXML
    private JFXTextArea hisMedRes;

    @FXML
    private JFXTextArea hisMedDiag;

    @FXML
    private JFXTextArea hisMedPro;

    @FXML
    private JFXTextArea hisMedTrata;

    @FXML
    private JFXTextField hisMedDoc;

    @FXML
    private JFXTextField hisMedCedula;

    @FXML
    private JFXTextField hisMedEspecialidad;

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
    private JFXTextField servHosEstado;

    @FXML
    private JFXTextField serHosDireccion;

    @FXML
    private AnchorPane scrollEventos;
    
    private ArrayList<modulos.Residente> Residentes = new ArrayList<modulos.Residente>();
    
    private modulos.Residente resSeleccionado = new modulos.Residente();
    
    private List<Object> tablaEnfermedades=new ArrayList<Object>();
    
    private String imageURL="";

    @FXML
    void modResidenteAction(ActionEvent event) {
    		
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
    	
	    	try {
	    		Residentes = new Residentes().residenteSelectNombre();
	    		for(int i=0;i<Residentes.size();i++) {
	    			selResNombre.getItems().add(new Label(Residentes.get(i).Nombre.toString()));
	    		}
	    		selResNombre.getSelectionModel().select(0);
	    		selResFoto.setImage(new Image(new File(Residentes.get(0).FotoUrl.toString()).toURI().toString()));
	    		infFoto.setImage(new Image(new File(Residentes.get(0).FotoUrl.toString()).toURI().toString()));
	    		resSeleccionado = Residentes.get(0);
	    		System.out.println(Residentes.get(0).IDResidente);
			resSeleccionado=new Residentes().residenteSelect(Residentes.get(0).IDResidente);
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
	    	infSex.getItems().add(new Label("Hombre"));
	    infSex.getItems().add(new Label("Mujer"));
	    
	    for(Enfermedad enf : resSeleccionado.HistorialEstadia.Enfermedades) {
	    		tablaEnfermedades.add(enf);
	    }
	    for(Operacion op : resSeleccionado.HistorialEstadia.Operaciones) {
	    		tablaEnfermedades.add(op);
	    }
	    	this.setValues();
    }
    
    @FXML
    void selResidenteAction(ActionEvent event) {
    		for(Residente res : Residentes) {
    			if(res.Nombre.equals(selResNombre.getValue().getText().toString())) {
    				System.out.println(res.IDResidente);
    				resSeleccionado=new Residentes().residenteSelect(res.IDResidente);
    			}
    		}
    		this.setValues();
    		selResFoto.setImage(new Image(new File(resSeleccionado.FotoUrl.toString()).toURI().toString()));
    		infFoto.setImage(new Image(new File(resSeleccionado.FotoUrl.toString()).toURI().toString()));
    }
    
    void killObjects() {
		adminNav=null;
		System.gc();
    }
    
    void setValues() {
    		infNombre.setText(resSeleccionado.Nombre);
    		infFecha.setValue(LOCAL_DATE(resSeleccionado.FechaNacimiento));
    		if(resSeleccionado.Sexo=='M') {
    			infSex.getSelectionModel().select(1);
    		}else{
    			infSex.getSelectionModel().select(0);
    		}
    		infLugar.setText(Integer.toString(resSeleccionado.Lugar));
    		infCama.setText(Integer.toString(resSeleccionado.Cama));
    		servCodigo.setText(resSeleccionado.EmergenciaMedica.Clave);
    		servTelefono.setText(resSeleccionado.EmergenciaMedica.Telefono);
    		servHospital.setText(resSeleccionado.ServicioHospital.Nombre);
    		servHospTel.setText(resSeleccionado.ServicioHospital.Telefono);
    		servHosMun.setText(resSeleccionado.ServicioHospital.Direccion.Municipio);
    		servHosEstado.setText(resSeleccionado.ServicioHospital.Direccion.Estado);
    		serHosDireccion.setText(resSeleccionado.ServicioHospital.Direccion.Calle);
    		this.renderTableFam();
    		this.renderTableEnf();
    		this.renderTableEve();
    }
    
    public void renderTableEve() {
	    	int index=0;
	    	scrollEventos.getChildren().clear();
	    	for(Evento eve : resSeleccionado.Eventos) {
	    		try {
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CellEvento.fxml"));
					AnchorPane pane = loader.load();
					CellEventoController con = loader.getController();
					con.setValues(eve);
					pane.setLayoutY(index*200-1);
					scrollEventos.setPrefHeight(index*200-1+200);
					scrollEventos.getChildren().add(pane);
	    		}catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    		index+=1;
	    	}
    }
    
    public void renderTableFam() {
	    	int index=0;
	    	scrollFamiliares.getChildren().clear();
	    	for(Familiar fam : resSeleccionado.Familiares) {
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
    
    public void renderTableEnf() {
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
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}
