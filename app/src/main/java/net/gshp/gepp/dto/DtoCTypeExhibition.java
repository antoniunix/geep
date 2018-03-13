package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoCTypeExhibition {

    private String fieldName;
    private long idCategory;
    private long id;
    private long position;
    private String value;

    public String getFieldName() {
        return fieldName;
    }

    public DtoCTypeExhibition setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public DtoCTypeExhibition setIdCategory(long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public long getId() {
        return id;
    }

    public DtoCTypeExhibition setId(long id) {
        this.id = id;
        return this;
    }

    public long getPosition() {
        return position;
    }

    public DtoCTypeExhibition setPosition(long position) {
        this.position = position;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DtoCTypeExhibition setValue(String value) {
        this.value = value;
        return this;
    }
}
