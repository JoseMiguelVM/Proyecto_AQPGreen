package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button cambioaRegistro = findViewById(R.id.logIn); // Para el cambio de boton de la parte superior del formulario
        // Para dirigirse al form de registro
        cambioaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(Registro.this, MainActivity.class);
                startActivity(intentNuevo);
            }
        });
    }
}