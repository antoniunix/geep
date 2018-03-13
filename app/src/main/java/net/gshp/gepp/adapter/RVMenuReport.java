package net.gshp.gepp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoMeasurementModule;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.model.ModelMenuReport;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class RVMenuReport extends RecyclerView.Adapter<RVMenuReport.ViewHolder> {

    private List<DtoMeasurementModule> lstDtoModules;
    private static OnItemClickListenerRV onItemClickListenerRV;
    private ModelMenuReport model;
    private Context context;

    public RVMenuReport(List<DtoMeasurementModule> lstDtoCoopPdvs, OnItemClickListenerRV onItemClickListenerRV, ModelMenuReport model) {
        this.lstDtoModules = lstDtoCoopPdvs;
        this.model = model;
        this.onItemClickListenerRV = onItemClickListenerRV;
        context = ContextApp.context;
    }

    @Override
    public RVMenuReport.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menureport, parent, false);
        RVMenuReport.ViewHolder viewHolder = new RVMenuReport.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DtoMeasurementModule dtoModule = lstDtoModules.get(position);
        switch ((int) dtoModule.getIdItemRelation()) {

            case Config.ENCUESTA:
                int responseModulePoll = model.isReportPoll();
                if (responseModulePoll != context.getResources().getInteger(R.integer.statusModuleReportWithOut)) {
                    holder.txtModul.setText(ContextApp.context.getString(R.string.ENCUESTA));
                    if (responseModulePoll == context.getResources().getInteger(R.integer.statusModuleReportDone)) {
                        holder.imgModul.setImageResource(R.drawable.encuesta);
                        holder.txtModul.setTextColor(Color.parseColor("#303F9F"));
                    } else {
                        holder.imgModul.setImageResource(R.drawable.encuesta2);
                    }
                }
                break;

            case Config.EXHIBICIONES:
                int responseExhibition = model.isReportExhibition();
                if (responseExhibition != context.getResources().getInteger(R.integer.statusModuleReportWithOut)) {
                    holder.txtModul.setText(ContextApp.context.getString(R.string.photo_success));
                    if (responseExhibition == context.getResources().getInteger(R.integer.statusModuleReportDone)) {
                        holder.imgModul.setImageResource(R.drawable.icn_fotoexito2);
                        holder.txtModul.setTextColor(Color.parseColor("#303F9F"));
                    } else {
                        holder.imgModul.setImageResource(R.drawable.icn_fotoexito);
                    }
                }
                break;

            case Config.SCANALLERT:
                holder.txtModul.setText(ContextApp.context.getString(R.string.alert));
                holder.imgModul.setImageResource(R.drawable.icn_alertas);
                break;


            case Config.CHECK_OUT:
                holder.txtModul.setText(ContextApp.context.getString(R.string.CHEKOUT));
                if (model.isCheck(Config.MODULE_TYPE_CHECKOUT)) {
                    holder.imgModul.setImageResource(R.drawable.icn_salir);
                    holder.txtModul.setTextColor(Color.parseColor("#303F9F"));
                } else {
                    holder.imgModul.setImageResource(R.drawable.icn_salir2);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lstDtoModules.size();
    }

    public DtoMeasurementModule getItem(int position) {
        return lstDtoModules.get(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtModul;
        RelativeLayout rltMain;
        ImageView imgModul;

        ViewHolder(View itenView) {
            super(itenView);
            txtModul = (TextView) itenView.findViewById(R.id.txtModul);
            imgModul = (ImageView) itenView.findViewById(R.id.imgModul);
            rltMain = (RelativeLayout) itenView.findViewById(R.id.rltMain);
            rltMain.setOnClickListener(this);

            ChangeFontStyle.changeFont(txtModul);
        }

        @Override
        public void onClick(View v) {
            onItemClickListenerRV.onItemClickListener(v, getAdapterPosition());
        }
    }


}
