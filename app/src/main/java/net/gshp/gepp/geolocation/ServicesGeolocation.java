package net.gshp.gepp.geolocation;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.gshp.api.utils.Crypto;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoReportGeolocation;
import net.gshp.gepp.dto.DtoReportGeolocation;
import net.gshp.gepp.network.NetworkConfig;
import net.gshp.gepp.util.Config;
import net.panamiur.geolocation.Geolocation;
import net.panamiur.geolocation.interfaces.OnApiGeolocation;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 5/03/18.
 */

public class ServicesGeolocation extends IntentService implements OnApiGeolocation {

    private final int ENVIOFINALIZADO = -1000;
    private final int SINREPORTES = -2000;
    private List<DtoReportGeolocation> dto;
    private DaoReportGeolocation daoReportGeolocation;
    private Geolocation geolocation;
    private NetworkConfig networkConfig;

    public ServicesGeolocation() {
        super("geolocation");
        Log.e("GEO", "Geo Service ");
        daoReportGeolocation = new DaoReportGeolocation();
        networkConfig = new NetworkConfig(new HandlerTask(), ContextApp.context);
        geolocation = new Geolocation(ServicesGeolocation.class);
        geolocation.setOnApiGeolocationListener(this)
                .setContext(ContextApp.context)
                .setTimeUpdateLocation(ContextApp.context.getResources().getInteger(R.integer.geolocation_time_update));

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("GEO", "Geo Service resume");
        geolocation.stopGeo();
        geolocation.startGeo();

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onApiGeolocationChange(Location location) {

        Log.e("GEO", "Geo se inserta ");
        new DaoReportGeolocation().Insert(new DtoReportGeolocation()
                .setLatitude(location.getLatitude())
                .setLongitude(location.getLongitude())
                .setBattery(String.valueOf(Config.getBatteryLevel()))
                .setAccuracy(String.valueOf(location.getAccuracy()))
                .setImei(Config.getIMEI())
                .setSatelliteUtc(String.valueOf(location.getTime()))
                .setDate(String.valueOf(System.currentTimeMillis()))
                .setTz(Config.getTimeZone())
                .setVersion("1")
                .setHash(Crypto.MD5(System.currentTimeMillis() + " " + Math.random()))
                .setProvider(location.getProvider()));
        geolocation.stopGeo();
        sendLocation();

    }

//    private void sendLocation() {
//        dto = daoReportGeolocation.Select();
//        new Thread() {
//            public void run() {
//                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//                nameValuePairs.add(new BasicNameValuePair("json", new Gson().toJson(dto)));
//                networkConfig.POST_GEO("geo", nameValuePairs, "geo");
//            }
//        }.start();
//    }

    public void sendLocation() {
        dto = daoReportGeolocation.Select();
        new Thread() {
            public void run() {
                String json = new Gson().toJson(dto);
                Log.e("GEO", "SEND GEO " + json);
                Map<String, String> header = new HashMap<String, String>();
                header.put(ContextApp.context.getString(R.string.network_header_name_application_json), ContextApp.context.getString(R.string.network_header_application_json));
                networkConfig.POST("device/geo", json, "geo", header);

            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class HandlerTask extends Handler {
        @Override
        public void handleMessage(Message msg) {
            NetworkTask completedTask = (NetworkTask) msg.obj;
            if (completedTask != null && (completedTask.getTag().equals("geo"))) {

                Log.i("geo", "status GEO" + completedTask.getResponseStatus());
                if (completedTask.getResponseStatus() == HttpStatus.SC_CREATED || completedTask.getResponseStatus() == HttpStatus.SC_OK) {
                    new DaoReportGeolocation().delete();
                }
                stopSelf();
            }
        }
    }

}
