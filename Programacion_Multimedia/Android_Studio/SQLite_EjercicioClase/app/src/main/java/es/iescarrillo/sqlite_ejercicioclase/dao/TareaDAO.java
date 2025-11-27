package es.iescarrillo.sqlite_ejercicioclase.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.iescarrillo.sqlite_ejercicioclase.database.DatabaseHelper;
import es.iescarrillo.sqlite_ejercicioclase.entidades.Tarea;

public class TareaDAO {
    private DatabaseHelper dbHelper;

    public TareaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertarTarea(Tarea tarea) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuarioid", tarea.getUsuarioid());
        valores.put("titulo", tarea.getTitulo());
        return db.insert("tarea", null, valores);
    }

    public ArrayList<Tarea> obtenerTodos(){
        ArrayList<Tarea> lista= new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tarea", null);

        if(cursor.moveToFirst()){
            do {
                Tarea t = new Tarea();
                t.setId(cursor.getInt(0));
                t.setTitulo(cursor.getString(1));
                t.setUsuarioid(cursor.getInt(2));
                lista.add(t);
            }while (cursor.moveToNext());
        }

        return lista;
    }


}
