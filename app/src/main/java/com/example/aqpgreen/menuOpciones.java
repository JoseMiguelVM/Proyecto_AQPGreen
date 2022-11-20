package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class menuOpciones extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        LinearLayout opcReciclaje=findViewById(R.id.huellaPlastico);

        opcReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentOpcReciclajeGreen fragmentOpcReciclajeGreen = new FragmentOpcReciclajeGreen();

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.menuGeneral, fragmentOpcReciclajeGreen, null);
                fragmentTransaction.commit();
            }
        });

    }


}