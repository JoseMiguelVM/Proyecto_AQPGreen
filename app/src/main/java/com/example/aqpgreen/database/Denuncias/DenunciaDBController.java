package com.example.aqpgreen.database.Denuncias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aqpgreen.database.Denuncias.DenunciaDBController;
import com.example.aqpgreen.database.Denuncias.ConectorDenunciasDB;

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

    public void insert(String dni, String nombres, String ubicacion, String descripcion) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ConectorDenunciasDB.DNI, dni);
        contentValue.put(ConectorDenunciasDB.NOMBRES_COMPLETOS, nombres);
        contentValue.put(ConectorDenunciasDB.UBICACION, ubicacion);
        contentValue.put(ConectorDenunciasDB.DESCRIPCION, descripcion);
        database.insert(ConectorDenunciasDB.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { ConectorDenunciasDB.DNI, ConectorDenunciasDB.NOMBRES_COMPLETOS, ConectorDenunciasDB.UBICACION,
                ConectorDenunciasDB.DESCRIPCION, ConectorDenunciasDB.DESCRIPCION};
        Cursor cursor = database.query(ConectorDenunciasDB.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
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
