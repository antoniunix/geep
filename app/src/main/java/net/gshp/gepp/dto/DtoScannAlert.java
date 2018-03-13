package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoScannAlert {
    private int id;
    private int idSku;
    private int idPdv;
    private int idTp;
    private String key;
    private String sku;
    private String skuDescription;
    private String promedioVtaSemanal;
    private String ventaSemanaActual;
    private String ventaSemanaAnterior;
    private String invUnidadesSemanaActual;
    private String diasInv;
    private String increment;

    public String getIncrement() {
        return increment;
    }

    public DtoScannAlert setIncrement(String increment) {
        this.increment = increment;
        return this;
    }

    public String getPromedioVtaSemanal() {
        return promedioVtaSemanal;
    }

    public void setPromedioVtaSemanal(String promedioVtaSemanal) {
        this.promedioVtaSemanal = promedioVtaSemanal;
    }

    public String getVentaSemanaActual() {
        return ventaSemanaActual;
    }

    public void setVentaSemanaActual(String ventaSemanaActual) {
        this.ventaSemanaActual = ventaSemanaActual;
    }

    public String getVentaSemanaAnterior() {
        return ventaSemanaAnterior;
    }

    public void setVentaSemanaAnterior(String ventaSemanaAnterior) {
        this.ventaSemanaAnterior = ventaSemanaAnterior;
    }

    public String getInvUnidadesSemanaActual() {
        return invUnidadesSemanaActual;
    }

    public void setInvUnidadesSemanaActual(String invUnidadesSemanaActual) {
        this.invUnidadesSemanaActual = invUnidadesSemanaActual;
    }

    public String getDiasInv() {
        return diasInv;
    }

    public void setDiasInv(String diasInv) {
        this.diasInv = diasInv;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getIdPdv() {
        return idPdv;
    }

    public void setIdPdv(int idPdv) {
        this.idPdv = idPdv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSku() {
        return idSku;
    }

    public void setIdSku(int idSku) {
        this.idSku = idSku;
    }

    public int getIdTp() {
        return idTp;
    }

    public void setIdTp(int idTp) {
        this.idTp = idTp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
