package net.gshp.gepp.dto;

import java.io.Serializable;

/**
 * Created by leo on 10/03/18.
 */

public class DtoReportCheck implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private long idReportLocal;
    private Long date;
    private String tz;
    private double latitude;
    private double longitude;
    private String accuracy;
    private String imei;
    private String satelliteUtc;
    private int typeCheck;
    private int send;
    private String provider;
    private String dateInactive;
    private int active;
    private String hash;

    public int getId() {
        return id;
    }

    public DtoReportCheck setId(int id) {
        this.id = id;
        return this;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportCheck setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTz() {
        return tz;
    }

    public DtoReportCheck setTz(String tz) {
        this.tz = tz;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public DtoReportCheck setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public DtoReportCheck setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public DtoReportCheck setAccuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public DtoReportCheck setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getSatelliteUtc() {
        return satelliteUtc;
    }

    public DtoReportCheck setSatelliteUtc(String satelliteUtc) {
        this.satelliteUtc = satelliteUtc;
        return this;
    }

    public int getTypeCheck() {
        return typeCheck;
    }

    public DtoReportCheck setTypeCheck(int typeCheck) {
        this.typeCheck = typeCheck;
        return this;
    }

    public int getSend() {
        return send;
    }

    public DtoReportCheck setSend(int send) {
        this.send = send;
        return this;
    }

    public String getProvider() {
        return provider;
    }

    public DtoReportCheck setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public String getDateInactive() {
        return dateInactive;
    }

    public DtoReportCheck setDateInactive(String dateInactive) {
        this.dateInactive = dateInactive;
        return this;
    }

    public int getActive() {
        return active;
    }

    public DtoReportCheck setActive(int active) {
        this.active = active;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public DtoReportCheck setHash(String hash) {
        this.hash = hash;
        return this;
    }
}
