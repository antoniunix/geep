package net.gshp.gepp.model;

import net.gshp.gepp.adapter.RVTypeReport;
import net.gshp.gepp.dao.DaoCTypeReport;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.listener.OnItemClickListenerRV;

import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class ModelTypeReport {
    private List<DtoCatalog> lstCatalog;
    private RVTypeReport adapter;

    public ModelTypeReport() {
    }

    public RVTypeReport getAdapter(OnItemClickListenerRV onItemClickListenerRV){
        lstCatalog=new DaoCTypeReport().select(0);
        return adapter=new RVTypeReport(lstCatalog,onItemClickListenerRV);
    }

    public List<DtoCatalog> getListItems(){
        return lstCatalog;
    }

    public DtoCatalog getListItems(int position){
        return lstCatalog.get(position);
    }
}
