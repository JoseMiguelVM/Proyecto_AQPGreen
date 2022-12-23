package com.example.aqpgreen.ui.Sesion;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;

public class RegistroFragment extends Fragment {

    // TODO: Declaracion de variables
    private UsuariosDBController db_usuarios;
    private NavController navController;
    private EditText et_usuario;
    private EditText et_correo;
    private EditText et_contrasena;
    private Button btn_registrar;
    private Button btn_regresar_acceso;
    private ImageView iv_logo;

    public RegistroFragment() {
        // Required empty public constructor
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

        db_usuarios = new UsuariosDBController(getContext());
        navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        btn_registrar.setOnClickListener(v -> registrar_datos());
        btn_regresar_acceso.setOnClickListener(v -> navController.popBackStack());
    }

    private void registrar_datos (){

        String et_usuario_string = et_usuario.getText().toString();
        String et_correo_string = et_correo.getText().toString();
        String et_contrasena_string = et_contrasena.getText().toString();

        if (et_usuario_string.isEmpty() || et_contrasena_string.isEmpty() || et_contrasena_string.isEmpty()) {
            Toast.makeText(getContext(), "NO deje campos vacíos", Toast.LENGTH_SHORT).show();
        }
        else {
            db_usuarios.open();
            Cursor cursor = db_usuarios.fetch(et_usuario_string);
            if (cursor.getCount() != 0){
                Toast.makeText(getContext(), "Ya existen un usuario con ese nombre", Toast.LENGTH_SHORT).show();
                db_usuarios.close();
            }
            else {
                db_usuarios.insert(et_usuario_string, et_correo_string, et_contrasena_string);
                db_usuarios.close();
                Toast.makeText(getContext(), "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }
        }
    }

    private void inicializar_elementos (View view) {
        et_usuario = view.findViewById(R.id.RnombreUsuarioEdit);
        et_correo = view.findViewById(R.id.Rcorreo);
        et_contrasena = view.findViewById(R.id.RcontraseñaEdit);
        btn_registrar = view.findViewById(R.id.botonRegistroCompleto);
        btn_regresar_acceso = view.findViewById(R.id.logIn);
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