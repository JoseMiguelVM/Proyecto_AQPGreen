package com.example.aqpgreen.ui.ModuloOrganizacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aqpgreen.R;
import com.google.android.material.navigation.NavigationView;


public class MenuOrganizacion extends Fragment {
    // TODO: Declaracion de Variables de submenu inferior
    private LinearLayout homeLayout;
    private LinearLayout likeLayout;
    private LinearLayout notificationLayout;
    private LinearLayout termLayout;

    // TODO: Declaracion de variables del menu general
    private LinearLayout peticionesRecibidas;
    private LinearLayout denunciasRecibidas;
    private LinearLayout publicarNoticias;
    private LinearLayout verEstadisticas;

    public MenuOrganizacion() {
        // Required empty public constructor
    }

    public void inicializar_elementos (View view) {
        homeLayout = view.findViewById(R.id.homeLayout);
        likeLayout = view.findViewById(R.id.likeLayout);
        notificationLayout = view.findViewById(R.id.notificationLayout);
        termLayout = view.findViewById(R.id.termLayout);

        peticionesRecibidas = view.findViewById(R.id.peticionesRecibidas);
        denunciasRecibidas = view.findViewById(R.id.denunciasRecibidas);
        publicarNoticias = view.findViewById(R.id.publicarNoticias);
        verEstadisticas = view.findViewById(R.id.verEstadisticas);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_organizacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        homeLayout.setOnClickListener(v -> navController.navigate(R.id.menuOrganizacion));

        likeLayout.setOnClickListener(v -> {
            //if(selectedTab != 2){
                navController.navigate(R.id.informacionOpciones);
        });

        notificationLayout.setOnClickListener(v -> {
            //if(selectedTab != 3){
                navController.navigate(R.id.itemAyudaOpciones);
        });

        termLayout.setOnClickListener(v -> navController.navigate(R.id.terminosYcondiciones2));


        // TODO: Redireccion a los menus generales
        peticionesRecibidas.setOnClickListener(v -> navController.navigate(R.id.revisionPeticiones));
        denunciasRecibidas.setOnClickListener(v -> navController.navigate(R.id.revisarDenuncias));
        publicarNoticias.setOnClickListener(v -> navController.navigate(R.id.noticiasPublicadas));
        verEstadisticas.setOnClickListener(v -> navController.navigate(R.id.estadisticasGenerales));


    }
}