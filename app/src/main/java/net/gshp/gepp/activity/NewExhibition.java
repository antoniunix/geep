package net.gshp.gepp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gshp.api.utils.Crypto;
import com.gshp.api.utils.ResizePicture;

import net.gshp.gepp.BuildConfig;
import net.gshp.gepp.R;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportExhibitionDetail;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;
import net.gshp.gepp.dto.DtoReportHeadExhibition;
import net.gshp.gepp.model.ModelNewExhibition;
import net.gshp.gepp.util.Config;

import de.greenrobot.event.EventBus;

import java.io.File;
import java.util.Random;

/**
 * Created by leo on 13/03/18.
 */

public class NewExhibition extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spn_manufacturer;
    private Spinner spn_category;
    private Spinner spn_family;
    private Spinner spn_subfamily;
    private Spinner spn_type;
    private Spinner spn_location;
    private Spinner spn_group;
    private Spinner spn_departament;
    private DtoBundle dtoBundle;
    private ModelNewExhibition modelNewExhibition;
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exhibition);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getResources().getString(R.string.app_bundle_name));
        spn_manufacturer = (Spinner) findViewById(R.id.spn_manufacturer);
        spn_category = (Spinner) findViewById(R.id.spn_category);
        spn_family = (Spinner) findViewById(R.id.spn_family);
        spn_subfamily = (Spinner) findViewById(R.id.spn_subfamily);
        spn_type = (Spinner) findViewById(R.id.spn_type);
        spn_location = (Spinner) findViewById(R.id.spn_location);
        spn_group = (Spinner) findViewById(R.id.spn_group);
        spn_departament = (Spinner) findViewById(R.id.spn_departament);
        modelNewExhibition = new ModelNewExhibition(dtoBundle);

        spn_manufacturer.setOnItemSelectedListener(this);
        spn_category.setOnItemSelectedListener(this);
        spn_family.setOnItemSelectedListener(this);
        spn_subfamily.setOnItemSelectedListener(this);
        spn_type.setOnItemSelectedListener(this);
        spn_location.setOnItemSelectedListener(this);
        spn_group.setOnItemSelectedListener(this);
        spn_departament.setOnItemSelectedListener(this);

        spn_manufacturer.setAdapter(modelNewExhibition.getItemAdapterManufacturer());
        spn_type.setAdapter(modelNewExhibition.getItemAdapterType());
        spn_group.setAdapter(modelNewExhibition.getItemAdapterGroup());


    }


    public void onClickPhoto(View v) {
        path = getResources().getString(R.string.app_path_photo) + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri outputFileUri= FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            startActivityForResult(intent, 0);

        }else{
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            startActivityForResult(intent, 0);
        }


    }

    public void onClicksave(View v) {
        DtoReportHeadExhibition dtoHead = new DtoReportHeadExhibition();
        DtoReportExhibitionDetail dtoDetail = new DtoReportExhibitionDetail();
        String hash = Crypto.MD5(System.currentTimeMillis() + " " + new Random().nextInt());

        if (path.equals("")) {
            Toast.makeText(this, "Debe tomar una fotografía", Toast.LENGTH_SHORT).show();
        } else {
            dtoHead.setIdReportLocal(dtoBundle.getIdReportLocal()).
                    setIdTypeExhibition(modelNewExhibition.getItemPositionType(spn_type.getSelectedItemPosition()).getIdItemRelation()).
                    setIdPdv((int) dtoBundle.getIdPDV()).
                    setCreatedDate(System.currentTimeMillis() + "").
                    setHash(hash).
                    setSend(0);
            dtoDetail.setIdReportLocal(dtoBundle.getIdReportLocal()).
                    setHashExhibition(hash).
                    setIdManufacturer(modelNewExhibition.getItemPositionManufacturer(spn_manufacturer.getSelectedItemPosition()).getIdItemRelation()).
                    setIdCategory(modelNewExhibition.getItemPositionCategory(spn_category.getSelectedItemPosition()).getIdItemRelation()).
                    setIdFamily(modelNewExhibition.getItemPositionFamily(spn_family.getSelectedItemPosition()).getIdItemRelation()).
                    setIdSubFamily(modelNewExhibition.getItemPositionSubFamily(spn_subfamily.getSelectedItemPosition()).getIdItemRelation()).
                    setType(modelNewExhibition.getItemPositionType(spn_type.getSelectedItemPosition()).getIdItemRelation()).
                    setLocation(modelNewExhibition.getItemPositionLocation(spn_location.getSelectedItemPosition()).getIdItemRelation()).
                    setIdExhibitionGroup(modelNewExhibition.getItemPositionGroup(spn_group.getSelectedItemPosition()).getIdItemRelation()).
                    setIdDepartment(modelNewExhibition.getItemPositionDepartament(spn_departament.getSelectedItemPosition()).getIdItemRelation()).
                    setPath(path).
                    setHash(Crypto.MD5(System.currentTimeMillis() + " " + new Random().nextInt())).
                    setSend(0).
                    setFamily(modelNewExhibition.getItemPositionSubFamily(spn_subfamily.getSelectedItemPosition()).getValue());
            modelNewExhibition.saveReport(dtoHead, dtoDetail);
            DtoReportExhibitionMantained dtoReportExhibitionMantained = new DtoReportExhibitionMantained();
            dtoReportExhibitionMantained.setTypeModule(2);
            dtoReportExhibitionMantained.setLocation(modelNewExhibition.getItemPositionLocation(spn_location.getSelectedItemPosition()).getValue());
            dtoReportExhibitionMantained.setExhibition_name(modelNewExhibition.getItemPositionType(spn_type.getSelectedItemPosition()).getValue());
            dtoReportExhibitionMantained.setValue(modelNewExhibition.getItemPositionType(spn_type.getSelectedItemPosition()).getValue());
            dtoReportExhibitionMantained.setFamily(modelNewExhibition.getItemPositionSubFamily(spn_subfamily.getSelectedItemPosition()).getValue());
            dtoReportExhibitionMantained.setId_exhibition_group(3);
            dtoReportExhibitionMantained.setHashExhibition(hash);
            dtoReportExhibitionMantained.setHash(dtoDetail.getHash());
            EventBus.getDefault().post(dtoReportExhibitionMantained);
            finish();

        }

    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (arg1 == RESULT_OK) {
            File f = new File(path);
            if (f.exists()) {
                ResizePicture.resizeAndRotate(path, getResources().getInteger(R.integer.SIZE_WIDTH_PHOTO),
                        getResources().getInteger(R.integer.SIZE_HEIGHT_PHOTO), "Fóto Exito "
                                + new DaoPdv().SelectById(dtoBundle.getIdPDV())
                                .getName());
            } else
                path = "";
        } else {
            path = "";
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spn_manufacturer:
                spn_category.setAdapter(modelNewExhibition.getItemAdapterCategory(modelNewExhibition.getItemPositionManufacturer(position).getIdItemRelation()));
                break;
            case R.id.spn_category:
                spn_family.setAdapter(modelNewExhibition.getItemAdapterFamily(modelNewExhibition.getItemPositionCategory(position).getIdItemRelation()));
                break;
            case R.id.spn_family:
                spn_subfamily.setAdapter(modelNewExhibition.getItemAdapterSubFamily(modelNewExhibition.getItemPositionFamily(position).getIdItemRelation()));
                break;
            case R.id.spn_group:
                spn_location.setAdapter(modelNewExhibition.getItemAdapterLocation(modelNewExhibition.getItemPositionGroup(position).getIdItemRelation()));
                break;
            case R.id.spn_location:
                 spn_departament.setAdapter(modelNewExhibition.getItemAdapterDepartament(modelNewExhibition.getItemPositionLocation(position).getIdItemRelation()));
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
