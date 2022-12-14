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

public class ListaPremiosAdaptador extends RecyclerView.Adapter<ListaPremiosAdaptador.PremiosViewHolder> implements View.OnClickListener{

    ArrayList<Premios> listaPremios;
    private View.OnClickListener listener;

    public ListaPremiosAdaptador(ArrayList<Premios> listaPremios){ // constructor
        this.listaPremios = listaPremios;
    }

    @Override
    public PremiosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_premios,null,false);
        view.setOnClickListener(this);
        return new PremiosViewHolder (view);
    }

    @Override
    public void onBindViewHolder(PremiosViewHolder holder, int position) {
        holder.txtNombre.setText(listaPremios.get(position).getNombre());
        holder.txtInformacion.setText(listaPremios.get(position).getInfo());
        Glide.with(holder.itemView.getContext())
                .load(listaPremios.get(position).getImagenId())
                .centerCrop()
                .placeholder(R.drawable.fragment_reciclaje_icon1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.foto);
    }

    public int getItemCount(){
        return listaPremios.size();
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
