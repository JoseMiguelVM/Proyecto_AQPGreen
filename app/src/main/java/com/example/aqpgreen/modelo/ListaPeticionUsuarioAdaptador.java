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

public class ListaPeticionUsuarioAdaptador extends RecyclerView.Adapter<ListaPeticionUsuarioAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_foto_peticion, estado_peticion;
        TextView categoria_peticion, descripcion_peticion, puntos_peticion;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            iv_foto_peticion = itemView.findViewById(R.id.iv_imagen_peticion);
            descripcion_peticion = itemView.findViewById(R.id.tv_descripcion_peticion);
            categoria_peticion = itemView.findViewById(R.id.tv_categoria_peticion);
            estado_peticion = itemView.findViewById(R.id.iv_estado_peticion);
            puntos_peticion = itemView.findViewById(R.id.tv_puntos_peticion);
        }

        public void bindData (final Peticion elemento, @NonNull ListaPeticionUsuarioAdaptador.ViewHolder holder) {
            Glide.with(holder.itemView.getContext())
                    .load(elemento.getFoto())
                    .centerCrop()
                    .placeholder(R.drawable.fragment_reciclaje_icon1)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(iv_foto_peticion);

            descripcion_peticion.setText(elemento.getDescripción());
            categoria_peticion.setText(elemento.getCategoria());
            puntos_peticion.setText(elemento.getPuntos() + "pts.");
            switch (elemento.getEstado()) {
                case 0 :
                    estado_peticion.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_estado_en_espera_24));
                    break;
                case 1 :
                    estado_peticion.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_estado_aceptado_24));
                    break;
                case 2 :
                    estado_peticion.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_estado_rechazado_24));
                    break;
            }

        }
    }

    private List<Peticion> lista_peticiones;

    public ListaPeticionUsuarioAdaptador(List<Peticion> _lista_peticiones) {
        this.lista_peticiones = _lista_peticiones;
    }

    @NonNull
    @Override
    public ListaPeticionUsuarioAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_peticiones_usuario, null, false);
        return new ListaPeticionUsuarioAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPeticionUsuarioAdaptador.ViewHolder holder, int position) {
        holder.bindData(lista_peticiones.get(position), holder);

        //holder.itemView.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return lista_peticiones.size();
    }
}
