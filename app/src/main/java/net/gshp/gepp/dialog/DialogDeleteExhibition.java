package net.gshp.gepp.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.activity.ReportExhibition;
import net.gshp.gepp.dao.DaoReportExhibitionDetail;
import net.gshp.gepp.dao.DaoReportHeadExhibition;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;

/**
 * Created by leo on 13/03/18.
 */

public class DialogDeleteExhibition extends DialogFragment implements View.OnClickListener {

    private View view;
    private TextView txt_info;
    private DaoReportHeadExhibition daoReportHeadExhibition;
    private DaoReportExhibitionDetail daoReportExhibitionDetail;
    private ReportExhibition reportExhibition;
    private Button btn_delate;
    private DtoReportExhibitionMantained dtoReportExhibitionMantained;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.reportExhibition = (ReportExhibition) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable());
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        daoReportHeadExhibition = new DaoReportHeadExhibition();
        daoReportExhibitionDetail = new DaoReportExhibitionDetail();
        view = inflater.inflate(R.layout.dialog_delete_exhibition, container);
        txt_info = (TextView) view.findViewById(R.id.txt_info);
        btn_delate = (Button) view.findViewById(R.id.btn_delete_exhibition);
        btn_delate.setOnClickListener(this);
        dtoReportExhibitionMantained = (DtoReportExhibitionMantained) getArguments().getParcelable(getResources().getString(R.string.app_bundle_name));
        txt_info.setText(dtoReportExhibitionMantained.getValue() + "\n" +dtoReportExhibitionMantained.getLocation()+"\n"+dtoReportExhibitionMantained.getFamily());
        return view;
    }

    @Override
    public void onClick(View v) {
        daoReportExhibitionDetail.deleteByHash(dtoReportExhibitionMantained.getHashExhibition());
        daoReportHeadExhibition.deleteByHash(dtoReportExhibitionMantained.getHashExhibition());
        ((ReportExhibition) getActivity()).removeItemAdapter(dtoReportExhibitionMantained);
        dismiss();


    }
}
