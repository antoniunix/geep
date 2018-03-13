package net.gshp.gepp.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import net.gshp.gepp.dto.DtoPolitc;

/**
 * Created by leo on 8/03/18.
 */

public class DaoPolitic extends DAO {

    private SQLiteDatabase db;

    public static String PK_FIELD="version";
    public static String TABLE_NAME="politics";
    private final String version="version";
    private final String politic="politic";

    /**
     *
     * @param_TABLE_NAME es el nombre de la tabla sobre la que se trabajara
     */
    public DaoPolitic() {
        super(TABLE_NAME, PK_FIELD);
    }
    /**
     * Insert
     */
    public int Insert(DtoPolitc dto)
    {
        db=helper.getWritableDatabase();
        int resp=0;
        try {

            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+" ("+version+","+politic+") VALUES(?,?);");
            db.beginTransaction();
            try {
                insStmnt.bindString(1, dto.getVersion());
            } catch (Exception e) {
                insStmnt.bindNull(1);
            }
            try {
                insStmnt.bindString(2, dto.getValue());
            } catch (Exception e) {
                insStmnt.bindNull(2);
            }
            insStmnt.executeInsert();
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
        return resp;
    }

    /**
     * Select
     */
    public DtoPolitc Select() {
        Log.e("intent","politic");
        db = helper.getReadableDatabase();
        String query="SELECT\n" +
                "politics.version,\n" +
                "politics.politic\n" +
                "FROM\n" +
                "politics\n" ;
        cursor = db.rawQuery(query, null);
        DtoPolitc dto=new DtoPolitc();
        if (cursor.moveToFirst()) {
            dto.setVersion(cursor.getString(cursor.getColumnIndexOrThrow("version")));
            dto.setValue(cursor.getString(cursor.getColumnIndexOrThrow("politic")));
        }
        cursor.close();
        db.close();
        return dto;
    }


}
