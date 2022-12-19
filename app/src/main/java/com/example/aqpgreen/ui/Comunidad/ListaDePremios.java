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
        listaPremios.add(new Premios("Árbol Pequeño", "Usted puede canjear este premio y poder plantarlo en su hogar, para asi promover el plantio de mas areas verdes.", R.drawable.arbol));
        listaPremios.add(new Premios("Gorra y Polo", "Estos premios reflejan su compromiso con la contaminacion ambiental.", R.drawable.gorra));
        listaPremios.add(new Premios("Mochila", "Es uno de los premios mas bellos, con este podras llevar todo el potencial clasico de las mochilas urbanas.", R.drawable.mochila));
        listaPremios.add(new Premios("Ropa para Perros", "Este premio es ideal para los consentidos de la casa y la moda que pueden llevar nuestros bebes caninos.", R.drawable.ropaperros));
        listaPremios.add(new Premios("Ropa para Gatos", "Este premio es ideal para los consentidos de la casa y la moda que pueden llevar nuestros bebes gatunos.", R.drawable.ropagatos));
        listaPremios.add(new Premios("Realizar Donación", "El premio mas importante, usted podra donar sus puntos acumulados a alguna organizacion benefica", R.drawable.donacion));
        listaPremios.add(new Premios("Ricocan (8KG)", "Ricocan Adulto Multisabores Todas las Razas, el mejor alimento para el engreido de casa.", R.drawable.ricocan));
        listaPremios.add(new Premios("Ricocat (9KG)", "Ricocat Adultos Atun, Sardina Y Trucha, el mejor alimento para el engreido de casa.", R.drawable.ricocat));
        listaPremios.add(new Premios("Patines Eléctricos", "Una moda ecológica y eficiente. Es fácil de conducir y plegar, no requiere mantenimiento y es sostenible con el medio ambiente.", R.drawable.patines));
        listaPremios.add(new Premios("Bicicletas Eléctricas", "Cabe destacar que permiten subir fácilmente terrenos con un gran desnivel, además de fomentar la práctica deportiva.", R.drawable.bicicleta));
        listaPremios.add(new Premios("Tablets", "El premio que puede ayudarte a estar conectado con las personas que deseas y claro, poder descargar nuestra app AQP Green.", R.drawable.tablet));
    }
}