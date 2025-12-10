package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ObservacionPK implements Serializable {
    private static final long serialVersionUID = 4905435721220415302L;
	private String especie;    // Debe llamarse igual que el campo en Observacion
    private Integer observador; // Debe llamarse igual que el campo en Observacion
    private Date fecha;

    public ObservacionPK() {}

    public ObservacionPK(String especie, Integer observador, Date fecha) {
        this.especie = especie;
        this.observador = observador;
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObservacionPK that = (ObservacionPK) o;
        return Objects.equals(especie, that.especie) &&
               Objects.equals(observador, that.observador) &&
               Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especie, observador, fecha);
    }
}