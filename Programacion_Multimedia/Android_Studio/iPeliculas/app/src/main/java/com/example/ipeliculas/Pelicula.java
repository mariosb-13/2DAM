package com.example.ipeliculas;

public class Pelicula {
    protected String titulo;
    protected String genero;
    protected float valoracion;
    protected String sinopsis;
    protected int imagen;

    public Pelicula(String titulo, String genero, float valoracion, String sinopsis, int imagen) {
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

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
