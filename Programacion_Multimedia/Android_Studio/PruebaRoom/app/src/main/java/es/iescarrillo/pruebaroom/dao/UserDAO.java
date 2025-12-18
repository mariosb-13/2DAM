package es.iescarrillo.pruebaroom.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import es.iescarrillo.pruebaroom.entidades.User;
import es.iescarrillo.pruebaroom.entidades.UserAndLibrary;
import es.iescarrillo.pruebaroom.entidades.UserWithPlaylist;

public interface UserDAO {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);


    /**
     * Relacion 1:1
     * Método que devuelve todas las instancias de la clase de datos que vincula la entidad principal (Usuario) con la secundaria (Library)
     */
    @Transaction
    @Query("SELECT * FROM users")
    List<UserAndLibrary> getUsersAndLibraries();

    /**
     * Relacion 1:N
     * Método que devuelve todas las instancias de la clase de datos que vincula la entidad principal (Usuario) con la secundaria (Playlist)
     * @return
     */
    @Transaction
    @Query("SELECT * FROM users")
    List<UserWithPlaylist> getUsersWithPlaylist();
}
