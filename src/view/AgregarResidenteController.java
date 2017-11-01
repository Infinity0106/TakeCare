package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;

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
import view.CambiarEscena;

import application.Main;

public class AgregarResidenteController {
	private CambiarEscena adminS = new CambiarEscena();
	@FXML
    private StackPane rootStack;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField NombreResidente;

    @FXML
    private JFXTextField ServicioEmergencia;

    @FXML
    private JFXDatePicker NacimientoResidente;

    @FXML
    private JFXComboBox<Label> Lugar;

    @FXML
    private JFXComboBox<Label> Cama;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXTextField familiarNombre;

    @FXML
    private JFXTextField familiarCorreo;

    @FXML
    private JFXTextField familiarTelefono;

    @FXML
    private JFXTextArea familiarDomicilio;

    @FXML
    private JFXComboBox<Label> familiarRelacion;

    @FXML
    private JFXButton btnEnfermedad;

    @FXML
    private JFXButton btnOperacion;

    @FXML
    private JFXButton btnAlergias;

    @FXML
    private AnchorPane enfermedadesScroll;

    @FXML
    private AnchorPane operacionesScroll;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXDialog diaEnfermedadParent;

    @FXML
    private JFXDialogLayout diaEnfermedad;

    @FXML
    private StackPane stackEnfermedad;

    @FXML
    private JFXTextField nombreEnfermedad;

    @FXML
    private JFXTextArea sintomasEnfermedad;

    @FXML
    private JFXTextArea tratamientoEnfermedad;

    @FXML
    private JFXButton btnAddEnfermedad;

    @FXML
    private StackPane stackAlergia;

    @FXML
    private JFXTextField nombreAlergia;

    @FXML
    private JFXTextArea sintomaAlergia;

    @FXML
    private JFXTextArea tratamientoAlergia;

    @FXML
    private JFXButton btnAddAlergia;

    @FXML
    private StackPane stackOperacion;

    @FXML
    private JFXTextField nombreOp;

    @FXML
    private JFXButton btnAddOp;

    @FXML
    private JFXDatePicker fechaOp;
    
    @FXML
    void btnEnfermedadAction(ActionEvent event) {
	    	diaEnfermedadParent = new JFXDialog(rootStack,diaEnfermedad,JFXDialog.DialogTransition.CENTER,true);
	    	stackEnfermedad.setVisible(true);
	    	stackAlergia.setVisible(false);
	    	stackOperacion.setVisible(false);
	    	diaEnfermedadParent.show();
    }
    
    @FXML
    void btnOperacionAction(ActionEvent event) {
	    	diaEnfermedadParent = new JFXDialog(rootStack,diaEnfermedad,JFXDialog.DialogTransition.CENTER,true);
	    	stackEnfermedad.setVisible(false);
	    	stackAlergia.setVisible(false);
	    	stackOperacion.setVisible(true);
	    	diaEnfermedadParent.show();
    }
    @FXML
    void btnAlergiasAction(ActionEvent event) {
	    	diaEnfermedadParent = new JFXDialog(rootStack,diaEnfermedad,JFXDialog.DialogTransition.CENTER,true);
	    	stackEnfermedad.setVisible(true);
	    	stackAlergia.setVisible(true);
	    	stackOperacion.setVisible(false);
	    	diaEnfermedadParent.show();
    }
    
    @FXML
    void loadAdd(ActionEvent event) {
	    adminS.addResidente();
    }

    @FXML
    void loadMed(ActionEvent event) {
    		adminS.addMedicamento();
    }

    @FXML
    void loadResidente(ActionEvent event) {
    		adminS.loadResidentes();
    }

    @FXML
    void loadSub(ActionEvent event) {
    }
    
    @FXML
    void addResidenteAction(ActionEvent event) {
    	
    }
    
    @FXML
    void addEnfermedad(ActionEvent event) {
    		int length = enfermedadesScroll.getChildren().size();
    		System.out.println(length);
    		try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/enfermedadCell.fxml"));
	        AnchorPane pane = loader.load();
	        enfermedadCellController controller = loader.getController();
	        controller.setValues(
	        		nombreEnfermedad.getText().toString(),
	        		sintomasEnfermedad.getText().toString(),
	        		tratamientoEnfermedad.getText().toString()
	        		);
	        pane.setLayoutY(length*100-1);
	        enfermedadesScroll.setPrefHeight(length*100-1+100);
	        enfermedadesScroll.getChildren().add(pane);
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	
    		diaEnfermedadParent.close();
    		nombreEnfermedad.setText("");
    		sintomasEnfermedad.setText("");
    		tratamientoEnfermedad.setText("");
    }
    
    @FXML
    void addAlergia(ActionEvent event) {
    		int length = enfermedadesScroll.getChildren().size();
		System.out.println(length);
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/enfermedadCell.fxml"));
        AnchorPane pane = loader.load();
        enfermedadCellController controller = loader.getController();
        controller.setValues(
        		nombreAlergia.getText().toString(),
        		sintomaAlergia.getText().toString(),
        		tratamientoAlergia.getText().toString()
        		);
        pane.setLayoutY(length*100-1);
        enfermedadesScroll.setPrefHeight(length*100-1+100);
        enfermedadesScroll.getChildren().add(pane);
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		diaEnfermedadParent.close();
		nombreAlergia.setText("");
		sintomaAlergia.setText("");
		tratamientoAlergia.setText("");
    }
    
    @FXML
    void addOperacion(ActionEvent event) {
    		int length = operacionesScroll.getChildren().size();
		System.out.println(length);
		try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operacionCell.fxml"));
	        AnchorPane pane = loader.load();
	        operacionCellController controller = loader.getController();
	        controller.setValues(
	        		nombreOp.getText().toString(),
	        		fechaOp.getValue().toString()
	        	);
	        pane.setLayoutY(length*100-1);
	        operacionesScroll.setPrefHeight(length*100-1+100);
	        operacionesScroll.getChildren().add(pane);
		}catch (IOException e) {
			e.printStackTrace();
		}
	
		diaEnfermedadParent.close();
		nombreAlergia.setText("");
		sintomaAlergia.setText("");
		tratamientoAlergia.setText("");
    }

    @FXML
    void initialize() {
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'AgregarResidente.fxml'.";
        assert btnEnfermedad != null : "fx:id=\"btnEnfermedad\" was not injected: check your FXML file 'AgregarResidente.fxml'.";
        assert btnOperacion != null : "fx:id=\"btnOperacion\" was not injected: check your FXML file 'AgregarResidente.fxml'.";
        assert btnAlergias != null : "fx:id=\"btnAlergias\" was not injected: check your FXML file 'AgregarResidente.fxml'.";
        assert diaEnfermedad != null : "fx:id=\"diaEnfermedad\" was not injected: check your FXML file 'AgregarResidente.fxml'.";
        
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("selecciona imagen");
        imageView.setOnMouseClicked((MouseEvent e) -> {
        		FileChooser filechooser = new FileChooser();
        		filechooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        		File Selectedfile = filechooser.showOpenDialog(Main.primaryStage);
        		if(Selectedfile!=null) {
        			System.out.println(Selectedfile.getPath());
        			imageView.setImage(new Image(Selectedfile.toURI().toString()));
        		}
        });
        
        Lugar.getItems().add(new Label("piso 1"));
        Lugar.getItems().add(new Label("piso 3"));
        Lugar.getItems().add(new Label("piso 2"));
        for(int i=1;i<=30;i++) {
        		Cama.getItems().add(new Label("cama "+i));        	
        }
        familiarRelacion.getItems().add(new Label("Hijo(a)"));
        familiarRelacion.getItems().add(new Label("Nieto(a)"));
        familiarRelacion.getItems().add(new Label("Hermano(a)"));
        
        Lugar.setConverter(new StringConverter<Label>() {
        		@Override
	        	public String toString(Label object) {
	        		return object==null?"":object.getText();
	        	}

			@Override
			public Label fromString(String string) {
				return new Label(string);
			}
        });
        
        Cama.setConverter(new StringConverter<Label>() {
	    		@Override
	        	public String toString(Label object) {
	        		return object==null?"":object.getText();
	        	}
	
			@Override
			public Label fromString(String string) {
				return new Label(string);
			}
	    });
        
        familiarRelacion.setConverter(new StringConverter<Label>() {
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
}
