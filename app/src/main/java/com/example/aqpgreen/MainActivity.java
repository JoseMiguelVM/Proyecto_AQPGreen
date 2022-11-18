package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    Button btnLogin, btnRegistro;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cambioaRegistro = findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        // Para dirigirse al form de registro
        cambioaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(MainActivity.this, Registro.class);
                startActivity(intentNuevo);
            }
        });
/*
        user=(EditText) findViewById(R.id.LnombreUsuarioEdit);
        pass=(EditText) findViewById(R.id.LcontraseñaEdit);
        btnLogin=(Button) findViewById(R.id.botonLoginCompleto);
        //btnRegistro=(Button) findViewById(R.id.botonRegistroCompleto);

        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);*/

    }
/*
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonLoginCompleto:
                break;
            case R.id.botonRegistroCompleto:
                Intent i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
                break;
        }
    }*/
}

