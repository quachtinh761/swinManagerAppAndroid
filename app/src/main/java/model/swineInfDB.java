package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import object.swineObject;

public class swineInfDB {

    private DBTemplate dbTemplate;
    private swineObject swine;
    private SQLiteDatabase db;
    //this is key of swine in database
    public String DBTable = "swine";
    public static String KEY_id = "id",
            KEY_name = "name",
            KEY_dateImport = "import",
            KEY_dateFirstVaccine = "vaccine",
            KEY_dateCoordination = "coordination",
            KEY_numOfGoat = "goat";

    public swineInfDB(Context context, swineObject swine) {
        dbTemplate = new DBTemplate(context);
        this.db = dbTemplate.getWritableDatabase();
        this.swine = swine;
    }

    public boolean insertSwineInf(swineObject swine) {
        Map <String, String> values = new HashMap<>();
        values.put(this.KEY_id, swine.getId());
        values.put(this.KEY_name, swine.getName());
        values.put(this.KEY_dateImport, swine.getDateImport());
        values.put(this.KEY_dateFirstVaccine, swine.getDateVaccine());
        values.put(this.KEY_dateCoordination, swine.getDateCoordination());
        values.put(this.KEY_numOfGoat, swine.getNumOfGoat());
        return DBTemplate.insertTable(this.db, this.DBTable, values);
    }

    //create swine table on database
    public boolean createTableSwine() {
        Map<String, String> table = new HashMap<>();
        table.put(this.KEY_id, "TEXT PRIMARY KEY");
        table.put(this.KEY_name, "TEXT NOT NULL");
        table.put(this.KEY_dateImport, "TEXT NOT NULL");
        table.put(this.KEY_dateFirstVaccine, "TEXT NOT NULL");
        table.put(this.KEY_dateCoordination, "TEXT NOT NULL");
        table.put(this.KEY_numOfGoat, "TEXT NOT NULL");
        return DBTemplate.createTable(this.db, this.DBTable, table);
    }

    //update swine information
    public void updateSwineInfo(swineObject swine){
        Map <String, String> values = new HashMap<>();
        values.put(this.KEY_name, swine.getName());
        values.put(this.KEY_dateImport, swine.getDateImport());
        values.put(this.KEY_dateFirstVaccine, swine.getDateVaccine());
        values.put(this.KEY_dateCoordination, swine.getDateCoordination());
        values.put(this.KEY_numOfGoat, swine.getNumOfGoat());
        DBTemplate.updateTable(this.db, this.DBTable, values, this.KEY_id + "='" + swine.getId() + "'");
    }

    //delete swine table (if it is needed)
    public void deleteTableSwine(){
        DBTemplate.deleteTable(this.db, DBTable);
    }

}
