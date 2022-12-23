package com.example.aqpgreen.database.Peticiones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

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

    public long insert(String usuario, String categoria, int cantidad,
                       String origen, String descripcion, int puntos, int estado, String urlFoto) {
        try{
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorPeticionesDB.USUARIO, usuario);
            contentValue.put(ConectorPeticionesDB.CATEGORIA, categoria);
            contentValue.put(ConectorPeticionesDB.CANTIDAD, cantidad);
            contentValue.put(ConectorPeticionesDB.ORIGEN, origen);
            contentValue.put(ConectorPeticionesDB.DESCRIPCION, descripcion);
            contentValue.put(ConectorPeticionesDB.PUNTOS, puntos);
            contentValue.put(ConectorPeticionesDB.ESTADO, estado); // 3 ESTADOS (0 ESPERA, 1 ACEPTADO, 2 RECHAZADO)
            contentValue.put(ConectorPeticionesDB.URLFOTO, urlFoto);
            return database.insertOrThrow(ConectorPeticionesDB.TABLE_NAME, null, contentValue);
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorPeticionesDB.USUARIO, usuario);
            contentValue.put(ConectorPeticionesDB.CATEGORIA, categoria);
            contentValue.put(ConectorPeticionesDB.CANTIDAD, cantidad);
            contentValue.put(ConectorPeticionesDB.ORIGEN, origen);
            contentValue.put(ConectorPeticionesDB.DESCRIPCION, descripcion);
            contentValue.put(ConectorPeticionesDB.PUNTOS, puntos);
            contentValue.put(ConectorPeticionesDB.ESTADO, estado); // 3 ESTADOS (0 ESPERA, 1 ACEPTADO, 2 RECHAZADO)
            contentValue.put(ConectorPeticionesDB.URLFOTO, urlFoto);
            return database.insert(ConectorPeticionesDB.TABLE_NAME, null, contentValue);
        }
    }

    public Cursor fetch (){
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME, null);
            return cursor;
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME, null);
            return cursor;
        }
    }

    public Cursor fetch(Long _id) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME + " WHERE "
                    + ConectorPeticionesDB.IDPETICION + "=\"" + _id + "\"", null);
            return cursor;
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME + " WHERE "
                    + ConectorPeticionesDB.IDPETICION + "=\"" + _id + "\"", null);
            return cursor;
        }
    }

    public int fetch_Distrito_Origen_count (String distrito){
        try {
            Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM  PETICIONES where lugarOrigen = '" + distrito + "'", null);
            int count=0;
            if(null != cursor)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
            cursor.close();
            return count;
        }
        catch(SQLiteException e){
            return 0;
        }
    }

    public int fetch_Distrito_Origen_sum (String distrito){
        try {
            Cursor cursor = database.rawQuery("SELECT SUM(cantidad) FROM  PETICIONES where lugarOrigen = '" + distrito + "'", null);
            int count=0;
            if(null != cursor)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
                }
            cursor.close();
            return count;
        }
        catch(SQLiteException e){
            return 0;
        }
    }

    public int fetch_Distrito_Origen_cantidad (String distrito){
        try {
            Cursor cursor = database.rawQuery("SELECT cantidad FROM  PETICIONES where lugarOrigen = '" + distrito + "'", null);
            int cantidad=0;
            if(null != cursor)
                if(cursor.getCount() > 0){
                    cursor.moveToFirst();
                    cantidad = cursor.getInt(0);
                }
            cursor.close();
            return cantidad;
        }
        catch(SQLiteException e){
            return 0;
        }
    }

    public Cursor fetch(String _usuario) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME + " WHERE "
                    + ConectorPeticionesDB.USUARIO + "=\"" + _usuario + "\"", null);
            return cursor;
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorPeticionesDB.TABLE_NAME + " WHERE "
                    + ConectorPeticionesDB.USUARIO + "=\"" + _usuario + "\"", null);
            return cursor;
        }
    }

    public int update(long _idpeticion, String key, int valor){
        ContentValues valores = new ContentValues();
        valores.put(key, valor);
        return database.update(ConectorPeticionesDB.TABLE_NAME, valores, ConectorPeticionesDB.IDPETICION + " = " + _idpeticion, null);
    }
    public int update(long _idpeticion, String key, String valor){
        ContentValues valores = new ContentValues();
        valores.put(key, valor);
        return database.update(ConectorPeticionesDB.TABLE_NAME, valores, ConectorPeticionesDB.IDPETICION + " = " + _idpeticion, null);
    }

    public void delete(long idpeticion) {
        database.delete(ConectorPeticionesDB.TABLE_NAME, ConectorPeticionesDB.IDPETICION + "=" + idpeticion, null);
    }
}
