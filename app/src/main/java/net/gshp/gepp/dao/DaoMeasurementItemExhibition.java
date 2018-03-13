package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMeasurementItemExhibition;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoMeasurementItemExhibition extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "measurement_item_exhibition";
    public static String PK_FIELD = "id_measurement";

    private final String ID_MEASUREMENT = "id_measurement";
    private final String ID_TYPE_CATALOG = "id_type_catalog";
    private final String ID_ITEM_RELATION = "id_item_relation";
    private final String VALUE = "value";
    private final String PARENT = "parent";
    private final String ORDER = "_order";
    private final String WEIGHT = "weight";

    public DaoMeasurementItemExhibition() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoMeasurementItemExhibition> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID_MEASUREMENT + "," + ID_TYPE_CATALOG +","
                    +ID_ITEM_RELATION+","+VALUE+","+PARENT+","+ORDER+","+WEIGHT+") " +
                    "VALUES(?,?,?,?,?,?,?);");
            db.beginTransaction();
            for (DtoMeasurementItemExhibition DtoCatalog : obj) {
                try {
                    insStmnt.bindLong(1, DtoCatalog.getIdMeasurement());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, DtoCatalog.getIdTypeCatalog());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindLong(3, DtoCatalog.getIdItemRelation());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, DtoCatalog.getValue());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindLong(5, DtoCatalog.getParent());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
                }
                try {
                    insStmnt.bindLong(6, DtoCatalog.getOrder());
                } catch (Exception e) {
                    insStmnt.bindNull(6);
                }
                try {
                    insStmnt.bindLong(7, DtoCatalog.getWeight());
                } catch (Exception e) {
                    insStmnt.bindNull(7);
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
