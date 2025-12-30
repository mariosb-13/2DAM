package com.example.practica_room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tarea", foreignKeys = {@ForeignKey(entity = Usuario.class, parentColumns = "idUsuario", childColumns = "idUsuario", onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Categoria.class, parentColumns = "idCategoria", childColumns = "idCategoria", onDelete = ForeignKey.CASCADE)})
public class Tarea {
    @PrimaryKey(autoGenerate = true)
    public int idTarea;

    public String titulo;
    public String descripcion;
    public String fecha;
    public boolean completada;

    // Claves foráneas (Foreign Keys)
    @ColumnInfo(name = "idCategoria")
    public int idUsuario;

    @ColumnInfo(name = "idUsuario")
    public int idCategoria;


    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}