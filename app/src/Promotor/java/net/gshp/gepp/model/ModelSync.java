package net.gshp.gepp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoAuthentication;
import net.gshp.gepp.dto.DtoStatus;
import net.gshp.gepp.dto.DtoUpdate;
import net.gshp.gepp.listener.OnProgressSync;
import net.gshp.gepp.network.NetworkConfig;
import net.gshp.gepp.util.Config;
import net.gshp.gepp.util.SharePreferenceCustom;

import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 8/03/18.
 */

public class ModelSync {

    private final String regId = "";
    private final int STORAGE_OK = 1;
    private final int STORAGE_ERROR = -1;
    private final int ACTUALIZAR = 2000;
    private HandlerTask handlerTask;
    private OnProgressSync listenerOnProgressSync;
    private boolean SC_UNAUTHORIZED = false;
    private boolean SC_CONFLICT = false;
    private boolean NUEVA_ACTUALIZACION = false;
    private boolean SC_NO_CONTENT = false;
    private boolean SC_FORBIDDEN = false;
    private boolean SC_PAYMENT_REQUIRED = false;
    private boolean SC_METHOD_FAILURE = false;
    private String SC_FORBIDDEN_RESPONSE = "";
    private String SC_METHOD_FAILURE_RESPONSE = "";
    private ModelDataBaseSync modelDataBaseSync;
    private HandlerStorage handlerStorage;
    private DtoUpdate dtoUpdate;
    private List<String> lstNoContent;
    private SharedPreferences prefs;
    private DtoUpdate dtoUpdateApp;

    private DtoStatus dtoStatus;
    private Context context;

    private boolean flag = false;
    private int numReportGuardados = 0;

    private final int NUMCATALOGOS = 39; //se usa para saber cuando ya se descargaron todos los catalogos y enviar mensaje de terminado
    private int numReportDownload = 0;
    private NetworkConfig networkConfig;

    public ModelSync(OnProgressSync listenerOnProgressSync, Context context) {
        prefs = context.getSharedPreferences(context.getString(R.string.app_share_preference_name), Context.MODE_PRIVATE);
        handlerTask = new HandlerTask();
        this.listenerOnProgressSync = listenerOnProgressSync;
        this.context = context;
        handlerStorage = new HandlerStorage();
        modelDataBaseSync = new ModelDataBaseSync(handlerStorage);
        networkConfig = new NetworkConfig(handlerTask, context);
        lstNoContent = new ArrayList<>();
//        regId = FirebaseInstanceId.getInstance().getToken();
        //      Log.e("RegId", "RegId " + regId);
    }

    public void checkStatusSync() {
        new Thread() {
            public void run() {
                networkConfig.GET("psspolicy/status", "STATUS");
            }

            ;
        }.start();
    }

    private void Actualizar() {
        new Thread() {
            public void run() {
                networkConfig.GET("version/android-app", "version");
            }

            ;
        }.start();
    }

    public void setAuthentication() {
        new Thread() {
            public void run() {
                String json = new Gson().toJson(getDataToAuthentication());
                Log.e("gson ", "gson " + json);
                networkConfig.POST("login/authentication", json, "token", null);
            }
        }.start();
    }


    private void Sincronizar() {
        new Thread() {
            public void run() {
                networkConfig.GET("multireport/catalog/pos_pos", "pdv_pdv");
               // networkConfig.GET("version/data/terms", "terms");
               networkConfig.GET("multireport/catalog/c_canal", "c_canal");
                networkConfig.GET("multireport/catalog/c_client", "c_client");
                networkConfig.GET("multireport/catalog/c_rtm", "c_rtm");
                networkConfig.GET("multireport/catalog/c_type_report", "c_type_report");
                networkConfig.GET("multireport/catalog/c_category", "c_category");
                networkConfig.GET("multireport/catalog/schedule", "schedule");

                //Encuesta
                networkConfig.GET("multireport/catalog/ea_poll", "ea_poll");
                networkConfig.GET("multireport/catalog/ea_question", "ea_question");
                networkConfig.GET("multireport/catalog/ea_question_type_cat", "ea_question_type_cat");
                networkConfig.GET("multireport/catalog/ea_question_option", "ea_question_option");
                networkConfig.GET("multireport/catalog/ea_section", "ea_section");
                networkConfig.GET("multireport/catalog/ea_answers_pdv", "ea_answers_pdv");

                /**
                 * Asignacion de Modulos
                 */
                networkConfig.GET("multireport/catalog/mam_app_module", "mam_app_module");
                networkConfig.GET("multireport/catalog/mam_module", "mam_module");
                networkConfig.GET("multireport/catalog/mam_region", "mam_region");
                networkConfig.GET("multireport/catalog/mam_canal", "mam_canal");
                networkConfig.GET("multireport/catalog/mam_rtm", "mam_rtm");
                networkConfig.GET("multireport/catalog/mam_client", "mam_client");
                networkConfig.GET("multireport/catalog/mam_format", "mam_format");
                networkConfig.GET("multireport/catalog/mam_pos", "mam_pdv");

                //Archivos descargables y mensaje
                networkConfig.GET("multireport/catalog/mdf_downloadable_files", "mdf_downloadable_files");
                networkConfig.GET("multireport/catalog/mdf_file", "mdf_file");
                networkConfig.GET("multireport/catalog/message_service", "messages_service");

                //SCANNALERT
                networkConfig.GET("multireport/catalog/scan_alert", "scann_alert");
                networkConfig.GET("multireport/catalog/c_type_sa_status", "c_status_scann");
                networkConfig.GET("multireport/catalog/c_type_sa_problem", "c_problem_scann");

                //Exhibition
                networkConfig.GET("multireport/catalog/c_exhibition", "c_exhibition");
                networkConfig.GET("multireport/catalog/mnx_exhibitions", "mnx_exhibitions");
                networkConfig.GET("multireport/catalog/c_type_catalog", "c_type_catalog");
                networkConfig.GET("multireport/catalog/mnx_items", "mnx_items");
                networkConfig.GET("multireport/catalog/mnx_type", "mnx_type");
                networkConfig.GET("multireport/catalog/mnx_cause", "mnx_cause");
                networkConfig.GET("multireport/catalog/mnx_canal", "mnx_canal");
                networkConfig.GET("multireport/catalog/mnx_rtm", "mnx_rtm");
                networkConfig.GET("multireport/catalog/mnx_client", "mnx_client");
                networkConfig.GET("multireport/catalog/mnx_format", "mnx_format");
                networkConfig.GET("multireport/catalog/mnx_pos", "mnx_pdv");
                networkConfig.GET("multireport/catalog/mnx_region", "mnx_region");

            }

        }.start();
    }

    private DtoAuthentication getDataToAuthentication() {
        return new DtoAuthentication()
                .setUsername(prefs.getString(context.getString(R.string.app_share_preference_user_account), ""))
                .setPassword(prefs.getString(context.getString(R.string.app_share_preference_user_pass), ""))
                .setImei(Config.getIMEI())
                .setBrand(Config.getBrandDevice())
                .setOs(Config.getOs())
                .setOsVersion(Config.getOsVersion())
                .setPhone(Config.getPhoneNumber())
                .setModel(Config.getModel());
        //.setDeviceId(FirebaseInstanceId.getInstance().getToken());
    }

    class HandlerTask extends Handler {
        @Override
        public void handleMessage(Message msg) {
            NetworkTask nt = (NetworkTask) msg.obj;


            if (!nt.getTag().equals("version") && !nt.getTag().equals("STATUS") && !nt.getTag().equals("token")) {
                numReportDownload++;
            }
            if (nt.getResponseStatus() == HttpStatus.SC_OK || nt.getResponseStatus() == HttpStatus.SC_CREATED) {
                if (nt.getTag().equals("token")) {
                    Log.e("leo", "token " + nt.getResponse());
                    SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.app_share_preference_name), Context.MODE_PRIVATE);
                    prefs.edit().putString(context.getString(R.string.app_share_preference_toke_webservices), nt.getResponse()).apply();
                    Actualizar();
                } else if (nt.getTag().equals("version")) {
                    Type typeObjectGson = new TypeToken<DtoUpdate>() {
                    }.getType();

                    dtoUpdateApp = new Gson().fromJson(nt.getResponse(), typeObjectGson);

                    String version;

                    try {
                        version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                        if (dtoUpdateApp.getVersion().equals(version) || dtoUpdateApp.getVersion().concat("_tmp").equals(version)) {
                            Sincronizar();
                        } else {
                            NUEVA_ACTUALIZACION = true;
                            infoUI();
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    } finally {

                    }
                } else {
                    Log.e("leo", "insert: ");
                    // enviamos los datos recividos a base
                    modelDataBaseSync.syncInsertion(nt);
                }
            } else if (nt.getResponseStatus() == HttpStatus.SC_UNAUTHORIZED) {
                numReportDownload = NUMCATALOGOS;
                numReportGuardados = NUMCATALOGOS;

                SC_UNAUTHORIZED = true;
            } else if (nt.getResponseStatus() == HttpStatus.SC_FORBIDDEN) {
                SC_FORBIDDEN = true;
                SC_FORBIDDEN_RESPONSE = nt.getResponse();
                numReportDownload = NUMCATALOGOS;
                numReportGuardados = NUMCATALOGOS;

            } else if (nt.getResponseStatus() == HttpStatus.SC_PAYMENT_REQUIRED) {
                SC_PAYMENT_REQUIRED = true;
                numReportDownload = NUMCATALOGOS;
                numReportGuardados = NUMCATALOGOS;

            } else if (nt.getResponseStatus() == HttpStatus.SC_METHOD_FAILURE) {
                SC_METHOD_FAILURE_RESPONSE = nt.getResponse();
                SC_METHOD_FAILURE = true;
                numReportDownload = NUMCATALOGOS;
                numReportGuardados = NUMCATALOGOS;

            } else if (nt.getResponseStatus() == HttpStatus.SC_NO_CONTENT) {
                //Se tiene que elimiar lo que contenga la tabla
                nt.setTag("DELETE" + nt.getTag());
                modelDataBaseSync.syncInsertion(nt);
                SC_NO_CONTENT = true;
                lstNoContent.add(nt.getTag().substring("DELETE".length()));
            } else {
                if (nt.getTag().equals("version") || nt.getTag().equals("STATUS") || nt.getTag().equals("token")) {
                    //si los servicios VERSION y/o MD5 fallan no deben descargarse los demas
                    //servicios, por eso se igualan las variables
                    numReportDownload = NUMCATALOGOS;
                    numReportGuardados = NUMCATALOGOS;
                    SC_CONFLICT = true;
                } else {
                    //como no se pudo descargar el catalogo, se tiene que aumentar al contador de base
                    //ya que si no nunca se guardara a la base y no se podra completar la validacion
                    // de catalogos guardados.
                    numReportGuardados++;
                    SC_CONFLICT = true;
                }
            }
            infoUI();

        }
    }

    class HandlerStorage extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == STORAGE_OK) {
                numReportGuardados++;
            } else if (msg.what == STORAGE_ERROR) {
                SC_CONFLICT = true;
                numReportGuardados++;
            }
            infoUI();
        }
    }

    /**
     * comprueba los resultados de la sincronizacion y los envia a la vista
     */
    private void infoUI() {
        int porcent = (numReportGuardados * 100) / NUMCATALOGOS;
        if (!SC_UNAUTHORIZED && !SC_CONFLICT) {
            listenerOnProgressSync.onProgresSync(porcent, 0, null);
        }
        if (NUEVA_ACTUALIZACION && !flag) {
            listenerOnProgressSync.onNewVersionExist(true);
            flag = true;
        } else if (numReportDownload == NUMCATALOGOS && numReportGuardados == NUMCATALOGOS) {
            numReportDownload = 0; //reseteamos el contador
            numReportGuardados = 0;
            if (!SC_UNAUTHORIZED && !SC_CONFLICT && !SC_NO_CONTENT && !SC_FORBIDDEN && !SC_PAYMENT_REQUIRED && !SC_METHOD_FAILURE) {
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_OK, null, null);
            } else if (SC_UNAUTHORIZED) {
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_UNAUTHORIZED, null, null);
                SC_UNAUTHORIZED = false;
            } else if (SC_CONFLICT) {
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_CONFLICT, null, null);
                SC_CONFLICT = false;
            } else if (SC_NO_CONTENT) {
                SC_NO_CONTENT = false;
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_NO_CONTENT, null, null);
            } else if (SC_FORBIDDEN) {
                SC_FORBIDDEN = false;
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_FORBIDDEN, null, SC_FORBIDDEN_RESPONSE);
            } else if (SC_PAYMENT_REQUIRED) {
                SC_PAYMENT_REQUIRED = false;
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_PAYMENT_REQUIRED, null, null);
            } else if (SC_METHOD_FAILURE) {
                SC_FORBIDDEN = false;
                listenerOnProgressSync.onFinishSync(HttpStatus.SC_METHOD_FAILURE, SC_METHOD_FAILURE_RESPONSE, SC_METHOD_FAILURE_RESPONSE);

            }

        }
    }
}
