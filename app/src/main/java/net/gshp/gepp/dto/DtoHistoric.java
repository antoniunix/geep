package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoHistoric {
    private Integer idPdv;
    private Integer value;
    private Integer count;
    private Integer week;

    public Integer getIdPdv() {
        return idPdv;
    }
    public void setIdPdv(Integer idPdv) {
        this.idPdv = idPdv;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getWeek() {
        return week;
    }
    public void setWeek(Integer week) {
        this.week = week;
    }

}
