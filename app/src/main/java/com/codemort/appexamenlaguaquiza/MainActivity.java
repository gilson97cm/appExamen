package com.codemort.appexamenlaguaquiza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


    }

    // INICIO FUNCION ONCLICK
    @Override
    public void onClick(View v) {
        //se utiliza un SWITCH porque en la vista AGREGAR hay mas de dos botones
        switch (v.getId()){
            //el case es para validar que boton se presiona
            case R.id.btnRegister:
                break;
            case R.id.btnList:
                break;
        }
    }
    //FIN FUNCION ONCLICK
}
