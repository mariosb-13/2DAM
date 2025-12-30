package com.example.practica_room.entities;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class UsuarioConTareas {
    @Embedded
    public Usuario usuario;

    @Relation(
            parentColumn = "idUsuario",   // La PK en Usuario
            entityColumn = "idUsuario"    // La FK en Tarea
    )
    public List<Tarea> tareas;
}