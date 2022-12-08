package com.example.aqpgreen.ui.Reciclaje;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReciclajeGreenFragment extends Fragment {

    ActivityResultLauncher<Intent> camaraResLauncher;
    static int RESULT_OK = -1;

    private PeticionDBController db_peticiones;
    private SharedPreferences preferencias;
    //private SharedPreferences.Editor editor_preferencias;

    /*
    * Variables para la captura de datos
     */
    private AutoCompleteTextView dd_categorias_plastico;
    private AutoCompleteTextView dd_lugar_origen;
    private TextInputLayout et_cantidad_plastico;
    private TextInputLayout et_descripcion_plastico;
    private FloatingActionButton btnCamara;
    private ImageView imgCapturada_preview;
    private FloatingActionButton btn_guardar_peticion;
    private ImageButton btn_regresar_fragment;

    private String url_foto;
    private String dd_categorias_plastico_string;
    private String dd_lugar_origen_string;

    private File imagen_archivo;
    private Uri foto_uri;

    //private String ruta_imagen;
    public ReciclajeGreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opc_reciclaje_green, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_peticiones = new PeticionDBController(getContext());

        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);
        tomar_captura();

        btn_regresar_fragment.setOnClickListener(view1 -> navController.popBackStack());

        btn_guardar_peticion.setOnClickListener(view12 -> guardar_datos_peticion ());
    }

    public void inicializar_elementos (View view) {

        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        btn_guardar_peticion = view.findViewById(R.id.idFabRegistro);
        dd_categorias_plastico = view.findViewById(R.id.dropdown_categoriaPlastico);
        et_cantidad_plastico = view.findViewById(R.id.editText_cantidadPlastico);
        dd_lugar_origen = view.findViewById(R.id.dropdown_lugarOrigen);
        et_descripcion_plastico = view.findViewById(R.id.editText_descripcionPlastico);
        dd_categorias_plastico = view.findViewById(R.id.dropdown_categoriaPlastico);
        dd_lugar_origen = view.findViewById(R.id.dropdown_lugarOrigen);

        btnCamara = view.findViewById(R.id.btnCamera);
        imgCapturada_preview = view.findViewById(R.id.img_plasticoPreview);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        //editor_preferencias = preferencias.edit();

        ArrayAdapter<CharSequence> adapter_categorias = ArrayAdapter.createFromResource(getContext(), R.array.opciones_categorias_productos, R.layout.elemento_dropdown);
        ArrayAdapter<CharSequence> adapter_origen = ArrayAdapter.createFromResource(getContext(), R.array.opciones_lugar_origen, R.layout.elemento_dropdown);
        dd_categorias_plastico.setAdapter(adapter_categorias);
        dd_lugar_origen.setAdapter(adapter_origen);

        dd_categorias_plastico.setOnItemClickListener((adapterView, view1, i, l) -> dd_categorias_plastico_string = adapterView.getItemAtPosition(i).toString());
        dd_lugar_origen.setOnItemClickListener((adapterView, view1, i, l) -> dd_lugar_origen_string = adapterView.getItemAtPosition(i).toString());

    }

    public void tomar_captura () {

        camaraResLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK){
                Bitmap imagen_Bitmap = (Bitmap) BitmapFactory.decodeFile(url_foto);
                imgCapturada_preview.setImageBitmap(imagen_Bitmap);
            }
        });

        btnCamara.setOnClickListener(v -> {

            try {
                imagen_archivo = crear_archivo_temp();
                if (imagen_archivo != null) {
                    foto_uri = FileProvider.getUriForFile(getContext(), "com.example.aqpgreen", imagen_archivo);
                    camaraResLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, foto_uri));
                }
            }
            catch (IOException ex) {
                Log.e("Error", ex.toString());
            }

        });
    }

    private File crear_archivo_temp() throws IOException {

        String tiempo_archivo = new SimpleDateFormat("yyyyMMdd_HH-mm-ss", Locale.getDefault()).format(new Date());
        String nombre_archivo = "IMG_" + tiempo_archivo + "_";
        File directorio;

        if (isExternalStorageWritable()) {
            directorio = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        else {
            directorio = getContext().getFilesDir();
        }

        File imagen_temp = File.createTempFile(nombre_archivo, ".jpg", directorio);
        url_foto = imagen_temp.getAbsolutePath();
        return imagen_temp;
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private void guardar_datos_peticion () {

        String et_cantidad_plastico_string = et_cantidad_plastico.getEditText().getText().toString();
        String et_descripcion_plastico_string = et_descripcion_plastico.getEditText().getText().toString();
        String usuario_sesion = preferencias.getString("usuario", "none");
        int puntos_plastico = obtener_puntos_categoria(dd_categorias_plastico_string);
        int estado_peticion = 0;

        //Log.e("MainActivity", usuario_sesion + ","+ dd_categorias_plastico_string +","+ et_cantidad_plastico_string +","
          //      + dd_lugar_origen_string +","+ et_descripcion_plastico_string +"," + puntos_plastico +"," + estado_peticion +","+url_foto);

        if (usuario_sesion.isEmpty() || dd_categorias_plastico_string == null ||
                et_cantidad_plastico_string.isEmpty() || dd_lugar_origen_string == null ||
                et_descripcion_plastico_string.isEmpty()) {
            Toast.makeText(getContext(), "Verifique los datos ingresados", Toast.LENGTH_SHORT).show();
        }
        else {
            db_peticiones.open();
            long confirm = db_peticiones.insert(usuario_sesion, dd_categorias_plastico_string, Integer.parseInt(et_cantidad_plastico_string),
                    dd_lugar_origen_string, et_descripcion_plastico_string, puntos_plastico, estado_peticion, url_foto);
            db_peticiones.close();

            if (confirm == -1) {
                Toast.makeText(getContext(), "Error, intentelo nuevamente ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Petición Guardada", Toast.LENGTH_SHORT).show();
                et_cantidad_plastico.getEditText().getText().clear();
                et_descripcion_plastico.getEditText().getText().clear();
                imgCapturada_preview.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_camera_alt_128));
            }
        }
    }

    private int obtener_puntos_categoria (String dd_categorias_plastico_string) {
        int puntos_obtenidos = 0;
        switch (dd_categorias_plastico_string) {
            case "PET/PETE":
                puntos_obtenidos = 5;
                break;
            case "HDPE":
                puntos_obtenidos = 7;
                break;
            case "PVC":
                puntos_obtenidos = 10;
                break;
            case "LDPE/PEDB":
                puntos_obtenidos = 14;
                break;
            case "PP":
                puntos_obtenidos = 15;
                break;
            case "PS":
                puntos_obtenidos = 17;
                break;
            case "Otros":
                puntos_obtenidos = 20;
                break;
        }
        return puntos_obtenidos;
    }


}