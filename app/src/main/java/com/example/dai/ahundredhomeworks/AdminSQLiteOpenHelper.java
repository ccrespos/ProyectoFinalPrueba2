package com.example.dai.ahundredhomeworks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // al crear la primera versión de la App, se crea una tabla en la base de datos
        db.execSQL("create table usuario(nombre string primary key, contra text, tipo text, descripcion text, celular text, correo text, institucion text, fortaleza text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(nombre string primary key, contra text, tipo text, descripcion text, celular text, correo text, institucion text, fortaleza text)");

    }

}