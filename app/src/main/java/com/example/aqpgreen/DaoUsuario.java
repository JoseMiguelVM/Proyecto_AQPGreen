package com.example.aqpgreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla = "create table usuario(id integer primary key autoincrement, usuario text, correo text, pass text)";

    public DaoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if(buscar(u.getNombreUsuario())==0){ // Verifica si existe ya el usuario o no
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getNombreUsuario());
            cv.put("correo", u.getCorreo());
            cv.put("pass", u.getContraseña());
            return (sql.insert("usuario", null,cv)>0);
        }else{
            return false;
        }
    }

    //Si el contador X devuelve 0 es que no existe ningun usuario con ese nombre y se puede registrar
    //Si el contador X devuelve 1 o mas es que si existe un usuario con ese nombre y no se puede registrar
    public int buscar(String u){
        int x=0;
        lista =selectUsuario();
        for(Usuario us:lista){
            if(us.getNombreUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr != null && cr.moveToFirst()){
            do{
                Usuario u=new Usuario();
                u.setID(cr.getInt(0));
                u.setNombreUsuario(cr.getString(1));
                u.setCorreo(cr.getString(2));
                u.setContraseña(cr.getString(3));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }
}
