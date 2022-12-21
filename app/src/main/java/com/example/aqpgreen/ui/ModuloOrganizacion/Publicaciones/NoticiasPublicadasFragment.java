package com.example.aqpgreen.ui.ModuloOrganizacion.Publicaciones;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Noticias.NoticiaDBController;

public class NoticiasPublicadasFragment extends Fragment {

    private NoticiaDBController db_noticias;

    private EditText et_titulo_noticia, et_fecha_noticia, et_descripcion_noticia;
    private Button btn_publicar_noticia;
    private ImageButton btn_regresar_fragment;

    public NoticiasPublicadasFragment() {
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
        return inflater.inflate(R.layout.fragment_noticias_publicadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_noticias = new NoticiaDBController(getContext());
        final NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);

        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());
        btn_publicar_noticia.setOnClickListener(v -> guardar_datos_noticia());

    }

    public void inicializar_elementos (View view) {
        et_titulo_noticia = view.findViewById(R.id.et_titulo_noticia);
        et_fecha_noticia = view.findViewById(R.id.et_fecha_noticia);
        et_descripcion_noticia = view.findViewById(R.id.et_descripcion_noticia);
        btn_publicar_noticia = view.findViewById(R.id.btn_publicar_noticia);
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
    }

    public void guardar_datos_noticia () {

        String et_titulo_noticia_string = et_titulo_noticia.getText().toString();
        String et_fecha_noticia_string = et_fecha_noticia.getText().toString();
        String et_descripcion_noticia_string = et_descripcion_noticia.getText().toString();

        if (et_titulo_noticia_string.isEmpty() || et_fecha_noticia_string.isEmpty() || et_descripcion_noticia_string.isEmpty()) {
            Toast.makeText(getContext(), "Verifique los datos ingresados", Toast.LENGTH_SHORT).show();
        }
        else {
            //Log.e("MainActivity", et_titulo_noticia_string + ","+ et_fecha_noticia_string +","+ et_descripcion_noticia_string);
            db_noticias.open();
            long confirmacion = db_noticias.insert(et_titulo_noticia_string, et_fecha_noticia_string, et_descripcion_noticia_string);
            db_noticias.close();

            if (confirmacion == -1){
                Toast.makeText(getContext(), "Error, intentelo nuevamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Noticia Guardada", Toast.LENGTH_SHORT).show();
                et_titulo_noticia.setText("");
                et_fecha_noticia.setText("");
                et_descripcion_noticia.setText("");
            }
        }

    }
}