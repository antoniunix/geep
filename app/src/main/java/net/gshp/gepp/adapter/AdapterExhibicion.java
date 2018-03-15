package net.gshp.gepp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class AdapterExhibicion extends ArrayAdapter<DtoReportExhibitionMantained> {

    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;

    public AdapterExhibicion(List<DtoReportExhibitionMantained> lstDtoReport, View.OnClickListener onClickListener) {
        super(ContextApp.context, R.layout.row_exhibitions, lstDtoReport);
        layoutInflater = (LayoutInflater) ContextApp.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.onClickListener = onClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView txt_name, txt_description, txt_location;
        final RadioButton rdb_yes;
        final RadioButton rdb_no;
        ImageView img_delete;
        TextView txt_num_photos;
        LinearLayout lyt_row_exhibition;

        if (v == null) {
            v = layoutInflater.inflate(R.layout.row_exhibitions, null);
            txt_name = (TextView) v.findViewById(R.id.txt_name);
            txt_num_photos = (TextView) v.findViewById(R.id.txt_num_photos);
            txt_description = (TextView) v.findViewById(R.id.txt_description);
            txt_location = (TextView) v.findViewById(R.id.txt_location);
            rdb_yes = (RadioButton) v.findViewById(R.id.rdb_yescamara_type);
            rdb_no = (RadioButton) v.findViewById(R.id.rdb_nocamara_type);
            img_delete = (ImageView) v.findViewById(R.id.img_delete_exhibition);
            lyt_row_exhibition = (LinearLayout) v.findViewById(R.id.lyt_row_exhibition);


            rdb_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DtoReportExhibitionMantained dto = (DtoReportExhibitionMantained) rdb_yes.getTag();
                    if (isChecked) {
                        dto.setIsExhibit(1);
                        rdb_yes.setChecked(true);
                        rdb_no.setChecked(false);
                    }
                }
            });
            rdb_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DtoReportExhibitionMantained dto = (DtoReportExhibitionMantained) rdb_no.getTag();
                    if (isChecked) {
                        dto.setIsExhibit(2);
                        rdb_yes.setChecked(false);
                        rdb_no.setChecked(true);
                    }
                }
            });
            rdb_no.setOnClickListener(onClickListener);
            rdb_yes.setOnClickListener(onClickListener);
            img_delete.setOnClickListener(onClickListener);
            txt_num_photos.setOnClickListener(onClickListener);

            v.setTag(new ViewHolder(txt_name, txt_num_photos, rdb_yes, rdb_no, txt_description, txt_location, img_delete, lyt_row_exhibition));
        } else {
            ViewHolder vh = (ViewHolder) v.getTag();
            txt_name = vh.txt_name;
            txt_description = vh.txt_description;
            txt_location = vh.txt_location;
            txt_num_photos = vh.txt_num_photos;
            rdb_yes = vh.rdb_yes;
            rdb_no = vh.rdb_no;
            img_delete = vh.img_delete;
            lyt_row_exhibition = vh.lyt_row_exhibition;

        }
        DtoReportExhibitionMantained dto = getItem(position);
        dto.setPosition(position);
        txt_name.setTag(dto);
        txt_description.setTag(dto);
        txt_location.setTag(dto);
        txt_num_photos.setTag(dto);
        rdb_yes.setTag(dto);
        rdb_no.setTag(dto);
        img_delete.setTag(dto);
        lyt_row_exhibition.setBackgroundColor(Color.WHITE);


        switch (dto.getId_exhibition_group()) {
            case -1:
                txt_name.setTextColor(getContext().getResources().getColor(R.color.color_white));
                txt_name.setText("CORPORATIVA");
                txt_description.setVisibility(View.GONE);
                txt_location.setVisibility(View.GONE);
                txt_num_photos.setVisibility(View.GONE);
                rdb_yes.setVisibility(View.GONE);
                rdb_no.setVisibility(View.GONE);
                img_delete.setVisibility(View.GONE);
                lyt_row_exhibition.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorBtnBluePrimary));
                break;
            case -2:
                txt_name.setTextColor(getContext().getResources().getColor(R.color.color_white));
                txt_name.setText("REGIONAL");
                txt_description.setVisibility(View.GONE);
                txt_location.setVisibility(View.GONE);
                txt_num_photos.setVisibility(View.GONE);
                rdb_yes.setVisibility(View.GONE);
                rdb_no.setVisibility(View.GONE);
                img_delete.setVisibility(View.GONE);
                lyt_row_exhibition.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorBtnBluePrimary));
                break;
            case -3:
                txt_name.setTextColor(getContext().getResources().getColor(R.color.color_white));
                txt_name.setText("ADICIONAL");
                txt_description.setVisibility(View.GONE);
                txt_location.setVisibility(View.GONE);
                txt_num_photos.setVisibility(View.GONE);
                rdb_yes.setVisibility(View.GONE);
                rdb_no.setVisibility(View.GONE);
                img_delete.setVisibility(View.GONE);
                lyt_row_exhibition.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorBtnBluePrimary));
                break;
            default:
                txt_description.setVisibility(View.VISIBLE);
                txt_location.setVisibility(View.VISIBLE);
                txt_num_photos.setVisibility(View.VISIBLE);
                rdb_yes.setVisibility(View.VISIBLE);
                rdb_no.setVisibility(View.VISIBLE);
                img_delete.setVisibility(View.GONE);
                txt_num_photos.setText(dto.getPhotoDone() + "/" + dto.getMax_photos());
                txt_name.setText(dto.getExhibition_name());
                txt_description.setText(dto.getFamily());
                txt_location.setText(dto.getLocation());
                break;
        }

        if (dto.getTypeModule() == 1) {
            rdb_yes.setVisibility(View.VISIBLE);
            rdb_no.setVisibility(View.VISIBLE);
            txt_num_photos.setVisibility(View.VISIBLE);
            img_delete.setVisibility(View.GONE);
        } else if (dto.getTypeModule() == 2) {
            txt_name.setText(dto.getExhibition_name());
            rdb_yes.setVisibility(View.GONE);
            rdb_no.setVisibility(View.GONE);
            txt_num_photos.setVisibility(View.GONE);
            img_delete.setVisibility(View.VISIBLE);
        }


        if (dto.getIsExhibit() == 1) {
            rdb_yes.setChecked(true);
            rdb_no.setChecked(false);
        } else if (dto.getIsExhibit() == 2) {
            rdb_yes.setChecked(false);
            rdb_no.setChecked(true);
        } else if (dto.getIsExhibit() == -1) {
            lyt_row_exhibition.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorStatusReed));
            rdb_yes.setChecked(false);
            rdb_no.setChecked(false);
        } else {
            rdb_yes.setChecked(false);
            rdb_no.setChecked(false);
        }

        return v;
    }

    class ViewHolder {
        TextView txt_name;
        TextView txt_description;
        TextView txt_location;
        TextView txt_num_photos;
        RadioButton rdb_yes;
        RadioButton rdb_no;
        ImageView img_delete;
        LinearLayout lyt_row_exhibition;

        public ViewHolder(TextView txt_name, TextView txt_num_photos, RadioButton rdb_yes, RadioButton rdb_no, TextView txt_description, TextView txt_location, ImageView img_delete, LinearLayout lyt_row_exhibition) {
            this.txt_name = txt_name;
            this.txt_description = txt_description;
            this.txt_location = txt_location;
            this.txt_num_photos = txt_num_photos;
            this.rdb_yes = rdb_yes;
            this.rdb_no = rdb_no;
            this.img_delete = img_delete;
            this.lyt_row_exhibition = lyt_row_exhibition;
        }
    }

}
