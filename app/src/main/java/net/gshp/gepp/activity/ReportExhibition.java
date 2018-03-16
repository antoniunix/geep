package net.gshp.gepp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.AdapterExhibicion;
import net.gshp.gepp.dialog.DialogDeleteExhibition;
import net.gshp.gepp.dialog.DialogReportActivityCaptureComment;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;
import net.gshp.gepp.listener.OnCancelDialogListener;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.model.ModelExhibitions;
import net.gshp.gepp.util.Config;

import de.greenrobot.event.EventBus;

import java.io.File;

/**
 * Created by leo on 12/03/18.
 */

public class ReportExhibition extends AppCompatActivity implements View.OnClickListener, OnCancelDialogListener {
    private DtoBundle dtoBundle;
    private ListView lst_exhibiton;
    private String path = "";
    private AdapterExhibicion adapterExhibicion;
    private ModelExhibitions modelExhibitions;
    private Button btn_add_exhibition, btn_save_exhibition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions);
        getSupportActionBar().hide();
        init();

    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getResources().getString(R.string.app_bundle_name));
        EventBus.getDefault().register(this);
        btn_add_exhibition = (Button) findViewById(R.id.btn_add_exhibition);
        btn_save_exhibition = (Button) findViewById(R.id.btn_save_exhibition);
        btn_add_exhibition.setOnClickListener(this);
        btn_save_exhibition.setOnClickListener(this);
        modelExhibitions = new ModelExhibitions(dtoBundle, this);
        lst_exhibiton = (ListView) findViewById(R.id.lst_exhibition);
        adapterExhibicion = modelExhibitions.getAdapterExhibition(this);
        lst_exhibiton.setAdapter(adapterExhibicion);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.txt_num_photos:
                DtoReportExhibitionMantained dto = (DtoReportExhibitionMantained) v.getTag();
                path = getString(R.string.app_path_photo) + System.currentTimeMillis() + ".jpg";
                File file = new File(path);
                Uri outputFileUri = Uri.fromFile(file);
                Intent intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                startActivityForResult(intent, dto.getPosition());
                break;
            case R.id.rdb_nocamara_type:
                DtoReportExhibitionMantained dtoReport = (DtoReportExhibitionMantained) v.getTag();
                Log.e("leo", "type " + dtoReport.getId_exhibition_group());
                DialogReportActivityCaptureComment dialogFragment = new DialogReportActivityCaptureComment();
                dialogFragment.setCancelable(false);
                dialogFragment.setArgument(dtoBundle, dtoReport, this);
                dialogFragment.show(getSupportFragmentManager(), "DialogComment");
                break;
            case R.id.rdb_yescamara_type:

                break;
            case R.id.btn_add_exhibition:
                startActivity(new Intent(this, NewExhibition.class).putExtra(getResources().getString(R.string.app_bundle_name), dtoBundle));
                break;
            case R.id.btn_save_exhibition:
                int status = -1;

                if ((status = modelExhibitions.saveExhibition()) != -1) {
                    lst_exhibiton.setSelection(status);
                    //  Toast.makeText(this, "Debe capturar las fotografías", Toast.LENGTH_SHORT).show();
                } else {
                    modelExhibitions.saveExhibition();
                    Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.img_delete_exhibition:
                DtoReportExhibitionMantained dtoReportExhibitionMantained = (DtoReportExhibitionMantained) v.getTag();
                FragmentManager fragmentManager = getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putParcelable(getString(R.string.app_bundle_name), dtoReportExhibitionMantained);
                DialogDeleteExhibition dialogDeleteExhibition = new DialogDeleteExhibition();
                dialogDeleteExhibition.setArguments(bundle);
                dialogDeleteExhibition.show(fragmentManager, "dialogdelteExhibition");
                break;
        }
    }

    @Override
    public void onCancelDialogListener(Object obj) {
        DtoReportExhibitionMantained dtoReportExhibitionMantained = (DtoReportExhibitionMantained) obj;
        modelExhibitions.getItemExhibition(dtoReportExhibitionMantained.getPosition()).setIsExhibit(0);
        adapterExhibicion.notifyDataSetChanged();
    }


    public void onEvent(DtoReportExhibitionMantained dtoReportExhibitionMantained) {
        Log.e("hash", " event " + dtoReportExhibitionMantained.toString());
        adapterExhibicion.add(dtoReportExhibitionMantained);
        adapterExhibicion.notifyDataSetChanged();
    }

    public void removeItemAdapter(DtoReportExhibitionMantained dto) {
        modelExhibitions.removeItemAdapter(dto);

    }

}
