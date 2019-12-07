package com.example.evaluacion.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.evaluacion.DAO.Equipo;
import com.example.evaluacion.Helpers.DatabaseHelper;

import java.util.ArrayList;

public class EquipoDAL {
    private DatabaseHelper dbHelper;
    private Equipo equipo;

    public EquipoDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.equipo = new Equipo();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public EquipoDAL(Context context, Equipo equipo) {
        this.dbHelper = new DatabaseHelper(context);
        this.equipo = equipo;
    }

    public ArrayList<Equipo> seleccionar() {
        ArrayList<Equipo> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM equipo", null);

        if (consulta.moveToFirst()) {
            do {
                int serie = consulta.getInt(0);
                String marca = consulta.getString(1);
                String descripcion = consulta.getString(2);

                Equipo equipo = new Equipo(serie, marca, descripcion);
                lista.add(equipo);


            } while (consulta.moveToNext());

        }
        return lista;
    }

    public boolean actualizar(Equipo equipo)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("marca", equipo.getMarca());
        c.put("descripcion", equipo.getDescripcion());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "equipo",
                    c,
                    "serie = ?",
                    new String[] { String.valueOf(equipo.getSerie()) }
            );
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean eliminar(int serie) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasAfectadas;

        try {
            filasAfectadas = db.delete("equipo","serie = ?",
                    new String[] { String.valueOf(serie) });
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);

    }

    public Equipo getEquipo()
    {
        return this.equipo;
    }

}
