package net.gshp.gepp.dto;

/**
 * Created by leo on 11/03/18.
 */

public class DtoModule {
    private long idTypePdv;
    private long idModule;

    public long getIdTypePdv() {
        return idTypePdv;
    }

    public DtoModule setIdTypePdv(long idTypePdv) {
        this.idTypePdv = idTypePdv;
        return this;
    }

    public long getIdModule() {
        return idModule;
    }

    public DtoModule setIdModule(long idModule) {
        this.idModule = idModule;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DtoModule dtoModule = (DtoModule) o;

        return idModule == dtoModule.idModule;

    }

    @Override
    public int hashCode() {
        return (int) (idModule ^ (idModule >>> 32));
    }

    @Override
    public String toString() {
        return "DtoModule{" +
                "idTypePdv=" + idTypePdv +
                ", idModule=" + idModule +
                '}';
    }
}
