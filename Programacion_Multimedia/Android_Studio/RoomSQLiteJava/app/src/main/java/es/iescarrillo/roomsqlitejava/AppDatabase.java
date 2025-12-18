package es.iescarrillo.roomsqlitejava;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Define la BBDD--solo tiene la tabla Usuario como entidad
@Database(
        entities = {Usuario.class},
        version = 1
)

public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoUsuario daoUSuario();
}







