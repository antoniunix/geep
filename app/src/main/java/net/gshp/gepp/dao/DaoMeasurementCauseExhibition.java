package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import net.gshp.gepp.dto.DtoMeasurementFilter;
import net.gshp.gepp.dto.DtoPdv;
import net.gshp.gepp.dto.DtoReportExhibitionMantainedCause;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoMeasurementCauseExhibition extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "measurement_cause_exhibition";
    public static String PK_FIELD = "id_measurement";


    private final String ID_MEASUREMENT = "id_measurement";
    private final String ID_ITEM_RELATION = "id_item_relation";
    private final String VALUE = "value";

    public DaoMeasurementCauseExhibition() {
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
                    + TABLE_NAME + " (" + ID_MEASUREMENT + "," + ID_ITEM_RELATION + ","
                    + VALUE + ") " +
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

    public List<DtoReportExhibitionMantainedCause> select(DtoPdv dtoPdv) {
        db = helper.getWritableDatabase();
        String qry = "SELECT DISTINCT\n" +
                "measurement_cause_exhibition.id_item_relation,\n" +
                "measurement_cause_exhibition.value\n" +
                "FROM\n" +
                "measurement_head_exhibition\n" +
                "LEFT JOIN measurement_exhibition_canal ON measurement_exhibition_canal.id_measurement = measurement_head_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_client ON measurement_exhibition_client.id_measurement = measurement_head_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_format ON measurement_exhibition_format.id_measurement = measurement_head_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_pdv ON measurement_exhibition_pdv.id_measurement = measurement_head_exhibition.id\n" +
                "LEFT JOIN measurement_exhibition_rtm ON measurement_exhibition_rtm.id_measurement = measurement_head_exhibition.id\n" +
                "INNER JOIN measurement_cause_exhibition ON measurement_cause_exhibition.id_measurement = measurement_head_exhibition.id\n" +
                "WHERE  (measurement_exhibition_canal.id_item_relation=" + dtoPdv.getIdCanal() + " OR measurement_exhibition_canal.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_client.id_item_relation=" + dtoPdv.getIdClient() + " OR measurement_exhibition_client.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_format.id_item_relation=" + dtoPdv.getIdFormat() + " OR measurement_exhibition_format.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_pdv.id_item_relation=" + dtoPdv.getId() + " OR measurement_exhibition_pdv.id_item_relation ISNULL) AND\n" +
                "(measurement_exhibition_rtm.id_item_relation=" + dtoPdv.getIdRtm() + " OR measurement_exhibition_rtm.id_item_relation ISNULL) ";
        cursor = db.rawQuery(qry, null);
        List<DtoReportExhibitionMantainedCause> obj = new ArrayList<>();
        DtoReportExhibitionMantainedCause catalogo;
        if (cursor.moveToFirst()) {
            int idItemRelation = cursor.getColumnIndexOrThrow("id_item_relation");
            int value = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoReportExhibitionMantainedCause();
                catalogo.setIdCause(cursor.getInt(idItemRelation));
                catalogo.setComment(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public DtoReportExhibitionMantainedCause existCause(long idReportLocal, String hashExhibition) {
        db = helper.getWritableDatabase();
        String qry = "SELECT\n" +
                "report_exhibition_mantained_cause.id_cause,\n" +
                "report_exhibition_mantained_cause.comment\n" +
                "FROM\n" +
                "report_exhibition_mantained_cause\n" +
                "WHERE\n" +
                "report_exhibition_mantained_cause.id_report_local=" + idReportLocal + "\n" +
                "AND report_exhibition_mantained_cause.hash_exhibition='" + hashExhibition + "'";
        Log.e("exhibition",qry);
        cursor = db.rawQuery(qry, null);
        DtoReportExhibitionMantainedCause catalogo = new DtoReportExhibitionMantainedCause();
        if (cursor.moveToFirst()) {
            int id_cause = cursor.getColumnIndexOrThrow("id_cause");
            int comment = cursor.getColumnIndexOrThrow("comment");

            catalogo.setIdCause(cursor.getInt(id_cause));
            catalogo.setComment(cursor.getString(comment));
        }
        cursor.close();
        db.close();
        return catalogo;
    }
}
