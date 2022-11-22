package com.example.aqpgreen.database.Peticiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectorPeticionesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "PETICIONES";
    public static final String IDPETICION = "idpeticion";
    public static final String IDUSUARIO = "idusuario";
    public static final String CATEGORIA = "categoria";
    public static final String CANTIDAD = "cantidad";
    public static final String ORIGEN = "lugarOrigen";
    public static final String DESCRPCION = "descripcion";
    public static final String PUNTOS = "puntos";
    public static final String ESTADO = "estado";
    public static final String FOTOURL = "fotoUrl";

    static final String DB_NAME = "aqpgreen.db";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + IDPETICION
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDPETICION + " TEXT NOT NULL, "
            + IDUSUARIO + " TEXT NOT NULL, " + CATEGORIA + "  TEXT NOT NULL,"
            + CANTIDAD + " INTEGER NOT NULL, " + ORIGEN + " TEXT NOT NULL,"
            + DESCRPCION + " TEXT NOT NULL," + PUNTOS + " INTEGER NOT NULL,"
            + ESTADO + " INTEGER NOT NULL," + FOTOURL + " TEXT NOT NULL,"
            + "FOREIGN KEY (" + IDUSUARIO + ") "
            + "REFERENCES USUARIOS (" + IDUSUARIO + ") ON DELETE CASCADE ON UPDATE NO ACTION);";

    public ConectorPeticionesDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
