package co.edu.software.controlador;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;

import co.edu.software.ManejadorEscenarios;
import co.edu.software.entidades.Empleado;
import co.edu.software.excepciones.ElementoNoEncontradoException;
import co.edu.software.modelo.AdministradorDelegado;
import co.edu.software.util.Utilidades;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorListarEmpleados implements Initializable{
	
    @FXML
    private TableView<Empleado> tablaEmpleados;

	@FXML
    private TableColumn<Empleado, String> columNombre;

    @FXML
    private TableColumn<Empleado, String> columClave;

    @FXML
    private TableColumn<Empleado, String> columApellidos;
    
    @FXML
    private TableColumn<Empleado, Long> columSalario;
    
    @FXML
    private TableColumn<Empleado, String> columFechaNacimmiento;
    
    @FXML
    private TableColumn<Empleado, String> columCedula;

    @FXML
    private TableColumn<Empleado, String> columCorreo;
    
    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnActualizar;

    @FXML
    private TextField txtClave;
    
    
    public ManejadorEscenarios manejador = new ManejadorEscenarios();
    public AdministradorDelegado delegado = AdministradorDelegado.administradorDelegado;
    public Empleado empleadoSeleccionado;
    private ObservableList<Empleado> listaEmpleados ;
    private int posicionTabla;    




    @FXML
    void buscarEmpleado(ActionEvent event) {
    	try {
    		String cedula = txtBuscar.getText();
        	
        	Empleado empleado = (Empleado)(delegado.buscarEmpleado(cedula));
        	if(empleado!=null) {
            	ObservableList<Empleado> nuevo = FXCollections.observableArrayList(empleado);
            	llenarTabla(nuevo);
        	}else if(cedula.isEmpty()){
        		inicializarListaEmpleados();
        	}else {
        		JOptionPane.showMessageDialog(null, "El Empleado identificado con la cedula: "+cedula+"\nNO se encuentra registrado");
        	}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "La cedula contiene caracteres no validos");
		}
    }

    @FXML
    void regresar(ActionEvent event) {

    }

    @FXML
    void AgregarEmpleado(ActionEvent event) {
        
        manejador.agregarEmpleado( event,  btnAgregarEmpleado);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
		inicializarListaEmpleados();		
		
		eventoTextFieldBuscar();
		
		columNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));		
		columApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellido"));		
		columCedula.setCellValueFactory(new PropertyValueFactory<Empleado,String>("cedula"));		
		columCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("email"));		
		columClave.setCellValueFactory(new PropertyValueFactory<Empleado,String>("clave"));		
		columFechaNacimmiento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("FechaNacimiento"));
		columSalario.setCellValueFactory(new PropertyValueFactory<Empleado,Long>("salario"));
		
		final ObservableList<Empleado> tablaEmpleadosSelect = tablaEmpleados.getSelectionModel().getSelectedItems();
		tablaEmpleadosSelect.addListener(selectTabla);
		
	}
    
    public void eventoTextFieldBuscar() {
		txtBuscar.textProperty().addListener((obs, oldText, newText) -> {
			inicializarListaEmpleados();
		});
	}
	
    private final ListChangeListener<Empleado> selectTabla = new ListChangeListener<Empleado>() {
		
		@Override
		public void onChanged(ListChangeListener.Change<? extends Empleado> c) {
			mostrarFilaSeleccionada();
		}
	};
	
	public Empleado obtenerFilaSeleccionada() {
		if(tablaEmpleados!=null) {
			Empleado empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();
			return empleadoSeleccionado;
		}
		
		return null;
	}
	
	public void mostrarFilaSeleccionada() {
		empleadoSeleccionado = obtenerFilaSeleccionada();
		posicionTabla=listaEmpleados.indexOf(empleadoSeleccionado);
		
		if(empleadoSeleccionado!=null) {
			
			reiniciarCampos();
			txtNombre.setText(empleadoSeleccionado.getNombre());
			txtApellidos.setText(empleadoSeleccionado.getApellido());
			txtCedula.setText(empleadoSeleccionado.getCedula());
			txtEmail.setText(empleadoSeleccionado.getEmail());
			txtClave.setText(empleadoSeleccionado.getClave());
			fechaNacimiento.setValue(Utilidades.pasarALocalDate(empleadoSeleccionado.getFechaNacimiento()));
			txtSalario.setText(empleadoSeleccionado.getSalario()+"");
			
		}
	}
	
	public void reiniciarCampos() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCedula.setText("");
		txtClave.setText("");
		txtEmail.setText("");
		txtSalario.setText("");
		fechaNacimiento.setValue(null);
	}
	
	public void inicializarListaEmpleados() {
		listaEmpleados = FXCollections.observableArrayList(obtenerLista());
		llenarTabla(listaEmpleados);
	}
	
    
    public void llenarTabla(ObservableList<Empleado> lista) {
    	tablaEmpleados.setItems(lista);
    }
    
    public List<Empleado> obtenerLista(){
    	try {
			return delegado.listarEmpleado();
		} catch (ElementoNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
    }
    
    @FXML
    void actualizarEmpleado(ActionEvent event) {
    	if(empleadoSeleccionado!=null) {
    		
    		String nomb = txtNombre.getText();
    		String apell = txtApellidos.getText();
    		String cc = txtCedula.getText();
    		String email = txtEmail.getText();
    		String clave = txtClave.getText();
    		String salario = txtSalario.getText();
    		
    		
    		try {
    			if(!(email.isEmpty()) || !(email.equals(" "))) {
    				empleadoSeleccionado.setNombre(nomb);
    				empleadoSeleccionado.setApellido(apell);
    				empleadoSeleccionado.setCedula(cc);
    				empleadoSeleccionado.setEmail(email);
    				empleadoSeleccionado.setClave(clave);
    				empleadoSeleccionado.setSalario(Double.parseDouble(salario));
    				empleadoSeleccionado.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
        			int cedul = Integer.parseInt(cc);
        			
            		if(delegado.actualizarEmpleado(empleadoSeleccionado)!=null) {
                		JOptionPane.showMessageDialog(null, "La informacion se actualizo con exito");
                		reiniciarCampos();
                		listaEmpleados=FXCollections.observableArrayList(obtenerLista());
                		llenarTabla(listaEmpleados);
                		
            		}else {
                		JOptionPane.showMessageDialog(null, "Error inesperado al actualizar la informacion del cliente");
            		}
    			}else {
    				JOptionPane.showMessageDialog(null, "Complete el campo Correo");
    			}
			} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null, "La cedula o el salario debe ser un dato numerico");
        	} 
    		
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "Seleccione un cliente para actualizar la informacion");
    	}
    }
	
    
    public void eliminarEmpleado() {
		if(delegado.eliminarEmpleado(empleadoSeleccionado)) {
    		reiniciarCampos();
    		Utilidades.mostrarMensaje("Registro", "El estudiante se elimino con exito");
    		listaEmpleados=FXCollections.observableArrayList(obtenerLista());
    		llenarTabla(listaEmpleados);
		}
    }

}