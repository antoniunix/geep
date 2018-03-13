package net.gshp.gepp.model;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.gshp.gepp.adapter.AdapterExhibicion;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoReportExhibitionMantained;
import net.gshp.gepp.dao.DaoReportExhibitionMantainedCause;
import net.gshp.gepp.dao.DaoReportExhibitionMantainedPhoto;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;
import net.gshp.gepp.dto.DtoReportExhibitionMantainedPhoto;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class ModelExhibitions {

    private DtoBundle dtoBundle;
    private DtoPdv dtoPdv;
    private AdapterExhibicion adapterExhibicion;
    private List<DtoReportExhibitionMantained> lstDtoReportExhibitionMantaineds;
    private List<DtoReportExhibitionMantained> lstDtoReportExhibitionMantainedstmp;
    private DaoReportExhibitionMantained daoReportExhibitionMantained;
    private Activity activity;

    public ModelExhibitions(DtoBundle dtoBundle, Activity activity) {
        this.activity = activity;
        this.dtoBundle = dtoBundle;
        dtoPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        daoReportExhibitionMantained = new DaoReportExhibitionMantained();
    }

    public AdapterExhibicion getAdapterExhibition(View.OnClickListener onClickListener) {
        lstDtoReportExhibitionMantaineds = daoReportExhibitionMantained.select(dtoBundle, dtoPdv);
        Log.e("lst", "getAdapter " + lstDtoReportExhibitionMantaineds.size());
        lstDtoReportExhibitionMantainedstmp = daoReportExhibitionMantained.selectTemp(dtoBundle);

        int flag = 0;
        for (int i = 0; i < lstDtoReportExhibitionMantaineds.size(); i++) {
            if (flag != lstDtoReportExhibitionMantaineds.get(i).getId_exhibition_group()) {
                flag = lstDtoReportExhibitionMantaineds.get(i).getId_exhibition_group();
                switch (flag) {
                    case 1:
                        lstDtoReportExhibitionMantaineds.add(i, new DtoReportExhibitionMantained().setId_exhibition_group(-1));
                        break;
                    case 2:
                        lstDtoReportExhibitionMantaineds.add(i, new DtoReportExhibitionMantained().setId_exhibition_group(-2));
                        break;
                    case 3:
                        lstDtoReportExhibitionMantaineds.add(i, new DtoReportExhibitionMantained().setId_exhibition_group(-3));
                        break;
                }
            }
        }
        for (int i = 0; i < lstDtoReportExhibitionMantainedstmp.size(); i++) {
            if (!lstDtoReportExhibitionMantaineds.contains(lstDtoReportExhibitionMantainedstmp.get(i))) {
                lstDtoReportExhibitionMantaineds.add(lstDtoReportExhibitionMantainedstmp.get(i));
            }
        }

        return adapterExhibicion = new AdapterExhibicion(lstDtoReportExhibitionMantaineds, onClickListener);
    }


    public DtoReportExhibitionMantained getItemExhibition(int position) {
        return lstDtoReportExhibitionMantaineds.get(position);
    }

    public void addPhotoExhibition(int position, String path) {

        DtoReportExhibitionMantainedPhoto dtoPhoto = new DtoReportExhibitionMantainedPhoto();
        DtoReportExhibitionMantained dto = lstDtoReportExhibitionMantaineds.get(position);
        dtoPhoto.setPath(path).setHashExhibitionCatalog(dto.getHashExhibition());
        new DaoReportExhibitionMantainedPhoto().insertOneRow(dtoPhoto, dtoBundle);
        dto.setPhotoDone(dto.getPhotoDone() + 1);
        adapterExhibicion.notifyDataSetChanged();
    }

    public int saveExhibition() {
        int status = -1;
        Log.e("lst", "size in saveExhibition " + lstDtoReportExhibitionMantaineds.size());
        for (int i = 0; i < lstDtoReportExhibitionMantaineds.size(); i++) {
            DtoReportExhibitionMantained dto = lstDtoReportExhibitionMantaineds.get(i);
            if (dto.getIsExhibit() == 1 && dto.getPhotoDone() < dto.getMin_photos()) {
                Toast.makeText(activity, "Debe capturar las fotografías en: \n" + dto.getExhibition_name()
                        , Toast.LENGTH_SHORT).show();
                return i;
            } else if ((dto.getIsExhibit() == 1 || dto.getIsExhibit() == 0) && dto.getPhotoDone() >= dto.getMin_photos()) {
                new DaoReportExhibitionMantainedCause().deleteById(dtoBundle.getIdReportLocal(), dto.getHashExhibition());
            }
        }

        daoReportExhibitionMantained.deleteById(dtoBundle.getIdReportLocal());
        daoReportExhibitionMantained.insert(lstDtoReportExhibitionMantaineds, dtoBundle);
        return status;

    }

    public void removeItemAdapter(DtoReportExhibitionMantained dto) {
        lstDtoReportExhibitionMantaineds.remove(dto);
        adapterExhibicion.notifyDataSetChanged();
    }


}
