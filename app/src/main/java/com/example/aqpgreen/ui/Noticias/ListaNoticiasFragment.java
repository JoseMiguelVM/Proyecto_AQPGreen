package com.example.aqpgreen.ui.Noticias;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Noticias.NoticiaDBController;
import com.example.aqpgreen.modelo.Noticia;

import java.util.ArrayList;
import java.util.List;


public class ListaNoticiasFragment extends Fragment {

    private NoticiaDBController db_noticias;
    private ImageButton btn_regresar_fragment;
    private TextView tv_aviso_vacio;
    private RecyclerView recyclerView;

    public ListaNoticiasFragment() {
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
        return inflater.inflate(R.layout.fragment_lista_noticias, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_noticias = new NoticiaDBController(getContext());
        final NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);
        generar_recyclerView(view);

        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());
    }

    private void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        tv_aviso_vacio = view.findViewById(R.id.tv_lista_vacia);
        recyclerView = view.findViewById(R.id.recycleView_listaNoticias);
    }

    private void generar_recyclerView(View view) {

        List<Noticia> lista_noticias = new ArrayList<>();

        db_noticias.open();
        Cursor cursor = db_noticias.fetch();
        if (cursor.getCount() != 0){
            tv_aviso_vacio.setText("");
            try {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    lista_noticias.add(new Noticia(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                }
            }
            catch (Exception e){
                Log.e("ListaNoticiasFragment", e.toString());
            }
            finally {
                cursor.close();
            }
            recyclerView.setVisibility(View.VISIBLE);
            /*
            ListaNoticiasUsuarioAdaptador adaptador = new ListaNoticiasUsuarioAdaptador(lista_noticias);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            recyclerView.setAdapter(adaptador);
            */
        }
        else {
            recyclerView.setVisibility(View.GONE);
            tv_aviso_vacio.setVisibility(View.VISIBLE);
            tv_aviso_vacio.setText("Aún no se han agregado noticias\nLo sentimos.");
        }

        db_noticias.close();
    }
}