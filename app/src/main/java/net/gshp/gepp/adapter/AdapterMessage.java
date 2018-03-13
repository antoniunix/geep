package net.gshp.gepp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoMessage;
import net.gshp.gepp.util.ChangeFontStyle;

import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class AdapterMessage extends ArrayAdapter<DtoMessage> {

    private LayoutInflater layoutInflater;
    private List<DtoMessage> lst;

    public AdapterMessage(List<DtoMessage> lst) {
        super(ContextApp.context, R.layout.row_adapter_message, lst);
        layoutInflater = (LayoutInflater) ContextApp.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lst=lst;
    }

    public DtoMessage getItem(int position){
        return lst.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView img_status;
        final TextView txt_title;
        final TextView txt_content;
        final TextView txt_view;
        LinearLayout lyt_mesage;

        if (v==null) {
            v=layoutInflater.inflate(R.layout.row_adapter_message, null);
            img_status=(ImageView)v.findViewById(R.id.img_status);
            txt_title=(TextView)v.findViewById(R.id.txt_title);
            txt_content=(TextView)v.findViewById(R.id.txt_content);
            txt_view=(TextView)v.findViewById(R.id.txt_view);
            lyt_mesage=(LinearLayout)v.findViewById(R.id.lyt_mesage);
            ChangeFontStyle.changeFont(txt_title,txt_content,txt_view);
            v.setTag(new ViewHolder(img_status, txt_title, txt_content,txt_view,lyt_mesage));


        }else{
            Log.e("dto","dto "+v.getTag());
            ViewHolder vh=(ViewHolder)v.getTag();
            img_status=vh.img_status;
            txt_title=vh.txt_title;
            txt_content=vh.txt_content;
            txt_view=vh.txt_view;
            lyt_mesage=vh.lyt_mesage;
        }

        DtoMessage dto =getItem(position);

        img_status.setTag(dto);
        txt_title.setTag(dto);
        txt_content.setTag(dto);
//        lyt_mesage.setTag(dto);

        txt_title.setText(dto.getTitle());
        txt_content.setText(dto.getDescription());

        if(dto.getDone()==1){
            txt_view.setText("visto");
        }else{
            txt_view.setText("por ver");
        }

        switch (dto.getType_id()) {

            case 1:
                img_status.setImageResource(R.drawable.m3);
                break;
            case 2:
                img_status.setImageResource(R.drawable.m1);
                break;
            case 3:
                img_status.setImageResource(R.drawable.m2);
                break;

            default:
                img_status.setImageResource(R.drawable.m1);
                break;

        }
        if (position%2==0){
            lyt_mesage.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorZebra1));
        }else {
            lyt_mesage.setBackgroundColor(ContextApp.context.getResources().getColor(R.color.colorZebra2));
        }

        return v;
    }

    private class ViewHolder{

        ImageView img_status;
        TextView txt_title;
        TextView txt_content;
        TextView txt_view;
        LinearLayout lyt_mesage;

        public ViewHolder(ImageView img_status, TextView txt_title, TextView txt_content,TextView txt_view,LinearLayout lyt_mesage) {
            super();
            this.img_status = img_status;
            this.txt_title = txt_title;
            this.txt_content = txt_content;
            this.txt_view = txt_view;
            this.lyt_mesage=lyt_mesage;
        }


    }
}
