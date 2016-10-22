package common.function;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;

public class handleJson {

    /**
     * Map <String, String> inputData = new HashMap<String,String>();
     * inputData.put("name1","value1");
     * inputData.put("name2","value2");
     **/
    public static String setJson(Map<String, String> inputData){
        String result = "{";
        for (Map.Entry<String, String> entry : inputData.entrySet()){
            String value = "";
            for (int i = 0; i < entry.getValue().length(); i++){
                value += handleString.encodeChar(entry.getValue().charAt(i));
            }
            result += "" + entry.getKey() + ":" + value + ",";
        }
        if (result.endsWith(",")){
            result = result.substring(0, result.length() - 1);
        }
        return (result + "}");
    }

    /**
     * inputObj: {"id":"1234", "name":"chuong1"};
     * String []params = {"id", "name"};
     * getJson(inputObject, params} = {"1234", "chuong1"}
     **/
    public static String[] getJson(Object inputObj, String[] params){
        String[] result = new String[params.length];
        for (int i = 0; i < result.length; i++){
            result[i] = "";
        }
        try{
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(inputObj.toString());
            JSONObject jsonObject = (JSONObject) obj;
            for (int i = 0; i < params.length; i++){
                result[i] = (String) jsonObject.get(params[i]);
            }
        }catch(Exception ex){
        }
        return result;
    }
}
