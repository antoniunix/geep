package net.gshp.gepp.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dao.DaoMeasurementCauseExhibition;
import net.gshp.gepp.dao.DaoPdv;
import net.gshp.gepp.dao.DaoReportExhibitionMantainedCause;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;
import net.gshp.gepp.dto.DtoReportExhibitionMantainedCause;
import net.gshp.gepp.listener.OnCancelDialogListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class DialogReportActivityCaptureComment extends DialogFragment implements View.OnClickListener {

    private View view;
    private List<DtoReportExhibitionMantainedCause> lstDtoMeasurementCauseExhibitions;
    private DaoMeasurementCauseExhibition daoMeasurementCauseExhibition;
    private Spinner spn_type;
    private EditText edt_comment;
    private Button btn_cancel, btn_save;
    private List<String> lstStrings;
    private ArrayAdapter<String> adapter;
    private DaoPdv daopdv;
    private DtoBundle dtoBundle;
    private DtoPdv dtoPdv;
    private DtoReportExhibitionMantained dtoReportExhibitionMantained;
    private OnCancelDialogListener onCancelDialogListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.layout_dialog_exhibition_comment, container);
        init();
        return view;
    }

    public void setArgument(DtoBundle dtoBundle, DtoReportExhibitionMantained dtoReportExhibitionMantained,
                            OnCancelDialogListener onCancelDialogListener) {
        this.dtoBundle = dtoBundle;
        this.onCancelDialogListener = onCancelDialogListener;
        this.dtoReportExhibitionMantained = dtoReportExhibitionMantained;
    }

    private void init() {
        daopdv = new DaoPdv();
        dtoPdv = daopdv.SelectById(dtoBundle.getIdPDV());
        daoMeasurementCauseExhibition = new DaoMeasurementCauseExhibition();
        spn_type = (Spinner) view.findViewById(R.id.spn_type);
        btn_save = (Button) view.findViewById(R.id.btn_save);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        edt_comment = (EditText) view.findViewById(R.id.edt_comment);
        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        lstDtoMeasurementCauseExhibitions = daoMeasurementCauseExhibition.select(dtoPdv);
        lstStrings = new ArrayList<String>(lstDtoMeasurementCauseExhibitions.size());
        for (DtoReportExhibitionMantainedCause dto : lstDtoMeasurementCauseExhibitions) {
            lstStrings.add(dto.getComment());
        }
        //adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_simple_list, lstStrings);
        spn_type.setAdapter(new ArrayAdapter<String>(ContextApp.context,R.layout.row_spiner_drawable,lstStrings));

        DtoReportExhibitionMantainedCause cause = daoMeasurementCauseExhibition.existCause(dtoBundle.getIdReportLocal(), dtoReportExhibitionMantained.getHashExhibition());
        if (cause.getIdCause() != null) {
            spn_type.setSelection(lstDtoMeasurementCauseExhibitions.indexOf(new DtoReportExhibitionMantainedCause().setIdCause(cause.getIdCause())));
            edt_comment.setText(cause.getComment());
        }
    }


    @Override
    public void onClick(View arg0) {

        if (arg0.getId() == R.id.btn_save) {

            DtoReportExhibitionMantainedCause dto = new DtoReportExhibitionMantainedCause();
            dto.setComment(edt_comment.getText().toString());
            dto.setIdCause(lstDtoMeasurementCauseExhibitions.get(spn_type.getSelectedItemPosition()).getIdCause());
            dto.setHashExhibition(dtoReportExhibitionMantained.getHashExhibition());
            new DaoReportExhibitionMantainedCause().deleteById(dtoBundle.getIdReportLocal(), dtoReportExhibitionMantained.getHashExhibition());
            new DaoReportExhibitionMantainedCause().insert(dto, dtoBundle);
            dismiss();


        } else if (arg0.getId() == R.id.btn_cancel) {
            onCancelDialogListener.onCancelDialogListener(dtoReportExhibitionMantained);
            dismiss();
        }
    }
}
