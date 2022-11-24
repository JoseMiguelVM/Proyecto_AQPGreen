package com.example.aqpgreen.database.Peticiones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aqpgreen.database.Usuarios.AdministradorUsuariosDB;
import com.example.aqpgreen.database.Usuarios.ConectorUsuariosDB;

public class PeticionDBController {

    private ConectorPeticionesDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public PeticionDBController(Context c) {
        context = c;
    }

    public PeticionDBController open() throws SQLException {
        dbHelper = new ConectorPeticionesDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public int insert(String usuario, String categoria, int cantidad,
                       String origen, String descripcion, int puntos, int estado, String urlFoto) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ConectorPeticionesDB.USUARIO, usuario);
        contentValue.put(ConectorPeticionesDB.CATEGORIA, categoria);
        contentValue.put(ConectorPeticionesDB.CANTIDAD, cantidad);
        contentValue.put(ConectorPeticionesDB.ORIGEN, origen);
        contentValue.put(ConectorPeticionesDB.DESCRIPCION, descripcion);
        contentValue.put(ConectorPeticionesDB.PUNTOS, puntos);
        contentValue.put(ConectorPeticionesDB.ESTADO, estado); // 3 ESTADOS (0 ESPERA, 1 ACEPTADO, 2 RECHAZADO)
        contentValue.put(ConectorPeticionesDB.URLFOTO, urlFoto);
        return (int) database.insert(ConectorPeticionesDB.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(String _usuario) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME + " WHERE "
                + ConectorPeticionesDB.USUARIO + "=\"" + _usuario + "\"", null);
        return cursor;
    }

    public int update (long idpeticion, String categoria, int cantidad,
                       String origen, String descripcion , String urlFoto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConectorPeticionesDB.CATEGORIA, categoria);
        contentValues.put(ConectorPeticionesDB.CANTIDAD, cantidad);
        contentValues.put(ConectorPeticionesDB.ORIGEN, origen);
        contentValues.put(ConectorPeticionesDB.DESCRIPCION, descripcion);
        contentValues.put(ConectorPeticionesDB.URLFOTO, urlFoto);
        return database.update(ConectorUsuariosDB.TABLE_NAME, contentValues, ConectorPeticionesDB.IDPETICION + " = " + idpeticion, null);
    }

    public void delete(long idpeticion) {
        database.delete(ConectorPeticionesDB.TABLE_NAME, ConectorPeticionesDB.IDPETICION + "=" + idpeticion, null);
    }
}
