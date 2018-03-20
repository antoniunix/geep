package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 13/03/18.
 */

public class DtoReportExhibitionDetail {

    private int id;
    private long idReportLocal;
    private String hashExhibition;
    @SerializedName("idGroup")
    private int idExhibitionGroup;
    private int idManufacturer;
    private int idCategory;
    private int idFamily;
    private int idSubFamily;
    private int type;
    @SerializedName("idLocation")
    private int location;
    private String path;
    private String hash;
    private int send;
    private int idReport;
    private int idPdv;
    private String family;
    private int idDepartment;

    public int getIdDepartment() {
        return idDepartment;
    }

    public DtoReportExhibitionDetail setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getIdPdv() {
        return idPdv;
    }

    public void setIdPdv(int idPdv) {
        this.idPdv = idPdv;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getHash() {
        return hash;
    }

    public DtoReportExhibitionDetail setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public int getId() {
        return id;
    }

    public DtoReportExhibitionDetail setId(int id) {
        this.id = id;
        return this;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportExhibitionDetail setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public String getHashExhibition() {
        return hashExhibition;
    }

    public DtoReportExhibitionDetail setHashExhibition(String hashExhibition) {
        this.hashExhibition = hashExhibition;
        return this;
    }

    public int getIdExhibitionGroup() {
        return idExhibitionGroup;
    }

    public DtoReportExhibitionDetail setIdExhibitionGroup(int idExhibitionGroup) {
        this.idExhibitionGroup = idExhibitionGroup;
        return this;
    }

    public int getIdManufacturer() {
        return idManufacturer;
    }

    public DtoReportExhibitionDetail setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
        return this;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public DtoReportExhibitionDetail setIdCategory(int idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public int getIdFamily() {
        return idFamily;
    }

    public DtoReportExhibitionDetail setIdFamily(int idFamily) {
        this.idFamily = idFamily;
        return this;
    }

    public int getIdSubFamily() {
        return idSubFamily;
    }

    public DtoReportExhibitionDetail setIdSubFamily(int idSubFamily) {
        this.idSubFamily = idSubFamily;
        return this;
    }

    public int getType() {
        return type;
    }

    public DtoReportExhibitionDetail setType(int type) {
        this.type = type;
        return this;
    }

    public int getLocation() {
        return location;
    }

    public DtoReportExhibitionDetail setLocation(int location) {
        this.location = location;
        return this;
    }

    public String getPath() {
        return path;
    }

    public DtoReportExhibitionDetail setPath(String path) {
        this.path = path;
        return this;
    }

    public int getSend() {
        return send;
    }

    public DtoReportExhibitionDetail setSend(int send) {
        this.send = send;
        return this;
    }

}
