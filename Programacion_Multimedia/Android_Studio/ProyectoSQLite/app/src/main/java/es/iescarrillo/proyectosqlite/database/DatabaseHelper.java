package es.iescarrillo.proyectosqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbCarLog.db";
    public static final int DB_VERSION = 3;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE marca (" +
                        "id_marca INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT," +
                        "pais TEXT," +
                        "telefono TEXT)"
        );

        db.execSQL(
                "CREATE TABLE proveedor (" +
                        "id_proveedor INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT," +
                        "email TEXT," +
                        "dias_entrega INTEGER)"
        );

        db.execSQL(
                "CREATE TABLE motor (" +
                        "id_motor INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT," +
                        "etiqueta_ambiental TEXT," +
                        "cilindrada REAL)"
        );

        db.execSQL("INSERT INTO marca (nombre, pais, telefono) VALUES " +
                "('Toyota', 'Japón', '111111111')," +
                "('Seat', 'España', '222222222')," +
                "('BMW', 'Alemania', '333333333')," +
                "('Ford', 'EEUU', '444444444')," +
                "('Audi', 'Alemania', '555555555')," +
                "('Tesla', 'EEUU', '666666666')");

        db.execSQL("INSERT INTO proveedor (nombre, email, dias_entrega) VALUES " +
                "('AutoSpain S.L.', 'ventas@autospain.com', 3)," +
                "('EuroCars Logistics', 'info@eurocars.com', 5)," +
                "('Global Motors', 'contact@globalmotors.com', 7)");

        db.execSQL("INSERT INTO motor (nombre, etiqueta_ambiental, cilindrada) VALUES " +
                "('1.6 VVT-i', 'C', 1.6)," +
                "('1.0 TSI', 'C', 1.0)," +
                "('2.0 Hybrid', 'ECO', 2.0)," +
                "('5.0 V8 Coyote', 'C', 5.0)," +
                "('2.0 TDI', 'B', 2.0)," +
                "('Electric Dual Motor', '0', 0.0)");

        db.execSQL(
                "CREATE TABLE coche (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "matricula TEXT," +
                        "modelo TEXT," +
                        "precio_venta FLOAT," +
                        "id_motor INTEGER," +
                        "id_marca INTEGER," +
                        "id_proveedor INTEGER," +
                        "FOREIGN KEY(id_motor) REFERENCES motor(id_motor)," +
                        "FOREIGN KEY(id_marca) REFERENCES marca(id_marca)," +
                        "FOREIGN KEY(id_proveedor) REFERENCES proveedor(id_proveedor))"
        );

        db.execSQL("INSERT INTO coche (matricula, modelo, precio_venta, id_motor, id_marca, id_proveedor) VALUES " +

                "('1234ABC', 'Toyota Corolla', 21000, 3, 1, 1)," +
                "('5678DEF', 'Seat Ibiza', 15500, 2, 2, 1)," +
                "('9012GHI', 'BMW Serie 1', 28000, 5, 3, 2)," +
                "('3344JWL', 'Ford Mustang GT', 55000, 4, 4, 3)," +
                "('5566KLS', 'Audi A3 Sportback', 32000, 5, 5, 2)," +
                "('7788MNP', 'Tesla Model 3', 46900, 6, 6, 3)," +
                "('9900QRS', 'Toyota RAV4', 38500, 3, 1, 1)," +
                "('1122TUV', 'Seat Leon FR', 24000, 1, 2, 1)," +
                "('3344WXY', 'Audi Q5', 48000, 5, 5, 2)," +
                "('5566ZAB', 'Ford Focus', 19000, 2, 4, 1)");
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