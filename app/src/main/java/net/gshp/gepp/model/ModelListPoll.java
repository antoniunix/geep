package net.gshp.gepp.model;

import android.content.Context;

import net.gshp.gepp.adapter.RVPoll;
import net.gshp.gepp.dao.DaoEAEncuesta;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoEAEncuesta;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.listener.OnItemClickListenerRV;

import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class ModelListPoll {
    private DaoEAEncuesta daoeaEncuesta;
    private List<DtoEAEncuesta> listEncuesta;
    private Context context;
    private DtoBundle dtoBundle;

    public ModelListPoll(Context context, DtoBundle dtoBundle) {
        this.context = context;
        this.dtoBundle = dtoBundle;
        daoeaEncuesta = new DaoEAEncuesta();
    }

    public RVPoll getAdapter(OnItemClickListenerRV onItemClickListenerRV) {
        DtoPdv dtoPdvPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        listEncuesta = daoeaEncuesta.selectTipoEncuestas(dtoBundle.getIdReportLocal(), dtoPdvPdv.getIdCanal(), dtoPdvPdv.getIdClient(),
                dtoBundle.getIdPDV(), dtoPdvPdv.getIdRtm(), dtoPdvPdv.getIdRegion());
        return new RVPoll(listEncuesta, onItemClickListenerRV);
    }

    public DtoEAEncuesta getItem(int position) {
        return listEncuesta.get(position);
    }

}
