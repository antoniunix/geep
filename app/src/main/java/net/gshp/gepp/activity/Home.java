package net.gshp.gepp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dialog.DialogAccount;
import net.gshp.gepp.dialog.DialogChangePassword;
import net.gshp.gepp.dialog.DialogHelp;
import net.gshp.gepp.dialog.DialogSync;
import net.gshp.gepp.dto.DtoCoopGeneral;
import net.gshp.gepp.geolocation.AlarmGeolocation;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.model.ModelAHBottomNavigation;
import net.gshp.gepp.model.ModelHome;
import net.gshp.gepp.model.ModelSendDataBase;

/**
 * Created by leo on 8/03/18.
 */

public class Home extends AppCompatActivity implements OnDissmisDialogListener {

    private TextView txt_name_user, txt_code_user, txt_date, txtNumWeek, txtPorcentWeek, txtAcumulatedAnnual, txtLabelStore, txt_version,
            txtNumWeekEfectividad, txtAcumulatedWeekEfectividad, txtAcumulatedAnnualEfectividad, txtCosto;
    private TextView txtPorcentPerctStore, txtNumPerctStore, txtPorcentSuperiorStore, txtNumSuperiorStore, txtPorcentRegularStore, txtNumRegularStore, txtPorcentCriticaStore, txtNumCriticaStore,
            txtPorcentSinMedicionStore, txtNumSinMedicionStore;
    private ImageView imgStatusPerfect, imgStatusSuperior, imgStatusRegular, imgStatusCritica, imgStatusSinMedicion;
    private TextView txt_head_efectividad, txt_head_acumulado, txtLabelResume, txtPerfect, txtPerctStore, txtSuperior, txtRegular,
            txtCritica, txtSinMedicion, txt_head_costo, txt_head_acumulado1, txtSuperiorStore, txtRegularStore, txtCriticaStore, txtSinMedicionStore;
    private BottomNavigationView bottomNavigationView;
    private DtoCoopGeneral dtoCoopGeneral;
    private ModelHome modelHome;
    private SharedPreferences preferences;
    private ModelAHBottomNavigation modelAHBottomNavigation;

    private void init(){
        AlarmGeolocation.getInstance();
        modelHome = new ModelHome();
        modelAHBottomNavigation = new ModelAHBottomNavigation(this);
        preferences = getSharedPreferences(getString(R.string.app_share_preference_name), Context.MODE_PRIVATE);
        txtLabelStore = (TextView) findViewById(R.id.txtLabelStore);
        txt_name_user = (TextView) findViewById(R.id.txt_name_user);
        txt_code_user = (TextView) findViewById(R.id.txt_code_user);
        txtNumWeek = (TextView) findViewById(R.id.txtNumWeek);
        txtPorcentWeek = (TextView) findViewById(R.id.txtPorcentWeek);
        txtAcumulatedAnnual = (TextView) findViewById(R.id.txtAcumulatedAnnual);
        txtNumWeekEfectividad = (TextView) findViewById(R.id.txtNumWeekEfectividad);
        txtAcumulatedWeekEfectividad = (TextView) findViewById(R.id.txtAcumulatedWeekEfectividad);
        txtAcumulatedAnnualEfectividad = (TextView) findViewById(R.id.txtAcumulatedAnnualEfectividad);
        String version = "";
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.logo);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        init();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sync) {
            if (preferences.getString(getString(R.string.app_share_preference_user_account), null) != null) {
                DialogSync diFragmentSync = new DialogSync();
                diFragmentSync.setCancelable(false);
                diFragmentSync.show(getSupportFragmentManager(), "DialogFragmentSync");
            } else {
                new DialogAccount().show(getSupportFragmentManager(), "Fragment_dialog_account");
            }
            return true;
        } else if (id == R.id.action_account) {
            new DialogAccount().show(getSupportFragmentManager(), "Fragment_dialog_account");
            return true;
        } else if (id == R.id.action_send_db) {
            new Thread() {
                @Override
                public void run() {
                    new ModelSendDataBase().sendBD();
                }
            }.start();
            return true;
        }
//        else if (id == R.id.action_about) {
//            new DialogAbout().show(getSupportFragmentManager(), "Dialog About");
//            return true;
//        }
        else if (id == R.id.action_change_pass) {
            DialogChangePassword dialogChangePassword = new DialogChangePassword();
            dialogChangePassword.show(getSupportFragmentManager(), "Dialog Change Password");
            return true;
        } else if (id == R.id.action_help) {
            DialogHelp dialogHelp = new DialogHelp();
            dialogHelp.show(getSupportFragmentManager(), "dialogHelp");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDissmisDialogListener(int status, int request, Object object) {

    }

    @Override
    public void onDissmisDialogListener(int status, Object object) {
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        modelAHBottomNavigation.onResume();
    }
}
