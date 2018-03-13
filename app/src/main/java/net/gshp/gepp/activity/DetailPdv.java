package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.geolocation.ServiceCheck;
import net.gshp.gepp.model.ModelMenuReport;

/**
 * Created by leo on 10/03/18.
 */

public class DetailPdv extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private DtoBundle dtoBundle;
    private Button btninit;
    private ScrollView scroll;
    private MapView mapView;
    private GoogleMap map;
    private ModelMenuReport modelMenuReport;


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
        btninit = (Button) findViewById(R.id.btninit);
        btninit.setOnClickListener(this);
        mapView = (MapView) findViewById(R.id.map);
        scroll = (ScrollView) findViewById(R.id.scroll);
        setUpMapIfNeeded();
    }

    @Override
    public void onClick(View view) {
        modelMenuReport.addReport();
        startService(new Intent(ContextApp.context, ServiceCheck.class).
                putExtra(getString(R.string.app_bundle_name), dtoBundle).
                putExtra("typeCheck", getResources().getInteger(R.integer.type_check_in)));
        modelMenuReport.setDtoBundle(dtoBundle);
        startActivity(new Intent(this, MenuReport.class).putExtra(getString(R.string.app_bundle_name), dtoBundle));
        finish();
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
