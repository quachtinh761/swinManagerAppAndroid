package object;

public class swineObject {
    //this is data of swine
    private String id, name, dateImport, dateFirstVaccine, dateCoordination, numOfGoat, earNumber,
            numOfChildDie, dateWeaned, dateVaccineChild;

    public swineObject() {
        this.numOfGoat = "";
        this.id = "";
        this.name = "";
        this.dateImport = "";
        this.dateFirstVaccine = "";
        this.dateCoordination = "";
        this.earNumber = "";
        this.numOfChildDie = "";
        this.dateWeaned = "";
        this.dateVaccineChild = "";
    }

    public swineObject(String id, String name, String dateImport, String dateFirstVaccine,
                       String dateCoordination, String numOfGoat, String earNumber,
                       String numOfChildDie, String dateWeaned, String dateVaccineChild) {
        this.numOfGoat = numOfGoat;
        this.id = id;
        this.name = name;
        this.dateImport = dateImport;
        this.dateFirstVaccine = dateFirstVaccine;
        this.dateCoordination = dateCoordination;
        this.earNumber = earNumber;
        this.numOfChildDie = numOfChildDie;
        this.dateWeaned = dateWeaned;
        this.dateVaccineChild = dateVaccineChild;
    }

    public swineObject (String ID, String name, String dateCoordination, String numOfGoat, String numOfChildDie,
                        String dateWeaned, String dateVaccineChild){
        this.id = ID;
        this.name = name;
        this.dateCoordination = dateCoordination;
        this.numOfGoat = numOfGoat;
        this.numOfChildDie = numOfChildDie;
        this.dateWeaned = dateWeaned;
        this.dateVaccineChild = dateVaccineChild;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateImport() {
        return dateImport;
    }

    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public String getDateVaccine() {
        return dateFirstVaccine;
    }

    public void setDateVaccine(String dateFirstVaccine) {
        this.dateFirstVaccine = dateFirstVaccine;
    }

    public String getDateCoordination() {
        return dateCoordination;
    }

    public void setDateCoordination(String dateCoordination) {
        this.dateCoordination = dateCoordination;
    }

    public String getNumOfGoat() {
        return numOfGoat;
    }

    public void setNumOfGoat(String numOfGoat) {
        this.numOfGoat = numOfGoat;
    }

    public String getearNumber() {
        return earNumber;
    }

    public void setearNumber(String numOfEar) {
        this.earNumber = numOfEar;
    }

    public String getNumOfChildDie() {
        return numOfChildDie;
    }

    public void setNumOfChildDie(String numOfChildDie) {
        this.numOfChildDie = numOfChildDie;
    }

    public String getDateWeaned() {
        return dateWeaned;
    }

    public void setDateWeaned(String dateWeaned) {
        this.dateWeaned = dateWeaned;
    }

    public String getDateFirstVaccine() {
        return dateFirstVaccine;
    }

    public void setDateFirstVaccine(String dateFirstVaccine) {
        this.dateFirstVaccine = dateFirstVaccine;
    }

    public String getDateVaccineChild() {
        return dateVaccineChild;
    }

    public void setDateVaccineChild(String dateVaccineChild) {
        this.dateVaccineChild = dateVaccineChild;
    }
}
