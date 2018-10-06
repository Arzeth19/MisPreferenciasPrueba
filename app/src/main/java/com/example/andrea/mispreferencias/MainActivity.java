package com.example.andrea.mispreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardar(View view){
        EditText etNombre = findViewById(R.id.et_nombre_usuario);
        EditText etClave = findViewById(R.id.et_clave);
        String nombre = etNombre.getText().toString();
        String clave = etClave.getText().toString();
        SharedPreferences preferences  = getSharedPreferences(nombre, Context.MODE_PRIVATE); //para guardar información en un xml
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("clave", clave);
        editor.commit(); //Se hacen las operaciones pendientes, el que cierra ya
    }

    public void entrar(View view){
        EditText etNombre = findViewById(R.id.et_nombre_usuario);
        String nombre = etNombre.getText().toString();
        SharedPreferences preferences  = getSharedPreferences(nombre, Context.MODE_PRIVATE); //para guardar información en un xml

        EditText etClave = findViewById(R.id.et_clave);
        String clave = etClave.getText().toString();
        String claveGuardada = preferences.getString("clave", "nada"); //"Nada" valor que tendria si no tecleo nada
        if(clave.equals(claveGuardada)){
            Snackbar.make(view, "Exito", Snackbar.LENGTH_LONG).show(); //Para qye salga mensaje de clave exitosa
        }
        else{
            Snackbar.make(view, "Contraseña incorrecta", Snackbar.LENGTH_LONG).show(); //Para qye salga mensaje de clave mala
        }
    }
}
