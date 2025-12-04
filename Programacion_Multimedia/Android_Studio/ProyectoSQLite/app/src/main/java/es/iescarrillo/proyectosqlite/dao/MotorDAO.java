package es.iescarrillo.proyectosqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.database.DatabaseHelper;

public class MotorDAO {
    private DatabaseHelper dbHelper;

    public MotorDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<String> obtenerNombresMotores() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM motor", null);
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public String obtenerNombreMotor(int id) {
        String nombre = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM motor WHERE id_motor = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }
        cursor.close();
        return nombre;
    }
}
