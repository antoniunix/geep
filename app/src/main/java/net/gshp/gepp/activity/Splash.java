package net.gshp.gepp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;

import net.gshp.gepp.R;



import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leo on 6/03/18.
 */

public class Splash extends AppCompatActivity {

    private final static Long TIME_OF_SPLASH = 3000L;
    private Timer timer;
    private Context context;
    private WeakReference<Splash> wakReference;

    public void init() {
        context = this;
        timer = new Timer();
        wakReference = new WeakReference<Splash>(this);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        init();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        double wi = (double) width / (double) dm.xdpi;
        double hi = (double) height / (double) dm.ydpi;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        Log.e("display","display "+width+" "+height+" "+screenInches+"\"");

    }

    @Override
    protected void onResume() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Splash activity = wakReference.get();
                if (activity != null && !activity.isFinishing()) {
                    startActivity(new Intent(context, Login.class));
                    finish();
                }
            }
        };
        timer.schedule(timerTask, TIME_OF_SPLASH);
        super.onResume();
    }

}
