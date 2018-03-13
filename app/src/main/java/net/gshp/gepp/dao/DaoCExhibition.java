package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoCExhibition;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoCExhibition extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "c_exhibition";
    public static String PK_FIELD = "id_type_exhibition";


    private final String EXHIBITION_NAME = "exhibition_name";
    private final String ID_TYPE_EXHIBITION = "id_type_exhibition";
    private final String ID_PDV = "id_pdv";
    private final String MIN_PHOTOS = "min_photos";
    private final String MAX_PHOTOS = "max_photos";
    private final String END_DATE = "end_date";
    private final String HASH = "hash";
    private final String ID_EXHIBITION_GROUP = "id_exhibition_group";
    private final String MANUFACTURER = "manufacturer";
    private final String CATEGORY = "category";
    private final String FAMILY = "family";
    private final String SUB_FAMILY = "sub_famlly";
    private final String TYPEEXHIBITION = "typeExhibition";
    private final String GROUP = "grupo";
    private final String IDLOCATION = "id_location";
    private final String LOCATION = "location";
    private final String IDDEPARTASMENT = "id_departament";
    private final String DEPARTAMENT = "department";

    public DaoCExhibition() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoCExhibition> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + EXHIBITION_NAME + "," + ID_TYPE_EXHIBITION + ","
                    + ID_PDV + "," + MIN_PHOTOS + "," + MAX_PHOTOS + "," + END_DATE + "," + HASH + ","
                    + ID_EXHIBITION_GROUP + "," + MANUFACTURER + "," + CATEGORY + "," + FAMILY + "," + SUB_FAMILY + "," + LOCATION + "," + TYPEEXHIBITION
                    + "," + GROUP + "," + DEPARTAMENT + "," + IDDEPARTASMENT + "," + IDLOCATION + ") " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            db.beginTransaction();
            for (DtoCExhibition DtoCatalog : obj) {
                try {
                    insStmnt.bindString(1, DtoCatalog.getExhibitionName());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindLong(2, DtoCatalog.getIdTypeExhibition());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindLong(3, DtoCatalog.getIdPdv());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindLong(4, DtoCatalog.getMinPhotos());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindLong(5, DtoCatalog.getMaxPhotos());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
                }
                try {
                    insStmnt.bindString(6, DtoCatalog.getEndDate());
                } catch (Exception e) {
                    insStmnt.bindNull(6);
                }
                try {
                    insStmnt.bindString(7, DtoCatalog.getHash());
                } catch (Exception e) {
                    insStmnt.bindNull(7);
                }
                try {
                    insStmnt.bindLong(8, DtoCatalog.getIdGroup());
                } catch (Exception e) {
                    insStmnt.bindNull(8);
                }
                try {
                    insStmnt.bindString(9, DtoCatalog.getManufacturer());
                } catch (Exception e) {
                    insStmnt.bindNull(9);
                }
                try {
                    insStmnt.bindString(10, DtoCatalog.getCategory());
                } catch (Exception e) {
                    insStmnt.bindNull(10);
                }
                try {
                    insStmnt.bindString(11, DtoCatalog.getFamily());
                } catch (Exception e) {
                    insStmnt.bindNull(11);
                }
                try {
                    insStmnt.bindString(12, DtoCatalog.getSubfamily());
                } catch (Exception e) {
                    insStmnt.bindNull(12);
                }
                try {
                    insStmnt.bindString(13, DtoCatalog.getLocation());
                } catch (Exception e) {
                    insStmnt.bindNull(13);
                }
                try {
                    insStmnt.bindString(14, DtoCatalog.getTypeExhibition());
                } catch (Exception e) {
                    insStmnt.bindNull(14);
                }
                try {
                    insStmnt.bindString(15, DtoCatalog.getGroup());
                } catch (Exception e) {
                    insStmnt.bindNull(15);
                }
                try {
                    insStmnt.bindString(16, DtoCatalog.getDepartment());
                } catch (Exception e) {
                    insStmnt.bindNull(16);
                }
                try {
                    insStmnt.bindLong(17, DtoCatalog.getIdDepartment());
                } catch (Exception e) {
                    insStmnt.bindNull(17);
                }
                try {
                    insStmnt.bindLong(18, DtoCatalog.getIdLocation());
                } catch (Exception e) {
                    insStmnt.bindNull(18);
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


}
