package com.example.aqpgreen.ui.Comunidad;

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
import com.example.aqpgreen.modelo.ListaONGAdaptador;
import com.example.aqpgreen.modelo.ListaPremiosAdaptador;
import com.example.aqpgreen.modelo.ONG;
import com.example.aqpgreen.modelo.Premios;

import java.util.ArrayList;

public class ListaDeOng extends Fragment {
    private ArrayList<ONG> listaONG;
    private RecyclerView recyclerONG;
    private ImageButton btn_regresar_fragment;

    public ListaDeOng() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_ong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);
        llenarListaONG();
        generar_recyclerView();

        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());
    }


    private void llenarListaONG() {
        listaONG.add(new ONG("Ecomar Fundacion", "Fomento de la Responsabilidad Social Empresarial a través del Voluntariado Corporativo junto a los ODS de la Fundación ECOMAR..", R.drawable.ong1));
        listaONG.add(new ONG("SERFOR", "El Servicio Nacional Forestal y de Fauna Silvestre (SERFOR).", R.drawable.ong2));
        listaONG.add(new ONG("UICN", "Unión Internacional para la Conservación de la Naturaleza y de los Recursos Naturales y al Fondo Mundial para la Naturaleza.", R.drawable.ong3));
        listaONG.add(new ONG("RAP", "RAP – Red Ambiental Peruana, contribuye al cuidado de los Recursos Naturales del Peru.", R.drawable.ong4));
        listaONG.add(new ONG("ARA", "La Articulación Regional Amazónica – ARA está empezando un nuevo momento en su comunicación trabajando para perfeccionar nuestra red.", R.drawable.ong5));
        listaONG.add(new ONG("Comité de Apoyo Parque Nacional Sierra del Divisor", "Protección de una muestra representativa de la región montañosa del bosque húmedo tropical del llano amazónico.", R.drawable.ong6));
    }

    private void inicializar_elementos (View view) {
        listaONG = new ArrayList<>();
        recyclerONG = view.findViewById(R.id.recyclerView_listaONG);
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
    }

    private void generar_recyclerView (){
        recyclerONG.setLayoutManager(new LinearLayoutManager(getContext()));
        ListaONGAdaptador adapter = new ListaONGAdaptador(listaONG);
        recyclerONG.setAdapter(adapter);

        adapter.setOnClickListener(v -> {
            Toast.makeText(getContext(),"Seleccion de ONG: "+
                    listaONG.get(recyclerONG.
                            getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();

            //interfaceComunicaFragments.enviarPlastico(listaPlastico.get(recyclerPlastico.getChildAdapterPosition(view)));
        });
    }
}