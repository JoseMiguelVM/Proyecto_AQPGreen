package com.example.aqpgreen.ui.Sesion;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccederFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccederFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Declaracion de Variables
    private UsuariosDBController dbManager;
    private EditText et_usuario;
    private EditText et_contrasena;
    private Button btn_ir_registro;

    private Button btn_acceder;
    private CheckBox check_guardar_sesion;

    private String et_usuario_string;
    private String et_contrasena_string;

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor_preferencias;

    private Button btn_login_organizacion;

    public AccederFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccederFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccederFragment newInstance(String param1, String param2) {
        AccederFragment fragment = new AccederFragment();
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
        return inflater.inflate(R.layout.fragment_acceder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        dbManager = new UsuariosDBController(getContext());
        inicializar_elementos(view);
        dbManager.open();

        btn_ir_registro.setOnClickListener(v -> navController.navigate(R.id.registroFragment));

        btn_acceder.setOnClickListener(v -> {

            et_usuario_string = et_usuario.getText().toString();
            et_contrasena_string = et_contrasena.getText().toString();

            if (dbManager.fetch(et_usuario_string, et_contrasena_string).getCount() != 0){
                guardar_sesion(et_usuario_string, check_guardar_sesion.isChecked());
                navController.navigate(R.id.menuFragment);
            }
            else {
                Toast.makeText(getContext(), "No se encuentra el usuario o\nla contraseña es errónea", Toast.LENGTH_SHORT).show();
            }
        });

        // Para redirigirse a Login de Organizacion
        btn_login_organizacion.setOnClickListener(v -> navController.navigate(R.id.loginOrganizacion));

    }

    /*private boolean revisar_sesion_activa() {
        return preferencias.getBoolean("sesion", false);
    }*/

    private void guardar_sesion(String _usuario, boolean _guardar_sesion) {
        editor_preferencias.putString("usuario", _usuario);
        editor_preferencias.putBoolean("sesion", _guardar_sesion);
        editor_preferencias.apply();
    }

    private void inicializar_elementos(View view) {
        btn_ir_registro = view.findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        btn_acceder = view.findViewById(R.id.botonLoginCompleto); // Ingresar a la otra interfaz
        et_usuario = view.findViewById(R.id.LnombreUsuarioEdit);
        et_contrasena = view.findViewById(R.id.LcontraseñaEdit);
        check_guardar_sesion = view.findViewById(R.id.checkbox_guardarSesion);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        editor_preferencias = preferencias.edit();

        // Login de organizacion
        btn_login_organizacion = view.findViewById(R.id.redireccionOrganizacion);
    }
}