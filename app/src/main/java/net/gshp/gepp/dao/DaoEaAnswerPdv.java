package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoEaAnswerPdv;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoEaAnswerPdv extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "EAAnswerPdv";
    public static String PK_FIELD = "id_pdv";

    private final String ID_PDV = "id_pdv";
    private final String ID_POLL = "id_poll";

    public DaoEaAnswerPdv() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoEaAnswerPdv> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID_PDV + "," + ID_POLL + ") VALUES(?,?);");
            db.beginTransaction();
            for (DtoEaAnswerPdv dto : obj) {
                try {
                    insStmnt.bindLong(1, dto.getId_pdv());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, dto.getId_poll());
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
}
