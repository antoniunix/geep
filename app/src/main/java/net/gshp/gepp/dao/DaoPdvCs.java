package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoPdvCS;

import java.util.List;

/**
 * Created by leo on 14/03/18.
 */

public class DaoPdvCs extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "pdvCs";
    public static String PK_FIELD = "pdv_id";

    private final String IDPDV = "pdv_id";
    private final String OPORUNITY = "oportunity";
    private final String SUCCESPHOTO = "success_photo";
    private final String CLUESTER = "cluster_total";
    private final String EXECUTION = "execution_time";
    private final String COLASMS = "colas_ms";
    private final String COLASS = "colas_ss";
    private final String SABORESMS = "sabores_ms";
    private final String SABORESS = "sabores_ss";
    private final String AGUA = "agua";
    private final String GAYTO = "gatorade";
    private final String LIPTON = "lipton";
    private final String START = "starbucks";
    private final String JUMEX = "jumex";
    private final String MIXER = "mixers";

    public DaoPdvCs() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * INSERT
     */

    public int Insert(List<DtoPdvCS> lstDtoPdvCS) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            String qry = "INSERT INTO " + TABLE_NAME +
                    "( " + IDPDV + "," + OPORUNITY + "," + SUCCESPHOTO + "," + CLUESTER + "," + EXECUTION + "," + COLASMS + "," + COLASS +
                    "," + SABORESMS + "," + SABORESS + "," + AGUA + "," + GAYTO + "," + LIPTON + "," + START + "," + JUMEX + "," + MIXER + " )" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            SQLiteStatement inStatement = db.compileStatement(qry);
            db.beginTransaction();

            for (int i = 0; i < lstDtoPdvCS.size(); i++) {
                DtoPdvCS dto = lstDtoPdvCS.get(i);
                try {
                    inStatement.bindLong(1, dto.getPdv_id());
                } catch (Exception e) {
                    inStatement.bindNull(1);
                }
                try {
                    inStatement.bindString(2, dto.getOportunity());
                } catch (Exception e) {
                    inStatement.bindNull(2);
                }
                try {
                    inStatement.bindString(3, dto.getSuccess_photo());
                } catch (Exception e) {
                    inStatement.bindNull(3);
                }
                try {
                    inStatement.bindString(4, dto.getCluster_total());
                } catch (Exception e) {
                    inStatement.bindNull(4);
                }
                try {
                    inStatement.bindString(5, dto.getExecution_time());
                } catch (Exception e) {
                    inStatement.bindNull(5);
                }
                try {
                    inStatement.bindString(6, dto.getColas_ms());
                } catch (Exception e) {
                    inStatement.bindNull(6);
                }
                try {
                    inStatement.bindString(7, dto.getColas_ss());
                } catch (Exception e) {
                    inStatement.bindNull(7);
                }
                try {
                    inStatement.bindString(8, dto.getSabores_ms());
                } catch (Exception e) {
                    inStatement.bindNull(8);
                }
                try {
                    inStatement.bindString(9, dto.getSabores_ss());
                } catch (Exception e) {
                    inStatement.bindNull(9);
                }
                try {
                    inStatement.bindString(10, dto.getAgua());
                } catch (Exception e) {
                    inStatement.bindNull(10);
                }
                try {
                    inStatement.bindString(11, dto.getGatorade());
                } catch (Exception e) {
                    inStatement.bindNull(11);
                }
                try {
                    inStatement.bindString(12, dto.getLipton());
                } catch (Exception e) {
                    inStatement.bindNull(12);
                }
                try {
                    inStatement.bindString(13, dto.getJumex());
                } catch (Exception e) {
                    inStatement.bindNull(13);
                }
                try {
                    inStatement.bindString(14, dto.getStarbucks());
                } catch (Exception e) {
                    inStatement.bindNull(14);
                }
                try {
                    inStatement.bindString(15, dto.getMixers());
                } catch (Exception e) {
                    inStatement.bindNull(15);
                }
                inStatement.executeInsert();
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

    public DtoPdvCS selectAll(long idPdv) {
        db = helper.getReadableDatabase();
        String qry = "SELECT *\n" +
                "FROM \n" +
                TABLE_NAME + " \n where pdv_id=" + idPdv;
        cursor = db.rawQuery(qry, null);
        DtoPdvCS catalogo = new DtoPdvCS();
        if (cursor.moveToFirst()) {
            catalogo.setPdv_id(cursor.getInt(cursor.getColumnIndexOrThrow(IDPDV)));
            catalogo.setOportunity(cursor.getString(cursor.getColumnIndexOrThrow(OPORUNITY)));
            catalogo.setSuccess_photo(cursor.getString(cursor.getColumnIndexOrThrow(SUCCESPHOTO)));
            catalogo.setCluster_total(cursor.getString(cursor.getColumnIndexOrThrow(CLUESTER)));
            catalogo.setExecution_time(cursor.getString(cursor.getColumnIndexOrThrow(EXECUTION)));
            catalogo.setColas_ms(cursor.getString(cursor.getColumnIndexOrThrow(COLASMS)));
            catalogo.setColas_ss(cursor.getString(cursor.getColumnIndexOrThrow(COLASS)));
            catalogo.setSabores_ms(cursor.getString(cursor.getColumnIndexOrThrow(SABORESMS)));
            catalogo.setSabores_ss(cursor.getString(cursor.getColumnIndexOrThrow(SABORESS)));
            catalogo.setAgua(cursor.getString(cursor.getColumnIndexOrThrow(AGUA)));
            catalogo.setGatorade(cursor.getString(cursor.getColumnIndexOrThrow(GAYTO)));
            catalogo.setLipton(cursor.getString(cursor.getColumnIndexOrThrow(LIPTON)));
            catalogo.setStarbucks(cursor.getString(cursor.getColumnIndexOrThrow(START)));
            catalogo.setJumex(cursor.getString(cursor.getColumnIndexOrThrow(JUMEX)));
            catalogo.setMixers(cursor.getString(cursor.getColumnIndexOrThrow(MIXER)));
        }
        cursor.close();
        db.close();
        return catalogo;

    }


}
