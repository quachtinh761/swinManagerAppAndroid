package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constant.vaccineConstant;
import object.vaccineObject;

/**
 * Created by Nguyen Van Tinh on 11/09/2016.
 */
public class vaccineInfDB {

    private DBTemplate dbTemplate;
    private SQLiteDatabase db;
    private vaccineObject vaccine;

    //this is key of vaccine in database
    public String DBTable ="vaccine";
    public static String KEY_id = vaccineConstant.idString,
                        KEY_name = vaccineConstant.nameString,
                        KEY_indication = vaccineConstant.indicationString,
                        KEY_dose = vaccineConstant.doseString;
    public vaccineInfDB(Context context,vaccineObject vaccine){
        dbTemplate = new DBTemplate(context);
        db = dbTemplate.getWritableDatabase();
        this.vaccine = vaccine;
    }

    public boolean createTableVaccine() {
        Map<String, String> table = new HashMap<>();
        table.put(this.KEY_id, "TEXT PRIMARY KEY");
        table.put(this.KEY_name, "TEXT NOT NULL");
        table.put(this.KEY_indication, "TEXT NOT NULL");
        table.put(this.KEY_dose, "TEXT NOT NULL");
        return DBTemplate.createTable(this.db, this.DBTable, table);
    }

    public boolean insertVaccineInf(vaccineObject vaccine) {
        Map<String, String> values = new HashMap<>();
        values.put(this.KEY_id, vaccine.getId());
        values.put(this.KEY_name, vaccine.getName());
        values.put(this.KEY_indication, vaccine.getIndication());
        values.put(this.KEY_dose, vaccine.getDose());
        return DBTemplate.insertTable(this.db, this.DBTable, values);
    }

    public void updateVaccineInf(vaccineObject vaccine){
        Map<String,String>values = new HashMap<>();
        values.put(this.KEY_name, vaccine.getName());
        values.put(this.KEY_indication, vaccine.getIndication());
        values.put(this.KEY_dose, vaccine.getDose());
        DBTemplate.updateTable(this.db, this.DBTable, values, this.KEY_id + "='" + vaccine.getId() + "'");
    }

    public void deleteVaccineInf(vaccineObject vaccine){

    }

    public void deleteTableVaccine(){
        DBTemplate.deleteTable(this.db,DBTable);
    }

    //conditions separated by a comma
    public List<String[]> search(String valueToSearch, String condition, String groupBy, String orderBy){
        return DBTemplate.searchTable(this.db, valueToSearch, this.DBTable, condition, "", "");
    }

    public List<String[]> searchById(String idToSearch){
        return DBTemplate.searchTable(this.db, "*", this.DBTable, this.KEY_id + "='" + idToSearch + "'", "", "");
    }

    public List <String[]> searchByName(String nameToSearch){
        return DBTemplate.searchTable(this.db, "*", this.DBTable, this.KEY_name + "='" + nameToSearch + "'", "", "");
    }



}
