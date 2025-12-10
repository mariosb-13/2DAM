package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ObservacionId implements Serializable {

	private static final long serialVersionUID = 1452321001255655686L;
	// Estos nombres deben coincidir con los atributos de la clase Observacion
	private String nombreCientifico;
	private Integer codObs;
	private Date fecha;

	public ObservacionId() {
	}

	public ObservacionId(String nombreCientifico, Integer codObs, Date fecha) {
		this.nombreCientifico = nombreCientifico;
		this.codObs = codObs;
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ObservacionId that = (ObservacionId) o;
		return Objects.equals(nombreCientifico, that.nombreCientifico) && Objects.equals(codObs, that.codObs)
				&& Objects.equals(fecha, that.fecha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreCientifico, codObs, fecha);
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public Integer getCodObs() {
		return codObs;
	}

	public void setCodObs(Integer codObs) {
		this.codObs = codObs;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}