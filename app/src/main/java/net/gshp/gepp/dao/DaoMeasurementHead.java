package net.gshp.gepp.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMeasurementHead;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoMeasurementHead extends DAO{

    private SQLiteDatabase db;
    public static String PK_FIELD="id";

    private final String id="id";
    private final String startDate="startDate";
    private final String endDate="endDate";
    private final String description="description";
    /**
     *
     * @param TABLE_NAME es el nombre de la tabla sobre la que se trabajara
     */
    public DaoMeasurementHead(String TABLE_NAME) {
        super(TABLE_NAME, PK_FIELD);
    }
    /**
     * Insert
     */
    public int Insert( List<DtoMeasurementHead> obj)
    {
        db=helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+id+","+startDate+","+endDate+","+description+") VALUES(?,?,?,?);");
            db.beginTransaction();
            for (DtoMeasurementHead dto : obj) {
                try {
                    insStmnt.bindLong(1, dto.getId());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindString(2, dto.getStartDate());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, dto.getEndDate());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, dto.getDescription());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                insStmnt.executeInsert();
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
        return resp;
    }

}
