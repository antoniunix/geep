package net.gshp.gepp.contextApp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.gosharp.apis.db.DBAPI;

import net.gshp.apiencuesta.APIEncuesta;
import net.gshp.gepp.R;

/**
 * Created by leo on 5/03/18.
 */

public class ContextApp extends MultiDexApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        APIEncuesta.setPATH_FOTO(getString(R.string.app_path_photo));
        APIEncuesta.setApplication(this);
        DBAPI dbapi = DBAPI.getInstance();
        dbapi.loadPropertiesFromFile(this.getApplicationContext().getResources());
        dbapi.createDB(this.getApplicationContext());
        Stetho.initializeWithDefaults(this);



    }
}
