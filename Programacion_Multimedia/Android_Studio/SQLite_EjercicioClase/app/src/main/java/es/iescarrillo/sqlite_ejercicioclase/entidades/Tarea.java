package es.iescarrillo.sqlite_ejercicioclase.entidades;

public class Tarea {
    private int id,usuarioid;
    private String titulo;


    public Tarea(){}
    public Tarea(int id, int usuarioid, String titulo) {
        this.id = id;
        this.usuarioid = usuarioid;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }
}
