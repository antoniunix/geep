package net.gshp.gepp.dto;

/**
 * Created by leo on 13/03/18.
 */

public class DtoReportHeadExhibition {
    private int id;
    private long idReportLocal;
    private int idTypeExhibition;
    private int idPdv;
    private String createdDate;
    private String hash;
    private int send;
    private int idReport;

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getId() {
        return id;
    }

    public DtoReportHeadExhibition setId(int id) {
        this.id = id;
        return this;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportHeadExhibition setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public int getIdTypeExhibition() {
        return idTypeExhibition;
    }

    public DtoReportHeadExhibition setIdTypeExhibition(int idTypeExhibition) {
        this.idTypeExhibition = idTypeExhibition;
        return this;
    }

    public int getIdPdv() {
        return idPdv;
    }

    public DtoReportHeadExhibition setIdPdv(int idPdv) {
        this.idPdv = idPdv;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public DtoReportHeadExhibition setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public DtoReportHeadExhibition setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public int getSend() {
        return send;
    }

    public DtoReportHeadExhibition setSend(int send) {
        this.send = send;
        return this;
    }
}
