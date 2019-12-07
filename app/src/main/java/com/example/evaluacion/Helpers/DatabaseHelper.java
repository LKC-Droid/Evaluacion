package com.example.evaluacion.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "evaluacion.db";
    public static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE equipo(serie INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT, descripcion TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Insert into equipo(marca,descripcion) VALUES('DELL','Notebook')");
        db.execSQL("Insert into equipo(marca,descripcion) VALUES('ASUS','Notebook')");
        db.execSQL("Insert into equipo(marca,descripcion) VALUES('XIAMI','Notebook')");
        db.execSQL("Insert into equipo(marca,descripcion) VALUES('HP','Notebook')");
    }
}
