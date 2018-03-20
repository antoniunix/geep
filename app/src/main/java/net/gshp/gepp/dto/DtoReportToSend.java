package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 11/03/18.
 */

public class DtoReportToSend {

    private long id;
    @SerializedName("scheduleId")
    private long idSchedule;
    private String version;
    private int idTipo;
    @SerializedName("pdvId")
    private long place;
    private String hash;
    @SerializedName("dateStart")
    private long checkIn;
    @SerializedName("tzStart")
    private String checkInTz;
    @SerializedName("latStart")
    private Double checkInLat;
    @SerializedName("lngStart")
    private Double checkInLon;
    @SerializedName("imei")
    private String checkInImei;
    @SerializedName("accuracyStart")
    private String checkInAccuracy;
    @SerializedName("gpsUtcStart")
    private Long checkInSateliteUTC;
    @SerializedName("dateEnd")
    private long checkOut;
    @SerializedName("tzEnd")
    private String checkOutTz;
    @SerializedName("latEnd")
    private Double checkOutLat;
    @SerializedName("lngEnd")
    private Double checkOutLon;
    private String checkOutImei;
    @SerializedName("accuracyEnd")
    private String checkOutAccuracy;
    @SerializedName("gpsUtcEnd")
    private Long checkOutSateliteUTC;
    private long idUser;

    private String pdv;
    private Double pdvLat;
    private Double pdvLon;
    private String client;

    public String getPdv() {
        return pdv;
    }

    public DtoReportToSend setPdv(String pdv) {
        this.pdv = pdv;
        return this;
    }

    public Double getPdvLat() {
        return pdvLat;
    }

    public DtoReportToSend setPdvLat(Double pdvLat) {
        this.pdvLat = pdvLat;
        return this;
    }

    public Double getPdvLon() {
        return pdvLon;
    }

    public DtoReportToSend setPdvLon(Double pdvLon) {
        this.pdvLon = pdvLon;
        return this;
    }

    public String getClient() {
        return client;
    }

    public DtoReportToSend setClient(String client) {
        this.client = client;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(long checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckInTz() {
        return checkInTz;
    }

    public void setCheckInTz(String checkInTz) {
        this.checkInTz = checkInTz;
    }

    public double getCheckInLat() {
        return checkInLat;
    }

    public void setCheckInLat(double checkInLat) {
        this.checkInLat = checkInLat;
    }

    public double getCheckInLon() {
        return checkInLon;
    }

    public void setCheckInLon(double checkInLon) {
        this.checkInLon = checkInLon;
    }

    public String getCheckInImei() {
        return checkInImei;
    }

    public void setCheckInImei(String checkInImei) {
        this.checkInImei = checkInImei;
    }

    public String getCheckInAccuracy() {
        return checkInAccuracy;
    }

    public void setCheckInAccuracy(String checkInAccuracy) {
        this.checkInAccuracy = checkInAccuracy;
    }

    public long getCheckInSateliteUTC() {
        return checkInSateliteUTC;
    }

    public void setCheckInSateliteUTC(long checkInSateliteUTC) {
        this.checkInSateliteUTC = checkInSateliteUTC;
    }

    public long getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(long checkOut) {
        this.checkOut = checkOut;
    }

    public String getCheckOutTz() {
        return checkOutTz;
    }

    public void setCheckOutTz(String checkOutTz) {
        this.checkOutTz = checkOutTz;
    }

    public double getCheckOutLat() {
        return checkOutLat;
    }

    public void setCheckOutLat(double checkOutLat) {
        this.checkOutLat = checkOutLat;
    }

    public double getCheckOutLon() {
        return checkOutLon;
    }

    public void setCheckOutLon(double checkOutLon) {
        this.checkOutLon = checkOutLon;
    }

    public String getCheckOutImei() {
        return checkOutImei;
    }

    public void setCheckOutImei(String checkOutImei) {
        this.checkOutImei = checkOutImei;
    }

    public String getCheckOutAccuracy() {
        return checkOutAccuracy;
    }

    public void setCheckOutAccuracy(String checkOutAccuracy) {
        this.checkOutAccuracy = checkOutAccuracy;
    }

    public long getCheckOutSateliteUTC() {
        return checkOutSateliteUTC;
    }

    public void setCheckOutSateliteUTC(long checkOutSateliteUTC) {
        this.checkOutSateliteUTC = checkOutSateliteUTC;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
