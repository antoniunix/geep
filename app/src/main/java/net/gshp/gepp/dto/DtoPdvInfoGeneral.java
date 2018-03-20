package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 19/03/18.
 */

public class DtoPdvInfoGeneral {
    @SerializedName("pos_id")
    private long pdv_id;
    private String total_producto;
    private int total_producto_bandera;
    private String colas_porcentaje;
    private int colas_bandera;
    private String sabores_porcentaje;
    private int sabores_bandera;
    private String agua_porcentaje;
    private int agua_bandera;
    private String gatorade_porcentaje;
    private int gatorade_bandera;
    private String lipton_porcentaje;
    private int lipton_bandera;
    private String jumex_porcentaje;
    private int jumex_bandera;
    @SerializedName("starbucks_porcetanje")
    private String starbucks_porcentaje;
    private int starbucks_bandera;
    @SerializedName("mixers_porcetanje")
    private String mixers_porcentaje;
    private int mixers_bandera;

    public long getPdv_id() {
        return pdv_id;
    }

    public DtoPdvInfoGeneral setPdv_id(long pdv_id) {
        this.pdv_id = pdv_id;
        return this;
    }

    public String getTotal_producto() {
        return total_producto;
    }

    public DtoPdvInfoGeneral setTotal_producto(String total_producto) {
        this.total_producto = total_producto;
        return this;
    }

    public int getTotal_producto_bandera() {
        return total_producto_bandera;
    }

    public DtoPdvInfoGeneral setTotal_producto_bandera(int total_producto_bandera) {
        this.total_producto_bandera = total_producto_bandera;
        return this;
    }

    public String getColas_porcentaje() {
        return colas_porcentaje;
    }

    public DtoPdvInfoGeneral setColas_porcentaje(String colas_porcentaje) {
        this.colas_porcentaje = colas_porcentaje;
        return this;
    }

    public int getColas_bandera() {
        return colas_bandera;
    }

    public DtoPdvInfoGeneral setColas_bandera(int colas_bandera) {
        this.colas_bandera = colas_bandera;
        return this;
    }

    public String getSabores_porcentaje() {
        return sabores_porcentaje;
    }

    public DtoPdvInfoGeneral setSabores_porcentaje(String sabores_porcentaje) {
        this.sabores_porcentaje = sabores_porcentaje;
        return this;
    }

    public int getSabores_bandera() {
        return sabores_bandera;
    }

    public DtoPdvInfoGeneral setSabores_bandera(int sabores_bandera) {
        this.sabores_bandera = sabores_bandera;
        return this;
    }

    public String getAgua_porcentaje() {
        return agua_porcentaje;
    }

    public DtoPdvInfoGeneral setAgua_porcentaje(String agua_porcentaje) {
        this.agua_porcentaje = agua_porcentaje;
        return this;
    }

    public int getAgua_bandera() {
        return agua_bandera;
    }

    public DtoPdvInfoGeneral setAgua_bandera(int agua_bandera) {
        this.agua_bandera = agua_bandera;
        return this;
    }

    public String getGatorade_porcentaje() {
        return gatorade_porcentaje;
    }

    public DtoPdvInfoGeneral setGatorade_porcentaje(String gatorade_porcentaje) {
        this.gatorade_porcentaje = gatorade_porcentaje;
        return this;
    }

    public int getGatorade_bandera() {
        return gatorade_bandera;
    }

    public DtoPdvInfoGeneral setGatorade_bandera(int gatorade_bandera) {
        this.gatorade_bandera = gatorade_bandera;
        return this;
    }

    public String getLipton_porcentaje() {
        return lipton_porcentaje;
    }

    public DtoPdvInfoGeneral setLipton_porcentaje(String lipton_porcentaje) {
        this.lipton_porcentaje = lipton_porcentaje;
        return this;
    }

    public int getLipton_bandera() {
        return lipton_bandera;
    }

    public DtoPdvInfoGeneral setLipton_bandera(int lipton_bandera) {
        this.lipton_bandera = lipton_bandera;
        return this;
    }

    public String getJumex_porcentaje() {
        return jumex_porcentaje;
    }

    public DtoPdvInfoGeneral setJumex_porcentaje(String jumex_porcentaje) {
        this.jumex_porcentaje = jumex_porcentaje;
        return this;
    }

    public int getJumex_bandera() {
        return jumex_bandera;
    }

    public DtoPdvInfoGeneral setJumex_bandera(int jumex_bandera) {
        this.jumex_bandera = jumex_bandera;
        return this;
    }

    public String getStarbucks_porcentaje() {
        return starbucks_porcentaje;
    }

    public DtoPdvInfoGeneral setStarbucks_porcentaje(String starbucks_porcentaje) {
        this.starbucks_porcentaje = starbucks_porcentaje;
        return this;
    }

    public int getStarbucks_bandera() {
        return starbucks_bandera;
    }

    public DtoPdvInfoGeneral setStarbucks_bandera(int starbucks_bandera) {
        this.starbucks_bandera = starbucks_bandera;
        return this;
    }

    public String getMixers_porcentaje() {
        return mixers_porcentaje;
    }

    public DtoPdvInfoGeneral setMixers_porcentaje(String mixers_porcentaje) {
        this.mixers_porcentaje = mixers_porcentaje;
        return this;
    }

    public int getMixers_bandera() {
        return mixers_bandera;
    }

    public DtoPdvInfoGeneral setMixers_bandera(int mixers_bandera) {
        this.mixers_bandera = mixers_bandera;
        return this;
    }
}
