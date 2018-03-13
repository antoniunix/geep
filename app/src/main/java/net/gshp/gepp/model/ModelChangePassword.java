package net.gshp.gepp.model;

import android.os.Handler;

import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.network.NetworkConfig;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


/**
 * Created by leo on 8/03/18.
 */

public class ModelChangePassword {
    private NetworkConfig networkConfig;
    private Handler handlerGUI;

    public ModelChangePassword(Handler handlerGUI){
        this.handlerGUI = handlerGUI;
        networkConfig = new NetworkConfig(handlerGUI, ContextApp.context);
    }
    public void sendPassword(final String pass, final String lastpast) {
        new Thread() {
            public void run() {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("pass", pass));
                networkConfig.POSTChangePass("psspolicy/update", nameValuePairs, "rsaa", lastpast);
            }
        }.start();
    }
}
