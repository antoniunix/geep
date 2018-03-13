package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import net.gshp.gepp.dto.DtoMessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 8/03/18.
 */

public class DaoMessageView extends DAO {

    private static final String TABLE_NAME="message_view";
    private static final String PK_FIELD = "id";

    private final String ID = "id";
    private final String ID_MESSAGE = "id_message";
    private final String ID_USER = "id_user";
    private final String DATE= "date";
    private final String TZ = "tz";
    private final String IMEI = "imei";
    private final String SEND = "send";

    private SQLiteDatabase db;
    private Cursor cursor;

    public DaoMessageView(){
        super(TABLE_NAME, PK_FIELD);
    }
    /**
     * Método que inserta los datos sobre la tabla, proviene de una lista de
     * dtos
     */
    public int Insert(DtoMessageView obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        String qry = "INSERT INTO message_view ("+ ID_MESSAGE + ","+ID_USER+ ","+DATE+ ","+TZ+ ","+IMEI+","+SEND+") VALUES (?,?,?,?,?,?);";
        try {
            SQLiteStatement insStmnt = db.compileStatement(qry);
            db.beginTransaction();

            try {
                insStmnt.bindLong(1, obj.getIdMessage());
            } catch (Exception e) {
                insStmnt.bindNull(1);
            }
            try {
                insStmnt.bindString(2, obj.getIdUser());
            } catch (Exception e) {
                insStmnt.bindNull(2);
            }
            try {
                insStmnt.bindString(3, obj.getDate());
            } catch (Exception e) {
                insStmnt.bindNull(3);
            }
            try {
                insStmnt.bindString(4, obj.getTz());
            } catch (Exception e) {
                insStmnt.bindNull(4);
            }
            try {
                insStmnt.bindString(5, obj.getImei());
            } catch (Exception e) {
                insStmnt.bindNull(5);
            }
            try {
                insStmnt.bindLong(6, obj.getSend());
            } catch (Exception e) {
                insStmnt.bindNull(6);
            }
            insStmnt.executeInsert();

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return resp;
    }

    public int isViewMessage(int id) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "count(*) count\n" +
                "FROM\n" +
                "message_view\n" +
                "WHERE\n" +
                "message_view.id_message="+id, null);
        int count=0;
        if (cursor.moveToFirst()) {
            count=cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return count;
    }

    public List<DtoMessageView> selectToSend() {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "message_view.id,\n" +
                "message_view.id_message,\n" +
                "message_view.id_user,\n" +
                "message_view.date,\n" +
                "message_view.tz,\n" +
                "message_view.imei,\n" +
                "message_view.send\n" +
                "FROM\n" +
                "message_view\n" +
                "WHERE\n" +
                "message_view.send=0", null);
        List<DtoMessageView> lst=new ArrayList<DtoMessageView>();
        DtoMessageView dto;
        if (cursor.moveToFirst()) {
            do{
                dto=new DtoMessageView();
                dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                dto.setIdMessage(cursor.getInt(cursor.getColumnIndexOrThrow("id_message")));
                dto.setIdUser(cursor.getString(cursor.getColumnIndexOrThrow("id_user")));
                dto.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                dto.setTz(cursor.getString(cursor.getColumnIndexOrThrow("tz")));
                dto.setImei(cursor.getString(cursor.getColumnIndexOrThrow("imei")));
                lst.add(dto);

            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lst;
    }

    /**
     * UPDATE
     */
    public void Update(String id) {
        Log.e("update", "table "+TABLE_NAME+ " idreportLocal "+id);
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send",1);
        Log.e("send", "id messageview "+id);
        db.update(TABLE_NAME, cv, "id=" + id, null);
        db.close();
    }


    /**
     * Metodo para contar mensajes que lleguen por medicion y se muestran en pantalla
     * @return
     */
    public int countMessage() {
        int count = 0;
        db = helper.getWritableDatabase();
        String qry = "SELECT count(*) as count\n" +
                "FROM \n" +
                "message \n" +
                "WHERE \n" +
                "message.end_date>=date('now')";
        cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return count;
    }
    /**
     * Metodo para contar mensaje leidos
     *
     * @return
     */
    public int countMessageViewed() {
        int count = 0;
        db = helper.getWritableDatabase();
        String qry = "SELECT count(DISTINCT message_view.id_message) as count\n" +
                "FROM\n" +
                "message\n" +
                "INNER JOIN message_view ON message_view.id_message=message.id\n" +
                "WHERE message.end_date>=date('now') ";
        cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return count;
    }




}
