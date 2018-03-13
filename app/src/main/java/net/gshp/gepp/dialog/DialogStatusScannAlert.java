package net.gshp.gepp.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import net.gshp.gepp.R;
import net.gshp.gepp.listener.DismissDialogStatusScann;
import net.gshp.gepp.model.ModelDialogStatusScannAlert;

/**
 * Created by leo on 12/03/18.
 */

public class DialogStatusScannAlert extends DialogFragment implements
        AdapterView.OnItemClickListener {

    private ListView lst_status;
    private ModelDialogStatusScannAlert modelDialogStatusScannAlert;
    private View view;
    private DismissDialogStatusScann dialogStatusScann;
    private int position;//es la posicion del item en la lista de los sku
    private long idTypePproblem;


    public void setDtoBundle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_status_scann_alert, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setTitle(R.string.menu_status_scann);
        init();

        return view;
    }
    private void init() {
        this.lst_status = (ListView) this.view
                .findViewById(R.id.lst_status);
        modelDialogStatusScannAlert=new ModelDialogStatusScannAlert();
        this.lst_status.setOnItemClickListener(this);
    }

    @Override
    public void onStart() {
        lst_status.setAdapter(modelDialogStatusScannAlert.getAdapterStatus(idTypePproblem));
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        dialogStatusScann.onDismissDialog(this.position, modelDialogStatusScannAlert.getItem(position).getId());
        Log.e("leo","id "+ modelDialogStatusScannAlert.getItem(position).getId());
        getDialog().dismiss();
    }

    public void setpositionListSku(int position,long idTypePproblem){
        this.idTypePproblem=idTypePproblem;
        this.position=position;
    }
    public void setOnDismissDialog(DismissDialogStatusScann dialogStatusScann){
        this.dialogStatusScann=dialogStatusScann;
    }



}
