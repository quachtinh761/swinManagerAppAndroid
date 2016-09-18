package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constant.swineConstant;
import object.swineObject;

public class swineInfDB {

    private DBTemplate dbTemplate;
    private swineObject swine;
    private SQLiteDatabase db;
    //this is key of swine in database
    public String DBTable = "swine";
    public static String KEY_id = swineConstant.idString,
            KEY_name = swineConstant.nameString,
            KEY_dateImport = swineConstant.dateImportString,
            KEY_dateFirstVaccine = swineConstant.dateFirstVaccineString,
            KEY_dateCoordination = swineConstant.dateCoordinationString,
            KEY_numOfGoat = swineConstant.numOfGoatString,
            KEY_earNumber = swineConstant.earNumberString,
            KEY_numOfChildDie = swineConstant.numOfChildDieString,
            KEY_dateWeaned = swineConstant.dateWeanedString,
            KEY_dateVaccineChild = swineConstant.dateVaccineChildString;

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
        values.put(this.KEY_earNumber, swine.getearNumber());
        values.put(this.KEY_numOfChildDie, swine.getNumOfChildDie());
        values.put(this.KEY_dateWeaned, swine.getDateWeaned());
        values.put(this.KEY_dateVaccineChild, swine.getDateVaccineChild());
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
        table.put(this.KEY_earNumber, "TEXT NOT NULL");
        table.put(this.KEY_numOfChildDie, "TEXT NOT NULL");
        table.put(this.KEY_dateWeaned, "TEXT NOT NULL");
        table.put(this.KEY_dateVaccineChild, "TEXT NOT NULL");
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
        values.put(this.KEY_earNumber, swine.getearNumber());
        values.put(this.KEY_numOfChildDie, swine.getNumOfChildDie());
        values.put(this.KEY_dateWeaned, swine.getDateWeaned());
        values.put(this.KEY_dateVaccineChild, swine.getDateVaccineChild());
        DBTemplate.updateTable(this.db, this.DBTable, values, this.KEY_id + "='" + swine.getId() + "'");
    }

    //delete swine table (if it is needed)
    public void deleteTableSwine(){
        DBTemplate.deleteTable(this.db, DBTable);
    }

    //conditions separated by a comma
    public List <String[]> search(String valueToSearch, String condition, String groupBy, String orderBy){
        return DBTemplate.searchTable(this.db, valueToSearch, this.DBTable, condition, "", "");
    }

    public List<String[]> searchById(String idToSearch){
        return DBTemplate.searchTable(this.db, "*", this.DBTable, this.KEY_id + "='" + idToSearch + "'", "", "");
    }

    public List <String[]> searchByName(String nameToSearch){
        return DBTemplate.searchTable(this.db, "*", this.DBTable, this.KEY_name + "='" + nameToSearch + "'", "", "");
    }
    public List <String[]> searchByDateImport(String dateImportToSearch){
        return DBTemplate.searchTable(this.db, "*" , this.DBTable , this.KEY_dateImport + "=" + dateImportToSearch + "'", "", "");
    }

    public List <String[]> searchByearNumber(String earNumberToSearch){
        return DBTemplate.searchTable(this.db, "*" , this.DBTable , this.KEY_dateImport + "=" + earNumberToSearch + "'", "", "");
    }

}
