package net.gshp.gepp.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import net.gshp.gepp.R;
import net.gshp.gepp.adapter.RVFilterSchedule;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelFilterSchedule;
import net.gshp.gepp.util.ChangeFontStyle;

/**
 * Created by leo on 9/03/18.
 */

public class DialogFilterSchedule extends DialogFragment implements OnItemClickListenerRV {

    private View view;
    private RecyclerView rcyUser;
    private RVFilterSchedule adapter;
    private ModelFilterSchedule model;
    private OnDissmisDialogListener onDissmisDialogListener;

    public DialogFilterSchedule(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_filter_schedule, container);
        rcyUser = (RecyclerView) view.findViewById(R.id.rcyUser);
        LinearLayoutManager lmy = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcyUser.setLayoutManager(lmy);

        model = new ModelFilterSchedule();
        ChangeFontStyle.changeFont(view.findViewById(R.id.toolbar_title));
        return view;
    }

    @Override
    public void onItemClickListener(View v, int position) {
        onDissmisDialogListener.onDissmisDialogListener(Activity.RESULT_OK,adapter.getItem(position));
        dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter= model.getAdapter(this);
        rcyUser.setAdapter(adapter);
    }

    public DialogFilterSchedule setData(OnDissmisDialogListener onDissmisDialogListener) {
        this.onDissmisDialogListener = onDissmisDialogListener;
        return this;
    }
}
