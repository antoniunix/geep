package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 14/03/18.
 */

public class DtoPdvCS {

    @SerializedName("pos_id")
    private long pdv_id;
    private String oportunity;
    private String success_photo;
    private String cluster_total;
    private String execution_time;
    private String colas_ms;
    private String colas_ss;
    private String sabores_ms;
    private String sabores_ss;
    private String agua;
    private String gatorade;
    private String lipton;
    private String starbucks;
    private String jumex;
    private String mixers;


    public long getPdv_id() {
        return pdv_id;
    }

    public DtoPdvCS setPdv_id(long pdv_id) {
        this.pdv_id = pdv_id;
        return this;
    }

    public String getOportunity() {
        return oportunity;
    }

    public DtoPdvCS setOportunity(String oportunity) {
        this.oportunity = oportunity;
        return this;
    }

    public String getSuccess_photo() {
        return success_photo;
    }

    public DtoPdvCS setSuccess_photo(String success_photo) {
        this.success_photo = success_photo;
        return this;
    }

    public String getCluster_total() {
        return cluster_total;
    }

    public DtoPdvCS setCluster_total(String cluster_total) {
        this.cluster_total = cluster_total;
        return this;
    }

    public String getExecution_time() {
        return execution_time;
    }

    public DtoPdvCS setExecution_time(String execution_time) {
        this.execution_time = execution_time;
        return this;
    }

    public String getColas_ms() {
        return colas_ms;
    }

    public DtoPdvCS setColas_ms(String colas_ms) {
        this.colas_ms = colas_ms;
        return this;
    }

    public String getColas_ss() {
        return colas_ss;
    }

    public DtoPdvCS setColas_ss(String colas_ss) {
        this.colas_ss = colas_ss;
        return this;
    }

    public String getSabores_ms() {
        return sabores_ms;
    }

    public DtoPdvCS setSabores_ms(String sabores_ms) {
        this.sabores_ms = sabores_ms;
        return this;
    }

    public String getSabores_ss() {
        return sabores_ss;
    }

    public DtoPdvCS setSabores_ss(String sabores_ss) {
        this.sabores_ss = sabores_ss;
        return this;
    }

    public String getAgua() {
        return agua;
    }

    public DtoPdvCS setAgua(String agua) {
        this.agua = agua;
        return this;
    }

    public String getGatorade() {
        return gatorade;
    }

    public DtoPdvCS setGatorade(String gatorade) {
        this.gatorade = gatorade;
        return this;
    }

    public String getLipton() {
        return lipton;
    }

    public DtoPdvCS setLipton(String lipton) {
        this.lipton = lipton;
        return this;
    }

    public String getStarbucks() {
        return starbucks;
    }

    public DtoPdvCS setStarbucks(String starbucks) {
        this.starbucks = starbucks;
        return this;
    }

    public String getJumex() {
        return jumex;
    }

    public DtoPdvCS setJumex(String jumex) {
        this.jumex = jumex;
        return this;
    }

    public String getMixers() {
        return mixers;
    }

    public DtoPdvCS setMixers(String mixers) {
        this.mixers = mixers;
        return this;
    }
}
