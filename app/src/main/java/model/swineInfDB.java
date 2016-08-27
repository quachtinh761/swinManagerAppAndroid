package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class swineInfDB {

    private DBHelper dbHelper;
    private swineObject swine;
    //this is key of swine in database
    public String DBTable = "swine";
    public static String KEY_id = "id",
            KEY_name = "name",
            KEY_dateImport = "import",
            KEY_dateFirstVaccine = "vaccine",
            KEY_dateCoordination = "coordination",
            KEY_numOfGoat = "goat";
    public swineInfDB(Context context) {
        dbHelper = new DBHelper(context);
        swine = new swineObject();
    }

    public int insertSwineInf() {
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.KEY_id, swine.getId());
        values.put(this.KEY_name, swine.getName());
        values.put(this.KEY_dateImport, swine.getDateImport());
        values.put(this.KEY_dateFirstVaccine, swine.getDateVaccine());
        values.put(this.KEY_dateCoordination, swine.getDateCoordination());
        values.put(this.KEY_numOfGoat, swine.getNumOfGoat());

        db.close(); // Closing database connection
        return 0;
    }

    //create swine table on database
    public void createTable(SQLiteDatabase db) {
        String CREATE_TABLE_STUDENT = "IF EXISTS ("
                + "SELECT * "
                + "FROM INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_NAME = N'" + this.DBTable + "') "
                + "BEGIN";
        CREATE_TABLE_STUDENT += "CREATE TABLE " + this.DBTable  + "("
                + this.KEY_id  + " TEXT PRIMARY KEY AUTOINCREMENT ,"
                + this.KEY_name + " TEXT, "
                + this.KEY_dateImport + " TEXT, "
                + this.KEY_dateFirstVaccine + " TEXT, "
                + this.KEY_dateCoordination + " TEXT, "
                + this.KEY_numOfGoat + " TEXT, ";
        CREATE_TABLE_STUDENT += "END";
        db.execSQL(CREATE_TABLE_STUDENT);
    }



}
