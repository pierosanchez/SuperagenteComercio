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
    public ArrayList<Ubigeo> ListarProvinciaUbigeo(String ubigeo1) {
        ArrayList<Ubigeo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListarProvincia/?ubigeo1=" + ubigeo1;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Ubigeo usuarioEntity = new Ubigeo();
                        usuarioEntity.setUbigeo1(utils.getValueStringOrNull(jsonObject, "ubigeo1"));
                        usuarioEntity.setUbigeo2(utils.getValueStringOrNull(jsonObject, "ubigeo2"));
                        usuarioEntity.setUbigeo3(utils.getValueStringOrNull(jsonObject, "ubigeo3"));
                        usuarioEntity.setDepartamento(utils.getValueStringOrNull(jsonObject, "departamento"));
                        usuarioEntity.setDistrito(utils.getValueStringOrNull(jsonObject, "distrito"));
                        usuarioEntity.setProvincia(utils.getValueStringOrNull(jsonObject, "provincia"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public ArrayList<Ubigeo> ListarDistritoUbigeo(String ubigeo1, String ubigeo2) {
        ArrayList<Ubigeo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListarDistrito/?ubigeo1=" + ubigeo1 + "&ubigeo2=" + ubigeo2;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Ubigeo usuarioEntity = new Ubigeo();
                        usuarioEntity.setUbigeo1(utils.getValueStringOrNull(jsonObject, "ubigeo1"));
                        usuarioEntity.setUbigeo2(utils.getValueStringOrNull(jsonObject, "ubigeo2"));
                        usuarioEntity.setUbigeo3(utils.getValueStringOrNull(jsonObject, "ubigeo3"));
                        usuarioEntity.setDepartamento(utils.getValueStringOrNull(jsonObject, "departamento"));
                        usuarioEntity.setDistrito(utils.getValueStringOrNull(jsonObject, "distrito"));
                        usuarioEntity.setProvincia(utils.getValueStringOrNull(jsonObject, "provincia"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public ArrayList<Ubigeo> ListarDepartamento() {
        ArrayList<Ubigeo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListarDepartamento/?vac03=";

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Ubigeo usuarioEntity = new Ubigeo();
                        usuarioEntity.setUbigeo1(utils.getValueStringOrNull(jsonObject, "ubigeo1"));
                        usuarioEntity.setUbigeo2(utils.getValueStringOrNull(jsonObject, "ubigeo2"));
                        usuarioEntity.setUbigeo3(utils.getValueStringOrNull(jsonObject, "ubigeo3"));
                        usuarioEntity.setDepartamento(utils.getValueStringOrNull(jsonObject, "departamento"));
                        usuarioEntity.setDistrito(utils.getValueStringOrNull(jsonObject, "distrito"));
                        usuarioEntity.setProvincia(utils.getValueStringOrNull(jsonObject, "provincia"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public ArrayList<VoucherPagoConsumo> reporteMovimientos(String idComercio) {
        ArrayList<VoucherPagoConsumo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ReporteMovimientosComercio/?comRMC=" + idComercio;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        VoucherPagoConsumo usuarioEntity = new VoucherPagoConsumo();
                        usuarioEntity.setNumeroUnico(utils.getValueStringOrNull(jsonObject, "numero_unico"));
                        usuarioEntity.setFecha(utils.getValueStringOrNull(jsonObject, "fecha"));
                        usuarioEntity.setHora(utils.getValueStringOrNull(jsonObject, "hora"));
                        usuarioEntity.setImporte(utils.getValueStringOrNull(jsonObject, "importe"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public ArrayList<VoucherPagoConsumo> detalleComercio(String numUnico) {
        ArrayList<VoucherPagoConsumo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/DetalleConsumo/?numUNICO=" + numUnico;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        VoucherPagoConsumo usuarioEntity = new VoucherPagoConsumo();
                        usuarioEntity.setNumeroUnico(utils.getValueStringOrNull(jsonObject, "numero_unico"));
                        usuarioEntity.setDireccionComercio(utils.getValueStringOrNull(jsonObject, "direccion_comercio"));
                        usuarioEntity.setDistritoComercio(utils.getValueStringOrNull(jsonObject, "distrito_comercio"));
                        usuarioEntity.setFecha(utils.getValueStringOrNull(jsonObject, "fecha"));
                        usuarioEntity.setHora(utils.getValueStringOrNull(jsonObject, "hora"));
                        usuarioEntity.setImporte(utils.getValueStringOrNull(jsonObject, "importe"));
                        usuarioEntity.setMarcaTarjeta(utils.getValueStringOrNull(jsonObject, "marca_tarjeta"));
                        usuarioEntity.setNombreComercio(utils.getValueStringOrNull(jsonObject, "nombre_comercio"));
                        usuarioEntity.setNroTarjeta(utils.getValueStringOrNull(jsonObject, "nro_tarjeta"));
                        usuarioEntity.setBancoTarjeta(utils.getValueStringOrNull(jsonObject, "banco_tarjeta"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public ArrayList<VoucherPagoConsumo> ListadoAnulacionesComercio(String id_com) {
        ArrayList<VoucherPagoConsumo> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ListadoAnulacionesComercio/?comPK=" + id_com;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        VoucherPagoConsumo usuarioEntity = new VoucherPagoConsumo();
                        usuarioEntity.setNumeroUnico(utils.getValueStringOrNull(jsonObject, "numero_unico"));
                        usuarioEntity.setNomCliente(utils.getValueStringOrNull(jsonObject, "nombre"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public PasswordComercio actualizarClaveAcceso(String clave_comercio, String id_Comercio, String clave_nueva_comercio, String respuesta_pregunta_comercio) {
        PasswordComercio user;
        try {
            user = new PasswordComercio();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ActualizarClaveComercio/?clave_comercio=" + clave_comercio + "&id_Comercio=" + id_Comercio + "&clave_nueva_comercio=" + clave_nueva_comercio + "&respuesta_pregunta_comercio=" + respuesta_pregunta_comercio;

            JSONArray arrayJason = utils.getJSONArrayfromURL(url);
            Log.e("Json", arrayJason.toString());
            if (arrayJason != null) {
                if (arrayJason.length() > 0) {
                    JSONObject jsonObject = arrayJason.getJSONObject(0);
                    user.setReptaCambioCalve(utils.getValueStringOrNull(jsonObject, "rpta_cambio_clave"));
                } else {
                    user = null;
                }
            } else {
                user = null;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
            user = null;
        }

        return user;
    }

    @Override
    public PasswordComercio validarCelularComercio(String numCel) {
        PasswordComercio user;
        try {
            user = new PasswordComercio();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/ValidarCelularComercio/?numcelcom=" + numCel;

            JSONArray arrayJason = utils.getJSONArrayfromURL(url);
            Log.e("Json", arrayJason.toString());
            if (arrayJason != null) {
                if (arrayJason.length() > 0) {
                    JSONObject jsonObject = arrayJason.getJSONObject(0);
                    user.setValidCelUsu(Integer.parseInt(utils.getValueStringOrNull(jsonObject, "val")));
                    user.setIdComercio(utils.getValueStringOrNull(jsonObject, "id"));

                } else {
                    user = null;
                }
            } else {
                user = null;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
            user = null;
        }

        return user;
    }

    @Override
    public ArrayList<PasswordComercio> detalleClaveAcceso(String id_com) {
        ArrayList<PasswordComercio> listaUsuario = new ArrayList<>();

        String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/DetalleClaveComercio/?id_comer=" + id_com;

        try {
            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PasswordComercio usuarioEntity = new PasswordComercio();
                        usuarioEntity.setPregunta(utils.getValueStringOrNull(jsonObject, "pregunta_comercio"));
                        listaUsuario.add(usuarioEntity);
                    }
                } else {
                    listaUsuario = null;
                }
            } else {
                listaUsuario = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public PasswordComercio ClaveAccesoOlvidada(String id_com, String nueva_clave_comercio, String resp_preg_comercio) {
        PasswordComercio user;
        try {
            user = new PasswordComercio();

            String url = Constante.IPORHOST + "webApi_2/apigeneral/ApiGeneral/CambioClaveComercio/?id_com=" + id_com + "&nueva_clave_comercio=" + nueva_clave_comercio + "&resp_preg_comercio=" + resp_preg_comercio;

            JSONArray arrayJason = utils.getJSONArrayfromURL(url);
            Log.e("Json", arrayJason.toString());
            if (arrayJason != null) {
                if (arrayJason.length() > 0) {
                    JSONObject jsonObject = arrayJason.getJSONObject(0);
                    user.setReptaCambioCalve(utils.getValueStringOrNull(jsonObject, "rpta_cambio_clave"));
                } else {
                    user = null;
                }
            } else {
                user = null;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
            user = null;
        }

        return user;
    }

    @Override
    public Comercio validarLoginComercio(String num_cel_comercio, String pass_comercio) {
        Comercio comercio;

        try {
            comercio = new Comercio();

            String url = Constante.IPORHOST + "/webApi_2/apigeneral/ApiGeneral/validarLoginComercio/?num_cel_comercio=" + URLEncoder.encode(num_cel_comercio, "UTF-8") + "&pass_comercio=" + URLEncoder.encode(pass_comercio, "UTF-8");

            JSONArray jsonArray = utils.getJSONArrayfromURL(url);
            if (jsonArray != null) {
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    comercio.setIdComercio(utils.getValueStringOrNull(jsonObject, "logComercio"));
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
    public Comercio InsertarComercio(String rucComercio, String razSocialComercio, String direccionComercio, String representanteComercio, String dniRepresentante, String departamento, String provincia, String distrito) {
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
                    comercio.setDepartamento(departamento);
                    comercio.setProvincia(provincia);
                    comercio.setDistrito(distrito);
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
