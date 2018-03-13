package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoCTypeCatalogExhibition;

import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoCTypeCatalogExhibition extends DAO{
    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "c_type_catalog_exhibition";
    public static String PK_FIELD = "id";

    private final String ID = "id";
    private final String VALUE = "value";
    private final String POSITION = "position";
    private final String FIELD_NAME = "field_name";
    private final String IDCATEGORY="idCategory";

    public DaoCTypeCatalogExhibition() {
        super(TABLE_NAME, PK_FIELD);
    }
    /**
     * Insert
     */
    public int Insert(List<DtoCTypeCatalogExhibition> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            SQLiteStatement insStmnt = db.compileStatement("" + "INSERT INTO "
                    + TABLE_NAME + " (" + ID + "," + VALUE +","
                    +POSITION+","+FIELD_NAME+","+IDCATEGORY+") " +
                    "VALUES(?,?,?,?,?);");
            db.beginTransaction();
            for (DtoCTypeCatalogExhibition DtoCatalog : obj) {
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
                    insStmnt.bindLong(3, DtoCatalog.getPosition());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, DtoCatalog.getFieldName());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindLong(5, DtoCatalog.getIdCategory());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
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
