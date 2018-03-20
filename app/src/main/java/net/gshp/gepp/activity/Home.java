package net.gshp.gepp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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
import net.gshp.gepp.dto.DtoUserInfo;
import net.gshp.gepp.geolocation.AlarmGeolocation;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.model.ModelAHBottomNavigation;
import net.gshp.gepp.model.ModelHome;
import net.gshp.gepp.model.ModelSendDataBase;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 8/03/18.
 */

public class Home extends AppCompatActivity implements OnDissmisDialogListener, View.OnClickListener {

    private TextView txt_name_user, txt_code_user, txt_date, txtNumWeek, txtPorcentWeek, txtAcumulatedAnnual, txtLabelStore, txt_version,
            txtNumWeekEfectividad, txtAcumulatedWeekEfectividad, txtAcumulatedAnnualEfectividad, txtCosto;
    private TextView txtPorcentPerctStore, txtNumPerctStore, txtPorcentSuperiorStore, txtNumSuperiorStore, txtPorcentRegularStore, txtNumRegularStore, txtPorcentCriticaStore, txtNumCriticaStore,
            txtPorcentSinMedicionStore, txtNumSinMedicionStore;
    private TextView txtNormal, txtNormalStore, txtPorcentNormalStore, txtNumNormalStore;
    private ImageView imgStatusPerfect, imgStatusSuperior, imgStatusRegular, imgStatusCritica, imgStatusSinMedicion;
    private TextView txt_head_efectividad, txt_head_acumulado, txtLabelResume, txtPerfect, txtPerctStore, txtSuperior, txtRegular,
            txtCritica, txtSinMedicion, txt_head_costo, txt_head_acumulado1, txtSuperiorStore, txtRegularStore, txtCriticaStore, txtSinMedicionStore;
    private ImageButton btnHelp, btnAccount, btnSynck;
    private TextView txtBasica, txtNumBasicaStore, txtPorcentBasicaStor, txtsaboresLable, txtGatoradeLabel;
    private BottomNavigationView bottomNavigationView;
    private DtoCoopGeneral dtoCoopGeneral;
    private ModelHome modelHome;
    private SharedPreferences preferences;
    private ModelAHBottomNavigation modelAHBottomNavigation;
    private TextView txtcolas, txtsabores, txtagua, txgatorade, txtlipton, txtJumex;
    private DtoUserInfo dtoUserInfo;

    private void init() {
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
        txtCosto = (TextView) findViewById(R.id.txtCosto);
        btnHelp = (ImageButton) findViewById(R.id.btnHelp);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnSynck = (ImageButton) findViewById(R.id.btnSynck);
        txtSuperior = (TextView) findViewById(R.id.txtSuperior);
        txtSuperiorStore = (TextView) findViewById(R.id.txtSuperiorStore);
        txtPorcentSuperiorStore = (TextView) findViewById(R.id.txtPorcentSuperiorStore);
        txtNumSuperiorStore = (TextView) findViewById(R.id.txtNumSuperiorStore);
        txtNormal = (TextView) findViewById(R.id.txtNormal);
        txtNormalStore = (TextView) findViewById(R.id.txtNormalStore);
        txtPorcentNormalStore = (TextView) findViewById(R.id.txtPorcentNormalStore);
        txtNumNormalStore = (TextView) findViewById(R.id.txtNumNormalStore);
        txtsaboresLable = (TextView) findViewById(R.id.txtsaboreslabel);
        txtGatoradeLabel = (TextView) findViewById(R.id.txtGatoradeLabel);
        txtBasica = (TextView) findViewById(R.id.txtBasica);
        txtNumBasicaStore = (TextView) findViewById(R.id.txtNumBasicaStore);
        txtPorcentBasicaStor = (TextView) findViewById(R.id.txtPorcentBasicaStore);
        txtCritica = (TextView) findViewById(R.id.txtCritica);
        txtCriticaStore = (TextView) findViewById(R.id.txtCriticaStore);
        txtNumCriticaStore = (TextView) findViewById(R.id.txtNumCriticaStore);
        txtPorcentCriticaStore = (TextView) findViewById(R.id.txtPorcentCriticaStore);
        txtSinMedicion = (TextView) findViewById(R.id.txtSinMedicion);
        txtSinMedicionStore = (TextView) findViewById(R.id.txtSinMedicionStore);
        txtNumSinMedicionStore = (TextView) findViewById(R.id.txtNumSinMedicionStore);
        txtPorcentSinMedicionStore = (TextView) findViewById(R.id.txtPorcentSinMedicionStore);
        txtsabores = (TextView) findViewById(R.id.txtsabores);
        txtsabores.setSelected(true);
        txtcolas = (TextView) findViewById(R.id.txtcolas);
        txtcolas.setSelected(true);
        txtagua = (TextView) findViewById(R.id.txtagua);
        txtagua.setSelected(true);
        txgatorade = (TextView) findViewById(R.id.txgatorade);
        txgatorade.setSelected(true);
        txtlipton = (TextView) findViewById(R.id.txtlipton);
        txtlipton.setSelected(true);
        txtJumex = (TextView) findViewById(R.id.txtjumex);
        txtJumex.setSelected(true);
        txtGatoradeLabel.setSelected(true);
        txtsaboresLable.setSelected(true);


        btnHelp.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnSynck.setOnClickListener(this);
        String version = "";
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        setInfoUser();

    }

    private void setInfoUser() {
        dtoUserInfo = modelHome.getUserInfo();
        txtNumWeek.setText(dtoUserInfo.getNumero_semana());
        txtNumWeekEfectividad.setText("SEMANA" + dtoUserInfo.getNumero_semana());
        txtAcumulatedAnnualEfectividad.setText(dtoUserInfo.getEfectividad_acumulado_anual());
        txtNumWeek.setText("SEMANA" + dtoUserInfo.getNumero_semana());
        txtCosto.setText("$" + dtoUserInfo.getCosto_inasistencia());
        txtAcumulatedWeekEfectividad.setText(dtoUserInfo.getEfectividad_porcentaje_semana() + "%");
        txtPorcentWeek.setText(dtoUserInfo.getEfectividad_porcentaje_semana() + "%");
        txtAcumulatedAnnual.setText(dtoUserInfo.getFoto_exito() + "%");
        txtNumSuperiorStore.setText(dtoUserInfo.getSuperior_tiendas() == null ? "--" : dtoUserInfo.getSuperior_tiendas());
        txtPorcentSuperiorStore.setText(dtoUserInfo.getSuperior_porcentaje() == null ? "--" : dtoUserInfo.getSuperior_porcentaje() + "%");
        txtNumNormalStore.setText(dtoUserInfo.getNormal_tiendas() == null ? "--" : dtoUserInfo.getNormal_tiendas());
        txtPorcentNormalStore.setText(dtoUserInfo.getNormal_porcentaje() == null ? "--" : dtoUserInfo.getNormal_porcentaje() + "%");
        txtNumBasicaStore.setText(dtoUserInfo.getBasica_tiendas() == null ? "--" : dtoUserInfo.getBasica_tiendas());
        txtPorcentBasicaStor.setText(dtoUserInfo.getBasica_porcentaje() == null ? "--" : dtoUserInfo.getBasica_porcentaje() + "%");
        txtNumCriticaStore.setText(dtoUserInfo.getCritica_tiendas() == null ? "--" : dtoUserInfo.getCritica_tiendas());
        txtPorcentCriticaStore.setText(dtoUserInfo.getCritica_porcentaje() == null ? "--" : dtoUserInfo.getCritica_porcentaje() + "%");
        txtNumSinMedicionStore.setText(dtoUserInfo.getSinmedicion_tienda() == null ? "--" : dtoUserInfo.getSinmedicion_tienda());
        txtPorcentSinMedicionStore.setText(dtoUserInfo.getSinmedicion_porcentaje() == null ? "--" : dtoUserInfo.getSinmedicion_porcentaje() + "%");
        txtcolas.setText(dtoUserInfo.getColas());
        txtcolas.setBackgroundColor(Color.parseColor(dtoUserInfo.getColas_color()));
        txtsabores.setText(dtoUserInfo.getSabores());
        txtsabores.setBackgroundColor(Color.parseColor(dtoUserInfo.getSabores_color()));
        txtagua.setText(dtoUserInfo.getAgua());
        txtagua.setBackgroundColor(Color.parseColor(dtoUserInfo.getAgua_color()));
        txgatorade.setText(dtoUserInfo.getGatorade());
        txgatorade.setBackgroundColor(Color.parseColor(dtoUserInfo.getGatorade_color()));
        txtlipton.setText(dtoUserInfo.getLipton());
        txtlipton.setBackgroundColor(Color.parseColor(dtoUserInfo.getLipton_color()));
        txtJumex.setText(dtoUserInfo.getJumex());
        txtJumex.setBackgroundColor(Color.parseColor(dtoUserInfo.getJumex_color()));

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
        } else if (v.getId() == R.id.action_change_pass) {
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
