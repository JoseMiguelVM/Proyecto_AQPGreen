package com.example.aqpgreen.database.Noticias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectorNoticiasDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "NOTICIAS";
    public static final String IDNOTICIA = "idnoticia";
    public static final String TITULO = "titulo";
    public static final String FECHA_PUBLICACION = "fecha";
    public static final String DESCRIPCION = "descripcion";

    static final String DB_NAME = "aqpgreen.db";
    static final int DB_VERSION = 3;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + IDNOTICIA
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITULO + " TEXT NOT NULL, "
            + FECHA_PUBLICACION + " TEXT NOT NULL, "
            + DESCRIPCION + " TEXT NOT NULL);";

    public ConectorNoticiasDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void DBcreateTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DBcreateTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
