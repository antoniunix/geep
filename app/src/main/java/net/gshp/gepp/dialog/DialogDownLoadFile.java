package net.gshp.gepp.dialog;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.gshp.gepp.BuildConfig;
import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoDownLoadDetail;
import net.gshp.gepp.dto.DtoMeasurementDownloadSku;
import net.gshp.gepp.model.ModelDownload;
import net.gshp.gepp.util.ChangeFontStyle;
import net.gshp.gepp.util.DownloadFileFromURL;
import net.gshp.gepp.util.MD5;

import java.io.File;

import static net.gshp.gepp.contextApp.ContextApp.context;

/**
 * Created by leo on 13/03/18.
 */

public class DialogDownLoadFile extends DialogFragment implements View.OnClickListener {

    private View view;
    private File file;
    private DtoMeasurementDownloadSku dtodownload;
    private ProgressBar progressBar;
    private DownloadFileFromURL downloadFileFromURL;
    private Handler handler;
    private TextView txt_porcent;
    private String name, md5;
    private ModelDownload modelDownload;
    private Button btn_next;
    private TextView txt_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.dialog_fragment_download, container);
        dtodownload = (DtoMeasurementDownloadSku) getArguments().getParcelable(context.getResources().getString(R.string.app_bundle_name));
        name = dtodownload.getNameFile();
        md5 = dtodownload.getMd5().toUpperCase();
        init();
        if (dtodownload.getUrl() != null && !dtodownload.getUrl().trim().equals("")
                && !dtodownload.getUrl().trim().equals("http://")
                && !dtodownload.getUrl().trim().equals("https://")) {
            downloadFileFromURL.execute(dtodownload.getUrl());
        } else {
            txt_porcent.setText("Enlace no encontrado");
            btn_next.setVisibility(View.VISIBLE);
        }
        return view;
    }
    private void init() {
        modelDownload = new ModelDownload();
        btn_next = (Button) view.findViewById(R.id.btn_next);
        progressBar = (ProgressBar) view.findViewById(R.id.bar_down);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        txt_porcent = (TextView) view.findViewById(R.id.txt_porcent);
        txt_title=(TextView)view.findViewById(R.id.txt_title);
        ChangeFontStyle.changeFont(btn_next,txt_porcent,txt_title);
        btn_next.setOnClickListener(this);
        handler = new HandlerTask();
        downloadFileFromURL = new DownloadFileFromURL(handler, name);

    }


    @Override
    public void onClick(View view) {
        dismiss();

    }
    @SuppressLint("HANDLERLeak")
    class HandlerTask extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    txt_porcent.setText("0 %");
                    break;
                case 2:
                    progressBar.setProgress(msg.arg2);
                    txt_porcent.setText(msg.arg2 + " %");
                    break;
                case 3:
                    if (txt_porcent.getText().equals("100 %")) {

                        if (md5.contains(MD5.fileToMD5(name))) {
                            Toast.makeText(getActivity(), "Archivo Descargado ", Toast.LENGTH_SHORT).show();
                            openFile(dtodownload);
                            dismiss();
                        } else {

                            File file = new File(name);
                            if (file.delete()) {
                                txt_porcent.setText("Error al descarga Archivo Intentarlo más tarde.");
                                Toast.makeText(getActivity(), "Error al descarga Archivo.\n Intentarlo más tarde.", Toast.LENGTH_SHORT).show();
                            } else {
                                txt_porcent.setText("Borrar el archivo ubicado en " + name + "\n Intentar descargar el más tarde.");
                                Toast.makeText(getActivity(), "Borrar el archivo ubicado en " + name, Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        File file = new File(name);
                        if (file.exists()) {
                            try {
                                progressBar.setVisibility(ProgressBar.GONE);
                                txt_porcent.setText(R.string.erro_download);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                progressBar.setVisibility(ProgressBar.GONE);
                                txt_porcent.setText(R.string.erro_download);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        btn_next.setVisibility(Button.VISIBLE);
                    }
                    break;
            }
        }
    }

    public void openFile(DtoMeasurementDownloadSku dto) {
        String typeFile = dto.getExt();
        File file = new File(dto.getNameFile());

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
            Uri path= FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (typeFile.equals(".pdf")) {
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            } else if (typeFile.equals(".mp4") || typeFile.equals(".avi") || typeFile.equals(".mpg")) {
                intent.setDataAndType(path, "video/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            } else if (typeFile.equals(".png") || typeFile.equals(".jpg")) {
                intent.setDataAndType(path, "image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            try {

                startActivity(intent);

            } catch (ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "No tiene aplicacion para ver el documento",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (typeFile.equals(".pdf")) {
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            } else if (typeFile.equals(".mp4") || typeFile.equals(".avi") || typeFile.equals(".mpg")) {
                intent.setDataAndType(path, "video/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            } else if (typeFile.equals(".png") || typeFile.equals(".jpg")) {
                intent.setDataAndType(path, "image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }

            try {

                startActivity(intent);

            } catch (ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "No tiene aplicacion para ver el documento",
                        Toast.LENGTH_SHORT).show();
            }
        }

        DtoDownLoadDetail dtoDownLoadDetail = new DtoDownLoadDetail();
        dtoDownLoadDetail.setIdFile(dto.getIdItemRelation());
        modelDownload.saveFileDetail(dtoDownLoadDetail);
    }


}
