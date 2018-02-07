package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.Operario;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class DetalleOperario extends Activity {

    TextView tv_nombreOper_deta,tv_edad_deta,tv_dni_deta,tv_sexo_deta,tv_direccion_deta,tv_ubicacion_deta,tv_cel_deta,
            tv_fijo_deta,tv_comercio_deta;
    FloatingActionButton btn_editar,btn_eliminar,btn_regresar,btn_menu;
    private Comercio idcomercio;
    private String id_ope,dni_ope,nom_ope,pater_ope,mater_ope,celular,fono_fijo,comercio,
            comercioj,direccion,direcom,ubicom,nomcom,user;
    private String sexo,pais,departamento,distrito,provincia;
    ImageView img_operario,icono_sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_operario);

        btn_editar = (FloatingActionButton) findViewById(R.id.action_editar);
        btn_menu = (FloatingActionButton)findViewById(R.id.action_menu);
        btn_eliminar = (FloatingActionButton) findViewById(R.id.action_eliminar);
        btn_regresar = (FloatingActionButton) findViewById(R.id.action_return);
        img_operario = (ImageView) findViewById(R.id.img_operario);
        icono_sexo = (ImageView) findViewById(R.id.icono_sexo);

        tv_nombreOper_deta = (TextView) findViewById(R.id.tv_nombreOper_deta);
        tv_edad_deta = (TextView) findViewById(R.id.tv_edad_deta);
        tv_dni_deta = (TextView) findViewById(R.id.tv_dni_deta);
        //tv_sexo_deta = (TextView) findViewById(R.id.tv_sexo_deta);
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
        user = extras.getString("user");


        nomcom = nom_ope+" "+pater_ope+" "+mater_ope;
        tv_nombreOper_deta.setText(nomcom);
        tv_edad_deta.setText(id_ope);
        tv_dni_deta.setText(dni_ope);
        //tv_sexo_deta.setText(sexo.substring(0,1));
        if(sexo.equals("Masculino")){
            img_operario.setImageResource(R.drawable.usuario_deta);
            icono_sexo.setImageResource(R.drawable.icon_masculino);
        }else{
            img_operario.setImageResource(R.drawable.usuaria_deta);
            icono_sexo.setImageResource(R.drawable.icon_fenenino);
        }




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

                intent.putExtra("comercio", comercio);
                intent.putExtra("comercioj", comercioj);
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
                intent.putExtra("user", user);
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

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }


    public void cancelar() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea regresar al menú?");
        alertDialog.setTitle("Cancelar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DetalleOperario.this, MenuCliente.class);
                intent.putExtra("comercio", idcomercio);
                intent.putExtra("user", user);
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

    private class eliminarOperario extends AsyncTask<String, Void, Operario> {

        @Override
        protected Operario doInBackground(String... params) {
            Operario user = null;
            try {

                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                user = dao.EliminarOperario(id_ope);

            } catch (Exception e) {
                user = null;

            }
            return user;
        }

    }


    public void eliminar() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea eliminar este operario?");
        alertDialog.setTitle("Eliminar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DetalleOperario.eliminarOperario insertar = new DetalleOperario.eliminarOperario();
                insertar.execute();

                Intent intent = new Intent(DetalleOperario.this, ListarOperario.class);
                intent.putExtra("comercio", idcomercio);
                intent.putExtra("user", user);
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


}
