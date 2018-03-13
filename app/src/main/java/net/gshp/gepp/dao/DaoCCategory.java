package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoPdv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoCCategory extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "c_category";
    public static String PK_FIELD = "id";

    private final String ID = "id";
    private final String VALUE = "value";

    public DaoCCategory() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoCatalog> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID + "," + VALUE + ") VALUES(?,?);");
            db.beginTransaction();
            for (DtoCatalog DtoCatalog : obj) {
                try {
                    insStmnt.bindLong(1, DtoCatalog.getId());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindString(2, DtoCatalog.getValue());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                insStmnt.executeInsert();
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }
	/*
	 * "SELECT\n" + "c_category.id,\n" + "c_category.value\n" + "FROM\n" +
	 * "c_category"
	 */
    /**
     * Método para seleccionar los SKUs con su respectiva categoria
     */
    public List<DtoCatalog> selectCategorySku() {
        db = helper.getReadableDatabase();
        String qry = "SELECT DISTINCT\n" + "c_category.id, \n"
                + "c_category.value\n" + "FROM\n" + "c_category\n"
                + "INNER JOIN sku_sku ON c_category.id = sku_sku.id_category\n"
                + "ORDER BY c_category.value";
        cursor = db.rawQuery(qry, null);
        List<DtoCatalog> obj = new ArrayList<DtoCatalog>();
        DtoCatalog catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow(ID);
            int value = cursor.getColumnIndexOrThrow(VALUE);
            do {
                catalogo = new DtoCatalog();
                catalogo.setId(cursor.getInt(id));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

        public List<DtoCatalog>Select(DtoPdv dtoPdv, DtoBundle dtoBundle){
        db = helper.getWritableDatabase();
        String qry =  "SELECT DISTINCT\n" +
                "  c_category.id,\n" +
                "c_category.value,\n"+
                "count(report_sos.id_category) count\n"+
                "FROM \n" +
                "measurement_sos_head \n" +
                " INNER JOIN measurement_sos ON measurement_sos.id_measurement = measurement_sos_head.id   \n" +
                "INNER JOIN c_category ON c_category.id = measurement_sos.id_category\n" +
                "LEFT JOIN measurement_sos_format ON measurement_sos_Format.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN measurement_sos_pdv ON measurement_sos_pdv.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN measurement_sos_canal ON measurement_sos_canal.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN measurement_sos_client ON measurement_sos_client.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN measurement_sos_region ON measurement_sos_region.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN measurement_sos_rtm ON measurement_sos_rtm.id_mesurement = measurement_sos_head.id \n" +
                "LEFT JOIN report_sos ON report_sos.id_sku=measurement_sos.id_item_relation AND report_sos.id_report_local="+dtoBundle.getIdReportLocal()+" AND report_sos.id_category=c_category.id \n" +
                " WHERE (measurement_sos_Format.id_item="+dtoPdv.getIdFormat()+" OR measurement_sos_Format.id_item ISNULL) AND\n" +
                "(measurement_sos_pdv.id_item="+dtoPdv.getId()+" OR measurement_sos_Pdv.id_item ISNULL) AND\n" +
                "(measurement_sos_canal.id_item="+dtoPdv.getIdCanal()+" OR measurement_sos_canal.id_item ISNULL) AND\n" +
                "(measurement_sos_client.id_item="+dtoPdv.getIdClient()+" OR measurement_sos_client.id_item ISNULL) AND\n" +
                "(measurement_sos_region.id_item="+dtoPdv.getIdRegion()+" OR measurement_sos_region.id_item ISNULL) AND\n" +
                "(measurement_sos_rtm.id_item="+dtoPdv.getIdRtm()+" OR measurement_sos_rtm.id_item ISNULL) AND\n" +
                "date('now','localtime') BETWEEN measurement_sos_head.startDate AND measurement_sos_head.endDate\n" +
                "GROUP BY c_category.id" ;
        cursor = db.rawQuery(qry, null);
        List<DtoCatalog>obj = new ArrayList<>();
        DtoCatalog catalog;
        if(cursor.moveToFirst()){
            int value= cursor.getColumnIndexOrThrow("value");
            int id=cursor.getColumnIndexOrThrow("id");
            int count=cursor.getColumnIndexOrThrow("count");
            do {
                catalog = new DtoCatalog();
                catalog.setValue(cursor.getString(value));
                catalog.setId(cursor.getInt(id));
                catalog.setSelected(cursor.getInt(count)>0);
                obj.add(catalog);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }
}
