package com.example.aqpgreen.ui.Estadisticas;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.example.aqpgreen.database.Usuarios.UsuariosDBController;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstadisticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticasFragment extends Fragment {

    VistaGraficoCircular miPropiaVista;
    LinearLayout linearLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static public String[] Lista;
    int[] data;
    int[] color;
    private PeticionDBController dbManager;

    public EstadisticasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEstadisticas.
     */
    // TODO: Rename and change types and number of parameters
    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_estadisticas, container, false);
        linearLayout=(LinearLayout) view.findViewById(R.id.estadisticas);
        String[] Lista = getResources().getStringArray(R.array.opciones_lugar_origen);
        data=new int[Lista.length];
        color=new int[Lista.length];
        dbManager = new PeticionDBController(getContext());
        dbManager.open();

        Random r = new Random();
        for (int i = 0; i < Lista.length; i++) {
            data[i]=dbManager.fetch_Distrito_Origen_cantidad(Lista[i])*dbManager.fetch_Distrito_Origen_count(Lista[i]);
            color[i]=Color.argb(255,r.nextInt(210-70) + 70,r.nextInt(210-70) + 70,r.nextInt(210-70) + 70);
        }
        linearLayout.addView(new VistaGraficoCircular(getContext(),data.length,data,color,Lista));

        return view;
    }
}
