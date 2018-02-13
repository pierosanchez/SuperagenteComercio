package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 03/11/2017.
 */

public class Departamento {

    private int idDepartamento;
    private String descripcionDepartamento;

    public Departamento(){

    }

    public Departamento(int idDepartamento, String descripcionDepartamento) {
        this.idDepartamento = idDepartamento;
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }
}
