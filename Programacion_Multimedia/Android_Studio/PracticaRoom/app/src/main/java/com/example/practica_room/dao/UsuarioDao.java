package com.example.practica_room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.practica_room.entities.Usuario;
import com.example.practica_room.entities.UsuarioConTareas;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void insertarUsuario(Usuario usuario);

    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerUsuarios();

    @Query("SELECT * FROM usuario WHERE nombre = :nombre LIMIT 1")
    Usuario obtenerUsuarioPorNombre(String nombre);

    @Query("SELECT * FROM usuario WHERE idUsuario = :id")
    Usuario obtenerUsuarioPorId(int id);

    @Transaction
    @Query("SELECT * FROM usuario")
    List<UsuarioConTareas> obtenerUsuariosConSusTareas();
}