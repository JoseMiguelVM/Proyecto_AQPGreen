package com.example.aqpgreen.ui.Comunidad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aqpgreen.R;
import com.example.aqpgreen.modelo.ListaPremiosAdaptador;
import com.example.aqpgreen.modelo.Premios;

import java.util.ArrayList;

public class ListaDePremios extends Fragment {
    private ArrayList<Premios> listaPremios;
    private RecyclerView recyclerPremios;
    //ListaDePremios listaDePremios;

    public ListaDePremios() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListaDePremios newInstance(String param1, String param2) {
        ListaDePremios fragment = new ListaDePremios();
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
        return inflater.inflate(R.layout.fragment_lista_de_premios, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaPremios=new ArrayList<>();
        recyclerPremios= (RecyclerView) view.findViewById(R.id.recyclerView_listaPremios);
        recyclerPremios.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPremios();

        ListaPremiosAdaptador adapter = new ListaPremiosAdaptador(listaPremios);
        recyclerPremios.setAdapter(adapter);

        adapter.setOnClickListener(view1 -> {
            Toast.makeText(getContext(),"Seleccion: "+
                    listaPremios.get(recyclerPremios.
                            getChildAdapterPosition(view1)).getNombre(),Toast.LENGTH_SHORT).show();

            //interfaceComunicaFragments.enviarPlastico(listaPlastico.get(recyclerPlastico.getChildAdapterPosition(view)));
        });
    }

    private void llenarListaPremios() {
        listaPremios.add(new Premios("Arbol Pequeño", "xxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "xxxx", R.drawable.tipoplastico));
    }
}