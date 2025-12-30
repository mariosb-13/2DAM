package com.example.practica_room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practica_room.entities.Categoria;

import java.util.List;

@Dao
public interface CategoriaDao {
    @Insert
    void insertarCategoria(Categoria categoria);

    @Query("SELECT * FROM categoria")
    List<Categoria> obtenerCategorias();

}