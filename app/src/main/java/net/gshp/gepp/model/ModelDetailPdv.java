package net.gshp.gepp.model;

import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dto.DtoPdv;

/**
 * Created by gnu on 14/03/18.
 */

public class ModelDetailPdv {

    public DtoPdv getPdv(long id){
        return new DaoPdv().SelectById(id);
    }
}
