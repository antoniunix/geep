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
import android.view.View;
import android.widget.ImageButton;
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
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 8/03/18.
 */

public class Home extends AppCompatActivity implements OnDissmisDialogListener,View.OnClickListener {

    private TextView txt_name_user, txt_code_user, txt_date, txtNumWeek, txtPorcentWeek, txtAcumulatedAnnual, txtLabelStore, txt_version,
            txtNumWeekEfectividad, txtAcumulatedWeekEfectividad, txtAcumulatedAnnualEfectividad, txtCosto;
    private TextView txtPorcentPerctStore, txtNumPerctStore, txtPorcentSuperiorStore, txtNumSuperiorStore, txtPorcentRegularStore, txtNumRegularStore, txtPorcentCriticaStore, txtNumCriticaStore,
            txtPorcentSinMedicionStore, txtNumSinMedicionStore;
    private ImageView imgStatusPerfect, imgStatusSuperior, imgStatusRegular, imgStatusCritica, imgStatusSinMedicion;
    private TextView txt_head_efectividad, txt_head_acumulado, txtLabelResume, txtPerfect, txtPerctStore, txtSuperior, txtRegular,
            txtCritica, txtSinMedicion, txt_head_costo, txt_head_acumulado1, txtSuperiorStore, txtRegularStore, txtCriticaStore, txtSinMedicionStore;
    private ImageButton btnHelp,btnAccount,btnSynck;
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
        txt_date = (TextView) findViewById(R.id.txt_date);
        txtPorcentWeek = (TextView) findViewById(R.id.txtPorcentWeek);
        txtAcumulatedAnnual = (TextView) findViewById(R.id.txtAcumulatedAnnual);
        txtNumWeekEfectividad = (TextView) findViewById(R.id.txtNumWeekEfectividad);
        txtAcumulatedWeekEfectividad = (TextView) findViewById(R.id.txtAcumulatedWeekEfectividad);
        txtAcumulatedAnnualEfectividad = (TextView) findViewById(R.id.txtAcumulatedAnnualEfectividad);
        btnHelp=(ImageButton) findViewById(R.id.btnHelp);
        btnAccount=(ImageButton) findViewById(R.id.btnAccount);
        btnSynck=(ImageButton) findViewById(R.id.btnSynck);

        btnHelp.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnSynck.setOnClickListener(this);
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
        getSupportActionBar().hide();
        init();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSynck) {
            if (preferences.getString(getString(R.string.app_share_preference_user_account), null) != null) {
                DialogSync diFragmentSync = new DialogSync();
                diFragmentSync.setCancelable(false);
                diFragmentSync.show(getSupportFragmentManager(), "DialogFragmentSync");
            } else {
                new DialogAccount().show(getSupportFragmentManager(), "Fragment_dialog_account");
            }
        } else if (v.getId() == R.id.btnAccount) {
            new DialogAccount().show(getSupportFragmentManager(), "Fragment_dialog_account");
        } else if (v.getId() == R.id.action_send_db) {
            new Thread() {
                @Override
                public void run() {
                    new ModelSendDataBase().sendBD();
                }
            }.start();
        }
        else if (v.getId() == R.id.action_change_pass) {
            DialogChangePassword dialogChangePassword = new DialogChangePassword();
            dialogChangePassword.show(getSupportFragmentManager(), "Dialog Change Password");
        } else if (v.getId() == R.id.btnHelp) {
            DialogHelp dialogHelp = new DialogHelp();
            dialogHelp.show(getSupportFragmentManager(), "dialogHelp");
        }
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
        txt_date.setText(Config.formatDate());
    }


}
