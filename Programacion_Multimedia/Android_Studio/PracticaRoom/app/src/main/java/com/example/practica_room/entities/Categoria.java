package com.example.practica_room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoria")
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    public int idCategoria;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    public Categoria(String descripcion, String nombre, int idCategoria) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }

    public Categoria() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}

