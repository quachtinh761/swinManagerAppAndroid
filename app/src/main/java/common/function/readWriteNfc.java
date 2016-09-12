package common.function;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    public static boolean readCheck = false;
    protected static String readData;
    /*--------------------------------------Public Method------------------------ */
    public static String[] readNfcTag(){
        String html = "http://192.168.4.1/Client/ReadTag?Key=123456123456";
        HttpAsyncTaskRead read = new HttpAsyncTaskRead();
        read.execute(html);
        return convertData.cuttingStringToArray(readData);
    }
    public static boolean writeNfcTag(String datawrite,String pass){

        String html = "192.168.4.1/Client/WriteTag?Key=" + pass;
        html = html + convertData.makeLinkToWrite(convertData.cuttingStringToArray(datawrite));
        HttpAsyncTaskRead wtr = new HttpAsyncTaskRead();
        wtr.execute(html);
        boolean checkwrite = readData.equals("False");
        if (checkwrite) return false;
        else return true;
    }
    /*--------------------------------------Private Method------------------------*/
    private static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    private static class HttpAsyncTaskRead extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            readData = result;
            super.onPostExecute(result);
        }
    }
}
