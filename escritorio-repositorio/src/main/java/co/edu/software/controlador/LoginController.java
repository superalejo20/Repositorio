package co.edu.software.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import co.edu.software.ManejadorEscenarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class LoginController implements Initializable{
	
    
  	
    @FXML
    private TextField txtUsuario;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label LabelContrasena;

    @FXML
    private Button IniciarSesion;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Hyperlink recuperarContrasena;
    
    public String usuario,contrasena;
    
    public ManejadorEscenarios manejador = new ManejadorEscenarios();
    
    @FXML
    void IniciarSesion(ActionEvent event) {     	
      usuario=txtUsuario.getText();
      contrasena=txtContrasena.getText();
      
      manejador.validarAdministrador(event, IniciarSesion, usuario, contrasena);
      
    }

    @FXML
    void RecuperarContrasena(ActionEvent event) {
    
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	


}
