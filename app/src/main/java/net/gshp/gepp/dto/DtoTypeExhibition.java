package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoTypeExhibition {
    private int idMeasurement;
    private int idItemRelation;
    private String value;

    public int getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(int idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    public int getIdItemRelation() {
        return idItemRelation;
    }

    public void setIdItemRelation(int idItemRelation) {
        this.idItemRelation = idItemRelation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
