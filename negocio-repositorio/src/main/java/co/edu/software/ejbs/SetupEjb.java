package co.edu.software.ejbs;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.software.entidades.Administrador;
import co.edu.software.entidades.Empleado;
import co.edu.software.entidades.Estado;

/**
 * se encarga de cargar la preconfiguracion de la capa de negocio
 * @author John Alejandro Gonzalez
 * @version 1.0 
 */
@Singleton
@LocalBean
@Startup
public class SetupEjb {

	/**
	 * permite manejar las transacciones en la bd
	 */
	@PersistenceContext
	private EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public SetupEjb() {
        
    }
    
    /**
     * carga la preconfiguracion
     */
    @PostConstruct
    private void init() {
    	
    	TypedQuery<Long> query= entityManager.createNamedQuery(Administrador.CONTAR_ADMINISTRADOR, Long.class);
    	long conteoAdmin= query.getSingleResult();
    	if(conteoAdmin == 0) {
  		
    		Administrador administrador = new Administrador();
    		administrador.setCedula("1234567890");
    		administrador.setNombre("robinson");
    		administrador.setApellido("pulgarin");
    		administrador.setEmail("robinson@hotmail.com");
    		administrador.setClave("12345");
    		administrador.setFechaNacimiento(new Date());
    		administrador.setEstado(Estado.activo);
    		entityManager.persist(administrador);
    		}
    	

		
    	
    	}

}
