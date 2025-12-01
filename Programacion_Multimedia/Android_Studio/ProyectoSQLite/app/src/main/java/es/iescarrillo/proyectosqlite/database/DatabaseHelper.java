package es.iescarrillo.proyectosqlite.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbCarLog.db";

    public static final int DB_VERSION = 2;

    public static final String TABLE_COCHE =
            "CREATE TABLE coche (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "matricula TEXT," +
                    "modelo TEXT," +
                    "precio_venta FLOAT," +
                    "id_motor INTEGER," +
                    "id_marca INTEGER," +
                    "id_proveedor INTEGER," +
                    "FOREIGN KEY (id_motor) REFERENCES motor(id_motor)," +
                    "FOREIGN KEY (id_marca) REFERENCES marca(id_marca)," +
                    "FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor))";


    public static final String TABLE_MOTOR =
            "CREATE TABLE motor (" +
                    "id_motor INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "etiqueta_ambiental TEXT," +
                    "cilindrada REAL)";

    public static final String TABLE_MARCA =
            "CREATE TABLE marca (" +
                    "id_marca INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "pais TEXT," +
                    "telefono TEXT)";

    public static final String TABLE_PROVEEDOR =
            "CREATE TABLE proveedor (" +
                    "id_proveedor INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "email TEXT," +
                    "dias_entrega INTEGER)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_COCHE);
        db.execSQL(TABLE_MARCA);
        db.execSQL(TABLE_PROVEEDOR);
        db.execSQL(TABLE_MOTOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS coche");
        db.execSQL("DROP TABLE IF EXISTS marca");
        db.execSQL("DROP TABLE IF EXISTS proveedor");
        db.execSQL("DROP TABLE IF EXISTS motor");
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
