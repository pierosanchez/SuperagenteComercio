package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 14/02/2018.
 */

public class TasasEntity {
    private int codTasa;
    private String descTasa;
    private String acrotasa;

    public TasasEntity(){

    }

    public TasasEntity(int codTasa, String descTasa, String acrotasa) {
        this.codTasa = codTasa;
        this.descTasa = descTasa;
        this.acrotasa = acrotasa;
    }

    public int getCodTasa() {
        return codTasa;
    }

    public void setCodTasa(int codTasa) {
        this.codTasa = codTasa;
    }

    public String getDescTasa() {
        return descTasa;
    }

    public void setDescTasa(String descTasa) {
        this.descTasa = descTasa;
    }

    public String getAcrotasa() {
        return acrotasa;
    }

    public void setAcrotasa(String acrotasa) {
        this.acrotasa = acrotasa;
    }
}
