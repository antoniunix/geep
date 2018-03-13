package net.gshp.gepp.geolocation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;

/**
 * Created by leo on 5/03/18.
 */

public class AlarmGeolocation {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private final static AlarmGeolocation INSTANCE = new AlarmGeolocation();

    private AlarmGeolocation(){setAlarm();}

    public static AlarmGeolocation getInstance(){return INSTANCE;}

    private void setAlarm(){
        alarmManager= (AlarmManager) ContextApp.context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ContextApp.context, Wakelock.class);
        alarmIntent=PendingIntent.getBroadcast(ContextApp.context,0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                ContextApp.context.getResources().getInteger(R.integer.geolocation_alarm_start),alarmIntent);
    }
}
