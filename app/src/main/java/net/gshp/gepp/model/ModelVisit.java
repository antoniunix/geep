package net.gshp.gepp.model;

import net.gshp.gepp.adapter.RVVisit;
import net.gshp.gepp.dao.DaoReport;
import net.gshp.gepp.dto.DtoReportVisit;
import net.gshp.gepp.listener.OnItemClickListenerRV;

import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class ModelVisit {
    private List<DtoReportVisit> lstDtoReportVisits;
    private RVVisit adapter;
    private DaoReport daoReport;

    public ModelVisit() {
        daoReport=new DaoReport();
    }

    public RVVisit getAdapter(OnItemClickListenerRV onClickListener){
        lstDtoReportVisits=daoReport.SelectReportVisit();
        return adapter=new RVVisit(lstDtoReportVisits,onClickListener);
    }



    public DtoReportVisit getItemPosition(int position){
        return lstDtoReportVisits.get(position);
    }

    public void deleteVisit(long idReport){
        daoReport.deleteById(idReport);
    }
}
