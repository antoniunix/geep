package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVVisitByDay;
import net.gshp.gepp.dialog.DialogFilterSchedule;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoSchedule;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelAHBottomNavigation;
import net.gshp.gepp.model.ModelVisitByDay;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 8/03/18.
 */

public class VisitByDay extends AppCompatActivity implements OnItemClickListenerRV, View.OnClickListener, OnDissmisDialogListener {

    private RecyclerView rv_visit;
    private ModelVisitByDay model;
    private RVVisitByDay adapter;
    private DtoBundle dtoBundle;
    private TextView txt_date, txtLabelSchedule;
    private ProgressBar prg_load;
    private ImageView imgFilter, imgChangeView;
    private ModelAHBottomNavigation modelAHBottomNavigation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_by_day);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        modelAHBottomNavigation = new ModelAHBottomNavigation(this);
        rv_visit = (RecyclerView) findViewById(R.id.rv_visit);
        txtLabelSchedule = (TextView) findViewById(R.id.txtLabelSchedule);
        txt_date = (TextView) findViewById(R.id.txt_date);
        imgFilter = (ImageView) findViewById(R.id.imgFilter);
        imgChangeView = (ImageView) findViewById(R.id.imgChangeView);
        prg_load = (ProgressBar) findViewById(R.id.prg_load);
        txt_date.setText(Config.formatDate());
        imgChangeView.setOnClickListener(this);
        imgFilter.setOnClickListener(this);
        model = new ModelVisitByDay();
        adapter = model.getAdapter(this, 0);
        LinearLayoutManager lmy = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_visit.setAdapter(adapter);
        rv_visit.setLayoutManager(lmy);

        ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.BOLD, txtLabelSchedule);

    }

    @Override
    protected void onResume() {
        super.onResume();
        modelAHBottomNavigation.onResume();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgFilter) {
            DialogFilterSchedule dialogFilter = new DialogFilterSchedule();
            dialogFilter.setData(this).show(getSupportFragmentManager(), "filter");
        } else {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    prg_load.setVisibility(View.VISIBLE);
                }
            });
            startActivity(new Intent(VisitByDay.this, Schedule.class));
            finish();
        }
    }

    @Override
    public void onDissmisDialogListener(int status, int request, Object object) {

    }

    @Override
    public void onDissmisDialogListener(int status, Object object) {
        adapter = model.getAdapter(this, ((DtoCatalog) object).getId());
        rv_visit.setAdapter(adapter);
    }

    @Override
    public void onItemClickListener(View v, int position) {
        DtoSchedule dto = adapter.getItem(position);
        dtoBundle = new DtoBundle();
        dtoBundle.setIdPDV(dto.getId_pdv()).setIdRutero(dto.getId_agenda());

        if (model.isSomeReportIncomplete() > 0) {
            Toast.makeText(this, "Tiene un reporte incompleto, debe completarlo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Visit.class));
            finish();
        } else if (model.isMakeTodayThisSchedule(dto.getId_agenda())) {
            Toast.makeText(this, "Ya hizo esta agenda el dia de hoy", Toast.LENGTH_SHORT).show();
        } else {
            startActivityForResult(new Intent(this, DetailPdv.class).putExtra(getString(R.string.app_bundle_name), dtoBundle), 1);
            finish();
        }
    }


}
