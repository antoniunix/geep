package net.gshp.gepp.model;

import net.gshp.gepp.adapter.RVFilterSchedule;
import net.gshp.gepp.dao.DaoSubordinate;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.listener.OnItemClickListenerRV;

import java.util.List;

/**
 * Created by leo on 9/03/18.
 */

public class ModelFilterSchedule {

    private List<DtoCatalog> lst;
    private DaoSubordinate daoSubordinate;
    private RVFilterSchedule adapter;

    public ModelFilterSchedule() {
        daoSubordinate = new DaoSubordinate();
    }

    public RVFilterSchedule getAdapter(OnItemClickListenerRV onItemClickListenerRV) {
        lst = daoSubordinate.selectUserSchedule();

        return adapter = new RVFilterSchedule(lst, onItemClickListenerRV);
    }


}
