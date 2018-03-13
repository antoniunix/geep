package net.gshp.gepp.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import net.gshp.APINetwork.NetworkTask;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.network.NetworkConfig;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by leo on 8/03/18.
 */

public class ModelSendDataBase {
    private Handler handler;
    private File fileDataBase;
    private NetworkConfig networkConfig;
    private Context context;

    public ModelSendDataBase(){
        context = ContextApp.context;
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                NetworkTask completedTask = (NetworkTask) msg.obj;
            }
        };
    }
    /**
     * envia la base de datos para revicion remota
     */
    public void sendBD() {

        fileDataBase = context.getDatabasePath(context.getString(R.string.app_db_name));
        Log.e("SEND_DB", "SEND_DB " + fileDataBase.getAbsolutePath());
        networkConfig = new NetworkConfig(handler, context);
        if (fileDataBase.exists()) {
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                    1);
            nameValuePairs.add(new BasicNameValuePair("json", ""));
            networkConfig.POST_MULTIPART_FILE("user-file", fileDataBase.getAbsolutePath(),
                    nameValuePairs, "user-file");
        }
    }

}
