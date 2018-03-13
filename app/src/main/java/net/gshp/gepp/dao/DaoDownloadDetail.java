package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.dto.DtoDownLoadDetail;
import net.gshp.gepp.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leo on 13/03/18.
 */

public class DaoDownloadDetail extends DAO {

    private static final String TABLE_NAME = "file_view";
    private static final String PK_FIELD = "id";

    private final String ID = "id";
    private final String ID_FILE = "id_file";
    private final String ID_USER = "id_user";
    private final String DATE = "date";
    private final String TZ = "tz";
    private final String IMEI = "imei";
    private final String SEND = "send";
    private final String HASH = "hash";

    private SQLiteDatabase db;
    private Cursor cursor;

    public DaoDownloadDetail() {
        super(TABLE_NAME, PK_FIELD);
    }

    public int Insert(DtoDownLoadDetail obj) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            cv.put(ID_FILE, obj.getIdFile());
            cv.put(ID_USER, "@user");
            cv.put(DATE, System.currentTimeMillis() + "");
            cv.put(TZ, Config.getTimeZone());
            cv.put(IMEI, Config.getIMEI());
            cv.put(SEND, 0);
            cv.put(HASH, Crypto.MD5(System.currentTimeMillis() + "" + obj.getIdFile() + "" + new Random().nextInt(100)));
            resp = (int) db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return resp;
    }

    public List<DtoDownLoadDetail> selectToSend() {
        db = helper.getReadableDatabase();
        String qry = "SELECT\n" +
                "file_view.id,\n" +
                "file_view.id_file,\n" +
                "file_view.id_user,\n" +
                "file_view.date,\n" +
                "file_view.tz,\n" +
                "file_view.imei,\n" +
                "file_view.hash,\n" +
                "file_view.send\n" +
                "FROM\n" +
                "file_view\n" +
                "WHERE\n" +
                "file_view.send=0";
        cursor = db.rawQuery(qry, null);
        List<DtoDownLoadDetail> lst = new ArrayList<>();
        DtoDownLoadDetail dto;
        if (cursor.moveToFirst()) {
            do {
                dto = new DtoDownLoadDetail();
                dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                dto.setIdFile(cursor.getInt(cursor.getColumnIndexOrThrow(ID_FILE)));
                dto.setIdUser(cursor.getString(cursor.getColumnIndexOrThrow(ID_USER)));
                dto.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                dto.setTz(cursor.getString(cursor.getColumnIndexOrThrow(TZ)));
                dto.setImei(cursor.getString(cursor.getColumnIndexOrThrow(IMEI)));
                dto.setHash(cursor.getString(cursor.getColumnIndexOrThrow(HASH)));
                lst.add(dto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lst;
    }

    public void update(String id) {
        Log.e("update", "table " + TABLE_NAME + " idreportLocal " + id);
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send", 1);
        Log.e("send", "id file " + id);
        db.update(TABLE_NAME, cv, "id=" + id, null);
        db.close();
    }
}
