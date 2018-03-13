package net.gshp.gepp.model;

import net.gshp.gepp.dao.DaoMeasurementDownloadSku;
import net.gshp.gepp.dao.DaoMessageView;

/**
 * Created by leo on 8/03/18.
 */

public class ModelNewMessageFile {

    private DaoMessageView daoMessageView;
    private DaoMeasurementDownloadSku daoMeasurementDownloadSku;

    public ModelNewMessageFile(){
        daoMeasurementDownloadSku = new DaoMeasurementDownloadSku();
        daoMessageView = new DaoMessageView();
    }

    public int countNewMessage(){
        int newMessage = 0;
        if(daoMessageView.countMessage()> daoMessageView.countMessageViewed()){
            newMessage = daoMessageView.countMessage()-daoMessageView.countMessageViewed();
        }
        return newMessage;
    }

    public int countNewFile(){
        int newfile=0;
        if(daoMeasurementDownloadSku.selectDodwnload().size()> daoMeasurementDownloadSku.countDownLoadFile()){
            newfile = daoMeasurementDownloadSku.selectDodwnload().size()- daoMeasurementDownloadSku.countDownLoadFile();
        }
        return newfile;
    }


}
