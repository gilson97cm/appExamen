package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar extends AppCompatActivity implements View.OnClickListener {

    //INSTANCIAR VISTAS
    TextView txtCodeEdit;
    EditText txtDetailEdit;
    EditText txtTotalEdit;
    EditText txtTypeEdit;

    Button btnEdit;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtCodeEdit = (TextView) findViewById(R.id.txtCodeEdit);
        txtDetailEdit = (EditText) findViewById(R.id.txtDetailEdit);
        txtTotalEdit = (EditText) findViewById(R.id.txtTotalEdit);
        txtTypeEdit = (EditText) findViewById(R.id.txtTypeEdit);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:
               // Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                //Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
