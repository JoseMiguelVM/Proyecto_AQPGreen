package com.example.aqpgreen.ui.Comunidad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Denuncias.DenunciaDBController;

public class Denuncias extends Fragment {

    private DenunciaDBController db_denuncias;
    private EditText et_dni_denunciante,
            et_nombre_denunciante,
            et_ubicacion_denunciante,
            et_descripcion_denunciante;
    private Button btn_registrar_denuncia;

    public Denuncias() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_denuncias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_denuncias = new DenunciaDBController(getContext());
        inicializar_elementos(view);

        btn_registrar_denuncia.setOnClickListener(v -> guardar_datos());

    }

    private void inicializar_elementos (View view) {
        et_dni_denunciante = view.findViewById(R.id.DNI_denunciante);
        et_nombre_denunciante = view.findViewById(R.id.nombre_denunciante);
        et_ubicacion_denunciante = view.findViewById(R.id.ubicacion_denunciante);
        et_descripcion_denunciante = view.findViewById(R.id.descripcion_denuncia);
        btn_registrar_denuncia = view.findViewById(R.id.botonRegistrarDenuncia);
    }

    private void guardar_datos () {
        String dni = et_dni_denunciante.getText().toString();
        String nombres = et_nombre_denunciante.getText().toString();
        String ubicacion = et_ubicacion_denunciante.getText().toString();
        String descripcion = et_descripcion_denunciante.getText().toString();

        if (dni.isEmpty() || nombres.isEmpty() || ubicacion.isEmpty() || descripcion.isEmpty()){
            Toast.makeText(getContext(), "Revise los datos ingresados", Toast.LENGTH_SHORT).show();
        }
        else {
            db_denuncias.open();
            Long confirm = db_denuncias.insert(dni, nombres, ubicacion, descripcion);
            db_denuncias.close();

            if (confirm == -1){
                Toast.makeText(getContext(), "Error, intentelo nuevamente ", Toast.LENGTH_SHORT).show();
            }
            else {
                et_dni_denunciante.setText("");
                et_nombre_denunciante.setText("");
                et_ubicacion_denunciante.setText("");
                et_descripcion_denunciante.setText("");
                Toast.makeText(getContext(), "Denuncia guardada exitosamente!!!", Toast.LENGTH_SHORT).show();
            }


        }
    }
}