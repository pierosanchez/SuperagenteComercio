package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.DistritoAdapter;
import com.example.administrador.superagentecomercio.adapter.PreguntaAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteComercioBD;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.PasswordComercio;
import com.example.administrador.superagentecomercio.entity.Pregunta;
import com.example.administrador.superagentecomercio.utils.Constante;

import java.util.ArrayList;

public class AfiliacionComercio extends Activity {

    Button btn_continuar;
    ArrayList<Pregunta> preguntaArrayList;
    PreguntaAdapter preguntaAdapter;
    Spinner sp_pregunta;
    EditText txt_clave_acceso, txt_confirma_clave_acceso, txt_respuesta, txt_confirme, txt_correo_electronico, txt_celular;
    private Comercio comercio;
    private String pregunta, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afiliacion_comercio);

        btn_continuar = (Button) findViewById(R.id.btn_continuar);

        txt_clave_acceso = (EditText) findViewById(R.id.txt_clave_acceso);
        txt_confirma_clave_acceso = (EditText) findViewById(R.id.txt_confirma_clave_acceso);
        txt_respuesta = (EditText) findViewById(R.id.txt_respuesta);
        txt_confirme = (EditText) findViewById(R.id.txt_confirme);
        txt_correo_electronico = (EditText) findViewById(R.id.txt_correo_electronico);
        txt_celular = (EditText) findViewById(R.id.txt_celular);

        sp_pregunta = (Spinner) findViewById(R.id.sp_pregunta);

        Bundle bundle = getIntent().getExtras();
        comercio = bundle.getParcelable("comercio");
        numero = bundle.getString("numero");

        if (numero.equals("")) {
            txt_celular.setText(numero);
            txt_celular.setEnabled(false);
        } else {
            txt_celular.setEnabled(true);
        }

        preguntaArrayList = null;
        preguntaAdapter = new PreguntaAdapter(preguntaArrayList, getApplication());
        sp_pregunta.setAdapter(preguntaAdapter);

        ejecutarListaPreguntas();

        sp_pregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pregunta = preguntaAdapter.getItem(position).getDescripcionPregunta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave = txt_clave_acceso.getText().toString();
                String confirm_clave = txt_confirma_clave_acceso.getText().toString();
                String respuesta = txt_respuesta.getText().toString();
                String confirm_respuesta = txt_confirme.getText().toString();
                String correo = txt_correo_electronico.getText().toString();
                String celular = txt_celular.getText().toString();
                if (clave.length() != 0 && confirm_clave.length() != 0 && respuesta.length() != 0 && confirm_respuesta.length() != 0 &&
                        correo.length() != 0 && celular.length() != 0) {
                    if (clave.equals(confirm_clave)) {
                        if (respuesta.equals(confirm_respuesta)) {

                            //EJECUTANDO EL METODO QUE INVOCA EL WEB SERVICE
                            AfiliacionComercio.ValidarUComercio validarUComercio = new AfiliacionComercio.ValidarUComercio();
                            validarUComercio.execute();

                            Intent intent = new Intent(AfiliacionComercio.this, IngresoCuentasComercio.class);
                            intent.putExtra("comercio", comercio);
                            startActivity(intent);
                            finish();

                        } else if (!respuesta.equals(confirm_respuesta)){
                            Toast.makeText(AfiliacionComercio.this, "Las respuestas no coinciden", Toast.LENGTH_LONG).show();
                        }
                    } else if(!clave.equals(confirm_clave)){
                        Toast.makeText(AfiliacionComercio.this, "Las claves de acceso no coinciden", Toast.LENGTH_LONG).show();
                    }
                } else if (clave.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese la clave de acceso", Toast.LENGTH_LONG).show();
                } else if (confirm_clave.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese la confirmacion de la clave de acceso", Toast.LENGTH_LONG).show();
                } else if (respuesta.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese la respuesta a la pregunta", Toast.LENGTH_LONG).show();
                } else if (confirm_respuesta.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese la confirmacion de la respuesta", Toast.LENGTH_LONG).show();
                } else if (correo.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese su correo electronico", Toast.LENGTH_LONG).show();
                } else if (celular.length() == 0){
                    Toast.makeText(AfiliacionComercio.this, "Ingrese su celular", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class ValidarUComercio extends AsyncTask<String, Void, PasswordComercio> {
        String clave = txt_clave_acceso.getText().toString();
        String respuesta = txt_respuesta.getText().toString();
        String correo = txt_correo_electronico.getText().toString();
        String celular = txt_celular.getText().toString();

        @Override
        protected PasswordComercio doInBackground(String... params) {
            PasswordComercio password;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                password = dao.IngresarPasswordComercio(comercio.getIdComercio(), clave, pregunta, respuesta, correo, celular);
                SuperAgenteComercioBD superAgenteBD = new SuperAgenteComercioBD(AfiliacionComercio.this);
                SQLiteDatabase db = superAgenteBD.getWritableDatabase();
                db.execSQL("INSERT INTO " + Constante.TB_NAME + " (movil) VALUES('" + celular + "')");
                db.close();
            } catch (Exception e) {
                password = null;
                //fldag_clic_ingreso = 0;;
            }
            return password;
        }
    }

    private void ejecutarListaPreguntas() {

        try {
            AfiliacionComercio.ListadoPreguntas listadoEmpresas = new AfiliacionComercio.ListadoPreguntas();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }

    }

    private class ListadoPreguntas extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                preguntaArrayList = dao.ListarPreguntas();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //usuarioEntityArrayList.remove(banco = banco_tarjeta);
            preguntaAdapter.setNewListPregunta(preguntaArrayList);
            preguntaAdapter.notifyDataSetChanged();
        }
    }
}
