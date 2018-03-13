package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoMeasurementFilter {

    private int idItemRelation;
    private int idMeasurement;
    private String value,filter1, filter2, filter3;
    private int min_photos, max_photos;
    private long id_location, orden;

    public String getFilter1() {
        return filter1;
    }

    public DtoMeasurementFilter setFilter1(String filter1) {
        this.filter1 = filter1;
        return this;
    }

    public String getFilter2() {
        return filter2;
    }

    public DtoMeasurementFilter setFilter2(String filter2) {
        this.filter2 = filter2;
        return this;
    }

    public String getFilter3() {
        return filter3;
    }

    public DtoMeasurementFilter setFilter3(String filter3) {
        this.filter3 = filter3;
        return this;
    }

    public int getIdItemRelation() {
        return idItemRelation;
    }

    public DtoMeasurementFilter setIdItemRelation(int idItemRelation) {
        this.idItemRelation = idItemRelation;
        return this;
    }

    public int getIdMeasurement() {
        return idMeasurement;
    }

    public DtoMeasurementFilter setIdMeasurement(int idMeasurement) {
        this.idMeasurement = idMeasurement;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DtoMeasurementFilter setValue(String value) {
        this.value = value;
        return this;
    }

    public int getMin_photos() {
        return min_photos;
    }

    public DtoMeasurementFilter setMin_photos(int min_photos) {
        this.min_photos = min_photos;
        return this;
    }

    public int getMax_photos() {
        return max_photos;
    }

    public DtoMeasurementFilter setMax_photos(int max_photos) {
        this.max_photos = max_photos;
        return this;
    }

    public long getId_location() {
        return id_location;
    }

    public DtoMeasurementFilter setId_location(long id_location) {
        this.id_location = id_location;
        return this;
    }

    public long getOrden() {
        return orden;
    }

    public DtoMeasurementFilter setOrden(long orden) {
        this.orden = orden;
        return this;
    }
}
