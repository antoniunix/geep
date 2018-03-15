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
    private String colas_ms_color;
    private String colas_ss_color;
    private String sabores_ms_color;
    private String sabores_ss_color;
    private String agua_color;
    private String gatorade_color;
    private String lipton_color;
    private String jumex_color;
    private String starbucks_color;
    private String mixers_color;

    public String getColas_ms_color() {
        return colas_ms_color;
    }

    public DtoPdvCS setColas_ms_color(String colas_ms_color) {
        this.colas_ms_color = colas_ms_color;
        return this;
    }

    public String getColas_ss_color() {
        return colas_ss_color;
    }

    public DtoPdvCS setColas_ss_color(String colas_ss_color) {
        this.colas_ss_color = colas_ss_color;
        return this;
    }

    public String getSabores_ms_color() {
        return sabores_ms_color;
    }

    public DtoPdvCS setSabores_ms_color(String sabores_ms_color) {
        this.sabores_ms_color = sabores_ms_color;
        return this;
    }

    public String getSabores_ss_color() {
        return sabores_ss_color;
    }

    public DtoPdvCS setSabores_ss_color(String sabores_ss_color) {
        this.sabores_ss_color = sabores_ss_color;
        return this;
    }

    public String getAgua_color() {
        return agua_color;
    }

    public DtoPdvCS setAgua_color(String agua_color) {
        this.agua_color = agua_color;
        return this;
    }

    public String getGatorade_color() {
        return gatorade_color;
    }

    public DtoPdvCS setGatorade_color(String gatorade_color) {
        this.gatorade_color = gatorade_color;
        return this;
    }

    public String getLipton_color() {
        return lipton_color;
    }

    public DtoPdvCS setLipton_color(String lipton_color) {
        this.lipton_color = lipton_color;
        return this;
    }

    public String getJumex_color() {
        return jumex_color;
    }

    public DtoPdvCS setJumex_color(String jumex_color) {
        this.jumex_color = jumex_color;
        return this;
    }

    public String getStarbucks_color() {
        return starbucks_color;
    }

    public DtoPdvCS setStarbucks_color(String starbucks_color) {
        this.starbucks_color = starbucks_color;
        return this;
    }

    public String getMixers_color() {
        return mixers_color;
    }

    public DtoPdvCS setMixers_color(String mixers_color) {
        this.mixers_color = mixers_color;
        return this;
    }

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
