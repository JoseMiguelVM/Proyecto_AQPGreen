package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.aqpgreen.ui.TiposPlastico.DetallePlasticoFragment;
import com.example.aqpgreen.ui.TiposPlastico.TipoPlasticoFragment;

public class MenuActivity extends AppCompatActivity implements TipoPlasticoFragment.OnFragmentInteractionListener,
        DetallePlasticoFragment.OnFragmentInteractionListener{

        TipoPlasticoFragment tiposFragment;
        DetallePlasticoFragment detalleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        tiposFragment = new TipoPlasticoFragment();
        detalleFragment = new DetallePlasticoFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, tiposFragment);


    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }
}