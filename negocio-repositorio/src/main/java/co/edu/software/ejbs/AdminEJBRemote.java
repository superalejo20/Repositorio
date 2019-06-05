package co.edu.software.ejbs;

import java.util.List;

import javax.ejb.Remote;


import co.edu.software.entidades.Administrador;
import co.edu.software.entidades.Empleado;
import co.edu.software.entidades.Persona;
import co.edu.software.excepciones.ElementoNoEncontradoException;
import co.edu.software.excepciones.ElementoRepetidoException;

/**
 * Interfaz que representa las transacciones que se pueden realizar desde la
 * capa de presentación
 * 
 * @author EinerZG
 * @version 1.0
 *
 */
@Remote
public interface AdminEJBRemote {

	String JNDI = "java:global/ear-repositorio/negocio-repositorio/AdminEJB!co.edu.software.ejbs.AdminEJBRemote";

	/**
	 * Permite agregar una persona en la base de datos
	 * 
	 * @param empleado empleado a insetar
	 * @return deveuelve el empleado insertado o null
	 * @throws ElementoRepetidoException cuando hay informacion repetida
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException;
	
	/**
	  * Permite buscar a una persona en la aplicacion
	  * @param cedula, identificacion de la persona a buscar
	  * @return la persona buscada
	  * @throws NoExisteException avisa si la persona a buscar no existe
	  */
	 public Persona buscarPersona(String cedula) throws ElementoNoEncontradoException;
	 /**
	  * Permite modificar a una persona registrada
	  * @param persona, persona que desea modificar la informacion
	  * @return true si la persona fue modificada con exito
	  */
	 public boolean modificarPersona(String cedula,Persona persona);
	
	/**
	 * Permite agregar una persona en la base de datos
	 * 
	 * @param administrador empleado a insetar
	 * @return deveuelve el empleado insertado o null
	 * @throws ElementoRepetidoException cuando hay informacion repetida
	 */
	Empleado insertarAdministrador(Administrador administrador) throws ElementoRepetidoException;

	/**
	 * Permite eliminar una persona según por medio de su cedula
	 * 
	 * @param cedula cedula de la persona
	 * @return persona elminada
	 * @throws ElementoNoEncontradoException si la persona no es encontrada
	 */
	Persona eliminarPersona(String cedula) throws ElementoNoEncontradoException;

	/**
	 * Permite mostrar todos loe empleados registrado
	 * 
	 * @return lista de empleado
	 * @throws ElementoNoEncontradoException 
	 */
	List<Empleado> listarEmpleados() ;
	
	/**
	  * Permite buscar a una persona en la aplicacion
	  * @param cedula, identificacion de la persona a buscar
	  * @return la persona buscada
	  * @throws NoExisteException avisa si la persona a buscar no existe
	  */
	 public Persona buscarPersonaCed(String cedula) throws ElementoNoEncontradoException;
	 
	 public Empleado actualizarEmpleado(Empleado empleado);

	 public boolean eliminarEmpleado(Empleado empleado);
	 
	 public Empleado buscarEmpleado(String cedula);
}

