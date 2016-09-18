package common.function;

/**
 * class dùng để covert dữ liệu trong chương trình
 *
 * Created by nguye on 9/8/2016.
 */
public class convertData {
    //cắt chuỗi dạng &vdvd#ddfd#cđ#cdv$ thành Json
    public static String[] cuttingStringToArray(String str){
        int m = 1;
        String[] buf= new String[5];
        int[] temp = new int[6];
        temp[0]=0;
        temp[5]=str.length()-1;
        if (str.startsWith("$")&&str.endsWith("$")){
            for (int i=1; i < str.length()-1; i++){
                if(str.charAt(i)=='#'){
                    temp[m] = i;
                    m++;
                }
            }
            if (m != 5) return null;
            for(int i=0;i<5;i++){
                buf[i] = str.substring(temp[i]+1,temp[i+1]);
            }
            return buf;
        }else return null;
    }
    //tạo thành string tạo đường link đưa xuống ghi
    public static String makeLinkToWrite(String[] data){
        String temp = data[0];
        if (data[1].length()!=0) temp =temp + "&NameFarm=" + data[1];
        if (data[2].length()!=0) temp =temp + "&NamePig=" + data[2];
        if (data[3].length()!=0) temp =temp + "&DateSex=" + data[3];
        if (data[4].length()!=0) temp =temp + "&NumberVaccineParent=" + data[4];
        if (data[5].length()!=0) temp =temp + "&VaccineParent=" + data[5];
        if (data[6].length()!=0) temp =temp + "&Dategoat=" + data[6];
        if (data[7].length()!=0) temp =temp + "&NumberVaccinechild=" + data[7];
        if (data[8].length()!=0) temp =temp + "&Vaccinechild=" + data[8];
        return temp;
    }
}
