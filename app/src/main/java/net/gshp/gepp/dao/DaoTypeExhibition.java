package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMeasurementFilter;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoTypeExhibition extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "type_exhibition";
    public static String PK_FIELD = "id_measurement";


    private final String ID_MEASUREMENT = "id_measurement";
    private final String ID_ITEM_RELATION = "id_item_relation";
    private final String VALUE = "value";

    public DaoTypeExhibition() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoMeasurementFilter> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID_MEASUREMENT + "," + ID_ITEM_RELATION +","
                    +VALUE+ ") " +
                    "VALUES(?,?,?);");
            db.beginTransaction();
            for (DtoMeasurementFilter DtoCatalog : obj) {
                try {
                    insStmnt.bindLong(1, DtoCatalog.getIdMeasurement());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, DtoCatalog.getIdItemRelation());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, DtoCatalog.getValue());
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
