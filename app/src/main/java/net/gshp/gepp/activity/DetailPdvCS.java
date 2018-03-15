package net.gshp.gepp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dao.DaoPdv;
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
    private TextView txtNamePdv, txtOportunity, txtphotoSuccess, txthrsEjecution, txtColaSS, txtColaMS, txtColaSSFlavor, txtColaMSFlavor, txtAgua, txtGayto, txtLipton, txtJumex;
    private DtoPdvCS dtoPdvCS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cs);
        init();
    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        dtoPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        dtoPdvCS = new DaoPdvCs().selectAll(dtoBundle.getIdPDV());
        txtNamePdv = (TextView) findViewById(R.id.txtNamePdv);
        txtOportunity = (TextView) findViewById(R.id.txtOportunity);
        txtphotoSuccess = (TextView) findViewById(R.id.txtphotoSuccess);
        txthrsEjecution = (TextView) findViewById(R.id.txthrsEjecution);
        txtColaSS = (TextView) findViewById(R.id.txtColaSS);
        txtColaMS = (TextView) findViewById(R.id.txtColaMS);
        txtColaSSFlavor = (TextView) findViewById(R.id.txtColaSSFlavor);
        txtColaMSFlavor = (TextView) findViewById(R.id.txtColaMSFlavor);
        txtAgua = (TextView) findViewById(R.id.txtAgua);
        txtGayto = (TextView) findViewById(R.id.txtGatorade);
        txtLipton = (TextView) findViewById(R.id.txtLipton);
        txtJumex = (TextView) findViewById(R.id.txtJumex);


        txtOportunity.setText(dtoPdvCS.getOportunity());
        txtphotoSuccess.setText(dtoPdvCS.getSuccess_photo());
        txthrsEjecution.setText(dtoPdvCS.getExecution_time());
        txtColaSS.setText(dtoPdvCS.getColas_ss());
        txtColaSS.setBackgroundColor(Color.parseColor(dtoPdvCS.getColas_ss_color()));
        txtColaMS.setText(dtoPdvCS.getColas_ms());
        txtColaMS.setBackgroundColor(Color.parseColor(dtoPdvCS.getColas_ms_color()));
        txtColaSSFlavor.setText(dtoPdvCS.getSabores_ss());
        txtColaSSFlavor.setBackgroundColor(Color.parseColor(dtoPdvCS.getColas_ss_color()));
        txtColaMSFlavor.setText(dtoPdvCS.getSabores_ms());
        txtColaMSFlavor.setBackgroundColor(Color.parseColor(dtoPdvCS.getColas_ms_color()));
        txtAgua.setText(dtoPdvCS.getAgua());
        txtAgua.setBackgroundColor(Color.parseColor(dtoPdvCS.getAgua_color()));
        txtJumex.setText(dtoPdvCS.getJumex());
        txtJumex.setBackgroundColor(Color.parseColor(dtoPdvCS.getJumex_color()));
        txtLipton.setText(dtoPdvCS.getLipton());
        txtLipton.setBackgroundColor(Color.parseColor(dtoPdvCS.getLipton_color()));
        txtGayto.setText(dtoPdvCS.getGatorade());
        txtGayto.setBackgroundColor(Color.parseColor(dtoPdvCS.getGatorade_color()));
        txtNamePdv.setText(dtoPdv.getName());


    }
}
