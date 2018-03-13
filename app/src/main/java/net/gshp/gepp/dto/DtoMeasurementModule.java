package net.gshp.gepp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 11/03/18.
 */

public class DtoMeasurementModule {
    private Integer id;
    private Integer idMeasurement;
    private Integer idItemRelation;
    private String value;
    private Integer required;
    @SerializedName("orden")
    private int _orden;

    public int get_orden() {
        return _orden;
    }

    public void set_orden(int _orden) {
        this._orden = _orden;
    }

    public Integer getId() {
        return id;
    }

    public DtoMeasurementModule setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getIdMeasurement() {
        return idMeasurement;
    }

    public DtoMeasurementModule setIdMeasurement(Integer idMeasurement) {
        this.idMeasurement = idMeasurement;
        return this;
    }

    public Integer getIdItemRelation() {
        return idItemRelation;
    }

    public DtoMeasurementModule setIdItemRelation(Integer idItemRelation) {
        this.idItemRelation = idItemRelation;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DtoMeasurementModule setValue(String value) {
        this.value = value;
        return this;
    }

    public Integer getRequired() {
        return required;
    }

    public DtoMeasurementModule setRequired(Integer required) {
        this.required = required;
        return this;
    }
}
