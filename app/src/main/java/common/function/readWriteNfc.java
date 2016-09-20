package common.function;

import android.os.AsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import common.function.handleJson;

/**
 * Thêm quyền sau:
 *  <uses-permission android:name="android.permission.INTERNET" />
 *  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 *  Thêm link trong Gradle Scripts -> build.gradle
 *  useLibrary 'org.apache.http.legacy'
 * Created by nguyenvanthi on 9/4/2016.
 */
public class readWriteNfc {
    /*---------------------------------------variale-----------------------------*/
    private String dataReaded="";

    /*--------------------------------------Public Method------------------------ */

    public String readNfcTag(String key){
        String[] temp = new String[2];
        temp[0]="http://192.168.4.1/Client/ReadTag";
        temp[1]=key;
        new PostTask().execute(temp);
        return convertStringReadedToJson(dataReaded,getNameFieldData());
    }

    public void writeNfcTag(String key,String idCard, String dataByJsonString){
        String[] temp = new String[4];
        temp[0] = "http://192.168.4.1/Client/WriteTag";
        temp[1] = key;
        temp[2] = idCard;
        temp[3] = dataByJsonString;
        new PostTask().execute(temp);
    }
    /*--------------------------------------Private Method------------------------*/
    //cắt chuỗi dạng &vdvd#ddfd#cđ#cdv$ thành Json
    private String[] getNameFieldData(){
        String[] nameFieldData = new String[14];
        nameFieldData[0] = "ID";
        nameFieldData[1] = "nameFarm";
        nameFieldData[2] = "earNumber";
        nameFieldData[3] = "dateImport";
        nameFieldData[4] = "dateSex";
        nameFieldData[5] = "numberVaccineParent";
        nameFieldData[6] = "vaccineParent";
        nameFieldData[7] = "dategoat";
        nameFieldData[8] = "numOfChildBorned";
        nameFieldData[9] = "numOfChildDie";
        nameFieldData[10] = "numberVaccinechild";
        nameFieldData[11] = "vaccinechild";
        nameFieldData[12] = "numberVaccineParentNew";
        nameFieldData[13] = "vaccineParentNew";
        return nameFieldData;
    }
    private String convertStringReadedToJson(String str,String[] nameFieldData){
        if(str == null) return null;
        Map<String, String> inputData = new HashMap<String,String>();
        int m = 1;
        int p = 1;
        for (int i = 0; i < str.length() ; i++){
            if(str.charAt(i) == '#') p++;
        }
        String[] buf= new String[p];
        int[] temp = new int[p + 1];
        temp[0]=0;
        temp[p]=str.length()-1;
        if(p != nameFieldData.length) return null;
        if (str.startsWith("$")&&str.endsWith("$")){
            for (int i=1; i < str.length()-1; i++){
                if(str.charAt(i)=='#'){
                    temp[m] = i;
                    m++;
                }
            }
            for(int i = 0; i < p; i++){
                buf[i] = str.substring(temp[i]+1,temp[i+1]);
            }
            String strOut="";
            for (int i = 0; i < p; i++){
                inputData.put(nameFieldData[i],buf[i]);
            }
            return handleJson.setJson(inputData);
        }else return null;
    }
    private class PostTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... data) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(data[0]);
            StringBuilder stringResponse = new StringBuilder();
            try {
                //add data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(data.length-1);
                nameValuePairs.add(new BasicNameValuePair("Key", data[1]));
                if(data.length>2) {
                    nameValuePairs.add(new BasicNameValuePair("ID", data[2]));
                    nameValuePairs.add(new BasicNameValuePair("Data", data[3]));
                }
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //execute http post
                HttpResponse response = httpclient.execute(httppost);
                if (response.getStatusLine().getStatusCode()==200){
                    HttpEntity messageEntity = response.getEntity();
                    InputStream is = messageEntity.getContent();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = br.readLine()) != null) {
                        stringResponse.append(line);
                    }
                }

                dataReaded=stringResponse.toString();

            } catch (IOException e) {

            }
            return dataReaded;
        }
    }
}
