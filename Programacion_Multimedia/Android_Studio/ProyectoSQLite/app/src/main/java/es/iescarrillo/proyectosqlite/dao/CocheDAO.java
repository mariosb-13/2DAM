package es.iescarrillo.proyectosqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.iescarrillo.proyectosqlite.database.DatabaseHelper;
import es.iescarrillo.proyectosqlite.entidades.Coche;

public class CocheDAO {
    private DatabaseHelper dbHelper;

    public CocheDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insertarCoche(Coche coche) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matricula", coche.getMatricula());
        values.put("modelo", coche.getModelo());
        values.put("precio_venta", coche.getPrecio_venta());
        values.put("id_motor", coche.getId_Motor());
        values.put("id_marca", coche.getId_Motor());
        values.put("id_proveedor", coche.getId_Motor());
        return db.insert("coche", null, values);
    }

    public ArrayList<Coche> obtenerCoches(){
        ArrayList<Coche> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM coche", null);

        if (cursor.moveToFirst()) {
            do {
                Coche c= new Coche();
                c.setId(cursor.getInt(0));
                c.setMatricula(cursor.getString(1));
                c.setModelo(cursor.getString(2));
                c.setPrecio_venta(cursor.getDouble(3));
                lista.add(c);
            }while (cursor.moveToNext());
        }
        return lista;
    }


}
