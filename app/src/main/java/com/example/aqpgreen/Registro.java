package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aqpgreen.database.Usuarios.AdministradorUsuariosDB;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity {
    private AdministradorUsuariosDB dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        EditText loginEdit = (EditText) findViewById(R.id.RnombreUsuarioEdit);
        EditText correoEdit = (EditText) findViewById(R.id.Rcorreo);
        EditText contrasenaEdit = (EditText) findViewById(R.id.RcontraseñaEdit);
        Button registrarBtn = (Button) findViewById(R.id.botonRegistroCompleto);
        dbManager = new AdministradorUsuariosDB(this);
        dbManager.open();

        // Registrando usuario con los datos a la BD
        registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginEdit.getText().toString();
                String correo = correoEdit.getText().toString();
                String contrasena = contrasenaEdit.getText().toString();
                dbManager.insert(login, correo, contrasena);
                dbManager.close();
                //iniciar Mainactivity
                Intent intentNuevo = new Intent(Registro.this, MainActivity.class);
                startActivity(intentNuevo);
            }
        });
    }
}