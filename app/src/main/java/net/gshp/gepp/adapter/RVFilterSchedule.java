package net.gshp.gepp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.util.ChangeFontStyle;

import java.util.List;

/**
 * Created by leo on 9/03/18.
 */

public class RVFilterSchedule extends RecyclerView.Adapter<RVFilterSchedule.ViewHolder> {

    private List<DtoCatalog> lstSubordinate;
    private OnItemClickListenerRV onItemClickListenerRV;

    public RVFilterSchedule(List<DtoCatalog> lstSubordinate, OnItemClickListenerRV onItemClickListenerRV) {
        this.lstSubordinate = lstSubordinate;
        this.onItemClickListenerRV = onItemClickListenerRV;
    }

    @Override
    public RVFilterSchedule.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sos_category, parent, false);
        RVFilterSchedule.ViewHolder viewHolder = new RVFilterSchedule.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RVFilterSchedule.ViewHolder holder, final int position) {
        final DtoCatalog dto = lstSubordinate.get(position);
        holder.txtValue.setText(dto.getValue().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return lstSubordinate.size();
    }

    public DtoCatalog getItem(int position) {
        return lstSubordinate.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtValue;
        ImageView imgMake;
        RelativeLayout rlyMain;
        ViewHolder(View itemView) {
            super(itemView);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);
            imgMake = (ImageView) itemView.findViewById(R.id.imgMake);
            rlyMain = (RelativeLayout) itemView.findViewById(R.id.rlyMain);

            rlyMain.setOnClickListener(this);

            ChangeFontStyle.changeFont(ChangeFontStyle.TYPE_FONT.BOLD, txtValue);
        }

        @Override
        public void onClick(View v) {
            onItemClickListenerRV.onItemClickListener(v,getAdapterPosition());
        }
    }

}
