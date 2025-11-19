package com.example.ipeliculas;

import android.widget.RatingBar;

public class Pelicula {
    protected String titulo;
    protected String genero;
    protected RatingBar valoracion;
    protected String sinopsis;
    protected String imagen;

    public Pelicula(String titulo, String genero, RatingBar valoracion, String sinopsis, String imagen) {
        this.titulo = titulo;
        this.genero = genero;
        this.valoracion = valoracion;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public RatingBar getValoracion() {
        return valoracion;
    }

    public void setValoracion(RatingBar valoracion) {
        this.valoracion = valoracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
