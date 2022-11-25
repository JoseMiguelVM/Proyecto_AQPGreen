package com.example.aqpgreen.ui.TiposPlastico;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aqpgreen.R;

import java.util.ArrayList;

public class TipoPlasticoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Plastico> listaPlastico;
    RecyclerView recyclerPlastico;


    public TipoPlasticoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPersonajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TipoPlasticoFragment newInstance(String param1, String param2) {
        TipoPlasticoFragment fragment = new TipoPlasticoFragment();
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
        View vista=inflater.inflate(R.layout.fragment_tipo_plastico, container, false);

        listaPlastico=new ArrayList<>();
        recyclerPlastico= (RecyclerView) vista.findViewById(R.id.recyclerId);
        recyclerPlastico.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorPlasticos adapter=new AdaptadorPlasticos(listaPlastico);
        recyclerPlastico.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion: "+
                        listaPlastico.get(recyclerPlastico.
                                getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();

                //interfaceComunicaFragments.enviarPlastico(listaPlastico.get(recyclerPlastico.getChildAdapterPosition(view)));
            }
        });


        return vista;
    }

    private void llenarListaPersonajes() {
        listaPlastico.add(new Plastico("PET o PETE","Botella de gaseosa, botella de refresco, botella de agua mineral, botella de aceite de cocina, cuerda de poliéster.",R.drawable.logo_pet));
        listaPlastico.add(new Plastico("HDPE","Botella de leche, envases de productos de limpieza, detergentes para la ropa, champú y gel de ducha, bolsas de plástico",R.drawable.logo_hdpe));
        listaPlastico.add(new Plastico("PVC","Tubos y cañerías, cables eléctricos, juguetes",R.drawable.logo_pvc));
        listaPlastico.add(new Plastico("LDPE o PEDB","Bolsas de basura, film transparente",R.drawable.logo_ldpe));
        listaPlastico.add(new Plastico("PP","Chapas de botellas, envases para almacenar alimentos, sorbetes",R.drawable.logo_pp));
        listaPlastico.add(new Plastico("PS","Envases de comida rápida, vasos descartables, vasitos de yogurt",R.drawable.logo_ps));
        listaPlastico.add(new Plastico("OTROS","Piezas de coches, CD’s,  DVD’s, Biberones",R.drawable.logo_otros));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}