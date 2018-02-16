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

public class LoginPasswordComercio extends Activity {

    private String numero;
    private EditText clave_acceso;
    private Button btn_aceptar, btn_salir;
    private String _clase;
    private int validaContra = 0;
    private ProgressBar circleProgressBar;
    private Comercio comercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_password_comercio);

        clave_acceso = (EditText) findViewById(R.id.clave_acceso);

        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        btn_salir = (Button) findViewById(R.id.btn_salir);

        circleProgressBar = (ProgressBar) findViewById(R.id.circleProgressBar);

        Bundle bundle = getIntent().getExtras();
        numero = bundle.getString("numero");

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _clase = clave_acceso.getText().toString();
                if (_clase.length() == 0) {
                    Toast.makeText(LoginPasswordComercio.this, "Ingrese su clave de acceso por favor", Toast.LENGTH_LONG).show();
                } else {
                    circleProgressBar.setVisibility(View.VISIBLE);
                    LoginPasswordComercio.ValidarLogin validaNumero = new LoginPasswordComercio.ValidarLogin();
                    validaNumero.execute();
                }
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPasswordComercio.this, LoginNumeroComercio.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }

    private class ValidarLogin extends AsyncTask<String, Void, Comercio> {
        String clave = clave_acceso.getText().toString();

        @Override
        protected Comercio doInBackground(String... params) {
            Comercio user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.validarLoginComercio(numero, clave);

            } catch (Exception e) {
                user = null;
                //fldag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(Comercio usuarioEntity) {
            if (usuarioEntity.getIdComercio() != null) {
                if (usuarioEntity.getIdComercio().equals("02")) {
                    Toast.makeText(LoginPasswordComercio.this, "El número ingresado, no es correcto", Toast.LENGTH_LONG).show();
                    clave_acceso.setText("");
                    circleProgressBar.setVisibility(View.GONE);
                } else if (usuarioEntity.getIdComercio().equals("01")) {
                    Toast.makeText(LoginPasswordComercio.this, "La clave ingresada no es correcta", Toast.LENGTH_LONG).show();
                    clave_acceso.setText("");
                    circleProgressBar.setVisibility(View.GONE);
                } else if (usuarioEntity.getIdComercio().equals("03")) {
                    clave_acceso.setText("");
                    circleProgressBar.setVisibility(View.GONE);
                    Intent sanipesIntent = new Intent(LoginPasswordComercio.this, VentanaErrores.class);
                    sanipesIntent.putExtra("comercio", usuarioEntity);
                    startActivityForResult(sanipesIntent, 0);
                    finish();
                } else if (usuarioEntity.getIdComercio().equals("00")) {
                    clave_acceso.setText("");
                    circleProgressBar.setVisibility(View.GONE);
                    Intent sanipesIntent = new Intent(LoginPasswordComercio.this, VentanaErrores.class);
                    sanipesIntent.putExtra("comercio", usuarioEntity);
                    startActivityForResult(sanipesIntent, 0);
                    finish();
                } else {
                    try {
                        circleProgressBar.setVisibility(View.GONE);
                        Intent sanipesIntent = new Intent(LoginPasswordComercio.this, MenuCliente.class);
                        sanipesIntent.putExtra("comercio", usuarioEntity);
                        sanipesIntent.putExtra("numero", numero);
                        startActivity(sanipesIntent);
                        finish();
                    } catch (Exception e) {
                        //flag_clic_ingreso = 0;
                    }

                }
            } else {
                //mensaje_error = getString(R.string.msg_error_sin_conexion);
                //sinConexion = true;

            }
        }
    }
}
