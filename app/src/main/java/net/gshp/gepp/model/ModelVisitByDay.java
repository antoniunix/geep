package net.gshp.gepp.model;

import net.gshp.gepp.adapter.RVVisitByDay;
import net.gshp.gepp.dao.DaoAgenda;
import net.gshp.gepp.dto.DtoSchedule;
import net.gshp.gepp.listener.OnItemClickListenerRV;

import java.util.List;

/**
 * Created by leo on 8/03/18.
 */

public class ModelVisitByDay {

    private List<DtoSchedule> lstDtoSchedules;
    private RVVisitByDay adapter;
    private DaoAgenda daoAgenda;

    public ModelVisitByDay() {
        daoAgenda = new DaoAgenda();
    }

    public RVVisitByDay getAdapter(OnItemClickListenerRV onItemClickListenerRV, long idUser) {
        lstDtoSchedules = daoAgenda.select(idUser);
        return adapter = new RVVisitByDay(lstDtoSchedules, onItemClickListenerRV);
    }

    public int isSomeReportIncomplete() {
        return daoAgenda.isSomeReportIncomplete();
    }

    public boolean isMakeTodayThisSchedule(long schedule) {
        return daoAgenda.isMakeTodayThisSchedule(schedule);
    }

}
