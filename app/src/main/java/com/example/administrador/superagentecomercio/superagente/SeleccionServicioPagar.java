package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.adapter.ClubesAdapter;
import com.example.administrador.superagentecomercio.adapter.EmpresasServiciosAdapter;
import com.example.administrador.superagentecomercio.adapter.ServiciosPublicosAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.ClubsEntity;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.EmpresasServiciosEntity;
import com.example.administrador.superagentecomercio.entity.ServiciosPublicEntity;

import java.util.ArrayList;

public class SeleccionServicioPagar extends Activity {

    Spinner sp_seleccion_servicio, sp_seleccion_tipo_servicio;
    EditText txt_numero_servicio;
    String servicios[] = {"Sedapal", "Luz del Sur", "Edelnor", "Movistar Fijo", "Movistar Movil", "Direct TV", "Claro Fijo", "Claro Movil", "Entel Fijo", "Entel Movil"};
    String tipoServicio[] = {"Fijo", "Móvil"};
    Button btn_aceptar_cuenta_tarjeta_abono, btn_cancelar_cuenta_tarjeta_abono;
    private Comercio comercio;
    ArrayList<EmpresasServiciosEntity> empresasServiciosEntityArrayList;
    ArrayList<ServiciosPublicEntity> serviciosPublicEntityArrayList;
    ArrayList<ClubsEntity> clubsEntityArrayList;
    EmpresasServiciosAdapter empresasServiciosAdapter;
    ServiciosPublicosAdapter serviciosPublicosAdapter;
    ClubesAdapter clubesAdapter;
    String servicio, cliente, tipo_servicio, cli_dni;
    int tipo_servicio_publico;
    TextView tv_seleccion_tipo_servicio, tv_nombre_recibo_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_servicio_pagar);

        sp_seleccion_servicio = (Spinner) findViewById(R.id.sp_seleccion_servicio);
        sp_seleccion_tipo_servicio = (Spinner) findViewById(R.id.sp_seleccion_tipo_servicio);

        btn_cancelar_cuenta_tarjeta_abono = (Button) findViewById(R.id.btn_cancelar_cuenta_tarjeta_abono);
        btn_aceptar_cuenta_tarjeta_abono = (Button) findViewById(R.id.btn_aceptar_cuenta_tarjeta_abono);

        txt_numero_servicio = (EditText) findViewById(R.id.txt_numero_servicio);

        tv_seleccion_tipo_servicio = (TextView) findViewById(R.id.tv_seleccion_tipo_servicio);
        tv_nombre_recibo_usuario = (TextView) findViewById(R.id.tv_nombre_recibo_usuario);

        Bundle bundle = getIntent().getExtras();
        comercio = bundle.getParcelable("comercio");

        txt_numero_servicio.requestFocus();

        empresasServiciosEntityArrayList = null;
        empresasServiciosAdapter = new EmpresasServiciosAdapter(empresasServiciosEntityArrayList, getApplication());
        sp_seleccion_servicio.setAdapter(empresasServiciosAdapter);

        ejecutarLista();

        sp_seleccion_servicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                servicio = empresasServiciosAdapter.getItem(position).getDes_emp_servicio();
                tipo_servicio_publico = empresasServiciosAdapter.getItem(position).getCod_tipo_emps_servicio();
                tv_nombre_recibo_usuario.setText(empresasServiciosAdapter.getItem(position).getNombre_recibo());

                if (tipo_servicio_publico == 3) {

                    sp_seleccion_tipo_servicio.setVisibility(View.GONE);
                    tv_seleccion_tipo_servicio.setVisibility(View.GONE);

                } else if (tipo_servicio_publico == 2) {

                    tv_seleccion_tipo_servicio.setText("SELECCIONE LA INSTITUCION");
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);

                    serviciosPublicEntityArrayList = null;
                    serviciosPublicosAdapter = new ServiciosPublicosAdapter(serviciosPublicEntityArrayList, getApplication());
                    sp_seleccion_tipo_servicio.setAdapter(serviciosPublicosAdapter);

                    ejecutarListaServiciosPublicos();

                } else if (tipo_servicio_publico == 1) {

                    txt_numero_servicio.setInputType(InputType.TYPE_CLASS_NUMBER);
                    //txt_numero_servicio.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});

                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    cargarTipoServicioCombo();

                } else if (tipo_servicio_publico == 4) {

                    tv_seleccion_tipo_servicio.setText("SELECCIONE EL CLUB");
                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);

                    clubsEntityArrayList = null;
                    clubesAdapter = new ClubesAdapter(clubsEntityArrayList, getApplication());
                    sp_seleccion_tipo_servicio.setAdapter(clubesAdapter);

                    ejecutarListaClubes();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_seleccion_tipo_servicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (tipo_servicio_publico == 2) {
                    tipo_servicio = serviciosPublicosAdapter.getItem(position).getDes_inst_edu();
                } else if (tipo_servicio_publico == 4) {
                    tipo_servicio = clubesAdapter.getItem(position).getDes_club();
                } else if (tipo_servicio_publico == 1) {
                    tipo_servicio = parent.getAdapter().getItem(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_aceptar_cuenta_tarjeta_abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero_servicio = txt_numero_servicio.getText().toString();
                String nombre_recibo = tv_nombre_recibo_usuario.getText().toString();
                if (!numero_servicio.equals("")) {
                    if (tipo_servicio_publico == 1){
                        if (sp_seleccion_tipo_servicio.getSelectedItem().equals("Móvil")){
                            if (numero_servicio.length() != 9) {
                                Toast.makeText(SeleccionServicioPagar.this, "El número debe de tener 9 dígitos", Toast.LENGTH_LONG).show();
                            } else if (numero_servicio.length() == 9){
                                Intent intent = new Intent(SeleccionServicioPagar.this, SeleccionRecibosPagar.class);
                                intent.putExtra("servicio", servicio);
                                intent.putExtra("num_servicio", numero_servicio);
                                intent.putExtra("comercio", comercio);
                                intent.putExtra("cliente", cliente);
                                intent.putExtra("tipo_servicio", tipo_servicio);
                                intent.putExtra("cli_dni", cli_dni);
                                intent.putExtra("nombre_recibo", nombre_recibo);
                                startActivity(intent);
                                finish();
                            }
                        } else if (sp_seleccion_tipo_servicio.getSelectedItem().equals("Fijo")){
                            if (numero_servicio.length() != 9){
                                Toast.makeText(SeleccionServicioPagar.this, "El número debe de tener 9 dígitos", Toast.LENGTH_LONG).show();
                            } else if (numero_servicio.length() == 9){
                                Intent intent = new Intent(SeleccionServicioPagar.this, SeleccionRecibosPagar.class);
                                intent.putExtra("servicio", servicio);
                                intent.putExtra("num_servicio", numero_servicio);
                                intent.putExtra("comercio", comercio);
                                intent.putExtra("cliente", cliente);
                                intent.putExtra("tipo_servicio", tipo_servicio);
                                intent.putExtra("cli_dni", cli_dni);
                                intent.putExtra("nombre_recibo", nombre_recibo);
                                startActivity(intent);
                                finish();
                            }
                        }
                    } else {
                        Intent intent = new Intent(SeleccionServicioPagar.this, SeleccionRecibosPagar.class);
                        intent.putExtra("servicio", servicio);
                        intent.putExtra("num_servicio", numero_servicio);
                        intent.putExtra("comercio", comercio);
                        intent.putExtra("cliente", cliente);
                        intent.putExtra("tipo_servicio", tipo_servicio);
                        intent.putExtra("cli_dni", cli_dni);
                        intent.putExtra("nombre_recibo", nombre_recibo);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(SeleccionServicioPagar.this, "Ingrese el codigo de cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancelar_cuenta_tarjeta_abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    public void cargarTipoServicioCombo() {
        ArrayAdapter<String> adaptadorTipoServicios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipoServicio);
        sp_seleccion_tipo_servicio.setAdapter(adaptadorTipoServicios);
    }

    private void ejecutarLista() {

        try {
            SeleccionServicioPagar.ListadoEmpresas listadoEmpresas = new SeleccionServicioPagar.ListadoEmpresas();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }

    }

    private class ListadoEmpresas extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                empresasServiciosEntityArrayList = dao.listarEmpresasServicios();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //usuarioEntityArrayList.remove(banco = banco_tarjeta);
            empresasServiciosAdapter.setNewListEmpresas(empresasServiciosEntityArrayList);
            empresasServiciosAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarListaServiciosPublicos() {

        try {
            SeleccionServicioPagar.ListadoServiciosPublicos listadoEmpresas = new SeleccionServicioPagar.ListadoServiciosPublicos();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }

    }

    private class ListadoServiciosPublicos extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                serviciosPublicEntityArrayList = dao.ListarServiciosPublicos();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //usuarioEntityArrayList.remove(banco = banco_tarjeta);
            serviciosPublicosAdapter.setNewListServiciosPublicos(serviciosPublicEntityArrayList);
            serviciosPublicosAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarListaClubes() {

        try {
            SeleccionServicioPagar.ListadoClubes listadoEmpresas = new SeleccionServicioPagar.ListadoClubes();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ListadoClubes extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                clubsEntityArrayList = dao.ListadoClubs();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            clubesAdapter.setNewListClubes(clubsEntityArrayList);
            clubesAdapter.notifyDataSetChanged();
        }
    }

    public void cancelar() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("¿Está seguro que desea cancelar la transacción?");
        alertDialog.setTitle("Cancelar");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SeleccionServicioPagar.this, MenuCliente.class);
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
}
