package net.gshp.gepp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoMeasurementDownloadSku;
import net.gshp.gepp.model.ModelDownload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class AdapterDownload extends BaseAdapter implements Filterable {

    private LayoutInflater layoutInflater;
    private List<DtoMeasurementDownloadSku> lstDown;
    List<DtoMeasurementDownloadSku>mOriginal;
    private View.OnClickListener onClickListener;
    private ModelDownload modelDownload;

    public AdapterDownload(List<DtoMeasurementDownloadSku> lst, View.OnClickListener onClickListener, ModelDownload modelDownload){
        layoutInflater =(LayoutInflater) ContextApp.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.modelDownload = modelDownload;
        this.onClickListener= onClickListener;
        this.lstDown = lst;
    }

    @Override
    public int getCount() {
        return lstDown.size();
    }

    @Override
    public Object getItem(int position) {
        return lstDown.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lstDown.get(position).getIdItemRelation();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final TextView txt_title;
        final TextView txt_description;
        final ImageButton btn_download;
        ImageView img_type;
        LinearLayout lyt_row;

        if(v== null){
            v = layoutInflater.inflate(R.layout.row_list_download, null);
            txt_description = (TextView) v.findViewById(R.id.txt_description);
            txt_title = (TextView) v.findViewById(R.id.txt_title);
            btn_download = (ImageButton) v.findViewById(R.id.btn_download);
            img_type = (ImageView) v.findViewById(R.id.img_type);
            lyt_row=(LinearLayout)v.findViewById(R.id.lyt_row);
            v.setTag(new ViewHolder(txt_title, txt_description, btn_download, img_type,lyt_row));
            btn_download.setOnClickListener(onClickListener);
            txt_description.setOnClickListener(onClickListener);
            txt_title.setOnClickListener(onClickListener);
            img_type.setOnClickListener(onClickListener);
        }else {
            ViewHolder vh = (ViewHolder)v.getTag();
            txt_description = vh.txt_description;
            txt_title = vh.txt_title;
            btn_download = vh.btn_download;
            img_type = vh.img_type;
            lyt_row=vh.lyt_row;
        }
        DtoMeasurementDownloadSku dto = (DtoMeasurementDownloadSku) getItem(position);

        txt_title.setTag(dto);
        txt_description.setTag(dto);
        btn_download.setTag(dto);
        img_type.setTag(dto);


        txt_description.setText(dto.getDescription());
        txt_title.setText(dto.getTitle());

        if (dto.getExt().equals(".mp4") || dto.getExt().equals(".avi") || dto.getExt().equals(".mpg")) {
            img_type.setImageResource(R.drawable.ic_video_library_black_24dp);
        } else if (dto.getExt().equals(".pdf")) {
            img_type.setImageResource(R.drawable.pdf);
        } else if (dto.getExt().equals(".png") || dto.getExt().equals(".jpg")) {
            img_type.setImageResource(R.drawable.ic_crop);
        }

        if (position%2==0){
            lyt_row.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorZebra1));
        }else {
            lyt_row.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorZebra2));
        }

        return v;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                lstDown = (List<DtoMeasurementDownloadSku>) results.values;

                notifyDataSetChanged();

            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<DtoMeasurementDownloadSku> filteredList = new ArrayList<>();
                if (mOriginal == null) {
                    mOriginal = new ArrayList<>(lstDown);
                }

                if (constraint == null || constraint.length() == 0) {
                    results.count = mOriginal.size();
                    results.values = mOriginal;
                    Log.e("filtro", "Regrso lista original " + results.values.toString());
                } else {
                    for (int i = 0; i < mOriginal.size(); i++) {
                        String title = mOriginal.get(i).getTitle().toLowerCase();
                        String description = mOriginal.get(i).getDescription().toLowerCase();

                        if (title.contains(constraint.toString().toLowerCase()) || description.contains(constraint.toString().toLowerCase())) {
                            Log.e("filtro", "filtro lista " + mOriginal.get(i).getTitle());
                            filteredList.add(mOriginal.get(i));
                        }
                        Log.e("filtro", "fileredArrList " + mOriginal.toString());
                    }
                    results.count = filteredList.size();
                    results.values = filteredList;
                    Log.e("filtro", "Fltro " + results.count);
                }
                return results;
            }
        };
        return filter;
    }


    private class ViewHolder {
        TextView txt_title;
        TextView txt_description;
        ImageButton btn_download;
        ImageView img_type;
        LinearLayout lyt_row;

        public ViewHolder(TextView txt_title, TextView txt_description, ImageButton btn_download, ImageView img_type,LinearLayout lyt_row) {
            super();
            this.txt_title = txt_title;
            this.txt_description = txt_description;
            this.btn_download = btn_download;
            this.img_type = img_type;
            this.lyt_row=lyt_row;
        }
    }
}

