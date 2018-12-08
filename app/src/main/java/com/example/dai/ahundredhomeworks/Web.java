package com.example.dai.ahundredhomeworks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) { //se hace la conexión con la interfaz y se instancian los atributos
        //Cuando se crea la ventana, se da acceso a la página de internet de ejercicios de cálculo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView mywebView = (WebView) findViewById(R.id.webView);
        mywebView.loadUrl("https://sites.google.com/site/calcdifintiv/");
    }
}
