package com.example.aqpgreen.ui.Comunidad;

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
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aqpgreen.R;

public class ComunidadFragment extends Fragment{

    private ImageButton btn_regresar_fragment;
    private Button btn_ir_premios;
    private Button btn_ir_denuncia;

    public ComunidadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comunidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        btn_regresar_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        btn_ir_premios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.listaDePremios);
            }
        });

        btn_ir_denuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.denuncias);
            }
        });
    }

    public void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        btn_ir_premios = view.findViewById(R.id.Btn_listaDePremios);
        btn_ir_denuncia = view.findViewById(R.id.Btn_denuncias);
    }
}