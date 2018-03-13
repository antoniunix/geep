package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoMessage extends DAO {

    private static final String TABLE_NAME = "message";
    private static final String PK_FIELD = "id";

    private final String ID = "id";
    private final String ID_GROUP = "id_group";
    private final String TYPE = "type";
    private final String TITLE= "title";
    private final String DESCRIPTION_SMALL = "descripcion";
    private final String CONT_HTML = "content";
    private final String END_DATE = "end_date";
    private final String VIEWED = "viewed";

    private SQLiteDatabase db;
    private Cursor cursor;

    public DaoMessage() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Método que inserta los datos sobre la tabla, proviene de una lista de
     * dtos
     */
    public int Insert(List<DtoMessage> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        String qry = "INSERT INTO message (" + ID + ", "
                +TYPE+ ","+TITLE+ ","+DESCRIPTION_SMALL+ ","+CONT_HTML+","+END_DATE+","+VIEWED+") VALUES (?,?,?,?,?,?,?);";
        try {
            SQLiteStatement insStmnt = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoMessage dto : obj) {
                try {
                    insStmnt.bindLong(1, dto.getId());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }

                try {
                    insStmnt.bindLong(2, dto.getType_id());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, dto.getTitle());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, dto.getDescription());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindString(5, dto.getContent());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
                }
                try {
                    insStmnt.bindString(6, dto.getEndDate());
                } catch (Exception e) {
                    insStmnt.bindNull(6);
                }
                try {
                    insStmnt.bindLong(7, 0);
                } catch (Exception e) {
                    insStmnt.bindNull(7);
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
    public List<DtoMessage> Select() {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT \n" +
                "				message.id, \n" +
                "				message.type, \n" +
                "				message.title, \n" +
                "				message.descripcion, \n" +
                "				message.content, \n" +
                "				message.end_date, \n" +
                "				CASE WHEN message_view.id ISNULL \n" +
                "				       THEN 0 \n" +
                "				       ELSE 1 \n" +
                "				       END AS done \n" +
                "				FROM \n" +
                "				message \n" +
                "				LEFT JOIN message_view ON message_view.id_message=message.id \n" +
                "				WHERE \n" +
                "				message.end_date>=date('now')\n" +
                "ORDER BY message_view.id ISNULL  DESC, message.end_date ASC", null);
        List<DtoMessage> obj = new ArrayList<DtoMessage>();
        DtoMessage catalogo;
        if (cursor.moveToFirst()) {
            do {
                catalogo = new DtoMessage();
                catalogo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                catalogo.setType_id(cursor.getInt(cursor.getColumnIndexOrThrow(TYPE)));
                catalogo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                catalogo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_SMALL)));
                catalogo.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONT_HTML)));
                catalogo.setEndDate(cursor.getString(cursor.getColumnIndexOrThrow("end_date")));
                catalogo.setDone(cursor.getInt(cursor.getColumnIndexOrThrow("done")));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public DtoMessage SelectById(int id) {
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT\n" +
                "message.id,\n" +
                "message.type,\n" +
                "message.title,\n" +
                "message.descripcion,\n" +
                "message.content,\n" +
                "CASE WHEN message_view.id ISNULL\n" +
                "       THEN 0\n" +
                "       ELSE 1\n" +
                "       END AS done\n" +
                "FROM\n" +
                "message\n" +
                "LEFT JOIN message_view ON message_view.id_message=message.id\n" +
                "WHERE\n" +
                "message.id="+id, null);
        DtoMessage catalogo= new DtoMessage();
        if (cursor.moveToFirst()) {
            catalogo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
            catalogo.setType_id(cursor.getInt(cursor.getColumnIndexOrThrow(TYPE)));
            catalogo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
            catalogo.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_SMALL)));
            catalogo.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONT_HTML)));
            catalogo.setDone(cursor.getInt(cursor.getColumnIndexOrThrow("done")));
        }
        cursor.close();
        db.close();
        return catalogo;
    }



}
