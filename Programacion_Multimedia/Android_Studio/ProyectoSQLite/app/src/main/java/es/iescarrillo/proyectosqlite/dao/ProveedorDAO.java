package es.iescarrillo.proyectosqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.database.DatabaseHelper;

public class ProveedorDAO {
    private DatabaseHelper dbHelper;

    public ProveedorDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<String> obtenerNombresProveedores() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM proveedor", null);
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public String obtenerNombreProveedor(int id) {
        String nombre = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM proveedor WHERE id_proveedor = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }
        cursor.close();
        return nombre;
    }
}
