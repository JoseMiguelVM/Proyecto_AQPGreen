package com.example.aqpgreen.ui.Reciclaje;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aqpgreen.R;

import java.io.File;
import java.io.IOException;

public class ReciclajeGreenFragment extends Fragment {

    ActivityResultLauncher<Intent> camaraResLauncher;
    static int RESULT_OK = -1;
    private View view;
    public static final String EXTRA_INFO = "default";
    public static final int Image_Capture_Code = 1;
    private ImageButton btnCamara;
    private ImageView imgCapturada_preview;

    //private String ruta_imagen;

    public ReciclajeGreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_opc_reciclaje_green, container, false);
        opciones_utilidad(view);
        tomar_captura(view);
        return view;
    }

    public void opciones_utilidad (View view) {
        Spinner sp_categorias_plastico = (Spinner) view.findViewById(R.id.spinnerCaegoriaPlastico);
        Spinner sp_lugar_origen = (Spinner) view.findViewById(R.id.spinnerLugarOrigen);

        ArrayAdapter<CharSequence> adapter_categorias = ArrayAdapter.createFromResource(getContext(), R.array.opciones_categorias_productos, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_origen = ArrayAdapter.createFromResource(getContext(), R.array.opciones_lugar_origen, android.R.layout.simple_spinner_item);
        sp_categorias_plastico.setAdapter(adapter_categorias);
        sp_lugar_origen.setAdapter(adapter_origen);
    }

    public void tomar_captura (View view) {
        btnCamara = view.findViewById(R.id.btnCamera);
        imgCapturada_preview = view.findViewById(R.id.imgProducto_preview);

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

    /*private File crear_archivo_temp () throws IOException {
        String nombre_archivo = "foto_";
        File dir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen_temp = File.createTempFile(nombre_archivo, ".jpg", dir);

        ruta_imagen = imagen_temp.getAbsolutePath();
        return imagen_temp;
    }*/
}