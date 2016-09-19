package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBTemplate extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;
    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBTemplate(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void deleteTable(SQLiteDatabase db, String tableName){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public static List<String[]> searchTable(SQLiteDatabase db, String valueNeedToSearch, String tableName, String condition, String orderBy, String groupBy){
        List <String[]> result = new ArrayList<>();
        try {
            String selectQuery = "SELECT " + valueNeedToSearch
                    + " FROM " + tableName
                    + " WHERE " + condition;
            if (!groupBy.equals("")){
                selectQuery += " GROUP BY " + groupBy;
            }
            if (!orderBy.equals("")){
                selectQuery += " ORDER BY " + orderBy;
            }
            Cursor cursor = db.rawQuery(selectQuery, null);
        }catch (Exception e){
        }
        return result;
    }

    public static List<String[]> searchTable(SQLiteDatabase db, String sql){
        List <String[]> result = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery(sql, null);
        }catch (Exception e){
        }
        return result;
    }

    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","fieldValueChange1");
     * params.put("fieldName2","fieldValueChange2");
     * condition: where ...
     **/
    public static void updateTable(SQLiteDatabase db, String tableName, Map<String, String> params, String condition){
        ContentValues values = new ContentValues();
        for (Map.Entry<String, String> entry : params.entrySet()){
            values.put(entry.getKey(), entry.getValue());
        }
        db.update(tableName, values, condition, null);
    }

    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","fieldValue1");
     * params.put("fieldName2","fieldValue2");
     **/
    public static boolean insertTable(SQLiteDatabase db, String tableName, Map<String, String> params) {
        //Open connection to write data
        try {
            ContentValues values = new ContentValues();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                values.put(entry.getKey(), entry.getValue());
            }
            // Inserting Row
            db.insert(tableName, null, values);
            db.close(); // Closing database connection
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","filedType1(primary key if needed)");
     * params.put("fieldName2","filedType2 (not null)");
     **/
    public static boolean createTable(SQLiteDatabase db, String tableName, Map<String, String> params){
        try {
            String CREATE_TABLE = "IF EXISTS ("
                    + "SELECT * "
                    + "FROM INFORMATION_SCHEMA.TABLES "
                    + "WHERE TABLE_NAME = N'" + tableName + "') "
                    + "BEGIN";
            CREATE_TABLE += "CREATE TABLE " + tableName  + "(";
            for (Map.Entry<String, String> entry : params.entrySet()){
                CREATE_TABLE += entry.getKey() + " " + entry.getValue() + ",";
            }
            if (CREATE_TABLE.endsWith(",")){
                CREATE_TABLE = CREATE_TABLE.substring(0, CREATE_TABLE.length() - 2);
                CREATE_TABLE += ") ";
            }else{
                return false;
            }
            CREATE_TABLE += "END";
            db.execSQL(CREATE_TABLE);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * delete record in table
     **/
    public static boolean deleteRecord(SQLiteDatabase db, String tableName, String condition){
        try {
            String sql = "DELETE FROM " + tableName + " WHERE " + condition;
            db.execSQL(sql);
            db.close(); //Closing database connection
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Map <String, String> conditions = new HashMap<String,String>();
     * conditions.put("id","123456");
     * conditions.put("<columnName>","<value>");
     **/
    public static boolean deleteRecordBy(SQLiteDatabase db, String tableName, Map<String, String> conditions){
        String sql = "DELETE FROM " + tableName + " WHERE ";
        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            sql += entry.getKey() + "=" + entry.getValue() + " AND ";
        }
        if (sql.toUpperCase().endsWith("AND ")){
            sql = sql.substring(0, sql.length() - 5);
        }
        sql += ";";
        try{
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
