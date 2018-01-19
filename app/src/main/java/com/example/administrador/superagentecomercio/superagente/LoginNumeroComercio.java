package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;

public class LoginNumeroComercio extends Activity {

    private EditText usuario;
    private Button btn_aceptar, btn_salir;
    private String _numero;
    private ProgressBar circleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_numero_comercio);

        usuario = (EditText) findViewById(R.id.usuario);

        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        btn_salir = (Button) findViewById(R.id.btn_salir);

        circleProgressBar = (ProgressBar) findViewById(R.id.circleProgressBar);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _numero = usuario.getText().toString();
                if (_numero.length() == 0){
                    Toast.makeText(LoginNumeroComercio.this, "Ingrese su número de celular por favor", Toast.LENGTH_LONG).show();
                } else if (_numero.length() != 9){
                    Toast.makeText(LoginNumeroComercio.this, "Número de celular incorrecto", Toast.LENGTH_LONG).show();
                } else {
                    circleProgressBar.setVisibility(View.VISIBLE);
                    LoginNumeroComercio.ValidaNumeroCliente validaNumero = new LoginNumeroComercio.ValidaNumeroCliente();
                    validaNumero.execute();
                }
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
    }

    private class ValidaNumeroCliente extends AsyncTask<String, Void, Comercio> {

        String _celular = usuario.getText().toString();

        @Override
        protected Comercio doInBackground(String... params) {
            Comercio user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.ValidaLoginNumeroComercio(_celular);

            } catch (Exception e) {
                user = null;
                //fldag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(Comercio usuarioEntity) {
            if (usuarioEntity != null) {
                if (usuarioEntity.getRptaLogin().equals("01")) {
                    circleProgressBar.setVisibility(View.GONE);
                    Intent sanipesIntent = new Intent(LoginNumeroComercio.this, VentanaErrores.class);
                    startActivityForResult(sanipesIntent, 0);
                    finish();
                } else if (usuarioEntity.getRptaLogin().equals("00")) {
                    circleProgressBar.setVisibility(View.GONE);
                    Intent sanipesIntent = new Intent(LoginNumeroComercio.this, LoginPasswordComercio.class);
                    sanipesIntent.putExtra("numero", _celular);
                    startActivity(sanipesIntent);
                    finish();
                }
            }
        }
    }
}
