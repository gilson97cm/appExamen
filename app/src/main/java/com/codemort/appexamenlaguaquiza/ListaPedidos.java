package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        CargarListado();

    }

    private void CargarListado() {
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas() {
        ArrayList<String> datos = new ArrayList<String>();
        Connection helper = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM pedidos";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                String linea = c.getString(0) + " " + c.getString(1)+" "+c.getString(2)+" "+c.getString(3);
                datos.add(linea);
            } while (c.moveToNext());

        }
        db.close();
        return datos;
    }
}
