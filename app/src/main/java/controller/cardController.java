package controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import model.swineInfDB;
import model.userInfDB;
import model.vaccineInfDB;
import object.*;
import common.function.readWriteNfc;

/**
 * Created by vanthi on 10/16/2016.
 */

public class cardController {
    private cardObject card;
    private userInfDB userDB;
    private swineInfDB swineDB;
    private vaccineInfDB vaccineDB;
    private Context context;
    private userObject user;
    private swineObject swine;
    private vaccineObject vaccine;
    private readWriteNfc nfcFunc;

    public String[] readCard(String key){
        return nfcFunc.readNfcTag(key);
    }
}
