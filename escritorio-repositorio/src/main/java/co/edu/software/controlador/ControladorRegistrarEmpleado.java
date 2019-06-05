package co.edu.software.controlador;

import co.edu.software.ManejadorEscenarios;
import co.edu.software.entidades.Empleado;
import co.edu.software.modelo.AdministradorDelegado;
import co.edu.software.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControladorRegistrarEmpleado {
	
    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnRegistrar;

    public AdministradorDelegado administradorDelegado = AdministradorDelegado.administradorDelegado;
    public ManejadorEscenarios manejador = new ManejadorEscenarios();

    @FXML
    void volver(ActionEvent event) {

    }

    @FXML
    void registrarEmpleado(ActionEvent event) {
    	
    	Empleado empleado = new Empleado();
    	empleado.setNombre(txtNombre.getText());
    	empleado.setApellido(txtApellidos.getText());
    	empleado.setCedula(txtCedula.getText());
    	empleado.setClave(txtContrasena.getText());
    	empleado.setEmail(txtEmail.getText());
    	empleado.setSalario(Long.parseLong(txtSalario.getText()));
    	empleado.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
    	
    	manejador.validarRegistroEmpleado(event, btnRegistrar, empleado);

    }

}
