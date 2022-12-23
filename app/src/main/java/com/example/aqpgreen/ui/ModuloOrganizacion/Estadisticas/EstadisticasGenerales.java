package com.example.aqpgreen.ui.ModuloOrganizacion.Estadisticas;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.example.aqpgreen.ui.Estadisticas.VistaGraficoCircular;

import java.util.Random;


public class EstadisticasGenerales extends Fragment {

    private VistaGraficoCircular miPropiaVista;
    private LinearLayout linearLayout;

    static public String[] Lista;
    private int[] data;
    private int[] color;
    private PeticionDBController db_peticiones;
    private ImageButton btn_regresar_fragment;
    private TextView leyenda;

    public EstadisticasGenerales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estadisticas_generales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_peticiones = new PeticionDBController(getContext());
        NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);
        crear_grafico();
        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());

    }

    private void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        linearLayout = view.findViewById(R.id.estadisticas);
        leyenda = view.findViewById(R.id.leyenda);
        Lista = getResources().getStringArray(R.array.opciones_lugar_origen);
        data = new int[Lista.length];
        color = new int[Lista.length];
    }

    private void crear_grafico () {
        db_peticiones.open();
        Random r = new Random();
        String leyenda_text = "";
        for (int i = 0; i < Lista.length; i++) {
            data[i] = db_peticiones.fetch_Distrito_Origen_cantidad(Lista[i])
                    * db_peticiones.fetch_Distrito_Origen_count(Lista[i]);
            color[i] = Color.argb(255,r.nextInt(210-70) + 70,
                    r.nextInt(210-70) + 70,
                    r.nextInt(210-70) + 70);
            if (data[i]!=0)
                leyenda_text=leyenda_text+"<font color=#"+Integer.toHexString(color[i]).substring(2)+">"+Lista[i]+"</font><br>";
        }
        db_peticiones.close();
        leyenda.setText(Html.fromHtml(leyenda_text));
        linearLayout.addView(new VistaGraficoCircular(getContext(), data.length, data, color, Lista));
    }
}