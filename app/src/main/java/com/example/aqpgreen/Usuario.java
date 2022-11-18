package com.example.aqpgreen;

public class Usuario {
    int ID;
    String nombreUsuario, correo, contraseña;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String correo, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    //Metodo para validar los campos si estan vacios
    public boolean isNull(){
        if(nombreUsuario.equals("") && correo.equals("") && contraseña.equals("")){
            return false;
        }else{
            return false;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + ID +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
