package com.example.aqpgreen.database.Denuncias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectorDenunciasDB  extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "DENUNCIAS";

    public static final String IDDENUNCIAS = "iddenuncias";
    public static final String DNI = "login";
    public static final String NOMBRES_COMPLETOS = "email";
    public static final String UBICACION = "password";
    public static final String DESCRIPCION = "password";

    static final String DB_NAME = "aqpgreen.db";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + IDDENUNCIAS
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DNI + " TEXT NOT NULL, " + NOMBRES_COMPLETOS + " TEXT NOT NULL, " + UBICACION
            + "  TEXT NOT NULL, " + DESCRIPCION + "  TEXT NOT NULL);";

    public ConectorDenunciasDB(Context context) {
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
