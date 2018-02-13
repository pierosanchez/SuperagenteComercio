package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.NumeroUnicoAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.VoucherPagoConsumo;
import com.example.administrador.superagentecomercio.entity.NumeroUnico;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.utils.Constante;

import java.util.ArrayList;

public class IngresoTarjetaCliente extends Activity {

    private Button btn_aceptar;
    private EditText nroTarjetaDigito1, nroTarjetaDigito2, nroTarjetaDigito3, nroTarjetaDigito4,
            txt_numero_dni, txt_digito_control_dni, txt_clave_tarjeta, tv_importe;
    private RadioGroup rdgrp_clave_tarjeta;
    private NumeroUnicoAdapter numeroUnicoAdapter;
    private ArrayList<NumeroUnico> numeroUnicoArrayList;
    private String nro_unico, nomComercio, idOperario, nomOperario, apePaterOperario, apeMaterOperario, distritoComercio;
    private Comercio comercio;
    private RadioButton rdbtn_visa_option, rdbtn_mc_option;
    //rdbtn_amex_option

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_tarjeta_cliente);

        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);

        rdgrp_clave_tarjeta = (RadioGroup) findViewById(R.id.rdgrp_clave_tarjeta);

        rdbtn_visa_option = (RadioButton) findViewById(R.id.rdbtn_visa_option);
        //rdbtn_amex_option = (RadioButton) findViewById(R.id.rdbtn_amex_option);
        rdbtn_mc_option = (RadioButton) findViewById(R.id.rdbtn_mc_option);

        nroTarjetaDigito1 = (EditText) findViewById(R.id.nroTarjetaDigito1);
        nroTarjetaDigito2 = (EditText) findViewById(R.id.nroTarjetaDigito2);
        nroTarjetaDigito3 = (EditText) findViewById(R.id.nroTarjetaDigito3);
        nroTarjetaDigito4 = (EditText) findViewById(R.id.nroTarjetaDigito4);
        txt_numero_dni = (EditText) findViewById(R.id.txt_numero_dni);
        txt_digito_control_dni = (EditText) findViewById(R.id.txt_digito_control_dni);
        txt_clave_tarjeta = (EditText) findViewById(R.id.txt_clave_tarjeta);
        tv_importe = (EditText) findViewById(R.id.tv_importe);

        String callingActivity = this.getCallingActivity().getClassName();
        if (callingActivity.equals(Constante.ACTIVITYROOT + "MenuCliente")){
            Bundle bundle = getIntent().getExtras();
            comercio = bundle.getParcelable("comercio");
            nomComercio = bundle.getString("nomComercio");
            idOperario = bundle.getString("idOperario");
            nomOperario = bundle.getString("nomOperario");
            apePaterOperario = bundle.getString("apePaterOperario");
            apeMaterOperario = bundle.getString("apeMaterOperario");
            distritoComercio = bundle.getString("distritoComercio");

            btnAceptarConsumo();
        } else if (callingActivity.equals(Constante.ACTIVITYROOT + "SeleccionRecibosPagar")) {
            Bundle bundle = getIntent().getExtras();
            comercio = bundle.getParcelable("comercio");

            btnAceptarServicios();
        }

        nroTarjetaDigito1.requestFocus();

        numeroUnicoArrayList = null;
        numeroUnicoAdapter = new NumeroUnicoAdapter(numeroUnicoArrayList, getApplication());

        ejecutarLista();

        numeroTarjeta();

        rdbtn_mc_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbtn_visa_option.setChecked(false);
                //rdbtn_amex_option.setChecked(false);
            }
        });

        /*rdbtn_amex_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdbtn_visa_option.setChecked(false);
                rdbtn_mc_option.setChecked(false);
            }
        });*/

        rdbtn_visa_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rdbtn_amex_option.setChecked(false);
                rdbtn_mc_option.setChecked(false);
            }
        });
    }

    private void btnAceptarConsumo(){
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarjeta1 = nroTarjetaDigito1.getText().toString();
                String tarjeta2 = nroTarjetaDigito2.getText().toString();
                String tarjeta3 = nroTarjetaDigito3.getText().toString();
                String tarjeta4 = nroTarjetaDigito4.getText().toString();
                String numeroTarjeta = tarjeta1 + tarjeta2 + tarjeta3 + tarjeta4;
                String dni = txt_numero_dni.getText().toString();
                String digitoControDNI = txt_digito_control_dni.getText().toString();
                String clave = txt_clave_tarjeta.getText().toString();
                String importe = tv_importe.getText().toString();

                if (numeroTarjeta.length() == 16 && rdbtn_mc_option.isChecked() //|| rdbtn_amex_option.isChecked()
                        || rdbtn_visa_option.isChecked() && dni.length() != 0 && digitoControDNI.length() != 0
                        && clave.length() != 0 && importe.length() != 0) {
                    IngresoTarjetaCliente.ingresarVoucher ingresoVoucher = new IngresoTarjetaCliente.ingresarVoucher();
                    ingresoVoucher.execute();
                } else if (numeroTarjeta.length() != 16 && !rdbtn_mc_option.isChecked() //&& !rdbtn_amex_option.isChecked()
                        && !rdbtn_visa_option.isChecked() && dni.length() == 0 && digitoControDNI.length() == 0
                        && clave.length() == 0 && importe.length() == 0){
                    Toast.makeText(IngresoTarjetaCliente.this, "Ingrese los datos", Toast.LENGTH_LONG).show();
                } else {
                    if (numeroTarjeta.length() != 16) {
                        Toast.makeText(IngresoTarjetaCliente.this, "Número de tarjeta inválido", Toast.LENGTH_LONG).show();
                    }
                    if (!rdbtn_mc_option.isChecked() //&& !rdbtn_amex_option.isChecked()
                            && !rdbtn_visa_option.isChecked()){
                        Toast.makeText(IngresoTarjetaCliente.this, "Seleccione la marca de la tarjeta", Toast.LENGTH_LONG).show();
                    }
                    if (dni.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el número de DNI", Toast.LENGTH_LONG).show();
                    }
                    if (digitoControDNI.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el dígito de control del DNI", Toast.LENGTH_LONG).show();
                    }
                    if (clave.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese la clave de la tarjeta", Toast.LENGTH_LONG).show();
                    }
                    if (importe.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el importe a pagar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void btnAceptarServicios(){
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarjeta1 = nroTarjetaDigito1.getText().toString();
                String tarjeta2 = nroTarjetaDigito2.getText().toString();
                String tarjeta3 = nroTarjetaDigito3.getText().toString();
                String tarjeta4 = nroTarjetaDigito4.getText().toString();
                String numeroTarjeta = tarjeta1 + tarjeta2 + tarjeta3 + tarjeta4;
                String dni = txt_numero_dni.getText().toString();
                String digitoControDNI = txt_digito_control_dni.getText().toString();
                String clave = txt_clave_tarjeta.getText().toString();
                String importe = tv_importe.getText().toString();

                if (numeroTarjeta.length() == 16 && rdbtn_mc_option.isChecked() //|| rdbtn_amex_option.isChecked()
                        || rdbtn_visa_option.isChecked() && dni.length() != 0 && digitoControDNI.length() != 0
                        && clave.length() != 0 && importe.length() != 0) {
                    Intent intent = new Intent(IngresoTarjetaCliente.this, MontoPagoPinPagoServicios.class);
                    intent.putExtra("comercio", comercio);
                    startActivity(intent);
                    finish();
                } else if (numeroTarjeta.length() != 16 && !rdbtn_mc_option.isChecked() //&& !rdbtn_amex_option.isChecked()
                        && !rdbtn_visa_option.isChecked() && dni.length() == 0 && digitoControDNI.length() == 0
                        && clave.length() == 0 && importe.length() == 0){
                    Toast.makeText(IngresoTarjetaCliente.this, "Ingrese los datos", Toast.LENGTH_LONG).show();
                } else {
                    if (numeroTarjeta.length() != 16) {
                        Toast.makeText(IngresoTarjetaCliente.this, "Número de tarjeta inválido", Toast.LENGTH_LONG).show();
                    }
                    if (!rdbtn_mc_option.isChecked() //&& !rdbtn_amex_option.isChecked()
                            && !rdbtn_visa_option.isChecked()){
                        Toast.makeText(IngresoTarjetaCliente.this, "Seleccione la marca de la tarjeta", Toast.LENGTH_LONG).show();
                    }
                    if (dni.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el número de DNI", Toast.LENGTH_LONG).show();
                    }
                    if (digitoControDNI.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el dígito de control del DNI", Toast.LENGTH_LONG).show();
                    }
                    if (clave.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese la clave de la tarjeta", Toast.LENGTH_LONG).show();
                    }
                    if (importe.length() == 0){
                        Toast.makeText(IngresoTarjetaCliente.this, "Ingrese el importe a pagar", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void numeroTarjeta() {


        nroTarjetaDigito1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (nroTarjetaDigito1.getText().toString().length() == 4)     //size as per your requirement
                {
                    nroTarjetaDigito2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        nroTarjetaDigito2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (nroTarjetaDigito2.getText().toString().length() == 4)     //size as per your requirement
                {
                    nroTarjetaDigito3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        nroTarjetaDigito3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (nroTarjetaDigito3.getText().toString().length() == 4)     //size as per your requirement
                {
                    nroTarjetaDigito4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
    }

    public String obtenerHora() {
        String hora;

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int horaS = today.hour;
        int min = today.minute;
        int seg = today.second;
        //para probar en celulares se comenta y cuando es con emuladores se descomenta
        //horaS = horaS - 5;

        hora = horaS + ":" + min + ":" + seg;

        return hora;
    }

    public String obtenerFecha() {

        String fecha;

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int dia = today.monthDay;
        int mes = today.month;
        int año = today.year;
        mes = mes + 1;

        fecha = dia + "/" + mes + "/" + año;

        return fecha;
    }

    private void ejecutarLista() {

        try {
            IngresoTarjetaCliente.getNumeroUnico listadoBeneficiario = new IngresoTarjetaCliente.getNumeroUnico();
            listadoBeneficiario.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ingresarVoucher extends AsyncTask<String, Void, VoucherPagoConsumo> {

        String importe = tv_importe.getText().toString();
        String tarjeta1 = nroTarjetaDigito1.getText().toString();
        String tarjeta2 = nroTarjetaDigito2.getText().toString();
        String tarjeta3 = nroTarjetaDigito3.getText().toString();
        String tarjeta4 = nroTarjetaDigito4.getText().toString();
        String numeroTarjeta = tarjeta1 + tarjeta2 + tarjeta3 + tarjeta4;

        @Override
        protected VoucherPagoConsumo doInBackground(String... params) {
            VoucherPagoConsumo user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.InsertarVoucherPagoConsumoComercio(numeroUnicoArrayList.get(0).getNumeroUnico(), obtenerFecha(), obtenerHora(), importe, numeroTarjeta, comercio.getIdComercio(), nomComercio, distritoComercio, nomOperario + " " + apePaterOperario + " " + apeMaterOperario, 0);

            } catch (Exception e) {
                user = null;
                //fldag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(VoucherPagoConsumo voucherPagoConsumo) {
            if (voucherPagoConsumo.getRptaC().equals("00")) {
                Intent intent = new Intent(IngresoTarjetaCliente.this, VoucherPagoConsumoPin.class);
                intent.putExtra("comercio", comercio);
                intent.putExtra("nomComercio", nomComercio);
                intent.putExtra("idOperario", idOperario);
                intent.putExtra("nomOperario", nomOperario);
                intent.putExtra("apePaterOperario", apePaterOperario);
                intent.putExtra("apeMaterOperario", apeMaterOperario);
                intent.putExtra("distritoComercio", distritoComercio);
                intent.putExtra("nro_unico", nro_unico);
                intent.putExtra("tarjeta4", tarjeta4);
                intent.putExtra("importe", importe);
                intent.putExtra("hora", obtenerHora());
                intent.putExtra("fecha", obtenerFecha());
                startActivity(intent);
                finish();
            }
        }
    }

    private class getNumeroUnico extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                numeroUnicoArrayList = dao.getNumeroUnico();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            numeroUnicoAdapter.setNewListNumeroUnico(numeroUnicoArrayList);
            numeroUnicoAdapter.notifyDataSetChanged();
            nro_unico = numeroUnicoArrayList.get(0).getNumeroUnico();
        }
    }
}
