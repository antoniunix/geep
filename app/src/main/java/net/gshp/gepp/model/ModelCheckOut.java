package net.gshp.gepp.model;

import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.Button;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.R;
import net.gshp.gepp.activity.CheckOut;
import net.gshp.gepp.dao.DaoReport;
import net.gshp.gepp.dao.DaoReportCheck;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportCheck;
import net.gshp.gepp.listener.OnFinishTimeWiteGeolocation;
import net.gshp.gepp.util.Config;
import net.panamiur.geolocation.Geolocation;
import net.panamiur.geolocation.interfaces.OnApiGeolocation;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leo on 11/03/18.
 */

public class ModelCheckOut implements OnApiGeolocation, OnFinishTimeWiteGeolocation {

    private DtoBundle dtoBundle;
    private Geolocation geolocation;
    private Context context;
    private DaoReport daoReportReport;
    private View view;
    private float bestAccuracy = 0;
    private Timer timer;
    private WeakReference<CheckOut> weakReference;
    private CheckOut checkOut;
    private DaoReportCheck daoReportCheck;

    public ModelCheckOut(Context context, DtoBundle dtoBundle, View view, CheckOut checkOut) {
        this.dtoBundle = dtoBundle;
        this.view = view;
        daoReportReport = new DaoReport();
        this.context = context;
        this.checkOut = checkOut;
        timer = new Timer();
        weakReference = new WeakReference<>(checkOut);
        addNewReport();

    }

    public void onStart() {
        lapseTime();
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
                    .setTypeCheck(2)
                    .setProvider(location.getProvider()));
            geolocation.stopGeo();
            checkOut.setMarker(location);
            view.setVisibility(View.VISIBLE);
            ((Button) view).setText(context.getString(R.string.report_check_exact)
                    + " " + location.getAccuracy()
                    + " m");

        }
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
        DtoReportCheck dtoCheck = new DtoReportCheck();
        dtoCheck.setIdReportLocal(dtoBundle.getIdReportLocal());
        dtoCheck.setDate(System.currentTimeMillis());
        dtoCheck.setTz(Config.getTimeZone());
        dtoCheck.setLatitude(0);
        dtoCheck.setLongitude(0);
        dtoCheck.setAccuracy(String.valueOf(0));
        dtoCheck.setImei(Config.getIMEI());
        dtoCheck.setSatelliteUtc(String.valueOf(0));
        dtoCheck.setTypeCheck(2);
        dtoCheck.setSend(0);
        dtoCheck.setProvider(context.getString(R.string.report_check_dont_provider));
        dtoCheck.setDateInactive(String.valueOf(0));
        dtoCheck.setActive(1);
        dtoCheck.setHash(Crypto.MD5(System.currentTimeMillis() + " " + Math.random()));
        new DaoReportCheck().insert(dtoCheck);


    }

}
