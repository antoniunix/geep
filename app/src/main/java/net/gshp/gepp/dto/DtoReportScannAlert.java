package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoReportScannAlert {
    private long id;
    private long idReport;
    private long idSku;
    private String key;
    private long pdv;
    private long status;
    private String hash;
    private String sku;
    private boolean send;
    private long idTp;
    private long idReportLocal;
    private String color;
    private long positionStatusSpinner;
    private String promedioVtaSemanal;
    private String ventaSemanaActual;
    private String ventaSemanaAnterior;
    private String invUnidadesSemanaActual;
    private String diasInv;

    public long getId() {
        return id;
    }

    public DtoReportScannAlert setId(long id) {
        this.id = id;
        return this;
    }

    public long getIdReport() {
        return idReport;
    }

    public DtoReportScannAlert setIdReport(long idReport) {
        this.idReport = idReport;
        return this;
    }

    public long getIdSku() {
        return idSku;
    }

    public DtoReportScannAlert setIdSku(long idSku) {
        this.idSku = idSku;
        return this;
    }

    public String getKey() {
        return key;
    }

    public DtoReportScannAlert setKey(String key) {
        this.key = key;
        return this;
    }

    public long getPdv() {
        return pdv;
    }

    public DtoReportScannAlert setPdv(long pdv) {
        this.pdv = pdv;
        return this;
    }

    public long getStatus() {
        return status;
    }

    public DtoReportScannAlert setStatus(long status) {
        this.status = status;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public DtoReportScannAlert setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public DtoReportScannAlert setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public boolean isSend() {
        return send;
    }

    public DtoReportScannAlert setSend(boolean send) {
        this.send = send;
        return this;
    }

    public long getIdTp() {
        return idTp;
    }

    public DtoReportScannAlert setIdTp(long idTp) {
        this.idTp = idTp;
        return this;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportScannAlert setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public String getColor() {
        return color;
    }

    public DtoReportScannAlert setColor(String color) {
        this.color = color;
        return this;
    }

    public long getPositionStatusSpinner() {
        return positionStatusSpinner;
    }

    public DtoReportScannAlert setPositionStatusSpinner(long positionStatusSpinner) {
        this.positionStatusSpinner = positionStatusSpinner;
        return this;
    }

    public String getPromedioVtaSemanal() {
        return promedioVtaSemanal;
    }

    public DtoReportScannAlert setPromedioVtaSemanal(String promedioVtaSemanal) {
        this.promedioVtaSemanal = promedioVtaSemanal;
        return this;
    }

    public String getVentaSemanaActual() {
        return ventaSemanaActual;
    }

    public DtoReportScannAlert setVentaSemanaActual(String ventaSemanaActual) {
        this.ventaSemanaActual = ventaSemanaActual;
        return this;
    }

    public String getVentaSemanaAnterior() {
        return ventaSemanaAnterior;
    }

    public DtoReportScannAlert setVentaSemanaAnterior(String ventaSemanaAnterior) {
        this.ventaSemanaAnterior = ventaSemanaAnterior;
        return this;
    }

    public String getInvUnidadesSemanaActual() {
        return invUnidadesSemanaActual;
    }

    public DtoReportScannAlert setInvUnidadesSemanaActual(String invUnidadesSemanaActual) {
        this.invUnidadesSemanaActual = invUnidadesSemanaActual;
        return this;
    }

    public String getDiasInv() {
        return diasInv;
    }

    public DtoReportScannAlert setDiasInv(String diasInv) {
        this.diasInv = diasInv;
        return this;
    }
}
