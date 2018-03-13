package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.util.Log;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportExhibitionMantainedCause;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leo on 12/03/18.
 */

public class DaoReportExhibitionMantainedCause extends DAO {

    public static String TABLE_NAME = "report_exhibition_mantained_cause";
    public static String PK_FIELD = "id_report_local";

    public DaoReportExhibitionMantainedCause() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert All
     */
    public int insert(List<DtoReportExhibitionMantainedCause> obj, DtoBundle dtoBundle) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            for (DtoReportExhibitionMantainedCause dto : obj) {
                cv.put("id_report_local", dtoBundle.getIdReportLocal());
                cv.put("hash_exhibition", dto.getHashExhibition());
                cv.put("id_cause", dto.getIdCause());
                cv.put("comment", dto.getComment());
                cv.put("send", 0);
                cv.put("hash", Crypto.MD5(System.currentTimeMillis() + "" + new Random().nextInt()));
                resp = (int) db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error db");
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }
    public int insert(DtoReportExhibitionMantainedCause obj, DtoBundle dtoBundle) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            cv.put("id_report_local", dtoBundle.getIdReportLocal());
            cv.put("hash_exhibition", obj.getHashExhibition());
            cv.put("id_cause", obj.getIdCause());
            cv.put("comment", obj.getComment());
            cv.put("send", 0);
            cv.put("hash", Crypto.MD5(System.currentTimeMillis() + "" + new Random().nextInt()));
            resp = (int) db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error db");
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }

    public int deleteById(long _idReportLocal, String hashExhibition)
    {
        int resp=0;
        try {
            db=helper.getWritableDatabase();
            resp=db.delete(TABLE_NAME, PK_FIELD + "="+_idReportLocal+" AND  hash_exhibition='"+hashExhibition+"'",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            db.close();
        }
        return resp;
    }

    public List<List<DtoReportExhibitionMantainedCause>> selectToSend() {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                        "report.id_report_server,\n" +
                        "report_exhibition_mantained_cause.id_report_local,\n" +
                        "report_exhibition_mantained_cause.hash_exhibition,\n" +
                        "report_exhibition_mantained_cause.id_cause,\n" +
                        "report_exhibition_mantained_cause.comment,\n" +
                        "report_exhibition_mantained_cause.hash\n" +
                        "FROM\n" +
                        "report_exhibition_mantained\n" +
                        "INNER JOIN report_exhibition_mantained_cause ON report_exhibition_mantained_cause.id_report_local = report_exhibition_mantained.id_report_local \n" +
                        "AND report_exhibition_mantained_cause.hash_exhibition = report_exhibition_mantained.hash_exhibition\n" +
                        "INNER JOIN report ON report.id = report_exhibition_mantained.id_report_local AND report.id_report_server>0\n" +
                        "WHERE report_exhibition_mantained.send=1 AND report_exhibition_mantained_cause.send=0\n" +
                        "ORDER BY  report_exhibition_mantained_cause.id_report_local ASC",
                null);
        List<List<DtoReportExhibitionMantainedCause>> lst = new ArrayList<>();
        List<DtoReportExhibitionMantainedCause> subLst = new ArrayList<>();
        DtoReportExhibitionMantainedCause catalogo;
        int tmpidReport;// variable para guardar el idreporte del cursor
        // anterior
        if (cursor.moveToFirst()) {
            // guardamos el idReporte del primer row
            tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local"));

            do {
                catalogo = new DtoReportExhibitionMantainedCause();
                catalogo.setIdReport(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_server")));
                catalogo.setIdReportLocal(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")));
                catalogo.setHashExhibition(cursor.getString(cursor.getColumnIndexOrThrow("hash_exhibition")));
                catalogo.setComment(cursor.getString(cursor.getColumnIndexOrThrow("comment")));
                catalogo.setIdCause(cursor.getInt(cursor.getColumnIndexOrThrow("id_cause")));
                catalogo.setHash(cursor.getString(cursor.getColumnIndexOrThrow("hash")));
                // si pertenece al mismo reporte se guardaran en la misma
                // sublista
                if (tmpidReport == cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")))
                    subLst.add(catalogo);
                else if (tmpidReport != cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")))// si
                {
                    // refrescamos el idReporte al actual id
                    tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local"));
                    lst.add(subLst);
                    subLst = new ArrayList<DtoReportExhibitionMantainedCause>();
                    subLst.add(catalogo);
                }
                if (cursor.isLast())
                    lst.add(subLst);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lst;
    }

    public void update(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send",1);
        Log.e("send", "manteined cause "+id);
        db.update(TABLE_NAME, cv, "id_report_local=" + id, null);
        db.close();
    }
}