package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CantidadPedidos extends AppCompatActivity {
    TextView lblSumC;
    TextView lblCodeC;
    TextView lblDetailC;
    TextView lblTotalC;
    TextView lblTypeC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad_pedidos);

        lblSumC = (TextView) findViewById(R.id.lblSumC);
        lblCodeC = (TextView) findViewById(R.id.lblCodeC);
        lblDetailC = (TextView) findViewById(R.id.lblDetailC);
        lblTotalC = (TextView) findViewById(R.id.lblTotalC);
        lblTypeC = (TextView) findViewById(R.id.lblTypeC);

        sum();
        end();
    }

    public void sum (){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();

        Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos", null);

        int c =fila.getCount();

        if (c>0) {
            lblSumC.setText(String.valueOf(c));
        } else {
            Toast.makeText(this, "No hay registro.", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();

    }

    public void end(){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();

        Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos ORDER BY codigo ASC LIMIT 1", null);

        if (fila.moveToFirst()) {
            lblCodeC.setText(fila.getString(0));
            lblDetailC.setText(fila.getString(1));
            lblTotalC.setText(fila.getString(2));
            lblTypeC.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No hay registro.", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();

    }
}
