package com.example.administrador.superagentecomercio.dao;

/**
 * Created by CTORRES on 05/05/2017.
 */

import android.util.Log;

import com.example.administrador.superagentecomercio.entity.*;
import com.example.administrador.superagentecomercio.utils.Constante;
import com.example.administrador.superagentecomercio.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Jove on 22/03/2017.
 */
public class SuperAgenteDaoImplement implements SuperAgenteDaoInterface {

    private Utils utils;

    public SuperAgenteDaoImplement() {
        utils = new Utils();
    }


    @Override
    public ArrayList<Banco> ListadoBancos() {

        ArrayList<Banco> listaBancos = new ArrayList<>();

        String url = Constante.IPORHOST + "/webApi_2/apigeneral/ApiGeneral/ListarBancos/?empt_y=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Banco bancosEntity = new Banco();
                        bancosEntity.setCod_banco(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "cod_banco")));
                        bancosEntity.setDesc_banco(utils.getValueStringOrNull(jsonObject, "desc_banco"));
                        bancosEntity.setAcro_banco(utils.getValueStringOrNull(jsonObject, "acro_banco"));
                        bancosEntity.setDesc_breve_banco(utils.getValueStringOrNull(jsonObject, "desc_breve_banco"));
                        listaBancos.add(bancosEntity);
                    }
                } else {
                    listaBancos = null;
                }
            } else {
                listaBancos = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaBancos;
    }

    @Override
    public ArrayList<Moneda> ListarMoneda() {

        ArrayList<Moneda> listaMoneda = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListarMoneda/?blank=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Moneda monedaEntity = new Moneda();
                        monedaEntity.setCod_tipo_moneda(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "cod_tipo_moneda")));
                        monedaEntity.setSigno_moneda(utils.getValueStringOrNull(jsonObject, "signo_moneda"));
                        monedaEntity.setTipo_moneda(utils.getValueStringOrNull(jsonObject, "tipo_moneda"));
                        listaMoneda.add(monedaEntity);
                    }
                } else {
                    listaMoneda = null;
                }
            } else {
                listaMoneda = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMoneda;
    }

    @Override
    public ArrayList<Pregunta> ListarPreguntas() {

        ArrayList<Pregunta> listaMoneda = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListadoPregunta/?aa=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Pregunta pregunta = new Pregunta();
                        pregunta.setIdPregunta(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "id_pregunta")));
                        pregunta.setDescripcionPregunta(utils.getValueStringOrNull(jsonObject, "desc_pregunta"));
                        listaMoneda.add(pregunta);
                    }
                } else {
                    listaMoneda = null;
                }
            } else {
                listaMoneda = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMoneda;
    }

    @Override
    public ArrayList<Distrito> ListarDistrito() {

        ArrayList<Distrito> listaMoneda = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListadoDistrito/?nat=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Distrito distrito = new Distrito();
                        distrito.setIdDistrito(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "id_distrito")));
                        distrito.setDescripcionDistrito(utils.getValueStringOrNull(jsonObject, "desc_distrito"));
                        listaMoneda.add(distrito);
                    }
                } else {
                    listaMoneda = null;
                }
            } else {
                listaMoneda = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMoneda;
    }

    @Override
    public ArrayList<Departamento> ListarDepartamentos() {

        ArrayList<Departamento> listaMoneda = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListadoDepartamentos/?h=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Departamento departamento = new Departamento();
                        departamento.setIdDepartamento(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "id_departamento")));
                        departamento.setDescripcionDepartamento(utils.getValueStringOrNull(jsonObject, "desc_departamento"));
                        listaMoneda.add(departamento);
                    }
                } else {
                    listaMoneda = null;
                }
            } else {
                listaMoneda = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMoneda;
    }

    @Override
    public ArrayList<Provincia> ListarProvincias() {

        ArrayList<Provincia> listaMoneda = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListadoProvincias/?o=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Provincia provincia = new Provincia();
                        provincia.setIdProvincia(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "id_provincia")));
                        provincia.setDescripcionProvincia(utils.getValueStringOrNull(jsonObject, "desc_provincia"));
                        listaMoneda.add(provincia);
                    }
                } else {
                    listaMoneda = null;
                }
            } else {
                listaMoneda = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMoneda;
    }

    @Override
    public Comercio InsertarComercio(String rucComercio, String razSocialComercio, String direccionComercio, String representanteComercio, String dniRepresentante, int departamento, int provincia, int distrito) {
        Comercio comercio;

        try {
            comercio = new Comercio();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/IngresoComercio/?rucComercio=" + URLEncoder.encode(rucComercio, "UTF-8")
                    + "&razSocialComercio=" + URLEncoder.encode(razSocialComercio, "UTF-8")
                    + "&direccionComercio=" + URLEncoder.encode(direccionComercio, "UTF-8")
                    + "&representanteComercio=" + URLEncoder.encode(representanteComercio, "UTF-8")
                    + "&dniRepresentante=" + URLEncoder.encode(dniRepresentante, "UTF-8")
                    + "&departamento=" + departamento
                    + "&provincia=" + provincia
                    + "&distrito=" + distrito;

            Log.e("METHOD", "InsertarComercio");
            Log.e("URL", url);

            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    comercio.setRuc(rucComercio);
                    comercio.setRazSocial(razSocialComercio);
                    comercio.setDireccion(direccionComercio);
                    comercio.setRepresentanteLegal(representanteComercio);
                    comercio.setDniRepresentante(dniRepresentante);
                    comercio.setDepartamentoComercio(departamento);
                    comercio.setProvinciaComercio(provincia);
                    comercio.setDistritoComercio(distrito);
                    comercio.setIdComercio(utils.getValueStringOrNull(jsonObject, "codComercio"));
                } else {
                    comercio = null;
                }
            } else {
                comercio = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            comercio = null;
        }

        return comercio;
    }

    @Override
    public Cuentas IngresarCuentasComercio(int tipoCuentaComercio, int banco, int moneda, String numCuentaComercio, String idComercio, String cciComercio) {
        Cuentas cuentas;

        try {
            cuentas = new Cuentas();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/IngresoCuentasComercio/?tipoCuentaComercio=" + tipoCuentaComercio
                    + "&banco=" + banco
                    + "&moneda=" + moneda
                    + "&numCuentaComercio=" + URLEncoder.encode(numCuentaComercio, "UTF-8")
                    + "&idComercio=" + URLEncoder.encode(idComercio, "UTF-8")
                    + "&cciComercio=" + URLEncoder.encode(cciComercio, "UTF-8");

            Log.e("METHOD", "IngresarCuentasComercio");
            Log.e("URL", url);

            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    cuentas.setTipoCuenta(tipoCuentaComercio);
                    cuentas.setBanco(banco);
                    cuentas.setMoneda(moneda);
                    cuentas.setNumCuenta(numCuentaComercio);
                    cuentas.setIdComercio(idComercio);
                    cuentas.setCci(cciComercio);
                } else {
                    cuentas = null;
                }
            } else {
                cuentas = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            cuentas = null;
        }

        return cuentas;
    }

    @Override
    public PasswordComercio IngresarPasswordComercio(String idComercio, String claveAcceso, String pregunta, String respuesta, String correoComercio, String celularComercio) {
        PasswordComercio passwordComercio;

        try {
            passwordComercio = new PasswordComercio();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/IngresePasswordComercio/?idComercio=" + URLEncoder.encode(idComercio, "UTF-8")
                    + "&claveAcceso=" + URLEncoder.encode(claveAcceso, "UTF-8")
                    + "&pregunta=" + URLEncoder.encode(pregunta, "UTF-8")
                    + "&respuesta=" + URLEncoder.encode(respuesta, "UTF-8")
                    + "&correoComercio=" + URLEncoder.encode(correoComercio, "UTF-8")
                    + "&celularComercio=" + URLEncoder.encode(celularComercio, "UTF-8");

            Log.e("METHOD", "IngresarPasswordComercio");
            Log.e("URL", url);

            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    //JSONObject jsonObject = jsonArray.getJSONObject(0);
                    passwordComercio.setIdComercio(idComercio);
                    passwordComercio.setClaveAcceso(claveAcceso);
                    passwordComercio.setPregunta(pregunta);
                    passwordComercio.setRespuesta(respuesta);
                    passwordComercio.setCorreo(correoComercio);
                    passwordComercio.setCelular(celularComercio);
                } else {
                    passwordComercio = null;
                }
            } else {
                passwordComercio = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            passwordComercio = null;
        }

        return passwordComercio;
    }

}
