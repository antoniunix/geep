package net.gshp.gepp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.model.ModelCheckOut;
import net.gshp.gepp.model.ModelSend;
import net.gshp.gepp.util.ChangeFontStyle;

/**
 * Created by leo on 11/03/18.
 */

public class CheckOut extends AppCompatActivity implements OnMapReadyCallback {

    private DtoBundle dtoBundle;
    private MapView mapView;
    private GoogleMap map;
    private Button btn_next;
    private ModelCheckOut model;

    private void init(Bundle savedInstanceState) {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        mapView = (MapView) findViewById(R.id.map);
        btn_next = (Button) findViewById(R.id.btn_next);
        ChangeFontStyle.changeFont(btn_next);
        model = new ModelCheckOut(this, dtoBundle, btn_next, this);
        mapView.onCreate(savedInstanceState);
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            mapView.getMapAsync(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        init(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    public void onClickNext(View v) {
        model.stopGeo();
        startActivity(new Intent(this, Visit.class));
        setResult(RESULT_OK, null);
        new ModelSend().start();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (btn_next.getVisibility() != View.VISIBLE) {
            Snackbar.make(findViewById(R.id.activity_check_out), getString(R.string.report_check_wite_button_visible), Snackbar.LENGTH_LONG).show();
        } else {
            model.stopGeo();
            startActivity(new Intent(this, Visit.class));
            setResult(RESULT_OK, null);
            new ModelSend().start();
            finish();
        }
    }

    private void setUpMap() {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(false);
        map.getMaxZoomLevel();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != -1
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != -1) {
            map.setMyLocationEnabled(true);
        }
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(22.7,
                -102.6), 4);
        map.moveCamera(zoom);
    }

    public void setMarker(Location location) {
        if (btn_next.getVisibility() == View.GONE) {
            map.addCircle(new CircleOptions().center(new LatLng(location.getLatitude(), location
                    .getLongitude())).radius(location.getAccuracy()).strokeColor(new Color().argb(50, 128, 128, 128)));
            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));
            CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
            map.moveCamera(zoom);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            setUpMap();
            model.onStart();
        }
    }
}
