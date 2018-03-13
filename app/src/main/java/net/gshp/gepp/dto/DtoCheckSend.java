package net.gshp.gepp.dto;

/**
 * Created by leo on 10/03/18.
 */

public class DtoCheckSend {
    private int idAgenda;
    private int idPdv;
    private String version;
    private String hash;
    private long checkInDate;
    private String checkInTz;
    private double checkInLat;
    private double checkInLon;
    private String checkInIMEI;
    private String checkInAccuracy;
    private long checkInSatelliteUTC;
    private String idUser="@user";
    private String idPerson="@person";
    private int idReportLocal;

    public int getIdReportLocal() {
        return idReportLocal;
    }
    public void setIdReportLocal(int idReportLocal) {
        this.idReportLocal = idReportLocal;
    }
    public int getIdAgenda() {
        return idAgenda;
    }
    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }
    public int getIdPdv() {
        return idPdv;
    }
    public void setIdPdv(int idPdv) {
        this.idPdv = idPdv;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public long getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
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
    public String getCheckInIMEI() {
        return checkInIMEI;
    }
    public void setCheckInIMEI(String checkInIMEI) {
        this.checkInIMEI = checkInIMEI;
    }
    public String getCheckInAccuracy() {
        return checkInAccuracy;
    }
    public void setCheckInAccuracy(String checkInAccuracy) {
        this.checkInAccuracy = checkInAccuracy;
    }
    public long getCheckInSatelliteUTC() {
        return checkInSatelliteUTC;
    }
    public void setCheckInSatelliteUTC(long checkInSatelliteUTC) {
        this.checkInSatelliteUTC = checkInSatelliteUTC;
    }
    @Override
    public String toString() {
        return "DTOCheckSend [idAgenda=" + idAgenda + ", idPdv=" + idPdv + ", version=" + version + ", hash=" + hash
                + ", checkInDate=" + checkInDate + ", checkInTz=" + checkInTz + ", checkInLat=" + checkInLat
                + ", checkInLon=" + checkInLon + ", checkInIMEI=" + checkInIMEI + ", checkInAccuracy=" + checkInAccuracy
                + ", checkInSatelliteUTC=" + checkInSatelliteUTC + ", idUser=" + idUser + ", idPerson=" + idPerson
                + ", idReportLocal=" + idReportLocal + "]";
    }
}
