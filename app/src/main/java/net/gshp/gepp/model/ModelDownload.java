package net.gshp.gepp.model;

import android.view.View;
import android.widget.ListAdapter;

import net.gshp.gepp.adapter.AdapterDownload;
import net.gshp.gepp.dao.DaoDownloadDetail;
import net.gshp.gepp.dao.DaoMeasurementDownloadSku;
import net.gshp.gepp.dto.DtoDownLoadDetail;
import net.gshp.gepp.dto.DtoMeasurementDownloadSku;

import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class ModelDownload {
    private DaoMeasurementDownloadSku daoMeasurementDownloadSku;
    private List<DtoMeasurementDownloadSku> lst;
    private AdapterDownload adapterDownload;
    private View.OnClickListener onClick;
    private DaoDownloadDetail daoDownloadDetail;

    public ModelDownload(View.OnClickListener onClick) {
        this.onClick = onClick;
        daoMeasurementDownloadSku = new DaoMeasurementDownloadSku();
        daoDownloadDetail = new DaoDownloadDetail();
    }

    public ModelDownload() {
        daoMeasurementDownloadSku = new DaoMeasurementDownloadSku();
        daoDownloadDetail = new DaoDownloadDetail();
    }

    public ListAdapter getAdapter() {
        lst = daoMeasurementDownloadSku.selectDodwnload();
        adapterDownload = new AdapterDownload(lst, onClick, this);
        return adapterDownload;
    }

    public void saveFileDetail(DtoDownLoadDetail dto) {
        daoDownloadDetail.Insert(dto);
    }
}
