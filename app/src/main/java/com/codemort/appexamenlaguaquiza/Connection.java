package com.codemort.appexamenlaguaquiza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Connection extends SQLiteOpenHelper {


    public Connection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crea las tablas de la base de datos
        //cuando crees otra tabla crea todos los campos de tipo TEXT
        //si creas un campo de otro tipo al momento editar y eliminar cambia un poco el codigo
        //ES MEJOR QUE CREES de tipo TEXT
        db.execSQL("CREATE TABLE pedidos(codigo TEXT PRIMARY KEY, detalle TEXT, total TEXT, tipo TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}