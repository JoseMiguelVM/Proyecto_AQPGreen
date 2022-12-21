package com.example.aqpgreen.modelo;

public class Noticia {

    private long id;
    private String titulo;
    private String fecha;
    private String descripcion;

    public Noticia(long id, String titulo, String fecha, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }


}
