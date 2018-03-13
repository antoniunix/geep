package net.gshp.gepp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.api_time_module.config.MD5;
import net.gshp.gepp.dto.DtoBundle;
import net.gshp.gepp.dto.DtoReportScannAlert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 12/03/18.
 */

public class DaoReportScannAlert extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;

    public static String TABLE_NAME = "report_scan_alert";
    public static String PK_FIELD = "id_report";


    private final String ID = "id";
    private final String ID_REPORT = "id_report";
    private final String ID_SKU = "id_sku";
    private final String KEY = "key";
    private final String PDV = "pdv";
    private final String STATUS = "status";
    private final String HASH = "hash";
    private final String SEND = "send";
    private final String ID_TP = "id_tp";

    public DaoReportScannAlert() {
        super(TABLE_NAME, PK_FIELD);
    }

    /**
     * Insert
     */
    public int Insert(List<DtoReportScannAlert> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;
        try {
            String qry = "INSERT INTO " + TABLE_NAME + " (" + ID_REPORT + "," + ID_SKU + "," + KEY + "," + PDV + "," + STATUS + "," + HASH + "," + SEND + "," + ID_TP + ")"
                    + "VALUES(?,?,?,?,?,?,?,?);";
            SQLiteStatement insStatement = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoReportScannAlert dto : obj) {

                if (dto.getIdTp() > 0) {
                    try {
                        insStatement.bindLong(1, dto.getIdReport());
                    } catch (Exception e) {
                        insStatement.bindNull(1);
                    }
                    try {
                        insStatement.bindLong(2, dto.getIdSku());
                    } catch (Exception e) {
                        insStatement.bindNull(2);
                    }
                    try {
                        insStatement.bindString(3, dto.getKey());
                    } catch (Exception e) {
                        insStatement.bindNull(3);
                    }
                    try {
                        insStatement.bindLong(4, dto.getPdv());
                    } catch (Exception e) {
                        insStatement.bindNull(4);
                    }
                    try {
                        insStatement.bindLong(5, dto.getStatus());
                    } catch (Exception e) {
                        insStatement.bindNull(5);
                    }
                    try {
                        insStatement.bindString(6, MD5.md5(System.currentTimeMillis() + " " + dto.getKey()));
                    } catch (Exception e) {
                        insStatement.bindNull(6);
                    }
                    try {
                        insStatement.bindLong(7, dto.isSend() ? 1 : 0);
                    } catch (Exception e) {
                        insStatement.bindNull(7);
                    }
                    try {

                        insStatement.bindLong(8, dto.getIdTp());
                    } catch (Exception e) {
                        insStatement.bindNull(8);
                    }
                    insStatement.executeInsert();

                }

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


    /**
     * Select
     */
    public List<DtoReportScannAlert> SelectScannAlert(DtoBundle dtoBundle) {
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT\n" +
                        "scann_alert.id,\n" +
                        "scann_alert.id_pdv,\n" +
                        "scann_alert.id_sku,\n" +
                        "scann_alert.id_tp,\n" +
                        "report_scan_alert.id_report,\n" +
                        "scann_alert.\"key\",\n" +
                        "report_scan_alert.send,\n" +
                        "report_scan_alert.status,\n" +
                        "report_scan_alert.hash,\n" +
                        "c_status_scann_alert.color,\n" +
                        "scann_alert.sku_description AS value,\n" +
                        "scann_alert.promedioVtaSemanal,\n" +
                        "scann_alert.ventaSemanaActual,\n" +
                        "scann_alert.ventaSemanaAnterior,\n" +
                        "scann_alert.invUnidadesSemanaActual,\n" +
                        "scann_alert.diasInv\n" +
                        "from scann_alert  \n" +
                        "LEFT JOIN report_scan_alert on report_scan_alert.id_sku=scann_alert.id_sku and report_scan_alert.id_tp=scann_alert.id_tp and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal() + "\n" +
                        "LEFT JOIN c_status_scann_alert  on c_status_scann_alert.id=report_scan_alert.status\n" +
                        "WHERE scann_alert.id_pdv= " + dtoBundle.getIdPDV() + "\n" +
                        "ORDER BY scann_alert.id_tp ASC", null);

        List<DtoReportScannAlert> obj = new ArrayList<DtoReportScannAlert>();
        DtoReportScannAlert catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow("id");
            int id_pdv = cursor.getColumnIndexOrThrow("id_pdv");
            int id_sku = cursor.getColumnIndexOrThrow("id_sku");
            int id_tp = cursor.getColumnIndexOrThrow("id_tp");
            int id_report = cursor.getColumnIndexOrThrow("id_report");
            int key = cursor.getColumnIndexOrThrow("key");
            int send = cursor.getColumnIndexOrThrow("send");
            int status = cursor.getColumnIndexOrThrow("status");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int sku = cursor.getColumnIndexOrThrow("value");
            int color = cursor.getColumnIndexOrThrow("color");
            int promedioVtaSemanal = cursor.getColumnIndexOrThrow("promedioVtaSemanal");
            int ventaSemanaActual = cursor.getColumnIndexOrThrow("ventaSemanaActual");
            int ventaSemanaAnterior = cursor.getColumnIndexOrThrow("ventaSemanaAnterior");
            int invUnidadesSemanaActual = cursor.getColumnIndexOrThrow("invUnidadesSemanaActual");
            int diasInv = cursor.getColumnIndexOrThrow("diasInv");

            do {
                catalogo = new DtoReportScannAlert();
                catalogo.setId(cursor.getInt(id));
                catalogo.setPdv(cursor.getInt(id_pdv));
                catalogo.setIdSku(cursor.getInt(id_sku));
                catalogo.setIdReport(dtoBundle.getIdReportLocal());
                catalogo.setKey(cursor.getString(key));
                catalogo.setSend(cursor.getInt(send) == 1);
                catalogo.setStatus(cursor.getInt(status));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setSku(cursor.getString(sku));
                catalogo.setColor(cursor.getString(color));
                catalogo.setIdTp(cursor.getInt(id_tp));
                catalogo.setPromedioVtaSemanal(cursor.getString(promedioVtaSemanal));
                catalogo.setVentaSemanaActual(cursor.getString(ventaSemanaActual));
                catalogo.setVentaSemanaAnterior(cursor.getString(ventaSemanaAnterior));
                catalogo.setInvUnidadesSemanaActual(cursor.getString(invUnidadesSemanaActual));
                catalogo.setDiasInv(cursor.getString(diasInv));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    /**
     * Select
     */
    public List<DtoReportScannAlert> SelectScannAlertType1(DtoBundle dtoBundle) {
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT scann_alert.id,scann_alert.id_pdv,scann_alert.id_sku,scann_alert.id_tp, \n" +
                        "report_scan_alert.id_report,scann_alert.\"key\",report_scan_alert.send,\n" +
                        "report_scan_alert.status,report_scan_alert.hash ,scann_alert.sku_description as value \n" +
                        "from scann_alert \n" +
                        "LEFT JOIN report_scan_alert on report_scan_alert.id_sku=scann_alert.id_sku and report_scan_alert.id_tp=scann_alert.id_tp and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal() + "\n" +
                        "WHERE scann_alert.id_pdv= " + dtoBundle.getIdPDV() + "   \n" +
                        "and scann_alert.id_tp=1", null);

        List<DtoReportScannAlert> obj = new ArrayList<DtoReportScannAlert>();
        DtoReportScannAlert catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow("id");
            int id_pdv = cursor.getColumnIndexOrThrow("id_pdv");
            int id_sku = cursor.getColumnIndexOrThrow("id_sku");
            int id_tp = cursor.getColumnIndexOrThrow("id_tp");
            int id_report = cursor.getColumnIndexOrThrow("id_report");
            int key = cursor.getColumnIndexOrThrow("key");
            int send = cursor.getColumnIndexOrThrow("send");
            int status = cursor.getColumnIndexOrThrow("status");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int sku = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoReportScannAlert();
                catalogo.setId(cursor.getInt(id));
                catalogo.setPdv(cursor.getInt(id_pdv));
                catalogo.setIdSku(cursor.getInt(id_sku));
                catalogo.setIdReport(cursor.getInt(id_report));
                catalogo.setKey(cursor.getString(key));
                catalogo.setSend(cursor.getInt(send) == 1);
                catalogo.setStatus(cursor.getInt(status));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setSku(cursor.getString(sku));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return obj;
    }

    /**
     * Select
     */
    public List<DtoReportScannAlert> SelectScannAlertType2(DtoBundle dtoBundle) {
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT scann_alert.id,scann_alert.id_pdv,scann_alert.id_sku,scann_alert.id_tp, \n" +
                        "report_scan_alert.id_report,scann_alert.\"key\",report_scan_alert.send,\n" +
                        "report_scan_alert.status,report_scan_alert.hash ,scann_alert.sku_description as value \n" +
                        "from scann_alert \n" +
                        "LEFT JOIN report_scan_alert on report_scan_alert.id_sku=scann_alert.id_sku and report_scan_alert.id_tp=scann_alert.id_tp and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal() + "\n" +
                        "WHERE scann_alert.id_pdv= " + dtoBundle.getIdPDV() + "   \n" +
                        "and scann_alert.id_tp=2", null);

        List<DtoReportScannAlert> obj = new ArrayList<DtoReportScannAlert>();
        DtoReportScannAlert catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow("id");
            int id_pdv = cursor.getColumnIndexOrThrow("id_pdv");
            int id_sku = cursor.getColumnIndexOrThrow("id_sku");
            int id_tp = cursor.getColumnIndexOrThrow("id_tp");
            int id_report = cursor.getColumnIndexOrThrow("id_report");
            int key = cursor.getColumnIndexOrThrow("key");
            int send = cursor.getColumnIndexOrThrow("send");
            int status = cursor.getColumnIndexOrThrow("status");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int sku = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoReportScannAlert();
                catalogo.setId(cursor.getInt(id));
                catalogo.setPdv(cursor.getInt(id_pdv));
                catalogo.setIdSku(cursor.getInt(id_sku));
                catalogo.setIdReport(cursor.getInt(id_report));
                catalogo.setKey(cursor.getString(key));
                catalogo.setSend(cursor.getInt(send) == 1);
                catalogo.setStatus(cursor.getInt(status));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setSku(cursor.getString(sku));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    /**
     * Select
     */
    public List<DtoReportScannAlert> SelectScannAlertType3(DtoBundle dtoBundle) {
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT scann_alert.id,scann_alert.id_pdv,scann_alert.id_sku,scann_alert.id_tp, \n" +
                        "report_scan_alert.id_report,scann_alert.\"key\",report_scan_alert.send,\n" +
                        "report_scan_alert.status,report_scan_alert.hash ,scann_alert.sku_description as value \n" +
                        "from scann_alert \n" +
                        "LEFT JOIN report_scan_alert on report_scan_alert.id_sku=scann_alert.id_sku and report_scan_alert.id_tp=scann_alert.id_tp and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal() + "\n" +
                        "WHERE scann_alert.id_pdv= " + dtoBundle.getIdPDV() + "   \n" +
                        "and scann_alert.id_tp=3", null);

        List<DtoReportScannAlert> obj = new ArrayList<DtoReportScannAlert>();
        DtoReportScannAlert catalogo;
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndexOrThrow("id");
            int id_pdv = cursor.getColumnIndexOrThrow("id_pdv");
            int id_sku = cursor.getColumnIndexOrThrow("id_sku");
            int id_tp = cursor.getColumnIndexOrThrow("id_tp");
            int id_report = cursor.getColumnIndexOrThrow("id_report");
            int key = cursor.getColumnIndexOrThrow("key");
            int send = cursor.getColumnIndexOrThrow("send");
            int status = cursor.getColumnIndexOrThrow("status");
            int hash = cursor.getColumnIndexOrThrow("hash");
            int sku = cursor.getColumnIndexOrThrow("value");

            do {
                catalogo = new DtoReportScannAlert();
                catalogo.setId(cursor.getInt(id));
                catalogo.setPdv(cursor.getInt(id_pdv));
                catalogo.setIdSku(cursor.getInt(id_sku));
                catalogo.setIdReport(cursor.getInt(id_report));
                catalogo.setKey(cursor.getString(key));
                catalogo.setSend(cursor.getInt(send) == 1);
                catalogo.setStatus(cursor.getInt(status));
                catalogo.setHash(cursor.getString(hash));
                catalogo.setSku(cursor.getString(sku));
                obj.add(catalogo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return obj;
    }

    /**
     * Select
     */
    public List<List<DtoReportScannAlert>> SelecttoSend() {
        db = helper.getReadableDatabase();

//		"SELECT\n" +
//		"report.id_report_server,\n" +
//		"report_scan_alert.id,\n" +
//		"report_scan_alert.id_report,\n" +
//		"report_scan_alert.id_sku,\n" +
//		"INNER JOIN report ON report.id = report_scan_alert.id_report  and report.id_report_server>0\n" +
//		"WHERE report_scan_alert.send=0\n" +
//		"ORDER BY  report_scan_alert.id_report ASC"
        cursor = db.rawQuery("SELECT DISTINCT\n" +
                "report.id_report_server,\n" +
                "report_scan_alert.id,\n" +
                "report_scan_alert.id_report,\n" +
                "report_scan_alert.id_sku,\n" +
                "report_scan_alert.status,\n" +
                "report_scan_alert.hash,\n" +
                "report_scan_alert.pdv,\n" +
                "report_scan_alert.\"key\",\n" +
                "report_scan_alert.send,\n" +
                "report_scan_alert.id_tp\n" +
                "FROM\n" +
                "report\n" +
                "INNER JOIN report_scan_alert ON report.id = report_scan_alert.id_report and report.id_report_server>0\n" +
                "WHERE report_scan_alert.send=0\n" +
                "ORDER BY  report_scan_alert.id_report ASC", null);
        List<List<DtoReportScannAlert>> lst = new ArrayList<List<DtoReportScannAlert>>();
        List<DtoReportScannAlert> subLst = new ArrayList<DtoReportScannAlert>();
        DtoReportScannAlert catalogo;
        int tmpidReport;//variable para guardar el idreporte del cursor anterior
        if (cursor.moveToFirst()) {
            //guardamos el idReporte del primer row
            tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report"));

            do {
                catalogo = new DtoReportScannAlert();
                catalogo.setIdReport(cursor.getInt(cursor.getColumnIndexOrThrow("id_report_server")));
                catalogo.setIdReportLocal(cursor.getInt(cursor.getColumnIndexOrThrow("id_report")));
                catalogo.setIdSku(cursor.getInt(cursor.getColumnIndexOrThrow("id_sku")));
                catalogo.setKey(cursor.getString(cursor.getColumnIndexOrThrow("key")));
                catalogo.setPdv(cursor.getInt(cursor.getColumnIndexOrThrow("pdv")));
                catalogo.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow("status")));
                catalogo.setHash(cursor.getString(cursor.getColumnIndexOrThrow("hash")));
                catalogo.setSend(cursor.getInt(cursor.getColumnIndexOrThrow("send")) == 1);
                // si pertenece al mismo reporte se guardaran en la misma sublista
                if (tmpidReport == cursor.getInt(cursor.getColumnIndexOrThrow("id_report")))
                    subLst.add(catalogo);
                else if (tmpidReport != cursor.getInt(cursor.getColumnIndexOrThrow("id_report")))// si cambia el idReporte guardamos la sublista en la lista y creamos otra sublista
                {
                    //refrescamos el idReporte al actual id
                    tmpidReport = cursor.getInt(cursor.getColumnIndexOrThrow("id_report"));
                    lst.add(subLst);
                    subLst = new ArrayList<DtoReportScannAlert>();
                    subLst.add(catalogo);
                }
                if (cursor.isLast())
                    lst.add(subLst);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lst;
    }

    /**
     * UPDATE
     */
    public void Update(String id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("send", 1);
        db.update(TABLE_NAME, cv, "id_report=" + id, null);
        db.close();
    }


    /**
     * Select
     */
    public int countItemsRespond(DtoBundle dtoBundle) {
        int items = 0;
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT\n" +
                                "count(*) as count\n" +
                                "FROM\n" +
                                "report_scan_alert\n" +
                                "WHERE report_scan_alert.status<>-1 and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal(),
                        null);

        if (cursor.moveToFirst()) {
            items = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return items;
    }

    public int countItemsRespondIncomplete(DtoBundle dtoBundle) {
        int items = 0;
        db = helper.getReadableDatabase();
        cursor = db
                .rawQuery("SELECT\n" +
                                "count(*) as count\n" +
                                "FROM\n" +
                                "report_scan_alert\n" +
                                "WHERE report_scan_alert.status=-1 and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal(),
                        null);

        if (cursor.moveToFirst()) {
            items = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
        }
        cursor.close();
        db.close();
        return items;
    }

    /**
     * Método que revisa si todos los campos se han llenado correctamente
     *
     * @return TRUE si el reporte se llenó correctamente, FALSE si el reporte no
     * se ha completado.
     */
    public boolean isReportComplete(DtoBundle dtoBundle) {
        db = helper.getReadableDatabase();
        boolean isReport = false;
        cursor = db.rawQuery("SELECT\n" +
                "count(report_scan_alert.id) as count\n" +
                "FROM\n" +
                "report_scan_alert\n" +
                "WHERE  report_scan_alert.status=-1 and report_scan_alert.id_report=" + dtoBundle.getIdReportLocal(), null);
        if (cursor.moveToFirst()) {
            isReport = cursor.getInt(cursor.getColumnIndexOrThrow("count")) == 0;
        }
        cursor.close();
        db.close();
        return isReport;
    }

}
