package object;

/**
 * Created by Nguyen Van Tinh on 11/09/2016.
 */
public class vaccineObject {

    //this is data of vaccine
    private String id,name,indication,dose;

    public vaccineObject(){

        id = "";
        name = "";
        indication = "";
        dose = "";
    }
    public vaccineObject(String id,String name,String indication,String dose){

        this.id = id;
        this.name = name;
        this.indication = indication;
        this.dose = dose;

    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getIndication(){
        return indication;
    }
    public void setIndication(String indication){
        this.indication=indication;
    }

    public String getDose(){
        return dose;
    }
    public void setDose(String dose){
        this.dose=dose;
    }

}
