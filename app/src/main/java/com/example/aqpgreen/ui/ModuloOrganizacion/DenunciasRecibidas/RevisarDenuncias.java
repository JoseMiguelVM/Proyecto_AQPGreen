package com.example.aqpgreen.ui.ModuloOrganizacion.DenunciasRecibidas;

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

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Denuncias.DenunciaDBController;
import com.example.aqpgreen.modelo.Denuncia;
import com.example.aqpgreen.modelo.ListaDenunciaAdaptador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RevisarDenuncias extends Fragment {

    private DenunciaDBController db_denuncias;
    private SharedPreferences preferencias;
    private FloatingActionButton btn_crear_peticion_fragment;
    private ImageButton btn_regresar_fragment;
    private ProgressBar pb_icono_carga;
    private TextView tv_aviso_vacio;
    private RecyclerView recyclerView;

    public RevisarDenuncias() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_revisar_denuncias, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_denuncias = new DenunciaDBController(getContext());
        NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);
        generar_recyclerView (view);

        btn_regresar_fragment.setOnClickListener(view1 -> navController.popBackStack());


    }

    public void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        pb_icono_carga = view.findViewById(R.id.pb_icono_carga);
        tv_aviso_vacio = view.findViewById(R.id.tv_lista_vacia);
        recyclerView = view.findViewById(R.id.recycleView_listaDenuncias);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor_preferencias = preferencias.edit();

    }

    private void generar_recyclerView(View view) {

        ExecutorService hilo = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        List<Denuncia> lista_denuncias = new ArrayList<>();

        db_denuncias.open();
        Cursor cursor = db_denuncias.fetch();

        if(cursor.getCount() != 0){
            hilo.execute(() -> {
                try{
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        lista_denuncias.add(new Denuncia(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                                cursor.getString(3), cursor.getString(4)));
                    }
                } catch (Exception e){
                    Log.e("ListaDenOrgFragment", e.toString());
                } finally {
                    cursor.close();
                }
                handler.post(() -> {
                    tv_aviso_vacio.setVisibility(View.GONE);
                    pb_icono_carga.setVisibility(View.GONE);
                    ListaDenunciaAdaptador adaptor = new ListaDenunciaAdaptador(lista_denuncias);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                    recyclerView.setAdapter(adaptor);
                });
            });
        }
        else {
            recyclerView.setVisibility(View.GONE);
            pb_icono_carga.setVisibility(View.GONE);
            tv_aviso_vacio.setText("No hay denuncias");
        }
        db_denuncias.close();

    }
}