package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.utils.Constante;

public class VentanaErrores extends Activity {

    private Button btn_opcion1, btn_opcion2, btn_opcion3;
    private Comercio comercio;
    private TextView tv_titulo, tv_mensaje;
    private LinearLayout ll_boton_opcion3;
    private String numCliente, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_errores);

        btn_opcion1 = (Button) findViewById(R.id.btn_opcion1);
        btn_opcion2 = (Button) findViewById(R.id.btn_opcion2);
        btn_opcion3 = (Button) findViewById(R.id.btn_opcion3);

        tv_titulo = (TextView) findViewById(R.id.tv_titulo);
        tv_mensaje = (TextView) findViewById(R.id.tv_mensaje);

        ll_boton_opcion3 = (LinearLayout) findViewById(R.id.ll_boton_opcion3);

        String callingActivity = this.getCallingActivity().getClassName();

        if (callingActivity.equals(Constante.ACTIVITYROOT + "LoginNumeroComercio")){
            Bundle bundle = getIntent().getExtras();
            numero = bundle.getString("numero");

            btn_opcion1.setText("Reintentar");
            btn_opcion2.setText("Registrarse");

            btn_opcion1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sanipesIntent = new Intent(VentanaErrores.this, LoginNumeroComercio.class);
                    startActivityForResult(sanipesIntent, 0);
                    finish();
                }
            });

            btn_opcion2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sanipesIntent = new Intent(VentanaErrores.this, TerminosCondiciones.class);
                    sanipesIntent.putExtra("numero", numero);
                    startActivity(sanipesIntent);
                    finish();
                }
            });
        }
    }
}
