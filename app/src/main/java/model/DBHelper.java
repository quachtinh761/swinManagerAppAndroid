package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;
    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
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

    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","filedValue1");
     * params.put("fieldName2","filedValue2");
     **/
    public void insert(SQLiteDatabase db, String tableName, Map<String, String> params) {
        //Open connection to write data
        ContentValues values = new ContentValues();
        for (Map.Entry<String, String> entry : params.entrySet()){
            values.put(entry.getKey(), entry.getValue());
        }
        // Inserting Row
        db.insert(tableName, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","filedType1");
     * params.put("fieldName2","filedType2");
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


}
