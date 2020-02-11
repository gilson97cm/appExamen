package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText txtCode;
    EditText txtDetail;
    EditText txtTotal;
    EditText txtType;

    Button btnRegister;
    Button btnList;

    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtCode = (EditText) findViewById(R.id.txtCode);
        txtDetail = (EditText) findViewById(R.id.txtDetail);
        txtTotal = (EditText) findViewById(R.id.txtTotal);
        txtType = (EditText) findViewById(R.id.txtType);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnList = (Button) findViewById(R.id.btnList);

        btnRegister.setOnClickListener(this);
        btnList.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(this);


    }

    // INICIO FUNCION ONCLICK
    @Override
    public void onClick(View v) {

        String codigo_ = txtCode.getText().toString();
        String detalle = txtDetail.getText().toString();
        String total = txtTotal.getText().toString();
        String tipo = txtType.getText().toString();

        Intent intent = null;
        switch (v.getId()){
            case R.id.btnRegister:
                if(!codigo_.isEmpty() || !detalle.isEmpty() || !total.isEmpty() || !tipo.isEmpty()){
                    insert(codigo_,detalle,total,tipo);
                }else{
                    Toast.makeText(this, "Hay campos vacios.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnList:
                intent = new Intent(this, ListaPedidos.class);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                search(codigo_);
                break;
        }
        if (intent != null){
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
    //FIN FUNCION ONCLICK


    public void insert (String codigo, String detalle, String total,String tipo){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();


        if ((!codigo.isEmpty()) && (!detalle.isEmpty()) && (!total.isEmpty()) && (!tipo.isEmpty()) ) {

            ContentValues registro = new ContentValues();

            Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos WHERE codigo = '"+codigo+"'", null);

            if (fila.getCount() <= 0){
                registro.put("codigo", codigo);
                registro.put("detalle", detalle);
                registro.put("total", total);
                registro.put("tipo", tipo);

                baseDatos.insert("pedidos", null, registro);
                baseDatos.close();

                //limpiar los campos despues de registrar
                txtCode.setText("");
                txtDetail.setText("");
                txtTotal.setText("");
                txtType.setText("");
                Toast.makeText(this, "Se registro un pedido", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "el código ya existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Hay campos vacios.", Toast.LENGTH_SHORT).show();
        }
    }

    //funcion buscar
    public void search (String codigo){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();


        if (!codigo.isEmpty()) {
            Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos WHERE codigo = '"+codigo+"' ", null);

            if (fila.moveToFirst()) {
                txtDetail.setText(fila.getString(1));
                txtTotal.setText(fila.getString(2));
                txtType.setText(fila.getString(3));
            } else {
                Toast.makeText(this, "No hay registro.", Toast.LENGTH_SHORT).show();
            }
            baseDatos.close();
        } else {
            Toast.makeText(this, "Nada para buscar.", Toast.LENGTH_SHORT).show();
        }

    }
}
