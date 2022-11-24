package com.example.aqpgreen.ui.Comunidad.Recicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqpgreen.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class AdaptadorPremios extends RecyclerView.Adapter<AdaptadorPremios.PremiosViewHolder> implements View.OnClickListener{

    ArrayList<Premios> premiosArrayList;
    private View.OnClickListener listener;

    public AdaptadorPremios(ArrayList<Premios> premiosArrayList){ // constructor
        this.premiosArrayList = premiosArrayList;
    }

    @Override
    public PremiosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listapremiosdesign,null,false);
        view.setOnClickListener(this);
        return new PremiosViewHolder (view);
    }

    @Override
    public void onBindViewHolder(PremiosViewHolder holder, int position) {
        holder.txtNombre.setText(premiosArrayList.get(position).getNombre());
        holder.txtInformacion.setText(premiosArrayList.get(position).getInfo());
        holder.foto.setImageResource(premiosArrayList.get(position).getImagenId());
    }

    public int getItemCount(){
        return premiosArrayList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }


    public class PremiosViewHolder  extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion;
        ImageView foto;

        public PremiosViewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
