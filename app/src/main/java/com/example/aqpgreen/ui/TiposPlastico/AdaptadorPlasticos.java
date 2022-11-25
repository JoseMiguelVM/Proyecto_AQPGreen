package com.example.aqpgreen.ui.TiposPlastico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aqpgreen.R;

import java.util.ArrayList;

public class AdaptadorPlasticos extends RecyclerView.Adapter<AdaptadorPlasticos.PlasticosViewHolder> implements View.OnClickListener{

    ArrayList<Plastico> listaPlasticos;
    private View.OnClickListener listener;

    public AdaptadorPlasticos (ArrayList<Plastico> listaPlasticos) {
        this.listaPlasticos = listaPlasticos;
    }

    @Override
    public PlasticosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new PlasticosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlasticosViewHolder holder, int position) {
        holder.txtNombre.setText(listaPlasticos.get(position).getNombre());
        holder.txtInformacion.setText(listaPlasticos.get(position).getInfo());
        holder.foto.setImageResource(listaPlasticos.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return listaPlasticos.size();
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

    public class PlasticosViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion;
        ImageView foto;

        public PlasticosViewHolder (View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
