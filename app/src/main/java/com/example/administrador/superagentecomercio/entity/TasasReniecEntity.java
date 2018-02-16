package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 14/02/2018.
 */

public class TasasReniecEntity {
    private int codReniecTasa;
    private String descReniecTasa;

    public TasasReniecEntity(){

    }

    public TasasReniecEntity(int codReniecTasa, String descReniecTasa) {
        this.codReniecTasa = codReniecTasa;
        this.descReniecTasa = descReniecTasa;
    }

    public int getCodReniecTasa() {
        return codReniecTasa;
    }

    public void setCodReniecTasa(int codReniecTasa) {
        this.codReniecTasa = codReniecTasa;
    }

    public String getDescReniecTasa() {
        return descReniecTasa;
    }

    public void setDescReniecTasa(String descReniecTasa) {
        this.descReniecTasa = descReniecTasa;
    }
}
