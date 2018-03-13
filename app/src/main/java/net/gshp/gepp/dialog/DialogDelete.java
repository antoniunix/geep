package net.gshp.gepp.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.listener.OnDissmisDialogListener;
import net.gshp.gepp.util.ChangeFontStyle;

/**
 * Created by leo on 13/03/18.
 */

public class DialogDelete extends DialogFragment implements View.OnClickListener {

    private View view;
    private TextView txt_message;
    private Button btn_cancel, btn_agree;
    private OnDissmisDialogListener onDissmisDialogListener;
    private long idReport;
    private String namePdv;

    public DialogDelete() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_delete, container);
        txt_message = (TextView) view.findViewById(R.id.txt_message);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_agree = (Button) view.findViewById(R.id.btn_agree);

        btn_cancel.setOnClickListener(this);
        btn_agree.setOnClickListener(this);

        ChangeFontStyle.changeFont(view.findViewById(R.id.toolbar_title), txt_message, btn_cancel, btn_agree);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txt_message.setText(txt_message.getText().toString().concat("\n"+namePdv));
    }

    public DialogDelete setOnDissmisDialogListener(OnDissmisDialogListener onDissmisDialogListener) {
        this.onDissmisDialogListener = onDissmisDialogListener;
        return this;
    }

    public DialogDelete setData(String namePdv,long idReport){
        this.idReport=idReport;
        this.namePdv=namePdv;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            dismiss();
        } else {
            onDissmisDialogListener.onDissmisDialogListener(Activity.RESULT_OK,idReport);
            dismiss();
        }

    }
}
