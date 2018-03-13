package net.gshp.gepp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.gshp.gepp.R;
import net.gshp.gepp.dialog.DialogHistoricScanAlert;
import net.gshp.gepp.dialog.DialogStatusScannAlert;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportScannAlert;
import net.gshp.gepp.listener.DismissDialogStatusScann;
import net.gshp.gepp.listener.OnItemClickListener;
import net.gshp.gepp.model.ModelScannAlert;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class ScanAlert extends AppCompatActivity implements OnItemClickListener, DismissDialogStatusScann, View.OnClickListener {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<DtoReportScannAlert> lst;
    private Context context;
    private ModelScannAlert modelScannAlert;
    private DtoBundle dtoBundle;
    private RecyclerView.Adapter adapter;
    private ImageView action_filter, action_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_alert);
        getSupportActionBar().hide();
        init();
        adapter = modelScannAlert.getAdapterV2(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void init() {
        context = this;
        action_filter = (ImageView) findViewById(R.id.action_filter);
        action_save = (ImageView) findViewById(R.id.action_save);
        action_save.setOnClickListener(this);
        action_filter.setOnClickListener(this);
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        modelScannAlert = new ModelScannAlert(dtoBundle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scann_alert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            FragmentManager fm = getSupportFragmentManager();
            DialogHistoricScanAlert dialog = new DialogHistoricScanAlert();
            dialog.setIdPdv(dtoBundle.getIdPDV());
            dialog.show(fm, "historic");
            return true;
        } else if (id == R.id.action_save) {
            int status;
            if ((status = modelScannAlert.saveReport()) != -1) {
                Toast.makeText(this, "debe completar la medición", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "se guardó correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(View view, int position, Object obj) {

        FragmentManager fm = getSupportFragmentManager();
        DialogStatusScannAlert dialogReportType = new DialogStatusScannAlert();
        dialogReportType.show(fm, "dialogReportType");
        dialogReportType.setpositionListSku(position, ((DtoReportScannAlert) obj).getIdTp());
        dialogReportType.setOnDismissDialog(this);
    }


    @Override
    public void onDismissDialog(int position, long status) {
        modelScannAlert.getItemReportScannAlert(position).setStatus(status).setColor(modelScannAlert.getColorOfStatus(status));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.action_filter) {
            FragmentManager fm = getSupportFragmentManager();
            DialogHistoricScanAlert dialog = new DialogHistoricScanAlert();
            dialog.setIdPdv(dtoBundle.getIdPDV());
            dialog.show(fm, "historic");
        } else if (view.getId() == R.id.action_save) {
            int status;
            if ((status = modelScannAlert.saveReport()) != -1) {
                Toast.makeText(this, "debe completar la medición", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "se guardó correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
