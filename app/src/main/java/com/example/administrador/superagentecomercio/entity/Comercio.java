package com.example.administrador.superagentecomercio.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 03/11/2017.
 */

public class Comercio implements Parcelable{
    private String idComercio, ruc, razSocial, direccion, representanteLegal, dniRepresentante, departamento, distrito, provincia;
    private int departamentoComercio, provinciaComercio, distritoComercio;
    private Context contexto;
    private String rptaLogin;

    public Comercio(String idComercio, String ruc, String razSocial, String direccion, String representanteLegal, String dniRepresentante, int departamentoComercio, int provinciaComercio, int distritoComercio) {
        this.idComercio = idComercio;
        this.ruc = ruc;
        this.razSocial = razSocial;
        this.direccion = direccion;
        this.representanteLegal = representanteLegal;
        this.dniRepresentante = dniRepresentante;
        this.departamentoComercio = departamentoComercio;
        this.provinciaComercio = provinciaComercio;
        this.distritoComercio = distritoComercio;
    }

    public Comercio() {
    }

    protected Comercio(Parcel in) {
        String[] data = new String[1];
        in.readStringArray(data);
        idComercio = data[0];
    }

    public String getRptaLogin() {
        return rptaLogin;
    }

    public void setRptaLogin(String rptaLogin) {
        this.rptaLogin = rptaLogin;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(String idComercio) {
        this.idComercio = idComercio;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getDniRepresentante() {
        return dniRepresentante;
    }

    public void setDniRepresentante(String dniRepresentante) {
        this.dniRepresentante = dniRepresentante;
    }

    public int getDepartamentoComercio() {
        return departamentoComercio;
    }

    public void setDepartamentoComercio(int departamentoComercio) {
        this.departamentoComercio = departamentoComercio;
    }

    public int getProvinciaComercio() {
        return provinciaComercio;
    }

    public void setProvinciaComercio(int provinciaComercio) {
        this.provinciaComercio = provinciaComercio;
    }

    public int getDistritoComercio() {
        return distritoComercio;
    }

    public void setDistritoComercio(int distritoComercio) {
        this.distritoComercio = distritoComercio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.idComercio});
    }

    public static final Parcelable.Creator<Comercio> CREATOR = new Creator<Comercio>() {
        @Override
        public Comercio createFromParcel(Parcel source) {
            return new Comercio(source);
        }

        @Override
        public Comercio[] newArray(int size) {
            return new Comercio[size];
        }
    };
}
