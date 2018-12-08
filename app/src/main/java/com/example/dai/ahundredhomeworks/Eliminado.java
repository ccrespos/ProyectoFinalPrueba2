package com.example.dai.ahundredhomeworks;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminado extends AppCompatActivity {

    private EditText usuario, con; //se declaran los atributos que se van a usar

    protected void onCreate (Bundle savedInstanceState){ //se hace la conexión con la interfaz y se instancian los atributos
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminado);



        usuario = (EditText) findViewById(R.id.etUsuario);
        con = (EditText) findViewById(R.id.etContrasena);
    }



    public void borrar (View v){ //Este método elimina al usuario de la base de datos
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administración", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String nombre = usuario.getText().toString();
            int cant = db.delete("usuario", "nombre='" + nombre + "'", null);
            db.close();
            if (cant == 1)
                Toast.makeText(this, "Usuario borrado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "No se pudo borrar", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error, no se pudo borrar perfil: " + e, Toast.LENGTH_LONG).show();
        }
    }

}
