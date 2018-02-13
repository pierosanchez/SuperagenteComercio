package com.example.administrador.superagentecomercio.entity;

/**
 * Created by Administrador on 19/12/2017.
 */

public class VoucherPagoConsumo {
    private String numeroUnico;
    private String fecha;
    private String hora;
    private String importe;
    private String nroTarjeta;
    private String marcaTarjeta;
    private String bancoTarjeta;
    private String nombreComercio;
    private String direccionComercio;
    private String distritoComercio;
    private String idCliente;
    private String nomCliente;
    private String rptaAnulacionVoucher;
    private int estadoVoucher;
    private String rptaC;

    public VoucherPagoConsumo(){

    }

    public VoucherPagoConsumo(String numeroUnico, String fecha, String hora, String importe, String nroTarjeta, String marcaTarjeta, String bancoTarjeta, String nombreComercio, String direccionComercio, String distritoComercio, String idCliente, String nomCliente) {
        this.numeroUnico = numeroUnico;
        this.fecha = fecha;
        this.hora = hora;
        this.importe = importe;
        this.nroTarjeta = nroTarjeta;
        this.marcaTarjeta = marcaTarjeta;
        this.bancoTarjeta = bancoTarjeta;
        this.nombreComercio = nombreComercio;
        this.direccionComercio = direccionComercio;
        this.distritoComercio = distritoComercio;
        this.idCliente = idCliente;
        this.nomCliente = nomCliente;
    }

    public String getRptaC() {
        return rptaC;
    }

    public void setRptaC(String rptaC) {
        this.rptaC = rptaC;
    }

    public int getEstadoVoucher() {
        return estadoVoucher;
    }

    public void setEstadoVoucher(int estadoVoucher) {
        this.estadoVoucher = estadoVoucher;
    }

    public String getRptaAnulacionVoucher() {
        return rptaAnulacionVoucher;
    }

    public void setRptaAnulacionVoucher(String rptaAnulacionVoucher) {
        this.rptaAnulacionVoucher = rptaAnulacionVoucher;
    }

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    public String getBancoTarjeta() {
        return bancoTarjeta;
    }

    public void setBancoTarjeta(String bancoTarjeta) {
        this.bancoTarjeta = bancoTarjeta;
    }

    public String getNombreComercio() {
        return nombreComercio;
    }

    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }

    public String getDireccionComercio() {
        return direccionComercio;
    }

    public void setDireccionComercio(String direccionComercio) {
        this.direccionComercio = direccionComercio;
    }

    public String getDistritoComercio() {
        return distritoComercio;
    }

    public void setDistritoComercio(String distritoComercio) {
        this.distritoComercio = distritoComercio;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
}
