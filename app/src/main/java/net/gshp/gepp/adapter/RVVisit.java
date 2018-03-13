package net.gshp.gepp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoReportVisit;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class RVVisit extends RecyclerView.Adapter<RVVisit.ViewHolder> {

    private List<DtoReportVisit> lstDtoReportVisits;
    private static OnItemClickListenerRV onItemClickListenerRV;

    public RVVisit(List<DtoReportVisit> lstDtoReportVisits, OnItemClickListenerRV onItemClickListenerRV) {
        this.lstDtoReportVisits = lstDtoReportVisits;
        this.onItemClickListenerRV = onItemClickListenerRV;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_visit, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DtoReportVisit dtoReportVisit = lstDtoReportVisits.get(position);
        holder.txtNamePdv.setText(dtoReportVisit.getNombrePdv());
        holder.txtCreated.setText(Config.formatDateFromCurrentMillis(dtoReportVisit.getDateCheckIn(),"dd MMMM yyyy"));
        holder.txtInit.setText(Config.formatDateFromCurrentMillis(dtoReportVisit.getDateCheckIn(),"hh:mm aa"));
        holder.txtFinish.setText(Config.formatDateFromCurrentMillis(dtoReportVisit.getDateCheckOut(),"hh:mm aa"));
        holder.txtCodePdv.setText(dtoReportVisit.getCodigoPdv());
        holder.txtTypePdv.setText(dtoReportVisit.getTipoPdv());


        if(dtoReportVisit.getDateCheckOut()==0){
            holder.viewStatus.setBackgroundResource(R.color.colorScheduleNotDone);
            holder.txtStatusText.setText("INCOMPLETO");
            holder.txtFinish.setText("--");
        }else if(dtoReportVisit.getDateCheckOut()!=0 && dtoReportVisit.getSend()==0){
            holder.viewStatus.setBackgroundResource(R.color.colorScheduleIncomplete);
            holder.txtStatusText.setText("POR ENVIAR");
        } else if(dtoReportVisit.getSend()==1){
            holder.viewStatus.setBackgroundResource(R.color.colorScheduleComplete);
            holder.txtStatusText.setText("ENVIADO");
        }else{
            holder.viewStatus.setBackgroundResource(R.color.colorScheduleComplete);
            holder.txtStatusText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return lstDtoReportVisits.size();
    }

    public DtoReportVisit getItem(int position) {
        return lstDtoReportVisits.get(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNamePdv,txtStatusText,txtCreated,txtCodePdv,txtTypePdv
                ,txtLabelInit,txtInit,txtLabelFinish,txtFinish;
        ImageView imgTrash;
        RelativeLayout lny_main;
        View viewStatus;

        ViewHolder(View itenView) {
            super(itenView);
            txtNamePdv = (TextView) itenView.findViewById(R.id.txtNamePdv);
            viewStatus = itenView.findViewById(R.id.viewStatus);
            txtStatusText = (TextView) itenView.findViewById(R.id.txtStatusText);
            txtCreated = (TextView) itenView.findViewById(R.id.txtCreated);
            txtFinish = (TextView) itenView.findViewById(R.id.txtFinish);
            txtCodePdv = (TextView) itenView.findViewById(R.id.txtCodePdv);
            imgTrash = (ImageView) itenView.findViewById(R.id.imgTrash);
            txtTypePdv = (TextView) itenView.findViewById(R.id.txtTypePdv);
            txtLabelInit = (TextView) itenView.findViewById(R.id.txtLabelInit);
            txtInit = (TextView) itenView.findViewById(R.id.txtInit);
            txtLabelFinish = (TextView) itenView.findViewById(R.id.txtLabelFinish);
            lny_main = (RelativeLayout) itenView.findViewById(R.id.lny_main);
            lny_main.setOnClickListener(this);
            imgTrash.setOnClickListener(this);


//            ChangeFontStyle.changeFont(txtNameSupervisor,txtDistance,txtStatus);
            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.BOLD,txtNamePdv,txtLabelInit,txtLabelFinish);
            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.NORMAL,txtStatusText,txtCreated
                    ,txtFinish,txtCodePdv,txtTypePdv,txtInit);
        }

        @Override
        public void onClick(View v) {
            onItemClickListenerRV.onItemClickListener(v, getAdapterPosition());
        }
    }
}
