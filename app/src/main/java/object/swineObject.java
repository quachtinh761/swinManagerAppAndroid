package object;

public class swineObject {
    //this is data of swine
    private String id, name, dateImport, dateFirstVaccine, dateCoordination, numOfGoat;

    public swineObject() {
        this.numOfGoat = "";
        this.id = "";
        this.name = "";
        this.dateImport = "";
        this.dateFirstVaccine = "";
        this.dateCoordination = "";
    }

    public swineObject(String id, String name, String dateImport, String dateFirstVaccine, String dateCoordination, String numOfGoat) {
        this.numOfGoat = numOfGoat;
        this.id = id;
        this.name = name;
        this.dateImport = dateImport;
        this.dateFirstVaccine = dateFirstVaccine;
        this.dateCoordination = dateCoordination;
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
}
