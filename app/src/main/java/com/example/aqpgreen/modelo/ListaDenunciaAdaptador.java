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

public class ListaDenunciaAdaptador extends RecyclerView.Adapter<ListaDenunciaAdaptador.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_foto_peticion;
        TextView usuario_dni, usuario_nombres, usuario_ubicacion, usuario_descripcion;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            iv_foto_peticion = itemView.findViewById(R.id.iv_imagen_peticion);
            usuario_dni = itemView.findViewById(R.id.rescate_dni);
            usuario_nombres = itemView.findViewById(R.id.rescate_nombres);
            usuario_ubicacion = itemView.findViewById(R.id.rescate_ubicacion);
            usuario_descripcion = itemView.findViewById(R.id.rescate_descripcion);
        }

        public void bindData (final Denuncia elemento, @NonNull ListaDenunciaAdaptador.ViewHolder holder) {
            usuario_dni.setText("DNI: " + elemento.getDni());
            usuario_nombres.setText("Usuario: " + elemento.getNombre());
            usuario_ubicacion.setText("Ubicacion: " + elemento.getUbicacion());
            usuario_descripcion.setText("Descripcion: " + elemento.getDescripcion());
        }
    }

    private List<Denuncia> lista_denuncias;

    public ListaDenunciaAdaptador(List<Denuncia> _lista_denuncias) {
        this.lista_denuncias = _lista_denuncias;
    }

    @NonNull
    @Override
    public ListaDenunciaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_denuncias_organizacion, null, false);
        return new ListaDenunciaAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(lista_denuncias.get(position), holder);
    }

    @Override
    public int getItemCount() {
        return lista_denuncias.size();
    }
}
