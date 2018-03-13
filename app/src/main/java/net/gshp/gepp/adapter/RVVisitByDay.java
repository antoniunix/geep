package net.gshp.gepp.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoSchedule;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

import java.util.List;

/**
 * Created by leo on 8/03/18.
 */

public class RVVisitByDay extends RecyclerView.Adapter<RVVisitByDay.ViewHolder> {

    private List<DtoSchedule> lstDtoSchedules;
    private OnItemClickListenerRV onItemClickListenerRV;

    public RVVisitByDay(List<DtoSchedule> lstDtoSchedules, OnItemClickListenerRV onItemClickListenerRV) {
        this.lstDtoSchedules = lstDtoSchedules;
        this.onItemClickListenerRV = onItemClickListenerRV;
    }

    @Override
    public RVVisitByDay.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_visit_by_day, parent, false);
        RVVisitByDay.ViewHolder viewHolder = new RVVisitByDay.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RVVisitByDay.ViewHolder holder, final int position) {
        DtoSchedule dto = lstDtoSchedules.get(position);
        holder.txtNameClient.setText(dto.getClient());
        holder.txtNamePdv.setText(dto.getName());
        holder.txtIni.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", "h:mm aa", dto.getStart_datetime()));
        holder.txtFinish.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", "h:mm aa", dto.getEnd_datetime()));
        holder.txtDayNumber.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", "dd", dto.getStart_datetime()));
        holder.txtDayName.setText(Config.formatDate("yyyy-MM-dd HH:mm:ss", "EEE", dto.getStart_datetime()));


        holder.txtStatus.setText("CRÍTICA");
        holder.txtPorcent.setText(dto.getQualification() + "%");
        holder.txtNumTask.setText(dto.getNumTask() + "");

        if (dto.isCheckIn() && dto.isCheckOut()) {
            holder.viewColor.setBackgroundResource(R.color.colorScheduleComplete);
        } else if (dto.isCheckIn() && !dto.isCheckOut()) {
            holder.viewColor.setBackgroundResource(R.color.colorScheduleIncomplete);
        } else {
            holder.viewColor.setBackgroundResource(R.color.colorScheduleInactive);
        }

        if (dto.getQualification() == 100) {
            holder.txtStatus.setText("PERFECTA");
            GradientDrawable bgShape = (GradientDrawable) holder.txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusGreen));
        } else if (dto.getQualification() >= 85) {
            holder.txtStatus.setText("SUPERIOR");
            GradientDrawable bgShape = (GradientDrawable) holder.txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusGreen));
        } else if (dto.getQualification() >= 60) {
            holder.txtStatus.setText("REGULAR");
            GradientDrawable bgShape = (GradientDrawable) holder.txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusYellow));
        } else {
            holder.txtStatus.setText("CRÍTICAS");
            GradientDrawable bgShape = (GradientDrawable) holder.txtPorcent.getBackground();
            bgShape.setColor(ContextCompat.getColor(ContextApp.context, R.color.colorStatusReed));
        }
    }

    @Override
    public int getItemCount() {
        return lstDtoSchedules.size();
    }


    public DtoSchedule getItem(int position) {
        return lstDtoSchedules.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtDayNumber, txtDayName, txtPorcent, txtNamePdv,
                txtNameClient, txtLabelIni, txtIni, txtFinish, txtLabelFinish, txtStatus,
                txtNumTask, txtLabelTask;
        View viewColor;
        LinearLayout lytMain;

        ViewHolder(View itemView) {
            super(itemView);
            txtDayNumber = (TextView) itemView.findViewById(R.id.txtDayNumber);
            txtDayName = (TextView) itemView.findViewById(R.id.txtDayName);
            txtPorcent = (TextView) itemView.findViewById(R.id.txtPorcent);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtNumTask = (TextView) itemView.findViewById(R.id.txtNumTask);
            txtLabelTask = (TextView) itemView.findViewById(R.id.txtLabelTask);
            txtNamePdv = (TextView) itemView.findViewById(R.id.txtNamePdv);
            txtNameClient = (TextView) itemView.findViewById(R.id.txtNameClient);
            txtLabelIni = (TextView) itemView.findViewById(R.id.txtLabelIni);
            txtFinish = (TextView) itemView.findViewById(R.id.txtFinish);
            txtIni = (TextView) itemView.findViewById(R.id.txtIni);
            txtLabelFinish = (TextView) itemView.findViewById(R.id.txtLabelFinish);
            lytMain = (LinearLayout) itemView.findViewById(R.id.lytMain);
            viewColor = itemView.findViewById(R.id.viewColor);

            lytMain.setOnClickListener(this);

             ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.BOLD, txtNamePdv, txtLabelIni, txtLabelFinish, txtDayNumber, txtDayName);
            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.NORMAL, txtPorcent, txtStatus, txtNumTask, txtLabelTask, txtNameClient, txtFinish, txtIni);
        }

        @Override
        public void onClick(View v) {
            onItemClickListenerRV.onItemClickListener(v, getAdapterPosition());
        }
    }
}
