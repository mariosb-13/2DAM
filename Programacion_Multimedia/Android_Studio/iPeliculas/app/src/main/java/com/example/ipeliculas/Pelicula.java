package com.example.ipeliculas;

public class Pelicula {
    protected String titulo,sinopsis,genero,director,fechaEstreno;

    protected float valoracion;
    protected int imagen;

    public Pelicula(String titulo, String genero, float valoracion, String sinopsis, int imagen, String director, String fechaEstreno) {
        this.titulo = titulo;
        this.genero = genero;
        this.valoracion = valoracion;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.director = director;
        this.fechaEstreno = fechaEstreno;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getGenero() {
        return genero;
    }


    public float getValoracion() {
        return valoracion;
    }


    public String getSinopsis() {
        return sinopsis;
    }


    public int getImagen() {
        return imagen;
    }

    public String getDirector() {
        return director;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }
}
