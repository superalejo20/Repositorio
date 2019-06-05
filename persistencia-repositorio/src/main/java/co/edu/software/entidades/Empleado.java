package co.edu.software.entidades;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.software.entidades.Persona;

/**
 * Informacion general de los empleados
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity

@NamedQueries({
	@NamedQuery(name = Empleado.BUSCAR_EMPLEADO, query = "select empleado from Empleado empleado where empleado.cedula=:cedula"),
	@NamedQuery(name = Empleado.LISTAR_EMPLEADOS, query = "select e from Empleado e")
})
public class Empleado extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * referencia a la consulta para listar todos los empleados
	 */
	public static final String LISTAR_EMPLEADOS = "ListarEmpleados";
	public static final String BUSCAR_EMPLEADO= "BuscarEmpleado";

	/**
	 * salario del empleado en el herbario
	 */
	private double salario;

	public Empleado() {
		super();
	}

	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

}
