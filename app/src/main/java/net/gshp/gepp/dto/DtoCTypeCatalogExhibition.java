package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoCTypeCatalogExhibition {
    private int id;
    private String fieldName;
    private int position;
    private String value;
    private int idCategory;

    public int getIdCategory() {
        return idCategory;
    }

    public DtoCTypeCatalogExhibition setIdCategory(int idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public int getId() {
        return id;
    }

    public DtoCTypeCatalogExhibition setId(int id) {
        this.id = id;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DtoCTypeCatalogExhibition setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public DtoCTypeCatalogExhibition setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DtoCTypeCatalogExhibition setValue(String value) {
        this.value = value;
        return this;
    }
}
