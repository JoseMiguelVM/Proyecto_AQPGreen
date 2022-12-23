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
import com.google.android.material.textfield.TextInputLayout;

public class HuellaPlasticoFragment extends Fragment {

    private Button button_calcular;
    private ImageButton btn_regresar_fragment;
    private TextInputLayout BotePET,
            BotePlastic, EnvAlim,
            EnvaYogurt, EnvaLimpieza,
            EnvaCosmetic, CepDientes,
            PastaDental, VasoPlastico,
            OtroPlastic;

    public HuellaPlasticoFragment() {

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

        btn_regresar_fragment.setOnClickListener(v -> navController.popBackStack());

        button_calcular.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("Tu huella de plástico es: ");
            double kgDeHuellaPlastica = CalculandHuellaPlastica();
            String mensaje = "Utilizarás "+String.valueOf(kgDeHuellaPlastica)+" kg. de plástico a lo largo de tu vida.";
            builder.setMessage(mensaje);


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button

                }
            });
            builder.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.create().show();

        });

    }

    public void inicializar_elementos (View view) {

        button_calcular = view.findViewById(R.id.buttonCalcularHuellaPlastica);
        BotePET = view.findViewById(R.id.editTextNumBotellasPet);
        BotePlastic = view.findViewById(R.id.editTextNumBolsasPlastic);
        EnvAlim = view.findViewById(R.id.editTextNumEnvolAlim);
        EnvaYogurt = view.findViewById(R.id.editTextNumEnvaYogurt);
        EnvaLimpieza = view.findViewById(R.id.editTextNumEnvaLimpieza);
        EnvaCosmetic = view.findViewById(R.id.editTextNumEnvaCosmetic);
        CepDientes = view.findViewById(R.id.editTextNumCepiDientes);
        PastaDental = view.findViewById(R.id.editTextNumPastasDentales);
        VasoPlastico = view.findViewById(R.id.editTextNumVasosPlastico);
        OtroPlastic = view.findViewById(R.id.editTextNumKgDecimal);

        btn_regresar_fragment = view.findViewById(R.id.btnIcoAtras);

    }
    public Double CalculandHuellaPlastica(){

        double botePET= (BotePET.getEditText().getText().toString().isEmpty())
                ? 0
                : Double.parseDouble(BotePET.getEditText().getText().toString());
        double botePlastic = (BotePlastic.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(BotePlastic.getEditText().getText().toString());
        double envAlim = (EnvAlim.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(EnvAlim.getEditText().getText().toString());
        double envaYogurt = (EnvaYogurt.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble( EnvaYogurt.getEditText().getText().toString());
        double envaLimpieza = (EnvaLimpieza.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(EnvaLimpieza.getEditText().getText().toString());
        double envaCosmetic = (EnvaCosmetic.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble( EnvaCosmetic.getEditText().getText().toString());
        double cepDientes = (CepDientes.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(CepDientes.getEditText().getText().toString());
        double pastaDental = (PastaDental.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(PastaDental.getEditText().getText().toString());
        double vasoPlastico = (VasoPlastico.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(VasoPlastico.getEditText().getText().toString());
        double otroPlastic = (OtroPlastic.getEditText().getText().toString().isEmpty())
                ? 0
                :Double.parseDouble(OtroPlastic.getEditText().getText().toString());

        double calculandoHuellaSemanal = (
                (botePET*0.036)
                +(botePlastic*0.008)
                +(envAlim*0.015)
                +(envaYogurt*0.015)
                +(envaLimpieza*0.006)
                +(envaCosmetic*0.080)
                +(cepDientes*0.020)
                +(pastaDental*0.015)
                +(vasoPlastico*0.003));

        double CepilloPastaOtrosTrimestral= (cepDientes*0.020) +(pastaDental*0.015) + (otroPlastic);
        double HuellaPlasticoAnual = calculandoHuellaSemanal * 48 + (CepilloPastaOtrosTrimestral*4);
        double HuellaPlasticoVidasinredondear = HuellaPlasticoAnual*65;
        double HuellaPlasticoVida = Math.round(HuellaPlasticoVidasinredondear*100.0)/100.0;

        return HuellaPlasticoVida;

    }


}