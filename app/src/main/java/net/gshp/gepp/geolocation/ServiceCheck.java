package net.gshp.gepp.geolocation;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.gshp.api.utils.Crypto;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoReportCheck;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCheckSend;
import net.gshp.gepp.dto.DtoReportCheck;
import net.gshp.gepp.network.NetworkConfig;
import net.gshp.gepp.util.Config;
import net.panamiur.geolocation.Geolocation;
import net.panamiur.geolocation.interfaces.OnApiGeolocation;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leo on 10/03/18.
 */

public class ServiceCheck extends IntentService implements OnApiGeolocation {

    private Geolocation geolocation;
    private DtoBundle dtoBundle;
    private List<DtoCheckSend> lstCheckSends;
    private NetworkConfig networkConfig;
    private Timer timer;
    private int typeCheck;
    private float bestAccuracy = 1000;

    public ServiceCheck() {
        super("ServiceCheck");
        Log.e("service check", "service check");
        geolocation = new Geolocation(ServiceCheck.class);
        geolocation.setOnApiGeolocationListener(this)
                .setContext(ContextApp.context)
                .setTimeUpdateLocation(ContextApp.context.getResources().getInteger(R.integer.geolocation_time_update));
        geolocation.stopGeo();
        geolocation.startGeo();
        networkConfig = new NetworkConfig(new HandlerSendCheck(), ContextApp.context);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        timer = new Timer();
        dtoBundle = (DtoBundle) intent.getExtras().get(ContextApp.context.getString(R.string.app_bundle_name));
        typeCheck = intent.getExtras().getInt("typeCheck");

        if (typeCheck > 0) {
            DtoReportCheck dtoCheck = new DtoReportCheck();
            dtoCheck.setIdReportLocal(dtoBundle.getIdReportLocal());
            dtoCheck.setDate(System.currentTimeMillis());
            dtoCheck.setTz(Config.getTimeZone());
            dtoCheck.setLatitude(0);
            dtoCheck.setLongitude(0);
            dtoCheck.setAccuracy(String.valueOf(0));
            dtoCheck.setImei(Config.getIMEI());
            dtoCheck.setSatelliteUtc(String.valueOf(0));
            dtoCheck.setTypeCheck(typeCheck);
            dtoCheck.setSend(0);
            dtoCheck.setProvider(ContextApp.context.getString(R.string.report_check_dont_provider));
            dtoCheck.setDateInactive(String.valueOf(0));
            dtoCheck.setActive(1);
            dtoCheck.setHash(Crypto.MD5(System.currentTimeMillis() + " " + Math.random()));
            new DaoReportCheck().insert(dtoCheck);
        }
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                geolocation.stopGeo();
                if (typeCheck == 2) {
                   // new ModelSend().start();
                }
                stopSelf();
            }
        };
        timer.schedule(timerTask, ContextApp.context.getResources().getInteger(R.integer.time_check_max));
    }

    @Override
    public void onApiGeolocationChange(Location location) {
        if (location.getAccuracy() < bestAccuracy) {

            if (typeCheck > 0) {
                DtoReportCheck dtoCheck = new DtoReportCheck();
                dtoCheck.setIdReportLocal(dtoBundle.getIdReportLocal());
                dtoCheck.setDate(System.currentTimeMillis());
                dtoCheck.setTz(Config.getTimeZone());
                dtoCheck.setLatitude(location.getLatitude());
                dtoCheck.setLongitude(location.getLongitude());
                dtoCheck.setAccuracy(location.getAccuracy() + "");
                dtoCheck.setImei(Config.getIMEI());
                dtoCheck.setSatelliteUtc(location.getTime() + "");
                dtoCheck.setTypeCheck(typeCheck);
                dtoCheck.setProvider(location.getProvider());
                dtoCheck.setSend(0);
                new DaoReportCheck().insert(dtoCheck);

            }
            bestAccuracy = location.getAccuracy();
        }
        sendCheck();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void sendCheck() {
        lstCheckSends = new DaoReportCheck().SelectToSend();
        Log.e("status", "size " + lstCheckSends.size());
        new Thread() {
            public void run() {
                for (int i = 0; i < lstCheckSends.size(); i++) {
                    String json = "[" + new Gson().toJson(lstCheckSends.get(i)) + "]";
                    Log.e("Lista", "Lista " + json);
                    Map<String, String> header = new HashMap<>();
                    header.put(ContextApp.context.getString(R.string.network_header_name_application_json), ContextApp.context.getString(R.string.network_header_application_json));

                    networkConfig.POST("multireport/insertnt/rcheckin/1", json,
                            "rsch" + lstCheckSends.get(i).getIdReportLocal(), header);
                }
            }

            ;
        }.start();
    }

    class HandlerSendCheck extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.e("kkk", "hhhh");
            NetworkTask nt = (NetworkTask) msg.obj;
            if (nt.getResponseStatus() == HttpStatus.SC_OK || nt.getResponseStatus() == HttpStatus.SC_CREATED) {
                new DaoReportCheck().markAsSent(nt.getTag().substring(4));
            }
        }
    }
}
