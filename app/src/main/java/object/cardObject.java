package object;

import java.util.Date;

/**
 * Created by vanthi on 10/16/2016.
 */

public class cardObject {
    private String id, earNumber, userID, lsIDVaccineFirst, lsIDVaccineHavedChild, lsIDVaccineChild;
    private Date dateImport, dateFirstVaccine, dateCoordination, dateVaccineChild, dateWeaned, dateWriteLasted;

    //constructor
    public cardObject(){
        id = earNumber = userID = lsIDVaccineFirst = lsIDVaccineHavedChild = lsIDVaccineChild = "";
        dateImport = dateFirstVaccine = dateCoordination = dateVaccineChild = dateWeaned = dateWriteLasted = null;
    }
    public cardObject(String id, Date dateImport, Date dateFirstVaccine, Date dateCoordination, String earNumber,
                      Date dateWeaned, Date dateVaccineChild, String userID, String lsIDVaccineFirst, String lsIDVaccineHavedChild,
                      String lsIDVaccineChild, Date dateWriteLasted)
    {
        this.id = id;
        this.dateImport = dateImport;
        this.dateFirstVaccine = dateFirstVaccine;
        this.dateCoordination = dateCoordination;
        this.earNumber = earNumber;
        this.dateWeaned = dateWeaned;
        this.dateVaccineChild = dateVaccineChild;
        this.userID = userID;
        this.lsIDVaccineFirst = lsIDVaccineFirst;
        this.lsIDVaccineHavedChild = lsIDVaccineHavedChild;
        this.lsIDVaccineChild = lsIDVaccineChild;
        this.dateWriteLasted = dateWriteLasted;
    }
    public void setID(String id) { this.id = id;}
    public void setDateImport(Date dateImport) {this.dateImport = dateImport;}
    public void setDateFirstVaccine(Date dateFirstVaccine){this.dateFirstVaccine = dateFirstVaccine;}
    public void setDateCoordination(Date dateCoordination){this.dateCoordination = dateCoordination;}
    public void setEarNumber(String earNumber) {this.earNumber = earNumber;}
    public void setDateWeaned(Date dateWeaned) {this.dateWeaned = dateWeaned;}
    public void setDateVaccineChild(Date dateVaccineChild) {this.dateVaccineChild = dateVaccineChild;}
    public void setUserID(String userID) {this.userID = userID;}
    public void setLsIDVaccineFirst(String lsIDVaccineFirst){this.lsIDVaccineFirst = lsIDVaccineFirst;}
    public void setLsIDVaccineFirst(String[] lsIDVaccineFirst){
        String data = "";
        for (int i = 0; i < lsIDVaccineFirst.length ; i++){
            data += lsIDVaccineFirst[i];
        }
        this.lsIDVaccineFirst = data;
    }
    public void setLsIDVaccineHavedChild(String lsIDVaccineHavedChild) {this.lsIDVaccineHavedChild = lsIDVaccineHavedChild;}
    public void setLsIDVaccineHavedChild(String[] LsIDVaccineHavedChild){
        String data = "";
        for (int i = 0; i < LsIDVaccineHavedChild.length ; i++){
            data += LsIDVaccineHavedChild[i];
        }
        this.lsIDVaccineHavedChild = data;
    }
    public void setLsIDVaccineChild(String lsIDVaccineChild){this.lsIDVaccineChild = lsIDVaccineChild;}
    public void setLsIDVaccineChild(String[] lsIDVaccineChild){
        String data = "";
        for (int i = 0; i < lsIDVaccineChild.length ; i++){
            data += lsIDVaccineChild[i];
        }
        this.lsIDVaccineChild = data;
    }
    public void setDateWriteLasted(Date dateWriteLasted) { this.dateWriteLasted = dateWriteLasted;}

    public String getID() { return this.id;}
    public Date getDateImport() {return this.dateImport;}
    public Date getDateFirstVaccine(){return this.dateFirstVaccine;}
    public Date getDateCoordination(){return this.dateCoordination;}
    public String getEarNumber() {return this.earNumber;}
    public Date getDateWeaned() {return this.dateWeaned;}
    public Date getDateVaccineChild() {return this.dateVaccineChild;}
    public String getUserID() {return userID;}
    public String getLsIDVaccineFirst(){return lsIDVaccineFirst;}
    public String getLsIDVaccineHavedChild() {return this.lsIDVaccineHavedChild;}
    public String getLsIDVaccineChild(){return this.lsIDVaccineChild;}
    public Date getDateWriteLasted() {return this.dateWriteLasted;}
}
