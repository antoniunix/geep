package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 8/03/18.
 */

public class DtoCoopGeneral {

    @SerializedName("id_coop")
    private long idCoop;
    @SerializedName("nombre_completo")
    private String nombreCompleto;
    private String username;
    @SerializedName("numero_semana")
    private String numeroSemana;
    @SerializedName("porcentaje_semana")
    private String porcentajeSemana;
    @SerializedName("acumulado_anual")
    private String acumuladoAnual;
    @SerializedName("efectividad_porcentaje_semana")
    private String efectividadPorcentajeSemana;
    @SerializedName("efectividad_porcentaje_semana_color")
    private String efectividad_porcentajeSemanaColor;
    @SerializedName("efectividad_acumulado_anual")
    private String efectividadAcumuladoAnual;
    @SerializedName("costo_inasistencia")
    private String costoInasistencia;
    @SerializedName("perfecta_tiendas")
    private String perfectaTiendas;
    @SerializedName("perfecta_porcentaje")
    private String perfectaPorcentaje;
    @SerializedName("perfecta_color")
    private String perfectaColor;
    @SerializedName("superior_tiendas")
    private String superiorTiendas;
    @SerializedName("superior_porcentaje")
    private String superiorPorcentaje;
    @SerializedName("superior_color")
    private String superiorColor;
    @SerializedName("regular_tiendas")
    private String regularTiendas;
    @SerializedName("regular_porcentaje")
    private String regularPorcentaje;
    @SerializedName("regular_color")
    private String regularColor;
    @SerializedName("critica_tiendas")
    private String criticaTiendas;
    @SerializedName("critica_porcentaje")
    private String criticaPorcentaje;
    @SerializedName("critica_color")
    private String criticaColor;
    @SerializedName("sinmedicion_tiendas")
    private String sinmedicionTiendas;
    @SerializedName("sinmedicion_porcentaje")
    private String sinmedicionPorcentaje;
    @SerializedName("sinmedicion_color")
    private String sinmedicionColor;
    @SerializedName("agotamiento_valor")
    private String agotamientoValor;
    @SerializedName("agotamiento_porcentaje")
    private String agotamientoPorcentaje;
    @SerializedName("agotamiento_color")
    private String agotamientoColor;
    @SerializedName("disponibilidad_valor")
    private String disponibilidadValor;
    @SerializedName("disponibilidad_porcentaje")
    private String disponibilidadPorcentaje;
    @SerializedName("disponibilidad_color")
    private String disponibilidadColor;
    @SerializedName("exhibiciones_valor")
    private String exhibicionesValor;
    @SerializedName("exhibiciones_porcentaje")
    private String exhibicionesPorcentaje;
    @SerializedName("exhibiciones_color")
    private String exhibicionesColor;
    @SerializedName("esenciales_valor")
    private String esencialesValor;
    @SerializedName("esenciales_porcentaje")
    private String esencialesPorcentaje;
    @SerializedName("esenciales_color")
    private String esencialesColor;
    @SerializedName("bigrocks_valor")
    private String bigrocksValor;
    @SerializedName("bigrocks_porcentaje")
    private String bigrocksPorcentaje;
    @SerializedName("bigrocks_color")
    private String bigrocksColor;
    @SerializedName("tacticos_valor")
    private String tacticosValor;
    @SerializedName("tacticos_porcentaje")
    private String tacticosPorcentaje;
    @SerializedName("tacticos_color")
    private String tacticosColor;
    @SerializedName("efectividad_valor")
    private String efectividadValor;
    @SerializedName("efectividad_porcentaje")
    private String efectividadPorcentaje;
    @SerializedName("efectividad_color")
    private String efectividadColor;
    @SerializedName("porcentaje_anual")
    private String porcentajeAnual;


    @Override
    public String toString() {
        return "DtoCoopGeneral{" +
                "idCoop=" + idCoop +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", username='" + username + '\'' +
                ", numeroSemana='" + numeroSemana + '\'' +
                ", porcentajeSemana='" + porcentajeSemana + '\'' +
                ", acumuladoAnual='" + acumuladoAnual + '\'' +
                ", efectividadPorcentajeSemana='" + efectividadPorcentajeSemana + '\'' +
                ", efectividad_porcentajeSemanaColor='" + efectividad_porcentajeSemanaColor + '\'' +
                ", efectividadAcumuladoAnual='" + efectividadAcumuladoAnual + '\'' +
                ", costoInasistencia='" + costoInasistencia + '\'' +
                ", perfectaTiendas='" + perfectaTiendas + '\'' +
                ", perfectaPorcentaje='" + perfectaPorcentaje + '\'' +
                ", perfectaColor='" + perfectaColor + '\'' +
                ", superiorTiendas='" + superiorTiendas + '\'' +
                ", superiorPorcentaje='" + superiorPorcentaje + '\'' +
                ", superiorColor='" + superiorColor + '\'' +
                ", regularTiendas='" + regularTiendas + '\'' +
                ", regularPorcentaje='" + regularPorcentaje + '\'' +
                ", regularColor='" + regularColor + '\'' +
                ", criticaTiendas='" + criticaTiendas + '\'' +
                ", criticaPorcentaje='" + criticaPorcentaje + '\'' +
                ", criticaColor='" + criticaColor + '\'' +
                ", sinmedicionTiendas='" + sinmedicionTiendas + '\'' +
                ", sinmedicionPorcentaje='" + sinmedicionPorcentaje + '\'' +
                ", sinmedicionColor='" + sinmedicionColor + '\'' +
                ", agotamientoValor='" + agotamientoValor + '\'' +
                ", agotamientoPorcentaje='" + agotamientoPorcentaje + '\'' +
                ", agotamientoColor='" + agotamientoColor + '\'' +
                ", disponibilidadValor='" + disponibilidadValor + '\'' +
                ", disponibilidadPorcentaje='" + disponibilidadPorcentaje + '\'' +
                ", disponibilidadColor='" + disponibilidadColor + '\'' +
                ", exhibicionesValor='" + exhibicionesValor + '\'' +
                ", exhibicionesPorcentaje='" + exhibicionesPorcentaje + '\'' +
                ", exhibicionesColor='" + exhibicionesColor + '\'' +
                ", esencialesValor='" + esencialesValor + '\'' +
                ", esencialesPorcentaje='" + esencialesPorcentaje + '\'' +
                ", esencialesColor='" + esencialesColor + '\'' +
                ", bigrocksValor='" + bigrocksValor + '\'' +
                ", bigrocksPorcentaje='" + bigrocksPorcentaje + '\'' +
                ", bigrocksColor='" + bigrocksColor + '\'' +
                ", tacticosValor='" + tacticosValor + '\'' +
                ", tacticosPorcentaje='" + tacticosPorcentaje + '\'' +
                ", tacticosColor='" + tacticosColor + '\'' +
                ", efectividadValor='" + efectividadValor + '\'' +
                ", efectividadPorcentaje='" + efectividadPorcentaje + '\'' +
                ", efectividadColor='" + efectividadColor + '\'' +
                ", porcentajeAnual='" + porcentajeAnual + '\'' +
                '}';
    }

    public long getIdCoop() {
        return idCoop;
    }

    public DtoCoopGeneral setIdCoop(long idCoop) {
        this.idCoop = idCoop;
        return this;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public DtoCoopGeneral setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DtoCoopGeneral setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNumeroSemana() {
        return numeroSemana;
    }

    public DtoCoopGeneral setNumeroSemana(String numeroSemana) {
        this.numeroSemana = numeroSemana;
        return this;
    }

    public String getPorcentajeSemana() {
        return porcentajeSemana;
    }

    public DtoCoopGeneral setPorcentajeSemana(String porcentajeSemana) {
        this.porcentajeSemana = porcentajeSemana;
        return this;
    }

    public String getAcumuladoAnual() {
        return acumuladoAnual;
    }

    public DtoCoopGeneral setAcumuladoAnual(String acumuladoAnual) {
        this.acumuladoAnual = acumuladoAnual;
        return this;
    }

    public String getEfectividadPorcentajeSemana() {
        return efectividadPorcentajeSemana;
    }

    public DtoCoopGeneral setEfectividadPorcentajeSemana(String efectividadPorcentajeSemana) {
        this.efectividadPorcentajeSemana = efectividadPorcentajeSemana;
        return this;
    }

    public String getEfectividad_porcentajeSemanaColor() {
        return efectividad_porcentajeSemanaColor;
    }

    public DtoCoopGeneral setEfectividad_porcentajeSemanaColor(String efectividad_porcentajeSemanaColor) {
        this.efectividad_porcentajeSemanaColor = efectividad_porcentajeSemanaColor;
        return this;
    }

    public String getEfectividadAcumuladoAnual() {
        return efectividadAcumuladoAnual;
    }

    public DtoCoopGeneral setEfectividadAcumuladoAnual(String efectividadAcumuladoAnual) {
        this.efectividadAcumuladoAnual = efectividadAcumuladoAnual;
        return this;
    }

    public String getCostoInasistencia() {
        return costoInasistencia;
    }

    public DtoCoopGeneral setCostoInasistencia(String costoInasistencia) {
        this.costoInasistencia = costoInasistencia;
        return this;
    }

    public String getPerfectaTiendas() {
        return perfectaTiendas;
    }

    public DtoCoopGeneral setPerfectaTiendas(String perfectaTiendas) {
        this.perfectaTiendas = perfectaTiendas;
        return this;
    }

    public String getPerfectaPorcentaje() {
        return perfectaPorcentaje;
    }

    public DtoCoopGeneral setPerfectaPorcentaje(String perfectaPorcentaje) {
        this.perfectaPorcentaje = perfectaPorcentaje;
        return this;
    }

    public String getPerfectaColor() {
        return perfectaColor;
    }

    public DtoCoopGeneral setPerfectaColor(String perfectaColor) {
        this.perfectaColor = perfectaColor;
        return this;
    }

    public String getSuperiorTiendas() {
        return superiorTiendas;
    }

    public DtoCoopGeneral setSuperiorTiendas(String superiorTiendas) {
        this.superiorTiendas = superiorTiendas;
        return this;
    }

    public String getSuperiorPorcentaje() {
        return superiorPorcentaje;
    }

    public DtoCoopGeneral setSuperiorPorcentaje(String superiorPorcentaje) {
        this.superiorPorcentaje = superiorPorcentaje;
        return this;
    }

    public String getSuperiorColor() {
        return superiorColor;
    }

    public DtoCoopGeneral setSuperiorColor(String superiorColor) {
        this.superiorColor = superiorColor;
        return this;
    }

    public String getRegularTiendas() {
        return regularTiendas;
    }

    public DtoCoopGeneral setRegularTiendas(String regularTiendas) {
        this.regularTiendas = regularTiendas;
        return this;
    }

    public String getRegularPorcentaje() {
        return regularPorcentaje;
    }

    public DtoCoopGeneral setRegularPorcentaje(String regularPorcentaje) {
        this.regularPorcentaje = regularPorcentaje;
        return this;
    }

    public String getRegularColor() {
        return regularColor;
    }

    public DtoCoopGeneral setRegularColor(String regularColor) {
        this.regularColor = regularColor;
        return this;
    }

    public String getCriticaTiendas() {
        return criticaTiendas;
    }

    public DtoCoopGeneral setCriticaTiendas(String criticaTiendas) {
        this.criticaTiendas = criticaTiendas;
        return this;
    }

    public String getCriticaPorcentaje() {
        return criticaPorcentaje;
    }

    public DtoCoopGeneral setCriticaPorcentaje(String criticaPorcentaje) {
        this.criticaPorcentaje = criticaPorcentaje;
        return this;
    }

    public String getCriticaColor() {
        return criticaColor;
    }

    public DtoCoopGeneral setCriticaColor(String criticaColor) {
        this.criticaColor = criticaColor;
        return this;
    }

    public String getSinmedicionTiendas() {
        return sinmedicionTiendas;
    }

    public DtoCoopGeneral setSinmedicionTiendas(String sinmedicionTiendas) {
        this.sinmedicionTiendas = sinmedicionTiendas;
        return this;
    }

    public String getSinmedicionPorcentaje() {
        return sinmedicionPorcentaje;
    }

    public DtoCoopGeneral setSinmedicionPorcentaje(String sinmedicionPorcentaje) {
        this.sinmedicionPorcentaje = sinmedicionPorcentaje;
        return this;
    }

    public String getSinmedicionColor() {
        return sinmedicionColor;
    }

    public DtoCoopGeneral setSinmedicionColor(String sinmedicionColor) {
        this.sinmedicionColor = sinmedicionColor;
        return this;
    }

    public String getAgotamientoValor() {
        return agotamientoValor;
    }

    public DtoCoopGeneral setAgotamientoValor(String agotamientoValor) {
        this.agotamientoValor = agotamientoValor;
        return this;
    }

    public String getAgotamientoPorcentaje() {
        return agotamientoPorcentaje;
    }

    public DtoCoopGeneral setAgotamientoPorcentaje(String agotamientoPorcentaje) {
        this.agotamientoPorcentaje = agotamientoPorcentaje;
        return this;
    }

    public String getAgotamientoColor() {
        return agotamientoColor;
    }

    public DtoCoopGeneral setAgotamientoColor(String agotamientoColor) {
        this.agotamientoColor = agotamientoColor;
        return this;
    }

    public String getDisponibilidadValor() {
        return disponibilidadValor;
    }

    public DtoCoopGeneral setDisponibilidadValor(String disponibilidadValor) {
        this.disponibilidadValor = disponibilidadValor;
        return this;
    }

    public String getDisponibilidadPorcentaje() {
        return disponibilidadPorcentaje;
    }

    public DtoCoopGeneral setDisponibilidadPorcentaje(String disponibilidadPorcentaje) {
        this.disponibilidadPorcentaje = disponibilidadPorcentaje;
        return this;
    }

    public String getDisponibilidadColor() {
        return disponibilidadColor;
    }

    public DtoCoopGeneral setDisponibilidadColor(String disponibilidadColor) {
        this.disponibilidadColor = disponibilidadColor;
        return this;
    }

    public String getExhibicionesValor() {
        return exhibicionesValor;
    }

    public DtoCoopGeneral setExhibicionesValor(String exhibicionesValor) {
        this.exhibicionesValor = exhibicionesValor;
        return this;
    }

    public String getExhibicionesPorcentaje() {
        return exhibicionesPorcentaje;
    }

    public DtoCoopGeneral setExhibicionesPorcentaje(String exhibicionesPorcentaje) {
        this.exhibicionesPorcentaje = exhibicionesPorcentaje;
        return this;
    }

    public String getExhibicionesColor() {
        return exhibicionesColor;
    }

    public DtoCoopGeneral setExhibicionesColor(String exhibicionesColor) {
        this.exhibicionesColor = exhibicionesColor;
        return this;
    }

    public String getEsencialesValor() {
        return esencialesValor;
    }

    public DtoCoopGeneral setEsencialesValor(String esencialesValor) {
        this.esencialesValor = esencialesValor;
        return this;
    }

    public String getEsencialesPorcentaje() {
        return esencialesPorcentaje;
    }

    public DtoCoopGeneral setEsencialesPorcentaje(String esencialesPorcentaje) {
        this.esencialesPorcentaje = esencialesPorcentaje;
        return this;
    }

    public String getEsencialesColor() {
        return esencialesColor;
    }

    public DtoCoopGeneral setEsencialesColor(String esencialesColor) {
        this.esencialesColor = esencialesColor;
        return this;
    }

    public String getBigrocksValor() {
        return bigrocksValor;
    }

    public DtoCoopGeneral setBigrocksValor(String bigrocksValor) {
        this.bigrocksValor = bigrocksValor;
        return this;
    }

    public String getBigrocksPorcentaje() {
        return bigrocksPorcentaje;
    }

    public DtoCoopGeneral setBigrocksPorcentaje(String bigrocksPorcentaje) {
        this.bigrocksPorcentaje = bigrocksPorcentaje;
        return this;
    }

    public String getBigrocksColor() {
        return bigrocksColor;
    }

    public DtoCoopGeneral setBigrocksColor(String bigrocksColor) {
        this.bigrocksColor = bigrocksColor;
        return this;
    }

    public String getTacticosValor() {
        return tacticosValor;
    }

    public DtoCoopGeneral setTacticosValor(String tacticosValor) {
        this.tacticosValor = tacticosValor;
        return this;
    }

    public String getTacticosPorcentaje() {
        return tacticosPorcentaje;
    }

    public DtoCoopGeneral setTacticosPorcentaje(String tacticosPorcentaje) {
        this.tacticosPorcentaje = tacticosPorcentaje;
        return this;
    }

    public String getTacticosColor() {
        return tacticosColor;
    }

    public DtoCoopGeneral setTacticosColor(String tacticosColor) {
        this.tacticosColor = tacticosColor;
        return this;
    }

    public String getEfectividadValor() {
        return efectividadValor;
    }

    public DtoCoopGeneral setEfectividadValor(String efectividadValor) {
        this.efectividadValor = efectividadValor;
        return this;
    }

    public String getEfectividadPorcentaje() {
        return efectividadPorcentaje;
    }

    public DtoCoopGeneral setEfectividadPorcentaje(String efectividadPorcentaje) {
        this.efectividadPorcentaje = efectividadPorcentaje;
        return this;
    }

    public String getEfectividadColor() {
        return efectividadColor;
    }

    public DtoCoopGeneral setEfectividadColor(String efectividadColor) {
        this.efectividadColor = efectividadColor;
        return this;
    }

    public String getPorcentajeAnual() {
        return porcentajeAnual;
    }

    public DtoCoopGeneral setPorcentajeAnual(String porcentajeAnual) {
        this.porcentajeAnual = porcentajeAnual;
        return this;
    }
}
