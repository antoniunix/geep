package net.gshp.gepp.listener;

/**
 * Created by leo on 6/03/18.
 */

public interface OnProgressSync {
    void onProgresSync(int porcentOfProgress, int httpstatus, String service);
    void onNewVersionExist(boolean isExist);
    void onFinishSync(int httpstatus, String response, Object obj);
}
