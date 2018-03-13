package net.gshp.gepp.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoReportScannAlert;
import net.gshp.gepp.listener.OnItemClickListener;
import net.gshp.gepp.model.ModelDialogStatusScannAlert;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class AdapterScannAlertV2 extends RecyclerView.Adapter<AdapterScannAlertV2.ViewHolder> {

    private List<DtoReportScannAlert> lst;
    private OnItemClickListener onItemClickListener;
    private ModelDialogStatusScannAlert modelDialogStatusScannAlert;

    public AdapterScannAlertV2(List<DtoReportScannAlert> lst, OnItemClickListener onItemClickListener) {
        this.lst = lst;
        this.onItemClickListener = onItemClickListener;
        modelDialogStatusScannAlert = new ModelDialogStatusScannAlert();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_scann_alert, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DtoReportScannAlert dto = lst.get(position);
        holder.txt_sku.setText(dto.getSku());
        holder.txt_key.setText(dto.getKey());
        holder.txt_promedioVtaSemanal.setText(dto.getPromedioVtaSemanal());
        holder.txt_ventaSemanaActual.setText(dto.getVentaSemanaActual());
        holder.txt_ventaSemanaAnterior.setText(dto.getVentaSemanaAnterior());
        holder.txt_invUnidadesSemanaActual.setText(dto.getInvUnidadesSemanaActual());
        holder.txt_diasInv.setText(dto.getDiasInv());

        final LinearLayout lyt = holder.lyt_general;

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(v, position, dto);
            }
        });

        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lyt.getVisibility() == View.VISIBLE) {
                    lyt.setVisibility(View.GONE);
                } else {
                    lyt.setVisibility(View.VISIBLE);
                }
            }
        });


        switch ((int) dto.getIdTp()) {

            case -1:
                holder.txt_tp.setText("AGOTAMIENTO");
                holder.img_status.setVisibility(View.GONE);
                holder.txt_sku.setVisibility(View.GONE);
                holder.txt_key.setVisibility(View.GONE);
                holder.txt_promedioVtaSemanal.setVisibility(View.GONE);
                holder.txt_ventaSemanaActual.setVisibility(View.GONE);
                holder.txt_ventaSemanaAnterior.setVisibility(View.GONE);
                holder.txt_invUnidadesSemanaActual.setVisibility(View.GONE);
                holder.txt_diasInv.setVisibility(View.GONE);

                holder.txtVentaSemanal.setVisibility(View.GONE);
                holder.txtVentaSemanalactual.setVisibility(View.GONE);
                holder.txtVentaSemanalAnterior.setVisibility(View.GONE);
                holder.txtinvUnidadesActual.setVisibility(View.GONE);
                holder.txtdiasInv.setVisibility(View.GONE);

                holder.txt_tp.setVisibility(View.VISIBLE);
                break;
            case -2:
                holder.txt_tp.setText("SIN VENTAS");
                holder.img_status.setVisibility(View.GONE);
                holder.txt_sku.setVisibility(View.GONE);
                holder.txt_key.setVisibility(View.GONE);
                holder.txt_promedioVtaSemanal.setVisibility(View.GONE);
                holder.txt_ventaSemanaActual.setVisibility(View.GONE);
                holder.txt_ventaSemanaAnterior.setVisibility(View.GONE);
                holder.txt_invUnidadesSemanaActual.setVisibility(View.GONE);
                holder.txt_diasInv.setVisibility(View.GONE);
                holder.txtVentaSemanal.setVisibility(View.GONE);
                holder.txtVentaSemanalactual.setVisibility(View.GONE);
                holder.txtVentaSemanalAnterior.setVisibility(View.GONE);
                holder.txtinvUnidadesActual.setVisibility(View.GONE);
                holder.txtdiasInv.setVisibility(View.GONE);
                holder.txt_tp.setVisibility(View.VISIBLE);
                break;
            case -3:
                holder.txt_tp.setText("EXCEDENTE");
                holder.img_status.setVisibility(View.GONE);
                holder.txt_sku.setVisibility(View.GONE);
                holder.txt_key.setVisibility(View.GONE);
                holder.txt_promedioVtaSemanal.setVisibility(View.GONE);
                holder.txt_ventaSemanaActual.setVisibility(View.GONE);
                holder.txt_ventaSemanaAnterior.setVisibility(View.GONE);
                holder.txt_invUnidadesSemanaActual.setVisibility(View.GONE);
                holder.txt_diasInv.setVisibility(View.GONE);
                holder.txtVentaSemanal.setVisibility(View.GONE);
                holder.txtVentaSemanalactual.setVisibility(View.GONE);
                holder.txtVentaSemanalAnterior.setVisibility(View.GONE);
                holder.txtinvUnidadesActual.setVisibility(View.GONE);
                holder.txtdiasInv.setVisibility(View.GONE);
                holder.txt_tp.setVisibility(View.VISIBLE);
                break;

            default:
                holder.img_status.setVisibility(View.VISIBLE);
                holder.txt_sku.setVisibility(View.VISIBLE);
                holder.txt_key.setVisibility(View.VISIBLE);
                holder.txt_promedioVtaSemanal.setVisibility(View.VISIBLE);
                holder.txt_ventaSemanaActual.setVisibility(View.VISIBLE);
                holder.txt_ventaSemanaAnterior.setVisibility(View.VISIBLE);
                holder.txt_invUnidadesSemanaActual.setVisibility(View.VISIBLE);
                holder.txt_diasInv.setVisibility(View.VISIBLE);
                holder.txtVentaSemanal.setVisibility(View.VISIBLE);
                holder.txtVentaSemanalactual.setVisibility(View.VISIBLE);
                holder.txtVentaSemanalAnterior.setVisibility(View.VISIBLE);
                holder.txtinvUnidadesActual.setVisibility(View.VISIBLE);
                holder.txtdiasInv.setVisibility(View.VISIBLE);
                holder.txt_tp.setVisibility(View.GONE);
                break;
        }

        if (dto.getStatus() != 0 && dto.getColor() != null) {
            crearCirculo(holder.img_status, dto.getColor());
        } else {
            crearCirculo(holder.img_status, "#3366cc");
        }


    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_status;
        private TextView txt_sku;
        private TextView txt_key;
        private TextView txt_tp;
        private CardView card_view;
        private TextView txt_promedioVtaSemanal;
        private TextView txt_ventaSemanaActual;
        private TextView txt_ventaSemanaAnterior;
        private TextView txt_invUnidadesSemanaActual;
        private TextView txt_diasInv;
        private TextView txtVentaSemanal;
        private TextView txtVentaSemanalactual;
        private TextView txtVentaSemanalAnterior;
        private TextView txtinvUnidadesActual;
        private TextView txtdiasInv;
        private LinearLayout lyt_general;
        private ImageView expand;


        public ViewHolder(View itemView) {
            super(itemView);
            img_status = (ImageView) itemView.findViewById(R.id.img_status);
            txt_sku = (TextView) itemView.findViewById(R.id.txt_sku);
            txt_key = (TextView) itemView.findViewById(R.id.txt_key);
            txt_tp = (TextView) itemView.findViewById(R.id.txt_tp);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            txt_promedioVtaSemanal = (TextView) itemView.findViewById(R.id.txt_promedioVtaSemanal);
            txt_ventaSemanaActual = (TextView) itemView.findViewById(R.id.txt_ventaSemanaActual);
            txt_ventaSemanaAnterior = (TextView) itemView.findViewById(R.id.txt_ventaSemanaAnterior);
            txt_invUnidadesSemanaActual = (TextView) itemView.findViewById(R.id.txt_invUnidadesSemanaActual);
            txt_diasInv = (TextView) itemView.findViewById(R.id.txt_diasInv);
            txtVentaSemanal = (TextView) itemView.findViewById(R.id.txtVentaSemanal);
            txtVentaSemanalactual = (TextView) itemView.findViewById(R.id.txtVentaSemanalactual);
            txtVentaSemanalAnterior = (TextView) itemView.findViewById(R.id.txtVentaSemanalAnterior);
            txtinvUnidadesActual = (TextView) itemView.findViewById(R.id.txtinvUnidadesActual);
            txtdiasInv = (TextView) itemView.findViewById(R.id.txtdiasInv);
            lyt_general = (LinearLayout) itemView.findViewById(R.id.lyt_general);
            expand = (ImageView) itemView.findViewById(R.id.expand);
        }

    }

    private void crearCirculo(ImageView imageView, String color) {
        imageView.setBackgroundColor(Color.parseColor(color));


    }
}
