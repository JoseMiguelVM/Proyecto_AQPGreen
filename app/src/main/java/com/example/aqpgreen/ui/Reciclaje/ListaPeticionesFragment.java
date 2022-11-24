package com.example.aqpgreen.ui.Reciclaje;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    private FloatingActionButton btn_crear_peticion_fragment;
    private ImageButton btn_regresar_fragment;

    private TableLayout tabla_lista_peticiones;

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
        btn_crear_peticion_fragment = view.findViewById(R.id.RegistroButton);
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        btn_crear_peticion_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { navController.navigate(R.id.reciclajeGreenFragment);}
        });
        btn_regresar_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { navController.popBackStack();}
        });

        //rellenar_tabla_peticiones(view);
    }

    /*private void rellenar_tabla_peticiones(View view) {
        tabla_lista_peticiones = view.findViewById(R.id.table_listaPeticiones);
        tabla_lista_peticiones.removeAllViews();
        db_peticiones.open();
        Cursor cursor = db_peticiones.fetch(01);
        if (cursor.getCount() !=0){
        }
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                //cursor.getInt(0);
                //cursor.getString();
            }
        } finally {
            cursor.close();
        }
        db_peticiones.close();
    }*/
}