package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity {

    //instancia del elemento de la vista
    ListView listView;
    //se crean variables para el conjunto de datos de la lista
    ArrayList<String> listado;


    //para que no colapese la vista
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        listView = (ListView) findViewById(R.id.listView);

    }
}
