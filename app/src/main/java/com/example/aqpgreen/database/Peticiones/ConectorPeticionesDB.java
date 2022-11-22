package com.example.aqpgreen.database.Peticiones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectorPeticionesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "PETICIONES";
    public static final String IDPETICION = "idPeticion";
    public static final String IDUSUARIO = "idUsuario";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    static final String DB_NAME = "aqpgreen.db";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + IDPETICION
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LOGIN + " TEXT NOT NULL, " + EMAIL + " TEXT NOT NULL, " + PASSWORD + "  TEXT NOT NULL);";

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