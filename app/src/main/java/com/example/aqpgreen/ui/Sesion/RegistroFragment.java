package com.example.aqpgreen.ui.Sesion;

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

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Declaracion de variables
    private UsuariosDBController dbManager;
    private EditText et_usuario;
    private EditText et_correo;
    private EditText et_contrasena;
    private Button btn_registrar;
    private Button btn_regresar_acceso;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbManager = new UsuariosDBController(getContext());
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        dbManager.open();

        btn_registrar.setOnClickListener(v -> {
            String et_usuario_string = et_usuario.getText().toString();
            String et_correo_string = et_correo.getText().toString();
            String st_contrasena_string = et_contrasena.getText().toString();

            dbManager.insert(et_usuario_string, et_correo_string, et_correo_string);
            dbManager.close();
            navController.popBackStack();
        });

        btn_regresar_acceso.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void inicializar_elementos (View view) {
        et_usuario = (EditText) view.findViewById(R.id.RnombreUsuarioEdit);
        et_correo = (EditText) view.findViewById(R.id.Rcorreo);
        et_contrasena = (EditText) view.findViewById(R.id.RcontraseñaEdit);
        btn_registrar = (Button) view.findViewById(R.id.botonRegistroCompleto);
        btn_regresar_acceso = (Button) view.findViewById(R.id.logIn);
    }
}