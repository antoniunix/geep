package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoCStatusScann {

    private long id;
    private String color;
    private String value;
    private int idProblem;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIdProblem() {
        return idProblem;
    }

    public void setIdProblem(int idProblem) {
        this.idProblem = idProblem;
    }
}
