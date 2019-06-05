package co.edu.software;
import java.io.IOException;

import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;



public class Main extends Application {
	
	public Label escena1;
	public Scene  scene;
	public Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
		
	}


	
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage=primaryStage;
		
		
	    Parent root = FXMLLoader.load(getClass().getResource("./vista/Login.fxml"));

	    
	    scene = new Scene(root);// indicamos qué scene contendrá el Parent de cada ventana

	    
	    primaryStage.setTitle("Repositorio");//Esto no es necesario a menos que deses colocar un titulo, iria entre comillas
	    primaryStage.setScene(scene);//declaras la escena de la ventana, en este caso sera la principal
	    primaryStage.show();


	}
	
	
	
	private void ButtonClicked(ActionEvent e) {
	    //aquí en caso de presionar algún boton se declara qué escena deseamos ejecutar
	    //esto podría teóricamente cambiarse con un switch, pero en este caso sólo hacemos intercambio entre dos escenas

	}
	
	
	

}
