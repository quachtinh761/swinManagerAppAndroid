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
import java.util.List;

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
    // String urlWrite = "http://192.168.4.1/Client/WriteTag";
    private String dataReaded="";
    private boolean checkReadAndWrite;
    /*--------------------------------------Public Method------------------------ */

    public Boolean readNfcTag(String key){
        String[] temp = new String[2];
        temp[0]="http://192.168.4.1/Client/ReadTag";
        temp[1]=key;
        new PostTask().execute(temp);
        return checkReadAndWrite;
    }

    //public void writeNfcTag(){

    //}
    /*--------------------------------------Private Method------------------------*/
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
