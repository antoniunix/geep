package net.gshp.gepp.sqlite;

/**
 * Created by leo on 5/03/18.
 */

public class Tables {
    public final String TABLE_SCHEDULE = "CREATE TABLE agenda("
            + "id INTEGER NOT NULL,"
            + "id_user INTEGER,"
            + "id_place INTEGER,"
            + "start_datetime TEXT,"
            + "end_datetime TEXT," +
            "lastUpdate INTEGER," +
            "id_rol INTEGER)";
    public final String TablePolitics = "CREATE TABLE politics("
            + "version TEXT,"
            + "politic TEXT)";
    public final String TABLE_PDV = "CREATE TABLE pdv("
            + "id INTEGER NOT NULL,"
            + "id_client INTEGER NOT NULL,"
            + "id_rtm INTEGER NOT NULL,"
            + "name TEXT,"
            + "address TEXT,"
            + "town INTEGER,"
            + "id_format INTEGER,"
            + "pdv_code TEXT,"
            + "lat REAL,"
            + "lon REAL,"
            + "extra_field1 TEXT,"
            + "extra_field2 TEXT,"
            + "extra_field3 TEXT,"
            + "type INTEGER," +
            " id_region INTEGER," +
            "id_rol INTEGER)";
    public final String Table_CClient = "CREATE TABLE c_client("
            + "id INTEGER NOT NULL,"
            + "value TEXT NOT NULL," +
            "id_rol INTEGER)";

    public final String TableReportReport = "CREATE TABLE report(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "id_pdv INTEGER," +
            "id_agenda INTEGER," +
            "version TEXT," +
            "date TEXT," +
            "tz TEXT," +
            "imei TEXT," +
            "hash TEXT," +
            "send INTEGER," +
            "type_report INTEGER," +
            "id_report_server INTEGER," +
            "date_inactive TEXT," +
            "active INTEGER)";

    public final String TableReportCheck = "CREATE TABLE report_check(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "id_report_local INTEGER," +
            "date TEXT," +
            "tz TEXT," +
            "latitude TEXT," +
            "longitude TEXT," +
            "accuracy TEXT," +
            "imei TEXT," +
            "satellite_utc TEXT," +
            "type INTEGER, " +
            "send INTEGER," +
            "provider TEXT," +
            "date_inactive TEXT," +
            "active INTEGER," +
            "hash TEXT)";
    public final String TableReportGeo = "CREATE TABLE report_geolocation(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "latitude TEXT," +
            "longitude TEXT," +
            "battery TEXT," +
            "accuracy TEXT," +
            "imei TEXT," +
            "satellite_utc TEXT," +
            "date TEXT," +
            "tz TEXT," +
            "version TEXT," +
            "send INTEGER," +
            "hash TEXT," +
            "provider TEXT," +
            "fakelocation_enabled INTEGER)";
    public final String TableCTypeReport = "CREATE TABLE c_type_report(" + "id INTEGER NOT NULL,"
            + "value TEXT NOT NULL," + "is_comment INTEGER," + "last_update TEXT)";

    /**
     * Encuesta
     */

    public final String Table_EAEncuesta = "CREATE TABLE EAEncuesta(" + "id INTEGER PRIMARY KEY NOT NULL,"
            + "nombre TEXT," + "vigenciaInicial INTEGER," + "vigenciaFinal INTEGER," + "repeticiones INTEGER,"
            + "canal TEXT," + "rtm TEXT," + "cliente TEXT," + "pdv TEXT," + "query TEXT," + "descripcion TEXT,"
            + "rtEnabled INTEGER,estado Integer,region TEXT,restriction INTEGER)";

    public final String Table_EAGrupo = "CREATE TABLE EAGrupo(" + "id INTEGER NOT NULL," + "nombre TEXT)";

    public final String Table_EAOpcionPregunta = "CREATE TABLE EAOpcionPregunta(" + "idPregunta INTEGER,"
            + "opcion TEXT," + "image TEXT)";

    public final String Table_EAPregunta = "CREATE TABLE EAPregunta(" + "id INTEGER PRIMARY KEY NOT NULL,"
            + "idSeccion INTEGER," + "idEncuesta INTEGER," + "idGrupo INTEGER," + "pregunta TEXT," + "parentId INTEGER,"
            + "idTipoPregunta INTEGER," + "obligatoria INTEGER," + "RangoMinimo TEXT," + "RangoMaximo TEXT,"
            + "orden INTEGER," + "peso INTEGER," + "operadorDependencia TEXT," + "valorDependencia1 TEXT,"
            + "valorDependencia2 TEXT," + "queryOpcionesDependencia TEXT," + "queryVisibility INTEGER,"
            + "queryOpciones TEXT)";

    public final String Table_EARespuesta = "CREATE TABLE EARespuesta("
            + "ida INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "idPregunta INTEGER, " + "idReporte INTEGER,"
            + "idReporteLocal INTEGER," + "idEncuesta INTEGER,"
            + "nombreEncuesta TEXT," + "respuesta TEXT," + "hash TEXT," + "enviado INTEGER," + "numeroEncuesta INTEGER,"
            + "campoExtra1 TEXT," + "campoExtra2 TEXT," + "timeStamp TEXT)";

    public final String Table_EARespuestaRT = "CREATE TABLE EARespuestaRT("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "idPregunta INTEGER, " + "idEncuesta INTEGER, "
            + "nombreEncuesta TEXT," + "respuesta TEXT," + "numeroEncuesta INTEGER," + "campoExtra1 TEXT,"
            + "campoExtra2 TEXT," + "timeStamp TEXT," + "idPdv INTEGER," + "hash TEXT," + "enviado INTEGER" + ")";

    public final String Table_EASeccion = "CREATE TABLE EASeccion(" + "id INTEGER PRIMARY KEY NOT NULL,"
            + "idEncuesta INTEGER," + "idParent INTEGER," + "orden INTEGER," + "peso INTEGER," + "nombre TEXT)";


    public static final String TableEAAnswerPdv = "CREATE TABLE EAAnswerPdv(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_pdv INTEGER, id_poll INTEGER," +
            "last_update TEXT)";

    public final String Table_EATipoPregunta = "CREATE TABLE EATipoPregunta(" + "id INTEGER PRIMARY KEY NOT NULL,"
            + "tipo TEXT)";

    /* Modulo Descargables
   */
    public final String TableDownloadHead = "CREATE TABLE measurement_download("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "startDate TEXT,"
            + "description TEXT,"
            + "endDate,"
            + "last_update TEXT)";
    ;

    public final String TableMeasurementDownloadSku = "CREATE TABLE mdf_sku("
            + "idItemRelation INTEGER,"
            + "title TEXT,"
            + "description TEXT,"
            + "idMeasurement INTEGER,"
            + "ext TEXT,"
            + "md5 TEXT,"
            + "url TEXT,"
            + "date_inactive TEXT," +
            "active TEXT)";

    public final String TableDownLoadDetail = "CREATE TABLE file_view ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "id_user INTEGER,"
            + "id_file INTEGER,"
            + "date TEXT,"
            + "tz TEXT,"
            + "imei TEXT,"
            + "hash TEXT,"
            + "send INTEGER)";

    //Message service
    public final String TableMessageService = "CREATE TABLE message("
            + "id INTEGER PRIMARY KEY,"
            + "type INTEGER,"
            + "title TEXT,"
            + "descripcion TEXT,"
            + "content TEXT,"
            + "end_date TEXT,"
            + "viewed INTEGER,"
            + "last_update TEXT)";

    //Message view
    public final String TableMessageView = "CREATE TABLE message_view("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "id_message INTEGER,"
            + "id_user INTEGER,"
            + "date TEXT,"
            + "tz TEXT,"
            + "imei TEXT,"
            + "send INTEGER,"
            + "date_inactive TEXT," +
            "active TEXT)";

    public final String Table_CCanal = "CREATE TABLE c_canal("
            + "id INTEGER NOT NULL,"
            + "value TEXT NOT NULL)";
    public final String Table_CRtm = "CREATE TABLE c_rtm("
            + "id INTEGER NOT NULL,"
            + "id_canal INTEGER NOT NULL,"
            + "value TEXT NOT NULL)";

    public final String TABLE_COOP_PDV = "CREATE TABLE coop_pdv(" +
            "id_coop INTEGER," +
            "id_supervisor INTEGER," +
            "id_mercaderista INTEGER," +
            "id_pdv INTEGER," +
            "tipo_pdv INTEGER," +
            "codigo_pdv TEXT," +
            "nombre_pdv TEXT," +
            "direccion TEXT," +
            "canal TEXT," +
            "modelo_atencion TEXT," +
            "nombre_supervisor TEXT," +
            "total INTEGER," +
            "total_color TEXT," +
            "inventario_sinventa TEXT," +
            "inventario_tiendas TEXT," +
            "inventario_color TEXT," +
            "agotamiento TEXT," +
            "agotamiento_tiendas TEXT," +
            "agotamiento_color TEXT," +
            "disponibilidad TEXT," +
            "disponibilidad_tiendas TEXT," +
            "disponibilidad_color TEXT," +
            "exhibiciones TEXT," +
            "exhibiciones_tiendas TEXT," +
            "exhibiciones_color TEXT," +
            "esenciales TEXT," +
            "esenciales_tiendas TEXT," +
            "esenciales_color TEXT," +
            "bigrocks TEXT," +
            "bigrocks_tiendas TEXT," +
            "bigrocks_color TEXT," +
            "tacticos TEXT," +
            "tacticos_tiendas TEXT," +
            "tacticos_color TEXT," +
            "efectividad TEXT," +
            "efectividad_tiendas TEXT," +
            "efectividad_color TEXT," +
            "rating TEXT," +
            "latitude TEXT," +
            "longitude TEXT)";

    public final String TableReportTask = "CREATE TABLE report_task(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "id_report INTEGER," +
            "id_pdv INTEGER," +
            "creation_date INTEGER," +
            "creation_tz TEXT," +
            "id_rol INTEGER," +
            "id_item TEXT," +
            "item_desc TEXT," +
            "id_type INTEGER," +
            "comment TEXT," +
            "hash TEXT," +
            "send INTEGER," +
            "id_report_task_server INTEGER," +
            "resolved_date INTEGER," +
            "id_tatus INTEGER," +
            "responsible_name TEXT," +
            "responsible_user TEXT," +
            "caducity_date TEXT," +
            "creator_user TEXT," +
            "creator_name TEXT," +
            "viewed INTEGER)";


    /**
     * Measurement of module menu report
     */
    public static final String TableMeasurementModuleHead = "CREATE TABLE measurement_module_head(" +
            "id INTEGER," +
            "startDate TEXT," +
            "endDate TEXT," +
            "description TEXT," +
            "last_update TEXT)";
    public static final String TableMeasurementModule = "CREATE TABLE measurement_module(" +
            "id INTEGER," +
            "id_measurement INTEGER," +
            "id_item INTEGER," +
            "value TEXT," +
            "required INTEGER," +
            "_orden INTEGER," +
            "last_update TEXT)";
    public static final String TableMeasurementModuleClient = "CREATE TABLE measurement_module_client(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";
    public static final String TableMeasurementModuleCanal = "CREATE TABLE measurement_module_canal(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";
    public static final String TableMeasurementModuleFormat = "CREATE TABLE measurement_module_format(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";
    public static final String TableMeasurementModulePdv = "CREATE TABLE measurement_module_pdv(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";
    public static final String TableMeasurementModuleRtm = "CREATE TABLE measurement_module_rtm(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";
    public static final String TableMeasurementModuleRegion = "CREATE TABLE measurement_module_region(" +
            "id_item_relation INTEGER NOT NULL," +
            "id_measurement INTEGER NOT NULL," +
            "last_update TEXT)";

    public static final String TableScannAlert = "CREATE TABLE scann_alert("
            + "id INTEGER," + "id_sku INTEGER," + "id_pdv INTEGER,"
            + "id_tp INTEGER," + "key TEXT, sku_description TEXT," +
            "promedioVtaSemanal TEXT," +
            "ventaSemanaActual TEXT," +
            "ventaSemanaAnterior TEXT," +
            "increment TEXT," +
            "invUnidadesSemanaActual TEXT," +
            "diasInv TEXT)";


    public static final String TableReportScannAlert = "CREATE TABLE report_scan_alert("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "id_report INTEGER,"
            + "id_sku INTEGER,"
            + "key TEXT,"
            + "pdv INTEGER,"
            + "status INTEGER,"
            + "hash TEXT,"
            + "send TEXT,"
            + "id_tp INTEGER)";

    public static final String TableStatusScannAlert = "CREATE TABLE  c_status_scann_alert ("
            + "id INTEGER," + "value TEXT,color TEXT,id_problem INTEGER)";

    public static final String TableProbleScannAlert = "CREATE TABLE  c_problem_scann_alert ("
            + "id INTEGER," + "value TEXT)";
    public static final String Table_CCategory = "CREATE TABLE c_category("
            + "id INTEGER NOT NULL," + "value TEXT NOT NULL)";

    public final String TableMeasurementHeadExhibition = "CREATE TABLE measurement_head_exhibition(" +
            "id INTEGER," +
            "startDate TEXT," +
            "description TEXT," +
            "endDate TEXT)";
    public final String TableCExhibition = "CREATE TABLE c_exhibition(" +
            "exhibition_name TEXT," +
            "id_type_exhibition INTEGER," +
            "typeExhibition TEXT," +
            "id_pdv INTEGER," +
            "min_photos INTEGER," +
            "max_photos INTEGER," +
            "end_date TEXT," +
            "hash TEXT," +
            "id_exhibition_group INTEGER," +
            "manufacturer TEXT," +
            "category TEXT," +
            "family TEXT," +
            "sub_famlly TEXT," +
            "grupo TEXT," +
            "department TEXT," +
            "location TEXT)";

    public static final String Table_Subordinado = "CREATE TABLE subordinate("
            + "id INTEGER,"
            + "apellido_pat TEXT,"
            + "apellido_mat TEXT,"
            + "nombre TEXT,"
            + "puesto TEXT,"
            + "report_asignados INTEGER,"
            + "report_realizado INTEGER,"
            + "lat REAL,"
            + "lon REAL)";


    public final String TableCTypeCatalogExhibition = "CREATE TABLE c_type_catalog_exhibition(" +
            "id INTEGER," +
            "value TEXT," +
            "position INTEGER," +
            "field_name TEXT," +
            "idCategory INTEGER," +
            "parent INTEGER)";


    public final String TableTypeExhibition = "CREATE TABLE type_exhibition(" +
            "id_measurement INTEGER," +
            "id_item_relation INTEGER," +
            "value TEXT)";

    public final String TableMeasurementCauseExhibition = "CREATE TABLE measurement_cause_exhibition(" +
            "id_measurement INTEGER," +
            "id_item_relation INTEGER," +
            "value TEXT)";

    public final String TableMeasurementExhibitionClient = "CREATE TABLE measurement_exhibition_client("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";
    public final String TableMeasurementExhibitioncanal = "CREATE TABLE measurement_exhibition_canal("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";
    public final String TableMeasurementExhibitionFormat = "CREATE TABLE measurement_exhibition_format("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";
    public final String TableMeasurementExhibitionPdv = "CREATE TABLE measurement_exhibition_pdv("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";
    public final String TableMeasurementExhibitionRtm = "CREATE TABLE measurement_exhibition_rtm("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";
    public final String TableMeasurementExhibitionRegion = "CREATE TABLE measurement_exhibition_region("
            + "id_item_relation INTEGER NOT NULL," + "id_measurement INTEGER NOT NULL,last_update TEXT)";


    public final String TableReportExhibitionMantained = " CREATE TABLE report_exhibition_mantained(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_report_local INTEGER," +
            "hash_exhibition TEXT," +
            "is_exhibit INTEGER," +
            "hash TEXT," +
            "send INTEGER)";

    public final String TableReportExhibitionMantainedCause = "CREATE TABLE report_exhibition_mantained_cause(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_report_local INTEGER," +
            "hash_exhibition TEXT," +
            "id_cause INTEGER," +
            "comment TEXT," +
            "hash TEXT," +
            "send INTEGER)";
    public final String TableReportExhibitionMantainedPhoto = "CREATE TABLE report_exhibition_mantained_photo(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_report_local INTEGER," +
            "id_report_exhibition_mantained INTEGER," +
            "hashExhibitionCatalog TEXT," +
            "hash TEXT," +
            "path TEXT," +
            "send INTEGER,"
            + "send_img INTEGER)";
    public final String TableMeasurementItemExhibition = "CREATE TABLE measurement_item_exhibition(" +
            "id_measurement INTEGER," +
            "id_type_catalog INTEGER," +
            "id_item_relation INTEGER," +
            "value TEXT," +
            "parent INTEGER," +
            "_order INTEGER," +
            "weight INTEGER)";

    public final String TableReportHeadExhibition = "CREATE TABLE report_head_exhibition(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_report_local INTEGER," +
            "id_type_exhibition INTEGER," +
            "id_pdv INTEGER," +
            "created_date TEXT," +
            "hash TEXT," +
            "send INTEGER)";

    public final String TableReportExhibitionDetail = "CREATE TABLE report_exhibition_detail(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_report_local INTEGER," +
            "hash_exhibition TEXT," +
            "id_exhibition_group INTEGER," +
            "id_manufacturer INTEGER," +
            "id_category INTEGER," +
            "id_family INTEGER," +
            "family TEXT," +
            "id_subfamily INTEGER," +
            "type INTEGER," +
            "location INTEGER," +
            "path TEXT," +
            "hash TEXT," +
            "send INTEGER)";


}
