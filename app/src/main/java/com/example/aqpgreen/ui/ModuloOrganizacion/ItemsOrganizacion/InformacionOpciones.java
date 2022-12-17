package com.example.aqpgreen.ui.ModuloOrganizacion.ItemsOrganizacion;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
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


public class InformacionOpciones extends Fragment {
    // TODO: Declaracion de Variabless
    private LinearLayout homeLayout;
    private LinearLayout likeLayout;
    private LinearLayout notificationLayout;
    private LinearLayout termLayout;

    // Imagenes para redireccionar a mas informacion de los apartados
    private ImageView infoPeticionesRecibidas;
    private ImageView infoDenunciasRecibidas;
    private ImageView infoPublicarNoticias;
    private ImageView infoEstadisticas;

    public InformacionOpciones() {
        // Required empty public constructor
    }

    public void inicializar_elementos (View view) {
        homeLayout = view.findViewById(R.id.homeLayout);
        likeLayout = view.findViewById(R.id.likeLayout);
        notificationLayout = view.findViewById(R.id.notificationLayout);
        termLayout = view.findViewById(R.id.termLayout);
        infoPeticionesRecibidas = view.findViewById(R.id.infoPeticionesRecibidas);
        infoDenunciasRecibidas = view.findViewById(R.id.infoDenunciasRecibidas);
        infoPublicarNoticias = view.findViewById(R.id.infoPublicarNoticias);
        infoEstadisticas = view.findViewById(R.id.infoEstadisticas);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacion_opciones, container, false);
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

        // Para abrir mas informacion y la organizacion sepa su funcionamiento
        infoPeticionesRecibidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {navController.navigate(R.id.infoExtraPeticiones);}
        });

        infoDenunciasRecibidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {navController.navigate(R.id.infoExtraDenuncias);}
        });

        infoPublicarNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {navController.navigate(R.id.infoExtraNoticias);}
        });

        infoEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {navController.navigate(R.id.infoExtraEstadistica);}
        });
    }

}