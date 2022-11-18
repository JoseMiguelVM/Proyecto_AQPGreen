package com.example.aqpgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity{
    EditText userName, email, password;
    Button btnRegistrarse;
    DaoUsuario dao;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button botonLogin = findViewById(R.id.logIn);
        // Para dirigirse al form de login
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(Registro.this, MainActivity.class);
                startActivity(intentNuevo);
            }
        });
/*
        userName=(EditText) findViewById(R.id.RnombreUsuarioEdit);
        email=(EditText) findViewById(R.id.Rcorreo);
        password=(EditText) findViewById(R.id.RcontraseñaEdit);
        btnRegistrarse=(Button) findViewById(R.id.botonRegistroCompleto);

        btnRegistrarse.setOnClickListener(this);
        dao=new DaoUsuario(this);*/
    }
/*
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonRegistroCompleto:
                Usuario user=new Usuario();
                user.setNombreUsuario(userName.getText().toString());
                user.setCorreo(email.getText().toString());
                user.setContraseña(password.getText().toString());
                //Validar datos
                if(user.isNull()){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if(dao.insertUsuario(user)){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Usuario registrado previamente", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }*/
}
