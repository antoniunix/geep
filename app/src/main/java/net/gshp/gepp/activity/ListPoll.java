package net.gshp.gepp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.gshp.apiencuesta.Encuesta;
import net.gshp.apiencuesta.ListEncuestas;
import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoEAEncuesta;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelListPoll;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 11/03/18.
 */

public class ListPoll extends AppCompatActivity implements OnItemClickListenerRV {

    private DtoBundle dtoBundle;
    private RecyclerView rv_polls;
    private ModelListPoll modelListPoll;
    private Context context;
    private TextView txt_date;

    private void init(){
        context = this;
        dtoBundle = (DtoBundle)getIntent().getExtras().get(getResources().getString(R.string.app_bundle_name));
        rv_polls = (RecyclerView)findViewById(R.id.rv_poll);
        txt_date = (TextView) findViewById(R.id.txt_date);
        modelListPoll = new ModelListPoll(context, dtoBundle);
        rv_polls.setHasFixedSize(true);
        LinearLayoutManager lmy = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_polls.setLayoutManager(lmy);
        rv_polls.setAdapter(modelListPoll.getAdapter(this));
        ChangeFontStyle.changeFont(txt_date,findViewById(R.id.txtLabel));
        txt_date.setText(Config.formatDate());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_poll);
        getSupportActionBar().hide();
        init();
    }




    @Override
    public void onItemClickListener(View v, int position) {
        DtoEAEncuesta dto=modelListPoll.getItem(position);
        if (dto.repeat > 0) {
            // iniciar el modulo de multiples encuestas
            startActivity(new Intent(context,ListEncuestas.class)
                    .putExtra("idReporte", (long)dtoBundle.getIdReportLocal())
                    .putExtra("idEncuesta",(long)dto.id)
                    .putExtra("idCanal",0)
                    .putExtra("idCliente",0)
                    .putExtra("idPdv",(int)dtoBundle.getIdPDV())
                    .putExtra("idRtm",0));
        } else {
            // iniciar la encuesta directamente
            startActivity(new Intent(context, Encuesta.class)
                    .putExtra("numeroEncuesta", 0)
                    .putExtra("idReporte", dtoBundle.getIdReportLocal())
                    .putExtra("idEncuesta",(long)dto.id)
                    .putExtra("idCanal",0)
                    .putExtra("idCliente",0)
                    .putExtra("idPdv",(int)dtoBundle.getIdPDV())
                    .putExtra("idRtm",0));
        }
    }
}
