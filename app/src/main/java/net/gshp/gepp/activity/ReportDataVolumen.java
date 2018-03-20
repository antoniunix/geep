package net.gshp.gepp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoPdvGeneral;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoPdvInfoGeneral;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 13/03/18.
 */

public class ReportDataVolumen extends AppCompatActivity {

    private DtoBundle dtoBundle;
    private TextView txt_date;
    private DtoPdvInfoGeneral dtoPdvInfoGeneral;
    private DtoPdv dtoPdv;
    private TextView txtporcentotal, txtporcentcolas,
            txtporcentsabores, txtporcentagua, txtporcentgatorade;
    private ImageView imgtotal, imgbanderacola, imgbanderasabores,
            imgbanderaagua, imgbanderagatorade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datavolumen);
        getSupportActionBar().hide();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_date.setText(Config.formatDate());
    }

    private void init() {
        dtoBundle = (DtoBundle) getIntent().getExtras().get(getString(R.string.app_bundle_name));
        dtoPdv = new DaoPdv().SelectById(dtoBundle.getIdPDV());
        txtporcentotal = (TextView) findViewById(R.id.txtporcentotal);
        txtporcentcolas = (TextView) findViewById(R.id.txtporcentcolas);
        txtporcentsabores = (TextView) findViewById(R.id.txtporcentsabores);
        txtporcentagua = (TextView) findViewById(R.id.txtporcentagua);
        txtporcentgatorade = (TextView) findViewById(R.id.txtporcentgatorade);
        imgtotal = (ImageView) findViewById(R.id.imgtotal);
        imgbanderacola = (ImageView) findViewById(R.id.imgbanderacola);
        imgbanderasabores = (ImageView) findViewById(R.id.imgbanderasabores);
        imgbanderaagua = (ImageView) findViewById(R.id.imgbanderaagua);
        imgbanderagatorade = (ImageView) findViewById(R.id.imgbanderagatorade);
        dtoPdvInfoGeneral = new DaoPdvGeneral().select(dtoBundle.getIdPDV());
        txtporcentotal.setText(dtoPdvInfoGeneral.getTotal_producto());
        txtporcentcolas.setText(dtoPdvInfoGeneral.getColas_porcentaje());
        txtporcentsabores.setText(dtoPdvInfoGeneral.getSabores_porcentaje());
        txtporcentagua.setText(dtoPdvInfoGeneral.getAgua_porcentaje());
        txtporcentgatorade.setText(dtoPdvInfoGeneral.getAgua_porcentaje());
        txt_date=(TextView) findViewById(R.id.txt_date);
        switch (dtoPdvInfoGeneral.getTotal_producto_bandera()){
            case 1:
                imgtotal.setImageResource(R.drawable.flecha_verde);
                txtporcentotal.setBackgroundResource(R.color.green_head);
                break;
            case 2:
                imgtotal.setImageResource(R.drawable.flecha_roja);
                txtporcentotal.setBackgroundResource(R.color.red_head);
                break;

        }
        switch (dtoPdvInfoGeneral.getSabores_bandera()){
            case 1:
                imgbanderacola.setImageResource(R.drawable.flecha_verde);
                txtporcentcolas.setTextColor(getResources().getColor(R.color.green_head));
                break;
            case 2:
                imgbanderacola.setImageResource(R.drawable.flecha_roja);
                txtporcentcolas.setTextColor(getResources().getColor(R.color.red_head));
                break;
        }

        switch (dtoPdvInfoGeneral.getAgua_bandera()){
            case 1:
                imgbanderaagua.setImageResource(R.drawable.flecha_verde);
                txtporcentagua.setTextColor(getResources().getColor(R.color.green_head));
                break;
            case 2:
                txtporcentagua.setTextColor(getResources().getColor(R.color.red_head));
                imgbanderaagua.setImageResource(R.drawable.flecha_roja);
                break;
        }
        switch (dtoPdvInfoGeneral.getSabores_bandera()){
            case 1:
                txtporcentsabores.setTextColor(getResources().getColor(R.color.green_head));
                imgbanderasabores.setImageResource(R.drawable.flecha_verde);
                break;
            case 2:
                txtporcentsabores.setTextColor(getResources().getColor(R.color.red_head));
                imgbanderasabores.setImageResource(R.drawable.flecha_roja);
                break;
        }

        switch (dtoPdvInfoGeneral.getGatorade_bandera()){
            case 1:
                txtporcentgatorade.setTextColor(getResources().getColor(R.color.green_head));
                imgbanderagatorade.setImageResource(R.drawable.flecha_verde);
                break;
            case 2:
                txtporcentgatorade.setTextColor(getResources().getColor(R.color.red_head));
                imgbanderagatorade.setImageResource(R.drawable.flecha_roja);
                break;
        }

    }
}
