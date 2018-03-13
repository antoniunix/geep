package net.gshp.gepp.model;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoCStatusScannAlert;
import net.gshp.gepp.dto.DtoCStatusScann;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class ModelDialogStatusScannAlert {
    private DaoCStatusScannAlert daoCStatusScannAlert;
    private List<DtoCStatusScann> lstCatalogs;
    private List<String> lstStatus;

    public ModelDialogStatusScannAlert() {
        daoCStatusScannAlert=new DaoCStatusScannAlert();
    }

    public ListAdapter getAdapterStatus(long idTypeProblem){
        lstCatalogs=daoCStatusScannAlert.SelectByProblem(idTypeProblem);
        lstStatus=new ArrayList<String>(lstCatalogs.size());
        for (DtoCStatusScann dto : lstCatalogs) {
            lstStatus.add(dto.getValue());
        }
        return new ArrayAdapter<String>(ContextApp.context, R.layout.row_status_scann,lstStatus);
    }

    public DtoCStatusScann getItem(int position){
        return lstCatalogs.get(position);
    }




}
