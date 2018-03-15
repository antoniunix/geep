package net.gshp.gepp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.stetho.common.Utf8Charset;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoMessage;
import net.gshp.gepp.dto.DtoMessageView;
import net.gshp.gepp.model.ModelMessageView;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 13/03/18.
 */

public class MessageView extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_title,txt_date;
    private WebView txt_message;
    private Button btn_continue;
    private int idMessage;
    private DtoMessage dtoMessage;
    private ModelMessageView model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos_view);
        getSupportActionBar().hide();
        init();
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    private void init() {
        idMessage = getIntent().getIntExtra(ContextApp.context.getResources().getString(R.string.app_bundle_name), 0);
        model = new ModelMessageView();
        txt_message = (WebView) findViewById(R.id.txt_message);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_title = (TextView) findViewById(R.id.txt_title);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        dtoMessage = model.getItem(idMessage);

        txt_title.setText(dtoMessage.getTitle());
        btn_continue.setOnClickListener(this);

        ChangeFontStyle.changeFont(txt_title, txt_title, btn_continue);
        DtoMessageView dto = new DtoMessageView();
        dto.setDate(System.currentTimeMillis() + "");
        dto.setIdMessage(dtoMessage.getId());
        dto.setIdUser("@user");
        dto.setImei(Config.getIMEI());
        dto.setTz(Config.getTimeZone());
        dto.setSend(0);
        model.messageView(dto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_message.loadData(dtoMessage.getContent(), "text/html; charset=utf-8", Utf8Charset.NAME);
        txt_date.setText(Config.formatDate());
    }
}

