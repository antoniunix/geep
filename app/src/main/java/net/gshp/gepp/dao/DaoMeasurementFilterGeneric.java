package net.gshp.gepp.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMeasurementFilter;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoMeasurementFilterGeneric extends DAO{

    private SQLiteDatabase db;

    public static String PK_FIELD = "id_item_relation";
    public String TABLE_NAME;

    private final String idItemRelation = "id_item_relation";
    private final String idMeasurement = "id_measurement";
    private final String value = "value";
    private final String filter1 = "filter1", filter2 = "filter2", filter3 = "filter3";

    /* Measurement Availability (Ma_Sku) */
    private final String min_photos = "min_photos", max_photos = "max_photos";
    private final String id_location = "id_location";

    /* Measurement Price (Mp_Sku) */
    private final String min_price = "min_price", max_price = "max_price";

    /* Measurement Expiration (Mex_Sku) */
    private final String min_date = "min_date", max_date = "max_date";
    private final String min_items = "min_items", max_items = "max_items";

    private final String _order="_order";


    /**
     *
     * @param TABLE_NAME
     *            es el nombre de la tabla sobre la que se trabajara
     */
    public DaoMeasurementFilterGeneric(String TABLE_NAME) {
        super(TABLE_NAME, PK_FIELD);
        this.TABLE_NAME = TABLE_NAME;
    }

    /**
     * Insert
     */
    public int Insert(List<DtoMeasurementFilter> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {

            SQLiteStatement insStmnt = db.compileStatement(
                    "" + "INSERT INTO " + TABLE_NAME + " (" + idItemRelation + "," + idMeasurement + ") VALUES(?,?);");
            db.beginTransaction();
            for (DtoMeasurementFilter dto : obj) {
                try {
                    insStmnt.bindLong(1, dto.getIdItemRelation());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, dto.getIdMeasurement());
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
            db.close();
        }
        return resp;
    }




    /**
     * Insert Generic With Value (idItemRelation, idMeasurement, value)
     */
    public int InsertWithValue(List<DtoMeasurementFilter> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {

            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO " + TABLE_NAME + " (" + idItemRelation
                    + "," + idMeasurement + "," + value + ") VALUES(?,?,?);");
            db.beginTransaction();
            for (DtoMeasurementFilter dto : obj) {
                try {
                    insStmnt.bindLong(1, dto.getIdItemRelation());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, dto.getIdMeasurement());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, dto.getValue());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                insStmnt.executeInsert();
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return resp;
    }

}
