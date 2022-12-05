package com.example.aqpgreen.ui.ModuloOrganizacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aqpgreen.R;


public class InformacionOpciones extends Fragment {
    // TODO: Declaracion de Variables
    private LinearLayout homeLayout;
    private LinearLayout likeLayout;
    private LinearLayout notificationLayout;
    private LinearLayout termLayout;
/*
    private ImageView homeImage;
    private ImageView likeImage;
    private ImageView notificationImage;

    private TextView homeTxt;
    private TextView likeTxt;
    private TextView notificationTxt;*/

    public InformacionOpciones() {
        // Required empty public constructor
    }

    public void inicializar_elementos (View view) {
        homeLayout = view.findViewById(R.id.homeLayout);
        likeLayout = view.findViewById(R.id.likeLayout);
        notificationLayout = view.findViewById(R.id.notificationLayout);
        termLayout = view.findViewById(R.id.termLayout);
/*
        homeImage = view.findViewById(R.id.homeImage);
        likeImage = view.findViewById(R.id.likeImage);
        notificationImage = view.findViewById(R.id.notificationImage);
        termImage = view.findViewById(R.id.termImage);

        homeTxt = view.findViewById(R.id.homeTxt);
        likeTxt = view.findViewById(R.id.likeTxt);
        notificationTxt = view.findViewById(R.id.notificationTxt);
        termTxt = view.findViewById(R.id.termTxt );*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacion_opciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        inicializar_elementos(view);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.menuOrganizacion);
/*
                    likeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);

                    likeImage.setImageResource(R.drawable.ic_google);
                    notificationImage.setImageResource(R.drawable.ic_twitter);

                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.ic_facebook);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);*/
            }
        });

        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.informacionOpciones);
/*
                    homeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.ic_facebook);
                    notificationImage.setImageResource(R.drawable.ic_twitter);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    likeTxt.setVisibility(View.VISIBLE);
                    likeImage.setImageResource(R.drawable.ic_google);
                    likeLayout.setBackgroundResource(R.drawable.round_back_like_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    likeLayout.startAnimation(scaleAnimation);*/
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    navController.navigate(R.id.itemAyudaOpciones);
/*
                    homeTxt.setVisibility(View.GONE);
                    likeTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.ic_facebook);
                    likeImage.setImageResource(R.drawable.ic_google);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    likeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    notificationTxt.setVisibility(View.VISIBLE);
                    notificationImage.setImageResource(R.drawable.ic_twitter);
                    notificationLayout.setBackgroundResource(R.drawable.round_back_notification_100);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    notificationLayout.startAnimation(scaleAnimation);*/
            }
        });

        termLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.terminosYcondiciones2);
            }
        });
    }
}