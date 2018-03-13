package net.gshp.gepp.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.github.mikephil.charting.charts.LineChart;

import net.gshp.gepp.R;
import net.gshp.gepp.model.ModelHistoricScanAlert;

/**
 * Created by leo on 12/03/18.
 */

public class DialogHistoricScanAlert extends DialogFragment {

    private View view;
    private LineChart chart_scan_alert;
    private ModelHistoricScanAlert modelHistoricScanAlert;
    private long  idPdv;

    public DialogHistoricScanAlert() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_historic_scan_alert, container);
        init();
        return view;
    }

    @Override
    public void onResume() {
        chart_scan_alert.setTouchEnabled(false);
        chart_scan_alert.setDragEnabled(false);
        chart_scan_alert.setHighlightEnabled(false);
        chart_scan_alert.setPadding(30, 30, 30, 30);
        chart_scan_alert.animateXY(2000, 2000);
        chart_scan_alert.setDescription("Scan Alert");
        chart_scan_alert.setBackgroundColor(Color.WHITE);

        chart_scan_alert.setData(modelHistoricScanAlert.getLineDateHistoricScanAlert(idPdv));
        super.onResume();
    }

    public void setIdPdv(long idPdv){
        this.idPdv=idPdv;
    }

    private void init(){
        chart_scan_alert=(LineChart)view.findViewById(R.id.chart_scan_alert);
        modelHistoricScanAlert=new ModelHistoricScanAlert();
    }

}
