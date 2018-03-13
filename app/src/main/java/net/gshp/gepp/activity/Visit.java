package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVVisit;
import net.gshp.gepp.dialog.DialogDelete;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelAHBottomNavigation;
import net.gshp.gepp.model.ModelVisit;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 11/03/18.
 */

public class Visit extends AppCompatActivity implements OnItemClickListenerRV, OnDissmisDialogListener {

    private ModelVisit model;
    private RecyclerView rcv_visit;
    private RVVisit adapter;
    private ModelAHBottomNavigation modelAHBottomNavigation;
    private TextView txt_date;

    public void init() {
        model = new ModelVisit();
        modelAHBottomNavigation = new ModelAHBottomNavigation(this);
        rcv_visit = (RecyclerView) findViewById(R.id.rcv_visit);
        txt_date = (TextView) findViewById(R.id.txt_date);

        adapter = model.getAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        rcv_visit.setLayoutManager(manager);
        ChangeFontStyle.changeFont(txt_date);
        txt_date.setText(Config.formatDate());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        getSupportActionBar().hide();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rcv_visit.setAdapter(adapter);
        modelAHBottomNavigation.onResume();
    }

    @Override
    public void onItemClickListener(View v, int position) {


        if (v.getId() == R.id.lny_main) {
            DtoBundle dtoBundle = new DtoBundle();
            dtoBundle.setIdPDV(adapter.getItem(position).getIdPdv()).
                    setIdReportLocal(adapter.getItem(position).getId());
            startActivity(new Intent(this, MenuReport.class).putExtra(getString(R.string.app_bundle_name), dtoBundle));
            finish();
        } else if (v.getId() == R.id.imgTrash) {
            DialogDelete dialog = new DialogDelete();
            dialog.setData(model.getItemPosition(position).getNombrePdv(),
                    model.getItemPosition(position).getId()).setOnDissmisDialogListener(this).
                    show(getSupportFragmentManager(), getString(R.string.label_delete_visit));
        }

    }


    @Override
    public void onDissmisDialogListener(int status, int request, Object object) {

    }

    @Override
    public void onDissmisDialogListener(int status, Object object) {
        if ((long) object > 0) {
            model.deleteVisit((long) object);
            adapter = model.getAdapter(this);
            rcv_visit.setAdapter(adapter);
        }
    }
}

