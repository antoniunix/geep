package net.gshp.gepp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoEAEncuesta;
import net.gshp.gepp.listener.OnItemClickListenerRV;
import net.gshp.gepp.util.ChangeFontStyle;

import java.util.List;

/**
 * Created by leo on 11/03/18.
 */

public class RVPoll extends RecyclerView.Adapter<RVPoll.ViewHolder> {

    private List<DtoEAEncuesta> listencuesta;
    private OnItemClickListenerRV onItemClickListenerRV;

    public RVPoll(List<DtoEAEncuesta> listencuesta, OnItemClickListenerRV onItemClickListenerRV) {
        this.listencuesta = listencuesta;
        this.onItemClickListenerRV = onItemClickListenerRV;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_poll, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DtoEAEncuesta dtoEAEncuesta = listencuesta.get(position);
        holder.txt_title.setText("" + dtoEAEncuesta.name);
        holder.txt_description.setText("" + dtoEAEncuesta.description);

        holder.lyt_tipo_encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListenerRV.onItemClickListener(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listencuesta.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title, txt_description;
        LinearLayout lyt_tipo_encuesta;

        ViewHolder(View itemView) {
            super(itemView);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title_poll);
            txt_description = (TextView) itemView.findViewById(R.id.txt_description_poll);
            lyt_tipo_encuesta = (LinearLayout) itemView.findViewById(R.id.lyt_tipo_encuesta);
            ChangeFontStyle.changeFont(txt_title, txt_description);
        }

    }
}
