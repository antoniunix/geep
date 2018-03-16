package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 16/03/18.
 */

public class DtoPdvGeneral {

    @SerializedName("pos_id")
    private long idPdv;
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
    private String starbucks_porcentaje;
    private int starbucks_bandera;
    private String mixers_porcentaje;
    private int mixers_bandera;

    public long getIdPdv() {
        return idPdv;
    }

    public DtoPdvGeneral setIdPdv(long idPdv) {
        this.idPdv = idPdv;
        return this;
    }

    public String getNumero_semana() {
        return numero_semana;
    }

    public DtoPdvGeneral setNumero_semana(String numero_semana) {
        this.numero_semana = numero_semana;
        return this;
    }

    public String getPorcentaje_semana() {
        return porcentaje_semana;
    }

    public DtoPdvGeneral setPorcentaje_semana(String porcentaje_semana) {
        this.porcentaje_semana = porcentaje_semana;
        return this;
    }

    public String getFoto_exito() {
        return foto_exito;
    }

    public DtoPdvGeneral setFoto_exito(String foto_exito) {
        this.foto_exito = foto_exito;
        return this;
    }

    public String getEfectividad_porcentaje_semana() {
        return efectividad_porcentaje_semana;
    }

    public DtoPdvGeneral setEfectividad_porcentaje_semana(String efectividad_porcentaje_semana) {
        this.efectividad_porcentaje_semana = efectividad_porcentaje_semana;
        return this;
    }

    public String getEfectividad_porcentaje_semana_bandera() {
        return efectividad_porcentaje_semana_bandera;
    }

    public DtoPdvGeneral setEfectividad_porcentaje_semana_bandera(String efectividad_porcentaje_semana_bandera) {
        this.efectividad_porcentaje_semana_bandera = efectividad_porcentaje_semana_bandera;
        return this;
    }

    public String getEfectividad_acumulado_anual() {
        return efectividad_acumulado_anual;
    }

    public DtoPdvGeneral setEfectividad_acumulado_anual(String efectividad_acumulado_anual) {
        this.efectividad_acumulado_anual = efectividad_acumulado_anual;
        return this;
    }

    public String getCosto_inasistencia() {
        return costo_inasistencia;
    }

    public DtoPdvGeneral setCosto_inasistencia(String costo_inasistencia) {
        this.costo_inasistencia = costo_inasistencia;
        return this;
    }

    public String getSuperior_tiendas() {
        return superior_tiendas;
    }

    public DtoPdvGeneral setSuperior_tiendas(String superior_tiendas) {
        this.superior_tiendas = superior_tiendas;
        return this;
    }

    public String getSuperior_porcentaje() {
        return superior_porcentaje;
    }

    public DtoPdvGeneral setSuperior_porcentaje(String superior_porcentaje) {
        this.superior_porcentaje = superior_porcentaje;
        return this;
    }

    public String getNormal_tiendas() {
        return normal_tiendas;
    }

    public DtoPdvGeneral setNormal_tiendas(String normal_tiendas) {
        this.normal_tiendas = normal_tiendas;
        return this;
    }

    public String getNormal_porcentaje() {
        return normal_porcentaje;
    }

    public DtoPdvGeneral setNormal_porcentaje(String normal_porcentaje) {
        this.normal_porcentaje = normal_porcentaje;
        return this;
    }

    public String getBasica_tiendas() {
        return basica_tiendas;
    }

    public DtoPdvGeneral setBasica_tiendas(String basica_tiendas) {
        this.basica_tiendas = basica_tiendas;
        return this;
    }

    public String getBasica_porcentaje() {
        return basica_porcentaje;
    }

    public DtoPdvGeneral setBasica_porcentaje(String basica_porcentaje) {
        this.basica_porcentaje = basica_porcentaje;
        return this;
    }

    public String getCritica_tiendas() {
        return critica_tiendas;
    }

    public DtoPdvGeneral setCritica_tiendas(String critica_tiendas) {
        this.critica_tiendas = critica_tiendas;
        return this;
    }

    public String getCritica_porcentaje() {
        return critica_porcentaje;
    }

    public DtoPdvGeneral setCritica_porcentaje(String critica_porcentaje) {
        this.critica_porcentaje = critica_porcentaje;
        return this;
    }

    public String getSinmedicion_tienda() {
        return sinmedicion_tienda;
    }

    public DtoPdvGeneral setSinmedicion_tienda(String sinmedicion_tienda) {
        this.sinmedicion_tienda = sinmedicion_tienda;
        return this;
    }

    public String getSinmedicion_porcentaje() {
        return sinmedicion_porcentaje;
    }

    public DtoPdvGeneral setSinmedicion_porcentaje(String sinmedicion_porcentaje) {
        this.sinmedicion_porcentaje = sinmedicion_porcentaje;
        return this;
    }

    public String getColas() {
        return colas;
    }

    public DtoPdvGeneral setColas(String colas) {
        this.colas = colas;
        return this;
    }

    public String getColas_color() {
        return colas_color;
    }

    public DtoPdvGeneral setColas_color(String colas_color) {
        this.colas_color = colas_color;
        return this;
    }

    public String getSabores() {
        return sabores;
    }

    public DtoPdvGeneral setSabores(String sabores) {
        this.sabores = sabores;
        return this;
    }

    public String getSabores_color() {
        return sabores_color;
    }

    public DtoPdvGeneral setSabores_color(String sabores_color) {
        this.sabores_color = sabores_color;
        return this;
    }

    public String getAgua() {
        return agua;
    }

    public DtoPdvGeneral setAgua(String agua) {
        this.agua = agua;
        return this;
    }

    public String getAgua_color() {
        return agua_color;
    }

    public DtoPdvGeneral setAgua_color(String agua_color) {
        this.agua_color = agua_color;
        return this;
    }

    public String getGatorade() {
        return gatorade;
    }

    public DtoPdvGeneral setGatorade(String gatorade) {
        this.gatorade = gatorade;
        return this;
    }

    public String getGatorade_color() {
        return gatorade_color;
    }

    public DtoPdvGeneral setGatorade_color(String gatorade_color) {
        this.gatorade_color = gatorade_color;
        return this;
    }

    public String getLipton() {
        return lipton;
    }

    public DtoPdvGeneral setLipton(String lipton) {
        this.lipton = lipton;
        return this;
    }

    public String getLipton_color() {
        return lipton_color;
    }

    public DtoPdvGeneral setLipton_color(String lipton_color) {
        this.lipton_color = lipton_color;
        return this;
    }

    public String getJumex() {
        return jumex;
    }

    public DtoPdvGeneral setJumex(String jumex) {
        this.jumex = jumex;
        return this;
    }

    public String getJumex_color() {
        return jumex_color;
    }

    public DtoPdvGeneral setJumex_color(String jumex_color) {
        this.jumex_color = jumex_color;
        return this;
    }

    public String getStarbucks() {
        return starbucks;
    }

    public DtoPdvGeneral setStarbucks(String starbucks) {
        this.starbucks = starbucks;
        return this;
    }

    public String getStarbucks_color() {
        return starbucks_color;
    }

    public DtoPdvGeneral setStarbucks_color(String starbucks_color) {
        this.starbucks_color = starbucks_color;
        return this;
    }

    public String getMixers() {
        return mixers;
    }

    public DtoPdvGeneral setMixers(String mixers) {
        this.mixers = mixers;
        return this;
    }

    public String getMixers_color() {
        return mixers_color;
    }

    public DtoPdvGeneral setMixers_color(String mixers_color) {
        this.mixers_color = mixers_color;
        return this;
    }

    public String getTotal_producto() {
        return total_producto;
    }

    public DtoPdvGeneral setTotal_producto(String total_producto) {
        this.total_producto = total_producto;
        return this;
    }

    public int getTotal_producto_bandera() {
        return total_producto_bandera;
    }

    public DtoPdvGeneral setTotal_producto_bandera(int total_producto_bandera) {
        this.total_producto_bandera = total_producto_bandera;
        return this;
    }

    public String getColas_porcentaje() {
        return colas_porcentaje;
    }

    public DtoPdvGeneral setColas_porcentaje(String colas_porcentaje) {
        this.colas_porcentaje = colas_porcentaje;
        return this;
    }

    public int getColas_bandera() {
        return colas_bandera;
    }

    public DtoPdvGeneral setColas_bandera(int colas_bandera) {
        this.colas_bandera = colas_bandera;
        return this;
    }

    public String getSabores_porcentaje() {
        return sabores_porcentaje;
    }

    public DtoPdvGeneral setSabores_porcentaje(String sabores_porcentaje) {
        this.sabores_porcentaje = sabores_porcentaje;
        return this;
    }

    public int getSabores_bandera() {
        return sabores_bandera;
    }

    public DtoPdvGeneral setSabores_bandera(int sabores_bandera) {
        this.sabores_bandera = sabores_bandera;
        return this;
    }

    public String getAgua_porcentaje() {
        return agua_porcentaje;
    }

    public DtoPdvGeneral setAgua_porcentaje(String agua_porcentaje) {
        this.agua_porcentaje = agua_porcentaje;
        return this;
    }

    public int getAgua_bandera() {
        return agua_bandera;
    }

    public DtoPdvGeneral setAgua_bandera(int agua_bandera) {
        this.agua_bandera = agua_bandera;
        return this;
    }

    public String getGatorade_porcentaje() {
        return gatorade_porcentaje;
    }

    public DtoPdvGeneral setGatorade_porcentaje(String gatorade_porcentaje) {
        this.gatorade_porcentaje = gatorade_porcentaje;
        return this;
    }

    public int getGatorade_bandera() {
        return gatorade_bandera;
    }

    public DtoPdvGeneral setGatorade_bandera(int gatorade_bandera) {
        this.gatorade_bandera = gatorade_bandera;
        return this;
    }

    public String getLipton_porcentaje() {
        return lipton_porcentaje;
    }

    public DtoPdvGeneral setLipton_porcentaje(String lipton_porcentaje) {
        this.lipton_porcentaje = lipton_porcentaje;
        return this;
    }

    public int getLipton_bandera() {
        return lipton_bandera;
    }

    public DtoPdvGeneral setLipton_bandera(int lipton_bandera) {
        this.lipton_bandera = lipton_bandera;
        return this;
    }

    public String getJumex_porcentaje() {
        return jumex_porcentaje;
    }

    public DtoPdvGeneral setJumex_porcentaje(String jumex_porcentaje) {
        this.jumex_porcentaje = jumex_porcentaje;
        return this;
    }

    public int getJumex_bandera() {
        return jumex_bandera;
    }

    public DtoPdvGeneral setJumex_bandera(int jumex_bandera) {
        this.jumex_bandera = jumex_bandera;
        return this;
    }

    public String getStarbucks_porcentaje() {
        return starbucks_porcentaje;
    }

    public DtoPdvGeneral setStarbucks_porcentaje(String starbucks_porcentaje) {
        this.starbucks_porcentaje = starbucks_porcentaje;
        return this;
    }

    public int getStarbucks_bandera() {
        return starbucks_bandera;
    }

    public DtoPdvGeneral setStarbucks_bandera(int starbucks_bandera) {
        this.starbucks_bandera = starbucks_bandera;
        return this;
    }

    public String getMixers_porcentaje() {
        return mixers_porcentaje;
    }

    public DtoPdvGeneral setMixers_porcentaje(String mixers_porcentaje) {
        this.mixers_porcentaje = mixers_porcentaje;
        return this;
    }

    public int getMixers_bandera() {
        return mixers_bandera;
    }

    public DtoPdvGeneral setMixers_bandera(int mixers_bandera) {
        this.mixers_bandera = mixers_bandera;
        return this;
    }
}
