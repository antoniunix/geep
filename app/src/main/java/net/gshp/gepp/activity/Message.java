package net.gshp.gepp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.gshp.gepp.R;
import net.gshp.gepp.dto.DtoMessage;
import net.gshp.gepp.model.ModelMessage;
import net.gshp.gepp.util.ChangeFontStyle;

/**
 * Created by leo on 13/03/18.
 */

public class Message extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lst_message;
    private ModelMessage modelMessage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.logo);
        toolbar.setTitle("");
        init();
        ChangeFontStyle.changeFont(findViewById(R.id.txtLabel));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        startActivity(new Intent(this, MessageView.class).putExtra(getResources().getString(R.string.app_bundle_name), ((DtoMessage) lst_message.getAdapter().getItem(position)).getId()));
    }

    private void init() {
        lst_message = (ListView) findViewById(R.id.lst_message);
        modelMessage = new ModelMessage();

        lst_message.setAdapter(modelMessage.getAdapter());
        lst_message.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        lst_message.setAdapter(modelMessage.getAdapter());

        super.onResume();
    }


}
