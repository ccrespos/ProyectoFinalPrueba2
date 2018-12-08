package com.example.dai.ahundredhomeworks;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    //se declaran los atributos que se van a usar
    private EditText nomUsu, contra, desc, cel, correo, institucion, forta;
    private Spinner tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //se hace la conexión con la interfaz y se instancian los atributos
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);
        nomUsu= (EditText)findViewById(R.id.etUsuario);
        contra=  (EditText)findViewById(R.id.etContra);
        tipo= (Spinner)findViewById(R.id.spTipo);
        desc= (EditText)findViewById(R.id.etDescripcion);
        cel= (EditText) findViewById(R.id.etCelular);
        correo = (EditText) findViewById(R.id.etCorreo);
        institucion = (EditText) findViewById(R.id.etInstitucion);
        forta=(EditText) findViewById(R.id.etFortalezaR);
    }

    public void alta (View v){ //Este método le permite al usuario, crear su perfil y darse de alta
        //en la base de datos para comenzar a usar la aplicación
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "administración", null, 1); // ¿?
        SQLiteDatabase bd= admin.getWritableDatabase(); //¿?
        String contrasena= contra.getText().toString();
        String nombre= nomUsu.getText().toString();
        String type= tipo.getPopupContext().toString();
        String descripcion= desc.getText().toString();
        String celular= cel.getText().toString();
        String mail= correo.getText().toString();
        String insti = institucion.getText().toString();
        String fortaS = forta.getText().toString();

        ContentValues alta= new ContentValues();
        alta.put("nombre", nombre);
        alta.put("contra", contrasena);
        alta.put("tipo", type);
        alta.put("descripcion", descripcion);
        alta.put("celular", celular);
        alta.put("correo", mail);
        alta.put("institucion", insti);
        alta.put("fortaleza", fortaS);

        bd.insert("usuario", null, alta);
        bd.close();
        Toast.makeText(this, "Datos cargados", Toast.LENGTH_LONG).show();
    }

    public void aceptar (View v){ //Regresa al usuario a la actividad en que puede iniciar sesión.
        Intent intent= new Intent(this, MainActivity.class);
        Bundle b= new Bundle();
        b.putString("Nombre", nomUsu.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }


}
