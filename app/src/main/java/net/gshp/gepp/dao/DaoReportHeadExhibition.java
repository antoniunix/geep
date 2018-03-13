package net.gshp.gepp.dao;

import android.content.ContentValues;

import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportHeadExhibition;
import net.gshp.gepp.dto.DtoTypeExhibition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class DaoReportHeadExhibition extends DAO {
    public static String TABLE_NAME = "report_head_exhibition";
    public static String PK_FIELD = "id_report_local";

    public DaoReportHeadExhibition() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(DtoReportHeadExhibition obj, long idReporteLocal) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            cv.put("id_report_local", idReporteLocal);
            cv.put("id_type_exhibition", obj.getIdTypeExhibition());
            cv.put("id_pdv", obj.getIdPdv());
            cv.put("created_date", obj.getCreatedDate());
            cv.put("hash", obj.getHash());
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

    public List<DtoTypeExhibition> SelectManufacturer(DtoPdv dtoPdv) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "measurement_item_exhibition.id_item_relation,\n" +
                "measurement_item_exhibition.value\n" +
                "FROM\n" +
                "c_type_catalog_exhibition\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id " +
                "AND measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "c_type_catalog_exhibition.id=1\n" +
                "ORDER BY measurement_item_exhibition.\"_order\",measurement_item_exhibition.weight ASC", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }
    public List<DtoTypeExhibition> SelectCategory(DtoPdv dtoPdv,int idManufacturer) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "measurement_item_exhibition.id_item_relation,\n" +
                "measurement_item_exhibition.value\n" +
                "FROM\n" +
                "c_type_catalog_exhibition\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id " +
                "AND measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "c_type_catalog_exhibition.id=2 and measurement_item_exhibition.parent="+idManufacturer+"\n" +
                "ORDER BY measurement_item_exhibition.\"_order\",measurement_item_exhibition.weight ASC", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoTypeExhibition> SelectFamily(DtoPdv dtoPdv,int idCategory) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "measurement_item_exhibition.id_item_relation,\n" +
                "measurement_item_exhibition.value\n" +
                "FROM\n" +
                "c_type_catalog_exhibition\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id " +
                "AND measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "c_type_catalog_exhibition.id=3 and measurement_item_exhibition.parent="+idCategory+"\n" +
                "ORDER BY measurement_item_exhibition.\"_order\",measurement_item_exhibition.weight ASC", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoTypeExhibition> SelectSubFamily(DtoPdv dtoPdv,int idFamily) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "measurement_item_exhibition.id_item_relation,\n" +
                "measurement_item_exhibition.value\n" +
                "FROM\n" +
                "c_type_catalog_exhibition\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id " +
                "AND measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "c_type_catalog_exhibition.id=4 and measurement_item_exhibition.parent="+idFamily+"\n" +
                "ORDER BY measurement_item_exhibition.\"_order\",measurement_item_exhibition.weight ASC", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoTypeExhibition> SelectType(DtoPdv dtoPdv) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "type_exhibition.id_item_relation,\n" +
                "type_exhibition.value\n" +
                "FROM\n" +
                "type_exhibition\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = type_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = type_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = type_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = type_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = type_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = type_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) ", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoTypeExhibition> SelectLocation(DtoPdv dtoPdv) {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "measurement_item_exhibition.id_item_relation,\n" +
                "measurement_item_exhibition.value\n" +
                "FROM\n" +
                "c_type_catalog_exhibition\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id " +
                "AND measurement_item_exhibition.id_type_catalog = c_type_catalog_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "LEFT JOIN measurement_exhibition_region ON measurement_exhibition_region.id_measurement = measurement_item_exhibition.id_measurement\n" +
                "WHERE (measurement_exhibition_canal.id_item_relation="+dtoPdv.getIdCanal()+" OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation="+dtoPdv.getIdClient()+" OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation="+dtoPdv.getIdClientFormat()+" OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation="+dtoPdv.getId()+" OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation="+dtoPdv.getIdRtm()+" OR measurement_exhibition_rtm.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_region.id_item_relation="+dtoPdv.getIdRegion()+" OR measurement_exhibition_region.id_item_relation ISNULL) AND\n" +
                "c_type_catalog_exhibition.id=5 \n" +
                "ORDER BY measurement_item_exhibition.\"_order\",measurement_item_exhibition.weight ASC", null);
        List<DtoTypeExhibition> obj = new ArrayList<>();
        DtoTypeExhibition catalogo;
        if (cursor.moveToFirst()) {
            Integer idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            Integer value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoTypeExhibition();
                catalogo.setIdItemRelation(cursor.getInt(idItemRelation));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }
    public List<List<DtoReportHeadExhibition>> selectToSend() {
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                        "report.id_report_server,\n" +
                        "report_head_exhibition.id,\n" +
                        "report_head_exhibition.id_report_local,\n" +
                        "report_head_exhibition.id_type_exhibition,\n" +
                        "report_head_exhibition.id_pdv,\n" +
                        "report_head_exhibition.created_date,\n" +
                        "report_head_exhibition.hash\n" +
                        "FROM\n" +
                        "report\n" +
                        "INNER JOIN report_head_exhibition ON report_head_exhibition.id_report_local = report.id\n" +
                        "WHERE report.id_report_server>0 AND report_head_exhibition.send=0\n" +
                        "ORDER BY report_head_exhibition.id_report_local ASC",
                null);
        List<List<DtoReportHeadExhibition>> lst = new ArrayList<>();
        List<DtoReportHeadExhibition> subLst = new ArrayList<>();
        DtoReportHeadExhibition catalogo;
        int tmpidReport;// variable para guardar el idreporte del cursor
        // anterior
        if (cursor.moveToFirst()) {
            // guardamos el idReporte del primer row
            tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local"));

            do {
                catalogo = new DtoReportHeadExhibition();
                catalogo.setIdReport(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_server")));
                catalogo.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                catalogo.setIdReportLocal(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_local")));
                catalogo.setIdTypeExhibition(cursor.getInt(cursor.getColumnIndexOrThrow("id_type_exhibition")));
                catalogo.setIdPdv(cursor.getInt(cursor.getColumnIndexOrThrow("id_pdv")));
                catalogo.setCreatedDate(cursor.getString(cursor.getColumnIndexOrThrow("created_date")));
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
                    subLst = new ArrayList<DtoReportHeadExhibition>();
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
        db.update(TABLE_NAME, cv, "id_report_local=" + id, null);
        db.close();
    }

    public int deleteByHash(String hash) {
        int resp = 0;
        try {
            db = helper.getWritableDatabase();
            resp = db.delete(TABLE_NAME, "hash=?", new String[]{hash});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return resp;
    }
}
