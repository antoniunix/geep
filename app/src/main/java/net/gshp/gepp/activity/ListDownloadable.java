package net.gshp.gepp.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import net.gshp.gepp.R;
import net.gshp.gepp.adapter.AdapterDownload;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dialog.DialogDownLoadFile;
import net.gshp.gepp.dto.DtoDownLoadDetail;
import net.gshp.gepp.dto.DtoMeasurementDownloadSku;
import net.gshp.gepp.listener.OnFilterListener;
import net.gshp.gepp.model.ModelDownload;
import net.gshp.gepp.util.MD5;

import java.io.File;

/**
 * Created by leo on 13/03/18.
 */

public class ListDownloadable extends AppCompatActivity implements View.OnClickListener, OnFilterListener {

    private ListView lstDownload;
    private EditText edt_search;
    private AdapterDownload adapterDownload;
    private ModelDownload modelDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_download);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        File f = new File(getString(R.string.app_path_photo));
        if (!f.exists()) {
            if (!f.mkdirs()) {
                Log.e("Error : ", "Problem creating Image folder");
            }
        }

        modelDownload = new ModelDownload();
        lstDownload = (ListView) findViewById(R.id.lst_download);
        modelDownload = new ModelDownload(this);
        adapterDownload = (AdapterDownload) modelDownload.getAdapter();
        lstDownload.setAdapter(adapterDownload);
        edt_search = (EditText) findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapterDownload.getFilter().filter(charSequence.toString());
                lstDownload.setAdapter(adapterDownload);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lstDownload.setAdapter(modelDownload.getAdapter());
    }

    @Override
    public void onClick(View v) {

        DtoMeasurementDownloadSku dto = (DtoMeasurementDownloadSku) v.getTag();
        fileExist(dto);


    }

    public void openFile(DtoMeasurementDownloadSku dto) {
        String typeFile = dto.getExt();
        File file = new File(dto.getNameFile());
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (typeFile.equals(".pdf")) {


            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (typeFile.equals(".mp4") || typeFile.equals(".avi") || typeFile.equals(".mpg")) {
            intent.setDataAndType(path, "video/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        } else if (typeFile.equals(".png") || typeFile.equals(".jpg")) {
            intent.setDataAndType(path, "image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        try {
            startActivityForResult(intent, 0);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No tiene aplicacion para ver el documento",
                    Toast.LENGTH_SHORT).show();
        }
        DtoDownLoadDetail dtoDownLoadDetail = new DtoDownLoadDetail();
        dtoDownLoadDetail.setIdFile(dto.getIdItemRelation());
        modelDownload.saveFileDetail(dtoDownLoadDetail);
    }

    public void fileExist(DtoMeasurementDownloadSku dto) {
        String typeFile = dto.getExt();
        String nameFile = ContextApp.context.getResources().getString(R.string.app_path_photo) + dto.getIdItemRelation() + "_" + dto.getTitle() + typeFile;

        File file = new File(nameFile);
        dto.setNameFile(nameFile);
        if (file.exists() && dto.getMd5().contains(MD5.fileToMD5(nameFile))) {
            openFile(dto);
        } else {
            if (file.exists()) {
                file.delete();
            }
            if (isNetwork()) {
                DialogDownLoadFile dialogDownLoadFile = new DialogDownLoadFile();
                FragmentManager fm = getSupportFragmentManager();
                dto.setNameFile(nameFile);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ContextApp.context.getResources().getString(R.string.app_bundle_name), dto);
                dialogDownLoadFile.setArguments(bundle);
                dialogDownLoadFile.show(fm, "Down");
                dialogDownLoadFile.setCancelable(false);
            } else {
                Toast.makeText(this, "Verifique que su dispositivo cuente con Internet",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void setOnFilter(String filter1) {
        adapterDownload.getFilter().filter(filter1);
    }

    public static boolean isNetwork() {
        ConnectivityManager connec = (ConnectivityManager) ContextApp.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo red = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected() || red.isConnected()) {
            Log.e("network", "Wifi " + wifi.isConnected() + " rec " + red.isConnected());
            return true;
        }
        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK || requestCode == 1) {
            finish();
        }
    }

}