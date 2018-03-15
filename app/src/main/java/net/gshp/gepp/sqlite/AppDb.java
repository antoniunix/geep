package net.gshp.gepp.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.gshp.gepp.R;
import net.gshp.gepp.contextApp.ContextApp;
import net.gshp.gepp.util.Config;

/**
 * Created by leo on 5/03/18.
 */

public class AppDb extends SQLiteOpenHelper {
    private Tables tables;

    public AppDb() {
        super(ContextApp.context, Config.getAbsolutePathDataBase(), null, ContextApp.context.getResources().getInteger(R.integer.app_version_db));
        tables = new Tables();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tables.TABLE_SCHEDULE);
        db.execSQL(tables.TableReportReport);
        db.execSQL(tables.TableReportCheck);
        db.execSQL(tables.TableReportGeo);
        db.execSQL(tables.TableCTypeReport);

        db.execSQL(tables.Table_EAEncuesta);
        db.execSQL(tables.Table_EAGrupo);
        db.execSQL(tables.Table_EAOpcionPregunta);
        db.execSQL(tables.Table_EAPregunta);
        db.execSQL(tables.Table_EARespuesta);
        db.execSQL(tables.Table_EASeccion);
        db.execSQL(tables.Table_EATipoPregunta);
        db.execSQL(tables.Table_EARespuestaRT);
        db.execSQL(tables.TableEAAnswerPdv);
        db.execSQL(tables.TablePolitics);
        db.execSQL(tables.TABLE_PDV);
        db.execSQL(tables.Table_CClient);
        db.execSQL(tables.TableMeasurementDownloadSku);
        db.execSQL(tables.TableDownloadHead);
        db.execSQL(tables.TableDownLoadDetail);
        db.execSQL(tables.TableMessageService);
        db.execSQL(tables.TableMessageView);
        db.execSQL(tables.Table_CCanal);
        db.execSQL(tables.Table_CRtm);
        db.execSQL(tables.TABLE_COOP_PDV);
        db.execSQL(tables.TableReportTask);

        //modulo
        db.execSQL(tables.TableMeasurementModuleHead);
        db.execSQL(tables.TableMeasurementModule);
        db.execSQL(tables.TableMeasurementModuleClient);
        db.execSQL(tables.TableMeasurementModuleCanal);
        db.execSQL(tables.TableMeasurementModuleFormat);
        db.execSQL(tables.TableMeasurementModulePdv);
        db.execSQL(tables.TableMeasurementModuleRtm);
        db.execSQL(tables.TableMeasurementModuleRegion);

        //scan
        db.execSQL(tables.TableScannAlert);
        db.execSQL(tables.TableReportScannAlert);
        db.execSQL(tables.TableStatusScannAlert);
        db.execSQL(tables.TableProbleScannAlert);
        db.execSQL(tables.Table_CCategory);

        //exhibition
        db.execSQL(tables.TableCExhibition);
        db.execSQL(tables.TableMeasurementItemExhibition);
        db.execSQL(tables.TableMeasurementHeadExhibition);
        db.execSQL(tables.TableTypeExhibition);
        db.execSQL(tables.TableCTypeCatalogExhibition);
        db.execSQL(tables.TableMeasurementCauseExhibition);
        db.execSQL(tables.TableMeasurementExhibitionClient);
        db.execSQL(tables.TableMeasurementExhibitioncanal);
        db.execSQL(tables.TableMeasurementExhibitionFormat);
        db.execSQL(tables.TableMeasurementExhibitionPdv);
        db.execSQL(tables.TableMeasurementExhibitionRtm);
        db.execSQL(tables.TableMeasurementExhibitionRegion);
        db.execSQL(tables.TableReportExhibitionMantained);
        db.execSQL(tables.TableReportExhibitionMantainedCause);
        db.execSQL(tables.TableReportExhibitionMantainedPhoto);
        db.execSQL(tables.TableReportHeadExhibition);
        db.execSQL(tables.TableReportExhibitionDetail);
        //Subordinado
        db.execSQL(tables.Table_Subordinado);
        //Scorecard
        db.execSQL(tables.TablePdvCS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
