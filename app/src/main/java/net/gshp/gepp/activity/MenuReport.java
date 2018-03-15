package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import net.gshp.apiencuesta.Encuesta;
import net.gshp.apiencuesta.ListEncuestas;
import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVMenuReport;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dialog.DialogEndVisit;
import net.gshp.gepp.dialog.DialogTypeReport;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelMenuReport;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 11/03/18.
 */

public class MenuReport extends AppCompatActivity implements OnItemClickListenerRV, OnDissmisDialogListener, View.OnClickListener {

    private DtoBundle dtoBundle;
    private RecyclerView rvMain;
    private RVMenuReport adapter;
    private ModelMenuReport model;
    private TextView txt_date;
    private ImageButton imbcheckIn, imbcheckOut;
    private boolean isCheckIn, isCheckOut = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_report);
        getSupportActionBar().hide();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_date.setText(Config.formatDate());
        adapter.notifyDataSetChanged();
        rvMain.setAdapter(adapter);
    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        rvMain = (RecyclerView) findViewById(R.id.rvMain);
        model = new ModelMenuReport(dtoBundle, this);
        txt_date = (TextView) findViewById(R.id.txt_date);
        rvMain.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvMain.setLayoutManager(manager);
        ChangeFontStyle.changeFont(txt_date);
        adapter = model.getAdapter(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDissmisDialogListener(int status, int request, Object object) {

    }

    @Override
    public void onDissmisDialogListener(int status, Object object) {
        if (status == RESULT_OK && (Long) object == R.id.action_check_out
                && model.getListMissingReport().length() > 0) {
            DialogTypeReport dialogTypeReport = new DialogTypeReport();
            dialogTypeReport.setOnDissmisDialogListener(this, 1000).
                    show(getSupportFragmentManager(), "dialogTypeReport");
        } else if (status == RESULT_OK && (Long) object == R.id.action_check_out) {
            validate(new Intent(this, CheckOut.class), getResources().getInteger(R.integer.CHECK_OUT));
        } else if (status == 1000) {
            model.updateTypeReport((DtoCatalog) object);
            validate(new Intent(this, CheckOut.class), getResources().getInteger(R.integer.CHECK_OUT));
        } else if (status == R.layout.dialog_time_report) {
            finish();
        }
    }

    @Override
    public void onItemClickListener(View v, int position) {
        if (model.getItemModule(position).getIdItemRelation() == getResources().getInteger(R.integer.EXHIBICIONES)) {
            validate(new Intent(this, ReportExhibition.class), getResources().getInteger(R.integer.EXHIBICIONES));
        } else if (model.getItemModule(position).getIdItemRelation() == getResources().getInteger(R.integer.ENCUESTA)) {
            validate(new Intent(this, ListPoll.class), getResources().getInteger(R.integer.ENCUESTA));
        } else if (model.getItemModule(position).getIdItemRelation() == getResources().getInteger(R.integer.ALERT)) {
            validate(new Intent(this, ScanAlert.class), getResources().getInteger(R.integer.ALERT));
        } else if (model.getItemModule(position).getIdItemRelation() == getResources().getInteger(R.integer.PRE_CHECK_OUT)) {
            validate(new Intent(this, CheckOut.class), getResources().getInteger(R.integer.PRE_CHECK_OUT));
        }
    }

    private void validate(Intent intent, int idModule) {
        if (model.isCheck(ContextApp.context.getResources().getInteger(R.integer.type_check_in)) && idModule == getResources().getInteger(R.integer.CHECK_IN)) {
            Snackbar.make(findViewById(R.id.rlyMain), "YA SE INICIO JORNADA", Snackbar.LENGTH_SHORT).show();
        } else if (!model.isCheck(ContextApp.context.getResources().getInteger(R.integer.type_check_in)) && idModule != getResources().getInteger(R.integer.CHECK_IN)) {
            Snackbar.make(findViewById(R.id.rlyMain), "DEBE INICIAR JORNADA", Snackbar.LENGTH_SHORT).show();
        } else if (model.isCheck(ContextApp.context.getResources().getInteger(R.integer.type_check_out))) {
            Snackbar.make(findViewById(R.id.rlyMain), "YA SE FINALIZO LA JORNADA", Snackbar.LENGTH_SHORT).show();
        } else if (!Config.isDateAutomatic()) {
            Snackbar.make(findViewById(R.id.rlyMain), "DEBE PONER LA HORA EN AUTOMÁTICO", Snackbar.LENGTH_SHORT).show();
        } else if (!Config.isDateAutomatic1()) {
            Snackbar.make(findViewById(R.id.rlyMain), "DEBE PONER ZONA HORARIA EN AUTOMATICO", Snackbar.LENGTH_SHORT).show();
        } else if (Config.isMockLocation()) {
            Snackbar.make(findViewById(R.id.rlyMain), "DEBE DESACTIVAR COORDENADAS FALSAS", Snackbar.LENGTH_SHORT).show();
        } else if (!Config.isGPSenabled()) {
            Snackbar.make(findViewById(R.id.rlyMain), "ACTIVE GPS", Snackbar.LENGTH_SHORT).show();
        } else if (idModule == getResources().getInteger(R.integer.PRE_CHECK_OUT)) {
            DialogEndVisit dialogEndVisit = new DialogEndVisit();
            dialogEndVisit.setOnDissmisDialogListener(this).
                    setData(model.getListMissingReport(), R.id.action_check_out).
                    show(getSupportFragmentManager(), "END VISIT");
        } else {
            startActivityForResult(intent.putExtra(getString(R.string.app_bundle_name), dtoBundle), idModule);
        }
    }
}
