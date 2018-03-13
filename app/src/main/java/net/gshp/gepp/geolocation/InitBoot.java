package net.gshp.gepp.geolocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by leo on 6/03/18.
 */

public class InitBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmGeolocation.getInstance();
    }
}
