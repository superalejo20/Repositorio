package co.edu.software.entidades;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.software.entidades.Empleado;

/**
 * Información general de un administrador
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = Administrador.LISTAR_ADMINISTRADOR, query = "select c from Administrador c"),
		@NamedQuery(name = Administrador.CONTAR_ADMINISTRADOR, query = "select count(a) from Administrador a")})
public class Administrador extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_ADMINISTRADOR = "listarAdministrador";
	/**
	 * variable estatica que permite contar adm
	 */
	public static final String CONTAR_ADMINISTRADOR = "contarAdministrador";

	public Administrador() {
		super();
	}
	
	/**
	 * Se encarga de concatenar los atributos
	 */
	@Override
	public String toString() {
		return "Administrador: \n" + super.toString() ;
	}

}
