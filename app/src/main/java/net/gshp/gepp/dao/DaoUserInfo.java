package net.gshp.gepp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.gshp.gepp.dto.DtoUserInfo;

import java.util.List;

/**
 * Created by leo on 19/03/18.
 */

public class DaoUserInfo extends DAO {

    private SQLiteDatabase db;
    private Cursor cursor;


    public static String TABLE_NAME = "userInfo";
    public static String PK_FIELD = "id";
    public DaoUserInfo() {
        super(TABLE_NAME, PK_FIELD);
    }


    private String NUSEMANA = "numero_semana";
    private String PORCENTAJESEMANA = "porcentaje_semana";
    private String FOTOEXITO = "foto_exito";
    private String EFECTIVIDADPORCENTAJE = "efectividad_porcentaje_semana";
    private String EFECTIVIDADBANDERA = "efectividad_porcentaje_semana_bandera";
    private String EFECTIVIDADANUAL = "efectividad_acumulado_anual";
    private String COSTOINASISTENCIA = "costo_inasistencia";
    private String SUPERIORTIENDA = "superior_tiendas";
    private String SUPERIORPORCENTAJE = "superior_porcentaje";
    private String NORMALTIENDA = "normal_tiendas";
    private String NORMALPORCENTAJE = "normal_porcentaje";
    private String BASICASTIENDAS = "basica_tiendas";
    private String BASICAPORCENTAJE = "basica_porcentaje";
    private String CRITICATIENDAS = "critica_tiendas";
    private String CRITICAPORCENTAJE = "critica_porcentaje";
    private String SINMEDICION = "sinmedicion_tienda";
    private String SINMEDICIONPORCENTAJE = "sinmedicion_porcentaje";
    private String COLAS = "colas";
    private String COLASCOLOR = "colas_color";
    private String SABORES = "sabores";
    private String SABORESCOLOR = "sabores_color";
    private String AGUA = "agua";
    private String AGUACOLOR = "agua_color";
    private String GATORADE = "gatorade";
    private String GATORADECOLOR = "gatorade_color";
    private String LIPTON = "lipton";
    private String LIPTONCOLOR = "lipton_color";
    private String JUMEX = "jumex";
    private String JUMEXCOLOR = "jumex_color";
    private String STARTBUCK = "starbucks";
    private String STARTBUCKCOLOR = "starbucks_color";
    private String MIXER = "mixers";
    private String MIXERCOLOR = "mixers_color";


    public int Insert(List<DtoUserInfo> obj) {
        db = helper.getWritableDatabase();
        int resp = 0;

        try {
            String qry = "INSERT INTO " + TABLE_NAME + " (" + NUSEMANA + "," + PORCENTAJESEMANA + ","
                    + FOTOEXITO + "," + EFECTIVIDADPORCENTAJE + "," + EFECTIVIDADBANDERA + "," +
                    EFECTIVIDADANUAL + "," + COSTOINASISTENCIA + ","
                    + SUPERIORTIENDA + "," + SUPERIORPORCENTAJE + "," +
                    NORMALTIENDA + "," + NORMALPORCENTAJE + "," +
                    BASICASTIENDAS + "," + BASICAPORCENTAJE + "," +
                    CRITICATIENDAS + "," + CRITICAPORCENTAJE + "," +
                    SINMEDICION + "," + SINMEDICIONPORCENTAJE + "," +
                    COLAS + "," + COLASCOLOR + "," + SABORES + "," +
                    SABORESCOLOR + "," + AGUA + "," + AGUACOLOR + "," + GATORADE + "," + GATORADECOLOR + "," + LIPTON + "," + LIPTONCOLOR + ","
                    + JUMEX + "," + JUMEXCOLOR + "," + STARTBUCK + "," + STARTBUCKCOLOR + "," +
                    MIXER + "," + MIXERCOLOR + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";;
            SQLiteStatement insStatement = db.compileStatement(qry);
            db.beginTransaction();
            for (DtoUserInfo dto : obj) {
                try {
                    insStatement.bindString(1, dto.getNumero_semana());
                } catch (Exception e) {
                    insStatement.bindNull(1);
                }
                try {
                    insStatement.bindString(2, dto.getPorcentaje_semana());
                } catch (Exception e) {
                    insStatement.bindNull(2);
                }
                try {
                    insStatement.bindString(3, dto.getFoto_exito());
                }catch (Exception e){
                    insStatement.bindNull(3);
                }
                try {
                    insStatement.bindString(4,dto.getEfectividad_porcentaje_semana());
                }catch (Exception e){
                    insStatement.bindNull(4);
                }
                try {
                    insStatement.bindString(5, dto.getEfectividad_porcentaje_semana_bandera());
                }catch (Exception e){
                    insStatement.bindNull(5);
                }
                try {
                    insStatement.bindString(6, dto.getEfectividad_acumulado_anual());
                }catch (Exception e){
                    insStatement.bindNull(6);
                }
                try {
                    insStatement.bindString(7,dto.getCosto_inasistencia());
                }catch (Exception e){
                    insStatement.bindNull(7);
                }
                try {
                    insStatement.bindString(8, dto.getSuperior_tiendas());
                }catch (Exception e){
                    insStatement.bindNull(8);
                }
                try {
                    insStatement.bindString(9,dto.getSuperior_porcentaje());
                }catch (Exception e){
                    insStatement.bindNull(9);
                }
                try {
                    insStatement.bindString(10,dto.getNormal_tiendas());
                }catch (Exception e){
                    insStatement.bindNull(10);
                }
                try {
                    insStatement.bindString(11,dto.getNormal_porcentaje() );
                }catch (Exception e){
                    insStatement.bindNull(11);
                }
                try {
                    insStatement.bindString(12, dto.getBasica_tiendas());
                }catch (Exception e){
                    insStatement.bindNull(12);
                }
                try {
                    insStatement.bindString(13, dto.getBasica_porcentaje());
                }catch (Exception e){
                    insStatement.bindNull(13);
                }
                try {
                    insStatement.bindString(14,dto.getCritica_tiendas());
                }catch (Exception e){
                    insStatement.bindNull(14);
                }
                try {
                    insStatement.bindString(15, dto.getCritica_porcentaje());
                }catch (Exception e){
                    insStatement.bindNull(15);
                }
                try {
                    insStatement.bindString(16, dto.getSinmedicion_tienda());
                }catch (Exception e){
                    insStatement.bindNull(16);
                }
                try {
                    insStatement.bindString(17,dto.getSinmedicion_porcentaje() );
                }catch (Exception e){
                    insStatement.bindNull(17);
                }
                try {
                    insStatement.bindString(18, dto.getColas());
                }catch (Exception e){
                    insStatement.bindNull(18);
                }
                try {
                    insStatement.bindString(19,dto.getColas_color() );
                }catch (Exception e){
                    insStatement.bindNull(19);
                }
                try {
                    insStatement.bindString(20,dto.getSabores());
                }catch (Exception e){
                    insStatement.bindNull(20);
                }
                try {
                    insStatement.bindString(21,dto.getSabores_color());
                }catch (Exception e){
                    insStatement.bindNull(21);
                }
                try {
                    insStatement.bindString(22, dto.getAgua());
                }catch (Exception e){
                    insStatement.bindNull(22);
                }
                try {
                    insStatement.bindString(23,dto.getAgua_color());
                }catch (Exception e){
                    insStatement.bindNull(23);
                }
                try {
                    insStatement.bindString(24, dto.getGatorade());
                }catch (Exception e){
                    insStatement.bindNull(24);
                }
                try {
                    insStatement.bindString(25, dto.getGatorade_color());
                }catch (Exception e){
                    insStatement.bindNull(25);
                }
                try {
                    insStatement.bindString(26, dto.getLipton());
                }catch (Exception e){
                    insStatement.bindNull(26);
                }
                try {
                    insStatement.bindString(27,dto.getLipton_color());
                }catch (Exception e){
                    insStatement.bindNull(27);
                }
                try {
                    insStatement.bindString(28, dto.getJumex());
                }catch (Exception e){
                    insStatement.bindNull(28);
                }
                try {
                    insStatement.bindString(29,dto.getJumex_color());
                }catch (Exception e){
                    insStatement.bindNull(29);
                }
                try {
                    insStatement.bindString(30, dto.getStarbucks());
                }catch (Exception e){
                    insStatement.bindNull(30);
                }
                try {
                    insStatement.bindString(31,dto.getStarbucks_color());
                }catch (Exception e){
                    insStatement.bindNull(31);
                }
                try {
                    insStatement.bindString(32, dto.getMixers());
                }catch (Exception e){
                    insStatement.bindNull(32);
                }
                try {
                    insStatement.bindString(33,dto.getMixers_color());
                }catch (Exception e){
                    insStatement.bindNull(33);
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

    public DtoUserInfo select(){
        db= helper.getReadableDatabase();
        String qry ="SELECT * FROM "+TABLE_NAME;
        cursor= db.rawQuery(qry,null);
        DtoUserInfo catalogo;
        catalogo= new DtoUserInfo();
        if(cursor.moveToFirst()){
            do {
                catalogo.setNumero_semana(cursor.getString(cursor.getColumnIndexOrThrow(NUSEMANA)));
                catalogo.setPorcentaje_semana(cursor.getString(cursor.getColumnIndexOrThrow(PORCENTAJESEMANA)));
                catalogo.setFoto_exito(cursor.getString(cursor.getColumnIndexOrThrow(FOTOEXITO)));
                catalogo.setEfectividad_porcentaje_semana(cursor.getString(cursor.getColumnIndexOrThrow(EFECTIVIDADPORCENTAJE)));
                catalogo.setEfectividad_porcentaje_semana_bandera(cursor.getString(cursor.getColumnIndexOrThrow(EFECTIVIDADBANDERA)));
                catalogo.setEfectividad_acumulado_anual(cursor.getString(cursor.getColumnIndexOrThrow(EFECTIVIDADANUAL)));
                catalogo.setCosto_inasistencia(cursor.getString(cursor.getColumnIndexOrThrow(COSTOINASISTENCIA)));
                catalogo.setSuperior_tiendas(cursor.getString(cursor.getColumnIndexOrThrow(SUPERIORTIENDA)));
                catalogo.setSuperior_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(SUPERIORPORCENTAJE)));
                catalogo.setNormal_tiendas(cursor.getString(cursor.getColumnIndexOrThrow(NORMALTIENDA)));
                catalogo.setNormal_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(NORMALPORCENTAJE)));
                catalogo.setBasica_tiendas(cursor.getString(cursor.getColumnIndexOrThrow(BASICASTIENDAS)));
                catalogo.setBasica_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(BASICAPORCENTAJE)));
                catalogo.setCritica_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(CRITICAPORCENTAJE)));
                catalogo.setCritica_tiendas(cursor.getString(cursor.getColumnIndexOrThrow(CRITICATIENDAS)));
                catalogo.setSinmedicion_porcentaje(cursor.getString(cursor.getColumnIndexOrThrow(SINMEDICIONPORCENTAJE)));
                catalogo.setSinmedicion_tienda(cursor.getString(cursor.getColumnIndexOrThrow(SINMEDICION)));
                catalogo.setColas(cursor.getString(cursor.getColumnIndexOrThrow(COLAS)));
                catalogo.setColas_color(cursor.getString(cursor.getColumnIndexOrThrow(COLASCOLOR)));
                catalogo.setSabores(cursor.getString(cursor.getColumnIndexOrThrow(SABORES)));
                catalogo.setSabores_color(cursor.getString(cursor.getColumnIndexOrThrow(SABORESCOLOR)));
                catalogo.setAgua(cursor.getString(cursor.getColumnIndexOrThrow(AGUA)));
                catalogo.setAgua_color(cursor.getString(cursor.getColumnIndexOrThrow(AGUACOLOR)));
                catalogo.setGatorade(cursor.getString(cursor.getColumnIndexOrThrow(GATORADE)));
                catalogo.setGatorade_color(cursor.getString(cursor.getColumnIndexOrThrow(GATORADECOLOR)));
                catalogo.setLipton(cursor.getString(cursor.getColumnIndexOrThrow(LIPTON)));
                catalogo.setLipton_color(cursor.getString(cursor.getColumnIndexOrThrow(LIPTONCOLOR)));
                catalogo.setJumex(cursor.getString(cursor.getColumnIndexOrThrow(JUMEX)));
                catalogo.setJumex_color(cursor.getString(cursor.getColumnIndexOrThrow(JUMEXCOLOR)));
                catalogo.setStarbucks(cursor.getString(cursor.getColumnIndexOrThrow(STARTBUCK)));
                catalogo.setStarbucks_color(cursor.getString(cursor.getColumnIndexOrThrow(STARTBUCKCOLOR)));
                catalogo.setMixers(cursor.getString(cursor.getColumnIndexOrThrow(MIXER)));
                catalogo.setMixers_color(cursor.getString(cursor.getColumnIndexOrThrow(MIXERCOLOR)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return catalogo;
    }

}
