package com.example.aqpgreen.ui.TiposPlastico;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.modelo.ListaPlasticosAdaptador;
import com.example.aqpgreen.modelo.Plastico;

import java.util.ArrayList;

public class TipoPlasticoFragment extends Fragment {

    // TODO: Declaracion de variables
    private ArrayList<Plastico> listaPlastico;
    private RecyclerView recyclerPlastico;
    private ImageButton btn_regresar_fragment;

    public TipoPlasticoFragment() {
        // Required empty public constructor
    }

    public static TipoPlasticoFragment newInstance(String param1, String param2) {
        TipoPlasticoFragment fragment = new TipoPlasticoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tipo_plastico, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);
        generar_recyclerView();

        btn_regresar_fragment.setOnClickListener(view1 -> navController.popBackStack());

    }

    private void inicializar_elementos (View view){
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        recyclerPlastico = view.findViewById(R.id.recyclerView_listaCategorias);
    }

    private void generar_recyclerView () {

        listaPlastico = new ArrayList<>();
        llenarListaTipos();
        ListaPlasticosAdaptador adapter = new ListaPlasticosAdaptador(listaPlastico);
        recyclerPlastico.setLayoutManager (new LinearLayoutManager(getContext()));
        recyclerPlastico.setAdapter(adapter);

        adapter.setOnClickListener(view1 -> {
            Toast.makeText(getContext(),"Seleccion: "+
                    listaPlastico.get(recyclerPlastico.
                            getChildAdapterPosition(view1)).getNombre(),Toast.LENGTH_SHORT).show();
            //interfaceComunicaFragments.enviarPlastico(listaPlastico.get(recyclerPlastico.getChildAdapterPosition(view)));
        });
    }

    private void llenarListaTipos() {
        listaPlastico.add(new Plastico("PET o PETE","Botella de gaseosa, botella de refresco, botella de agua mineral, botella de aceite de cocina, cuerda de poli??ster.",R.drawable.logo_pet));
        listaPlastico.add(new Plastico("HDPE","Botella de leche, envases de productos de limpieza, detergentes para la ropa, champ?? y gel de ducha, bolsas de pl??stico",R.drawable.logo_hdpe));
        listaPlastico.add(new Plastico("PVC","Tubos y ca??er??as, cables el??ctricos en general, juguetes y accesorios.",R.drawable.logo_pvc));
        listaPlastico.add(new Plastico("LDPE o PEDB","Bolsas de basura, film transparente, envases para el sector cosm??tico y el sector sanitario",R.drawable.logo_ldpe));
        listaPlastico.add(new Plastico("PP","Chapas de botellas, envases para almacenar alimentos, sorbetes",R.drawable.logo_pp));
        listaPlastico.add(new Plastico("PS","Envases de comida r??pida, vasos descartables, vasitos de yogurt",R.drawable.logo_ps));
        listaPlastico.add(new Plastico("OTROS","Piezas de coches, CD???s,  DVD???s, Biberones, todo lo considerado fuera de lo antes mencionado.",R.drawable.logo_otros));
    }
}