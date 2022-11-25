package com.example.aqpgreen.ui.Reciclaje;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReciclajeGreenFragment extends Fragment {

    ActivityResultLauncher<Intent> camaraResLauncher;
    static int RESULT_OK = -1;
    private View view;
    public static final String EXTRA_INFO = "default";
    public static final int Image_Capture_Code = 1;

    private PeticionDBController db_peticiones;
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor_preferencias;
    /*
    * Variables para la captura de datos
     */
    private Spinner sp_categorias_plastico;
    private Spinner sp_lugar_origen;
    private EditText et_cantidad_plastico;
    private EditText et_descripcion_plastico;
    private ImageButton btnCamara;
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
    private String sp_categorias_plastico_string;
    private String et_cantidad_plastico_string;
    private String sp_lugar_origen_string;
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
        tomar_captura(view);


        btn_regresar_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        btn_guardar_peticion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar_datos_peticion ();
            }
        });
    }

    public void inicializar_elementos (View view) {

        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        btn_guardar_peticion = view.findViewById(R.id.idFabRegistro);
        sp_categorias_plastico = view.findViewById(R.id.spinner_categoriaPlastico);
        et_cantidad_plastico = (EditText) view.findViewById(R.id.editText_cantidadPlastico);
        sp_lugar_origen = view.findViewById(R.id.spinner_lugarOrigen);
        et_descripcion_plastico = (EditText) view.findViewById(R.id.editText_descripcionPlastico);
        sp_categorias_plastico = (Spinner) view.findViewById(R.id.spinner_categoriaPlastico);
        sp_lugar_origen = (Spinner) view.findViewById(R.id.spinner_lugarOrigen);

        btnCamara = view.findViewById(R.id.btnCamera);
        imgCapturada_preview = view.findViewById(R.id.imgProducto_preview);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        editor_preferencias = preferencias.edit();

        ArrayAdapter<CharSequence> adapter_categorias = ArrayAdapter.createFromResource(getContext(), R.array.opciones_categorias_productos, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_origen = ArrayAdapter.createFromResource(getContext(), R.array.opciones_lugar_origen, android.R.layout.simple_spinner_item);
        sp_categorias_plastico.setAdapter(adapter_categorias);
        sp_lugar_origen.setAdapter(adapter_origen);

        sp_categorias_plastico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_categorias_plastico_string = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        sp_lugar_origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_lugar_origen_string = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    public void tomar_captura (View view) {

        camaraResLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    Bundle extras = result.getData().getExtras();
                    Bitmap imgBitmap = (Bitmap) extras.get("data");
                    imgCapturada_preview.setImageBitmap(imgBitmap);
                }
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

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
            }
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

        et_cantidad_plastico_string = et_cantidad_plastico.getText().toString();
        et_descripcion_plastico_string = et_descripcion_plastico.getText().toString();

        usuario_sesion = preferencias.getString("usuario", "none");
        puntos_plastico = 15;
        estado_peticion = 0;
        urlFoto = "https://i.imgur.com/DvpvklR.png";

        if (usuario_sesion.isEmpty() || sp_categorias_plastico_string == null ||
                et_cantidad_plastico_string.isEmpty() || sp_lugar_origen_string == null ||
                et_descripcion_plastico_string.isEmpty()) {
            Toast.makeText(getContext(), "Verifique los datos ingresados", Toast.LENGTH_SHORT).show();
            /*Log.e("MainActivity", usuario_peticion + ","+ sp_categorias_plastico_string+","+Integer.parseInt(et_cantidad_plastico_string)+","
                    +sp_lugar_origen_string+","+et_descripcion_plastico_string +"," +puntos_plastico+"," +estado_peticion+","+urlFoto);*/
        }
        else {
            db_peticiones.open();
            /*Log.e("MainActivity", usuario_peticion + ","+ sp_categorias_plastico_string+","+Integer.parseInt(et_cantidad_plastico_string)+","
                    +sp_lugar_origen_string+","+et_descripcion_plastico_string +"," +puntos_plastico+"," +estado_peticion+","+urlFoto);*/
            long confirm = db_peticiones.insert(usuario_sesion, sp_categorias_plastico_string, Integer.parseInt(et_cantidad_plastico_string),
                    sp_lugar_origen_string, et_descripcion_plastico_string, puntos_plastico, estado_peticion, urlFoto);
            db_peticiones.close();

            if (confirm == -1) {
                Toast.makeText(getContext(), "Error, intentelo nuevamente ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Petición Guardada", Toast.LENGTH_SHORT).show();
                et_cantidad_plastico.setText("");
                et_descripcion_plastico.setText("");
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