package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.util.Log;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportExhibitionMantained;
import net.gshp.gepp.util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leo on 12/03/18.
 */

public class DaoReportExhibitionMantained extends DAO {

    public static String TABLE_NAME = "report_exhibition_mantained";
    public static String PK_FIELD = "id_report_local";

    public DaoReportExhibitionMantained() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert All
     */
    public int insert(List<DtoReportExhibitionMantained> obj, DtoBundle dtoBundle) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            for (DtoReportExhibitionMantained dto : obj) {
                if (dto.getId_exhibition_group() > 0 && dto.getTypeModule() == 1) {
                    cv.put("id_report_local", dtoBundle.getIdReportLocal());
                    cv.put("hash_exhibition", dto.getHashExhibition());
                    cv.put("is_exhibit", (dto.getIsExhibit() == 1 || dto.getIsExhibit() == 2) ? dto.getIsExhibit() :
                            ContextApp.context.getResources().getInteger(R.integer.INCOMPLETE));
                    cv.put("send", 0);
                    cv.put("hash", Crypto.MD5(System.currentTimeMillis() + "" + new Random().nextInt()));
                    resp = (int) db.insert(TABLE_NAME, null, cv);
                }
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


    /**
     * Metodo para validar cuantos items fueron contestados en este reporte
     *
     * @param idReportLocal
     * @return
     */
    public int isReport(long idReportLocal) {
        db = helper.getReadableDatabase();
        int isReport = 0;
        String qry = "SELECT count(*) AS count\n" +
                "FROM\n" +
                "report_exhibition_mantained\n" +
                "WHERE report_exhibition_mantained.id_report_local=" + idReportLocal + "\n" +
                "AND report_exhibition_mantained.is_exhibit>0";
        cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            isReport = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return isReport;
    }

    /**
     * SelectFamilySku
     */
    public List<DtoReportExhibitionMantained> select(DtoBundle dtobundle, DtoPdv dtoPdv) {
        db = helper.getWritableDatabase();

        String qry = "SELECT DISTINCT \n" +
                " 1 \"type_module\", \n" +
                " c_exhibition.exhibition_name, \n" +
                " c_exhibition.min_photos, \n" +
                " c_exhibition.max_photos, \n" +
                " c_exhibition.end_date, \n" +
                " c_exhibition.location, \n" +
                " c_exhibition.sub_famlly, \n" +
                " c_exhibition.family, \n" +
                " c_exhibition.category, \n" +
                " c_exhibition.manufacturer, \n" +
                " c_exhibition.hash, \n" +
                " c_exhibition.typeExhibition, \n" +
                " report_exhibition_mantained.is_exhibit, \n" +
                "count(DISTINCT report_exhibition_mantained_photo.path) count,\n" +
                " report_exhibition_mantained_photo.path, \n" +
                " c_exhibition.id_exhibition_group \n" +
                " FROM \n" +
                " c_exhibition \n" +
                " LEFT JOIN report_exhibition_mantained ON report_exhibition_mantained.hash_exhibition = c_exhibition.hash " +
                " AND report_exhibition_mantained.id_report_local=" + dtobundle.getIdReportLocal() + "\n" +
                " LEFT JOIN report_exhibition_mantained_photo ON c_exhibition.hash = report_exhibition_mantained_photo.hashExhibitionCatalog " +
                " AND report_exhibition_mantained_photo.id_report_local=" + dtobundle.getIdReportLocal() + "\n" +
                "WHERE  c_exhibition.id_pdv=" + dtoPdv.getId() + "\n" +
                "AND  strftime('%Y-%m-%d','now','localtime') <= c_exhibition.end_date\n" +
                "GROUP BY report_exhibition_mantained_photo.hashExhibitionCatalog, c_exhibition.hash  ORDER BY c_exhibition.id_exhibition_group DESC";
        Log.e("Exhibicion", "Exhibicion Mantenida" + qry);
        cursor = db.rawQuery(qry, null);
        List<DtoReportExhibitionMantained> obj = new ArrayList<>();
        DtoReportExhibitionMantained catalogo;
        if (cursor.moveToFirst()) {

            int exhibition_name = cursor.getColumnIndexOrThrow("exhibition_name");
            int min_photos = cursor.getColumnIndexOrThrow("min_photos");
            int max_photos = cursor.getColumnIndexOrThrow("max_photos");
            int end_date = cursor.getColumnIndexOrThrow("end_date");
            int location = cursor.getColumnIndexOrThrow("location");
            int sub_famlly = cursor.getColumnIndexOrThrow("sub_famlly");
            int family = cursor.getColumnIndexOrThrow("family");
            int category = cursor.getColumnIndexOrThrow("category");
            int manufacturer = cursor.getColumnIndexOrThrow("manufacturer");
            int value = cursor.getColumnIndexOrThrow("typeExhibition");
            int is_exhibit = cursor.getColumnIndexOrThrow("is_exhibit");
            int count = cursor.getColumnIndexOrThrow("count");
            int id_exhibition_group = cursor.getColumnIndexOrThrow("id_exhibition_group");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int type_module = cursor.getColumnIndexOrThrow("type_module");
            do {
                catalogo = new DtoReportExhibitionMantained();
                catalogo.setTypeModule(cursor.getInt(type_module));
                catalogo.setIdReportLocal(dtobundle.getIdReportLocal());
                catalogo.setExhibition_name(cursor.getString(exhibition_name));
                catalogo.setMin_photos(cursor.getInt(min_photos));
                catalogo.setMax_photos(cursor.getInt(max_photos));
                catalogo.setEnd_date(cursor.getString(end_date));
                catalogo.setLocation(cursor.getString(location));
                catalogo.setSub_famlly(cursor.getString(sub_famlly));
                catalogo.setFamily(cursor.getString(family));
                catalogo.setCategory(cursor.getString(category));
                catalogo.setManufacturer(cursor.getString(manufacturer));
                catalogo.setValue(cursor.getString(value));
                catalogo.setHashExhibition(cursor.getString(hash));
                catalogo.setIsExhibit(cursor.getInt(is_exhibit));
                catalogo.setPhotoDone(cursor.getInt(count));
                catalogo.setId_exhibition_group(cursor.getInt(id_exhibition_group));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoReportExhibitionMantained> selectTemp(DtoBundle dtobundle) {
        db = helper.getWritableDatabase();
        String query = "SELECT DISTINCT\n" +
                " 2 \"type_module\", \n" +
                "type_exhibition.value,\n" +
                "datetime(report_head_exhibition.created_date/1000, 'unixepoch','localtime') create_date,\n" +
                "measurement_item_exhibition.value AS location,\n" +
                "report_exhibition_detail.id_exhibition_group,\n" +
                "report_exhibition_detail.family,\n" +
                "report_exhibition_detail.hash,\n" +
                "report_exhibition_detail.hash_exhibition\n" +
                "FROM\n" +
                "report_head_exhibition\n" +
                "INNER JOIN type_exhibition ON type_exhibition.id_item_relation = report_head_exhibition.id_type_exhibition\n" +
                "INNER JOIN report_exhibition_detail ON report_exhibition_detail.hash_exhibition = report_head_exhibition.hash and report_exhibition_detail.id_report_local=" + dtobundle.getIdReportLocal() + "\n" +
                "INNER JOIN measurement_item_exhibition ON measurement_item_exhibition.id_item_relation = report_exhibition_detail.location and measurement_item_exhibition.id_type_catalog=5\n" +
                "";
        cursor = db.rawQuery(query, null);
        Log.e("Exhibicion", "Exhibicion Temporal" + query);
        List<DtoReportExhibitionMantained> obj = new ArrayList<>();
        DtoReportExhibitionMantained catalogo;
        if (cursor.moveToFirst()) {
            int type_module = cursor.getColumnIndexOrThrow("type_module");
            int value = cursor.getColumnIndexOrThrow("value");
            int create_date = cursor.getColumnIndexOrThrow("create_date");
            int location = cursor.getColumnIndexOrThrow("location");
            int id_exhibition_group = cursor.getColumnIndexOrThrow("id_exhibition_group");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int family = cursor.getColumnIndexOrThrow("family");
            int hashExibition = cursor.getColumnIndexOrThrow("hash_exhibition");
            do {
                catalogo = new DtoReportExhibitionMantained();
                catalogo.setTypeModule(cursor.getInt(type_module));
                catalogo.setLocation(cursor.getString(location));
                catalogo.setCreateDate(cursor.getString(create_date));
                catalogo.setExhibition_name(cursor.getString(value));
                catalogo.setValue(cursor.getString(value));
                catalogo.setLocation(cursor.getString(location));
                catalogo.setFamily(cursor.getString(family));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setId_exhibition_group(cursor.getInt(id_exhibition_group));
                catalogo.setHashExhibition(cursor.getString(hashExibition));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }
}
