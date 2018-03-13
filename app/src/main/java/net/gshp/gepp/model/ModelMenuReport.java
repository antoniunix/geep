package net.gshp.gepp.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVMenuReport;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoEAEncuesta;
import net.gshp.gepp.dao.DaoModule;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoReport;
import net.gshp.gepp.dao.DaoReportCheck;
import net.gshp.gepp.dao.DaoReportExhibitionMantained;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoCheckIsPoll;
import net.gshp.gepp.dto.DtoEAEncuesta;
import net.gshp.gepp.dto.DtoMeasurementModule;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReport;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.util.Config;

import java.util.Iterator;
import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class ModelMenuReport {

    private List<DtoMeasurementModule> lstDtoModules;
    private DaoModule daoModule;
    private DaoReport daoReport;
    private DtoPdv dtoPdvPdv;
    private DtoBundle dtoBundle;
    private RVMenuReport adapter;
    private Context context;


    public ModelMenuReport(DtoBundle dtoBundle, Context context) {
        this.dtoBundle = dtoBundle;
        this.context = context;
        daoModule = new DaoModule();
        dtoPdvPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        addModule();
    }

    private void addModule() {
        lstDtoModules = daoModule.selectModule(dtoPdvPdv);
        DtoMeasurementModule dtoMeasurementModule = new DtoMeasurementModule();
        dtoMeasurementModule = new DtoMeasurementModule();
        dtoMeasurementModule.setIdItemRelation(ContextApp.context.getResources().getInteger(R.integer.PRE_CHECK_OUT));
        dtoMeasurementModule.setValue("Fin de la Jornada");
        lstDtoModules.add(dtoMeasurementModule);
    }

    public void setDtoBundle(DtoBundle dtoBundle) {
        this.dtoBundle = dtoBundle;
    }

    public RVMenuReport getAdapter(OnItemClickListenerRV onItemClickListenerRV) {

        Iterator<DtoMeasurementModule> iter = lstDtoModules.iterator();


        while (iter.hasNext()) {
            DtoMeasurementModule dto = iter.next();
            switch ((int) dto.getIdItemRelation()) {
                case Config.ENCUESTA:
                    if (isReportPoll() == context.getResources().getInteger(R.integer.statusModuleReportWithOut)) {
                        iter.remove();
                    }
                    break;

            }
        }

        return adapter = new RVMenuReport(lstDtoModules, onItemClickListenerRV, this);
    }

    public int isReportPoll() {
        DaoEAEncuesta dao = new DaoEAEncuesta();
        DtoPdv dtoPdvPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        boolean pollForcedIncomplete = false;
        boolean pollObligatedIncomplete = false;
        List<DtoCheckIsPoll> lstisPollForced = dao.selectIsPollComplete(dtoBundle, dtoPdvPdv, 1);
        List<DtoCheckIsPoll> lstisPollObligate = dao.selectIsPollComplete(dtoBundle, dtoPdvPdv, 2);
        for (DtoCheckIsPoll dto : lstisPollObligate) {
            if (dto.getPreguntas() > dto.getRespuestas()) {
                pollObligatedIncomplete = true;
                continue;
            }
        }
        for (DtoCheckIsPoll dto : lstisPollForced) {
            if (dto.getPreguntas() > dto.getRespuestas()) {
                pollForcedIncomplete = true;
                continue;
            }
        }

        List<DtoEAEncuesta> listEncuesta = dao.selectTipoEncuestas(dtoBundle.getIdReportLocal(), dtoPdvPdv.getIdCanal(), dtoPdvPdv.getIdClient(),
                dtoBundle.getIdPDV(), dtoPdvPdv.getIdRtm(), dtoPdvPdv.getIdRegion());
        Log.e("leo", "obligatorio " + pollObligatedIncomplete + " forced " + pollForcedIncomplete);

        if (listEncuesta.isEmpty()
                || !new DaoModule().existModule(ContextApp.context.getResources().getInteger(R.integer.menu_report_encuesta), dtoPdvPdv)) {
            return context.getResources().getInteger(R.integer.statusModuleReportWithOut);
        } else if (!pollObligatedIncomplete && !pollForcedIncomplete) {
            return context.getResources().getInteger(R.integer.statusModuleReportDone);
        } else if (pollForcedIncomplete) {
            return context.getResources().getInteger(R.integer.statusModuleReportIncompleteForced);
        } else {
            return context.getResources().getInteger(R.integer.statusModuleReportNotDone);
        }
    }

    public int isReportExhibition(){
        DaoReportExhibitionMantained daoReportExhibition= new DaoReportExhibitionMantained();
        if (daoReportExhibition.isReport(dtoBundle.getIdReportLocal()) >= daoReportExhibition.select(dtoBundle, dtoPdvPdv).size()) {
            Log.e("exit", "Exhibition Completo en este visita");
            return context.getResources().getInteger(R.integer.statusModuleReportDone);
        } else {
            Log.e("exit", "No lo completo");
            return context.getResources().getInteger(R.integer.statusModuleReportIncomplete);
        }

    }

    public boolean isCheck(int idType) {
        Log.e("ischeck", "dto " + dtoBundle.getIdReportLocal() + " metod " + new DaoReportCheck().isCheck(dtoBundle.getIdReportLocal(), idType));
        return new DaoReportCheck().isCheck(dtoBundle.getIdReportLocal(), idType);
    }

    public DtoMeasurementModule getItemModule(int position) {
        return lstDtoModules.get(position);
    }

    public String getListMissingReport() {
        String listMissingReport = "";

        if (isReportPoll() == context.getResources().getInteger(R.integer.statusModuleReportNotDone)) {
            listMissingReport = listMissingReport.concat("ENCUESTAS\n");
        }
        if (isReportPoll() == context.getResources().getInteger(R.integer.statusModuleReportIncompleteForced)) {
            listMissingReport = listMissingReport.concat("ENCUESTAS FORZOSAS\n");
        }

        if (listMissingReport.length() > 0) {
            listMissingReport = "LE FALTA RESPONDER:\n".concat(listMissingReport);
        }
        return listMissingReport;
    }

    public void updateTypeReport(DtoCatalog dtoCatalog) {
        new DaoReport().updateTypeReport(dtoBundle, dtoCatalog);
    }

    public void addReport() {
        String version = "";
        try {
            version = ContextApp.context.getPackageManager().getPackageInfo(ContextApp.context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DtoReport dtoReport = new DtoReport();
        dtoReport.setIdPdv(dtoBundle.getIdPDV())
                .setIdSchedule(dtoBundle.getIdRutero())//Al no existir rutero se setea 1
                .setVersion(version)
                .setDate(String.valueOf(System.currentTimeMillis()))
                .setTz(Config.getTimeZone())
                .setImei(Config.getIMEI())
                .setHash(Crypto.MD5(System.currentTimeMillis() + " " + Math.random()))
                .setSend(0)
                .setTypeReport(1)
                .setIdReportServer(0)
                .setDateInactive(String.valueOf(0))
                .setActive(1);
        dtoBundle.setIdReportLocal((int) new DaoReport().insert(dtoReport));
    }

}
