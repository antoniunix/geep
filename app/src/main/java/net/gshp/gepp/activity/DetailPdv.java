package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Line;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.geolocation.ServiceCheck;
import net.gshp.gepp.model.ModelDetailPdv;
import net.gshp.gepp.model.ModelMenuReport;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 10/03/18.
 */

public class DetailPdv extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private DtoBundle dtoBundle;
    private Button btninit, btncancel;
    private ScrollView scroll;
    private MapView mapView;
    private GoogleMap map;
    private ModelMenuReport modelMenuReport;
    private LinearLayout lyt_scorecard_info;
    private TextView txtpdvName, txtAddress, txt_date,txtNamePdv;
    private ModelDetailPdv model;
    private DtoPdv dtoPdv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pdv);
        getSupportActionBar().hide();
        init();
        mapView.onCreate(savedInstanceState);
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            mapView.getMapAsync(this);
        }
    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        modelMenuReport = new ModelMenuReport(dtoBundle, this);
        model = new ModelDetailPdv();
        dtoPdv = model.getPdv(dtoBundle.getIdPDV());
        btninit = (Button) findViewById(R.id.btninit);
        btncancel = (Button) findViewById(R.id.btncancel);
        txtpdvName = (TextView) findViewById(R.id.txtpdvName);
        txtNamePdv = (TextView) findViewById(R.id.txtNamePdv);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txt_date = (TextView) findViewById(R.id.txt_date);
        btninit.setOnClickListener(this);
        btncancel.setOnClickListener(this);
        mapView = (MapView) findViewById(R.id.map);
        scroll = (ScrollView) findViewById(R.id.scroll);
        lyt_scorecard_info = (LinearLayout) findViewById(R.id.lyt_scorecard_info);
        lyt_scorecard_info.setOnClickListener(this);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtpdvName.setText(dtoPdv.getClient());
        txtNamePdv.setText(dtoPdv.getName());
        txtAddress.setText(dtoPdv.getAddress());
        txt_date.setText(Config.formatDate());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btninit) {
            if (!Config.isDateAutomatic()) {
                Snackbar.make(findViewById(R.id.rlyMain), "DEBE PONER LA HORA EN AUTOMÁTICO", Snackbar.LENGTH_SHORT).show();
            } else if (!Config.isDateAutomatic1()) {
                Snackbar.make(findViewById(R.id.rlyMain), "DEBE PONER ZONA HORARIA EN AUTOMATICO", Snackbar.LENGTH_SHORT).show();
            } else if (Config.isMockLocation()) {
                Snackbar.make(findViewById(R.id.rlyMain), "DEBE DESACTIVAR COORDENADAS FALSAS", Snackbar.LENGTH_SHORT).show();
            } else if (!Config.isGPSenabled()) {
                Snackbar.make(findViewById(R.id.rlyMain), "ACTIVE GPS", Snackbar.LENGTH_SHORT).show();
            }else {
                modelMenuReport.addReport();
                startService(new Intent(ContextApp.context, ServiceCheck.class).
                        putExtra(getString(R.string.app_bundle_name), dtoBundle).
                        putExtra("typeCheck", getResources().getInteger(R.integer.type_check_in)));
                modelMenuReport.setDtoBundle(dtoBundle);
                startActivity(new Intent(this, MenuReport.class).putExtra(getString(R.string.app_bundle_name), dtoBundle));
                finish();
            }

        } else if (view.getId() == R.id.lyt_scorecard_info) {
            Log.e("else", "l");
            startActivityForResult(new Intent(this, DetailPdvCS.class).putExtra(getString(R.string.app_bundle_name), dtoBundle), 1);

        } else if (view.getId() == R.id.btncancel) {
            finish();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getMaxZoomLevel();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != -1
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != -1) {
            map.setMyLocationEnabled(true);
        }
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(22.7,
                -102.6), 4);
        map.moveCamera(zoom);
        setMarker();
    }

    public void setMarker() {
        Double latitude;
        Double longitude;
        try {
            // latitude = Double.valueOf(dtoCoopPdv.getLatitude());
            //longitude = Double.valueOf(dtoCoopPdv.getLongitude());
            latitude = 0.0;
            longitude = 0.0;
        } catch (Exception e) {
            latitude = 0d;
            longitude = 0d;
        }
        if (latitude != 0 && longitude != 0) {
            map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_search)));/*.title(dtoCoopPdv.getNombrePdv()));*/
            CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                    longitude), 15);
            map.moveCamera(zoom);
        }
    }
}
