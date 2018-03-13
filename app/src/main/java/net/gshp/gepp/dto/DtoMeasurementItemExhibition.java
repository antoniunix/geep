package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoMeasurementItemExhibition {

    private int idMeasurement;
    private int idTypeCatalog;
    private int idItemRelation;
    private String value;
    private int parent;
    private int order;
    private int weight;

    public int getIdItemRelation() {
        return idItemRelation;
    }

    public void setIdItemRelation(int idItemRelation) {
        this.idItemRelation = idItemRelation;
    }

    public int getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(int idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    public int getIdTypeCatalog() {
        return idTypeCatalog;
    }

    public void setIdTypeCatalog(int idTypeCatalog) {
        this.idTypeCatalog = idTypeCatalog;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
