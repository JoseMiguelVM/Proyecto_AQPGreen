package com.example.aqpgreen.ui.ModuloOrganizacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqpgreen.R;

public class LoginOrganizacion extends Fragment {

    // TODO: Declaracion de Variables
    private EditText entrada_usuario;
    private EditText entrada_contraseña;
    private Button btn_acceder_organizacion;

    public LoginOrganizacion() {
        // Required empty public constructor
    }

    public void inicializar_elementos (View view) {
        entrada_usuario = view.findViewById(R.id.editTextTextUsuario);
        entrada_contraseña = view.findViewById(R.id.editTextPassword);
        btn_acceder_organizacion = view.findViewById(R.id.buttonIngresar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_organizacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        btn_acceder_organizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.menuOrganizacion);
            }
        });

    }

}