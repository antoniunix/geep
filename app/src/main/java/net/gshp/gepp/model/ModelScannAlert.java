package net.gshp.gepp.model;

import android.support.v7.widget.RecyclerView;

import net.gshp.gepp.adapter.AdapterScannAlertV2;
import net.gshp.gepp.dao.DaoCStatusScannAlert;
import net.gshp.gepp.dao.DaoReportScannAlert;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportScannAlert;
import net.gshp.gepp.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class ModelScannAlert {
    private DaoReportScannAlert daoReportScannAlert;
    private DtoBundle dtoBundle;
    private List<DtoReportScannAlert> lstSkuScannAlert;
    private AdapterScannAlertV2 adapterScannAlertV2;

    public ModelScannAlert(DtoBundle dtoBundle) {
        this.dtoBundle = dtoBundle;
        daoReportScannAlert = new DaoReportScannAlert();
    }

    public RecyclerView.Adapter getAdapterV2(OnItemClickListener onItemClickListener) {
        lstSkuScannAlert = daoReportScannAlert.SelectScannAlert(dtoBundle);
        int flag = 0;

        for (int i = 0; i < lstSkuScannAlert.size(); i++) {
            if (flag != lstSkuScannAlert.get(i).getIdTp()) {
                flag = (int) lstSkuScannAlert.get(i).getIdTp();
                switch (flag) {
                    case 1:
                        lstSkuScannAlert.add(i, new DtoReportScannAlert().setIdTp(-1).setStatus(-1));
                        break;
                    case 2:
                        lstSkuScannAlert.add(i, new DtoReportScannAlert().setIdTp(-2).setStatus(-1));
                        break;
                    case 3:
                        lstSkuScannAlert.add(i, new DtoReportScannAlert().setIdTp(-3).setStatus(-1));
                        break;

                    default:
                        break;
                }
            }
        }


        adapterScannAlertV2 = new AdapterScannAlertV2(lstSkuScannAlert, onItemClickListener);
        return adapterScannAlertV2;
    }

    public DtoReportScannAlert getItemReportScannAlert(int position) {
        return lstSkuScannAlert.get(position);
    }

    public String getColorOfStatus(long id) {
        return new DaoCStatusScannAlert().SelectColor(id);
    }

    public int saveReport() {
        int status = -1;

        for (int i = 0; i < lstSkuScannAlert.size(); i++) {
            if (lstSkuScannAlert.get(i).getStatus() == 0) {
                lstSkuScannAlert.get(i).setStatus(-1);
            }
        }
        daoReportScannAlert.deleteById(dtoBundle.getIdReportLocal());
        daoReportScannAlert.Insert(lstSkuScannAlert);
        return status;
    }


}
