package com.example.aqpgreen.database.Noticias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.aqpgreen.database.Usuarios.ConectorUsuariosDB;

public class NoticiaDBController {

    private ConectorNoticiasDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public NoticiaDBController(Context c) {
        context = c;
    }

    public NoticiaDBController open() throws SQLException {
        dbHelper = new ConectorNoticiasDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String titulo, String fecha_publicacion, String descripcion) {
        try{
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorNoticiasDB.TITULO, titulo);
            contentValue.put(ConectorNoticiasDB.FECHA_PUBLICACION, fecha_publicacion);
            contentValue.put(ConectorNoticiasDB.DESCRIPCION, descripcion);
            return database.insertOrThrow(ConectorNoticiasDB.TABLE_NAME, null, contentValue);
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorNoticiasDB.TITULO, titulo);
            contentValue.put(ConectorNoticiasDB.FECHA_PUBLICACION, fecha_publicacion);
            contentValue.put(ConectorNoticiasDB.DESCRIPCION, descripcion);
            return database.insert(ConectorNoticiasDB.TABLE_NAME, null, contentValue);
        }
    }

    public Cursor fetch (){
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorNoticiasDB.TABLE_NAME, null);
            return cursor;
        }
        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            Cursor cursor = database.rawQuery("SELECT * FROM " + ConectorNoticiasDB.TABLE_NAME, null);
            return cursor;
        }
    }

    public int update (long idnoticia, String titulo, String fecha_publicacion, String descripcion) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConectorNoticiasDB.TITULO, titulo);
        contentValues.put(ConectorNoticiasDB.FECHA_PUBLICACION, fecha_publicacion);
        contentValues.put(ConectorNoticiasDB.DESCRIPCION, descripcion);
        return database.update(ConectorUsuariosDB.TABLE_NAME, contentValues, ConectorNoticiasDB.IDNOTICIA + " = " + idnoticia, null);
    }

    public void delete(long idnoticia) {
        database.delete(ConectorNoticiasDB.TABLE_NAME, ConectorNoticiasDB.IDNOTICIA + "=" + idnoticia, null);
    }
}
