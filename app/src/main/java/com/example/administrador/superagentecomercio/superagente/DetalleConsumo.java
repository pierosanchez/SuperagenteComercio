package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.AnulacionComercioAdapter;
import com.example.administrador.superagentecomercio.adapter.DetalleComercioAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.Cuentas;
import com.example.administrador.superagentecomercio.entity.VoucherPagoConsumo;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetalleConsumo extends Activity {

    String numUnico;
    TextView tv_fecha_pago, txt_hora_pago, txt_numero_unico_voucher_consumos,
            txt_importe_voucher_consumo, tv_tipo_tarjeta_voucher_consumo, txt_numero_tarjeta_voucher_consumo,
            tv_banco_voucher_consumo, tv_nombre_comercio, tv_direccion_comercio, tv_distrito_comercio;
    Button btn_anular, btn_regresar;
    ArrayList<VoucherPagoConsumo> voucherPagoConsumoArrayList;
    DetalleComercioAdapter detalleComercioAdapter;
    private Comercio comercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_consumo);

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

        btn_anular = (Button) findViewById(R.id.btn_anular);
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
                Intent intent = new Intent(DetalleConsumo.this, ListadoAnulacionesComercio.class);
                intent.putExtra("comercio", comercio);
                startActivity(intent);
                finish();
            }
        });

        btn_anular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensaje();
            }
        });
    }

    private void ejecutarLista() {

        try {
            DetalleConsumo.getDetalleConsumo listadoBeneficiario = new DetalleConsumo.getDetalleConsumo();
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
        }
    }

    public void Mensaje() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Esta seguro que desea anular esta operacion?");
        alertDialog.setTitle("Anulación");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(DetalleConsumo.this, "Operacion eliminada satisfactoriamente", Toast.LENGTH_LONG).show();

                DetalleConsumo.anularVoucherComercio anularVoucher = new DetalleConsumo.anularVoucherComercio();
                anularVoucher.execute();

                Intent intent = new Intent(DetalleConsumo.this, MenuCliente.class);
                intent.putExtra("comercio", comercio);
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

    private class anularVoucherComercio extends AsyncTask<String, Void, VoucherPagoConsumo> {

        @Override
        protected VoucherPagoConsumo doInBackground(String... params) {
            VoucherPagoConsumo password;
            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                password = dao.AnulacionVoucherConsumo(numUnico);
            } catch (Exception e) {
                password = null;
                //fldag_clic_ingreso = 0;;
            }
            return password;
        }

        @Override
        protected void onPostExecute(VoucherPagoConsumo voucherPagoConsumo) {
            if (voucherPagoConsumo != null) {
                if (voucherPagoConsumo.getRptaAnulacionVoucher().equals("00")) {
                    Toast.makeText(DetalleConsumo.this, "Se anuló la operacion exitosamente", Toast.LENGTH_LONG).show();
                } else if (voucherPagoConsumo.getRptaAnulacionVoucher().equals("01")) {
                    Toast.makeText(DetalleConsumo.this, "Hubo un error al anular la operacion", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
