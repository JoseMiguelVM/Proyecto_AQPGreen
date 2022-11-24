package com.example.aqpgreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aqpgreen.database.Usuarios.AdministradorUsuariosDB;
import com.example.aqpgreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AdministradorUsuariosDB dbManager;
    private EditText et_usuario;
    private EditText et_contrasena;
    private Button btn_ir_registro;
    private Button btn_acceder;
    private CheckBox check_guardar_sesion;

    private String et_usuario_string;
    private String et_contrasena_string;

    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor_preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new AdministradorUsuariosDB(this);
        inicializar_elementos ();
        dbManager.open();

        if (revisar_sesion_activa()) {
            startActivity(new Intent(this, MenuActivity.class));
        }

        // Para dirigirse al form de registro
        btn_ir_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNuevo = new Intent(MainActivity.this, Registro.class);
                startActivity(intentNuevo);
            }
        });

        btn_acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et_usuario_string = et_usuario.getText().toString();
                et_contrasena_string = et_contrasena.getText().toString();

                if (dbManager.fetch(et_usuario_string, et_contrasena_string).getCount() != 0){
                    guardar_sesion(et_usuario_string, check_guardar_sesion.isChecked());
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "No se encuentra el usuario o\nla contraseña es errónea", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean revisar_sesion_activa() {
        return preferencias.getBoolean("sesion", false);
    }

    private void guardar_sesion(String _usuario, boolean _guardar_sesion) {
        editor_preferencias.putString("usuario", _usuario);
        editor_preferencias.putBoolean("sesion", _guardar_sesion);
        editor_preferencias.apply();
    }

    private void inicializar_elementos() {
        btn_ir_registro = findViewById(R.id.singUp); // Para el cambio de boton de la parte superior del formulario
        btn_acceder = findViewById(R.id.botonLoginCompleto); // Ingresar a la otra interfaz
        et_usuario = (EditText) findViewById(R.id.LnombreUsuarioEdit);
        et_contrasena = (EditText) findViewById(R.id.LcontraseñaEdit);
        check_guardar_sesion = findViewById(R.id.checkbox_guardarSesion);

        preferencias = getSharedPreferences("var_sesion", Context.MODE_PRIVATE);
        editor_preferencias = preferencias.edit();
    }

    /*@SuppressLint("Range")
    private boolean ExisteDuplaUsuarioContrasena(Cursor cursor,String login,String password){
        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                if(cursor.getString( cursor.getColumnIndex("login") ).equals(login)
                        && cursor.getString( cursor.getColumnIndex("password") ).equals(password)){
                    cursor.close();
                    return true;
                }
            }
        } finally {
            cursor.close();
        }
        return false;
    }*/
}

