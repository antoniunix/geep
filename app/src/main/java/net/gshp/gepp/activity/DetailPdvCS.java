package net.gshp.gepp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dao.DaoPdvCs;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoPdvCS;

/**
 * Created by leo on 14/03/18.
 */

public class DetailPdvCS extends AppCompatActivity {
    private DtoPdv dtoPdv;
    private DtoBundle dtoBundle;
    private TextView txtOportunity, txtphotoSuccess, txthrsEjecution, txtColaSS, txtColaMS, txtColaSSFlavor, txtColaMSFlavor;
    private DtoPdvCS dtoPdvCS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cs);
        init();
    }

    private void init(){
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        dtoPdvCS= new DaoPdvCs().selectAll(dtoBundle.getIdPDV());
        txtOportunity=(TextView)findViewById(R.id.txtOportunity);
        txtphotoSuccess=(TextView)findViewById(R.id.txtphotoSuccess);
        txthrsEjecution=(TextView)findViewById(R.id.txthrsEjecution);
        txtColaSS=(TextView)findViewById(R.id.txtColaSS);
        txtColaMS=(TextView)findViewById(R.id.txtColaMS);
        txtColaSSFlavor=(TextView)findViewById(R.id.txtColaSSFlavor);
        txtColaMSFlavor=(TextView)findViewById(R.id.txtColaMSFlavor);

        txtOportunity.setText(dtoPdvCS.getOportunity());
        txtphotoSuccess.setText(dtoPdvCS.getSuccess_photo());
        txthrsEjecution.setText(dtoPdvCS.getExecution_time());
        txtColaSS.setText(dtoPdvCS.getColas_ss());
        txtColaMS.setText(dtoPdvCS.getColas_ms());
        txtColaSSFlavor.setText(dtoPdvCS.getSabores_ss());
        txtColaMSFlavor.setText(dtoPdvCS.getSabores_ms());


    }
}
