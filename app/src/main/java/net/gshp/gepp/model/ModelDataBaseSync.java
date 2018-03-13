package net.gshp.gepp.model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoAgenda;
import net.gshp.gepp.dao.DaoCCategory;
import net.gshp.gepp.dao.DaoCExhibition;
import net.gshp.gepp.dao.DaoCProblemScann;
import net.gshp.gepp.dao.DaoCStatusScannAlert;
import net.gshp.gepp.dao.DaoCTypeCatalogExhibition;
import net.gshp.gepp.dao.DaoCTypeReport;
import net.gshp.gepp.dao.DaoCanal;
import net.gshp.gepp.dao.DaoCliente;
import net.gshp.gepp.dao.DaoEAEncuesta;
import net.gshp.gepp.dao.DaoEAOpcionPregunta;
import net.gshp.gepp.dao.DaoEAPregunta;
import net.gshp.gepp.dao.DaoEASeccion;
import net.gshp.gepp.dao.DaoEATypeOpcionRespuesta;
import net.gshp.gepp.dao.DaoEaAnswerPdv;
import net.gshp.gepp.dao.DaoMeasurementCauseExhibition;
import net.gshp.gepp.dao.DaoMeasurementDownloadSku;
import net.gshp.gepp.dao.DaoMeasurementFilter;
import net.gshp.gepp.dao.DaoMeasurementHead;
import net.gshp.gepp.dao.DaoMeasurementItemExhibition;
import net.gshp.gepp.dao.DaoMeasurementModule;
import net.gshp.gepp.dao.DaoMessage;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoRtm;
import net.gshp.gepp.dao.DaoScannAlert;
import net.gshp.gepp.dao.DaoTypeExhibition;
import net.gshp.gepp.dto.DtoCExhibition;
import net.gshp.gepp.dto.DtoCStatusScann;
import net.gshp.gepp.dto.DtoCTypeCatalogExhibition;
import net.gshp.gepp.dto.DtoEAOpcionPregunta;
import net.gshp.gepp.dto.DtoEASeccion;
import net.gshp.gepp.dto.DtoEATipoPregunta;
import net.gshp.gepp.dto.DtoAgenda;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoEAEncuesta;
import net.gshp.gepp.dto.DtoEAPregunta;
import net.gshp.gepp.dto.DtoEaAnswerPdv;
import net.gshp.gepp.dto.DtoMeasurementDownloadSku;
import net.gshp.gepp.dto.DtoMeasurementFilter;
import net.gshp.gepp.dto.DtoMeasurementHead;
import net.gshp.gepp.dto.DtoMeasurementItemExhibition;
import net.gshp.gepp.dto.DtoMeasurementModule;
import net.gshp.gepp.dto.DtoMessage;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoRtm;
import net.gshp.gepp.dto.DtoScannAlert;
import net.gshp.gepp.dto.DtoTypeExhibition;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by leo on 12/03/18.
 */

public class ModelDataBaseSync {

    private final static String TAG = "ModelDataBaseSync";
    private int STORAGE_OK = 1;
    private Handler handlerstorage;
    private ExecutorService executor;

    public ModelDataBaseSync(Handler handlerstorage) {
        executor = Executors.newSingleThreadExecutor();
        this.handlerstorage = handlerstorage;
    }

    protected void syncInsertion(final NetworkTask nt) {
        Runnable command = new Runnable() {

            @Override
            public void run() {
                Type typeObjectGson;
                STORAGE_OK = 1;
                try {

                    if (nt.getTag().equals("pdv_pdv")) {

                        Log.d("SYNC", "pdv_pdv " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoPdv>>() {
                        }.getType();

                        List<DtoPdv> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoPdv().delete();
                        new DaoPdv().insert(lst, ContextApp.context.getResources().getInteger(R.integer.idRolSupervisor));
                    } else if (nt.getTag().equals("schedule")) {
                        Log.d("SYNC", "schedule " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoAgenda>>() {
                        }.getType();

                        List<DtoAgenda> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoAgenda().delete();
                        new DaoAgenda().insert(lst, ContextApp.context.getResources().getInteger(R.integer.idRolMercaderista));
                    } else if (nt.getTag().equals("c_client")) {
                        Log.d("SYNC", "c_client " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCatalog>>() {
                        }.getType();

                        List<DtoCatalog> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoCliente().delete();
                        new DaoCliente().insert(lst);
                    } else if (nt.getTag().equals("c_rtm")) {
                        Log.d("SYNC", "c_rtm " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoRtm>>() {
                        }.getType();

                        List<DtoRtm> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoRtm().delete();
                        new DaoRtm().insert(lst);
                    } else if (nt.getTag().equals("c_canal")) {
                        Log.d("SYNC", "c_canal " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCatalog>>() {
                        }.getType();

                        List<DtoCatalog> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoCanal().delete();
                        new DaoCanal().insert(lst);
                    } else if (nt.getTag().equals("c_type_report")) {
                        Log.d("SYNC", "c_type_report " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCatalog>>() {
                        }.getType();
                        List<DtoCatalog> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoCTypeReport().delete();
                        new DaoCTypeReport().Insert(lst);
                    } else if (nt.getTag().equals("mam_app_module")) {
                        Log.d(TAG, "inserting into measurement_module_head " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementHead>>() {
                        }.getType();
                        List<DtoMeasurementHead> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementHead("measurement_module_head").delete();
                        new DaoMeasurementHead("measurement_module_head").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_module")) {
                        Log.d(TAG, "inserting into measurement_module " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementModule>>() {
                        }.getType();
                        List<DtoMeasurementModule> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementModule().delete();
                        new DaoMeasurementModule().Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_canal")) {
                        Log.d(TAG, "inserting into measurement_module_canal " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_canal").delete();
                        new DaoMeasurementFilter("measurement_module_canal").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_client")) {
                        Log.d(TAG, "inserting into measurement_module_client " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_client").delete();
                        new DaoMeasurementFilter("measurement_module_client").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_format")) {
                        Log.d(TAG, "inserting into measurement_module_format " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_format").delete();
                        new DaoMeasurementFilter("measurement_module_format").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_pdv")) {
                        Log.d(TAG, "inserting into measurement_module_pdv " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_pdv").delete();
                        new DaoMeasurementFilter("measurement_module_pdv").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_rtm")) {
                        Log.d(TAG, "inserting into measurement_module_rtm " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_rtm").delete();
                        new DaoMeasurementFilter("measurement_module_rtm").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_region")) {
                        Log.d(TAG, "inserting into measurement_module_region " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_region").delete();
                        new DaoMeasurementFilter("measurement_module_region").Insert(lstCatalog);
                    } else if (nt.getTag().equals("ea_poll")) {
                        Log.d(TAG, "inserting into ea_poll " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEAEncuesta>>() {
                        }.getType();
                        List<DtoEAEncuesta> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEAEncuesta().delete();
                        new DaoEAEncuesta().Insert(lstCatalog);
                    } else if (nt.getTag().equals("ea_question")) {
                        Log.d(TAG, "inserting into ea_question " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEAPregunta>>() {
                        }.getType();
                        List<DtoEAPregunta> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEAPregunta().delete();
                        new DaoEAPregunta().Insert(lstCatalog);
                    } else if (nt.getTag().equals("ea_question_type_cat")) {
                        Log.d(TAG, "inserting into ea_question_type_cat " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEATipoPregunta>>() {
                        }.getType();
                        List<DtoEATipoPregunta> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEATypeOpcionRespuesta().delete();
                        new DaoEATypeOpcionRespuesta().Insert(lstCatalog);
                    } else if (nt.getTag().equals("ea_question_option")) {
                        Log.d(TAG, "inserting into ea_question_option " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEAOpcionPregunta>>() {
                        }.getType();
                        List<DtoEAOpcionPregunta> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEAOpcionPregunta().delete();
                        new DaoEAOpcionPregunta().Insert(lstCatalog);
                    } else if (nt.getTag().equals("ea_section")) {
                        Log.d(TAG, "inserting into ea_section " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEASeccion>>() {
                        }.getType();
                        List<DtoEASeccion> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEASeccion().delete();
                        new DaoEASeccion().insertAllSeccion(lstCatalog);
                    } else if (nt.getTag().equals("ea_answers_pdv")) {
                        Log.d(TAG, "inserting into ea_answers_pdv " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoEaAnswerPdv>>() {
                        }.getType();
                        List<DtoEaAnswerPdv> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoEaAnswerPdv().delete();
                        new DaoEaAnswerPdv().Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_app_module")) {
                        Log.d(TAG, "inserting into measurement_module_head " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementHead>>() {
                        }.getType();
                        List<DtoMeasurementHead> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementHead("measurement_module_head").delete();
                        new DaoMeasurementHead("measurement_module_head").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_module")) {
                        Log.d(TAG, "inserting into measurement_module " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementModule>>() {
                        }.getType();
                        List<DtoMeasurementModule> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementModule().delete();
                        new DaoMeasurementModule().Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_canal")) {
                        Log.d(TAG, "inserting into measurement_module_canal " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_canal").delete();
                        new DaoMeasurementFilter("measurement_module_canal").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_client")) {
                        Log.d(TAG, "inserting into measurement_module_client " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_client").delete();
                        new DaoMeasurementFilter("measurement_module_client").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_format")) {
                        Log.d(TAG, "inserting into measurement_module_format " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_format").delete();
                        new DaoMeasurementFilter("measurement_module_format").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_pdv")) {
                        Log.d(TAG, "inserting into measurement_module_pdv " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_pdv").delete();
                        new DaoMeasurementFilter("measurement_module_pdv").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_rtm")) {
                        Log.d(TAG, "inserting into measurement_module_rtm " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_rtm").delete();
                        new DaoMeasurementFilter("measurement_module_rtm").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mam_region")) {
                        Log.d(TAG, "inserting into measurement_module_region " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoMeasurementFilter("measurement_module_region").delete();
                        new DaoMeasurementFilter("measurement_module_region").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mdf_downloadable_files")) {
                        Log.d(TAG, "inserting into mdf_downloadable_files " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementHead>>() {
                        }.getType();
                        List<DtoMeasurementHead> lstCatalog = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        new DaoMeasurementHead("measurement_download").delete();
                        new DaoMeasurementHead("measurement_download").Insert(lstCatalog);
                    } else if (nt.getTag().equals("mdf_file")) {
                        Log.d(TAG, "inserting into mdf_file " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementDownloadSku>>() {
                        }.getType();
                        List<DtoMeasurementDownloadSku> lstCatalog = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        new DaoMeasurementDownloadSku().delete();
                        new DaoMeasurementDownloadSku().Insert(lstCatalog);
                    } else if (nt.getTag().equals("messages_service")) {
                        Log.d(TAG, "inserting into c_subtype_photo " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMessage>>() {
                        }.getType();
                        List<DtoMessage> lstCatalog = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        new DaoMessage().delete();
                        new DaoMessage().Insert(lstCatalog);
                    } else if (nt.getTag().equals("scann_alert")) {
                        Log.d("SYNC", "scann_alert" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoScannAlert>>() {
                        }.getType();
                        List<DtoScannAlert> lst;
                        lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        DaoScannAlert dao = new DaoScannAlert();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("c_status_scann")) {
                        Log.d("SYNC", "c_status_scann" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCStatusScann>>() {
                        }.getType();
                        List<DtoCStatusScann> lst;
                        lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        DaoCStatusScannAlert dao = new DaoCStatusScannAlert();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("c_problem_scann")) {
                        Log.d("SYNC", "c_problem_scann" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCStatusScann>>() {
                        }.getType();
                        List<DtoCStatusScann> lst;
                        lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        DaoCProblemScann dao = new DaoCProblemScann();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("schedule")) {
                        Log.d("SYNC", "schedule " + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoAgenda>>() {
                        }.getType();

                        List<DtoAgenda> lst = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoAgenda().delete(ContextApp.context.getResources().getInteger(R.integer.idRolMercaderista));
                        new DaoAgenda().insert(lst, ContextApp.context.getResources().getInteger(R.integer.idRolMercaderista));
                    } else if (nt.getTag().equals("c_category")) {
                        Log.d("SYNC", "c_category" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCatalog>>() {
                        }.getType();
                        List<DtoCatalog> lstCatalog = new Gson().fromJson(nt.getResponse(),
                                typeObjectGson);
                        new DaoCCategory().delete();
                        new DaoCCategory().Insert(lstCatalog);
                    } else if (nt.getTag().equals("c_exhibition")) {
                        Log.d("SYNC", "c_exhibition" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCExhibition>>() {
                        }.getType();
                        List<DtoCExhibition> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoCExhibition dao = new DaoCExhibition();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_exhibitions")) {
                        Log.d("SYNC", "mnx_exhibitions" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementHead>>() {
                        }.getType();
                        List<DtoMeasurementHead> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementHead dao = new DaoMeasurementHead("measurement_head_exhibition");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("c_type_catalog")) {
                        Log.d("SYNC", "c_type_catalog" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoCTypeCatalogExhibition>>() {
                        }.getType();
                        List<DtoCTypeCatalogExhibition> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoCTypeCatalogExhibition dao = new DaoCTypeCatalogExhibition();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_items")) {
                        Log.d("SYNC", "mnx_items" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementItemExhibition>>() {
                        }.getType();
                        List<DtoMeasurementItemExhibition> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementItemExhibition dao = new DaoMeasurementItemExhibition();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_type")) {
                        Log.d("SYNC", "mnx_type" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoTypeExhibition dao = new DaoTypeExhibition();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_cause")) {
                        Log.d("SYNC", "mnx_cause" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementCauseExhibition dao = new DaoMeasurementCauseExhibition();
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_canal")) {
                        Log.d("SYNC", "mnx_canal" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_canal");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_rtm")) {
                        Log.d("SYNC", "mnx_rtm" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_rtm");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_client")) {
                        Log.d("SYNC", "mnx_client" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_client");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_format")) {
                        Log.d("SYNC", "mnx_format" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_format");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_pdv")) {
                        Log.d("SYNC", "mnx_pdv" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_pdv");
                        dao.delete();
                        dao.Insert(lst);
                    } else if (nt.getTag().equals("mnx_region")) {
                        Log.d("SYNC", "mnx_region" + nt.getResponse());
                        typeObjectGson = new TypeToken<List<DtoMeasurementFilter>>() {
                        }.getType();
                        List<DtoMeasurementFilter> lst;
                        lst = new Gson().fromJson(nt.getResponse(), typeObjectGson);
                        DaoMeasurementFilter dao = new DaoMeasurementFilter("measurement_exhibition_region");
                        dao.delete();
                        dao.Insert(lst);
                    }

                } catch (Exception e) {
                    STORAGE_OK = -1;
                    e.printStackTrace();
                }

                handlerstorage.sendEmptyMessage(STORAGE_OK);
            }
        };
        executor.execute(command);

    }

}
