package es.iescarrillo.roomsqlitejava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoUsuario {

    @Query("SELECT * FROM users")
    List<Usuario> obtenerUsuarios();//Devuelve una lista deUsuarios y enlace el nombre del método

    @Query("SELECT * FROM users where usuario= :user ")
    Usuario obtenerUsuario(String user);

    @Insert
    void insertarUsuario(Usuario...usuarios);//Recibe uno o muchos usuarios

    @Query("UPDATE users SET nombre= :nombre, correo= :correo WHERE usuario= :user")
    void actualizarUsuario(String user,String nombre, String correo );

    @Query ("DELETE FROM users WHERE usuario = :user")
    void borrarUsuario(String user);
}
