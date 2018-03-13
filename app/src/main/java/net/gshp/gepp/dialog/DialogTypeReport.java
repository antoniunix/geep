package net.gshp.gepp.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVTypeReport;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelTypeReport;
import net.gshp.gepp.util.ChangeFontStyle;

import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class DialogTypeReport extends DialogFragment implements View.OnClickListener, OnItemClickListenerRV {

    private View view;
    private RecyclerView rvTypeReport;
    private Button btn_next;
    private ModelTypeReport model;
    private RVTypeReport adapter;
    private OnDissmisDialogListener onDissmisDialogListener;
    private DtoCatalog dtoCatalog;
    private int requestCode;

    public DialogTypeReport() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_type_report, container);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        rvTypeReport = (RecyclerView) view.findViewById(R.id.rvTypeReport);
        model = new ModelTypeReport();
        adapter = model.getAdapter(this);
        LinearLayoutManager lmy = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTypeReport.setLayoutManager(lmy);
        rvTypeReport.setAdapter(adapter);
        btn_next.setOnClickListener(this);

        ChangeFontStyle.changeFont(btn_next, view.findViewById(R.id.toolbar_title));
        return view;
    }

    public DialogTypeReport setOnDissmisDialogListener(OnDissmisDialogListener onDissmisDialogListener, int requestCode) {
        this.onDissmisDialogListener = onDissmisDialogListener;
        this.requestCode = requestCode;
        return this;
    }

    @Override
    public void onClick(View v) {
        if(dtoCatalog!=null){
            onDissmisDialogListener.onDissmisDialogListener(requestCode, dtoCatalog);
            this.dismiss();
        }else{
            Toast.makeText(getContext(),"SELECCIONE UNA OPCIÓN",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClickListener(View v, int position) {
        List<DtoCatalog> lst = model.getListItems();
        for (DtoCatalog dto : lst) {
            dto.setSelected(false);
        }
        model.getListItems(position).setSelected(true);
        dtoCatalog = model.getListItems(position);
        adapter.notifyDataSetChanged();
    }
}
