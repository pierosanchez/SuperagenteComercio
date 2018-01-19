package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.DetalleClaveAccesoAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.PasswordComercio;

import java.util.ArrayList;

public class CambioClaveAcceso extends Activity {

    private PasswordComercio usuario;
    Button btn_aceptar_cambio_clave, btn_regresar_cambio_clave;
    EditText txt_clave_acceso_actual_cambio, txt_respuesta_pregunta_cambio, txt_nueva_clave_acceso, txt_confirme_nueva_clave_acceso;
    TextView txt_primera_pregunta_cambio;
    String usu;
    DetalleClaveAccesoAdapter detalleClaveAccesoAdapter;
    ArrayList<PasswordComercio> usuarioEntityArrayList;
    String cliente, cli_dni, id_comercio;
    private Comercio comercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_clave_acceso);

        btn_aceptar_cambio_clave = (Button) findViewById(R.id.btn_aceptar_cambio_clave);
        btn_regresar_cambio_clave = (Button) findViewById(R.id.btn_regresar_cambio_clave);

        txt_clave_acceso_actual_cambio = (EditText) findViewById(R.id.txt_clave_acceso_actual_cambio);
        txt_primera_pregunta_cambio = (TextView) findViewById(R.id.txt_primera_pregunta_cambio);
        txt_respuesta_pregunta_cambio = (EditText) findViewById(R.id.txt_respuesta_pregunta_cambio);
        txt_nueva_clave_acceso = (EditText) findViewById(R.id.txt_nueva_clave_acceso);
        txt_confirme_nueva_clave_acceso = (EditText) findViewById(R.id.txt_confirme_nueva_clave_acceso);

        Bundle bundle = getIntent().getExtras();
        comercio = bundle.getParcelable("comercio");

        usuarioEntityArrayList = null;
        detalleClaveAccesoAdapter = new DetalleClaveAccesoAdapter(usuarioEntityArrayList, getApplication());

        ejecutarLista();

        btn_aceptar_cambio_clave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nueva_clave = txt_nueva_clave_acceso.getText().toString();
                String nueva_clave_confirmacion = txt_confirme_nueva_clave_acceso.getText().toString();

                if (nueva_clave.equals(nueva_clave_confirmacion)) {
                    CambioClaveAcceso.actualizarClaveAcceso validador = new CambioClaveAcceso.actualizarClaveAcceso();
                    validador.execute();
                } else if (!nueva_clave.equals(nueva_clave_confirmacion)) {
                    Toast.makeText(CambioClaveAcceso.this, "No coinciden las contraseñas ingresadas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_regresar_cambio_clave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CambioClaveAcceso.this, MenuCliente.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });
    }

    private class actualizarClaveAcceso extends AsyncTask<String,Void,PasswordComercio> {
        String clave = txt_clave_acceso_actual_cambio.getText().toString();
        String nueva_clave = txt_nueva_clave_acceso.getText().toString();
        String respuesta = txt_respuesta_pregunta_cambio.getText().toString();

        @Override
        protected PasswordComercio doInBackground(String... params) {
            PasswordComercio user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.actualizarClaveAcceso(clave, comercio.getIdComercio(),  nueva_clave, respuesta);
                //Log.e("idCliente", "CodCliente=" + user.getCodCliente() + ", usuarioId=" + usuario.getUsuarioId());
                //usuario.setClaveAcceso(user.getClaveAcceso());
            } catch (Exception e) {
                user = null;
                //flag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(PasswordComercio usuarioEntity) {
            usuario = usuarioEntity;
            if (usuario.getReptaCambioCalve() != null) {
                if (usuario.getReptaCambioCalve().equals("1")) {
                    Toast.makeText(CambioClaveAcceso.this, "Pregunta secreta incorrecta", Toast.LENGTH_SHORT).show();
                } else if (usuario.getReptaCambioCalve().equals("2")) {
                    Toast.makeText(CambioClaveAcceso.this, "Pregunta secreta incorrecta", Toast.LENGTH_SHORT).show();
                } else if (usuario.getReptaCambioCalve().equals("3")) {
                    Toast.makeText(CambioClaveAcceso.this, "La contraseña ingresada, ya existe para este usuario", Toast.LENGTH_SHORT).show();
                } else if (usuario.getReptaCambioCalve().equals("0")) {
                    Intent intent = new Intent(CambioClaveAcceso.this, CambioClaveAccesoExitosa.class);
                    intent.putExtra("comercio", comercio);
                    startActivity(intent);
                    finish();
                }
            } else {
                //mensaje_error = getString(R.string.msg_error_sin_conexion);
                //sinConexion = true;

            }
        }
    }

    private void ejecutarLista(){

        try {
            CambioClaveAcceso.DetalleClaveAcceso listadoBeneficiario = new CambioClaveAcceso.DetalleClaveAcceso();
            listadoBeneficiario.execute();
        } catch (Exception e){
            //listadoBeneficiario = null;
        }

    }

    private class DetalleClaveAcceso extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                usuarioEntityArrayList = dao.detalleClaveAcceso(comercio.getIdComercio());
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detalleClaveAccesoAdapter.setNewListClaveOlvidadaComercio(usuarioEntityArrayList);
            detalleClaveAccesoAdapter.notifyDataSetChanged();
            txt_primera_pregunta_cambio.setText(usuarioEntityArrayList.get(0).getPregunta());
        }
    }
}
