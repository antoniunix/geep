package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoScannAlert;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoScannAlert extends DAO {
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "scann_alert";
    public static String PK_FIELD = "id";


    private final String ID = "id";
    private final String ID_SKU = "id_sku";
    private final String ID_PDV = "id_pdv";
    private final String ID_TP = "id_tp";
    private final String KEY = "key";
    private final String SKU_DESCRIPTION = "sku_description";

    private final String PROMEDIOVTASEMANAL = "promedioVtaSemanal";
    private final String VENTASEMANAACTUAL = "ventaSemanaActual";
    private final String VENTASEMANAANTERIOR = "ventaSemanaAnterior";
    private final String INVUNIDADESSEMANAACTUAL = "invUnidadesSemanaActual";
    private final String DIASINV = "diasInv";
    private final String INCREMENT = "increment";

    public DaoScannAlert() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoScannAlert> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            String qry = "INSERT INTO " + TABLE_NAME + " (" + ID + ","
                    + ID_SKU + "," + ID_PDV + "," + ID_TP + "," + KEY + ","
                    + SKU_DESCRIPTION + "," + PROMEDIOVTASEMANAL + "," + VENTASEMANAACTUAL + "," + VENTASEMANAANTERIOR
                    + "," + INVUNIDADESSEMANAACTUAL + "," + DIASINV + "," + INCREMENT + ")"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
            SQLiteStatement insStatement = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoScannAlert dto : obj) {

                try {
                    insStatement.bindLong(1, dto.getId());
                } catch (Exception e) {
                    insStatement.bindNull(1);
                }
                try {
                    insStatement.bindLong(2, dto.getIdSku());
                } catch (Exception e) {
                    insStatement.bindNull(2);
                }
                try {
                    insStatement.bindLong(3, dto.getIdPdv());
                } catch (Exception e) {
                    insStatement.bindNull(3);
                }
                try {
                    insStatement.bindLong(4, dto.getIdTp());
                } catch (Exception e) {
                    insStatement.bindNull(4);
                }
                try {
                    insStatement.bindString(5, dto.getKey());
                } catch (Exception e) {
                    insStatement.bindNull(5);
                }
                try {
                    insStatement.bindString(6, dto.getSkuDescription());
                } catch (Exception e) {
                    insStatement.bindNull(6);
                }
                try {
                    insStatement.bindString(7, dto.getPromedioVtaSemanal());
                } catch (Exception e) {
                    insStatement.bindNull(7);
                }
                try {
                    insStatement.bindString(8, dto.getVentaSemanaActual());
                } catch (Exception e) {
                    insStatement.bindNull(8);
                }
                try {
                    insStatement.bindString(9, dto.getVentaSemanaAnterior());
                } catch (Exception e) {
                    insStatement.bindNull(9);
                }
                try {
                    insStatement.bindString(10, dto.getInvUnidadesSemanaActual());
                } catch (Exception e) {
                    insStatement.bindNull(10);
                }
                try {
                    insStatement.bindString(11, dto.getDiasInv());
                } catch (Exception e) {
                    insStatement.bindNull(11);
                }
                try {
                    insStatement.bindString(12, dto.getIncrement());
                } catch (Exception e) {
                    insStatement.bindNull(12);
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


}
