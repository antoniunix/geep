package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import net.gshp.gepp.dto.DtoCStatusScann;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoCStatusScannAlert extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "c_status_scann_alert";
    public static String PK_FIELD = "id";

    private final String ID = "id";
    private final String VALUE = "value";
    private final String COLOR = "color";
    private final String ID_PROBLEM = "id_problem";


    public DaoCStatusScannAlert() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoCStatusScann> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID + "," + VALUE + "," + COLOR + "," + ID_PROBLEM + ") VALUES(?,?,?,?);");
            db.beginTransaction();
            for (DtoCStatusScann DtoCatalog : obj) {
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
                try {
                    insStmnt.bindString(3, DtoCatalog.getColor());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindLong(4, DtoCatalog.getIdProblem());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
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
     * Select
     */
    public List<DtoCStatusScann> Select() {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT id,value,id_problem,color FROM " + TABLE_NAME
                + " ORDER BY value ASC", null);
        List<DtoCStatusScann> obj = new ArrayList<DtoCStatusScann>();
        DtoCStatusScann catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow(ID);
            int value = cursor.getColumnIndexOrThrow(VALUE);
            int idProblem = cursor.getColumnIndexOrThrow(ID_PROBLEM);
            int color = cursor.getColumnIndexOrThrow(COLOR);
            do {
                catalogo = new DtoCStatusScann();
                catalogo.setId(cursor.getInt(id));
                catalogo.setValue(cursor.getString(value));
                catalogo.setIdProblem(cursor.getInt(idProblem));
                catalogo.setColor(cursor.getString(color));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    /**
     * Select
     */
    public List<DtoCStatusScann> SelectByProblem(long idTypeProblem) {
        db = helper.getReadableDatabase();
        Log.e("TP", "TP " + idTypeProblem);
        String qry = "SELECT id,value,id_problem,color FROM " + TABLE_NAME
                + " WHERE id_problem=" + idTypeProblem + " ORDER BY value ASC";
        Log.e("leo", "qry \n" + qry);
        cursor = db.rawQuery(qry, null);
        List<DtoCStatusScann> obj = new ArrayList<DtoCStatusScann>();
        DtoCStatusScann catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow(ID);
            int value = cursor.getColumnIndexOrThrow(VALUE);
            int idProblem = cursor.getColumnIndexOrThrow(ID_PROBLEM);
            int color = cursor.getColumnIndexOrThrow(COLOR);
            do {
                catalogo = new DtoCStatusScann();
                catalogo.setId(cursor.getInt(id));
                catalogo.setValue(cursor.getString(value));
                catalogo.setIdProblem(cursor.getInt(idProblem));
                catalogo.setColor(cursor.getString(color));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    /**
     * Select
     */
    public String SelectColor(long id) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT color FROM " + TABLE_NAME
                + " WHERE id=" + id, null);
        String catalogo = "";
        if (cursor.moveToFirst()) {
            int color = cursor.getColumnIndexOrThrow(COLOR);
            do {
                catalogo = cursor.getString(color);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return catalogo;
    }

}