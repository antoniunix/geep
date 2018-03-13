package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoCatalog;
import net.gshp.gepp.dto.DtoSubordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 9/03/18.
 */

public class DaoSubordinate extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME="subordinate";
    public static String PK_FIELD="id";

    private final String id="id";
    private final String apellido_pat="apellido_pat";
    private final String apellido_mat="apellido_mat";
    private final String nombre="nombre";
    private final String puesto="puesto";
    private final String report_asignados="report_asignados";
    private final String report_realizado="report_realizado";
    private final String lat="lat";
    private final String lon="lon";
    public DaoSubordinate() {
        super(TABLE_NAME, PK_FIELD);
        // TODO Auto-generated constructor stub
    }

    /**
     * Insert
     */
    public int Insert( List<DtoSubordinate> obj) {
        db=helper.getWritableDatabase();
        int resp=0;
        try {
            SQLiteStatement insStmnt=db.compileStatement("" +
                    "INSERT INTO " +
                    TABLE_NAME+
                    " ("+id+","+apellido_pat+","+apellido_mat+","+nombre+","+puesto+","+report_asignados+","+report_realizado+","+lat+","+lon+")" +
                    " VALUES(?,?,?,?,?,?,?,?,?);");
            db.beginTransaction();
            for (DtoSubordinate dtoCatalog : obj) {
                try {
                    insStmnt.bindLong(1, dtoCatalog.getIdusuario());
                } catch (Exception e) {
                    insStmnt.bindNull(1);
                }
                try {
                    insStmnt.bindString(2, dtoCatalog.getApellidoPat());
                } catch (Exception e) {
                    insStmnt.bindNull(2);
                }
                try {
                    insStmnt.bindString(3, dtoCatalog.getApellidoMat());
                } catch (Exception e) {
                    insStmnt.bindNull(3);
                }
                try {
                    insStmnt.bindString(4, dtoCatalog.getNombre());
                } catch (Exception e) {
                    insStmnt.bindNull(4);
                }
                try {
                    insStmnt.bindString(5, dtoCatalog.getPuesto());
                } catch (Exception e) {
                    insStmnt.bindNull(5);
                }
                try {
                    insStmnt.bindLong(6, dtoCatalog.getReportesAsignados());
                } catch (Exception e) {
                    insStmnt.bindNull(6);
                }
                try {
                    insStmnt.bindLong(7, dtoCatalog.getReportesRealizados());
                } catch (Exception e) {
                    insStmnt.bindNull(7);
                }
                try {
                    insStmnt.bindDouble(8, dtoCatalog.getLat());
                } catch (Exception e) {
                    insStmnt.bindNull(8);
                }
                try {
                    insStmnt.bindDouble(9, dtoCatalog.getLon());
                } catch (Exception e) {
                    insStmnt.bindNull(9);
                }
                insStmnt.executeInsert();
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error db");
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }
        db.close();
        return resp;
    }
    /**
     * Select
     */
    public List<DtoSubordinate> Select()
    {
        db=helper.getReadableDatabase();
        cursor=db.rawQuery("SELECT\n" +
                "subordinate.id,\n" +
                "subordinate.apellido_pat,\n" +
                "subordinate.apellido_mat,\n" +
                "subordinate.nombre,\n" +
                "subordinate.puesto,\n" +
                "subordinate.report_asignados,\n" +
                "subordinate.report_realizado,\n" +
                "subordinate.lat,\n" +
                "subordinate.lon,\n" +
                "round((subordinate.report_realizado*100.0)/subordinate.report_asignados ,1) as promedio\n" +
                "FROM\n" +
                "subordinate\n" +
                "ORDER BY promedio DESC",null);
        List<DtoSubordinate> obj=new ArrayList<>();
        DtoSubordinate catalogo;
        if(cursor.moveToFirst())
        {
            int id=cursor.getColumnIndexOrThrow("id");
            int apellido_pat=cursor.getColumnIndexOrThrow("apellido_pat");
            int apellido_mat=cursor.getColumnIndexOrThrow("apellido_mat");
            int nombre=cursor.getColumnIndexOrThrow("nombre");
            int puesto=cursor.getColumnIndexOrThrow("puesto");
            int report_asignados=cursor.getColumnIndexOrThrow("report_asignados");
            int report_realizado=cursor.getColumnIndexOrThrow("report_realizado");
            int lat=cursor.getColumnIndexOrThrow("lat");
            int lon=cursor.getColumnIndexOrThrow("lon");
            int promedio=cursor.getColumnIndexOrThrow("promedio");
            do
            {
                catalogo=new DtoSubordinate();
                catalogo.setIdusuario(cursor.getInt(id));
                catalogo.setApellidoPat(cursor.getString(apellido_pat));
                catalogo.setApellidoMat(cursor.getString(apellido_mat));
                catalogo.setNombre(cursor.getString(nombre));
                catalogo.setPuesto(cursor.getString(puesto));
                catalogo.setReportesAsignados(cursor.getInt(report_asignados));
                catalogo.setReportesRealizados(cursor.getInt(report_realizado));
                catalogo.setLat(cursor.getDouble(lat));
                catalogo.setLon(cursor.getDouble(lon));
                catalogo.setPromedio(cursor.getFloat(promedio));
                obj.add(catalogo);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public List<DtoCatalog> selectUserSchedule() {
        db = helper.getReadableDatabase();
        String qry = "SELECT DISTINCT\n" +
                "agenda.id_user,\n" +
                "CASE WHEN subordinate.id ISNULL THEN 'Mi ruta' ELSE  subordinate.nombre||' '||subordinate.apellido_pat   end as name \n" +
                "FROM agenda\n" +
                "LEFT JOIN subordinate on subordinate.id=agenda.id_user\n" +
                "INNER JOIN pdv ON pdv.id=agenda.id_place ";
        cursor = db.rawQuery(qry, null);
        List<DtoCatalog> obj = new ArrayList<DtoCatalog>();
        DtoCatalog catalogo;
        if (cursor.moveToFirst()) {

            int id=cursor.getColumnIndexOrThrow("id_user");
            int value=cursor.getColumnIndexOrThrow("name");
            do {
                catalogo = new DtoCatalog();
                catalogo.setId(cursor.getInt(id));
                catalogo.setValue(cursor.getString(value));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    public String  getSelectNameUser(int id) {
        db=helper.getReadableDatabase();
        String name="";
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "
                + PK_FIELD + " = " + id, null);
        if (cursor.moveToFirst()) {
            name = cursor.getString(1) +" " +cursor.getString(2) +" "+cursor.getString(3);
        }
        cursor.close();
        db.close();
        return name.replace("null ", "");
    }

    public String  getSelectNamePDV(int id) {
        db=helper.getReadableDatabase();
        String name="";
        cursor = db.rawQuery("SELECT * FROM pdv WHERE id = " + id, null);
        if (cursor.moveToFirst()) {
            name = cursor.getString(1) +" " +cursor.getString(2) +" "+cursor.getString(3);
        }
        cursor.close();
        db.close();
        return name.replace("null ", "");
    }

}
