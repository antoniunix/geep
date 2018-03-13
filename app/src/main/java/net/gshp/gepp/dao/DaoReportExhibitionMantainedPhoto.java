package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.util.Log;

import com.gshp.api.utils.Crypto;

import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportExhibitionMantainedPhoto;
import net.gshp.gepp.util.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leo on 12/03/18.
 */

public class DaoReportExhibitionMantainedPhoto extends DAO {

    public static String TABLE_NAME = "report_exhibition_mantained_photo";
    public static String PK_FIELD = "id_report_local";

    public DaoReportExhibitionMantainedPhoto() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert All
     */
    public int insert(List<DtoReportExhibitionMantainedPhoto> obj, DtoBundle dtoBundle) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();
            for (DtoReportExhibitionMantainedPhoto dto : obj) {

                cv.put("id_report_local", dtoBundle.getIdReportLocal());
                cv.put("id_report_exhibition_mantained", dto.getIdReportExhibitionMantained());
                cv.put("path", dto.getPath());
                cv.put("send", 0);
                cv.put("hash", Crypto.MD5(System.currentTimeMillis() + "" + new Random().nextInt()));
                resp = (int) db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error db");
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }

    /**
     * Insert All
     */
    public int insertOneRow(DtoReportExhibitionMantainedPhoto obj, DtoBundle dtoBundle) {
        db = helper.getWritableDatabase();
        ContentValues cv;
        int resp = 0;
        try {
            db.beginTransaction();
            cv = new ContentValues();

            cv.put("id_report_local", dtoBundle.getIdReportLocal());
            cv.put("id_report_exhibition_mantained", obj.getIdReportExhibitionMantained());
            cv.put("path", obj.getPath());
            cv.put("hashExhibitionCatalog", obj.getHashExhibitionCatalog());
            cv.put("send", 0);
            cv.put("hash", Crypto.MD5(System.currentTimeMillis() + "" + new Random().nextInt()));
            resp = (int) db.insert(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error db");
        } finally {
            db.endTransaction();
        }
        db.close();
        return resp;
    }

    public List<DtoReportExhibitionMantainedPhoto> SelectToSendPhotoReprocesed()
    {
        db=helper.getReadableDatabase();
        cursor=db.rawQuery("SELECT\n" +
                " report.id_pdv, \n" +
                " report_exhibition_mantained.hash, \n" +
                " report_exhibition_mantained_photo.path, \n" +
                " report_exhibition_mantained_photo.id\n" +
                " FROM \n" +
                " report_exhibition_mantained \n" +
                " INNER JOIN report_exhibition_mantained_photo \n" +
                "ON report_exhibition_mantained_photo.id_report_local = report_exhibition_mantained.id_report_local \n" +
                " AND report_exhibition_mantained_photo.hashExhibitionCatalog = report_exhibition_mantained.hash_exhibition\n" +
                " INNER JOIN report ON report.id = report_exhibition_mantained.id_report_local \n" +
                " AND report.id_report_server>0 \n" +
                " WHERE report_exhibition_mantained.send=1 \n" +
                "AND report_exhibition_mantained_photo.send_img ISNULL\n" +
                "AND report.date < 1493423940000\n" +
                " ORDER BY report_exhibition_mantained_photo.id_report_local ASC",null);

        List<DtoReportExhibitionMantainedPhoto> obj=new ArrayList<>();
        DtoReportExhibitionMantainedPhoto catalogo;
        if(cursor.moveToFirst())
        {
            int id=cursor.getColumnIndexOrThrow("id");
            int hash=cursor.getColumnIndexOrThrow("hash");
            int path=cursor.getColumnIndexOrThrow("path");
            int id_pdv=cursor.getColumnIndexOrThrow("id_pdv");
            do
            {
                catalogo=new DtoReportExhibitionMantainedPhoto();
                catalogo.setId(cursor.getInt(id));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setPath(cursor.getString(path));
                catalogo.setIdPdv(cursor.getInt(id_pdv));

                if(new File(catalogo.getPath()).exists()){
                    String md5= Config.checMd5(catalogo.getPath());
                    catalogo.setMd5(md5);
                    Log.e("md5", "Hash "+catalogo.getHash()+" md5 "+ md5);
                    obj.add(catalogo);

                }

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;

    }

    public List<DtoReportExhibitionMantainedPhoto> SelectToSendPhoto()
    {
        db=helper.getReadableDatabase();
        cursor=db.rawQuery("SELECT\n" +
                "report.id_pdv,\n" +
                "report_exhibition_mantained.hash,\n" +
                "report_exhibition_mantained_photo.path,\n" +
                "report_exhibition_mantained_photo.id\n" +
                "FROM\n" +
                "report_exhibition_mantained\n" +
                "INNER JOIN report_exhibition_mantained_photo ON report_exhibition_mantained_photo.id_report_local = report_exhibition_mantained.id_report_local\n" +
                "AND report_exhibition_mantained_photo.hashExhibitionCatalog = report_exhibition_mantained.hash_exhibition\n"+
                "AND report_exhibition_mantained_photo.send=0\n" +
                "INNER JOIN report ON report.id = report_exhibition_mantained.id_report_local\n" +
                "AND report.id_report_server>0\n" +
                "WHERE report_exhibition_mantained.send=1\n" +
                "ORDER BY report_exhibition_mantained_photo.id_report_local ASC",null);

        List<DtoReportExhibitionMantainedPhoto> obj=new ArrayList<>();
        DtoReportExhibitionMantainedPhoto catalogo;
        if(cursor.moveToFirst())
        {
            int id=cursor.getColumnIndexOrThrow("id");
            int hash=cursor.getColumnIndexOrThrow("hash");
            int path=cursor.getColumnIndexOrThrow("path");
            int id_pdv=cursor.getColumnIndexOrThrow("id_pdv");
            do
            {
                catalogo=new DtoReportExhibitionMantainedPhoto();
                catalogo.setId(cursor.getInt(id));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setPath(cursor.getString(path));
                catalogo.setIdPdv(cursor.getInt(id_pdv));

                if(new File(catalogo.getPath()).exists()){
                    obj.add(catalogo);
                }

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public void updatePhoto(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send",1);
        Log.e("send", "exhibition mantenida foto id"+id);
        db.update(TABLE_NAME, cv, "id=" + id, null);
        db.close();
    }
    public void updatePhotoReprocesed(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send_img",1);
        Log.e("send", "exhibition foto reprocesed"+id);
        db.update(TABLE_NAME, cv, "id=" + id, null);
        db.close();
    }


    public int deletePhotoById(int _idReportLocal, String hashExhibition)
    {
        int resp=0;
        try {
            db=helper.getWritableDatabase();
            resp=db.delete(TABLE_NAME, PK_FIELD + "="+_idReportLocal+" AND  hashExhibitionCatalog='"+hashExhibition+"'",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            db.close();
        }
        return resp;
    }



}
