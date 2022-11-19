package com.example.aqpgreen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AdministradorUsuariosDB {

    private ConectorUsuariosDB dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public AdministradorUsuariosDB(Context c) {
        context = c;
    }

    public AdministradorUsuariosDB open() throws SQLException {
        dbHelper = new ConectorUsuariosDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String login, String email, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ConectorUsuariosDB.LOGIN, login);
        contentValue.put(ConectorUsuariosDB.EMAIL, email);
        contentValue.put(ConectorUsuariosDB.PASSWORD, password);
        database.insert(ConectorUsuariosDB.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { ConectorUsuariosDB.IDUSUARIO, ConectorUsuariosDB.LOGIN, ConectorUsuariosDB.EMAIL, ConectorUsuariosDB.PASSWORD};
        Cursor cursor = database.query(ConectorUsuariosDB.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long idusuario, String login, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConectorUsuariosDB.LOGIN, login);
        contentValues.put(ConectorUsuariosDB.EMAIL, email);
        contentValues.put(ConectorUsuariosDB.PASSWORD, password);
        int i = database.update(ConectorUsuariosDB.TABLE_NAME, contentValues, ConectorUsuariosDB.IDUSUARIO + " = " + idusuario, null);
        return i;
    }

    public void delete(long idusuario) {
        database.delete(ConectorUsuariosDB.TABLE_NAME, ConectorUsuariosDB.IDUSUARIO + "=" + idusuario, null);
    }

}
