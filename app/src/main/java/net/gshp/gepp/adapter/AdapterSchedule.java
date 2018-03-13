package net.gshp.gepp.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoSchedule;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

import java.util.List;

/**
 * Created by leo on 10/03/18.
 */

public class AdapterSchedule extends ArrayAdapter<DtoSchedule> {

    private LayoutInflater layoutInflater;
    private List<DtoSchedule>lst;
    private List<DtoSchedule>lstFilter;

    public AdapterSchedule(List<DtoSchedule> lst) {
        super(ContextApp.context, R.layout.row_calendar_item, lst);
        this.lst = lst;
        layoutInflater = (LayoutInflater) ContextApp.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        TextView txtDistance, txtScore, txtNomPdv, txtNomCliente, txtDate, txtInit, txtEnd,
                txtLabelInit, txtLabelEnd, txtNumTask, txtLabelTask, txtStatus, txtPorcent;
        RelativeLayout lnyQualif;
        View view;

        if (v == null) {
            v = layoutInflater.inflate(R.layout.row_calendar_item, null);
            txtDistance = (TextView) v.findViewById(R.id.txtDistance);
            txtScore = (TextView) v.findViewById(R.id.txtScore);
            txtNomPdv = (TextView) v.findViewById(R.id.txtNomPdv);
            txtNomCliente = (TextView) v.findViewById(R.id.txtNomCliente);
            txtDate = (TextView) v.findViewById(R.id.txtDate);
            txtInit = (TextView) v.findViewById(R.id.txtInit);
            txtEnd = (TextView) v.findViewById(R.id.txtEnd);
            txtLabelInit = (TextView) v.findViewById(R.id.txtLabelInit);
            txtLabelEnd = (TextView) v.findViewById(R.id.txtLabelEnd);
            txtNumTask = (TextView) v.findViewById(R.id.txtNumTask);
            txtLabelTask = (TextView) v.findViewById(R.id.txtLabelTask);
            txtStatus = (TextView) v.findViewById(R.id.txtStatus);
            txtPorcent = (TextView) v.findViewById(R.id.txtPorcent);
            lnyQualif = (RelativeLayout) v.findViewById(R.id.lnyQualif);
            view = v.findViewById(R.id.view);
            v.setTag(new ViewHolder(txtDistance, txtScore, txtNomPdv, txtNomCliente, txtDate,
                    txtInit, txtEnd, view, txtLabelInit, txtLabelEnd, txtNumTask, txtLabelTask, txtStatus, txtPorcent,lnyQualif));
        } else {
            ViewHolder vh = (ViewHolder) v.getTag();
            txtDistance = vh.txtDistance;
            txtScore = vh.txtScore;
            txtNomPdv = vh.txtNomPdv;
            txtNomCliente = vh.txtNomCliente;
            txtDate = vh.txtDate;
            txtInit = vh.txtInit;
            txtEnd = vh.txtEnd;
            txtLabelInit = vh.txtLabelInit;
            txtLabelEnd = vh.txtLabelEnd;
            txtNumTask = vh.txtNumTask;
            txtLabelTask = vh.txtLabelTask;
            txtStatus = vh.txtStatus;
            txtPorcent = vh.txtPorcent;
            lnyQualif = vh.lnyQualif;
            view = vh.view;
        }
        DtoSchedule dto = getItem(position);

        txtNomPdv.setText(dto.getName() != null ? dto.getName() : "");
        txtNomCliente.setText(dto.getClient() != null ? dto.getClient() : "");
        txtNumTask.setText(dto.getNumTask() + "");
        txtPorcent.setText(dto.getQualification() + "%");
        if (dto.getStart_datetime() != null) {
            txtInit.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", " h:mm aa", dto.getStart_datetime()));
            txtEnd.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", " h:mm aa", dto.getEnd_datetime()));
            txtLabelInit.setVisibility(View.VISIBLE);
            txtLabelEnd.setVisibility(View.VISIBLE);
            lnyQualif.setVisibility(View.VISIBLE);
        } else {
            txtLabelInit.setVisibility(View.GONE);
            txtLabelEnd.setVisibility(View.GONE);
            lnyQualif.setVisibility(View.INVISIBLE);
            txtInit.setText("");
            txtEnd.setText("");
        }

        if (dto.getQualification() == 100) {
            txtStatus.setText("PERFECTA");
            GradientDrawable bgShape = (GradientDrawable) txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusGreen));
        } else if (dto.getQualification() >= 85) {
            txtStatus.setText("SUPERIOR");
            GradientDrawable bgShape = (GradientDrawable) txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusGreen));
        } else if (dto.getQualification() >= 60) {
            txtStatus.setText("REGULAR");
            GradientDrawable bgShape = (GradientDrawable) txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusYellow));
        } else {
            txtStatus.setText("CRÍTICA");
            GradientDrawable bgShape = (GradientDrawable) txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusReed));
        }


        if (dto.isCheckIn() && dto.isCheckOut() && dto.getStart_datetime() != null) {
            view.setBackgroundResource(R.color.colorScheduleComplete);
        } else if (dto.isCheckIn() && !dto.isCheckOut() && dto.getStart_datetime() != null) {
            view.setBackgroundResource(R.color.colorScheduleIncomplete);
        } else if (dto.getStart_datetime() != null) {
            view.setBackgroundResource(R.color.colorScheduleInactive);
        } else {
            view.setBackgroundResource(R.color.colorgrayBackgroundVisit);
        }


        return v;
    }


    public void notifyDataSetChange() {
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView txtDistance, txtScore, txtNomPdv, txtNomCliente, txtDate, txtInit,
                txtEnd, txtLabelInit, txtLabelEnd, txtNumTask, txtLabelTask, txtStatus, txtPorcent;
        View view;
        RelativeLayout lnyQualif;

        public ViewHolder(TextView txtDistance, TextView txtScore,
                          TextView txtNomPdv, TextView txtNomCliente,
                          TextView txtDate, TextView txtInit, TextView txtEnd, View view,
                          TextView txtLabelInit, TextView txtLabelEnd,
                          TextView txtNumTask, TextView txtLabelTask, TextView txtStatus, TextView txtPorcent,RelativeLayout lnyQualif) {
            this.txtDistance = txtDistance;
            this.txtScore = txtScore;
            this.txtNomPdv = txtNomPdv;
            this.txtNomCliente = txtNomCliente;
            this.txtDate = txtDate;
            this.txtInit = txtInit;
            this.txtEnd = txtEnd;
            this.view = view;
            this.txtLabelInit = txtLabelInit;
            this.txtLabelEnd = txtLabelEnd;
            this.txtNumTask = txtNumTask;
            this.txtLabelTask = txtLabelTask;
            this.txtStatus = txtStatus;
            this.lnyQualif=lnyQualif;
            this.txtPorcent = txtPorcent;

            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.NORMAL, txtDistance, txtScore, txtNomCliente,
                    txtDate, txtInit, txtEnd, txtNumTask, txtLabelTask, txtStatus, txtPorcent);
            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.BOLD, txtNomPdv, txtLabelInit, txtLabelEnd);
        }
    }


}
