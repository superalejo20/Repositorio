package co.edu.software;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.sun.codemodel.JOp;
import com.sun.enterprise.ee.cms.core.Action;

import co.edu.software.entidades.Empleado;
import co.edu.software.entidades.Persona;
import co.edu.software.excepciones.ElementoNoEncontradoException;
import co.edu.software.excepciones.ElementoRepetidoException;
import co.edu.software.modelo.AdministradorDelegado;
import co.edu.software.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManejadorEscenarios {
	
	//public static final ManejadorEscenarios instanciaEscenarios= new ManejadorEscenarios();
	private AdministradorDelegado administradorDelegado = AdministradorDelegado.administradorDelegado;
	
	public Stage stage; 
    public Parent root ;
    public FXMLLoader loader = new FXMLLoader();
	
    
//	public static ManejadorEscenarios getInstancia() {
//		return instanciaEscenarios;
//	}	

	
	public void validarAdministrador(ActionEvent event, Button iniciarSesion, String usuario, String contrasena) {
		try {
			
			Persona persona = administradorDelegado.buscarPersonaCed(usuario);
			
			if(persona!=null) {
				if( persona.getClave().equals(contrasena) ){
			  		  vistaGestionEmpleados(event, iniciarSesion);			
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña invalida");
				}
			}
			
		} catch (ElementoNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
	}
	
	public void vistaGestionEmpleados(ActionEvent event, Button boton) {
		try {
			 if(event.getSource()==boton){
	  			  //Obtener referencia a la Escena del botón         
	  			  stage=(Stage) boton.getScene().getWindow();
	  			  //cargar el otro documento, en este caso la segundo pantalla
	  			  root = loader.load(getClass().getResource("./vista/listarEmpleados.fxml").openStream());
	  			  Scene scene = new Scene(root);
	  			  stage.setScene(scene);
	  			  stage.show();
	  		 }		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void agregarEmpleado(ActionEvent event, Button btnAgregarEmpleado) {
		try {
			   if(event.getSource()==btnAgregarEmpleado){
			          //Obtener referencia a la Escena del botón         
			          stage=(Stage)btnAgregarEmpleado.getScene().getWindow();
			          //cargar el otro documento, en este caso la segundo pantalla
			      	  root = loader.load(getClass().getResource("./vista/RegistrarEmpleado.fxml").openStream());
			      	  Scene scene = new Scene(root);
			      	  stage.setScene(scene);
			      	  stage.show();
			      }	  			  
		  		 	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	public void validarRegistroEmpleado(ActionEvent event, Button btnRegistrar, Empleado emp) {
		
		try {
			Empleado empleado = administradorDelegado.registrarEmpleado(emp);
			
			if(empleado!=null) {
				Utilidades.mostrarMensaje("Registro", "El estudiante se registro con exito");
				vistaGestionEmpleados(event, btnRegistrar);
			}
			
		} catch (ElementoRepetidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	


}
