package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aqpgreen.database.Usuarios.AdministradorUsuariosDB;

public class MainActivity extends AppCompatActivity {
    private AdministradorUsuariosDB dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cambioaRegistro = findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        Button Login = findViewById(R.id.botonLoginCompleto); // Ingresar a la otra interfaz
        EditText loginEdit = (EditText) findViewById(R.id.LnombreUsuarioEdit);
        EditText passwordEdit = (EditText) findViewById(R.id.LcontraseñaEdit);
        dbManager = new AdministradorUsuariosDB(this);
        dbManager.open();
        // Para dirigirse al form de registro
        cambioaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(MainActivity.this, Registro.class);
                startActivity(intentNuevo);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ExisteDuplaUsuarioContrasena(dbManager.fetch(),loginEdit.getText().toString(),passwordEdit.getText().toString())){
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(i);
                }

            }
        });
    }

    @SuppressLint("Range")
    private boolean ExisteDuplaUsuarioContrasena(Cursor cursor,String login,String password){
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                if(cursor.getString( cursor.getColumnIndex("login") ).equals(login)
                        && cursor.getString( cursor.getColumnIndex("password") ).equals(password)){
                    cursor.close();
                    return true;
                }
            }
        } finally {
            cursor.close();
        }
        return false;
    }
}

