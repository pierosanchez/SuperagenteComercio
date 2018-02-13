package com.example.administrador.superagentecomercio.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abarrera on 05/01/2018.
 */

public class Operario implements Parcelable {

    private String id_ope,dni_ope,nom_ope,pater_ope,mater_ope,celular,fono_fijo;
    private String sexo;
    private String comercio;
    private String comercioj;
    private String departamento;
    private String distrito;
    private String provincia;
    private String direccion;
    private String usu_reg;
    private String usu_upd;
    private String validPassOpe;

    //*************************************************************************************

    public Operario() {
    }

    //*************************************************************************************

    public Operario(String id_ope, String dni_ope, String nom_ope, String pater_ope, String mater_ope, String celular, String fono_fijo, String sexo, String comercio, String comercioj, String departamento, String distrito, String provincia, String direccion, String usu_reg, String usu_upd) {
        this.id_ope = id_ope;
        this.dni_ope = dni_ope;
        this.nom_ope = nom_ope;
        this.pater_ope = pater_ope;
        this.mater_ope = mater_ope;
        this.celular = celular;
        this.fono_fijo = fono_fijo;
        this.sexo = sexo;
        this.comercio = comercio;
        this.comercioj = comercioj;
        this.departamento = departamento;
        this.distrito = distrito;
        this.provincia = provincia;
        this.direccion = direccion;
        this.usu_reg = usu_reg;
        this.usu_upd = usu_upd;
    }


    //*************************************************************************************

    protected Operario(Parcel in) {
        String[] data = new String[1];
        in.readStringArray(data);
        id_ope = data[0];
    }

    //*************************************************************************************


    public String getValidPassOpe() {
        return validPassOpe;
    }

    public void setValidPassOpe(String validPassOpe) {
        this.validPassOpe = validPassOpe;
    }

    public String getId_ope() {
        return id_ope;
    }

    public void setId_ope(String id_ope) {
        this.id_ope = id_ope;
    }

    public String getDni_ope() {
        return dni_ope;
    }

    public void setDni_ope(String dni_ope) {
        this.dni_ope = dni_ope;
    }

    public String getNom_ope() {
        return nom_ope;
    }

    public void setNom_ope(String nom_ope) {
        this.nom_ope = nom_ope;
    }

    public String getPater_ope() {
        return pater_ope;
    }

    public void setPater_ope(String pater_ope) {
        this.pater_ope = pater_ope;
    }

    public String getMater_ope() {
        return mater_ope;
    }

    public void setMater_ope(String mater_ope) {
        this.mater_ope = mater_ope;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFono_fijo() {
        return fono_fijo;
    }

    public void setFono_fijo(String fono_fijo) {
        this.fono_fijo = fono_fijo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getComercioj() {
        return comercioj;
    }

    public void setComercioj(String comercioj) {
        this.comercioj = comercioj;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsu_reg() {
        return usu_reg;
    }

    public void setUsu_reg(String usu_reg) {
        this.usu_reg = usu_reg;
    }

    public String getUsu_upd() {
        return usu_upd;
    }

    public void setUsu_upd(String usu_upd) {
        this.usu_upd = usu_upd;
    }


    //*************************************************************************************

    public static final Creator<Operario> CREATOR = new Creator<Operario>() {
        @Override
        public Operario createFromParcel(Parcel source) {
            return new Operario(source);
        }

        @Override
        public Operario[] newArray(int size) {
            return new Operario[size];
        }
    };

    //*************************************************************************************

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id_ope});
    }
}
