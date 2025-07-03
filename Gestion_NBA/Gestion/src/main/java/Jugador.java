package java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jugadores")
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	@Column(name = "maxima_anotacion")
    private int maximaAnotacion;
	@JoinColumn(name = "id_equipo")
	@ManyToOne()
	private Equipo equipo;
	
	public Jugador() {}
	
	public Jugador(String nombre, String apellido, int maximaAnotacion, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.maximaAnotacion = maximaAnotacion;
		this.equipo = equipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getMaximaAnotacion() {
		return maximaAnotacion;
	}
	public void setMaximaAnotacion(int maximaAnotacion) {
		this.maximaAnotacion = maximaAnotacion;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " - " + maximaAnotacion + " puntos";
	}
	
	
}
