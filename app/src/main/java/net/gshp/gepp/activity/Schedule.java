package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.AdapterSchedule;
import net.gshp.gepp.dialog.DialogFilterSchedule;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoSchedule;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.model.ModelSchedule;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 10/03/18.
 */

public class Schedule extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, OnDissmisDialogListener {

    private GridView gridRutero;
    private ModelSchedule model;
    private AdapterSchedule adapter;
    private DtoBundle dtoBundle;
    private ImageView imgFilter, imgChangeView;
    private ProgressBar prg_load;
    private TextView txt_date, txtLabelSchedule;

    private void init() {
        gridRutero = (GridView) findViewById(R.id.gridRutero);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txtLabelSchedule = (TextView) findViewById(R.id.txtLabelSchedule);
        imgFilter = (ImageView) findViewById(R.id.imgFilter);
        imgChangeView = (ImageView) findViewById(R.id.imgChangeView);
        prg_load = (ProgressBar) findViewById(R.id.prg_load);

        txt_date.setText(Config.formatDate());

        imgChangeView.setOnClickListener(this);
        imgFilter.setOnClickListener(this);
        model = new ModelSchedule();
        adapter = model.getAdapterSchedule(0);
        gridRutero.setAdapter(adapter);
        gridRutero.setOnItemClickListener(this);
        ChangeFontStyle.changeFont(txtLabelSchedule);

        ChangeFontStyle.changeFontById(ChangeFontStyle.TYPE_FONT.NORMAL,
                this, R.id.txtmonday, R.id.txtTuesday, R.id.txtWednesday, R.id.txtThursday, R.id.txtFriday, R.id.txtSaturday, R.id.txtSunday);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();
        init();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DtoSchedule dto = adapter.getItem(position);
        dtoBundle = new DtoBundle();
        dtoBundle.setIdPDV(dto.getId_pdv()).setIdRutero(dto.getId_agenda());
        if (model.isSomeReportIncomplete() > 0) {
            Toast.makeText(this, "Tiene un reporte incompleto, debe completarlo", Toast.LENGTH_SHORT).show();
        } else if (model.isMakeTodayThisSchedule(dto.getId_agenda())) {
            Toast.makeText(this, "Ya hizo esta agenda el dia de hoy", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, DetailPdv.class).putExtra(getString(R.string.app_bundle_name), dtoBundle));
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgFilter) {
            DialogFilterSchedule dialogFilter = new DialogFilterSchedule();
            dialogFilter.setData(this).show(getSupportFragmentManager(), "filter");
        } else {
            prg_load.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, VisitByDay.class));
            finish();
        }
    }

    @Override
    public void onDissmisDialogListener(int status, int request, Object object) {

    }

    @Override
    public void onDissmisDialogListener(int status, Object object) {
        if (RESULT_OK == status) {
            adapter = model.getAdapterSchedule(((DtoCatalog) object).getId());
            gridRutero.setAdapter(adapter);
        }
    }
}

