package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.util.Log;

import net.gshp.gepp.dto.DtoReportExhibitionDetail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class DaoReportExhibitionDetail extends DAO {
    public static String TABLE_NAME = "report_exhibition_detail";
    public static String PK_FIELD = "id_report_local";

    public DaoReportExhibitionDetail() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(DtoReportExhibitionDetail obj, long idReporteLocal) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();

            cv.put("id_report_local", idReporteLocal);
            cv.put("hash_exhibition", obj.getHashExhibition());
            cv.put("id_exhibition_group", obj.getIdExhibitionGroup());
            cv.put("id_manufacturer", obj.getIdManufacturer());
            cv.put("id_category", obj.getIdCategory());
            cv.put("id_family", obj.getIdFamily());
            cv.put("id_subfamily", obj.getIdSubFamily());
            cv.put("type", obj.getType());
            cv.put("location", obj.getLocation());
            cv.put("path", obj.getPath());
            cv.put("hash", obj.getHash());
            cv.put("family", obj.getFamily());
            cv.put("send", 0);
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

    public List<List<DtoReportExhibitionDetail>> selectToSend() {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT DISTINCT \n" +
                        "report.id_report_server,\n" +
                        "report_exhibition_detail.hash_exhibition,\n" +
                        "report_exhibition_detail.id_exhibition_group,\n" +
                        "report_exhibition_detail.id_manufacturer,\n" +
                        "report_exhibition_detail.id_category,\n" +
                        "report_exhibition_detail.id_family,\n" +
                        "report_exhibition_detail.id_subfamily,\n" +
                        "report_exhibition_detail.location,\n" +
                        "report_exhibition_detail.id_report_local\n" +
                        "FROM\n" +
                        "report\n" +
                        "INNER JOIN report_head_exhibition ON report_head_exhibition.id_report_local = report.id \n" +
                        "and report_head_exhibition.send=1\n" +
                        "INNER JOIN report_exhibition_detail ON report_exhibition_detail.id_report_local = report_head_exhibition.id_report_local \n" +
                        "and report_exhibition_detail.send=0\n" +
                        "WHERE  report.id_report_server>0\n" +
                        "ORDER BY  report_head_exhibition.id_report_local ASC",
                null);
        List<List<DtoReportExhibitionDetail>> lst = new ArrayList<>();
        List<DtoReportExhibitionDetail> subLst = new ArrayList<>();
        DtoReportExhibitionDetail catalogo;
        int tmpidReport;// variable para guardar el idreporte del cursor
        // anterior
        if (cursor.moveToFirst()) {
            // guardamos el idReporte del primer row
            tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local"));

            do {
                catalogo = new DtoReportExhibitionDetail();
                catalogo.setIdReport(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_server")));
                catalogo.setHashExhibition(cursor.getString(cursor.getColumnIndexOrThrow("hash_exhibition")));
                catalogo.setIdReportLocal(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")));
                catalogo.setIdExhibitionGroup(cursor.getInt(cursor.getColumnIndexOrThrow("id_exhibition_group")));
                catalogo.setIdManufacturer(cursor.getInt(cursor.getColumnIndexOrThrow("id_manufacturer")));
                catalogo.setIdCategory(cursor.getInt(cursor.getColumnIndexOrThrow("id_category")));
                catalogo.setIdFamily(cursor.getInt(cursor.getColumnIndexOrThrow("id_family")));
                catalogo.setIdSubFamily(cursor.getInt(cursor.getColumnIndexOrThrow("id_subfamily")));
                catalogo.setLocation(cursor.getInt(cursor.getColumnIndexOrThrow("location")));
                // si pertenece al mismo reporte se guardaran en la misma
                // sublista
                if (tmpidReport == cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")))
                    subLst.add(catalogo);
                else if (tmpidReport != cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")))// si
                {
                    // refrescamos el idReporte al actual id
                    tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local"));
                    lst.add(subLst);
                    subLst = new ArrayList<DtoReportExhibitionDetail>();
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

    public List<DtoReportExhibitionDetail> SelectToSendPhoto()
    {
        db=helper.getReadableDatabase();
        cursor=db.rawQuery("SELECT DISTINCT\n" +
                "report.id_report_server,\n" +
                "report_exhibition_detail.id_report_local,\n" +
                "report_exhibition_detail.path,\n" +
                "report_exhibition_detail.id,\n" +
                "report_head_exhibition.id_pdv,\n" +
                "report_head_exhibition.hash\n" +
                "FROM\n" +
                "report\n" +
                "INNER JOIN report_head_exhibition ON report_head_exhibition.id_report_local = report.id \n" +
                "and report_head_exhibition.send=1\n" +
                "INNER JOIN report_exhibition_detail ON report_exhibition_detail.hash_exhibition = report_head_exhibition.hash \n" +
                "and report_exhibition_detail.send=1\n" +
                "WHERE  report.id_report_server>0\n" +
                "ORDER BY  report_head_exhibition.id_report_local ASC",null);
        List<DtoReportExhibitionDetail> obj=new ArrayList<>();
        DtoReportExhibitionDetail catalogo;
        if(cursor.moveToFirst())
        {
            int id=cursor.getColumnIndexOrThrow("id");
            int id_report_local=cursor.getColumnIndexOrThrow("id_report_local");
            int hash=cursor.getColumnIndexOrThrow("hash");
            int id_pdv=cursor.getColumnIndexOrThrow("id_pdv");
            int id_report_server=cursor.getColumnIndexOrThrow("id_report_server");
            int path=cursor.getColumnIndexOrThrow("path");
            do
            {
                catalogo=new DtoReportExhibitionDetail();
                catalogo.setId(cursor.getInt(id));
                catalogo.setIdReportLocal(cursor.getInt(id_report_local));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setIdPdv(cursor.getInt(id_pdv));
                catalogo.setIdReport(cursor.getInt(id_report_server));
                catalogo.setPath(cursor.getString(path));

                if(new File(catalogo.getPath()).exists()){
                    obj.add(catalogo);
                }

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public void update(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send",1);
        Log.e("send", "exhibition detail id "+id);
        db.update(TABLE_NAME, cv, "id_report_local=" + id, null);
        db.close();
    }

    public void updatePhoto(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send",2);
        Log.e("send", "exhibition detail "+id);
        db.update(TABLE_NAME, cv, "id=" + id, null);
        db.close();
    }

    public int deleteByHash(String hash) {
        int resp = 0;
        try {
            db = helper.getWritableDatabase();
            resp = db.delete(TABLE_NAME, "hash_exhibition=?", new String[]{hash});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return resp;
    }
}
