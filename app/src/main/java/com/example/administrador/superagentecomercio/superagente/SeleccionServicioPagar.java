package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
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
import com.example.administrador.superagentecomercio.adapter.ServiciosPublicosComercioAdapter;
import com.example.administrador.superagentecomercio.adapter.TasasAdapter;
import com.example.administrador.superagentecomercio.adapter.TasasMtcAdapter;
import com.example.administrador.superagentecomercio.adapter.TasasPjAdapter;
import com.example.administrador.superagentecomercio.adapter.TasasPnpAdapter;
import com.example.administrador.superagentecomercio.adapter.TasasReniecAdapter;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoImplement;
import com.example.administrador.superagentecomercio.dao.SuperAgenteDaoInterface;
import com.example.administrador.superagentecomercio.entity.ClubsEntity;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.EmpresasServiciosEntity;
import com.example.administrador.superagentecomercio.entity.ServiciosPublicEntity;
import com.example.administrador.superagentecomercio.entity.TasasEntity;
import com.example.administrador.superagentecomercio.entity.TasasMtcEntity;
import com.example.administrador.superagentecomercio.entity.TasasPjEnity;
import com.example.administrador.superagentecomercio.entity.TasasPnpEntity;
import com.example.administrador.superagentecomercio.entity.TasasReniecEntity;

import java.util.ArrayList;

public class SeleccionServicioPagar extends Activity {

    Spinner sp_seleccion_servicio, sp_seleccion_tipo_servicio, sp_seleccion_tipo_tasa;
    EditText txt_numero_servicio;
    String tipoServicio[] = {"Fijo", "Móvil"};
    Button btn_aceptar_cuenta_tarjeta_abono, btn_cancelar_cuenta_tarjeta_abono;
    private Comercio comercio;
    ArrayList<EmpresasServiciosEntity> empresasServiciosEntityArrayList;
    ArrayList<ServiciosPublicEntity> serviciosPublicEntityArrayList;
    ArrayList<ClubsEntity> clubsEntityArrayList;
    ArrayList<TasasEntity> tasasEntityArrayList;
    ArrayList<TasasMtcEntity> tasasMtcEntityArrayList;
    ArrayList<TasasPjEnity> tasasPjEnityArrayList;
    ArrayList<TasasReniecEntity> tasasReniecEntityArrayList;
    ArrayList<TasasPnpEntity> tasasPnpEntityArrayList;
    ServiciosPublicosComercioAdapter empresasServiciosAdapter;
    ServiciosPublicosAdapter serviciosPublicosAdapter;
    ClubesAdapter clubesAdapter;
    TasasAdapter tasasAdapter;
    TasasReniecAdapter tasasReniecAdapter;
    TasasPnpAdapter tasasPnpAdapter;
    TasasPjAdapter tasasPjAdapter;
    TasasMtcAdapter tasasMtcAdapter;
    String servicio, cliente, tipo_servicio, cli_dni, tipoTasa;
    int tipo_servicio_publico;
    TextView tv_seleccion_tipo_servicio, tv_nombre_recibo_usuario, tv_seleccion_tipo_tasa, tv_seleccione_servicio, tv_tipo_moneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_servicio_pagar);

        sp_seleccion_servicio = (Spinner) findViewById(R.id.sp_seleccion_servicio);
        sp_seleccion_tipo_servicio = (Spinner) findViewById(R.id.sp_seleccion_tipo_servicio);
        sp_seleccion_tipo_tasa = (Spinner) findViewById(R.id.sp_seleccion_tipo_tasa);

        btn_cancelar_cuenta_tarjeta_abono = (Button) findViewById(R.id.btn_cancelar_cuenta_tarjeta_abono);
        btn_aceptar_cuenta_tarjeta_abono = (Button) findViewById(R.id.btn_aceptar_cuenta_tarjeta_abono);

        txt_numero_servicio = (EditText) findViewById(R.id.txt_numero_servicio);

        tv_seleccion_tipo_servicio = (TextView) findViewById(R.id.tv_seleccion_tipo_servicio);
        tv_nombre_recibo_usuario = (TextView) findViewById(R.id.tv_nombre_recibo_usuario);
        tv_seleccion_tipo_tasa = (TextView) findViewById(R.id.tv_seleccion_tipo_tasa);
        tv_seleccione_servicio = (TextView) findViewById(R.id.tv_seleccione_servicio);
        tv_tipo_moneda = (TextView) findViewById(R.id.tv_tipo_moneda);

        Bundle bundle = getIntent().getExtras();
        comercio = bundle.getParcelable("comercio");

        txt_numero_servicio.requestFocus();

        empresasServiciosEntityArrayList = null;
        empresasServiciosAdapter = new ServiciosPublicosComercioAdapter(empresasServiciosEntityArrayList, getApplication());
        sp_seleccion_servicio.setAdapter(empresasServiciosAdapter);

        ejecutarLista();

        sp_seleccion_tipo_tasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (tipo_servicio.equals("RENIEC")){
                    tipoTasa = tasasReniecAdapter.getItem(position).getDescReniecTasa();
                } else if (tipo_servicio.equals("MTC")){
                    tipoTasa = tasasMtcAdapter.getItem(position).getDescMtcTasa();
                } else if (tipo_servicio.equals("PJ")) {
                    tipoTasa = tasasPjAdapter.getItem(position).getDescPjTasa();
                } else if (tipo_servicio.equals("PNP")) {
                    tipoTasa = tasasPnpAdapter.getItem(position).getDescPnpTasa();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_seleccion_servicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                servicio = empresasServiciosAdapter.getItem(position).getDes_emp_servicio();
                tipo_servicio_publico = empresasServiciosAdapter.getItem(position).getCod_tipo_emps_servicio();
                tv_nombre_recibo_usuario.setText(empresasServiciosAdapter.getItem(position).getNombre_recibo());

                if (tipo_servicio_publico == 3) {

                    tv_seleccione_servicio.setText("SELECCIONE EL SERVICIO");
                    txt_numero_servicio.setEnabled(true);
                    txt_numero_servicio.setText("");
                    sp_seleccion_tipo_servicio.setVisibility(View.GONE);
                    tv_seleccion_tipo_servicio.setVisibility(View.GONE);
                    tv_tipo_moneda.setVisibility(View.GONE);
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);

                    tipo_servicio = null;

                } else if (tipo_servicio_publico == 2) {

                    tv_seleccione_servicio.setText("SELECCIONE EL SERVICIO");
                    txt_numero_servicio.setEnabled(true);
                    txt_numero_servicio.setText("");
                    tv_seleccion_tipo_servicio.setText("SELECCIONE LA INSTITUCION");
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_tipo_moneda.setVisibility(View.GONE);
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);

                    serviciosPublicEntityArrayList = null;
                    serviciosPublicosAdapter = new ServiciosPublicosAdapter(serviciosPublicEntityArrayList, getApplication());
                    sp_seleccion_tipo_servicio.setAdapter(serviciosPublicosAdapter);

                    ejecutarListaServiciosPublicos();

                } else if (tipo_servicio_publico == 1) {

                    tv_seleccione_servicio.setText("SELECCIONE EL SERVICIO");
                    txt_numero_servicio.setEnabled(true);
                    txt_numero_servicio.setText("");
                    txt_numero_servicio.setInputType(InputType.TYPE_CLASS_NUMBER);
                    //txt_numero_servicio.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});

                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_tipo_moneda.setVisibility(View.GONE);
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);
                    cargarTipoServicioCombo();

                } else if (tipo_servicio_publico == 4) {

                    tv_seleccione_servicio.setText("SELECCIONE EL SERVICIO");
                    txt_numero_servicio.setEnabled(true);
                    txt_numero_servicio.setText("");
                    tv_seleccion_tipo_servicio.setText("SELECCIONE EL CLUB");
                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_tipo_moneda.setVisibility(View.GONE);
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);

                    clubsEntityArrayList = null;
                    clubesAdapter = new ClubesAdapter(clubsEntityArrayList, getApplication());
                    sp_seleccion_tipo_servicio.setAdapter(clubesAdapter);

                    ejecutarListaClubes();
                } else if (tipo_servicio_publico == 5) {
                    txt_numero_servicio.setEnabled(false);
                    txt_numero_servicio.setGravity(Gravity.RIGHT);
                    txt_numero_servicio.setTypeface(null, Typeface.BOLD);
                    tv_seleccione_servicio.setText("SELECCIONE LA TASA");
                    tv_seleccion_tipo_servicio.setText("SELECCIONE EL TIPO DE TASA");
                    tv_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_servicio.setVisibility(View.VISIBLE);
                    tv_tipo_moneda.setVisibility(View.VISIBLE);
                    tv_seleccion_tipo_tasa.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_tasa.setVisibility(View.VISIBLE);

                    tasasEntityArrayList = null;
                    tasasAdapter = new TasasAdapter(tasasEntityArrayList, getApplication());
                    sp_seleccion_tipo_servicio.setAdapter(tasasAdapter);

                    ejecutarListaTasas();
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
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);
                    txt_numero_servicio.setText("");
                    tipo_servicio = serviciosPublicosAdapter.getItem(position).getDes_inst_edu();
                } else if (tipo_servicio_publico == 4) {
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);
                    txt_numero_servicio.setText("");
                    tipo_servicio = clubesAdapter.getItem(position).getDes_club();
                } else if (tipo_servicio_publico == 1) {
                    tv_seleccion_tipo_tasa.setVisibility(View.GONE);
                    sp_seleccion_tipo_tasa.setVisibility(View.GONE);
                    txt_numero_servicio.setText("");
                    tipo_servicio = parent.getAdapter().getItem(position).toString();
                } else if (tipo_servicio_publico == 5) {
                    tv_seleccion_tipo_tasa.setVisibility(View.VISIBLE);
                    sp_seleccion_tipo_tasa.setVisibility(View.VISIBLE);
                    tv_nombre_recibo_usuario.setText("IMPORTE");
                    tipo_servicio = tasasAdapter.getItem(position).getAcrotasa();
                    if (tipo_servicio.equals("RENIEC")){
                        txt_numero_servicio.setText("17.90");
                        tasasReniecEntityArrayList = null;
                        tasasReniecAdapter = new TasasReniecAdapter(tasasReniecEntityArrayList, getApplication());
                        sp_seleccion_tipo_tasa.setAdapter(tasasReniecAdapter);

                        ejecutarTasasReniec();
                    } else if (tipo_servicio.equals("MTC")){
                        txt_numero_servicio.setText("19.90");
                        tasasMtcEntityArrayList = null;
                        tasasMtcAdapter = new TasasMtcAdapter(tasasMtcEntityArrayList, getApplication());
                        sp_seleccion_tipo_tasa.setAdapter(tasasMtcAdapter);

                        ejecutarTasasMtc();
                    } else if (tipo_servicio.equals("PJ")) {
                        txt_numero_servicio.setText("20.00");
                        tasasPjEnityArrayList = null;
                        tasasPjAdapter = new TasasPjAdapter(tasasPjEnityArrayList, getApplication());
                        sp_seleccion_tipo_tasa.setAdapter(tasasPjAdapter);

                        ejecutarTasasPj();
                    } else if (tipo_servicio.equals("PNP")) {
                        txt_numero_servicio.setText("18.90");
                        tasasPnpEntityArrayList = null;
                        tasasPnpAdapter = new TasasPnpAdapter(tasasPnpEntityArrayList, getApplication());
                        sp_seleccion_tipo_tasa.setAdapter(tasasPnpAdapter);

                        ejecutarTasasPnp();
                    }
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
                        if (tipo_servicio_publico == 5) {
                            Intent intent = new Intent(SeleccionServicioPagar.this, IngresoTarjetaCliente.class);
                            intent.putExtra("servicio", servicio);
                            intent.putExtra("num_servicio", numero_servicio);
                            intent.putExtra("comercio", comercio);
                            intent.putExtra("cliente", "Edgar Salazar Melendez");
                            intent.putExtra("tipo_servicio", tipo_servicio);
                            intent.putExtra("cli_dni", cli_dni);
                            intent.putExtra("nombre_recibo", nombre_recibo);
                            intent.putExtra("monto_servicio", txt_numero_servicio.getText().toString());
                            intent.putExtra("cliente", "Edgar Salazar Melendez");
                            intent.putExtra("tasa", 1);
                            intent.putExtra("tipoTasa", tipoTasa);
                            startActivityForResult(intent, 0);
                            finish();
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
                empresasServiciosEntityArrayList = dao.ListarEmpresasServiciosComercio();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //usuarioEntityArrayList.remove(banco = banco_tarjeta);
            empresasServiciosAdapter.setNewListServiciosPublicosComercio(empresasServiciosEntityArrayList);
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

    private void ejecutarListaTasas() {

        try {
            SeleccionServicioPagar.ListadoTasas listadoEmpresas = new SeleccionServicioPagar.ListadoTasas();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }

    }

    private class ListadoTasas extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                tasasEntityArrayList = dao.ListarTasas();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //usuarioEntityArrayList.remove(banco = banco_tarjeta);
            tasasAdapter.setNewListTasas(tasasEntityArrayList);
            tasasAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarTasasReniec() {

        try {
            SeleccionServicioPagar.ListadoTasaReniec listadoEmpresas = new SeleccionServicioPagar.ListadoTasaReniec();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ListadoTasaReniec extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                tasasReniecEntityArrayList = dao.ListarTasasReniec();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tasasReniecAdapter.setNewListTasasReniec(tasasReniecEntityArrayList);
            tasasReniecAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarTasasMtc() {

        try {
            SeleccionServicioPagar.ListadoTasaMtc listadoEmpresas = new SeleccionServicioPagar.ListadoTasaMtc();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ListadoTasaMtc extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                tasasMtcEntityArrayList = dao.ListarTasasMtc();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tasasMtcAdapter.setNewListTasasMtc(tasasMtcEntityArrayList);
            tasasMtcAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarTasasPj() {

        try {
            SeleccionServicioPagar.ListadoTasaPj listadoEmpresas = new SeleccionServicioPagar.ListadoTasaPj();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ListadoTasaPj extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                tasasPjEnityArrayList = dao.ListarTasasPj();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tasasPjAdapter.setNewListTasasPj(tasasPjEnityArrayList);
            tasasPjAdapter.notifyDataSetChanged();
        }
    }

    private void ejecutarTasasPnp() {

        try {
            SeleccionServicioPagar.ListadoTasaPnp listadoEmpresas = new SeleccionServicioPagar.ListadoTasaPnp();
            listadoEmpresas.execute();
        } catch (Exception e) {
            //listadoBeneficiario = null;
        }
    }

    private class ListadoTasaPnp extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                SuperAgenteDaoInterface dao = new SuperAgenteDaoImplement();
                tasasPnpEntityArrayList = dao.ListarTasasPnp();
            } catch (Exception e) {
                //fldag_clic_ingreso = 0;;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tasasPnpAdapter.setNewListTasasPnp(tasasPnpEntityArrayList);
            tasasPnpAdapter.notifyDataSetChanged();
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
