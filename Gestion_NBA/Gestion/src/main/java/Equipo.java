package java;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String ciudad;
	@Column(name = "fundacion_year")
	private int anhoFundacion;
	@OneToMany(mappedBy = "equipo")
	private List<Jugador> jugadores;
	
	@OneToMany(mappedBy = "local")
	private List<Partido> partidosLocal;
	@OneToMany(mappedBy = "visitante")
	private List<Partido> partidosVisitante;
	
	public Equipo() {}
	
	public Equipo(String nombre, String ciudad, int anhoFundacion) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.anhoFundacion = anhoFundacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getAnhoFundacion() {
		return anhoFundacion;
	}
	public void setAnhoFundacion(int anhoFundacion) {
		this.anhoFundacion = anhoFundacion;
	}
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Partido> getPartidosLocal() {
		return partidosLocal;
	}

	public void setPartidosLocal(List<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public List<Partido> getPartidosVisitante() {
		return partidosVisitante;
	}

	public void setPartidosVisitante(List<Partido> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}
}
