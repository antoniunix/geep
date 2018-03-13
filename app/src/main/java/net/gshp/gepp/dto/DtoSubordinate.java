package net.gshp.gepp.dto;

/**
 * Created by leo on 9/03/18.
 */

public class DtoSubordinate  {
    private long idusuario;
    private String apellidoMat;
    private String apellidoPat;
    private String nombre;
    private String puesto;
    private int reportesAsignados;
    private int reportesRealizados;
    private double lat;
    private double lon;
    private float promedio;

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getReportesAsignados() {
        return reportesAsignados;
    }

    public void setReportesAsignados(int reportesAsignados) {
        this.reportesAsignados = reportesAsignados;
    }

    public int getReportesRealizados() {
        return reportesRealizados;
    }

    public void setReportesRealizados(int reportesRealizados) {
        this.reportesRealizados = reportesRealizados;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
}