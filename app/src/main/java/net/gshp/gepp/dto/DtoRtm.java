package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoRtm {
    private long id;
    private long idCanal;
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(long idCanal) {
        this.idCanal = idCanal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
