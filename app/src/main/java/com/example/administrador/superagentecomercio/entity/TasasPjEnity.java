package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 14/02/2018.
 */

public class TasasPjEnity {
    private int codPjTasa;
    private String descPjTasa;

    public TasasPjEnity(){

    }

    public TasasPjEnity(int codPjTasa, String descPjTasa) {
        this.codPjTasa = codPjTasa;
        this.descPjTasa = descPjTasa;
    }

    public int getCodPjTasa() {
        return codPjTasa;
    }

    public void setCodPjTasa(int codPjTasa) {
        this.codPjTasa = codPjTasa;
    }

    public String getDescPjTasa() {
        return descPjTasa;
    }

    public void setDescPjTasa(String descPjTasa) {
        this.descPjTasa = descPjTasa;
    }
}
