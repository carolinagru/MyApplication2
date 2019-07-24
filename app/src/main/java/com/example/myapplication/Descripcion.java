package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Descripcion extends AppCompatActivity {

    private ImageView img;
    private String nombre;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        img = findViewById(R.id.imagen);
        nombre = getIntent().getStringExtra("dato");
        nombre = nombre.toLowerCase();
        text = findViewById(R.id.texto);
        agregarDescripcion();
        text.setMovementMethod(new ScrollingMovementMethod());
    }

    protected void agregarDescripcion(){
       AssetManager archivo = getAssets();
       InputStream leer = null;
    try {
          int id = getResources().getIdentifier(nombre,"drawable",this.getPackageName());
          img.setImageResource(id);
          String ruta = "archivos/"+nombre+".txt";
          leer = archivo.open(ruta);
          String completo = convertirBytes(leer);
          text.setText(completo);
          leer.close();


    } catch (IOException e){}
    }

    public String convertirBytes( InputStream inputStream ) throws IOException
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len=0;
        while ( (len=inputStream.read(bytes))>0 )
        {
            b.write(bytes,0,len);
        }
        return new String( b.toByteArray(),"UTF8");
    }
}

