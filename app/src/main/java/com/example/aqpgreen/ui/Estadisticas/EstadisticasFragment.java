package com.example.aqpgreen.ui.Estadisticas;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.aqpgreen.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstadisticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticasFragment extends Fragment {

    miPropiaVista miPropiaVista;
    LinearLayout linearLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int[] data={7,5,12,8,4,6,7,9,7,8,9,9,4,5};
    int[] color={Color.argb(255,205,115,114),
            Color.argb(255,79,129,188),
            Color.argb(255,192,80,78),
            Color.argb(255,155,187,88),
            Color.argb(255,128,100,161),
            Color.argb(255,74,172,197),
            Color.argb(255,247,150,71),
            Color.argb(255,44,77,118),
            Color.argb(255,119,43,43),
            Color.argb(255,97,117,48),
            Color.argb(255,75,59,98),
            Color.argb(255,39,106,123),
            Color.argb(255,182,87,7),
            Color.argb(255,114,154,205),
    };

    public EstadisticasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEstadisticas.
     */
    // TODO: Rename and change types and number of parameters
    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();
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

        View view =  inflater.inflate(R.layout.fragment_estadisticas, container, false);
        linearLayout=(LinearLayout) view.findViewById(R.id.estadisticas);
        linearLayout.addView(new miPropiaVista(getContext(),14,data,color));
        return view;
    }
}