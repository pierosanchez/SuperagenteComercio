package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.DetalleComercioAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.VoucherPagoConsumo;

import java.util.ArrayList;

public class VoucherConsumos extends Activity {

    String numUnico;
    TextView tv_fecha_pago, txt_hora_pago, txt_numero_unico_voucher_consumos,
            txt_importe_voucher_consumo, tv_tipo_tarjeta_voucher_consumo, txt_numero_tarjeta_voucher_consumo,
            tv_banco_voucher_consumo, tv_nombre_comercio, tv_direccion_comercio, tv_distrito_comercio,  tv_estado_voucher;
    Button btn_regresar;
    ArrayList<VoucherPagoConsumo> voucherPagoConsumoArrayList;
    DetalleComercioAdapter detalleComercioAdapter;
    private Comercio comercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher_consumos);

        tv_fecha_pago = (TextView) findViewById(R.id.tv_fecha_pago);
        txt_hora_pago = (TextView) findViewById(R.id.txt_hora_pago);
        txt_numero_unico_voucher_consumos = (TextView) findViewById(R.id.txt_numero_unico_voucher_consumos);
        txt_importe_voucher_consumo = (TextView) findViewById(R.id.txt_importe_voucher_consumo);
        tv_tipo_tarjeta_voucher_consumo = (TextView) findViewById(R.id.tv_tipo_tarjeta_voucher_consumo);
        txt_numero_tarjeta_voucher_consumo = (TextView) findViewById(R.id.txt_numero_tarjeta_voucher_consumo);
        tv_banco_voucher_consumo = (TextView) findViewById(R.id.tv_banco_voucher_consumo);
        tv_nombre_comercio = (TextView) findViewById(R.id.tv_nombre_comercio);
        tv_direccion_comercio = (TextView) findViewById(R.id.tv_direccion_comercio);
        tv_distrito_comercio = (TextView) findViewById(R.id.tv_distrito_comercio);
        tv_estado_voucher = (TextView) findViewById(R.id.tv_estado_voucher);

        btn_regresar = (Button) findViewById(R.id.btn_regresar);

        Bundle bundle = getIntent().getExtras();
        numUnico = bundle.getString("numUnico");
        comercio = bundle.getParcelable("comercio");

        voucherPagoConsumoArrayList = null;
        detalleComercioAdapter = new DetalleComercioAdapter(voucherPagoConsumoArrayList, getApplication());

        ejecutarLista();

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoucherConsumos.this, ReporteMovimientos.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });
    }

    private void ejecutarLista() {

        try {
            VoucherConsumos.getDetalleConsumo listadoBeneficiario = new VoucherConsumos.getDetalleConsumo();
            listadoBeneficiario.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }

    }

    private class getDetalleConsumo extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                voucherPagoConsumoArrayList = dao.detalleComercio(numUnico);
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detalleComercioAdapter.setNewListAnulaciones(voucherPagoConsumoArrayList);
            detalleComercioAdapter.notifyDataSetChanged();
            tv_fecha_pago.setText(voucherPagoConsumoArrayList.get(0).getFecha());
            txt_hora_pago.setText(voucherPagoConsumoArrayList.get(0).getHora());
            txt_numero_unico_voucher_consumos.setText(voucherPagoConsumoArrayList.get(0).getNumeroUnico());
            txt_importe_voucher_consumo.setText(voucherPagoConsumoArrayList.get(0).getImporte());
            tv_tipo_tarjeta_voucher_consumo.setText(voucherPagoConsumoArrayList.get(0).getMarcaTarjeta());
            txt_numero_tarjeta_voucher_consumo.setText(voucherPagoConsumoArrayList.get(0).getNroTarjeta());
            tv_banco_voucher_consumo.setText(voucherPagoConsumoArrayList.get(0).getBancoTarjeta());
            tv_nombre_comercio.setText(voucherPagoConsumoArrayList.get(0).getNombreComercio());
            tv_direccion_comercio.setText(voucherPagoConsumoArrayList.get(0).getDireccionComercio());
            tv_distrito_comercio.setText(voucherPagoConsumoArrayList.get(0).getDistritoComercio());
            if (voucherPagoConsumoArrayList.get(0).getEstadoVoucher() == 0) {
                tv_estado_voucher.setText("Vigente");
            } else if (voucherPagoConsumoArrayList.get(0).getEstadoVoucher() == 1) {
                tv_estado_voucher.setText("Anulado");
            }
        }
    }
}
