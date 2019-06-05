/**
 * 
 */
package co.edu.software.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import co.edu.software.ejbs.AdminEJBRemote;
import co.edu.software.entidades.Empleado;
import co.edu.software.entidades.Persona;
import co.edu.software.excepciones.ElementoNoEncontradoException;
import co.edu.software.excepciones.ElementoRepetidoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * 
 * @author EinerZG
 * @version 1.0
 */
public class AdministradorDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private AdminEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradorDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() {
		try {
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradorDelegado == null) {
			administradorDelegado = new AdministradorDelegado();
			return administradorDelegado;
		}
		return administradorDelegado;
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue registrado
	 * @throws ElementoRepetidoException 
	 */
	public Empleado registrarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		return adminEJB.insertarEmpleado(empleado);
	}
	
	/**
	 * permite modificar persona
	 * 
	 * @param persona
	 * @return
	 * @see baloterabolt.co.persona.PersonaEJBRemote#modificarPersona(baloterabolt.co.Persona)
	 */
	public boolean modificarPersona(String cedula, Persona persona) {
		return adminEJB.modificarPersona(cedula, persona);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 * @throws ElementoNoEncontradoException 
	 */
	public List<Empleado> listarEmpleado() throws ElementoNoEncontradoException {
		return adminEJB.listarEmpleados();
	}


	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 * @throws ElementoNoEncontradoException 
	 */
	public ObservableList<EmpleadoObservable> listarEmpleadosObservables() throws ElementoNoEncontradoException {
		List<Empleado> empleados = listarEmpleado();
		ObservableList<EmpleadoObservable> empleadosObservables = FXCollections.observableArrayList();
		for (Persona persona : empleados) {
			empleadosObservables.add(new EmpleadoObservable(persona));
		}
		return empleadosObservables;
	}
	
	/**
	 * permite buscar persona
	 * 
	 * @param cedula
	 * @return
	 * @throws NoExisteException
	 * @see baloterabolt.co.persona.PersonaEJBRemote#buscarPersona(java.lang.String)
	 */
	public Persona buscarPersonaCed(String cedula) throws ElementoNoEncontradoException {
		return adminEJB.buscarPersona(cedula);
	}
	
	public Empleado actualizarEmpleado(Empleado empleado) {
		return adminEJB.actualizarEmpleado(empleado);
	}
	
	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 */

	public boolean eliminarEmpleado(Empleado empleado) {
		return adminEJB.eliminarEmpleado(empleado);
	}
	
	public Empleado buscarEmpleado(String cedula) {
		return adminEJB.buscarEmpleado(cedula);
	}

}
