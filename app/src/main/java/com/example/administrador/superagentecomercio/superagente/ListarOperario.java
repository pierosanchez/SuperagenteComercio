package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.OperarioAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.Operario;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListarOperario extends Activity {

    FloatingActionButton btn_menu,btn_agregar;
    private Operario operario;
    private Comercio idcomercio;
    ListView lv_listado_operario;
    private ProgressBar circleProgressBar;

    OperarioAdapter operarioAdapter;
    ArrayList<Operario> arrayoperario;

    private String id_ope,dni_ope,nom_ope,pater_ope,mater_ope,celular,fono_fijo,comercio,
            comercioj,direccion,user;
    private String sexo,departamento,distrito,provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_operario);

        lv_listado_operario = (ListView) findViewById(R.id.lv_listado_operario);

        btn_menu = (FloatingActionButton) findViewById(R.id.action_menu);
        btn_menu = (FloatingActionButton)findViewById(R.id.action_menu);
        btn_agregar = (FloatingActionButton) findViewById(R.id.action_agregar);

        Bundle bundle = getIntent().getExtras();
        idcomercio = bundle.getParcelable("comercio");
        user = bundle.getString("user");

        arrayoperario = null;

        operarioAdapter = new OperarioAdapter(arrayoperario, getApplication());

        lv_listado_operario.setAdapter(operarioAdapter);

        ejecutarLista();

        lv_listado_operario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_ope = operarioAdapter.getItem(position).getId_ope();
                dni_ope = operarioAdapter.getItem(position).getDni_ope();
                nom_ope = operarioAdapter.getItem(position).getNom_ope();
                pater_ope = operarioAdapter.getItem(position).getPater_ope();
                mater_ope = operarioAdapter.getItem(position).getMater_ope();
                celular = operarioAdapter.getItem(position).getCelular();
                fono_fijo = operarioAdapter.getItem(position).getFono_fijo();
                comercio = operarioAdapter.getItem(position).getComercio();
                comercioj = operarioAdapter.getItem(position).getComercioj();
                direccion = operarioAdapter.getItem(position).getDireccion();
                sexo = operarioAdapter.getItem(position).getSexo();
                departamento = operarioAdapter.getItem(position).getDepartamento();
                distrito = operarioAdapter.getItem(position).getDistrito();
                provincia = operarioAdapter.getItem(position).getProvincia();


                    Intent intent = new Intent(ListarOperario.this, DetalleOperario.class);
                    intent.putExtra("id_ope", id_ope);
                    intent.putExtra("dni_ope", dni_ope);
                    intent.putExtra("nom_ope", nom_ope);
                    intent.putExtra("pater_ope", pater_ope);
                    intent.putExtra("mater_ope", mater_ope);
                    intent.putExtra("fono_fijo", fono_fijo);
                    intent.putExtra("celular", celular);
                    intent.putExtra("comercio", comercio);
                    intent.putExtra("comercioj", comercioj);
                    intent.putExtra("sexo", sexo);
                    intent.putExtra("departamento", departamento);
                    intent.putExtra("distrito", distrito);
                    intent.putExtra("provincia", provincia);
                    intent.putExtra("direccion", direccion);
                    intent.putExtra("idcomercio", idcomercio);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();


            }
        });

        /*btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarOperario.this, DetalleOperario.class);
                startActivity(intent);
                finish();
            }
        });*/

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = "";
                String nombre = "";
                String paterno = "";
                String materno = "";
                String celular = "";
                String fono = "";
                String sexo = "";

                Intent intent = new Intent(ListarOperario.this, AgregarOperario.class);
                intent.putExtra("idcomercio", idcomercio);
                intent.putExtra("dni", dni);
                intent.putExtra("nombre", nombre);
                intent.putExtra("paterno", paterno);
                intent.putExtra("materno", materno);
                intent.putExtra("celular", celular);
                intent.putExtra("fono", fono);
                intent.putExtra("sexo", sexo);
                intent.putExtra("user", user);
                //intent.putExtra("idcomercio", idcomercio);
                //intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });


    }


    private void ejecutarLista(){
        //idcomercio = operario.getComercio();

        try {
            ListarOperario.ListadoOperario listadoOperario = new ListarOperario.ListadoOperario();
            listadoOperario.execute();
        } catch (Exception e){
            //listadoBeneficiario = null;
        }

    }

    private class ListadoOperario extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                arrayoperario = dao.ListadoOperario(idcomercio.getIdComercio());
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            operarioAdapter.setNewListOperario(arrayoperario);
            /*operarioAdapter.notifyDataSetChanged();
            circleProgressBar.setVisibility(View.GONE);*/
        }
    }

    public void cancelar() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea regresar al menú?");
        alertDialog.setTitle("Cancelar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ListarOperario.this, MenuCliente.class);
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
