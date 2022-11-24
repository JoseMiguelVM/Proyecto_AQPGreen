package com.example.aqpgreen.ui.Menu;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aqpgreen.R;

public class MenuFragment extends Fragment {
    public MenuFragment() {
        // Required empty publi constructor
    }

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor_preferencias;
    private CardView c_huella;
    private CardView c_tipo_plastico;
    private CardView c_reciclaje;
    private CardView c_estadisticas;
    private CardView c_noticias;
    private CardView c_comunidad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);

        c_huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.huellaPlasticoFragment);
            }
        });

        c_tipo_plastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.tipoPlasticoFragment);
            }
        });

        c_reciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.listaPeticionesFragment);
            }
        });

        c_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.estadisticasFragment);
            }
        });

        c_noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.noticiasFragment);
            }
        });

        c_comunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.comunidadFragment);
            }
        });

    }

    private void inicializar_elementos(View view) {
        c_huella = view.findViewById(R.id.cardView_Huella);
        c_tipo_plastico = view.findViewById(R.id.cardView_TipoPlastico);
        c_reciclaje = view.findViewById(R.id.cardView_Reciclaje);
        c_estadisticas = view.findViewById(R.id.cardView_Estadisticas);
        c_noticias = view.findViewById(R.id.cardView_Noticias);
        c_comunidad = view.findViewById(R.id.cardView_Comunidad);
    }
}