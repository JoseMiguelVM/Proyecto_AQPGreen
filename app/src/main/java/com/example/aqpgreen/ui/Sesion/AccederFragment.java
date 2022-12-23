package com.example.aqpgreen.ui.Sesion;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;

public class AccederFragment extends Fragment {

    // TODO: Declaracion de Variables
    private UsuariosDBController db_usuarios;
    private EditText et_usuario;
    private EditText et_contrasena;
    private Button btn_ir_registro;
    private ImageView iv_logo;
    private Button btn_acceder;
    private CheckBox check_guardar_sesion;
    private NavController navController;
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor_preferencias;

    private Button btn_login_organizacion;

    public AccederFragment() {
        // Required empty public constructor
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
        navController = Navigation.findNavController(view);
        db_usuarios = new UsuariosDBController(getContext());
        inicializar_elementos(view);

        btn_ir_registro.setOnClickListener(v -> navController.navigate(R.id.registroFragment));
        btn_acceder.setOnClickListener(v -> comprobar_datos());
        btn_login_organizacion.setOnClickListener(v -> navController.navigate(R.id.loginOrganizacion));

    }

    /*private boolean revisar_sesion_activa() {
        return preferencias.getBoolean("sesion", false);
    }*/
    private void comprobar_datos () {
        String et_usuario_string = et_usuario.getText().toString();
        String et_contrasena_string = et_contrasena.getText().toString();

        if(!et_contrasena_string.isEmpty()){
            if (!et_contrasena_string.isEmpty()){
                db_usuarios.open();
                Cursor cursor = db_usuarios.fetch(et_usuario_string, et_contrasena_string);
                if (cursor.getCount() != 0){
                    guardar_sesion(et_usuario_string, check_guardar_sesion.isChecked());
                    db_usuarios.close();
                    navController.navigate(R.id.menuFragment);
                }
                else {
                    Toast.makeText(getContext(), "No se encuentra las credenciales", Toast.LENGTH_SHORT).show();
                    db_usuarios.close();
                }
            }
            else{
                Toast.makeText(getContext(), "Escriba la contraseña", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getContext(), "Escriba el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardar_sesion(String _usuario, boolean _guardar_sesion) {
        editor_preferencias.putString("usuario", _usuario);
        editor_preferencias.putBoolean("sesion", _guardar_sesion);
        editor_preferencias.apply();
    }

    private void inicializar_elementos(View view) {
        btn_ir_registro = view.findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        btn_acceder = view.findViewById(R.id.botonLoginCompleto); // Ingresar a la otra interfaz
        btn_login_organizacion = view.findViewById(R.id.redireccionOrganizacion);
        et_usuario = view.findViewById(R.id.LnombreUsuarioEdit);
        et_contrasena = view.findViewById(R.id.LcontraseñaEdit);
        check_guardar_sesion = view.findViewById(R.id.checkbox_guardarSesion);
        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        editor_preferencias = preferencias.edit();

        iv_logo = view.findViewById(R.id.iv_logo);

        Glide.with(getContext())
                .load(R.drawable.aqpgreen_logo)
                .centerCrop()
                .placeholder(R.drawable.fragment_reciclaje_icon1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(iv_logo);
    }
}