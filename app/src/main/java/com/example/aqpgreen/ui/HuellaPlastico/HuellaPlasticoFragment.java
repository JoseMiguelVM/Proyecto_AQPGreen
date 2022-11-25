package com.example.aqpgreen.ui.HuellaPlastico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.aqpgreen.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HuellaPlasticoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HuellaPlasticoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button button_calcular;
    private ImageButton btn_regresar_fragment;
    private EditText BotePET,BotePlastic,EnvAlim,EnvaYogurt,EnvaLimpieza,EnvaCosmetic,CepDientes,PastaDental,VasoPlastico,OtroPlastic;

    public HuellaPlasticoFragment() {

    }

    public static HuellaPlasticoFragment newInstance(String param1, String param2) {
        HuellaPlasticoFragment fragment = new HuellaPlasticoFragment();
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
        return inflater.inflate(R.layout.fragment_huella_plastico, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        inicializar_elementos(view);

        btn_regresar_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navController.navigate(R.id.);
                Log.d("TAG","Hicimos click en el botton CALCULAR BOTELLA");

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                //AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("TU HUELLA DE PLASTICO ES: ");
                double kgDeHuellaPlastica = CalculandHuellaPlastica();
                String mensaje = String.valueOf(kgDeHuellaPlastica);
                builder.setMessage(mensaje);


                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.create().show();


            }
        });

    }

    public void inicializar_elementos (View view) {

        button_calcular = view.findViewById(R.id.buttonCalcularHuellaPlastica);
        BotePET = view.findViewById(R.id.editTextNumBotellasPet);
        BotePlastic = view.findViewById(R.id.editTextNumBotellasPet);
        EnvAlim = view.findViewById(R.id.editTextNumEnvolAlim);
        EnvaYogurt = view.findViewById(R.id.editTextNumEnvaYogurt);
        EnvaLimpieza = view.findViewById(R.id.editTextNumEnvaLimpieza);
        EnvaCosmetic = view.findViewById(R.id.editTextNumEnvaCosmetic);
        CepDientes = view.findViewById(R.id.editTextNumCepiDientes);
        PastaDental = view.findViewById(R.id.editTextNumPastasDentales);
        VasoPlastico = view.findViewById(R.id.editTextNumVasosPlastico);
        OtroPlastic = view.findViewById(R.id.editTextNumKgDecimal);
      //  OtroPlastic = view.findViewById(R.id.editTextNumOtrosPlastic);

        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);

    }
    public Double CalculandHuellaPlastica(){

        double botePET = Double.parseDouble(BotePET.getText().toString());
        double botePlastic = Double.parseDouble(BotePlastic.getText().toString());
        double envAlim = Double.parseDouble(EnvAlim.getText().toString());
        double envaYogurt = Double.parseDouble( EnvaYogurt.getText().toString());
        double envaLimpieza = Double.parseDouble(EnvaLimpieza.getText().toString());
        double envaCosmetic = Double.parseDouble( EnvaCosmetic.getText().toString());
        double cepDientes = Double.parseDouble(CepDientes.getText().toString());
        double pastaDental = Double.parseDouble(PastaDental.getText().toString());
        double vasoPlastico = Double.parseDouble(VasoPlastico.getText().toString());
        double otroPlastic = Double.parseDouble(OtroPlastic.getText().toString());

        double calculandoHuellaSemanal = (
                (botePET*0.036)
                +(botePlastic*0.008)
                +(envAlim*0.015)
                +(envaYogurt*0.015)
                +(envaLimpieza*0.006)
                +(envaCosmetic*0.080)
                +(cepDientes*0.020)
                +(pastaDental*0.015)
                +(vasoPlastico*0.003)+(otroPlastic));

        double HuellaPlasticoAnual = calculandoHuellaSemanal * 48;
        double HuellaPlasticoVida = HuellaPlasticoAnual*65;
        System.out.println("CONSUMES  "+HuellaPlasticoVida+ "KG A LA LARGO DE TU VIDA");

        return HuellaPlasticoVida;

    }


}