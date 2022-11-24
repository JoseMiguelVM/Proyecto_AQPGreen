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
import com.example.aqpgreen.ui.Comunidad.Recicler.AdaptadorPremios;
import com.example.aqpgreen.ui.Comunidad.Recicler.Premios;

import java.util.ArrayList;

public class ListaDePremios extends Fragment {
    ArrayList<Premios> listaPremios;
    RecyclerView recyclerPremios;
    ListaDePremios listaDePremios;

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
        View vista=inflater.inflate(R.layout.fragment_lista_de_premios, container, false);
        listaPremios=new ArrayList<>();
        recyclerPremios = vista.findViewById(R.id.recyclerId);
        recyclerPremios.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaPremios();

        AdaptadorPremios adapter = new AdaptadorPremios(listaPremios);
        recyclerPremios.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Selecciona: "+listaPremios.get(recyclerPremios.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

    private void llenarListaPremios() {
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
        listaPremios.add(new Premios("Arbol Pequeño", "", R.drawable.tipoplastico));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}