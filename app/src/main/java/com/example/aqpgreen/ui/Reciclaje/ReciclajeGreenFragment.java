package com.example.aqpgreen.ui.Reciclaje;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

public class ReciclajeGreenFragment extends Fragment {

    ActivityResultLauncher<Intent> camaraResLauncher;
    static int RESULT_OK = -1;
    private View view;
    //public static final String EXTRA_INFO = "default";
    public static final int Image_Capture_Code = 1;

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

    /*
    * Atributos de la Peticion
    * */
    private String usuario_sesion;
    private int puntos_plastico;
    private int estado_peticion;
    private String urlFoto;
    private String dd_categorias_plastico_string;
    private String et_cantidad_plastico_string;
    private String dd_lugar_origen_string;
    private String et_descripcion_plastico_string;

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
                Bundle extras = result.getData() != null ? result.getData().getExtras() : null;
                Bitmap imgBitmap = (Bitmap) (extras != null ? extras.get("data") : null);
                imgCapturada_preview.setImageBitmap(imgBitmap);
            }
        });

        btnCamara.setOnClickListener(v -> {

            /*File imagen_archivo = null;

            try {
                imagen_archivo = crear_archivo_temp();
            } catch (IOException ex) {
                Log.e("Error", ex.toString());
            }

            if (imagen_archivo != null) {
                Uri foto_uri = FileProvider.getUriForFile(getContext(), "com.example.aqpgreen.ui.Reciclaje.fileprovider", imagen_archivo);*/
                //camaraResLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, foto_uri));
            camaraResLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            //}
        });
    }

    /*private void obtener_componentes_vista (View view) {

        btn_guardar_peticion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar_datos_peticion ();
            }
        });

    }*/

    private void guardar_datos_peticion () {

        et_cantidad_plastico_string = et_cantidad_plastico.getEditText().getText().toString();
        et_descripcion_plastico_string = et_descripcion_plastico.getEditText().getText().toString();

        usuario_sesion = preferencias.getString("usuario", "none");
        puntos_plastico = 15;
        estado_peticion = 0;
        urlFoto = "https://i.imgur.com/DvpvklR.png";

        Log.e("MainActivity", usuario_sesion + ","+ dd_categorias_plastico_string +","+ et_cantidad_plastico_string+","
                + dd_lugar_origen_string +","+et_descripcion_plastico_string +"," +puntos_plastico+"," +estado_peticion+","+urlFoto);

        if (usuario_sesion.isEmpty() || dd_categorias_plastico_string == null ||
                et_cantidad_plastico_string.isEmpty() || dd_lugar_origen_string == null ||
                et_descripcion_plastico_string.isEmpty()) {
            Toast.makeText(getContext(), "Verifique los datos ingresados", Toast.LENGTH_SHORT).show();
        }
        else {
            db_peticiones.open();
            long confirm = db_peticiones.insert(usuario_sesion, dd_categorias_plastico_string, Integer.parseInt(et_cantidad_plastico_string),
                    dd_lugar_origen_string, et_descripcion_plastico_string, puntos_plastico, estado_peticion, urlFoto);
            db_peticiones.close();

            if (confirm == -1) {
                Toast.makeText(getContext(), "Error, intentelo nuevamente ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Petición Guardada", Toast.LENGTH_SHORT).show();
                et_cantidad_plastico.getEditText().getText().clear();
                et_descripcion_plastico.getEditText().getText().clear();
            }
        }
    }

    /*private File crear_archivo_temp () throws IOException {
        String nombre_archivo = "foto_";
        File dir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen_temp = File.createTempFile(nombre_archivo, ".jpg", dir);

        ruta_imagen = imagen_temp.getAbsolutePath();
        return imagen_temp;
    }*/
}