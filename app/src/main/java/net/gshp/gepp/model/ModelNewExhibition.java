package net.gshp.gepp.model;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoReportExhibitionDetail;
import net.gshp.gepp.dao.DaoReportHeadExhibition;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportExhibitionDetail;
import net.gshp.gepp.dto.DtoReportHeadExhibition;
import net.gshp.gepp.dto.DtoTypeExhibition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class ModelNewExhibition {
    private DaoReportHeadExhibition daoReportHeadExhibition;
    private DaoReportExhibitionDetail daoReportExhibitionDetail;
    private List<DtoTypeExhibition> lstDtoExhibitionManufacturer;
    private List<String> lstStringManufacturer;
    private List<DtoTypeExhibition> lstDtoExhibitionCategory;
    private List<String> lstStringCategory;
    private List<DtoTypeExhibition> lstDtoExhibitionFamily;
    private List<String> lstStringFamily;
    private List<DtoTypeExhibition> lstDtoExhibitionSubFamily;
    private List<String> lstStringSubFamily;
    private List<DtoTypeExhibition> lstDtoExhibitionType;
    private List<String> lstStringType;
    private List<DtoTypeExhibition> lstDtoExhibitionLocation;
    private List<String> lstStringLocation;
    private List<DtoTypeExhibition> lstDtoExhibitionGroup;
    private List<String> lstStringGroup;
    private List<DtoTypeExhibition> lstDtoExhibitionDepartament;
    private List<String> lstStrinfDepartament;
    private DaoPdv daopdv;
    private DtoPdv dtoPdv;
    private DtoBundle dtoBundle;

    public ModelNewExhibition(DtoBundle dtoBundle) {
        this.dtoBundle = dtoBundle;
        daoReportHeadExhibition = new DaoReportHeadExhibition();
        daoReportExhibitionDetail = new DaoReportExhibitionDetail();
        daopdv = new DaoPdv();
        dtoPdv = daopdv.SelectById(dtoBundle.getIdPDV());
    }

    public SpinnerAdapter getItemAdapterManufacturer() {
        lstDtoExhibitionManufacturer = daoReportHeadExhibition.SelectManufacturer(dtoPdv);
        lstStringManufacturer = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionManufacturer) {
            lstStringManufacturer.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringManufacturer);
    }

    public SpinnerAdapter getItemAdapterCategory(int idManufacturer) {
        lstDtoExhibitionCategory = daoReportHeadExhibition.SelectCategory(dtoPdv, idManufacturer);
        lstStringCategory = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionCategory) {
            lstStringCategory.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringCategory);
    }

    public SpinnerAdapter getItemAdapterGroup() {
        lstDtoExhibitionGroup = daoReportHeadExhibition.SelectGroup(dtoPdv);
        lstStringGroup = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionGroup) {
            lstStringGroup.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringGroup);
    }

    public SpinnerAdapter getItemAdapterDepartament(int idFamily) {
        lstDtoExhibitionDepartament = daoReportHeadExhibition.SelectDepartament(dtoPdv, idFamily);
        lstStrinfDepartament = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionDepartament) {
            lstStrinfDepartament.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStrinfDepartament);
    }

    public SpinnerAdapter getItemAdapterFamily(int idCategory) {
        lstDtoExhibitionFamily = daoReportHeadExhibition.SelectFamily(dtoPdv, idCategory);
        lstStringFamily = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionFamily) {
            lstStringFamily.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringFamily);
    }

    public SpinnerAdapter getItemAdapterSubFamily(int idFamily) {
        lstDtoExhibitionSubFamily = daoReportHeadExhibition.SelectSubFamily(dtoPdv, idFamily);
        lstStringSubFamily = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionSubFamily) {
            lstStringSubFamily.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringSubFamily);
    }


    public SpinnerAdapter getItemAdapterType() {
        lstDtoExhibitionType = daoReportHeadExhibition.SelectType(dtoPdv);
        lstStringType = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionType) {
            lstStringType.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringType);
    }

    public SpinnerAdapter getItemAdapterLocation(int idGroup) {
        lstDtoExhibitionLocation = daoReportHeadExhibition.SelectLocation(dtoPdv, idGroup);
        lstStringLocation = new ArrayList<>();
        for (DtoTypeExhibition dto : lstDtoExhibitionLocation) {
            lstStringLocation.add(dto.getValue());
        }
        return new ArrayAdapter<>(ContextApp.context, R.layout.spinner_simple_list, lstStringLocation);
    }


    public DtoTypeExhibition getItemPositionManufacturer(int position) {
        return lstDtoExhibitionManufacturer.get(position);
    }

    public DtoTypeExhibition getItemPositionCategory(int position) {
        return lstDtoExhibitionCategory.get(position);
    }

    public DtoTypeExhibition getItemPositionFamily(int position) {
        return lstDtoExhibitionFamily.get(position);
    }

    public DtoTypeExhibition getItemPositionGroup(int position) {
        return lstDtoExhibitionGroup.get(position);
    }

    public DtoTypeExhibition getItemPositionDepartament(int position) {
        return lstDtoExhibitionDepartament.get(position);
    }


    public DtoTypeExhibition getItemPositionSubFamily(int position) {
        return lstDtoExhibitionSubFamily.get(position);
    }

    public DtoTypeExhibition getItemPositionType(int position) {
        return lstDtoExhibitionType.get(position);
    }

    public DtoTypeExhibition getItemPositionLocation(int position) {
        return lstDtoExhibitionLocation.get(position);
    }

    public void saveReport(DtoReportHeadExhibition dtoHead, DtoReportExhibitionDetail dtoDetail) {
        daoReportHeadExhibition.Insert(dtoHead, dtoBundle.getIdReportLocal());
        daoReportExhibitionDetail.Insert(dtoDetail, dtoBundle.getIdReportLocal());
    }

}
