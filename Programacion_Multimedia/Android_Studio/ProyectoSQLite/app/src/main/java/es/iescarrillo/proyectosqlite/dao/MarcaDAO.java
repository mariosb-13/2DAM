package es.iescarrillo.proyectosqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.database.DatabaseHelper;

public class MarcaDAO {
    private DatabaseHelper dbHelper;

    public MarcaDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<String> obtenerNombresMarcas() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM marca", null);
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    // Añade este método para buscar el nombre por ID
    public String obtenerNombreMarca(int id) {
        String nombre = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM marca WHERE id_marca = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }
        cursor.close();
        return nombre;
    }

    public int obtenerIdPorNombre(String nombre) {
        int id = -1;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_marca FROM marca WHERE nombre = ?", new String[]{nombre});

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
}
