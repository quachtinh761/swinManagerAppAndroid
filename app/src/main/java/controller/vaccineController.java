package controller;

import android.content.Context;

import model.vaccineInfDB;
import object.vaccineObject;

/**
 * Created by Nguyen Van Tinh on 11/09/2016.
 */
public class vaccineController {

    private vaccineObject vaccine;
    private vaccineInfDB vaccineDB;
    private Context context;

    public vaccineController(Context context,vaccineObject vaccine){
        this.context = context;
        this.vaccine=vaccine;
        vaccineDB=new vaccineInfDB(context,vaccine);
    }

    public boolean deleteVaccine(vaccineObject vaccine){
        boolean success = checkValidVaccine(vaccine);
        if(success){
            vaccineDB.deleteVaccineInf(vaccine);
        }
        return success;
    }

    public boolean addNewVaccine(vaccineObject vaccine){
        boolean success = checkValidVaccine(vaccine);

        if(success){
            vaccineDB.insertVaccineInf(vaccine);
        }
        return success;
    }

    public boolean checkValidVaccine(vaccineObject vaccine){
       if( vaccine.getId().equals("") ||vaccineDB.searchById(vaccine.getName()).size()!=0){
           return false;
       }
        return true;
    }
}
