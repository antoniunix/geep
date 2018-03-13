package net.gshp.gepp.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.gshp.api.utils.Crypto;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.activity.CheckOut;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoReport;
import net.gshp.gepp.dao.DaoReportCheck;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCheckSend;
import net.gshp.gepp.dto.DtoReport;
import net.gshp.gepp.dto.DtoReportCheck;
import net.gshp.gepp.listener.OnFinishTimeWiteGeolocation;
import net.gshp.gepp.network.NetworkConfig;
import net.gshp.gepp.util.Config;
import net.panamiur.geolocation.Geolocation;
import net.panamiur.geolocation.interfaces.OnApiGeolocation;

import org.apache.http.HttpStatus;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leo on 11/03/18.
 */

public class ModelCheckIn implements OnApiGeolocation, OnFinishTimeWiteGeolocation {

    private DtoBundle dtoBundle;
    private Geolocation geolocation;
    private Context context;
    private DaoReport daoReportReport;
    private View view;

    private float bestAccuracy = 0;
    private Timer timer;
        private WeakReference<CheckOut> weakReference;
    private CheckOut checkOut;
    private List<DtoCheckSend> lstCheckSends;
    private HandlerSendCheck handlerSendCheck;
    private NetworkConfig networkConfig;
    private DaoReportCheck daoReportCheck;

    public ModelCheckIn(Context context, DtoBundle dtoBundle, View view, CheckOut checkOut) {
        this.dtoBundle = dtoBundle;
        this.view = view;
        handlerSendCheck = new HandlerSendCheck();
        networkConfig = new NetworkConfig(handlerSendCheck, ContextApp.context);
        daoReportReport = new DaoReport();
        this.context = context;
        this.checkOut = checkOut;
        timer = new Timer();
        weakReference = new WeakReference<>(checkOut);

    }

    public void onStart() {
        lapseTime();
        addNewReport();
        geolocation = new Geolocation(ModelCheckIn.class);
        geolocation.setOnApiGeolocationListener(this)
                .setContext(context)
                .setTimeUpdateLocation(context.getResources().getInteger(R.integer.geolocation_time_update));
        geolocation.stopGeo();
        geolocation.startGeo();
    }

    @Override
    public void onApiGeolocationChange(Location location) {
        if (bestAccuracy == 0 || bestAccuracy > location.getAccuracy()) {
            bestAccuracy = location.getAccuracy();
            new DaoReportCheck().insert(new DtoReportCheck()
                    .setIdReportLocal(dtoBundle.getIdReportLocal())
                    .setLatitude(location.getLatitude())
                    .setLongitude(location.getLongitude())
                    .setAccuracy(String.valueOf(location.getAccuracy()))
                    .setSatelliteUtc(String.valueOf(location.getTime()))
                    .setTypeCheck(1)
                    .setProvider(location.getProvider()));
            geolocation.stopGeo();
            checkOut.setMarker(location);
            view.setVisibility(View.VISIBLE);
            ((Button) view).setText(context.getString(R.string.report_check_exact)
                    + " " + location.getAccuracy()
                    + " m\nCONTINUAR");


        }
    }

    public void sendCheck() {
        lstCheckSends = new DaoReportCheck().SelectToSend();
        Log.e("status", "size " + lstCheckSends.size());
        new Thread() {
            public void run() {
                for (int i = 0; i < lstCheckSends.size(); i++) {
                    String json = new Gson().toJson(lstCheckSends.get(i));
                    Log.e(ContextApp.context.getResources().getString(R.string.app_name), "Report " + json);
                    Map<String, String> header = new HashMap<String, String>();
                    header.put(ContextApp.context.getString(R.string.network_header_name_application_json), ContextApp.context.getString(R.string.network_header_application_json));
                    networkConfig.POST("report/create", json, "rprt" + lstCheckSends.get(i).getIdReportLocal(), header);

                }
            }


        }.start();
    }

    public void stopGeo() {
        geolocation.stopGeo();
    }

    /**
     * cuando pasa el tiempo maximo para la captura de coordenada y no se ha podido obtener, se dejara
     * la coordenada por default
     *
     * @param timeCurrent
     * @param timeFinish
     */

    @Override
    public void onFinishTimeWiteGeolocation(long timeCurrent, boolean timeFinish) {
        if (view.getVisibility() != View.VISIBLE) {
            checkOut.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.VISIBLE);
                    ((Button) view).setText(context.getString(R.string.report_check_without_location));

                }
            });
        }
    }

    private void lapseTime() {
        final OnFinishTimeWiteGeolocation onFinishTimeWiteGeolocation = this;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                CheckOut activity = weakReference.get();
                if (activity != null && !activity.isFinishing()) {
                    onFinishTimeWiteGeolocation.onFinishTimeWiteGeolocation(0, true);

                }
            }
        };
        timer.schedule(timerTask, context.getResources().getInteger(R.integer.geolocation_lapse_time_max));
    }

    private void addNewReport() {
        String version = "";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DtoReport dtoReport = new DtoReport();
        dtoReport.setIdPdv(dtoBundle.getIdPDV())
                .setIdSchedule(1)//Al no existir rutero se setea 1
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
        dtoBundle.setIdReportLocal((int) daoReportReport.insert(dtoReport));
        DtoReportCheck dtoCheck = new DtoReportCheck();
        dtoCheck.setIdReportLocal(dtoBundle.getIdReportLocal());
        dtoCheck.setDate(System.currentTimeMillis());
        dtoCheck.setTz(Config.getTimeZone());
        dtoCheck.setLatitude(0);
        dtoCheck.setLongitude(0);
        dtoCheck.setAccuracy(String.valueOf(0));
        dtoCheck.setImei(Config.getIMEI());
        dtoCheck.setSatelliteUtc(String.valueOf(0));
        dtoCheck.setTypeCheck(1);
        dtoCheck.setSend(0);
        dtoCheck.setProvider(context.getString(R.string.report_check_dont_provider));
        dtoCheck.setDateInactive(String.valueOf(0));
        dtoCheck.setActive(1);
        dtoCheck.setHash(Crypto.MD5(System.currentTimeMillis() + " " + Math.random()));
        new DaoReportCheck().insert(dtoCheck);


    }

    class HandlerSendCheck extends Handler {
        @Override
        public void handleMessage(Message msg) {
            NetworkTask nt = (NetworkTask) msg.obj;
            if (nt.getResponseStatus() == HttpStatus.SC_OK || nt.getResponseStatus() == HttpStatus.SC_CREATED) {
                new DaoReportCheck().markAsSent(nt.getTag().substring(4));
            }
        }
    }


}
