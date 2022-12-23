package com.example.aqpgreen.ui.ModuloOrganizacion.ItemsOrganizacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aqpgreen.R;

import java.net.URI;


public class ItemAyudaOpciones extends Fragment {
    // TODO: Declaracion de Variables
    private LinearLayout homeLayout;
    private LinearLayout likeLayout;
    private LinearLayout notificationLayout;
    private LinearLayout termLayout;

    public ItemAyudaOpciones() {
        // Required empty public constructor
    }

    public void inicializar_elementos (View view) {
        homeLayout = view.findViewById(R.id.homeLayout);
        likeLayout = view.findViewById(R.id.likeLayout);
        notificationLayout = view.findViewById(R.id.notificationLayout);
        termLayout = view.findViewById(R.id.termLayout);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_ayuda_opciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.menuOrganizacion);
            }
        });

        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.informacionOpciones);
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.itemAyudaOpciones);
            }
        });

        termLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.terminosYcondiciones2);
            }
        });
    }
}