package com.example.practica_room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practica_room.entities.Tarea;

import java.util.List;

@Dao
public interface TareaDao {
    @Insert
    void insertarTarea(Tarea tarea);

    @Query("SELECT * FROM tarea")
    List<Tarea> obtenerTodasLasTareas();

}