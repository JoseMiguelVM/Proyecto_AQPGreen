package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aqpgreen.FragmentsMenu.FragmentComunidad;
import com.example.aqpgreen.FragmentsMenu.FragmentEstadisticas;
import com.example.aqpgreen.FragmentsMenu.FragmentHuellaPlastico;
import com.example.aqpgreen.FragmentsMenu.FragmentNoticias;
import com.example.aqpgreen.FragmentsMenu.FragmentReciclajeGreen;
import com.example.aqpgreen.FragmentsMenu.FragmentTipoPlastico;

public class menuOpciones extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        // Botones definidos para el redireccionamiento a los Fragments
        Button redirigeHuellaPlastico = findViewById(R.id.reciclajeGreen);
        Button redirigeFragmentTipo = findViewById(R.id.tipoPlastico);
        Button redirigeFragmentReciclaje = findViewById(R.id.reciclajeGreen);
        Button redirigeFragmentEstadisticas = findViewById(R.id.estadisticas);
        Button redirigeFragmentNoticias = findViewById(R.id.noticiasGreen);
        Button redirigeFragmentComunidad = findViewById(R.id.comunidadGreen);

        //CardViews definidos para oder ocultar lo innecesario en el proceso de redireccion
        CardView ocultaCard1 = findViewById(R.id.cardView);
        CardView ocultaCard3 = findViewById(R.id.cardView2);
        CardView ocultaCard2 = findViewById(R.id.cardView3);
        CardView ocultaCard4 = findViewById(R.id.cardView4);
        CardView ocultaCard5 = findViewById(R.id.cardView5);
        CardView ocultaCard6 = findViewById(R.id.cardView6);

        //1. Click en Opcion para redirigirse a Fragment "Huella de Plastico"
        redirigeHuellaPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHuellaPlastico Huella = new FragmentHuellaPlastico();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Huella, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });

        //2. Click en Opcion para redirigirse a Fragment "Tipo de Plastico"
        redirigeFragmentTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTipoPlastico Tipo = new FragmentTipoPlastico();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Tipo, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });

        //3. Click en Opcion para redirigirse a Fragment "Reciclaje Green"
        redirigeFragmentReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentReciclajeGreen Reciclaje = new FragmentReciclajeGreen();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Reciclaje, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });

        //4. Click en Opcion para redirigirse a Fragment "Estadisticas"
        redirigeFragmentEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentEstadisticas Estadistica = new FragmentEstadisticas();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Estadistica, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });

        //5. Click en Opcion para redirigirse a Fragment "Noticias Green"
        redirigeFragmentNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNoticias Noticia = new FragmentNoticias();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Noticia, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });

        //6. Click en Opcion para redirigirse a Fragment "Comunidad Green"
        redirigeFragmentComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentComunidad Comunidad = new FragmentComunidad();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.general, Comunidad, null);
                fragmentTransaction.commit();
                //Ocultando CARDS al momento de entrar al fragment deseado
                ocultaCard1.setVisibility(View.GONE);
                ocultaCard2.setVisibility(View.GONE);
                ocultaCard3.setVisibility(View.GONE);
                ocultaCard4.setVisibility(View.GONE);
                ocultaCard5.setVisibility(View.GONE);
                ocultaCard6.setVisibility(View.GONE);
            }
        });
    }
}