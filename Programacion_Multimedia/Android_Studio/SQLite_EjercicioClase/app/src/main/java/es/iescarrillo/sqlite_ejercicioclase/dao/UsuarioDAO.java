package es.iescarrillo.sqlite_ejercicioclase.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import es.iescarrillo.sqlite_ejercicioclase.database.DatabaseHelper;
import es.iescarrillo.sqlite_ejercicioclase.entidades.Usuario;

import java.util.ArrayList;

public class UsuarioDAO {

    private DatabaseHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // ---------- CREATE ----------
    public long insertar(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", usuario.getNombre());
        valores.put("correo", usuario.getCorreo());
        return db.insert("usuarios", null, valores);
    }

    // ---------- READ ----------
    public ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios", null);

        if (cursor.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cursor.getInt(0));
                u.setNombre(cursor.getString(1));
                u.setCorreo(cursor.getString(2));
                lista.add(u);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    // ---------- UPDATE ----------
    public int actualizar(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", usuario.getNombre());
        valores.put("correo", usuario.getCorreo());
        return db.update("usuarios", valores, "id=?", new String[]{String.valueOf(usuario.getId())});
    }

    // ---------- DELETE ----------
    public int eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("usuarios", "id=?", new String[]{String.valueOf(id)});
    }
}
