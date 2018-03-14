package net.gshp.gepp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoPolitic;
import net.gshp.gepp.dialog.DialogChangePassword;
import net.gshp.gepp.dialog.DialogPrivacyPolitic;
import net.gshp.gepp.dialog.DialogUpdateApp;
import net.gshp.gepp.dto.DtoPolitc;
import net.gshp.gepp.listener.OnProgressSync;
import net.gshp.gepp.model.ModelSync;

import org.apache.http.HttpStatus;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

/**
 * Created by leo on 6/03/18.
 */

public class Login extends AppCompatActivity implements View.OnClickListener, OnProgressSync {

    private Button btn_sync, btn_sync_agree, btn_sync_cancel, btn_next;
    private ProgressBar id_progressbar;
    private SharedPreferences prefs;
    private EditText edt_user_name, edt_pass;
    private RelativeLayout rlt_authentication, rlyt_delete_data, rlyt_sync;
    private TextView txtPorcent;
    private int statusSync;
    private ModelSync model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EventBus.getDefault().register(this);
        init();

    }

    private void init() {
        File f = new File(getString(R.string.app_path_photo));
        if (!f.exists()) {
            if (!f.mkdir()) {
            }
        }
        DtoPolitc dtoPolitc = new DaoPolitic().Select();
        String version = dtoPolitc.getVersion() == null || dtoPolitc.getValue().isEmpty() ? "TERMS_1.0" : dtoPolitc.getVersion();
        prefs = getSharedPreferences(getString(R.string.app_share_preference_name), Context.MODE_PRIVATE);

        if (!prefs.getString(getResources().getString(R.string.app_share_preference_privacy_politic), "").equals(version)) {
            DialogPrivacyPolitic dialogPrivacyPolitic = new DialogPrivacyPolitic();
            dialogPrivacyPolitic.setCancelable(false);
            dialogPrivacyPolitic.show(getSupportFragmentManager(), "DialogPolit");
        }
        model = new ModelSync(this, this);
        if(googlePlayServicesAvailability()){
            if((System.currentTimeMillis()-prefs.getLong(getResources().getString(R.string.app_share_preference_time_synch), 0L))
                >getResources().getInteger(R.integer.time_synch)) {
                id_progressbar = (ProgressBar) findViewById(R.id.id_progressbar);
                btn_sync = (Button) findViewById(R.id.btn_sync);
                btn_sync_agree = (Button) findViewById(R.id.btn_sync_agree);
                btn_sync_cancel = (Button) findViewById(R.id.btn_sync_cancel);
                btn_next = (Button) findViewById(R.id.btn_next);
                edt_user_name = (EditText) findViewById(R.id.edt_user_name);
                edt_pass = (EditText) findViewById(R.id.edt_pass);
                rlt_authentication = (RelativeLayout) findViewById(R.id.rlt_authentication);
                rlyt_delete_data = (RelativeLayout) findViewById(R.id.rlyt_delete_data);
                rlyt_sync = (RelativeLayout) findViewById(R.id.rlyt_sync);
                txtPorcent = (TextView) findViewById(R.id.txtPorcent);
                btn_sync.setOnClickListener(this);
                btn_sync_agree.setOnClickListener(this);
                btn_sync_cancel.setOnClickListener(this);
                btn_next.setOnClickListener(this);
                edt_user_name.setText(prefs.getString(getString(R.string.app_share_preference_user_account), ""));
                edt_pass.setText(prefs.getString(getString(R.string.app_share_preference_user_pass), ""));
            }else {
                startActivity(new Intent(this,Home.class));
                finish();
            }
        }

    }

    private boolean googlePlayServicesAvailability() {
        int checkGooglePlayServices = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ContextApp.context);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices, this, 1122).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sync:
                if (edt_user_name.getText().toString().isEmpty()) {
                    edt_user_name.setError(getString(R.string.error_empty_user_name));
                } else if (edt_pass.getText().toString().isEmpty()) {
                    edt_pass.setError(getString(R.string.error_empty_password));
                } else if (prefs.getString(getString(R.string.app_share_preference_user_account), null) != null &&
                        !prefs.getString(getString(R.string.app_share_preference_user_account), "").equals(edt_user_name.getText().toString().trim())) {
                    rlt_authentication.setVisibility(View.GONE);
                    rlyt_delete_data.setVisibility(View.VISIBLE);
                    rlyt_sync.setVisibility(View.GONE);
                } else {
                    prefs.edit().putString(getString(R.string.app_share_preference_user_account), edt_user_name.getText().toString().trim()).apply();
                    prefs.edit().putString(getString(R.string.app_share_preference_user_pass), edt_pass.getText().toString().trim()).apply();
                    rlt_authentication.setVisibility(View.GONE);
                    rlyt_delete_data.setVisibility(View.GONE);
                    rlyt_sync.setVisibility(View.VISIBLE);
                    model.setAuthentication();
                    txtPorcent.setText("% 0");

                }
                break;
            case R.id.btn_sync_agree:
                rlt_authentication.setVisibility(View.GONE);
                rlyt_delete_data.setVisibility(View.GONE);
                rlyt_sync.setVisibility(View.VISIBLE);
                prefs.edit().putString(getString(R.string.app_share_preference_user_account), edt_user_name.getText().toString().trim()).apply();
                prefs.edit().putString(getString(R.string.app_share_preference_user_pass), edt_pass.getText().toString().trim()).apply();
                deleteDatabase(getResources().getString(R.string.app_db_name));
                startActivity(new Intent(this,Splash.class));
                finish();
                break;
            case R.id.btn_sync_cancel:
                rlt_authentication.setVisibility(View.VISIBLE);
                rlyt_delete_data.setVisibility(View.GONE);
                rlyt_sync.setVisibility(View.GONE);
                break;
            case R.id.btn_next:
                switch (statusSync) {
                    case HttpStatus.SC_OK:
                        startActivity(new Intent(this, Home.class));
                        finish();
                        break;
                    case HttpStatus.SC_UNAUTHORIZED:
                        btn_next.setVisibility(View.VISIBLE);
                        rlt_authentication.setVisibility(View.VISIBLE);
                        btn_next.setVisibility(View.GONE);
                        rlyt_delete_data.setVisibility(View.GONE);
                        rlyt_sync.setVisibility(View.GONE);
//                        DialogAccount dialogAccount = new DialogAccount();
//                        dialogAccount.show(getSupportFragmentManager(), "dialog");
                        break;
                    case HttpStatus.SC_PAYMENT_REQUIRED:

                        DialogChangePassword dialogChangePassword = new DialogChangePassword();
                        dialogChangePassword.show(getSupportFragmentManager(),"dialogChange" );
                        rlt_authentication.setVisibility(View.VISIBLE);
                        btn_next.setVisibility(View.GONE);
                        rlyt_delete_data.setVisibility(View.GONE);
                        rlyt_sync.setVisibility(View.GONE);
                        break;

                    default:
                        btn_next.setVisibility(View.GONE);
                        rlt_authentication.setVisibility(View.VISIBLE);
                        rlyt_delete_data.setVisibility(View.GONE);
                        rlyt_sync.setVisibility(View.GONE);
                        break;
                }
                break;
        }

    }



    @Override
    public void onProgresSync(int porcentOfProgress, int httpstatus, String service) {
        id_progressbar.setProgress(porcentOfProgress);
        txtPorcent.setText("%" + porcentOfProgress);
    }

    @Override
    public void onNewVersionExist(boolean isExist) {
        if (isExist) {
            DialogUpdateApp dialogUpdateApp = new DialogUpdateApp();
            dialogUpdateApp.setCancelable(false);
            dialogUpdateApp.show(getSupportFragmentManager(), "Dialog Update App");

        }
    }

    @Override
    public void onFinishSync(int httpstatus, String response, Object obj) {
        statusSync = httpstatus;
        btn_next.setVisibility(View.VISIBLE);
        switch (httpstatus) {
            case HttpStatus.SC_OK:
//                prefs.edit().putLong(getString(R.string.app_share_time_last_sync), System.currentTimeMillis()).apply();
//                if (!prefs.getBoolean(getString(R.string.terms_and_conditions_sharepreference_is_accepted), false)) {
//                    startActivityForResult(new Intent(this, TermsAndConditions.class), 1);
//                }
                prefs.edit().putLong(getResources().getString(R.string.app_share_preference_time_synch), System.currentTimeMillis())
                        .commit();
                break;
            case HttpStatus.SC_CONFLICT:
                txtPorcent.setText(getString(R.string.network_sc_conflict));
                break;
            case HttpStatus.SC_UNAUTHORIZED:
                txtPorcent.setText(getString(R.string.network_sc_unauthorized));
                break;
            case HttpStatus.SC_NO_CONTENT:
                txtPorcent.setText(getString(R.string.network_no_content));
                break;
            case HttpStatus.SC_FORBIDDEN:
                txtPorcent.setText(getString(R.string.network_forbidden));
                break;
            case HttpStatus.SC_METHOD_FAILURE:
                prefs.edit().putInt(getString(R.string.app_share_preference_count_unauthorized), 1).commit();
                txtPorcent.setText(obj.toString());
                break;
            case HttpStatus.SC_PAYMENT_REQUIRED:
                txtPorcent.setText(getString(R.string.network_sc_precondition_failed));
                break;
            case HttpStatus.SC_BAD_GATEWAY:
                txtPorcent.setText(getString(R.string.network_error));
                break;
            case HttpStatus.SC_SERVICE_UNAVAILABLE:
                txtPorcent.setText(R.string.network_server_disable);
                break;

        }
    }
    @Subscribe
    public void onEvent(Integer event) {
        startActivity(new Intent(this, Home.class));
        finish();
    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
