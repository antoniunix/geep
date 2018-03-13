package net.gshp.gepp.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import net.gshp.apiencuesta.util.ChangeFontStyle;
import net.gshp.gepp.R;

/**
 * Created by leo on 8/03/18.
 */

public class DialogHelp extends DialogFragment implements View.OnClickListener {

    private View view;
    private Button  btn_metropoliGSHP;

    public DialogHelp(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_help, container);
        btn_metropoliGSHP = (Button) view.findViewById(R.id.btn_metropoliGSHP);

        btn_metropoliGSHP.setOnClickListener(this);


        ChangeFontStyle.changeFont(btn_metropoliGSHP,  view.findViewById(R.id.toolbar_title));
        return view;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse(getString(R.string.action_call_technical_suport_metropoli_gshp))));
    }
}
