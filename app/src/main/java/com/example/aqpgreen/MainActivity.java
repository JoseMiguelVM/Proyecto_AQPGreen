package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cambioaRegistro = findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        Button Login = findViewById(R.id.botonLoginCompleto); // Ingresar a la otra interfaz

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
                Intent i = new Intent(MainActivity.this, menuOpciones.class);
                startActivity(i);
            }
        });
    }
}

