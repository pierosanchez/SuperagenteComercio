package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.NumeroUnicoAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.NumeroUnico;
import com.example.administrador.superagentecomercio.entity.VoucherPagoServicioEntity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VoucherPagoServicio extends Activity {

    private Comercio comercio;
    private VoucherPagoServicioEntity voucherServicio;
    Button btn_salir, btn_pagar_otros_servicios, btn_efectuar_otra_operacion;
    String num_tarjeta, monto_servicio, servicio, num_servicio, tipo_moneda_deuda, comision,
            cliente, cli_dni, nombre_recibo, tipo_servicio, fechaV, horaV, nro_unico, validacion_tarjeta,
            tipoPago, tipoTasa;
    int tipo_tarjeta, emisor_tarjeta, tipo_tarjeta_pago, cod_banco;
    TextView tv_fecha_pago, txt_hora_pago, tv_comision_oper_servicio, tv_importe_servicio, tv_forma_pago, txt_suministro_pagar_voucher, txt_servicio_pagar_voucher,
            tv_total_servicio_pagar_voucher, tv_banco_tarjeta_usuario, txt_pagado_por,
            tv_tipo_moneda_importe_voucher, tv_tipo_moneda_comision_voucher, tv_tipo_moneda_total_voucher,
            tv_nombre_recibo_usuario, txt_tipo_servicio_pagar_voucher, txt_numero_unico,
            txt_numero_tarjeta_voucher_servicio, tv_tipo_tasa, tv_tipo_tasa_label;
    TableRow tr_tipo_servicio_pagar, tr_tipo_tasa;
    DecimalFormat decimal = new DecimalFormat("0.00");
    NumeroUnicoAdapter numeroUnicoAdapter;
    ArrayList<NumeroUnico> numeroUnicoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher_pago_servicio);

        Bundle extras = getIntent().getExtras();
        comercio = extras.getParcelable("comercio");
        num_tarjeta = extras.getString("num_tarjeta");
        emisor_tarjeta = extras.getInt("emisor_tarjeta");
        monto_servicio = extras.getString("monto_servicio");
        servicio = extras.getString("servicio");
        num_servicio = extras.getString("num_servicio");
        tipo_moneda_deuda = extras.getString("tipo_moneda_deuda");
        comision = extras.getString("comision");
        tipo_tarjeta_pago = extras.getInt("tipo_tarjeta_pago");
        cod_banco = extras.getInt("cod_banco");
        cliente = extras.getString("cliente");
        cli_dni = extras.getString("cli_dni");
        nombre_recibo = extras.getString("nombre_recibo");
        tipo_servicio = extras.getString("tipo_servicio");
        nro_unico = extras.getString("nro_unico");
        validacion_tarjeta = extras.getString("validacion_tarjeta");
        tipoTasa = extras.getString("tipoTasa");
        //tipoPago = tipoTarjeta() + " con " + validacion_tarjeta;
        fechaV = "FECHA: " + obtenerFecha();
        horaV = "HORA: " + obtenerHora();

        VoucherPagoServicio.getNumUnico numUnico = new VoucherPagoServicio.getNumUnico();
        numUnico.execute();

        btn_salir = (Button) findViewById(R.id.btn_salir);
        btn_pagar_otros_servicios = (Button) findViewById(R.id.btn_pagar_otros_servicios);
        btn_efectuar_otra_operacion = (Button) findViewById(R.id.btn_efectuar_otra_operacion);

        tr_tipo_servicio_pagar = (TableRow) findViewById(R.id.tr_tipo_servicio_pagar);
        tr_tipo_tasa = (TableRow) findViewById(R.id.tr_tipo_tasa);

        tv_fecha_pago = (TextView) findViewById(R.id.tv_fecha_pago);
        txt_hora_pago = (TextView) findViewById(R.id.txt_hora_pago);
        tv_comision_oper_servicio = (TextView) findViewById(R.id.tv_comision_oper_servicio);
        tv_importe_servicio = (TextView) findViewById(R.id.tv_importe_servicio);
        //tv_forma_pago = (TextView) findViewById(R.id.tv_forma_pago);
        txt_suministro_pagar_voucher = (TextView) findViewById(R.id.txt_suministro_pagar_voucher);
        txt_servicio_pagar_voucher = (TextView) findViewById(R.id.txt_servicio_pagar_voucher);
        tv_total_servicio_pagar_voucher = (TextView) findViewById(R.id.tv_total_servicio_pagar_voucher);
        //tv_banco_tarjeta_usuario = (TextView) findViewById(R.id.tv_banco_tarjeta_usuario);
        //txt_pagado_por = (TextView) findViewById(R.id.txt_pagado_por);
        tv_tipo_moneda_importe_voucher = (TextView) findViewById(R.id.tv_tipo_moneda_importe_voucher);
        tv_tipo_moneda_comision_voucher = (TextView) findViewById(R.id.tv_tipo_moneda_comision_voucher);
        tv_tipo_moneda_total_voucher = (TextView) findViewById(R.id.tv_tipo_moneda_total_voucher);
        tv_nombre_recibo_usuario = (TextView) findViewById(R.id.tv_nombre_recibo_usuario);
        txt_tipo_servicio_pagar_voucher = (TextView) findViewById(R.id.txt_tipo_servicio_pagar_voucher);
        txt_numero_unico = (TextView) findViewById(R.id.txt_numero_unico);
        txt_numero_tarjeta_voucher_servicio = (TextView) findViewById(R.id.txt_numero_tarjeta_voucher_servicio);
        tv_tipo_tasa = (TextView) findViewById(R.id.tv_tipo_tasa);
        tv_tipo_tasa_label = (TextView) findViewById(R.id.tv_tipo_tasa_label);

        if (tipo_servicio != null){
            tr_tipo_servicio_pagar.setVisibility(View.VISIBLE);
            txt_tipo_servicio_pagar_voucher.setText(tipo_servicio);
            tr_tipo_tasa.setVisibility(View.VISIBLE);
            tv_tipo_tasa.setText(tipoTasa);
            tv_tipo_tasa_label.setText("TIPO DE TASA");
        } else {
            tr_tipo_servicio_pagar.setVisibility(View.GONE);
            tr_tipo_tasa.setVisibility(View.GONE);
        }

        tv_fecha_pago.setText(fechaV);
        txt_hora_pago.setText(horaV);
        tv_comision_oper_servicio.setText(transformarComision());
        tv_importe_servicio.setText(transformarImporteServicio());
        txt_servicio_pagar_voucher.setText(servicio);
        txt_suministro_pagar_voucher.setText(num_servicio);
        tv_total_servicio_pagar_voucher.setText(totalServicioPagar());
        //tv_forma_pago.setText(tipoPago);
        //tv_banco_tarjeta_usuario.setText(obtenerBancoTarjeta());
        //txt_pagado_por.setText(cliente);
        tv_tipo_moneda_importe_voucher.setText(tipo_moneda_deuda);
        tv_tipo_moneda_comision_voucher.setText(tipo_moneda_deuda);
        tv_tipo_moneda_total_voucher.setText(tipo_moneda_deuda);
        tv_nombre_recibo_usuario.setText(nombre_recibo);
        txt_numero_tarjeta_voucher_servicio.setText(num_tarjeta);
        tv_tipo_tasa.setText(tipoTasa);

        btn_efectuar_otra_operacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VoucherPagoServicio.this, MenuCliente.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });

        btn_pagar_otros_servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VoucherPagoServicio.this, SeleccionServicioPagar.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
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

    public String totalServicioPagar() {

        double monto_p = Double.parseDouble(monto_servicio);
        double comision_p = Double.parseDouble(comision);

        double importe = monto_p + comision_p;

        return decimal.format(importe);
    }

    public String tipoTarjeta(){
        String tipo = "";

        if (tipo_tarjeta_pago == 1) {
            tipo = "Crédito";
        } else if (tipo_tarjeta_pago == 2) {
            tipo = "Débito";
        }

        return tipo;
    }

    public String transformarComision(){
        double convert = Double.parseDouble(comision);
        return decimal.format(convert);
    }

    public String transformarImporteServicio(){
        double convert = Double.parseDouble(monto_servicio);
        return decimal.format(convert);
    }

    private class getNumUnico extends AsyncTask<String, Void, VoucherPagoServicioEntity> {

        @Override
        protected VoucherPagoServicioEntity doInBackground(String... params) {
            VoucherPagoServicioEntity user;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.getNumeroUnicoServicios(nro_unico);

            } catch (Exception e) {
                user = null;
                //fldag_clic_ingreso = 0;;
            }
            return user;
        }

        @Override
        protected void onPostExecute(VoucherPagoServicioEntity voucherPagoServicioEntity){
            voucherServicio = voucherPagoServicioEntity;
            if (voucherServicio != null){
                if (voucherServicio.getNumeroUnico() != null){
                    txt_numero_unico.setText(voucherServicio.getNumeroUnico());
                } else {
                    Toast.makeText(VoucherPagoServicio.this, "no se trajo el numero unico", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(VoucherPagoServicio.this, "la entidad no tiene data", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void salir() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Esta seguro que desea salir");
        alertDialog.setTitle("Cancelar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(VoucherPagoServicio.this, LoginNumeroComercio.class);
                startActivityForResult(intent, 0);
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
