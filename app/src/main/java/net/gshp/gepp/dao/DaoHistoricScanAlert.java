package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoHistoric;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoHistoricScanAlert extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "historic_scan_alert";
    public static String PK_FIELD = "id_pdv";

    private final String ID_PDV = "id_pdv";
    private final String COUNT = "count";
    private final String VALUE = "value";
    private final String WEEK = "week";

    public DaoHistoricScanAlert() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoHistoric> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            String qry = "INSERT INTO " + TABLE_NAME + " (" + ID_PDV + ","+COUNT+","
                    + VALUE + "," + WEEK +")"
                    + "VALUES(?,?,?,?);";
            SQLiteStatement insStatement = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoHistoric dto : obj) {

                try {
                    insStatement.bindLong(1, dto.getIdPdv());
                } catch (Exception e) {
                    insStatement.bindNull(1);
                }
                try {
                    insStatement.bindLong(2, dto.getCount());
                } catch (Exception e) {
                    insStatement.bindNull(2);
                }
                try {
                    insStatement.bindLong(3, dto.getValue());
                } catch (Exception e) {
                    insStatement.bindNull(3);
                }
                try {
                    insStatement.bindLong(4, dto.getWeek());
                } catch (Exception e) {
                    insStatement.bindNull(4);
                }
                insStatement.executeInsert();
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

    /**
     * Select
     */
    public List<DtoHistoric> SelectSP(long idPdv) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT \n" +
                "				historic_scan_alert.id_pdv, \n" +
                "				historic_scan_alert.count, \n" +
                "				historic_scan_alert.value, \n" +
                "				historic_scan_alert.week \n" +
                "				FROM \n" +
                "				historic_scan_alert \n" +
                "				where  historic_scan_alert.value= 1 and historic_scan_alert.id_pdv="+idPdv+"\n" +
                "				ORDER BY  historic_scan_alert.week DESC", null);
        List<DtoHistoric> obj = new ArrayList<DtoHistoric>();
        DtoHistoric catalogo;
        if (cursor.moveToFirst()) {
            int id_Pdv = cursor.getColumnIndexOrThrow(ID_PDV);
            int count = cursor.getColumnIndexOrThrow("count");
            int value = cursor.getColumnIndexOrThrow(VALUE);
            int week = cursor.getColumnIndexOrThrow("week");
            do {
                catalogo = new DtoHistoric();
                catalogo.setIdPdv(cursor.getInt(id_Pdv));
                catalogo.setCount(cursor.getInt(count));
                catalogo.setValue(cursor.getInt(value));
                catalogo.setWeek(cursor.getInt(week));
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
    public List<DtoHistoric> SelectISV(long idPdv) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT \n" +
                "				historic_scan_alert.id_pdv, \n" +
                "				historic_scan_alert.count, \n" +
                "				historic_scan_alert.value, \n" +
                "				historic_scan_alert.week \n" +
                "				FROM \n" +
                "				historic_scan_alert \n" +
                "				where  historic_scan_alert.value= 2 and historic_scan_alert.id_pdv="+idPdv+"\n" +
                "				ORDER BY  historic_scan_alert.week DESC", null);
        List<DtoHistoric> obj = new ArrayList<DtoHistoric>();
        DtoHistoric catalogo;
        if (cursor.moveToFirst()) {
            int id_Pdv = cursor.getColumnIndexOrThrow(ID_PDV);
            int count = cursor.getColumnIndexOrThrow("count");
            int value = cursor.getColumnIndexOrThrow(VALUE);
            int week = cursor.getColumnIndexOrThrow("week");
            do {
                catalogo = new DtoHistoric();
                catalogo.setIdPdv(cursor.getInt(id_Pdv));
                catalogo.setCount(cursor.getInt(count));
                catalogo.setValue(cursor.getInt(value));
                catalogo.setWeek(cursor.getInt(week));
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
    public List<DtoHistoric> SelectPE(long idPdv) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT \n" +
                "				historic_scan_alert.id_pdv, \n" +
                "				historic_scan_alert.count, \n" +
                "				historic_scan_alert.value, \n" +
                "				historic_scan_alert.week \n" +
                "				FROM \n" +
                "				historic_scan_alert \n" +
                "				where  historic_scan_alert.value= 3 and historic_scan_alert.id_pdv="+idPdv+"\n" +
                "				ORDER BY  historic_scan_alert.week DESC", null);
        List<DtoHistoric> obj = new ArrayList<DtoHistoric>();
        DtoHistoric catalogo;
        if (cursor.moveToFirst()) {
            int id_Pdv = cursor.getColumnIndexOrThrow(ID_PDV);
            int count = cursor.getColumnIndexOrThrow("count");
            int value = cursor.getColumnIndexOrThrow(VALUE);
            int week = cursor.getColumnIndexOrThrow("week");
            do {
                catalogo = new DtoHistoric();
                catalogo.setIdPdv(cursor.getInt(id_Pdv));
                catalogo.setCount(cursor.getInt(count));
                catalogo.setValue(cursor.getInt(value));
                catalogo.setWeek(cursor.getInt(week));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

}
