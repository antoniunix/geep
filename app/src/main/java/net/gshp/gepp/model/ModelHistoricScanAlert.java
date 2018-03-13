package net.gshp.gepp.model;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import net.gshp.gepp.dao.DaoHistoricScanAlert;
import net.gshp.gepp.dto.DtoHistoric;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class ModelHistoricScanAlert {
    private DaoHistoricScanAlert daoHistoricScanAlert;

    public ModelHistoricScanAlert() {
        daoHistoricScanAlert = new DaoHistoricScanAlert();
    }


    public LineData getLineDateHistoricScanAlert(long idPdv) {

        List<DtoHistoric> lstSP = daoHistoricScanAlert.SelectSP(idPdv);
        List<DtoHistoric> lstISV = daoHistoricScanAlert.SelectISV(idPdv);
        List<DtoHistoric> lstPE = daoHistoricScanAlert.SelectPE(idPdv);
        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        Log.e("size","lstSP "+lstSP.size()+"\nlstISV "+lstISV.size()+"\nlstPE "+lstPE.size());

        String[] stages = {"Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5"};

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < lstSP.size(); i++) {
            yVals.add(new Entry(lstSP.get(i).getCount(), i));
        }
        if (lstSP.size() > 0) {
            LineDataSet set1 = new LineDataSet(yVals, "SP");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            dataSets.add(set1);

        }

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < lstISV.size(); i++) {
            yVals1.add(new Entry(lstISV.get(i).getCount(), i));
        }
        if (yVals1.size() > 0) {
            LineDataSet set2 = new LineDataSet(yVals1, "ISV");
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(1f);
            dataSets.add(set2);

        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < lstPE.size(); i++) {
            yVals2.add(new Entry(lstPE.get(i).getCount(), i));
        }
        if (yVals2.size() > 0) {
            LineDataSet set3 = new LineDataSet(yVals2, "PE");
            set3.setColor(Color.BLUE);
            set3.setCircleColor(Color.BLUE);
            set3.setLineWidth(1f);
            dataSets.add(set3);

        }


        LineData data = new LineData(stages, dataSets);
        return data;

    }

}
