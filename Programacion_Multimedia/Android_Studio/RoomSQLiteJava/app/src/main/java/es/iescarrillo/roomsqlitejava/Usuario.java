package es.iescarrillo.roomsqlitejava;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Usuario {
    @PrimaryKey
    @NonNull
    public String usuario;
    public String nombre;
    public String correo;

    public Usuario(@NonNull String usuario, String nombre, String correo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;

    }

    @NonNull
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
