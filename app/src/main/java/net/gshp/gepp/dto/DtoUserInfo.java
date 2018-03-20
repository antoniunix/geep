package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 16/03/18.
 */

public class DtoUserInfo {


    private String numero_semana;
    private String porcentaje_semana;
    private String foto_exito;
    private String efectividad_porcentaje_semana;
    private String efectividad_porcentaje_semana_bandera;
    private String efectividad_acumulado_anual;
    private String costo_inasistencia;
    private String superior_tiendas;
    private String superior_porcentaje;
    private String normal_tiendas;
    private String normal_porcentaje;
    private String basica_tiendas;
    private String basica_porcentaje;
    private String critica_tiendas;
    private String critica_porcentaje;
    @SerializedName("sinmedicion_tiendas}")
    private String sinmedicion_tienda;
    private String sinmedicion_porcentaje;
    private String colas;
    private String colas_color;
    private String sabores;
    private String sabores_color;
    private String agua;
    private String agua_color;
    private String gatorade;
    private String gatorade_color;
    private String lipton;
    private String lipton_color;
    private String jumex;
    private String jumex_color;
    private String starbucks;
    private String starbucks_color;
    private String mixers;
    private String mixers_color;


    public String getNumero_semana() {
        return numero_semana;
    }

    public DtoUserInfo setNumero_semana(String numero_semana) {
        this.numero_semana = numero_semana;
        return this;
    }

    public String getPorcentaje_semana() {
        return porcentaje_semana;
    }

    public DtoUserInfo setPorcentaje_semana(String porcentaje_semana) {
        this.porcentaje_semana = porcentaje_semana;
        return this;
    }

    public String getFoto_exito() {
        return foto_exito;
    }

    public DtoUserInfo setFoto_exito(String foto_exito) {
        this.foto_exito = foto_exito;
        return this;
    }

    public String getEfectividad_porcentaje_semana() {
        return efectividad_porcentaje_semana;
    }

    public DtoUserInfo setEfectividad_porcentaje_semana(String efectividad_porcentaje_semana) {
        this.efectividad_porcentaje_semana = efectividad_porcentaje_semana;
        return this;
    }

    public String getEfectividad_porcentaje_semana_bandera() {
        return efectividad_porcentaje_semana_bandera;
    }

    public DtoUserInfo setEfectividad_porcentaje_semana_bandera(String efectividad_porcentaje_semana_bandera) {
        this.efectividad_porcentaje_semana_bandera = efectividad_porcentaje_semana_bandera;
        return this;
    }

    public String getEfectividad_acumulado_anual() {
        return efectividad_acumulado_anual;
    }

    public DtoUserInfo setEfectividad_acumulado_anual(String efectividad_acumulado_anual) {
        this.efectividad_acumulado_anual = efectividad_acumulado_anual;
        return this;
    }

    public String getCosto_inasistencia() {
        return costo_inasistencia;
    }

    public DtoUserInfo setCosto_inasistencia(String costo_inasistencia) {
        this.costo_inasistencia = costo_inasistencia;
        return this;
    }

    public String getSuperior_tiendas() {
        return superior_tiendas;
    }

    public DtoUserInfo setSuperior_tiendas(String superior_tiendas) {
        this.superior_tiendas = superior_tiendas;
        return this;
    }

    public String getSuperior_porcentaje() {
        return superior_porcentaje;
    }

    public DtoUserInfo setSuperior_porcentaje(String superior_porcentaje) {
        this.superior_porcentaje = superior_porcentaje;
        return this;
    }

    public String getNormal_tiendas() {
        return normal_tiendas;
    }

    public DtoUserInfo setNormal_tiendas(String normal_tiendas) {
        this.normal_tiendas = normal_tiendas;
        return this;
    }

    public String getNormal_porcentaje() {
        return normal_porcentaje;
    }

    public DtoUserInfo setNormal_porcentaje(String normal_porcentaje) {
        this.normal_porcentaje = normal_porcentaje;
        return this;
    }

    public String getBasica_tiendas() {
        return basica_tiendas;
    }

    public DtoUserInfo setBasica_tiendas(String basica_tiendas) {
        this.basica_tiendas = basica_tiendas;
        return this;
    }

    public String getBasica_porcentaje() {
        return basica_porcentaje;
    }

    public DtoUserInfo setBasica_porcentaje(String basica_porcentaje) {
        this.basica_porcentaje = basica_porcentaje;
        return this;
    }

    public String getCritica_tiendas() {
        return critica_tiendas;
    }

    public DtoUserInfo setCritica_tiendas(String critica_tiendas) {
        this.critica_tiendas = critica_tiendas;
        return this;
    }

    public String getCritica_porcentaje() {
        return critica_porcentaje;
    }

    public DtoUserInfo setCritica_porcentaje(String critica_porcentaje) {
        this.critica_porcentaje = critica_porcentaje;
        return this;
    }

    public String getSinmedicion_tienda() {
        return sinmedicion_tienda;
    }

    public DtoUserInfo setSinmedicion_tienda(String sinmedicion_tienda) {
        this.sinmedicion_tienda = sinmedicion_tienda;
        return this;
    }

    public String getSinmedicion_porcentaje() {
        return sinmedicion_porcentaje;
    }

    public DtoUserInfo setSinmedicion_porcentaje(String sinmedicion_porcentaje) {
        this.sinmedicion_porcentaje = sinmedicion_porcentaje;
        return this;
    }

    public String getColas() {
        return colas;
    }

    public DtoUserInfo setColas(String colas) {
        this.colas = colas;
        return this;
    }

    public String getColas_color() {
        return colas_color;
    }

    public DtoUserInfo setColas_color(String colas_color) {
        this.colas_color = colas_color;
        return this;
    }

    public String getSabores() {
        return sabores;
    }

    public DtoUserInfo setSabores(String sabores) {
        this.sabores = sabores;
        return this;
    }

    public String getSabores_color() {
        return sabores_color;
    }

    public DtoUserInfo setSabores_color(String sabores_color) {
        this.sabores_color = sabores_color;
        return this;
    }

    public String getAgua() {
        return agua;
    }

    public DtoUserInfo setAgua(String agua) {
        this.agua = agua;
        return this;
    }

    public String getAgua_color() {
        return agua_color;
    }

    public DtoUserInfo setAgua_color(String agua_color) {
        this.agua_color = agua_color;
        return this;
    }

    public String getGatorade() {
        return gatorade;
    }

    public DtoUserInfo setGatorade(String gatorade) {
        this.gatorade = gatorade;
        return this;
    }

    public String getGatorade_color() {
        return gatorade_color;
    }

    public DtoUserInfo setGatorade_color(String gatorade_color) {
        this.gatorade_color = gatorade_color;
        return this;
    }

    public String getLipton() {
        return lipton;
    }

    public DtoUserInfo setLipton(String lipton) {
        this.lipton = lipton;
        return this;
    }

    public String getLipton_color() {
        return lipton_color;
    }

    public DtoUserInfo setLipton_color(String lipton_color) {
        this.lipton_color = lipton_color;
        return this;
    }

    public String getJumex() {
        return jumex;
    }

    public DtoUserInfo setJumex(String jumex) {
        this.jumex = jumex;
        return this;
    }

    public String getJumex_color() {
        return jumex_color;
    }

    public DtoUserInfo setJumex_color(String jumex_color) {
        this.jumex_color = jumex_color;
        return this;
    }

    public String getStarbucks() {
        return starbucks;
    }

    public DtoUserInfo setStarbucks(String starbucks) {
        this.starbucks = starbucks;
        return this;
    }

    public String getStarbucks_color() {
        return starbucks_color;
    }

    public DtoUserInfo setStarbucks_color(String starbucks_color) {
        this.starbucks_color = starbucks_color;
        return this;
    }

    public String getMixers() {
        return mixers;
    }

    public DtoUserInfo setMixers(String mixers) {
        this.mixers = mixers;
        return this;
    }

    public String getMixers_color() {
        return mixers_color;
    }

    public DtoUserInfo setMixers_color(String mixers_color) {
        this.mixers_color = mixers_color;
        return this;
    }
}
