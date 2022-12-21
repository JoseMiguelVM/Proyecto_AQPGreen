package com.example.aqpgreen.ui.ModuloOrganizacion.PeticionesRecibidas;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class DatosPeticionesFragment extends Fragment {

    private ImageView img_foto_peticion;
    private TextView tv_usuario,
            tv_correo_usuario,
            tv_descripcion_peticion,
            tv_cantidad_peticion,
            tv_categoria_peticion;
    private TextInputLayout et_puntos_peticion;
    private FloatingActionButton btn_actualizar_peticion, btn_rechazar_peticion;
    private ImageButton btn_regresar_fragment;

    private PeticionDBController db_peticiones;
    private UsuariosDBController db_usuarios;
    private NavController navController;

    private String usuario;
    private Long idpeticion;

    public DatosPeticionesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_peticiones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_peticiones = new PeticionDBController(getContext());
        db_usuarios = new UsuariosDBController(getContext());
        navController = Navigation.findNavController(view);

        inicializar_elementos(view);
        mostrar_datos(view);

        btn_regresar_fragment.setOnClickListener(view1 -> navController.popBackStack());
        btn_actualizar_peticion.setOnClickListener(view1 -> actualizar_puntaje());
        btn_rechazar_peticion.setOnClickListener(view12 -> rechazar_peticion());

    }

    public void inicializar_elementos (View view) {

        usuario = getArguments().getString("usuario");
        idpeticion = getArguments().getLong("idpeticion");

        tv_usuario = view.findViewById(R.id.tv_usuario);
        tv_correo_usuario = view.findViewById(R.id.tv_correo_usuario);
        img_foto_peticion = view.findViewById(R.id.img_foto_peticion);
        tv_descripcion_peticion = view.findViewById(R.id.tv_descripcion_peticion);
        tv_cantidad_peticion = view.findViewById(R.id.tv_cantidad_peticion);
        tv_categoria_peticion = view.findViewById(R.id.tv_categoria_peticion);
        et_puntos_peticion = view.findViewById(R.id.et_puntos_peticion);

        btn_actualizar_peticion = view.findViewById(R.id. btn_actualizar_peticion);
        btn_rechazar_peticion = view.findViewById(R.id.btn_rechazar_peticion);
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
    }

    public void mostrar_datos (View view) {
        try {
            db_peticiones.open();
            Cursor cursor_pet = db_peticiones.fetch(idpeticion);
            if (cursor_pet.getCount() != 0) {
                cursor_pet.moveToFirst();
                Glide.with(view)
                        .load(cursor_pet.getString(8))
                        .centerCrop()
                        .placeholder(R.drawable.fragment_reciclaje_icon1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                        .into(img_foto_peticion);
                tv_descripcion_peticion.setText(cursor_pet.getString(5));
                tv_cantidad_peticion.setText(cursor_pet.getInt(3) + " unidades");
                tv_categoria_peticion.setText(cursor_pet.getString(2));
                et_puntos_peticion.setPlaceholderText("Actualmente tiene " + cursor_pet.getInt(6));
            }
            else { Log.e("DatosPetFragment", "No se encontro la petición"); }
            cursor_pet.close();
            db_peticiones.close();

            db_usuarios.open();
            Cursor cursor_usu = db_usuarios.fetch(usuario);
            if (cursor_usu.getCount() != 0) {
                cursor_usu.moveToFirst();
                tv_usuario.setText(cursor_usu.getString(1));
                tv_correo_usuario.setText(cursor_usu.getString(2));
            }
            else { Log.e("DatosPetFragment", "No se encontro el usuario"); }
            cursor_usu.close();
            db_usuarios.close();

        }
        catch (Exception e) {
            Log.e("DatosPeticion", e.toString());
        }
    }

    private void actualizar_puntaje() {
        String et_puntos_peticion_string = et_puntos_peticion.getEditText().getText().toString();
        if (et_puntos_peticion_string.isEmpty()){
            Toast.makeText(getContext(), "Debes actualizar el puntaje\nde la petición", Toast.LENGTH_SHORT).show();
        }
        else {
            db_peticiones.open();
            db_peticiones.update(idpeticion, "puntos", Integer.parseInt(et_puntos_peticion_string));
            db_peticiones.update(idpeticion, "estado", 1);
            db_peticiones.close();
            Toast.makeText(getContext(), "Peticion aceptada\nPuntaje actualizado", Toast.LENGTH_SHORT).show();
            navController.popBackStack();
        }
    }

    // 0 espera, 1 aceptado, 2 rechazado
    private void rechazar_peticion(){
        db_peticiones.open();
        db_peticiones.update(idpeticion, "estado", 2);
        db_peticiones.close();
        Toast.makeText(getContext(), "Petición rechazada", Toast.LENGTH_SHORT).show();
        navController.popBackStack();
    }
}