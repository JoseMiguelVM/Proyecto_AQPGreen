package com.example.aqpgreen.modelo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqpgreen.R;

import java.util.List;

public class ListaPeticionAdaptador extends RecyclerView.Adapter<ListaPeticionAdaptador.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_foto_peticion;
        TextView categoria_peticion, descripcion_peticion, estado_petición;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            iv_foto_peticion = (ImageView) itemView.findViewById(R.id.iv_imagen_peticion);
            descripcion_peticion = itemView.findViewById(R.id.tv_descripcion_peticion);
            categoria_peticion = itemView.findViewById(R.id.tv_categoria_peticion);
            estado_petición = itemView.findViewById(R.id.tv_estado_peticion);
        }

        public void bindData (final Peticion elemento) {
            //iv_foto_peticion.setColorFilter(Color.parseColor(elemento.getFoto()), PorterDuff.Mode.SRC_IN);
            //iv_foto_peticion.setImageResource(R.drawable.foto_peticion);
            descripcion_peticion.setText(elemento.getDescripción());
            categoria_peticion.setText(elemento.getCategoria());
            switch (elemento.getEstado()) {
                case 0 :
                    estado_petición.setText("En espera");
                    break;
                case 1 :
                    estado_petición.setText("Aceptado");
                    break;
                case 2 :
                    estado_petición.setText("Rechazado");
                    break;
            }
        }
    }

    private List<Peticion> lista_peticiones;

    public ListaPeticionAdaptador (List<Peticion> _lista_peticiones) {
        this.lista_peticiones = _lista_peticiones;
    }

    @NonNull
    @Override
    public ListaPeticionAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_peticiones, null, false);
        return new ListaPeticionAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPeticionAdaptador.ViewHolder holder, int position) {
        holder.bindData(lista_peticiones.get(position));
    }

    @Override
    public int getItemCount() {
        return lista_peticiones.size();
    }
}
