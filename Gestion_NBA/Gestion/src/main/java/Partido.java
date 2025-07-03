package java;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partidos")
public class Partido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Date fecha;
	@Id
	@JoinColumn(name="id_equipo_local")
	@ManyToOne()
    private Equipo local;
	@Id
	@JoinColumn(name="id_equipo_visitante")
	@ManyToOne()
    private Equipo visitante;
	@Column(name="resultado_local")
	private int resultadoLocal;
	@Column(name="resultado_visitante")
	private int resultadoVisitante;
	
	Partido(){}
	
	public Partido(Date fecha, Equipo local, Equipo visitante, int resultadoLocal, int resultadoVisitante) {
		super();
		this.fecha = fecha;
		this.local = local;
		this.visitante = visitante;
		this.resultadoLocal = resultadoLocal;
		this.resultadoVisitante = resultadoVisitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	public int getResultadoLocal() {
		return resultadoLocal;
	}

	public void setResultadoLocal(int resultadoLocal) {
		this.resultadoLocal = resultadoLocal;
	}

	public int getResultadoVisitante() {
		return resultadoVisitante;
	}

	public void setResultadoVisitante(int resultadoVisitante) {
		this.resultadoVisitante = resultadoVisitante;
	}
}
