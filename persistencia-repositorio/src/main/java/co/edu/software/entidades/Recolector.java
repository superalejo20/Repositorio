package co.edu.software.entidades;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.software.entidades.Persona;

/**
 * Información general de un recolector
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
public class Recolector extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}
}
