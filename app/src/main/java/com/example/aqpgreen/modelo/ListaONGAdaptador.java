package com.example.aqpgreen.modelo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aqpgreen.R;

import java.util.ArrayList;

public class ListaONGAdaptador extends RecyclerView.Adapter<ListaONGAdaptador.ONGViewHolder> implements View.OnClickListener{
    ArrayList<ONG> listaONG;
    private View.OnClickListener listener;

    public ListaONGAdaptador(ArrayList<ONG> listaONG){ // constructor
        this.listaONG = listaONG;
    }

    @Override
    public ListaONGAdaptador.ONGViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ong,null,false);
        view.setOnClickListener(this);
        return new ListaONGAdaptador.ONGViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaONGAdaptador.ONGViewHolder holder, int position) {
        holder.txtNombre.setText(listaONG.get(position).getNombre());
        holder.txtInformacion.setText(listaONG.get(position).getInfo());
        Glide.with(holder.itemView.getContext())
                .load(listaONG.get(position).getImagenId())
                .centerCrop()
                .placeholder(R.drawable.fragment_reciclaje_icon1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.foto);
    }

    public int getItemCount(){
        return listaONG.size();
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

    public class ONGViewHolder  extends RecyclerView.ViewHolder {
        TextView txtNombre,txtInformacion;
        ImageView foto;

        public ONGViewHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
