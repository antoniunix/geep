package net.gshp.gepp.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.model.ModelSend;
import net.gshp.gepp.util.Config;

import java.util.Calendar;

/**
 * Created by leo on 5/03/18.
 */

public class Wakelock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar calendar = Config.getCalendar();
        Log.e("WAKELOCK", "WAKELOCK INIT");

        if (calendar.get(Calendar.HOUR_OF_DAY) >= ContextApp.context.getResources().getInteger(R.integer.geolocation_schedule_start)
                && calendar.get(Calendar.HOUR_OF_DAY) <= ContextApp.context.getResources().getInteger(R.integer.geolocation_schedule_end)) {
            ContextApp.context.startService(new Intent(ContextApp.context, ServicesGeolocation.class));
        }
        new ModelSend().start();

    }
}
