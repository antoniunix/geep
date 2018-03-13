package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoReportExhibitionMantainedPhoto {
    private int id;
    private int idReportLocal;
    private int idReportExhibitionMantained;
    private String hash;
    private String path;
    private int send;
    private String hashExhibitionCatalog;
    private int idPdv;
    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getIdPdv() {
        return idPdv;
    }

    public void setIdPdv(int idPdv) {
        this.idPdv = idPdv;
    }

    public String getHashExhibitionCatalog() {
        return hashExhibitionCatalog;
    }

    public DtoReportExhibitionMantainedPhoto setHashExhibitionCatalog(String hashExhibitionCatalog) {
        this.hashExhibitionCatalog = hashExhibitionCatalog;
        return this;
    }

    public int getId() {
        return id;
    }

    public DtoReportExhibitionMantainedPhoto setId(int id) {
        this.id = id;
        return this;
    }

    public int getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportExhibitionMantainedPhoto setIdReportLocal(int idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public int getIdReportExhibitionMantained() {
        return idReportExhibitionMantained;
    }

    public DtoReportExhibitionMantainedPhoto setIdReportExhibitionMantained(int idReportExhibitionMantained) {
        this.idReportExhibitionMantained = idReportExhibitionMantained;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public DtoReportExhibitionMantainedPhoto setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getPath() {
        return path;
    }

    public DtoReportExhibitionMantainedPhoto setPath(String path) {
        this.path = path;
        return this;
    }

    public int getSend() {
        return send;
    }

    public DtoReportExhibitionMantainedPhoto setSend(int send) {
        this.send = send;
        return this;
    }
}
