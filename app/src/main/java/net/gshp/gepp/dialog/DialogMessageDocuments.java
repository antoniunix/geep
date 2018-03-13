package net.gshp.gepp.dialog;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import net.gshp.gepp.R;
import net.gshp.gepp.activity.ListDownloadable;
import net.gshp.gepp.activity.Message;
import net.gshp.gepp.model.ModelNewMessageFile;

/**
 * Created by leo on 13/03/18.
 */

public class DialogMessageDocuments  extends DialogFragment implements View.OnClickListener {
    private View view;
    private Button btn_message, btn_down;
    ModelNewMessageFile modelHome;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_message, container);
        init();
        return view;
    }

    public void init() {
        modelHome = new ModelNewMessageFile();
        btn_message = (Button) view.findViewById(R.id.btn_message);
        btn_down = (Button) view.findViewById(R.id.btn_download);

        btn_down.setOnClickListener(this);
        btn_message.setOnClickListener(this);

        if (modelHome.countNewFile() > 0) {
            btn_down.setText(btn_down.getText() + "   (" + modelHome.countNewFile() + ")");

        }
        if (modelHome.countNewMessage() > 0) {

            btn_message.setText(btn_message.getText() + "   (" + modelHome.countNewMessage() + ")");
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_message:
                startActivity(new Intent(getActivity(),
                        Message.class));
                dismiss();

                // getActivity().finish();
                break;

            case R.id.btn_download:
                Log.e("btn", "on");
                startActivity(new Intent(getActivity(),
                        ListDownloadable.class));
                dismiss();

                // getActivity().finish();
                break;

        }
    }
}