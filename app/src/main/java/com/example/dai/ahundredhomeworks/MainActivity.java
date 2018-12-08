package com.example.dai.ahundredhomeworks;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usuario, con; //se declaran los atributos que se van a usar

    @Override
    protected void onCreate(Bundle savedInstanceState) { //se hace la conexión con la interfaz y se instancian los atributos
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        usuario= (EditText)findViewById(R.id.etUsuario);
        con = (EditText)findViewById(R.id.etContrasena);


    }

    public void registrarse (View v){ //Manda al usuario a la ventana en donde puede registrarse para quedar guardado en la base de datos
        Intent intent= new Intent(this, Registro.class);
        startActivity(intent);
    }



    public void iniciarSesion(View v){ //manda al usuario a la nueva pantalla de su perfil, si puso bien su usuario
        //y contraseñá y carga sus datos en la nueva pantalla. Si el usuario o contraseña son incorrectos le manda
        //al usuario un message box indicandole que alguno de los dos es incorrecto
        try{
            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administración", null, 1);
            SQLiteDatabase db= admin.getWritableDatabase();
            String nombre= usuario.getText().toString();
            String contraS = con.getText().toString();

            //checaremos si existe un usuario con ese nombre
            Cursor fila= db.rawQuery("select nombre from usuario where nombre= '" +nombre+"' and contra= '" +contraS+ "'", null);
            //si tiene datos
            if(fila.moveToFirst()){
                // ir a la ventana que muestra el perfil del usuario
                Intent intent= new Intent(this, InicioSesion.class);
                Bundle b = new Bundle();
                b.putString("Nombre usuario", usuario.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }else{
                Toast.makeText(this, "No existe alumnno con ese nombre  y contraseña", Toast.LENGTH_LONG).show();
            }
            db.close();

        }catch (Exception e){
            Toast.makeText(this, "Error, no se pudo inciar sesión: "+e, Toast.LENGTH_LONG).show();
        }
    }

    public void irAEjercicios (View v){ //Este método manda al usuario a una nueva actividad en que puede
        //consultar una página web con ejercicios de Cálculo
        Intent intent= new Intent(this, Web.class);
        startActivity(intent);
    }

    public void irABorrar(View v){ //Este método manda al usuario a una nueva actividad en que
        //se le pregunta si esta seguro de eliminarse y le permite hacerlo.
        Intent intent= new Intent(this, Eliminado.class);
        startActivity(intent);
    }

}
