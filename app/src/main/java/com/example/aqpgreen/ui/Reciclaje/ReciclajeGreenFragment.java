package com.example.aqpgreen.ui.Reciclaje;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.aqpgreen.R;

public class ReciclajeGreenFragment extends Fragment {

    ActivityResultLauncher<Intent> actResLauncher;
    private View view;
    public static final String EXTRA_INFO = "default";
    public static final int Image_Capture_Code = 1;
    private Button btnCamara;
    private ImageView imgCapturada;

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
        imgCapturada = view.findViewById(R.id.imagenProducto);

        btnCamara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                actResLauncher.launch(new_intent);
            }
        });
    }
}