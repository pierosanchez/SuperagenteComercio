package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.DetalleClaveAccesoAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.Operario;
import com.example.administrador.superagentecomercio.entity.PasswordComercio;

public class MenuCliente extends Activity {

    private Button btn_salir, btn_cambio_clave, btn_mantenimiento, btn_anulacion, btn_reporte_movimientos,
            btn_pago_consumo, btn_pago_servicio;
    private Comercio comercio;
    //parte de la ventana emergente
    private Button btn_aceptar, btn_cancelar;
    private EditText txt_clave_operario;
    private String nomComercio, idOperario, nomOperario, apePaterOperario, apeMaterOperario, distritoComercio, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);

        btn_salir = (Button) findViewById(R.id.btn_salir);
        btn_cambio_clave = (Button) findViewById(R.id.btn_cambio_clave);
        btn_mantenimiento = (Button) findViewById(R.id.btn_mantenimiento_menu);
        btn_anulacion = (Button) findViewById(R.id.btn_anulacion);
        btn_reporte_movimientos = (Button) findViewById(R.id.btn_reporte_movimientos);
        btn_pago_consumo = (Button) findViewById(R.id.btn_pago_consumo);
        btn_pago_servicio = (Button) findViewById(R.id.btn_pago_servicio);

        Bundle extra = getIntent().getExtras();
        comercio = extra.getParcelable("comercio");
        numero = extra.getString("numero");


        btn_cambio_clave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuCliente.this, CambioClaveAcceso.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_anulacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuCliente.this, ListadoAnulacionesComercio.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCliente.this, ListarOperario.class);
                intent.putExtra("comercio", comercio);
                intent.putExtra("user", numero);
                startActivity(intent);
                finish();
            }
        });

        btn_reporte_movimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCliente.this, ReporteMovimientos.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_pago_servicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCliente.this, SeleccionServicioPagar.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        btn_pago_consumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidaClaveOperarioDialog();
            }
        });
    }

    private void ValidaClaveOperarioDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuCliente.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_ingreso_clave_operario, null);

        txt_clave_operario = (EditText) view.findViewById(R.id.txt_clave_operario);

        btn_aceptar = (Button) view.findViewById(R.id.btn_aceptar);

        txt_clave_operario.requestFocus();

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _claveOperario = txt_clave_operario.getText().toString();

                if (_claveOperario.length() != 0){
                    MenuCliente.validarClaveOperario validarClave = new MenuCliente.validarClaveOperario();
                    validarClave.execute();
                } else if (_claveOperario.length() == 0) {
                    Toast.makeText(MenuCliente.this, "Ingrese la clave del operario", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class validarClaveOperario extends AsyncTask<String, Void, Operario> {

        String clave = txt_clave_operario.getText().toString();

        @Override
        protected Operario doInBackground(String... params) {
            Operario user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.ValidarClaveOperario(clave);
            } catch (Exception e) {
                user = null;
                //flag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(Operario usuarioEntity) {
            if (usuarioEntity.getValidPassOpe().equals("00")) {
                nomComercio = usuarioEntity.getComercio();
                idOperario = usuarioEntity.getId_ope();
                nomOperario = usuarioEntity.getNom_ope();
                apePaterOperario = usuarioEntity.getPater_ope();
                apeMaterOperario = usuarioEntity.getMater_ope();
                distritoComercio = usuarioEntity.getDistrito();
                Intent intent = new Intent(MenuCliente.this, IngresoTarjetaCliente.class);
                intent.putExtra("comercio", comercio);
                intent.putExtra("nomComercio", nomComercio);
                intent.putExtra("idOperario", idOperario);
                intent.putExtra("nomOperario", nomOperario);
                intent.putExtra("apePaterOperario", apePaterOperario);
                intent.putExtra("apeMaterOperario", apeMaterOperario);
                intent.putExtra("distritoComercio", distritoComercio);
                startActivityForResult(intent, 0);
                finish();
            } else if (usuarioEntity.getValidPassOpe().equals("01")) {
                Toast.makeText(MenuCliente.this, "La clave ingresada es incorrecta", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
