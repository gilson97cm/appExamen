package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SumaPedidos extends AppCompatActivity {
    TextView lblSum;
    TextView lblCode;
    TextView lblDetail;
    TextView lblTotal;
    TextView lblType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma_pedidos);

        lblSum = (TextView) findViewById(R.id.lblSum);
        lblCode = (TextView) findViewById(R.id.lblCode);
        lblDetail = (TextView) findViewById(R.id.lblDetail);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
        lblType = (TextView) findViewById(R.id.lblType);

        sum();
        first();

    }

    public void sum (){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();

        Cursor fila = baseDatos.rawQuery("SELECT SUM(total) FROM pedidos", null);

        if (fila.moveToFirst()) {
            lblSum.setText(fila.getString(0));
        } else {
            Toast.makeText(this, "No hay registro.", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();

    }

    public void first (){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();

        Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos ORDER BY codigo DESC LIMIT 1", null);

        if (fila.moveToFirst()) {
            lblCode.setText(fila.getString(0));
            lblDetail.setText(fila.getString(1));
            lblTotal.setText(fila.getString(2));
            lblType.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No hay registro.", Toast.LENGTH_SHORT).show();
        }
        baseDatos.close();

    }

}
