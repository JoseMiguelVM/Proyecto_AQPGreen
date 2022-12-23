package com.example.aqpgreen.ui.ModuloOrganizacion.PeticionesRecibidas;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqpgreen.R;
import com.example.aqpgreen.database.Peticiones.PeticionDBController;
import com.example.aqpgreen.modelo.ListaPeticionOrganizacionAdaptador;
import com.example.aqpgreen.modelo.Peticion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RevisionPeticionesFragment extends Fragment {


    private NavController navController;
    private PeticionDBController db_peticiones;
    private SharedPreferences preferencias;
    private FloatingActionButton btn_crear_peticion_fragment;
    private ImageButton btn_regresar_fragment;
    private ProgressBar pb_icono_carga;
    private TextView tv_aviso_vacio;
    private RecyclerView recyclerView;

    public RevisionPeticionesFragment() {
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
        return inflater.inflate(R.layout.fragment_revision_peticiones, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db_peticiones = new PeticionDBController(getContext());
        navController = Navigation.findNavController(view);
        inicializar_elementos(view);
        generar_recyclerView (view);
        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());
    }

    public void inicializar_elementos (View view) {
        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);
        pb_icono_carga = view.findViewById(R.id.pb_icono_carga);
        tv_aviso_vacio = view.findViewById(R.id.tv_lista_vacia);
        recyclerView = view.findViewById(R.id.recycleView_listaPeticiones);

        preferencias = getContext().getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor_preferencias = preferencias.edit();

    }

    private void generar_recyclerView(View view) {

        ExecutorService hilo = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        List<Peticion> lista_peticiones = new ArrayList<>();

        hilo.execute(() -> {
            db_peticiones.open();
            Cursor cursor = db_peticiones.fetch();
            if(cursor.getCount() != 0){
                try{
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        lista_peticiones.add(new Peticion(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                                cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8)));
                    }
                } catch (Exception e){
                    Log.e("ListaPetOrgFragment", e.toString());
                } finally {
                    cursor.close();
                }
            }
            else {
                recyclerView.setVisibility(View.GONE);
                tv_aviso_vacio.setText("No hay peticiones");
            }
            db_peticiones.close();

            handler.post(() -> {
                recyclerView.setVisibility(View.VISIBLE);
                tv_aviso_vacio.setVisibility(View.GONE);
                pb_icono_carga.setVisibility(View.GONE);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                ListaPeticionOrganizacionAdaptador adaptor = new ListaPeticionOrganizacionAdaptador(lista_peticiones);

                adaptor.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putLong("idpeticion", lista_peticiones.get(recyclerView.getChildAdapterPosition(v)).getId());
                    bundle.putString("usuario", lista_peticiones.get(recyclerView.getChildAdapterPosition(v)).getUsuario());
                    navController.navigate(R.id.datosPeticionesFragment, bundle);
                });

                recyclerView.setAdapter(adaptor);
            });
        });
    }
}