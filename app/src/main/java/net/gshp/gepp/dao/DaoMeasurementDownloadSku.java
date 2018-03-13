package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMeasurementDownloadSku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 8/03/18.
 */

public class DaoMeasurementDownloadSku extends DAO {
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String Table_NAME = "mdf_sku";
    public static String PK_FIELD = "idMeasurement";


    private final String ID_MEASUREMENT = "idMeasurement";
    private final String ID_ITEM_RELATION = "idItemRelation";
    private final String DESCRIPTION = "description";
    private final String TITLE = "title";
    private final String URL = "url";
    private final String EXT = "ext";
    private final String MD5="md5";
    private final String  ACTIVITY="active";

    public DaoMeasurementDownloadSku() {
        super(Table_NAME, PK_FIELD);
    }
    /**
     * Insert
     */

    public int Insert(List<DtoMeasurementDownloadSku> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;

        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID_MEASUREMENT + "," + ID_ITEM_RELATION + "," + DESCRIPTION + "," + TITLE + "," + URL + "," + EXT + ","+MD5+","+ACTIVITY+") VALUES(?,?,?,?,?,?,?,?);");
            db.beginTransaction();

            for (DtoMeasurementDownloadSku catalog : obj) {
                try {
                    insStmnt.bindLong(1, catalog.getIdMeasurement());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }

                try {
                    insStmnt.bindLong(2, catalog.getIdItemRelation());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, catalog.getDescription());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, catalog.getTitle());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindString(5, catalog.getUrl());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
                }
                try {
                    insStmnt.bindString(6, catalog.getExt());
                } catch (Exception e) {
                    insStmnt.bindNull(6);
                }
                try {
                    insStmnt.bindString(7, catalog.getMd5());
                }catch (Exception e){
                    insStmnt.bindNull(7);
                }try {
                    insStmnt.bindString(8, String.valueOf(System.currentTimeMillis()));
                }catch (Exception e){
                    insStmnt.bindNull(8);
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


    public List<DtoMeasurementDownloadSku> selectDodwnload() {
        db = helper.getReadableDatabase();
        String qry = "SELECT\n" +
                "mdf_sku.idItemRelation,\n" +
                "mdf_sku.title,\n" +
                "mdf_sku.description,\n" +
                "mdf_sku.url,\n" +
                "mdf_sku.ext,\n"+
                "mdf_sku.md5,\n"+
                "mdf_sku.idMeasurement\n" +
                "FROM\n" +
                "mdf_sku\n" +
                "INNER JOIN measurement_download ON measurement_download.id=mdf_sku.idMeasurement\n" +
                "WHERE measurement_download.endDate>=date('now')";
        cursor = db.rawQuery(qry, null);
        List<DtoMeasurementDownloadSku> obj = new ArrayList<>();
        DtoMeasurementDownloadSku catalogo;
        if (cursor.moveToFirst()) {
            do {
                catalogo = new DtoMeasurementDownloadSku();
                catalogo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                catalogo.setIdItemRelation(cursor.getInt(cursor.getColumnIndexOrThrow(ID_ITEM_RELATION)));
                catalogo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                catalogo.setExt(cursor.getString(cursor.getColumnIndexOrThrow(EXT)));
                catalogo.setMd5(cursor.getString(cursor.getColumnIndexOrThrow(MD5)));
                catalogo.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
                obj.add(catalogo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }


    /**
     * Metodo para contar archivos descargados
     * @return
     */
    public int countDownLoadFile() {
        int count = 0;
        db = helper.getWritableDatabase();
        String qry = "SELECT\n" +
                "count(DISTINCT file_view.id_file) as count\n" +
                "FROM\n" +
                "mdf_sku \n" +
                "INNER JOIN measurement_download ON measurement_download.id= mdf_sku.idMeasurement\n" +
                "INNER JOIN file_view on file_view.id_file= mdf_sku.idItemRelation\n" +
                "WHERE measurement_download.endDate>=date('now')";
        cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return count;
    }

}
