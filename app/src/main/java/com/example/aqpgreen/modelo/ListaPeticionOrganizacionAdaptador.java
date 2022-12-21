package com.example.aqpgreen.modelo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.aqpgreen.R;

import java.util.List;

public class ListaPeticionOrganizacionAdaptador
        extends RecyclerView.Adapter<ListaPeticionOrganizacionAdaptador.ViewHolder>
        implements View.OnClickListener {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_foto_peticion;
        TextView usuario_peticion, categoria_peticion;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            iv_foto_peticion = itemView.findViewById(R.id.iv_imagen_peticion);
            categoria_peticion = itemView.findViewById(R.id.tv_categoria_peticion);
            usuario_peticion = itemView.findViewById(R.id.tv_usuario_peticion);
        }

        public void bindData (final Peticion elemento, @NonNull ListaPeticionOrganizacionAdaptador.ViewHolder holder) {
            Glide.with(holder.itemView.getContext())
                    .load(elemento.getFoto())
                    .centerCrop()
                    .placeholder(R.drawable.fragment_reciclaje_icon1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(iv_foto_peticion);
            usuario_peticion.setText("Usuario: " + elemento.getUsuario());
            categoria_peticion.setText("Peticion de: " + elemento.getCategoria());
        }
    }

    private List<Peticion> lista_peticiones;
    private View.OnClickListener listener;

    public void setOnClickListener (View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public ListaPeticionOrganizacionAdaptador(List<Peticion> _lista_peticiones) {
        this.lista_peticiones = _lista_peticiones;
    }

    @NonNull
    @Override
    public ListaPeticionOrganizacionAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_peticiones_organizacion, null, false);
        view.setOnClickListener(this);
        return new ListaPeticionOrganizacionAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPeticionOrganizacionAdaptador.ViewHolder holder, int position) {
        holder.bindData(lista_peticiones.get(position), holder);
        //holder.itemView.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return lista_peticiones.size();
    }
}
