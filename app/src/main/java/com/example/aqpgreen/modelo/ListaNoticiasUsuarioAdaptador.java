package com.example.aqpgreen.modelo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aqpgreen.R;

import java.util.List;

public class ListaNoticiasUsuarioAdaptador extends RecyclerView.Adapter<ListaNoticiasUsuarioAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo_noticia, descripcion_noticia, fecha_noticia;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            titulo_noticia = itemView.findViewById(R.id.tv_titulo_noticia);
            descripcion_noticia = itemView.findViewById(R.id.tv_descripcion_noticia);
            fecha_noticia = itemView.findViewById(R.id.tv_fecha_noticia);
        }

        public void bindData (final Noticia _not) {
            titulo_noticia.setText(_not.getTitulo());
            fecha_noticia.setText(_not.getFecha());
            descripcion_noticia.setText(_not.getDescripcion());
        }
    }

    private final List<Noticia> lista_noticias;

    public ListaNoticiasUsuarioAdaptador(List<Noticia> _lista_noticias) {
        this.lista_noticias = _lista_noticias;
    }

    @NonNull
    @Override
    public ListaNoticiasUsuarioAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista_noticias_usuario, parent, false);
        return new ListaNoticiasUsuarioAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaNoticiasUsuarioAdaptador.ViewHolder holder, int position) {
        holder.bindData(lista_noticias.get(position));
    }

    @Override
    public int getItemCount() {
        return lista_noticias.size();
    }
}
