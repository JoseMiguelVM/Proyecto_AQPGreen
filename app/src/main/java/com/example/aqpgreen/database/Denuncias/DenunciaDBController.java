package com.example.aqpgreen.database.Denuncias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DenunciaDBController {

    private ConectorDenunciasDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DenunciaDBController(Context c) {
        context = c;
    }

    public DenunciaDBController open() throws SQLException {
        dbHelper = new ConectorDenunciasDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String dni, String nombres, String ubicacion, String descripcion) {
        try{
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorDenunciasDB.DNI, dni);
            contentValue.put(ConectorDenunciasDB.NOMBRES_COMPLETOS, nombres);
            contentValue.put(ConectorDenunciasDB.UBICACION, ubicacion);
            contentValue.put(ConectorDenunciasDB.DESCRIPCION, descripcion);
            return database.insertOrThrow(ConectorDenunciasDB.TABLE_NAME, null, contentValue);
        }
        catch (SQLiteException e){
            Log.e("asads", "-1 ocurrio la excepción");
            dbHelper.DBcreateTable(database);
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorDenunciasDB.DNI, dni);
            contentValue.put(ConectorDenunciasDB.NOMBRES_COMPLETOS, nombres);
            contentValue.put(ConectorDenunciasDB.UBICACION, ubicacion);
            contentValue.put(ConectorDenunciasDB.DESCRIPCION, descripcion);
            return database.insert(ConectorDenunciasDB.TABLE_NAME, null, contentValue);
        }

    }

    public Cursor fetch (){
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorDenunciasDB.TABLE_NAME, null);
            return cursor;
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorDenunciasDB.TABLE_NAME, null);
            return cursor;
        }
    }

    public int update(long iddenuncia, String dni, String nombres, String ubicacion, String descripcion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConectorDenunciasDB.DNI, dni);
        contentValues.put(ConectorDenunciasDB.NOMBRES_COMPLETOS, nombres);
        contentValues.put(ConectorDenunciasDB.UBICACION, ubicacion);
        contentValues.put(ConectorDenunciasDB.DESCRIPCION, descripcion);
        int i = database.update(ConectorDenunciasDB.TABLE_NAME, contentValues, ConectorDenunciasDB.IDDENUNCIAS + " = " + iddenuncia, null);
        return i;
    }

    public void delete(long iddenuncias) {
        database.delete(ConectorDenunciasDB.TABLE_NAME, ConectorDenunciasDB.IDDENUNCIAS+ "=" + iddenuncias, null);
    }

}
