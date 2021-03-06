package com.example.administrador.superagentecomercio.superagente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.DepartamentosUbigeoAdapter;
import com.example.administrador.superagentecomercio.adapter.DistritoUbigeoAdapter;
import com.example.administrador.superagentecomercio.adapter.ProvinciaUbigeoAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.UbigeoEntity;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AgregarOperario extends Activity {

    FloatingActionButton btn_guardar,btn_menu,btn_regresar;
    Button btn_siguiente;
    Spinner sp_sexo;
    TextView pruebas;
    private Comercio idcomercio;

    EditText et_dni,et_nombre,et_paterno,et_materno,et_celular,et_fono;
    String dni,nombre,paterno,materno,celular,fono,sexo,user;

    String[] items;
    private boolean isFirstTime = true;


    ArrayList<UbigeoEntity> ubigeoArrayListDepartamento;
    ArrayList<UbigeoEntity> ubigeoArrayListDistrito;
    ArrayList<UbigeoEntity> ubigeoArrayListProvincia;
    DepartamentosUbigeoAdapter departamentosUbigeoAdapter;
    DistritoUbigeoAdapter distritoUbigeoAdapter;
    ProvinciaUbigeoAdapter provinciaUbigeoAdapter;

    String departamentoUbigeo, departamentoUbigeoDesc, distritoUbigeo,
            distritoUbigeoDesc, provinciaUbigeo, provinciaUbigeoDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_operario);

        btn_regresar = (FloatingActionButton) findViewById(R.id.action_return);
        btn_menu = (FloatingActionButton)findViewById(R.id.action_menu);
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);

        et_dni = (EditText) findViewById(R.id.et_dni);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_paterno = (EditText) findViewById(R.id.et_paterno);
        et_materno = (EditText) findViewById(R.id.et_materno);
        et_celular = (EditText) findViewById(R.id.et_celular);
        et_fono = (EditText) findViewById(R.id.et_fono);


        sp_sexo = (Spinner) findViewById(R.id.sp_sexo);
        pruebas = (TextView) findViewById(R.id.pruebas);


        Bundle bundle = getIntent().getExtras();
        idcomercio = bundle.getParcelable("idcomercio");
        dni = bundle.getString("dni");
        nombre = bundle.getString("nombre");
        paterno = bundle.getString("paterno");
        materno = bundle.getString("materno");
        celular = bundle.getString("celular");
        fono = bundle.getString("fono");
        sexo = bundle.getString("sexo");
        user = bundle.getString("user");

        et_dni.setText(dni);
        et_nombre.setText(nombre);
        et_paterno.setText(paterno);
        et_materno.setText(materno);
        et_celular.setText(celular);
        et_fono.setText(fono);

        //idcomercio = bundle.getParcelable("idcomercio");
        //user = bundle.getString("user");

        items = getResources().getStringArray(R.array.lista_sexos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_sexo.setAdapter(adapter);
        sp_sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                sexo=items[position];
                /*if(isFirstTime){
                    isFirstTime = false;
                }
                else {
                    //Toast.makeText(getApplicationContext(),items[position],Toast.LENGTH_LONG).show();
                    //pruebas.setText(items[position]);

                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dni = et_dni.getText().toString();
                nombre = et_nombre.getText().toString();
                paterno = et_paterno.getText().toString();
                materno = et_materno.getText().toString();
                celular = et_celular.getText().toString();
                fono = et_fono.getText().toString();



                if(dni.length()!= 0 && dni.length() == 8  && nombre.length() != 0 && paterno.length() != 0 && materno.length() != 0 && celular.length() != 0 && celular.length() == 9 && fono.length() != 0 && fono.length() == 9){

                    Intent intent = new Intent(AgregarOperario.this, AgregarOperario2.class);
                    intent.putExtra("idcomercio", idcomercio);
                    intent.putExtra("dni", dni);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("paterno", paterno);
                    intent.putExtra("materno", materno);
                    intent.putExtra("celular", celular);
                    intent.putExtra("fono", fono);
                    intent.putExtra("sexo", sexo);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }else if(dni.length()!= 8){
                    Toast.makeText(AgregarOperario.this, "Ingrese su dni", Toast.LENGTH_SHORT).show();
                }else if(nombre.length() == 0){
                    Toast.makeText(AgregarOperario.this, "Ingrese su nombre", Toast.LENGTH_SHORT).show();
                }else if(paterno.length() == 0){
                    Toast.makeText(AgregarOperario.this, "Ingrese su apellido paterno", Toast.LENGTH_SHORT).show();
                }else if(materno.length() == 0){
                    Toast.makeText(AgregarOperario.this, "Ingrese su apellido materno", Toast.LENGTH_SHORT).show();
                }else if(celular.length() == 0 && celular.length() != 9){
                    Toast.makeText(AgregarOperario.this, "Ingrese un número de celular", Toast.LENGTH_SHORT).show();
                }else if(fono.length() == 0 && fono.length() != 9){
                    Toast.makeText(AgregarOperario.this, "Ingrese un número de teléfono fijo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AgregarOperario.this, "Debe llenar los campos solicitados", Toast.LENGTH_SHORT).show();

                }


            }
        });

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarOperario.this, ListarOperario.class);
                intent.putExtra("comercio", idcomercio);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

    }


    public void cancelar() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea regresar al menú?");
        alertDialog.setTitle("Cancelar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AgregarOperario.this, MenuCliente.class);
                intent.putExtra("comercio", idcomercio);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

}
