package com.example.dai.ahundredhomeworks;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicioSesion extends AppCompatActivity {
    //se declaran los atributos que se van a usar
    private TextView nom, inst, cor, cel, fort, des, nomConsulta;

    private EditText nvocorreo, nvaFortaleza, nomBuscado;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //se hace la conexión con la interfaz y se instancian los atributos
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        nom= (TextView)findViewById(R.id.tvNombre);
        Bundle bundle = this.getIntent().getExtras();
        nom.setText(bundle.getString("Nombre usuario"));
        try {
            //relacionar interface, componentes y código
            inst = (TextView) findViewById(R.id.tvInstitucion);
            cor = (TextView) findViewById(R.id.tvCorreo);
            cel = (TextView) findViewById(R.id.tvCelular);
            fort = (TextView) findViewById(R.id.tvFortalezas);
            des = (TextView) findViewById(R.id.tvDescripcion);

            nvocorreo = (EditText) findViewById(R.id.etModificaCorreo);
            nvaFortaleza = (EditText) findViewById(R.id.etNuevaFortaleza);

            nomBuscado=(EditText)findViewById(R.id.etBusquedaUsuario);

            nomConsulta = (TextView) findViewById(R.id.nomCon);


            //establecer los datos del perfil desde la información de la base de datos, tomando el nombre de usuario de la pantalla de inicio
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administración", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor fila = db.rawQuery("select institucion, correo, celular, descripcion, fortaleza from usuario where nombre= '" + nom.getText().toString() + "'", null);
            //si tiene datos
            //Acomoda los datos en la pantalla del perfil del usuario
            if (fila.moveToFirst()) {
                inst.setText("" + "Institución: " + fila.getString(0));
                cor.setText(fila.getString(1));
                cel.setText(fila.getString(2));
                des.setText(fila.getString(3));
                if(fila.getString(4)==null){
                    fort.setText("");
                }
                else
                    fort.setText(""  + fila.getString(4));
            } else {
            Toast.makeText(this, "no existe alumnno con ese nombre", Toast.LENGTH_LONG).show();
            }
            db.close();
            }catch (Exception ex){
                Toast.makeText(this, "error: " + ex, Toast.LENGTH_LONG).show();
            }
    }


    public void agregarFortaleza(View v) { //Con este método el usurio puede agregar fortalezas a su perfil y se guardan en la base de datos
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administración", null, 1);
        SQLiteDatabase db= admin.getWritableDatabase(); //Hace conexión con la base de datos

        String nombre= nom.getText().toString();
        String fortaleza= fort.getText().toString();
        String nuevaFortaleza= nvaFortaleza.getText().toString();

        ContentValues registro= new ContentValues();
        //Guarda las nuevas fortalezas en la base de datos
        registro.put("nombre", nombre);
        registro.put("fortaleza", fortaleza+" , "+ nuevaFortaleza);

        int cant= db.update("usuario", registro, "nombre='"+nombre+"'", null  );

        db.close();

        if (cant==1)
            Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, "No se pudo modificar", Toast.LENGTH_LONG).show();
        nvaFortaleza.setText("");
    }

    public void modifica(View v){ //Con este método se puede modificar el correo del usuario y guardarlo en la base de datos


            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administración", null, 1);
            SQLiteDatabase db= admin.getWritableDatabase(); //Hace conexión con la base de datos

            String nombre= nom.getText().toString();
            String nuevoCor= nvocorreo.getText().toString();
            ContentValues registro= new ContentValues();
        //Guarda las nuevas fortalezas en la base de datos
            registro.put("nombre", nombre);
            registro.put("correo", nuevoCor);

            int cant= db.update("usuario", registro, "nombre='"+nombre+"'", null  );

            db.close();

            if (cant==1)
                Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show();

            else
                Toast.makeText(this, "No se pudo modificar", Toast.LENGTH_LONG).show();
            nvocorreo.setText("");

    }

    public void irOtroUser (View v){
        Intent intent= new Intent(this, Eliminado.class);
        Bundle b = new Bundle();
        b.putString("Usuario", nom.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }

    public void consulta(View v){ //Este método busca y muestra los datos de otro usuario
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administración", null, 1);
        SQLiteDatabase db= admin.getWritableDatabase(); //Hace conexión con la base de datos
        String nomBus=nomBuscado.getText().toString();

        //Trae los datos del usuario directo de la base de datos
        Cursor fila= db.rawQuery("select nombre, institucion, correo, celular, fortaleza, descripcion from usuario where nombre='"+nomBus+"'", null);

        if(fila.moveToFirst()){//si tiene datos
            //llena el TextView con los datos del ususario
            nomConsulta.setText(""+ "Nombre: "+fila.getString(0)+ "\n"+ "Institución: " + fila.getString(1)+ "\n"+ "Correo: "+fila.getString(2) + "\n"+ "Celular: "+fila.getString(3)+ "\n"+ "Fortalezas: "+fila.getString(4)+  "\n"+ "Descripción: "+fila.getString(5));

        }else{
            Toast.makeText(this, "no existe alumnno con ese nombre", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

}
