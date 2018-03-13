package net.gshp.gepp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leo on 12/03/18.
 */

public class DtoReportExhibitionMantained implements Parcelable {


    private int id;
    private long idReportLocal;
    private String hashExhibition;
    private int isExhibit;
    private String hash;
    private int send;
    private String createDate;

    private String exhibition_name;
    private int min_photos;
    private int max_photos;
    private String end_date;
    private String location;
    private String sub_famlly;
    private String family;
    private String category;
    private String manufacturer;
    private String value;
    private int photoDone;
    private int id_exhibition_group;
    private int position;
    private int typeModule;
    private int idReport;

    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoReportExhibitionMantained)) return false;

        DtoReportExhibitionMantained that = (DtoReportExhibitionMantained) o;

        // Log.e("hash", "hash "+hash);
        if (!hash.equals(that.hash)) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = hash.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getTypeModule() {
        return typeModule;
    }

    public void setTypeModule(int typeModule) {
        this.typeModule = typeModule;
    }

    public int getPhotoDone() {
        return photoDone;
    }

    public void setPhotoDone(int photoDone) {
        this.photoDone = photoDone;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getExhibition_name() {
        return exhibition_name;
    }

    public void setExhibition_name(String exhibition_name) {
        this.exhibition_name = exhibition_name;
    }

    public int getMin_photos() {
        return min_photos;
    }

    public void setMin_photos(int min_photos) {
        this.min_photos = min_photos;
    }

    public int getMax_photos() {
        return max_photos;
    }

    public void setMax_photos(int max_photos) {
        this.max_photos = max_photos;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSub_famlly() {
        return sub_famlly;
    }

    public void setSub_famlly(String sub_famlly) {
        this.sub_famlly = sub_famlly;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public int getId_exhibition_group() {
        return id_exhibition_group;
    }

    public DtoReportExhibitionMantained setId_exhibition_group(int id_exhibition_group) {
        this.id_exhibition_group = id_exhibition_group;
        return this;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoReportExhibitionMantained setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public String getHashExhibition() {
        return hashExhibition;
    }

    public void setHashExhibition(String hashExhibition) {
        this.hashExhibition = hashExhibition;
    }

    public int getIsExhibit() {
        return isExhibit;
    }

    public void setIsExhibit(int isExhibit) {
        this.isExhibit = isExhibit;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    @Override
    public String toString() {
        return "DtoReportExhibitionMantained{" +
                "id=" + id +
                ", idReportLocal=" + idReportLocal +
                ", hashExhibition='" + hashExhibition + '\'' +
                ", isExhibit=" + isExhibit +
                ", hash='" + hash + '\'' +
                ", send=" + send +
                ", exhibition_name='" + exhibition_name + '\'' +
                ", min_photos=" + min_photos +
                ", max_photos=" + max_photos +
                ", end_date='" + end_date + '\'' +
                ", location='" + location + '\'' +
                ", sub_famlly='" + sub_famlly + '\'' +
                ", family='" + family + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", value='" + value + '\'' +
                ", photoDone=" + photoDone +
                ", id_exhibition_group=" + id_exhibition_group +
                ", position=" + position +
                ", count=" + count +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeLong(this.idReportLocal);
        dest.writeString(this.hashExhibition);
        dest.writeInt(this.isExhibit);
        dest.writeString(this.hash);
        dest.writeInt(this.send);
        dest.writeString(this.createDate);
        dest.writeString(this.exhibition_name);
        dest.writeInt(this.min_photos);
        dest.writeInt(this.max_photos);
        dest.writeString(this.end_date);
        dest.writeString(this.location);
        dest.writeString(this.sub_famlly);
        dest.writeString(this.family);
        dest.writeString(this.category);
        dest.writeString(this.manufacturer);
        dest.writeString(this.value);
        dest.writeInt(this.photoDone);
        dest.writeInt(this.id_exhibition_group);
        dest.writeInt(this.position);
        dest.writeInt(this.typeModule);
        dest.writeInt(this.idReport);
        dest.writeInt(this.count);
    }

    public DtoReportExhibitionMantained() {
    }

    protected DtoReportExhibitionMantained(Parcel in) {
        this.id = in.readInt();
        this.idReportLocal = in.readInt();
        this.hashExhibition = in.readString();
        this.isExhibit = in.readInt();
        this.hash = in.readString();
        this.send = in.readInt();
        this.createDate = in.readString();
        this.exhibition_name = in.readString();
        this.min_photos = in.readInt();
        this.max_photos = in.readInt();
        this.end_date = in.readString();
        this.location = in.readString();
        this.sub_famlly = in.readString();
        this.family = in.readString();
        this.category = in.readString();
        this.manufacturer = in.readString();
        this.value = in.readString();
        this.photoDone = in.readInt();
        this.id_exhibition_group = in.readInt();
        this.position = in.readInt();
        this.typeModule = in.readInt();
        this.idReport = in.readInt();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<DtoReportExhibitionMantained> CREATOR = new Parcelable.Creator<DtoReportExhibitionMantained>() {
        @Override
        public DtoReportExhibitionMantained createFromParcel(Parcel source) {
            return new DtoReportExhibitionMantained(source);
        }

        @Override
        public DtoReportExhibitionMantained[] newArray(int size) {
            return new DtoReportExhibitionMantained[size];
        }
    };
}
