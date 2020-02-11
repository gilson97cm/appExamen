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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Modificar extends AppCompatActivity implements View.OnClickListener {

    String codigo;
    String detalle;
    String total;
    String tipo;

    //INSTANCIAR VISTAS
    TextView txtCodeEdit;
    EditText txtDetailEdit;
    EditText txtTotalEdit;
    EditText txtTypeEdit;

    Button btnEdit;
    Button btnDelete;

    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        //recibe datos que envia la lista
        Bundle data = getIntent().getExtras();
        if (data != null) {
            codigo = data.getString("codigo");
            detalle = data.getString("detalle");
            total = data.getString("total");
            tipo = data.getString("tipo");
        }

        txtCodeEdit = (TextView) findViewById(R.id.txtCodeEdit);
        txtDetailEdit = (EditText) findViewById(R.id.txtDetailEdit);
        txtTotalEdit = (EditText) findViewById(R.id.txtTotalEdit);
        txtTypeEdit = (EditText) findViewById(R.id.txtTypeEdit);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        txtCodeEdit.setText(codigo);
        txtDetailEdit.setText(detalle);
        txtTotalEdit.setText(total);
        txtTypeEdit.setText(tipo);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        ///////7 para poner la flechita de atras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {
        String cod = txtCodeEdit.getText().toString();
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnEdit:
                update(cod);
                // Toast.makeText(this, "Editado", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ListaPedidos.class);
                break;
            case R.id.btnDelete:
                destroy(cod);
                intent = new Intent(this, ListaPedidos.class);
                // Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
                break;
        }
        if(intent != null){
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        }
    }

    public void update (String codigo_){
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();
        String detalle = txtDetailEdit.getText().toString();
        String total = txtTotalEdit.getText().toString();
        String tipo = txtTypeEdit.getText().toString();


        if ((!detalle.isEmpty()) && (!total.isEmpty()) && (!tipo.isEmpty()) ) {
            ContentValues registro = new ContentValues();
            registro.put("detalle", detalle);
            registro.put("total", total);
            registro.put("tipo", tipo);
            
            Integer t = Integer.parseInt(txtTypeEdit.getText().toString());
            if(t !=7){
                baseDatos.update("pedidos", registro, "codigo = '"+codigo_+"'" , null);
                baseDatos.close();
                Toast.makeText(this, "Se actualizo un pedido", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "el tipo tiene que ser diferente de 7", Toast.LENGTH_SHORT).show();
            }

          
        } else {
            Toast.makeText(this, "Hay campos vacios.", Toast.LENGTH_SHORT).show();
        }

    }
    public void destroy (String codigo_) {
        Connection db = new Connection(this, "bddemo", null, 1);
        SQLiteDatabase baseDatos = db.getWritableDatabase();

        if(!codigo_.equals("")){
            Cursor fila = baseDatos.rawQuery("SELECT * FROM pedidos WHERE codigo = '"+codigo_+"' ", null);
            if(fila.getCount() <=0){
                Toast.makeText(this, "Nada para eliminar.", Toast.LENGTH_SHORT).show();
            }else {
                //validar si es menor a 1000 se elimina
                //capturamos el valor del campo total y transformamos a enterp
                Integer t = Integer.parseInt(txtTotalEdit.getText().toString());
                //validamos
                if(t < 1000){
                    baseDatos.delete("pedidos","codigo = '"+codigo_+"' ",null);
                    baseDatos.close();
                    Toast.makeText(this, "Se elimino.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "el total debe ser menor a 1000", Toast.LENGTH_SHORT).show();
                }


            }
        }else{
            Toast.makeText(this, "Nada para eliminar.", Toast.LENGTH_SHORT).show();
        }
    }
}
