package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private List<String> plantas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.EditText1);
        plantas = new ArrayList<String>();
        crearLista();
    }

    public void crearLista() {
     try {
         InputStream plantitas = this.getResources().openRawResource(R.raw.plantas);
         BufferedReader leer = new BufferedReader(new InputStreamReader(plantitas));
         String linea;
         while ((linea = leer.readLine()) != null)
             plantas.add(linea.split(";")[0]);
         plantitas.close();
     }catch (IOException e){}
    }

    public boolean esta(String nombre){
        for (String nombreAux : plantas)
            if (nombre.equalsIgnoreCase(nombreAux))
                return true;
        return false;
    }

    public void buscar(View view) {
        String nombre = et1.getText().toString();

        if (esta(nombre)){
            Intent descrip = new Intent(this, Descripcion.class);
            descrip.putExtra("dato", nombre);
            startActivity(descrip);
        }
        else Toast.makeText(this, "No se encuentra", Toast.LENGTH_LONG).show();
    }




}
