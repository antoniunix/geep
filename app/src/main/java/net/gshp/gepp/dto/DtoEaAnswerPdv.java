package net.gshp.gepp.dto;

/**
 * Created by leo on 12/03/18.
 */

public class DtoEaAnswerPdv {
    private long id_pdv;
    private long id_poll;

    public long getId_pdv() {
        return id_pdv;
    }

    public DtoEaAnswerPdv setId_pdv(long id_pdv) {
        this.id_pdv = id_pdv;
        return this;
    }

    public long getId_poll() {
        return id_poll;
    }

    public DtoEaAnswerPdv setId_poll(long id_poll) {
        this.id_poll = id_poll;
        return this;
    }
}
