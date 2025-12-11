package es.iescarrillo.proyectosqlite.dao;

import android.content.ContentValues;
import android.content.Context;
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

    public CocheDAO(Context context) {
    }

    public void insertarCoche(Coche coche) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matricula", coche.getMatricula());
        values.put("modelo", coche.getModelo());
        values.put("precio_venta", coche.getPrecio_venta());
        values.put("id_motor", coche.getId_Motor());
        values.put("id_marca", coche.getId_Marca());
        values.put("id_proveedor", coche.getId_Proveedor());

        db.insert("coche", null, values);
    }

    public ArrayList<Coche> obtenerCoches() {
        ArrayList<Coche> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM coche", null);

        if (cursor.moveToFirst()) {
            do {
                Coche c = new Coche();
                c.setId_coche(cursor.getInt(0));
                c.setMatricula(cursor.getString(1));
                c.setModelo(cursor.getString(2));
                c.setPrecio_venta(cursor.getDouble(3));
                c.setId_Motor(cursor.getInt(4));
                c.setId_Marca(cursor.getInt(5));
                c.setId_Proveedor(cursor.getInt(6));

                lista.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void actualizarCoche(Coche coche) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matricula", coche.getMatricula());
        values.put("modelo", coche.getModelo());
        values.put("precio_venta", coche.getPrecio_venta());
        values.put("id_motor", coche.getId_Motor());
        values.put("id_marca", coche.getId_Marca());
        values.put("id_proveedor", coche.getId_Proveedor());

        db.update("coche", values, "id = ?", new String[]{String.valueOf(coche.getId_coche())});
    }

    public void eliminarCoche(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("coche", "id = ?", new String[]{String.valueOf(id)});
    }


}
