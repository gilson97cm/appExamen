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

   //instancia de las vistas (txtCode,txtDetail,ect. Son los
   // id de los campos de la vista, para que no te confundas
   // utiliza los mismos nombres en las vistas y en estas variables)
    EditText txtCode;
    EditText txtDetail;
    EditText txtTotal;
    EditText txtType;

    Button btnRegister;
    Button btnList;

    Button btnSearch;
    Button btnTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacionamos las variables declaradas arriba con las vistas
        //variable de arriba                    //id de las vistas
        txtCode = (EditText) findViewById(R.id.txtCode);
        txtDetail = (EditText) findViewById(R.id.txtDetail);
        txtTotal = (EditText) findViewById(R.id.txtTotal);
        txtType = (EditText) findViewById(R.id.txtType);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnList = (Button) findViewById(R.id.btnList);

        //se agregaa el evento click a los botones
        // si te fijas arriba en el nombre de la clase despues de "AppCompatActivity"
        // se aumenta "implements View.OnClickListener"
        //y se crea la funcion ONCLICK
        btnRegister.setOnClickListener(this);
        btnList.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnTotal = (Button) findViewById(R.id.btnTotal);

        btnSearch.setOnClickListener(this);
        btnTotal.setOnClickListener(this);


    }

    // INICIO FUNCION ONCLICK
    @Override
    public void onClick(View v) {
        //creas variables y tomas el valor de cada campo de texto
        //para guardar en las variables
        String codigo_ = txtCode.getText().toString();
        String detalle = txtDetail.getText().toString();
        String total = txtTotal.getText().toString();
        String tipo = txtType.getText().toString();

        Intent intent = null;
        //se utiliza un SWITCH porque en la vista AGREGAR hay mas de dos botones
        switch (v.getId()){
            //el case es para validar que boton se presiona
            case R.id.btnRegister:
                // validas si los campos etan vacios
                //esta el signo "!" antes de cada validacion para negar y se lee asi:
                //SI CODIGO NO ESTA VACIO inserta los datos
                //si no estuviese el signo "!" se leeria asi
                //SI CODIGO ESTA VACIO no inserta los datos

                //no te vayas a confundir
                // cuando esta el signo antes de la validacion (--> !codigo.isEmpty() ) la funcion "insert" va dentro del "if"
                //cuando la validacion esta sin signo (--> codigo.isEmpty() ) la funcion "insert" va en el "else"
                if(!codigo_.isEmpty() || !detalle.isEmpty() || !total.isEmpty() || !tipo.isEmpty()){
                    //si todos loc ampos estan llenos
                    //se ejecuta la funcion "insert"
                    insert(codigo_,detalle,total,tipo);
                }else{
                    //si algun campo esta vacio se muestra un mensaje
                    Toast.makeText(this, "Hay campos vacios.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnList:
                //creas un intent para ir a la vista de las listas
                //el "this" hace refrencia a la clase donde te encuentras en este caso a "MainActivitiy"
                intent = new Intent(this, ListaPedidos.class);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                search(codigo_);
                break;
            case R.id.btnTotal:
                intent = new Intent(this, TotalPedidos.class);
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
