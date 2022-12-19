package com.example.aqpgreen.modelo;

public class Peticion {

    private long id;
    private String usuario;
    private String categoria;
    private int cantidad;
    private String lugar_origen;
    private String descripción;
    private int puntos;
    private int estado;
    private String foto;

    public Peticion(long id, String usuario, String categoria, int cantidad, String lugar_origen, String descripción, int puntos, int estado, String foto) {
        this.id = id;
        this.usuario = usuario;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.lugar_origen = lugar_origen;
        this.descripción = descripción;
        this.puntos = puntos;
        this.estado = estado;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLugar_origen() {
        return lugar_origen;
    }

    public void setLugar_origen(String lugar_origen) {
        this.lugar_origen = lugar_origen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }
}
