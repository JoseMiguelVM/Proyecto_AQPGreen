package com.example.aqpgreen.ui.Reciclaje;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.example.aqpgreen.modelo.ListaPeticionAdaptador;
import com.example.aqpgreen.modelo.Peticion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaPeticionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPeticionesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PeticionDBController db_peticiones;
    private SharedPreferences preferencias;
    private FloatingActionButton btn_crear_peticion_fragment;
    private ImageButton btn_regresar_fragment;

    public ListaPeticionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPeticionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPeticionesFragment newInstance(String param1, String param2) {
        ListaPeticionesFragment fragment = new ListaPeticionesFragment();
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
        return inflater.inflate(R.layout.fragment_lista_peticiones, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_peticiones = new PeticionDBController(getContext());
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        btn_crear_peticion_fragment.setOnClickListener(v -> navController.navigate(R.id.reciclajeGreenFragment));
        btn_regresar_fragment.setOnClickListener(view1 -> navController.popBackStack());

        generar_recyclerView (view);
    }

    public void inicializar_elementos (View view) {
        btn_crear_peticion_fragment = view.findViewById(R.id.RegistroButton);
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor_preferencias = preferencias.edit();

    }

    private void generar_recyclerView(View view) {
        List<Peticion> lista_peticiones = new ArrayList<>();
        db_peticiones.open();

        // obtengo los datos y los añado al arraylist
        // lista_peticiones.add(new Peticion());
        String usuario_sesion = preferencias.getString("usuario", "none");
        Cursor cursor = db_peticiones.fetch(usuario_sesion);
        if(cursor.getCount() != 0){
                try{
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        lista_peticiones.add(new Peticion(cursor.getInt(0), cursor.getString(2),
                                cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8)));
                    }
                } catch (Exception e){
                    Log.e("ListaPeticionesFragment", e.toString());
                } finally {
                    cursor.close();
                }
        }
        else {
            Log.e("ListaPetCursorelse", "No hay registros");
        }


        db_peticiones.close();

        ListaPeticionAdaptador adaptador = new ListaPeticionAdaptador(lista_peticiones);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_listaPeticiones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adaptador);
    }
}