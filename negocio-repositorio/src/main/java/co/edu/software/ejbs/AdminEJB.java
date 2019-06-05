package co.edu.software.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.software.entidades.Administrador;
import co.edu.software.entidades.Empleado;
import co.edu.software.entidades.Estado;
import co.edu.software.entidades.Persona;
import co.edu.software.excepciones.ElementoNoEncontradoException;
import co.edu.software.excepciones.ElementoRepetidoException;

/**
 * Maneja todas las operaciones asociadas a los adminstradores
 * 
 * @author EinerZG
 * @version 1.0
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	/**
	 * permite hacer todas las transacciones de la base de datos
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public AdminEJB() {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * baloterabolt.co.persona.PersonaBeanRemote#buscarPersona(java.lang.String)
	 */
	public Persona buscarPersona(String cedula) throws ElementoNoEncontradoException {

		Persona persona = entityManager.find(Persona.class, cedula);
		if (persona == null) {
			throw new ElementoNoEncontradoException("Lo siento, la persona no se pudo encontrar");
		}
		return persona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#insertarEmpleado(co.edu.uniquindio
	 * .grid.entidades.Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		if (entityManager.find(Persona.class, empleado.getCedula()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con esta cedula");
		} else if (buscarPersonaPorEmail(empleado.getEmail()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con este email");
		}
		try {
			empleado.setEstado(Estado.activo);
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#insertarEmpleado(co.edu.uniquindio
	 * .grid.entidades.Empleado)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * baloterabolt.co.persona.PersonaBeanRemote#modificarPersona(baloterabolt.co.
	 * Persona)
	 */
	public boolean modificarPersona(String cedula, Persona personanueva) {
		try {
			Persona persona = buscarPersona(cedula);
			if (persona != null) {
				persona = personanueva;
				entityManager.merge(persona);
				return true;
			}
		} catch (ElementoNoEncontradoException e) {
			return false;
		}
		return false;

	}
	
	public Empleado insertarAdministrador(Administrador administrador) throws ElementoRepetidoException {
		if (entityManager.find(Persona.class, administrador.getCedula()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con esta cedula");
		} else if (buscarPersonaPorEmail(administrador.getEmail()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con este email");
		}
		try {
			administrador.setEstado(Estado.activo);
			entityManager.persist(administrador);
			return administrador;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#eliminarPersona(java.lang.String)
	 */
	public Persona eliminarPersona(String cedula) throws ElementoNoEncontradoException {
		Persona persona = entityManager.find(Empleado.class, cedula);
		if (persona == null) {
			throw new ElementoNoEncontradoException("Persona no encontrada en los registros");
		} else {
			entityManager.remove(persona);			
			return persona;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#listarEmpleados()
	 */
	public List<Empleado> listarEmpleados()  {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Empleado.class);
		List<Empleado> empleados = query.getResultList();
		
		for (Empleado empleado : empleados) {
			System.out.println(empleado.toString());
		}

		return empleados;
	}

	/**
	 * permite buscar una personas usando su email
	 * 
	 * @param email email de la presona
	 * @return persona con el email especificado
	 */
	private Persona buscarPersonaPorEmail(String email) {

		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_POR_EMAIL, Persona.class);
			query.setParameter("email", email);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * baloterabolt.co.persona.PersonaBeanRemote#buscarPersona(java.lang.String)
	 */
	public Persona buscarPersonaCed(String cedula) throws ElementoNoEncontradoException {

		Persona persona = entityManager.find(Persona.class, cedula);
		if (persona == null) {
			throw new ElementoNoEncontradoException("Lo siento, la persona no se pudo encontrar");
		}
		return persona;
	}
	
	
	public Empleado actualizarEmpleado(Empleado empleado) {
		if (entityManager.find(Empleado.class, empleado.getCedula()) != null) {
			entityManager.merge(empleado);
			
			return empleado;
		}
		return null;
	}
	
	public boolean eliminarEmpleado(Empleado empleado) {
		if (entityManager.find(Persona.class, empleado.getCedula()) != null) {
			if (!entityManager.contains(empleado)) {
				empleado = entityManager.merge(empleado);
			}
			entityManager.remove((Persona) empleado);
			return true;
		}
		return false;
	}
	
	public Empleado buscarEmpleado(String cedula) {
		try {
			
			TypedQuery<Empleado> query=entityManager.createNamedQuery(Empleado.BUSCAR_EMPLEADO, Empleado.class);
			query.setParameter("cedula", cedula);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

}
