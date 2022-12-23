package com.example.aqpgreen.ui.ModuloOrganizacion.Estadisticas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.aqpgreen.R;



public class EstadisticasGenerales extends Fragment {

    private ImageButton btn_regresar_fragment;

    public EstadisticasGenerales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estadisticas_generales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);
        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());
    }

    private void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
    }
}