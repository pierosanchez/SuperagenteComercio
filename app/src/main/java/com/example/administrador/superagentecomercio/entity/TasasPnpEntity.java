package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 14/02/2018.
 */

public class TasasPnpEntity {
    private int codPnpTasa;
    private String descPnpTasa;

    public TasasPnpEntity(){

    }

    public TasasPnpEntity(int codPnpTasa, String descPnpTasa) {
        this.codPnpTasa = codPnpTasa;
        this.descPnpTasa = descPnpTasa;
    }

    public int getCodPnpTasa() {
        return codPnpTasa;
    }

    public void setCodPnpTasa(int codPnpTasa) {
        this.codPnpTasa = codPnpTasa;
    }

    public String getDescPnpTasa() {
        return descPnpTasa;
    }

    public void setDescPnpTasa(String descPnpTasa) {
        this.descPnpTasa = descPnpTasa;
    }
}
