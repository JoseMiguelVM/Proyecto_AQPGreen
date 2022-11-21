package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class menuOpciones extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        Button redirigeFragmentReciclar = findViewById(R.id.reciclajeGreen);
        //Click en Opcion para redirigirse a Fragment "Reciclar"
        redirigeFragmentReciclar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentOpcReciclajeGreen fragmentReciclaje = new FragmentOpcReciclajeGreen();

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, fragmentReciclaje, null);
                fragmentTransaction.commit();
            }
        });


    }


}