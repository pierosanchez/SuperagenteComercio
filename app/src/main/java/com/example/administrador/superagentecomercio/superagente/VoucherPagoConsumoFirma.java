package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Comercio;

import java.text.DecimalFormat;

public class VoucherPagoConsumoFirma extends Activity {

    private Bitmap b;
    private Button btn_fimar;
    private ImageView signImage;
    private String nro_unico, nomComercio, idOperario, tarjeta4, importe, hora, fecha,
            nomOperario, apePaterOperario, apeMaterOperario, distritoComercio, numTarjeta;
    private Comercio comercio;
    private TextView tv_fecha_pago, txt_hora_pago, txt_importe_voucher_consumo, txt_numero_tarjeta_voucher_consumo,
            txt_numero_unico_voucher_consumos, tv_nombre_comercio, tv_distrito_comercio, tv_nombre_operario;
    private Button btn_efectuar_otra_operacion;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher_pago_consumo_firma);

        tv_fecha_pago = (TextView) findViewById(R.id.tv_fecha_pago);
        txt_hora_pago = (TextView) findViewById(R.id.txt_hora_pago);
        txt_importe_voucher_consumo = (TextView) findViewById(R.id.txt_importe_voucher_consumo);
        txt_numero_tarjeta_voucher_consumo = (TextView) findViewById(R.id.txt_numero_tarjeta_voucher_consumo);
        txt_numero_unico_voucher_consumos = (TextView) findViewById(R.id.txt_numero_unico_voucher_consumos);
        tv_nombre_comercio = (TextView) findViewById(R.id.tv_nombre_comercio);
        tv_distrito_comercio = (TextView) findViewById(R.id.tv_distrito_comercio);
        tv_nombre_operario = (TextView) findViewById(R.id.tv_nombre_operario);

        btn_efectuar_otra_operacion = (Button) findViewById(R.id.btn_efectuar_otra_operacion);
        btn_fimar = (Button) findViewById(R.id.btn_firmar);

        signImage = (ImageView) findViewById(R.id.signImage);

        Bundle bundle = getIntent().getExtras();
        comercio = bundle.getParcelable("comercio");
        nomComercio = bundle.getString("nomComercio");
        idOperario = bundle.getString("idOperario");
        nomOperario = bundle.getString("nomOperario");
        apePaterOperario = bundle.getString("apePaterOperario");
        apeMaterOperario = bundle.getString("apeMaterOperario");
        distritoComercio = bundle.getString("distritoComercio");
        nro_unico = bundle.getString("nro_unico");
        tarjeta4 = bundle.getString("tarjeta4");
        importe = bundle.getString("importe");
        hora = bundle.getString("hora");
        fecha = bundle.getString("fecha");
        numTarjeta = "**** **** **** " + tarjeta4;

        tv_fecha_pago.setText(fecha);
        txt_hora_pago.setText(hora);
        txt_importe_voucher_consumo.setText(covertImporte());
        txt_numero_unico_voucher_consumos.setText(nro_unico);
        tv_distrito_comercio.setText(distritoComercio);
        tv_nombre_comercio.setText(nomComercio);
        tv_nombre_operario.setText(nomOperario + " " + apePaterOperario + " " + apeMaterOperario);
        txt_numero_tarjeta_voucher_consumo.setText(numTarjeta);
        tv_distrito_comercio.setText(distritoComercio);

        btn_efectuar_otra_operacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signImage.getDrawable() == null) {
                    Toast.makeText(VoucherPagoConsumoFirma.this, "Por favor registre su firma", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(VoucherPagoConsumoFirma.this, MenuCliente.class);
                    intent.putExtra("comercio", comercio);
                    startActivity(intent);
                    finish();
                }
            }
        });



        btn_fimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VoucherPagoConsumoFirma.this, CaptureSignature.class);
                intent.putExtra("comercio", comercio);
                startActivityForResult(intent, 0);
                /*startActivity(intent);
                finish();*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            b = BitmapFactory.decodeByteArray(
                    data.getByteArrayExtra("byteArray"), 0,
                    data.getByteArrayExtra("byteArray").length);
            signImage.setImageBitmap(b);
            btn_fimar.setVisibility(View.GONE);
        }
    }

    private String covertImporte(){
        return decimalFormat.format(Double.parseDouble(importe));
    }
}
