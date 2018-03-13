package net.gshp.gepp.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leo on 9/03/18.
 */

public class DtoBundle implements Parcelable {

    private long idPDV;
    private long idReportLocal;
    private long idRutero;
    private long idCategory;
    private int typeTask;
    private long idUser;
    private int module;

    @Override
    public String toString() {
        return "DtoBundle{" +
                "idPDV=" + idPDV +
                ", idReportLocal=" + idReportLocal +
                ", idRutero=" + idRutero +
                ", idCategory=" + idCategory +
                ", typeTask=" + typeTask +
                ", idUser=" + idUser +
                ", module=" + module +
                '}';
    }

    public long getIdPDV() {
        return idPDV;
    }

    public DtoBundle setIdPDV(long idPDV) {
        this.idPDV = idPDV;
        return this;
    }

    public long getIdReportLocal() {
        return idReportLocal;
    }

    public DtoBundle setIdReportLocal(long idReportLocal) {
        this.idReportLocal = idReportLocal;
        return this;
    }

    public long getIdRutero() {
        return idRutero;
    }

    public DtoBundle setIdRutero(long idRutero) {
        this.idRutero = idRutero;
        return this;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public DtoBundle setIdCategory(long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public int getTypeTask() {
        return typeTask;
    }

    public DtoBundle setTypeTask(int typeTask) {
        this.typeTask = typeTask;
        return this;
    }

    public long getIdUser() {
        return idUser;
    }

    public DtoBundle setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }

    public int getModule() {
        return module;
    }

    public DtoBundle setModule(int module) {
        this.module = module;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.idPDV);
        dest.writeLong(this.idReportLocal);
        dest.writeLong(this.idRutero);
        dest.writeLong(this.idCategory);
        dest.writeInt(this.typeTask);
        dest.writeLong(this.idUser);
        dest.writeInt(this.module);
    }

    public DtoBundle() {
    }

    protected DtoBundle(Parcel in) {
        this.idPDV = in.readLong();
        this.idReportLocal = in.readLong();
        this.idRutero = in.readLong();
        this.idCategory = in.readLong();
        this.typeTask = in.readInt();
        this.idUser = in.readLong();
        this.module = in.readInt();
    }

    public static final Parcelable.Creator<DtoBundle> CREATOR = new Parcelable.Creator<DtoBundle>() {
        @Override
        public DtoBundle createFromParcel(Parcel source) {
            return new DtoBundle(source);
        }

        @Override
        public DtoBundle[] newArray(int size) {
            return new DtoBundle[size];
        }
    };
}