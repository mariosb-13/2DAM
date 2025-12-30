package com.example.practica_room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practica_room.dao.CategoriaDao;
import com.example.practica_room.dao.TareaDao;
import com.example.practica_room.dao.UsuarioDao;
import com.example.practica_room.entities.Categoria;
import com.example.practica_room.entities.Tarea;
import com.example.practica_room.entities.Usuario;

// Definimos las entidades y la versión de la BBDD
@Database(
        entities = {Usuario.class, Categoria.class, Tarea.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDao daoUsuario();

    public abstract CategoriaDao daoCategoria();

    public abstract TareaDao daoTarea();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Creamos la base de datos
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "db_gestion_tareas" // Nombre del archivo de la BBDD
                            )
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}