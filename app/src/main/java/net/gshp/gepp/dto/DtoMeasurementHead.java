package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoMeasurementHead {
    private int id;
    private String startDate;
    private String endDate;
    private String description;
    private int numPhoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumPhoto() {
        return numPhoto;
    }

    public void setNumPhoto(int numPhoto) {
        this.numPhoto = numPhoto;
    }
}
