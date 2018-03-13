package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoReportExhibitionMantainedCause {
    private int id;
    private int idReportLocal;
    private String hashExhibition;
    private int idCause;
    private String comment;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReportLocal() {
        return idReportLocal;
    }

    public void setIdReportLocal(int idReportLocal) {
        this.idReportLocal = idReportLocal;
    }

    public String getHashExhibition() {
        return hashExhibition;
    }

    public void setHashExhibition(String hashExhibition) {
        this.hashExhibition = hashExhibition;
    }

    public Integer getIdCause() {
        return idCause;
    }

    public DtoReportExhibitionMantainedCause setIdCause(Integer idCause) {
        this.idCause = idCause;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DtoReportExhibitionMantainedCause cause = (DtoReportExhibitionMantainedCause) o;

        return idCause == cause.idCause;

    }

    @Override
    public int hashCode() {
        return idCause;
    }
}
