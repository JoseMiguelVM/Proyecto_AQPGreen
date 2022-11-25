package com.example.aqpgreen.ui.TiposPlastico;

public class Plastico {
    private String nombre;
    private String info;
    private String descripcion;
    private int imageId;
    private int imageDetalle;

    public Plastico (){
    }

    public Plastico (String nombre, String info, int imageId){
        this.nombre = nombre;
        this.info = info;
        this.imageId = imageId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }
}

