package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 14/02/2018.
 */

public class TasasMtcEntity {
    private int codMtcTasa;
    private String descMtcTasa;

    public TasasMtcEntity(){

    }

    public TasasMtcEntity(int codMtcTasa, String descMtcTasa) {
        this.codMtcTasa = codMtcTasa;
        this.descMtcTasa = descMtcTasa;
    }

    public int getCodMtcTasa() {
        return codMtcTasa;
    }

    public void setCodMtcTasa(int codMtcTasa) {
        this.codMtcTasa = codMtcTasa;
    }

    public String getDescMtcTasa() {
        return descMtcTasa;
    }

    public void setDescMtcTasa(String descMtcTasa) {
        this.descMtcTasa = descMtcTasa;
    }
}
