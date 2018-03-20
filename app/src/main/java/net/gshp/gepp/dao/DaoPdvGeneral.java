package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoPdvInfoGeneral;

import java.util.List;

/**
 * Created by leo on 16/03/18.
 */

public class DaoPdvGeneral extends DAO {


    private SQLiteDatabase db;
    private Cursor cursor;


    public static String TABLE_NAME = "pdvInfoGeneral";
    public static String PK_FIELD = "id_pdv";

    public DaoPdvGeneral() {
        super(TABLE_NAME, PK_FIELD);
    }

    private final String IDPDV = "id_pdv";
    private final String TOTALPRODUCTO = "total_producto";
    private final String TOTALBANDERA = "total_producto_bandera";
    private final String COLASPORCENTAJE = "colas_porcentaje";
    private final String COLASBANDERA = "colas_bandera";
    private final String SABORESPORCENTAJE = "sabores_porcentaje";
    private final String SABORESBANDERA = "sabores_bandera";
    private final String AGUAPORCENTAJE = "agua_porcentaje";
    private final String AGUABANDERA = "agua_bandera";
    private final String GATORADEPORCENTAJE = "gatorade_porcentaje";
    private final String GATORADEBANDERA = "gatorade_bandera";
    private final String LIPTONPORCENTAJE = "lipton_porcentaje";
    private final String LIPTONBANDERA = "lipton_bandera";
    private final String JUMEXPORCENTAJE = "jumex_porcentaje";
    private final String JUMEXBANDERA = "jumex_bandera";
    private final String STARBUCKSPORCENTAJE = "starbucks_porcentaje";
    private final String STARTBUCKSBANDERA = "starbucks_bandera";
    private final String MIXERPORCENTAJE = "mixers_porcentaje";
    private final String MIXERBANDERA = "mixers_bandera";


    public int Insert(List<DtoPdvInfoGeneral> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;

        try {
            String qry = "INSERT INTO " + TABLE_NAME + " (" +
                    TOTALPRODUCTO + "," + TOTALBANDERA + "," +
                    COLASPORCENTAJE + "," + COLASBANDERA + "," +
                    SABORESPORCENTAJE + "," + SABORESBANDERA + "," +
                    AGUAPORCENTAJE + "," + AGUABANDERA + "," +
                    GATORADEPORCENTAJE + "," + GATORADEBANDERA + "," +
                    LIPTONPORCENTAJE + "," + LIPTONBANDERA + "," +
                    JUMEXPORCENTAJE + "," + JUMEXBANDERA + "," +
                    STARBUCKSPORCENTAJE + "," + STARTBUCKSBANDERA + "," +
                    MIXERPORCENTAJE + "," + MIXERBANDERA +","+IDPDV+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            SQLiteStatement insStatement = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoPdvInfoGeneral dto : obj) {
                try {
                    insStatement.bindString(1, dto.getTotal_producto());
                } catch (Exception e) {
                    insStatement.bindNull(1);
                }
                try {
                    insStatement.bindLong(2, dto.getTotal_producto_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(2);
                }
                try {
                    insStatement.bindString(3, dto.getColas_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(3);
                }
                try {
                    insStatement.bindLong(4, dto.getColas_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(4);
                }
                try {
                    insStatement.bindString(5, dto.getSabores_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(5);
                }
                try {
                    insStatement.bindLong(6, dto.getSabores_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(6);
                }
                try {
                    insStatement.bindString(7, dto.getAgua_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(7);
                }
                try {
                    insStatement.bindLong(8, dto.getAgua_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(8);
                }
                try {
                    insStatement.bindString(9, dto.getGatorade_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(9);
                }
                try {
                    insStatement.bindLong(10, dto.getGatorade_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(10);
                }
                try {
                    insStatement.bindString(11, dto.getLipton_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(11);
                }
                try {
                    insStatement.bindLong(12, dto.getLipton_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(12);
                }
                try {
                    insStatement.bindString(13, dto.getJumex_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(13);
                }
                try {
                    insStatement.bindLong(14, dto.getJumex_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(14);
                }
                try {
                    insStatement.bindString(15, dto.getStarbucks_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(15);
                }
                try {
                    insStatement.bindLong(16, dto.getStarbucks_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(16);
                }
                try {
                    insStatement.bindString(17, dto.getMixers_porcentaje());
                } catch (Exception e) {
                    insStatement.bindNull(17);
                }
                try {
                    insStatement.bindLong(18, dto.getMixers_bandera());
                } catch (Exception e) {
                    insStatement.bindNull(18);
                }
                try {
                    insStatement.bindLong(19, dto.getPdv_id());
                }catch (Exception e){
                    insStatement.bindNull(19);
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

    public DtoPdvInfoGeneral select(long idPdv){
        db= helper.getReadableDatabase();
        String qry ="SELECT * FROM "+TABLE_NAME+" WHERE id_pdv="+idPdv;
        cursor= db.rawQuery(qry,null);
        DtoPdvInfoGeneral catalogo= new DtoPdvInfoGeneral();
        if(cursor.moveToFirst()){
            do {
                catalogo.setTotal_producto(cursor.getString(cursor.getColumnIndexOrThrow(TOTALPRODUCTO)));
                catalogo.setTotal_producto_bandera(cursor.getInt(cursor.getColumnIndexOrThrow(TOTALBANDERA)));
                catalogo.setColas_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(COLASPORCENTAJE)));
                catalogo.setColas_bandera(cursor.getInt(cursor.getColumnIndexOrThrow(COLASBANDERA)));
                catalogo.setSabores_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(SABORESPORCENTAJE)));
                catalogo.setSabores_bandera(cursor.getInt(cursor.getColumnIndexOrThrow(SABORESBANDERA)));
                catalogo.setAgua_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(AGUAPORCENTAJE)));
                catalogo.setAgua_bandera(cursor.getInt(cursor.getColumnIndexOrThrow(AGUABANDERA)));
                catalogo.setGatorade_bandera(cursor.getInt(cursor.getColumnIndexOrThrow(GATORADEBANDERA)));
                catalogo.setGatorade_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(GATORADEPORCENTAJE)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return catalogo;
        }

    }



