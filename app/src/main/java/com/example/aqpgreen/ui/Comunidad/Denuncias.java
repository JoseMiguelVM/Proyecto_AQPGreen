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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Denuncias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Denuncias extends Fragment {
    private DenunciaDBController dbManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Denuncias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Denuncias.
     */
    // TODO: Rename and change types and number of parameters
    public static Denuncias newInstance(String param1, String param2) {
        Denuncias fragment = new Denuncias();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dbManager = new DenunciaDBController(getContext());
        dbManager.open();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText dni_denunciante_Edit = (EditText) view.findViewById(R.id.DNI_denunciante);
        EditText nombre_denunciante_Edit = (EditText) view.findViewById(R.id.nombre_denunciante);
        EditText ubicacion_denunciante_Edit = (EditText) view.findViewById(R.id.ubicacion_denunciante);
        EditText descripcion_denunciante_Edit = (EditText) view.findViewById(R.id.descripcion_denuncia);
        Button registrardenuncia_btn = (Button) view.findViewById(R.id.botonRegistrarDenuncia);
        registrardenuncia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = dni_denunciante_Edit.getText().toString();
                String nombres = nombre_denunciante_Edit.getText().toString();
                String ubicacion = ubicacion_denunciante_Edit.getText().toString();
                String descripcion = descripcion_denunciante_Edit.getText().toString();
                dbManager.insert(dni, nombres, ubicacion, descripcion);
                dbManager.close();
                Toast.makeText(getActivity(), "Denuncia guardada exitosamente!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_denuncias, container, false);
    }
}