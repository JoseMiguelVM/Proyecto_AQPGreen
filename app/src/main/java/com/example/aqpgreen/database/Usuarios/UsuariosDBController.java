package com.example.aqpgreen.database.Usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class UsuariosDBController {

    private ConectorUsuariosDB dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    public UsuariosDBController(Context c) {
        context = c;
    }

    public UsuariosDBController open() throws SQLException {
        dbHelper = new ConectorUsuariosDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public int insert(String login, String email, String password) {

        int exito;

        try{
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorUsuariosDB.LOGIN, login);
            contentValue.put(ConectorUsuariosDB.EMAIL, email);
            contentValue.put(ConectorUsuariosDB.PASSWORD, password);
            exito = (int) database.insertOrThrow(ConectorUsuariosDB.TABLE_NAME, null, contentValue);
        }

        catch(SQLiteException e){
            dbHelper.DBcreateTable(database);
            ContentValues contentValue = new ContentValues();
            contentValue.put(ConectorUsuariosDB.LOGIN, login);
            contentValue.put(ConectorUsuariosDB.EMAIL, email);
            contentValue.put(ConectorUsuariosDB.PASSWORD, password);
            exito = (int) database.insert(ConectorUsuariosDB.TABLE_NAME, null, contentValue);
        }

        return exito;
    }

    public Cursor fetch() {
        String[] columns = new String[] { ConectorUsuariosDB.IDUSUARIO, ConectorUsuariosDB.LOGIN, ConectorUsuariosDB.EMAIL, ConectorUsuariosDB.PASSWORD};
        Cursor cursor = database.query(ConectorUsuariosDB.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch(String _usuario) {
        return database.rawQuery("SELECT * FROM " + ConectorUsuariosDB.TABLE_NAME +
                " WHERE " + ConectorUsuariosDB.LOGIN + " = \""+ _usuario + "\"", null);
    }

    public Cursor fetch(String _usuario, String _contrasena) {
        return database.rawQuery("SELECT * FROM " + ConectorUsuariosDB.TABLE_NAME +
                " WHERE " + ConectorUsuariosDB.LOGIN + " = \""+ _usuario +
                "\" AND " + ConectorUsuariosDB.PASSWORD + "=\"" + _contrasena + "\"", null);
    }

    public int update(long idusuario, String login, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConectorUsuariosDB.LOGIN, login);
        contentValues.put(ConectorUsuariosDB.EMAIL, email);
        contentValues.put(ConectorUsuariosDB.PASSWORD, password);
        return database.update(ConectorUsuariosDB.TABLE_NAME, contentValues, ConectorUsuariosDB.IDUSUARIO + " = " + idusuario, null);
    }

    public void delete(long idusuario) {
        database.delete(ConectorUsuariosDB.TABLE_NAME, ConectorUsuariosDB.IDUSUARIO + "=" + idusuario, null);
    }

}
