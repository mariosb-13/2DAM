package es.iescarrillo.sqlite_ejercicioclase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "usuariosPrueba.db";
    public static final int DB_VERSION = 2;

    public static final String TABLE_USUARIOS =
            "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "correo TEXT)";

    public static final String TABLE_TAREAS =
            "CREATE TABLE tarea (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuarioid INTEGER NOT NULL," +
                    "titulo TEXT NOT NULL," +
                    "FOREIGN KEY (usuarioid) REFERENCES usuarios(id) ON DELETE CASCADE)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIOS);
        db.execSQL(TABLE_TAREAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS tarea");
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
