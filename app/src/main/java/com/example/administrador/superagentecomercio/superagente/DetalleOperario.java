package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class DetalleOperario extends Activity {

    TextView tv_nombreOper_deta,tv_edad_deta,tv_dni_deta,tv_sexo_deta,tv_direccion_deta,tv_ubicacion_deta,tv_cel_deta,
            tv_fijo_deta,tv_comercio_deta;
    FloatingActionButton btn_editar,btn_eliminar,btn_regresar;
    private Comercio idcomercio;
    private String id_ope,dni_ope,nom_ope,pater_ope,mater_ope,celular,fono_fijo,comercio,
            comercioj,direccion,direcom,ubicom,nomcom;
    private String sexo,pais,departamento,distrito,provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_operario);

        btn_editar = (FloatingActionButton) findViewById(R.id.action_editar);
        btn_eliminar = (FloatingActionButton) findViewById(R.id.action_agregar);
        btn_regresar = (FloatingActionButton) findViewById(R.id.action_return);

        tv_nombreOper_deta = (TextView) findViewById(R.id.tv_nombreOper_deta);
        tv_edad_deta = (TextView) findViewById(R.id.tv_edad_deta);
        tv_dni_deta = (TextView) findViewById(R.id.tv_dni_deta);
        tv_sexo_deta = (TextView) findViewById(R.id.tv_sexo_deta);
        tv_direccion_deta = (TextView) findViewById(R.id.tv_direccion_deta);
        tv_ubicacion_deta = (TextView) findViewById(R.id.tv_ubicacion_deta);
        tv_cel_deta = (TextView) findViewById(R.id.tv_cel_deta);
        tv_fijo_deta = (TextView) findViewById(R.id.tv_fijo_deta);
        tv_comercio_deta = (TextView) findViewById(R.id.tv_comercio_deta);

        Bundle extras = getIntent().getExtras();
        //bmp = (Bitmap) extras.getParcelable("imagebitmap");
        id_ope = extras.getString("id_ope");
        dni_ope = extras.getString("dni_ope");
        nom_ope = extras.getString("nom_ope");
        pater_ope = extras.getString("pater_ope");
        mater_ope = extras.getString("mater_ope");
        fono_fijo = extras.getString("fono_fijo");
        comercio = extras.getString("comercio");
        comercioj = extras.getString("comercioj");
        sexo = extras.getString("sexo");
        departamento = extras.getString("departamento");
        distrito = extras.getString("distrito");
        provincia = extras.getString("provincia");
        direccion = extras.getString("direccion");
        celular = extras.getString("celular");
        idcomercio = extras.getParcelable("idcomercio");


        nomcom = nom_ope+" "+pater_ope+" "+mater_ope;
        tv_nombreOper_deta.setText(nomcom);
        tv_edad_deta.setText(id_ope);
        tv_dni_deta.setText(dni_ope);
        tv_sexo_deta.setText(sexo.substring(0,1));

        direcom=direccion+", "+distrito+" - "+provincia;

        tv_direccion_deta.setText(direcom);

        ubicom=departamento+", "+"PERÚ";

        tv_ubicacion_deta.setText(ubicom);

        tv_cel_deta.setText(celular);
        tv_fijo_deta.setText(fono_fijo);
        tv_comercio_deta.setText(comercioj);




        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleOperario.this, EditarOperario.class);
                intent.putExtra("idcomercio", idcomercio);
                intent.putExtra("id_ope", id_ope);
                intent.putExtra("dni_ope", dni_ope);
                //intent.putExtra("id_ope", id_ope);
                intent.putExtra("nom_ope", nom_ope);
                intent.putExtra("pater_ope", pater_ope);
                intent.putExtra("mater_ope", mater_ope);
                intent.putExtra("fono_fijo", fono_fijo);
                intent.putExtra("sexo", sexo);
                intent.putExtra("departamento", departamento);
                intent.putExtra("distrito", distrito);
                intent.putExtra("provincia", provincia);
                intent.putExtra("direccion", direccion);
                intent.putExtra("celular", celular);
                startActivity(intent);
                finish();
            }
        });

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleOperario.this, ListarOperario.class);
                intent.putExtra("comercio", idcomercio);
                startActivity(intent);
                finish();
            }
        });
    }
}
