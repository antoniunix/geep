package net.gshp.gepp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leo on 8/03/18.
 */

public class DtoMeasurementDownloadSku implements Parcelable {

    private int idItemRelation;
    private String title;
    private String description;
    private int idMeasurement;
    private String url;
    private String ext;
    private String nameFile;
    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getIdItemRelation() {
        return idItemRelation;
    }

    public void setIdItemRelation(int idItemRelation) {
        this.idItemRelation = idItemRelation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(int idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public DtoMeasurementDownloadSku() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idItemRelation);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.idMeasurement);
        dest.writeString(this.url);
        dest.writeString(this.ext);
        dest.writeString(this.nameFile);
    }

    protected DtoMeasurementDownloadSku(Parcel in) {
        this.idItemRelation = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.idMeasurement = in.readInt();
        this.url = in.readString();
        this.ext = in.readString();
        this.nameFile = in.readString();
    }

    public static final Creator<DtoMeasurementDownloadSku> CREATOR = new Creator<DtoMeasurementDownloadSku>() {
        @Override
        public DtoMeasurementDownloadSku createFromParcel(Parcel source) {
            return new DtoMeasurementDownloadSku(source);
        }

        @Override
        public DtoMeasurementDownloadSku[] newArray(int size) {
            return new DtoMeasurementDownloadSku[size];
        }
    };
}

