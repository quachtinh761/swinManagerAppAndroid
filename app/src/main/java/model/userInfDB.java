package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constant.userConstant;
import object.userObject;

/**
 * Created by Nguyen Van Tinh on 11/09/2016.
 */
public class userInfDB {
    private DBTemplate dbTemplate;
    private userObject user;
    private SQLiteDatabase db;
    //this is key of swine in database
    public String DBTable = "user";
    public static String KEY_id = userConstant.userIdString,
            KEY_password = userConstant.passwordString,
            KEY_name = userConstant.nameString,
            KEY_birthday = userConstant.birthdayString,
            KEY_right = userConstant.rightString;
    public userInfDB(Context context, userObject user) {
        dbTemplate = new DBTemplate(context);
        this.db = dbTemplate.getWritableDatabase();
        this.user = user;
    }

    public boolean createTableUser() {
        Map<String, String> table = new HashMap<>();
        table.put(this.KEY_id, "TEXT PRIMARY KEY");
        table.put(this.KEY_password, "TEXT NOT NULL");
        table.put(this.KEY_name, "TEXT NOT NULL");
        table.put(this.KEY_birthday, "TEXT NOT NULL");
        table.put(this.KEY_right, "TEXT NOT NULL");
        return DBTemplate.createTable(this.db, this.DBTable, table);
    }

    public boolean insertUserInf(userObject user) {
        Map<String, String> values = new HashMap<>();
        values.put(this.KEY_id, user.getUserId());
        values.put(this.KEY_password, user.getUserPassword());
        values.put(this.KEY_name, user.getName());
        values.put(this.KEY_birthday, user.getBirthday());
        values.put(this.KEY_right, user.getRight());
        return DBTemplate.insertTable(this.db, this.DBTable, values);
    }

    public void updateUserInf(userObject user){
        Map <String, String> values = new HashMap<>();
        values.put(this.KEY_password, user.getUserPassword());
        values.put(this.KEY_name, user.getName());
        values.put(this.KEY_birthday, user.getBirthday());
        values.put(this.KEY_right, user.getRight());
        DBTemplate.updateTable(this.db, this.DBTable, values, this.KEY_id + "='" + user.getUserId() + "'");
    }

    //delete swine table (if it is needed)
    public void deleteTableSwine(){
        DBTemplate.deleteTable(this.db, DBTable);
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
